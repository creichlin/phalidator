package ch.kerbtier.phalidator.expression;

public class XField implements XPression, XString {
  private String name;
  
  public XField(String name) {
    this.name = name;
  }
  
  public String toString() {
    if(name == null) {
      return "";
    } else {
      return "$" + name;
    }
  }
}
