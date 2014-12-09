package ch.kerbtier.phalidator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Namespace implements Iterable<Rule>, TreeElement {
  private Map<String, Namespace> children = new LinkedHashMap<String, Namespace>();
  private List<Rule> rules = new ArrayList<Rule>();

  private final String name;

  public Namespace(String name) {
    this.name = name;
  }

  public void add(Namespace namespace) {
    if (children.containsKey(namespace.getName())) {
      throw new RuntimeException("namespace exists already");
    }
    children.put(namespace.getName(), namespace);
  }

  private String getName() {
    return name;
  }
  
  public Namespace get(String entity) {
    if(children.containsKey(entity)) {
      return children.get(entity);
    }
    throw new EntityNotFoundException(entity);
  }

  @Override
  public Iterator<Rule> iterator() {
    return rules.iterator();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    toString(sb, 0);

    return sb.toString();
  }

  private void toString(StringBuilder sb, int indentation) {
    String indent = "";
    for (int cnt = 0; cnt < indentation; cnt++) {
      indent += "  ";
    }

    sb.append(indent + name + " {\n");

    for(Rule r: rules) {
      sb.append(indent + "  " + r.getName() + ": " + r.getExpression() + "\n");
    }
    
    
    for (Namespace child : children.values()) {
      child.toString(sb, indentation + 1);
    }
    
    

    sb.append(indent + "}\n");
  }

  public void add(Rule rule) {
    rules.add(rule);
  }

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
}
