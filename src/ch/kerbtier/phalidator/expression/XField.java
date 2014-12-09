package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

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

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }

  public String getName() {
    return name;
  }
}
