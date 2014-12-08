package ch.kerbtier.phalidator.expression;

public class XLess implements XBool {
  private XPression left;
  private XPression right;
  
  public XLess(XPression left, XPression right) {
    this.left = left;
    this.right = right;
  }
  
  public String toString() {
    return "(" + left + " < " + right + ")";
  }
}
