package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XOr implements XBool {
  private XBool left;
  private XBool right;
  
  public XOr(XBool left, XBool right) {
    this.left = left;
    this.right = right;
  }
  
  public String toString() {
    return "(" + left + " OR " + right + ")";
  }
  
  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
}
