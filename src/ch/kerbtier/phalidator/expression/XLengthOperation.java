package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XLengthOperation implements XOperation, XNumber {
  private XString value;

  public XLengthOperation(XString value) {
    this.value = value;
  }
  
  public String toString() {
    return value + ".length";
  }
  
  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }

  public XString getField() {
    return value;
  }
}
