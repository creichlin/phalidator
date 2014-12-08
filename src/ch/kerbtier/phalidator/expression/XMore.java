package ch.kerbtier.phalidator.expression;

public class XMore implements XBool {
  private XPression left;
  private XPression right;
  
  public XMore(XPression left, XPression right) {
    this.left = left;
    this.right = right;
  }
  
  public String toString() {
    return "(" + left + " > " + right + ")";
  }
}
