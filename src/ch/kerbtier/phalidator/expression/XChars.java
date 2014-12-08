package ch.kerbtier.phalidator.expression;

public class XChars implements XString, XSet {
  private String value;

  public XChars(String value) {
    this.value = value;
  }
  
  public String toString() {
    return "\"" + value + "\"";
  }
}
