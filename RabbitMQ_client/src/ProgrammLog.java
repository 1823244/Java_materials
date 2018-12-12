import java.io.PrintStream;
import java.sql.Connection;


class ProgrammLog
{
  private Connection JDBCConnect;
  
  public ProgrammLog(Connection PJDBCConnect) {}
  
  public Status LogMessage(String MsgSource, String MsgText)
  {
    System.out.println(" Источник: " + MsgSource + " Сообщение: " + MsgText);
    return null;
  }
}
