package ch.kerbtier.phalidator.expression;

public class XCharsOperation implements XOperation, XString {
  private XString value;

  public XCharsOperation(XString value) {
    this.value = value;
  }
  
  public String toString() {
    return value + ".chars";
  }
}
