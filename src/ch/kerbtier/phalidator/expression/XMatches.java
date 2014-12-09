package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XMatches implements XBool {
  private XPression value;
  private XPattern pattern;
  
  public XMatches(XPression value, XPattern pattern) {
    this.value = value;
    this.pattern = pattern;
  }
  
  
  public String toString(){
    return value + " matches " + pattern;
  }
  
  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
}
