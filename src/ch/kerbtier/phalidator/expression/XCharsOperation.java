package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XCharsOperation implements XOperation, XString {
  private XString value;

  public XCharsOperation(XString value) {
    this.value = value;
  }
  
  public String toString() {
    return value + ".chars";
  }

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }

  public XString getField() {
    return value;
  }
}
