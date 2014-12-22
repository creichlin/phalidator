package ch.kerbtier.phalidator.rt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Util {
  public static boolean in(Object value, Object set) {
    
    Set<Object> left = new HashSet<Object>();
    Set<Object> right = new HashSet<Object>();
    
    if(value instanceof Collection) {
      left.addAll((Collection)value);
    } else {
      left.add(value);
    }
    
    if(set instanceof String) {
      right.addAll(Arrays.asList(((String) set).split("(?!^)")));
    } else if(set instanceof Collection) {
      right.addAll((Collection)set);
    } else {
      throw new RuntimeException("invalid in operands " + value + " in " + set);
    }
    
    return right.containsAll(left);
  }

  public static Collection range(Object start, Object end) {
    if(start instanceof BigDecimal && end instanceof BigDecimal) {
      List<BigDecimal> values = new ArrayList<BigDecimal>();
      
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
    
    throw new RuntimeException("invalid range operands for " + start + ".." + end);
  }

  public static boolean matches(Object input, Object pattern) {
    
    if(input instanceof String && pattern instanceof Pattern) {
      Pattern p = (Pattern)pattern;
      return p.matcher((String)input).matches();
    }
    
    throw new RuntimeException("invalid operands for " + input + " matches " + pattern);
  }
}
