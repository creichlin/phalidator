package ch.kerbtier.phalidator.expression;

public class XIn implements XBool {
  private XPression expression;
  private XSet set;
  
  public XIn(XPression expression, XSet set) {
    this.expression = expression;
    this.set = set;
  }
  
  public String toString() {
    return "(" + expression + " IN " + set + ")";
  }
}
