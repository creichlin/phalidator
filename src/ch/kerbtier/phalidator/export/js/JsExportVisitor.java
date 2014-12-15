package ch.kerbtier.phalidator.export.js;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

import ch.kerbtier.phalidator.AbstractVisitor;
import ch.kerbtier.phalidator.Namespace;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.Rule;
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
import ch.kerbtier.phalidator.expression.XPression;
import ch.kerbtier.phalidator.expression.XRange;
import ch.kerbtier.phalidator.expression.XRegexp;
import ch.kerbtier.phalidator.expression.XCompare.Operation;

public class JsExportVisitor extends AbstractVisitor {
  
  private String name;
  
  public JsExportVisitor(String name) {
    this.name = name;
  }
  
  private String currentField;

  @Override
  public Object visit(Phalidator phalidator) {
    return visit((Namespace) phalidator);
  }

  @Override
  public Object visit(Namespace namespace) {
    String ret;
    if (namespace.getName() == null) {
      ret = "var phalidatorData = phalidatorData || {};\nphalidatorData." + name + " = {\n";
    } else {
      ret = namespace.getName() + ": {\n";
    }

    ret += indent("ns: {\n");
    for (String ns : namespace.getNamespaces()) {
      ret += indent(indent((String) namespace.get(ns).accept(this)));
    }
    ret += indent("},\n"); 
    
    ret += indent("rules: {\n");
    
    for(Rule r: namespace) {
      ret += indent(indent((String)r.accept(this)));
    }
    
    ret += indent("}\n");

    if (namespace.getName() == null) {
      ret += "}\n";
    } else {
      ret += "},\n";
    }
    
    return ret;
  }
  
  @Override
  public Object visit(Rule rule) {
    String ret = rule.getName() + ": function(hash, ph){\n";
    currentField = rule.getName();
    ret += indent("return " + rule.getExpression().accept(this) + ";\n");
    ret += "},\n";
    return ret;
  }

  private String indent(String in) {
    return in.replaceAll("(?m)^", "  ");
  }

  @Override
  public Object visit(XCompare xCompare) {
    Operation op = xCompare.getOperation();
    Object left = xCompare.getLeft().accept(this);
    Object right = xCompare.getRight().accept(this);
    return "(" + left + " " + op.sign() + " " + right + ")";
  }

  @Override
  public Object visit(XLengthOperation xLengthOperation) {
    Object result = xLengthOperation.getField().accept(this);
    return result + ".length";
  }

  @Override
  public Object visit(XCharsOperation xCharsOperation) {
    Object field = xCharsOperation.getField().accept(this);
    return field + " + ''";
  }

  @Override
  public Object visit(XLettersOperation xLettersOperation) {
    Object field = xLettersOperation.getField().accept(this);
    return "ph.letters(" + field + ")";
  }

  @Override
  public Object visit(XField xField) {
    String name = xField.getName();
    return "hash['" + (name == null ? currentField : name) + "']";
  }

  @Override
  public Object visit(XDecimal xDecimal) {
    return xDecimal.getValue() + "";
  }

  @Override
  public Object visit(XIn xIn) {
    Object set = xIn.getSet().accept(this);
    Object value = xIn.getValue().accept(this);

    return "ph.in(" + value + ", " + set + ")";
  }

  @Override
  // TODO, create a range set instead of a list containing all the actual
  // values.
  public Object visit(XRange xRange) {
    Object start = xRange.getStart().accept(this);
    Object end = xRange.getEnd().accept(this);
    return "ph.range(" + start + ", " + end + ")";
  }

  @Override
  public Object visit(XArray xArray) {
    List<Object> values = new ArrayList<Object>();
    for (XPression element : xArray) {
      values.add(element.accept(this));
    }
    return "[" + Joiner.on(", ").join(values) + "]";
  }

  @Override
  public Object visit(XChars xChars) {
    return "'" + xChars.getValue() + "'";
  }

  @Override
  public Object visit(XAnd xAnd) {
    Object left = xAnd.getLeft().accept(this);
    Object right = xAnd.getRight().accept(this);

    return "(" + left + " && " + right + ")";
  }

  @Override
  public Object visit(XOr xOr) {
    Object left = xOr.getLeft().accept(this);
    Object right = xOr.getRight().accept(this);

    return "(" + left + " || " + right + ")";
  }

  @Override
  public Object visit(XNot xNot) {
    Object operand = xNot.getOperand().accept(this);

    return "!(" + operand + ")";
  }

  @Override
  public Object visit(XMatches xMatches) {
    Object value = xMatches.getValue().accept(this);
    Object pattern = xMatches.getPattern().accept(this);
    
    return "ph.matches(" + value + ", " + pattern + ")";
  }

  @Override
  public Object visit(XRegexp xRegexp) {
    return "'" + xRegexp.getPattern() + "'";
  }
}
