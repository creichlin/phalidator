package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XChars implements XString, XSet {
  private String value;

  public XChars(String value) {
    this.value = value;
  }
  
  public String toString() {
    return "\"" + value + "\"";
  }

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }

  public String getValue() {
    return value;
  }
}
