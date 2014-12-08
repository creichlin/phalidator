package ch.kerbtier.phalidator.expression;

public class XMatches implements XBool {
  private XPression value;
  private XPattern pattern;
  
  public XMatches(XPression value, XPattern pattern) {
    this.value = value;
    this.pattern = pattern;
  }
  
  
  public String toString(){
    return value + " matches " + pattern;
  }
  
}
