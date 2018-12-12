import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class RabbitTo1CGate
{
  public RabbitTo1CGate() {}
  
  public static void main(String[] args)
  {
    System.out.println("version 1.5");
    
    String Temp = "";
    
    Map<String, CmdLineParameter> InitialParameters = new HashMap();
    

    for (String Item : args) {
      String ItemValue = Item.toString();
      try {
        int IndexOfDelimiter = ItemValue.indexOf("=");
        String ParameterValue = ItemValue.substring(IndexOfDelimiter + 1, ItemValue.length());
        ParameterName = ItemValue.substring(0, IndexOfDelimiter); } catch (StringIndexOutOfBoundsException ???) { String ParameterName;
        System.out.println("Parameter format is incorrect :" + ItemValue); continue; }
      int IndexOfDelimiter; String ParameterName; String ParameterValue; switch (ParameterName) {
      case "sourcename":  Temp = ItemValue;InitialParameters.put("sourcename", new CmdLineParameter(ParameterValue)); break;
      case "bufserverdnsname":  Temp = ItemValue;InitialParameters.put("bufserverdnsname", new CmdLineParameter(ParameterValue)); break;
      case "bufserverport":  Temp = ItemValue;InitialParameters.put("bufserverport", new CmdLineParameter(Integer.valueOf(ParameterValue))); break;
      case "bufserverusername":  Temp = ItemValue;InitialParameters.put("bufserverusername", new CmdLineParameter(ParameterValue)); break;
      case "bufserverpassword":  Temp = ItemValue;InitialParameters.put("bufserverpassword", new CmdLineParameter(ParameterValue)); break;
      case "bufdatabasename":  Temp = ItemValue;InitialParameters.put("bufdatabasename", new CmdLineParameter(ParameterValue)); break;
      case "rmqserver":  Temp = ItemValue;InitialParameters.put("rmqserver", new CmdLineParameter(ParameterValue)); break;
      case "rmqqueue":  Temp = ItemValue;InitialParameters.put("rmqqueue", new CmdLineParameter(ParameterValue)); break;
      case "rmqport":  Temp = ItemValue;InitialParameters.put("rmqport", new CmdLineParameter(Integer.valueOf(ParameterValue))); break;
      case "rmqusername":  Temp = ItemValue;InitialParameters.put("rmqusername", new CmdLineParameter(ParameterValue)); break;
      case "rmqpassword":  Temp = ItemValue;InitialParameters.put("rmqpassword", new CmdLineParameter(ParameterValue)); break;
      case "numberofmessages":  Temp = ItemValue;InitialParameters.put("numberofmessages", new CmdLineParameter(Long.valueOf(ParameterValue)));
      }
      
    }
    

    Connection Conn = null;
    
    Properties connectionProps = new Properties();
    connectionProps.put("user", ((CmdLineParameter)InitialParameters.get("bufserverusername")).ToString());
    connectionProps.put("password", ((CmdLineParameter)InitialParameters.get("bufserverpassword")).ToString());
    
    connectionProps.put("databaseName", ((CmdLineParameter)InitialParameters.get("bufdatabasename")).ToString());
    
    ProgrammLog MLog = new ProgrammLog(Conn);
    try
    {
      Conn = DriverManager.getConnection("jdbc:sqlserver://" + ((CmdLineParameter)InitialParameters.get("bufserverdnsname")).ToString() + ":" + ((CmdLineParameter)InitialParameters.get("bufserverport")).ToString(), connectionProps);
    } catch (SQLException e) {
      MLog.LogMessage("main\\connecting to buffer", "Programm stopped due critical error:" + e.toString() + " caused by " + e.getCause().toString());
      return;
    }
    


    MLog.LogMessage("main\\", " Connected to database " + Conn.toString());
    
    try
    {
      QueueReader QR = new QueueReader(((CmdLineParameter)InitialParameters.get("sourcename")).ToString(), Conn, ((CmdLineParameter)InitialParameters.get("rmqqueue")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqserver")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqport")).Toint(), ((CmdLineParameter)InitialParameters.get("rmqusername")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqpassword")).ToString(), ((CmdLineParameter)InitialParameters.get("bufdatabasename")).ToString());
      try
      {
        QR.LoadFromQueue(Long.valueOf(((CmdLineParameter)InitialParameters.get("numberofmessages")).ToLong()));
      } catch (NullPointerException Err) {
        QR.LoadFromQueue(new Long(4L));
        MLog.LogMessage("main\\processing queue ", "Parameter - numberofmessages is missing: Only 4 messages will be read");
      }
      
      QR.CloseQueue();
      

      try
      {
        Conn.close();
      } catch (Throwable e) {
        MLog.LogMessage("main\\processing queue ", "Unable connected to queue. Programm stopped due critical error:" + e.toString() + " caused by " + e.getCause().toString());
      }
      
      System.exit(0);
    }
    catch (Throwable AnyException)
    {
      MLog.LogMessage("main\\processing queue ", "Programm stopped due critical error:" + AnyException.toString() + " caused by " + AnyException.getCause().toString());
      return;
    } finally {
      try {
        Conn.close();
      } catch (Throwable e) {
        MLog.LogMessage("main\\processing queue ", "Unable connected to queue. Programm stopped due critical error:" + e.toString() + " caused by " + e.getCause().toString());
      }
    }
  }
}
