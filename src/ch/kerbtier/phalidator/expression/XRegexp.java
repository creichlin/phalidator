package ch.kerbtier.phalidator.expression;

import java.util.regex.Pattern;

public class XRegexp implements XPattern {
  private Pattern pattern;
  
  public XRegexp(String pattern) {
    this.pattern = Pattern.compile(pattern);
  }
  
  public String toString() {
    return pattern.toString();
  }
}
