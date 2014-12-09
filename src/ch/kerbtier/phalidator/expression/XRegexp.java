package ch.kerbtier.phalidator.expression;

import java.util.regex.Pattern;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XRegexp implements XPattern {
  private Pattern pattern;
  
  public XRegexp(String pattern) {
    this.pattern = Pattern.compile(pattern);
  }
  
  public String toString() {
    return pattern.toString();
  }
  
  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
}
