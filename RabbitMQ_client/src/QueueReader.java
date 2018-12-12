import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.GetResponse;
import java.io.PrintStream;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;


class QueueReader
{
  private ProgrammLog QLog;
  private String QSourceName;
  private Channel QOpenedChannel;
  private java.sql.Connection QJDBCConnect;
  private String QQueueName;
  private String QBufDbName;
  
  public QueueReader(String PSourceName, java.sql.Connection PJDBCConnect, String PQueueName, String PRabbitServer, int PRabbitServerPort, String PRMQUserName, String PRMQUserPassword, String PRMQBufDbName)
    throws Throwable
  {
    try
    {
      QLog = new ProgrammLog(PJDBCConnect);
      QQueueName = PQueueName;
      QJDBCConnect = PJDBCConnect;
      QSourceName = PSourceName;
      QBufDbName = PRMQBufDbName;
      
      ConnectionFactory factory = new ConnectionFactory();
      
      System.out.println(" Begin authorization");
      
      factory.setHost(PRabbitServer);
      QLog.LogMessage("class QueueReader", " Default port " + factory.getPort());
      factory.setPort(PRabbitServerPort);
      factory.setUsername(PRMQUserName);
      factory.setPassword(PRMQUserPassword);
      

      QLog.LogMessage("class QueueReader", " TCP connection Timeout " + factory.getConnectionTimeout());
      QLog.LogMessage("class QueueReader", " Handshake connection Timeout " + factory.getHandshakeTimeout());
      
      com.rabbitmq.client.Connection connection = null;
      
      connection = factory.newConnection();
      
      QLog.LogMessage("class QueueReader", " Authorization passed");
      
      QOpenedChannel = connection.createChannel();
      



      QOpenedChannel.queueDeclarePassive(PQueueName);
      
      QLog.LogMessage("class QueueReader", " [*] Waiting for messages. To exit press CTRL+C");
    }
    finally {}
  }
  

  public Status LoadFromQueue(Long PRecords)
    throws Throwable
  {
    boolean autoAck = false;
    String BigXml = "";
    long MCount = 0L;
    
    try
    {
      PreparedStatement InsertMsg = QJDBCConnect.prepareStatement("INSERT INTO " + QBufDbName + ".dbo.msg_buffer(MsgXMLText,MsgVarCharMax,MsgHeader,MsgID,MsgDescription,MsgSource,MsgReceiveTime) VALUES (?,?,?,?,?,?,?)");
      
      for (int i = 0; i < PRecords.longValue(); i++)
      {
        GetResponse response = QOpenedChannel.basicGet(QQueueName, autoAck);
        
        if (response == null) {
          break;
        }
        
        AMQP.BasicProperties props = response.getProps();
        long messagecount = response.getMessageCount();
        Envelope Env = response.getEnvelope();
        String RoutingKey = Env.getRoutingKey();
        String Exchange = Env.getExchange();
        
        byte[] body = response.getBody();
        String messagetext = new String(body, "UTF-8");
        
        long deliveryTag = response.getEnvelope().getDeliveryTag();
        String Msgid = props.getMessageId();
        
        Map<String, Object> Headers = props.getHeaders();
        

        BigXml = "";
        
        QJDBCConnect.setAutoCommit(false);
        InsertMsg.setNString(1, messagetext);
        InsertMsg.setNString(2, BigXml);
        InsertMsg.setNString(3, RoutingKey);
        InsertMsg.setNString(4, Msgid);
        

        InsertMsg.setNString(5, RoutingKey + " " + Exchange);
        InsertMsg.setNString(6, QSourceName);
        InsertMsg.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
        try {
          int nInsertedRows = InsertMsg.executeUpdate();
          QJDBCConnect.commit();
          QOpenedChannel.basicAck(deliveryTag, false);
        }
        catch (Throwable CommitError)
        {
          QLog.LogMessage("class QueueReader.LoadFromQueue ", "Cannot commit message with MsgId " + Msgid + " due following error: " + CommitError.toString());
          QOpenedChannel.basicNack(deliveryTag, false, true);
          
          break;
        }
        
        MCount += 1L;

      }
      

    }
    finally
    {

      QLog.LogMessage("class QueueReader.LoadFromQueue", "Readed\\Saved messages " + MCount);
    }
    PreparedStatement InsertMsg;
    return new Status(true, QueueStatus.SUCCESS, "Readed Saved messages: " + MCount);
  }
  
  public Status CloseQueue()
    throws Throwable
  {
    try
    {
      QOpenedChannel.close();
      QLog.LogMessage("class QueueReader.CloseQueue", " Queue " + QOpenedChannel.toString() + " is close");
    }
    finally {}
    


    return new Status(true, QueueStatus.SUCCESS, "Connection closed successfully");
  }
}
