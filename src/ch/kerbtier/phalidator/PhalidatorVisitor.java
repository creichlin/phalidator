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

public interface PhalidatorVisitor {
  Object visit(Namespace namespace);

  Object visit(Phalidator phalidator);

  Object visit(Rule rule);

  Object visit(XAnd xAnd);

  Object visit(XArray xArray);

  Object visit(XChars xChars);

  Object visit(XDecimal xDecimal);

  Object visit(XRegexp xRegexp);

  Object visit(XRange xRange);

  Object visit(XOr xOr);

  Object visit(XNot xNot);

  Object visit(XField xField);

  Object visit(XIn xIn);

  Object visit(XLengthOperation xLengthOperation);

  Object visit(XLettersOperation xLettersOperation);

  Object visit(XMatches xMatches);

  Object visit(XCharsOperation xCharsOperation);

  Object visit(XCompare xCompare);
}
