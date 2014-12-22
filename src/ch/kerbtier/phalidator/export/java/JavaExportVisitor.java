package ch.kerbtier.phalidator.export.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

import com.google.common.base.CaseFormat;
import com.sun.codemodel.JArray;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

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
import ch.kerbtier.phalidator.rt.Util;
import ch.kerbtier.phalidator.rt.Validateable;

public class JavaExportVisitor extends AbstractVisitor {

  private Stack<JDefinedClass> main = new Stack<JDefinedClass>();
  private Stack<JExpression> isValidReturn = new Stack<JExpression>();
  private Stack<JBlock> getInvalidBlock = new Stack<JBlock>();

  private JCodeModel model = new JCodeModel();
  private Rule current;

  private String name;
  private String pack;

  public JavaExportVisitor(String name, String pack) {
    this.name = name;
    this.pack = pack;
  }

  @Override
  public Object visit(Phalidator phalidator) {
    for (String ns : phalidator.getNamespaces()) {
      phalidator.get(ns).accept(this);
    }
    return model;
  }
  
  private String ucc(String name) {
    return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name);
  }

  @Override
  public Object visit(Namespace namespace) {
    String fullName = pack + "." + ucc((namespace.getName() == null ? name : namespace.getName()));

    try {
      main.push(model._class(fullName));
      
      main.peek()._implements(Validateable.class);
      
      JFieldVar map = main.peek().field(JMod.PRIVATE, model.ref(Map.class).narrow(String.class, String.class), "model");
      
      
      JMethod constructor = main.peek().constructor(JMod.PUBLIC);
      constructor.param(model.ref(Map.class).narrow(String.class, String.class), "model");
      constructor.body().assign(JExpr._this().ref(map), constructor.listParams()[0]);

      for (String ns : namespace.getNamespaces()) {
        namespace.get(ns).accept(this);
      }
      
      
      JMethod invalidKeys = main.peek().method(JMod.PUBLIC,  model.ref(Collection.class).narrow(String.class), "getInvalidKeys");
      getInvalidBlock.push(invalidKeys.body());
      getInvalidBlock.peek().decl(model.ref(Collection.class).narrow(String.class), "invalidKeys", JExpr._new(model.ref(ArrayList.class).narrow(String.class)));
      
      isValidReturn.push(JExpr.TRUE);
      
      for (Rule r : namespace) {
        r.accept(this);
      }
      JMethod method = main.peek().method(JMod.PUBLIC, Boolean.TYPE, "isValid");
      method.body()._return(isValidReturn.pop());
      
      getInvalidBlock.pop()._return(JExpr.ref("invalidKeys"));;
      
      main.pop();
      return model;
    } catch (JClassAlreadyExistsException e) {
      throw new RuntimeException(fullName + " already exists", e);
    }

  }

  @Override
  public Object visit(Rule rule) {
    current = rule;
    JMethod validationmethod = main.peek().method(JMod.PUBLIC, Boolean.TYPE, "is" + ucc(rule.getName()) + "Valid");
    
    JConditional cond = getInvalidBlock.peek()._if(JExpr.invoke(validationmethod).not());
    JInvocation inv = JExpr.ref("invalidKeys").invoke("add");
    inv.arg(JExpr.lit(rule.getName()));
    cond._then().add(inv);
    
    isValidReturn.push(isValidReturn.pop().cand(JExpr.invoke(validationmethod)));

    validationmethod.body()._return((JExpression) rule.getExpression().accept(this));
    return null;
  }

  @Override
  public Object visit(XCompare xCompare) {
    Operation op = xCompare.getOperation();
    JExpression left = (JExpression) xCompare.getLeft().accept(this);
    JExpression right = (JExpression) xCompare.getRight().accept(this);

    JInvocation jinv = left.invoke("compareTo");
    jinv.arg(right);
    
    
    if (op == Operation.EQUAL) {
      return jinv.eq(JExpr.lit(0));
    } else if (op == Operation.LESS) {
      return jinv.lt(JExpr.lit(0));
    } else if (op == Operation.LESS_EQUAL) {
      return jinv.lte(JExpr.lit(0));
    } else if (op == Operation.MORE) {
      return jinv.gt(JExpr.lit(0));
    } else if (op == Operation.MORE_EQUAL) {
      return jinv.gte(JExpr.lit(0));
    }
    throw new RuntimeException();
  }

  @Override
  public Object visit(XLengthOperation xLengthOperation) {
    JExpression result = (JExpression) xLengthOperation.getField().accept(this);
    JInvocation con = JExpr._new(model.ref(BigDecimal.class));
    con.arg(result.invoke("length"));
    return con;
  }

  @Override
  public Object visit(XCharsOperation xCharsOperation) {
    return xCharsOperation.getField().accept(this);
  }

  @Override
  public Object visit(XLettersOperation xLettersOperation) {
    JExpression field = (JExpression) xLettersOperation.getField().accept(this);

    JInvocation array = field.invoke("split");
    array.arg(JExpr.lit("(?!^)"));

    JInvocation cllection = model.ref(Arrays.class).staticInvoke("asList");
    cllection.arg(array);
    
    JInvocation jinv = JExpr._new(model.ref(HashSet.class));
    jinv.arg(cllection);

    return jinv;
  }

  @Override
  public Object visit(XField xField) {
    String name = xField.getName() != null ? xField.getName() : current.getName();
    
    JFieldVar map = main.peek().fields().get("model");
    JInvocation getter = JExpr._this().ref(map).invoke("get");
    getter.arg(JExpr.lit(name));

    return getter;
  }

  @Override
  public Object visit(XDecimal xDecimal) {
    JInvocation voc =  JExpr._new(model._ref(BigDecimal.class));
    voc.arg(JExpr.lit(xDecimal.getValue().toPlainString()));
    return voc;
  }

  @Override
  public Object visit(XIn xIn) {
    JExpression set = (JExpression)xIn.getSet().accept(this);
    JExpression value = (JExpression)xIn.getValue().accept(this);

    JInvocation jinv = model.ref(Util.class).staticInvoke("in");
    jinv.arg(value);
    jinv.arg(set);
    
    
    return jinv;
  }

  @Override
  // TODO, create a range set instead of a list containing all the actual
  // values.
  public Object visit(XRange xRange) {
    JExpression start = (JExpression)xRange.getStart().accept(this);
    JExpression end = (JExpression)xRange.getEnd().accept(this);
    JInvocation jinv = model.ref(Util.class).staticInvoke("range");
    jinv.arg(start);
    jinv.arg(end);
    return jinv;
  }

  @Override
  public Object visit(XArray xArray) {
    JArray array = JExpr.newArray(model.ref(Object.class));
    for (XPression element : xArray) {
      array.add((JExpression)element.accept(this));
    }
    return model.ref(Arrays.class).staticInvoke("asList").arg(array);
  }

  @Override
  public Object visit(XChars xChars) {
    return JExpr.lit(xChars.getValue());
  }

  @Override
  public Object visit(XAnd xAnd) {
    JExpression left = (JExpression)xAnd.getLeft().accept(this);
    JExpression right = (JExpression)xAnd.getRight().accept(this);

    return left.cand(right);
  }

  @Override
  public Object visit(XOr xOr) {
    JExpression left = (JExpression)xOr.getLeft().accept(this);
    JExpression right = (JExpression)xOr.getRight().accept(this);

    return left.cor(right);
  }

  @Override
  public Object visit(XNot xNot) {
    JExpression left = (JExpression)xNot.getOperand().accept(this);
    return left.not();
  }

  @Override
  public Object visit(XMatches xMatches) {
    JExpression value = (JExpression)xMatches.getValue().accept(this);
    JExpression pattern = (JExpression)xMatches.getPattern().accept(this);
    JInvocation jinv = model.ref(Util.class).staticInvoke("matches");
    jinv.arg(value);
    jinv.arg(pattern);
    return jinv;
  }

  @Override
  public Object visit(XRegexp xRegexp) {
    JInvocation invo = model.ref(Pattern.class).staticInvoke("compile");
    invo.arg(JExpr.lit(xRegexp.toString()));
    return invo;
  }
}
