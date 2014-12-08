package ch.kerbtier.phalidator.expression;

public class XLettersOperation implements XOperation {
  private XString value;

  public XLettersOperation(XString value) {
    this.value = value;
  }
  
  public String toString() {
    return value + ".chars";
  }
}
