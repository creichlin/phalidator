package ch.kerbtier.phalidator.expression;

public class XLengthOperation implements XOperation, XNumber {
  private XString value;

  public XLengthOperation(XString value) {
    this.value = value;
  }
  
  public String toString() {
    return value + ".length";
  }
}
