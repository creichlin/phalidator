package ch.kerbtier.phalidator.expression;

public class XAnd implements XBool {
  private XBool left;
  private XBool right;
  
  public XAnd(XBool left, XBool right) {
    this.left = left;
    this.right = right;
  }
  
  public String toString() {
    return "(" + left + " AND " + right + ")";
  }
}
