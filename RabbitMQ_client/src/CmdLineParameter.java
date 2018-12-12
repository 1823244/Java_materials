
class CmdLineParameter
{
  private Integer intValue;
  private String StringValue;
  private Long longValue;
  
  public CmdLineParameter(Integer Value)
  {
    intValue = Value;StringValue = null;longValue = null; }
  public CmdLineParameter(String Value) { intValue = null;StringValue = Value;longValue = null; }
  public CmdLineParameter(Long Value) { intValue = null;StringValue = null;longValue = Value; }
  
  public int Toint() { return intValue.intValue(); }
  public String ToString() { if (StringValue != null) return StringValue; if (intValue != null) return intValue.toString(); if (longValue != null) return longValue.toString();
    return null; }
  
  public long ToLong() { if (longValue != null) return longValue.longValue(); return intValue.intValue();
  }
}
