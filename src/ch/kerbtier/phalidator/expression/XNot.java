package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XNot implements XBool {
  private XBool expression;
  
  public XNot(XBool expression) {
    this.expression = expression;
  }
  
  public String toString() {
    return "!(" + expression + ")";
  }
  
  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
}
