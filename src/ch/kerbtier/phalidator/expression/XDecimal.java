package ch.kerbtier.phalidator.expression;

import java.math.BigDecimal;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XDecimal implements XNumber, XString {
  private BigDecimal value;

  public XDecimal(int val) {
    this.value = new BigDecimal(val);
  }
  
  public String toString() {
    return value.toString();
  }

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }

  public Object getValue() {
    return value;
  }
}
