package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

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

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }

  public XBool getLeft() {
    return left;
  }

  public XBool getRight() {
    return right;
  }
}
