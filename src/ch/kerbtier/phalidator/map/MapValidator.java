package ch.kerbtier.phalidator.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.kerbtier.phalidator.Namespace;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.Rule;
import ch.kerbtier.phalidator.expression.XBool;

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

    MapValidatorVisitor mVisi = new MapValidatorVisitor(name, values);

    Object result = expression.accept(mVisi);

    return (Boolean) result;
  }

}
