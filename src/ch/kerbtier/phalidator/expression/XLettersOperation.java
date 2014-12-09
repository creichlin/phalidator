package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XLettersOperation implements XOperation {
  private XString value;

  public XLettersOperation(XString value) {
    this.value = value;
  }
  
  public String toString() {
    return value + ".chars";
  }
  
  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
}
