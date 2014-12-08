package ch.kerbtier.phalidator.expression;

public class XMoreEqual implements XBool {
  private XPression left;
  private XPression right;
  
  public XMoreEqual(XPression left, XPression right) {
    this.left = left;
    this.right = right;
  }
  
  public String toString() {
    return "(" + left + " >= " + right + ")";
  }
}
