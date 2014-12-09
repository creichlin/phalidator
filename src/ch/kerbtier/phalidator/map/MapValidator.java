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
import ch.kerbtier.phalidator.Namespace;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.Rule;
import ch.kerbtier.phalidator.expression.XBool;
import ch.kerbtier.phalidator.expression.XCompare;
import ch.kerbtier.phalidator.expression.XCompare.Operation;
import ch.kerbtier.phalidator.expression.XDecimal;
import ch.kerbtier.phalidator.expression.XField;
import ch.kerbtier.phalidator.expression.XIn;
import ch.kerbtier.phalidator.expression.XLengthOperation;
import ch.kerbtier.phalidator.expression.XPression;
import ch.kerbtier.phalidator.expression.XRange;
import ch.kerbtier.phalidator.expression.XSet;
import ch.kerbtier.phalidator.expression.XString;

public class MapValidator {
  private Namespace namespace;
  private Map<String, String> values;

  public MapValidator(Phalidator phalidator, String entity, Map<String, String> values) {
    this.values = values;
    this.namespace = phalidator.get(entity);
  }

  public boolean isValid() {
    for (Rule rule : namespace) {
      if (!isValid(rule)) {
        return false;
      }
    }
    return true;
  }
  
  public List<String> getInvalidRules() {
    List<String> keys = new ArrayList<String>();
    for (Rule rule : namespace) {
      if (!isValid(rule)) {
        keys.add(rule.getName());
      }
    }
    return keys;
  }


  private boolean isValid(Rule rule) {
    String name = rule.getName();
    XBool expression = rule.getExpression();

    MVisi mVisi = new MVisi(name);

    Object result = expression.accept(mVisi);

    return (Boolean) result;
  }

  class MVisi extends AbstractVisitor {

    private String name;

    public MVisi(String name) {
      this.name = name;
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
      }
      throw new InvalidOperandsException(left + " " + right);
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

    @SuppressWarnings("unchecked")
    @Override
    public Object visit(XIn xIn) {
      XPression value = xIn.getValue();
      XSet set = xIn.getSet();
      Object v = value.accept(this);
      Object s = set.accept(this);

      Set<Object> right = toSet(s);
      Set<Object> left = toSet(v);

      return right.containsAll(left);
    }

    private Set<Object> toSet(Object v) {
      Set<Object> left = new HashSet<Object>();
      if (v instanceof Collection<?>) {
        left.addAll((Collection<Object>) v);
      } else if (v instanceof String) {
        for (String letter : ((String) v).split("(?!^)")) {
          left.add(letter);
        }
      } else {
        left.add(v);
      }
      return left;
    }

    @Override
    // TODO, create a range set instead of a list containing all the actual values.
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
  }

}
