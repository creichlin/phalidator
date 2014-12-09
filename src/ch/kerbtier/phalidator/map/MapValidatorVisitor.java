package ch.kerbtier.phalidator.map;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.kerbtier.phalidator.AbstractVisitor;
import ch.kerbtier.phalidator.InvalidOperandsException;
import ch.kerbtier.phalidator.expression.XAnd;
import ch.kerbtier.phalidator.expression.XArray;
import ch.kerbtier.phalidator.expression.XBool;
import ch.kerbtier.phalidator.expression.XChars;
import ch.kerbtier.phalidator.expression.XCharsOperation;
import ch.kerbtier.phalidator.expression.XCompare;
import ch.kerbtier.phalidator.expression.XDecimal;
import ch.kerbtier.phalidator.expression.XField;
import ch.kerbtier.phalidator.expression.XIn;
import ch.kerbtier.phalidator.expression.XLengthOperation;
import ch.kerbtier.phalidator.expression.XLettersOperation;
import ch.kerbtier.phalidator.expression.XNot;
import ch.kerbtier.phalidator.expression.XOr;
import ch.kerbtier.phalidator.expression.XPression;
import ch.kerbtier.phalidator.expression.XRange;
import ch.kerbtier.phalidator.expression.XSet;
import ch.kerbtier.phalidator.expression.XString;
import ch.kerbtier.phalidator.expression.XCompare.Operation;

public class MapValidatorVisitor extends AbstractVisitor {

  private String name;
  private Map<String, String> values;

  public MapValidatorVisitor(String name, Map<String, String> values) {
    this.name = name;
    this.values = values;
  }

  @Override
  public Object visit(XCompare xCompare) {
    XPression left = xCompare.getLeft();
    XPression right = xCompare.getRight();
    Operation op = xCompare.getOperation();
    Object l = left.accept(this);
    Object r = right.accept(this);
    if (l instanceof BigDecimal && r instanceof BigDecimal) {
      if (op == Operation.MORE) {
        return ((BigDecimal) l).compareTo((BigDecimal) r) > 0;
      } else if (op == Operation.MORE_EQUAL) {
        return ((BigDecimal) l).compareTo((BigDecimal) r) >= 0;
      } else if (op == Operation.LESS) {
        return ((BigDecimal) l).compareTo((BigDecimal) r) < 0;
      } else if (op == Operation.LESS_EQUAL) {
        return ((BigDecimal) l).compareTo((BigDecimal) r) <= 0;
      } else if (op == Operation.EQUAL) {
        return ((BigDecimal) l).compareTo((BigDecimal) r) == 0;
      }
    } else if(l instanceof String && r instanceof String) {
      if (op == Operation.EQUAL) {
        return l.equals(r);
      }
    }
    throw new InvalidOperandsException(xCompare + " ");
  }

  @Override
  public Object visit(XLengthOperation xLengthOperation) {
    XString field = xLengthOperation.getField();
    Object result = field.accept(this);
    if (result instanceof String) {
      return new BigDecimal(((String) result).length());
    }
    throw new InvalidOperandsException(result + "");
  }
  
  @Override
  public Object visit(XCharsOperation xCharsOperation) {
    XString field = xCharsOperation.getField();
    Object result = field.accept(this);
    if (result instanceof String) {
      return result;
    }
    throw new InvalidOperandsException(result + "");
  }

  @Override
  public Object visit(XLettersOperation xLettersOperation) {
    XString field = xLettersOperation.getField();
    Object result = field.accept(this);
    if (result instanceof String) {
      Set<String> letters = new HashSet<String>();
      for (String letter : ((String) result).split("(?!^)")) {
        letters.add(letter);
      }
      return letters;
    }
    throw new InvalidOperandsException(result + "");
  }

  @Override
  public Object visit(XField xField) {
    String name = xField.getName();

    if (name == null) {
      return values.get(this.name);
    }
    return values.get(name);
  }

  @Override
  public Object visit(XDecimal xDecimal) {
    return xDecimal.getValue();
  }

  @Override
  public Object visit(XIn xIn) {
    Set<Object> set = toSet(xIn.getSet().accept(this), true);
    Set<Object> value = toSet(xIn.getValue().accept(this), false);

    return set.containsAll(value);
  }

  private Set<Object> toSet(Object v, boolean splitStrings) {
    Set<Object> left = new HashSet<Object>();
    if (v instanceof Collection<?>) {
      left.addAll((Collection<?>) v);
    } else if (v instanceof String && splitStrings) {
      for (String letter : ((String) v).split("(?!^)")) {
        left.add(letter);
      }
    } else {
      left.add(v);
    }
    return left;
  }

  @Override
  // TODO, create a range set instead of a list containing all the actual
  // values.
  public Object visit(XRange xRange) {
    Object start = xRange.getStart().accept(this);
    Object end = xRange.getEnd().accept(this);
    List<Object> values = new ArrayList<Object>();
    if (start instanceof BigDecimal && end instanceof BigDecimal) {
      BigDecimal val = (BigDecimal) start;
      if (val.compareTo((BigDecimal) end) < 0) {
        while (val.compareTo((BigDecimal) end) <= 0) {
          values.add(val);
          val = val.add(BigDecimal.ONE);
        }
      } else {
        while (val.compareTo((BigDecimal) end) >= 0) {
          values.add(val);
          val = val.subtract(BigDecimal.ONE);
        }
      }
      return values;
    }

    throw new InvalidOperandsException(xRange + "");
  }

  @Override
  public Object visit(XArray xArray) {
    List<Object> values = new ArrayList<Object>();
    for(XPression element: xArray) {
      values.add(element.accept(this));
    }
    return values;
  }

  @Override
  public Object visit(XChars xChars) {
    return xChars.getValue();
  }

  @Override
  public Object visit(XAnd xAnd) {
    Object left = xAnd.getLeft().accept(this);
    Object right = xAnd.getRight().accept(this);
    
    if(left instanceof Boolean && right instanceof Boolean) {
      return ((Boolean)left) && ((Boolean)right);
    }
    throw new InvalidOperandsException(xAnd + "");
  }

  @Override
  public Object visit(XOr xOr) {
    Object left = xOr.getLeft().accept(this);
    Object right = xOr.getRight().accept(this);
    
    if(left instanceof Boolean && right instanceof Boolean) {
      return ((Boolean)left) && ((Boolean)right);
    }
    throw new InvalidOperandsException(xOr + "");
  }

  @Override
  public Object visit(XNot xNot) {
    Object operand = xNot.getOperand().accept(this);
    
    if(operand instanceof Boolean) {
      return !((Boolean)operand);
    }
    throw new InvalidOperandsException(xNot + "");
  }
  
  
}
