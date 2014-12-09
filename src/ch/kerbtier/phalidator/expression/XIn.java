package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

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
  
  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }

  public XPression getValue() {
    return expression;
  }

  public XSet getSet() {
    return set;
  }
}
