import java.io.PrintStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class From1CtoRabbit
{
  public From1CtoRabbit() {}
  
  public static void main(String[] args)
    throws Throwable
  {
    System.out.println("version 1.0");
    
    String Temp = "";
    
    Map<String, CmdLineParameter> InitialParameters = new HashMap();
    

    for (String Item : args) {
      String ItemValue = Item.toString();
      try {
        int IndexOfDelimiter = ItemValue.indexOf("=");
        String ParameterValue = ItemValue.substring(IndexOfDelimiter + 1, ItemValue.length());
        ParameterName = ItemValue.substring(0, IndexOfDelimiter);
      } catch (StringIndexOutOfBoundsException ???) { String ParameterName;
        System.out.println("Parameter format is incorrect :" + ItemValue);
        continue; }
      int IndexOfDelimiter;
      String ParameterName; String ParameterValue; switch (ParameterName) {
      case "sourcename": 
        Temp = ItemValue;
        InitialParameters.put("sourcename", new CmdLineParameter(ParameterValue));
        break;
      
      case "rmqserver": 
        Temp = ItemValue;
        InitialParameters.put("rmqserver", new CmdLineParameter(ParameterValue));
        break;
      
      case "rmqqueue": 
        Temp = ItemValue;
        InitialParameters.put("rmqqueue", new CmdLineParameter(ParameterValue));
        break;
      
      case "rmqport": 
        Temp = ItemValue;
        InitialParameters.put("rmqport", new CmdLineParameter(Integer.valueOf(ParameterValue)));
        break;
      
      case "rmqusername": 
        Temp = ItemValue;
        InitialParameters.put("rmqusername", new CmdLineParameter(ParameterValue));
        break;
      
      case "rmqpassword": 
        Temp = ItemValue;
        InitialParameters.put("rmqpassword", new CmdLineParameter(ParameterValue));
        break;
      
      case "filemask": 
        Temp = ItemValue;
        InitialParameters.put("filemask", new CmdLineParameter(ParameterValue));
        break;
      

      case "rmqexchange": 
        Temp = ItemValue;
        InitialParameters.put("rmqexchange", new CmdLineParameter(ParameterValue));
        break;
      

      case "filespath": 
        Temp = ItemValue;
        InitialParameters.put("filespath", new CmdLineParameter(ParameterValue));
        break;
      

      case "rmqcommonheader": 
        Temp = ItemValue;
        InitialParameters.put("rmqcommonheader", new CmdLineParameter(ParameterValue));
        break;
      

      case "clearfilesolderthendays": 
        Temp = ItemValue;
        InitialParameters.put("clearfilesolderthendays", new CmdLineParameter(Integer.valueOf(ParameterValue)));
        break;
      

      case "batchmode": 
        Temp = ItemValue;
        InitialParameters.put("batchmode", new CmdLineParameter(ParameterValue));
      }
      
    }

    ProgrammLog MLog = new ProgrammLog();
    MLog.LogMessage(" " + From1CtoRabbit.class.getName() + "\\main", "Start send to Queue ");

    if (((CmdLineParameter)InitialParameters.get("batchmode")).ToString().compareTo("sendfile") == 0)
    {
      QueueWriter QW = null;
      try {
        QW = new QueueWriter(((CmdLineParameter)InitialParameters.get("sourcename")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqqueue")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqserver")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqport")).Toint(), ((CmdLineParameter)InitialParameters.get("rmqusername")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqpassword")).ToString());

        Path VFilesForSendPath = Paths.get(((CmdLineParameter)InitialParameters.get("filespath")).ToString(), new String[0]);
        Path VFilesParentDir = VFilesForSendPath.getParent();
        DirectoryStream<Path> SelectedFiles = Files.newDirectoryStream(VFilesForSendPath, ((CmdLineParameter)InitialParameters.get("filemask")).ToString());
        
        for (Path SelectedFilE : SelectedFiles)
        {
          QW.SendFileToQueue(SelectedFilE, ((CmdLineParameter)InitialParameters.get("rmqexchange")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqqueue")).ToString(), ((CmdLineParameter)InitialParameters.get("rmqcommonheader")).ToString());
        }
      }
      catch (Throwable AnyException)
      {
        MLog.LogMessage("main\\processing queue ", "Programm stopped due critical error:" + AnyException.toString() + " caused by " + AnyException.getCause().toString());
      } finally {
        QW.CloseQueue();
      }
    }
    System.exit(0);
  }
}
