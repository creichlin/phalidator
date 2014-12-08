package ch.kerbtier.phalidator.expression;

public class XNot implements XBool {
  private XBool expression;
  
  public XNot(XBool expression) {
    this.expression = expression;
  }
  
  public String toString() {
    return "!(" + expression + ")";
  }
}
