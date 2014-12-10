package ch.kerbtier.phalidator;

import ch.kerbtier.phalidator.expression.XPression;

@SuppressWarnings("serial")
public class InvalidOperandsException extends RuntimeException {

  public InvalidOperandsException(XPression xpression) {
    super(xpression.toString());
  }

}
