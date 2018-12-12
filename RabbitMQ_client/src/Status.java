class Status
{
  public boolean Success;
  public String StatusMessage;
  
  public Status(boolean Psuccess, QueueStatus PDetailedStatus, String PStatusMessage)
  {
    Success = Psuccess;StatusMessage = (" " + PDetailedStatus + " " + PStatusMessage);
  }
}