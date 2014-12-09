package ch.kerbtier.phalidator;

import ch.kerbtier.phalidator.expression.XBool;

public class Rule implements TreeElement {

  private final String name;
  private final XBool expression;
  
  public Rule(String name, XBool expression) {
    this.name = name;
    this.expression = expression;
  }

  public String getName() {
    return name;
  }

  public XBool getExpression() {
    return expression;
  }

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
}
