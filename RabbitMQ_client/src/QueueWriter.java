import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.PrintStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

class QueueWriter
{
  private ProgrammLog QLog;
  private String QSourceName;
  private Channel QOpenedChannel;
  private String QQueueName;
  
  public QueueWriter(String PSourceName, String PQueueName, String PRabbitServer, int PRabbitServerPort, String PRMQUserName, String PRMQUserPassword)
    throws Throwable
  {
    try
    {
      QLog = new ProgrammLog();
      QQueueName = PQueueName;
      
      QSourceName = PSourceName;
      
      System.out.println(" Begin authorization");
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost(PRabbitServer);
      QLog.LogMessage("class QueueReader", " Default port " + factory.getPort());
      factory.setPort(PRabbitServerPort);
      factory.setUsername(PRMQUserName);
      factory.setPassword(PRMQUserPassword);
      
      QLog.LogMessage("class QueueReader", " TCP connection Timeout " + factory.getConnectionTimeout());
      QLog.LogMessage("class QueueReader", " Handshake connection Timeout " + factory.getHandshakeTimeout());
      
      QLog.LogMessage("class QueueReader", " Authorization passed");
      Connection connection = factory.newConnection();
      QOpenedChannel = connection.createChannel();

      QOpenedChannel.queueDeclarePassive(PQueueName);
      
      QLog.LogMessage("class QueueWriter", " [*] Waiting for messages. To exit press CTRL+C");

    }
    catch (Throwable AnyException)
    {

      AnyException = 

        AnyException;QLog.LogMessage("QueueWriter constructor ", "Programm stopped due critical error:" + AnyException.toString() + " caused by " + AnyException.getCause().toString());
    }
    finally {}
  }

  public Status SendFileToQueue(Path PmsgFile, String PRmqExchange, String PRmqQueue, String PRmqCommonHeader)
    throws Throwable
  {
    String[] FileNameAsArray = PmsgFile.getFileName().toString().split("\\_");
    byte[] messageBodyBytes = Files.readAllBytes(PmsgFile);
    Map<String, Object> BPropHeaders = new HashMap();
    BPropHeaders.put("MainHeader", PRmqCommonHeader);

    AMQP.BasicProperties BProp = new AMQP.BasicProperties.Builder().contentType("XML").contentEncoding("UTF-8").headers(BPropHeaders).deliveryMode(Integer.valueOf(2)).build();
    QOpenedChannel.basicPublish(PRmqExchange, FileNameAsArray[1], BProp, messageBodyBytes);
    
    Files.move(PmsgFile, PmsgFile.resolveSibling(PmsgFile.toString().replace(".xml", ".rmqpayload_sent")), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
    return new Status(true, QueueStatus.SUCCESS, "Message sent sacessfully");
  }
  
  public Status CloseQueue() throws Throwable
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
