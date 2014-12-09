package ch.kerbtier.phalidator;

import ch.kerbtier.phalidator.expression.XAnd;
import ch.kerbtier.phalidator.expression.XArray;
import ch.kerbtier.phalidator.expression.XChars;
import ch.kerbtier.phalidator.expression.XCharsOperation;
import ch.kerbtier.phalidator.expression.XCompare;
import ch.kerbtier.phalidator.expression.XDecimal;
import ch.kerbtier.phalidator.expression.XField;
import ch.kerbtier.phalidator.expression.XIn;
import ch.kerbtier.phalidator.expression.XLengthOperation;
import ch.kerbtier.phalidator.expression.XLettersOperation;
import ch.kerbtier.phalidator.expression.XMatches;
import ch.kerbtier.phalidator.expression.XNot;
import ch.kerbtier.phalidator.expression.XOr;
import ch.kerbtier.phalidator.expression.XRange;
import ch.kerbtier.phalidator.expression.XRegexp;

public class AbstractVisitor implements PhalidatorVisitor {

  @Override
  public Object visit(Namespace namespace) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Phalidator phalidator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(Rule rule) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XAnd xAnd) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XArray xArray) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XChars xChars) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XDecimal xDecimal) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XRegexp xRegexp) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XRange xRange) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XOr xOr) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XNot xNot) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XField xField) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XIn xIn) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XLengthOperation xLengthOperation) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XCompare xCompare) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XLettersOperation xLettersOperation) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XMatches xMatches) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object visit(XCharsOperation xCharsOperation) {
    throw new UnsupportedOperationException();
  }

}
