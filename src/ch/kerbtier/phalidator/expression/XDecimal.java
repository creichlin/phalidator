package ch.kerbtier.phalidator.expression;

import java.math.BigDecimal;

public class XDecimal implements XNumber, XString {
  private BigDecimal value;

  public XDecimal(int val) {
    this.value = new BigDecimal(val);
  }
  
  public String toString() {
    return value.toString();
  }
}
