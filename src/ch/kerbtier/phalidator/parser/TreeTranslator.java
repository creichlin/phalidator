package ch.kerbtier.phalidator.parser;

import ch.kerbtier.phalidator.Namespace;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.Rule;
import ch.kerbtier.phalidator.expression.XAnd;
import ch.kerbtier.phalidator.expression.XArray;
import ch.kerbtier.phalidator.expression.XBool;
import ch.kerbtier.phalidator.expression.XChars;
import ch.kerbtier.phalidator.expression.XCharsOperation;
import ch.kerbtier.phalidator.expression.XDecimal;
import ch.kerbtier.phalidator.expression.XEqual;
import ch.kerbtier.phalidator.expression.XField;
import ch.kerbtier.phalidator.expression.XIn;
import ch.kerbtier.phalidator.expression.XLengthOperation;
import ch.kerbtier.phalidator.expression.XLess;
import ch.kerbtier.phalidator.expression.XLessEqual;
import ch.kerbtier.phalidator.expression.XLettersOperation;
import ch.kerbtier.phalidator.expression.XMatches;
import ch.kerbtier.phalidator.expression.XMore;
import ch.kerbtier.phalidator.expression.XMoreEqual;
import ch.kerbtier.phalidator.expression.XNot;
import ch.kerbtier.phalidator.expression.XOr;
import ch.kerbtier.phalidator.expression.XPattern;
import ch.kerbtier.phalidator.expression.XPression;
import ch.kerbtier.phalidator.expression.XRange;
import ch.kerbtier.phalidator.expression.XRegexp;
import ch.kerbtier.phalidator.expression.XSet;
import ch.kerbtier.phalidator.expression.XOperation;
import ch.kerbtier.phalidator.parser.PhalParser.ArrayContext;
import ch.kerbtier.phalidator.parser.PhalParser.BooleanExpressionContext;
import ch.kerbtier.phalidator.parser.PhalParser.BooleanValueContext;
import ch.kerbtier.phalidator.parser.PhalParser.ExpressionContext;
import ch.kerbtier.phalidator.parser.PhalParser.NamespaceContext;
import ch.kerbtier.phalidator.parser.PhalParser.NormContext;
import ch.kerbtier.phalidator.parser.PhalParser.OperationContext;
import ch.kerbtier.phalidator.parser.PhalParser.PrimaryExpressionContext;
import ch.kerbtier.phalidator.parser.PhalParser.RegexpContext;
import ch.kerbtier.phalidator.parser.PhalParser.SetContext;
import ch.kerbtier.phalidator.parser.PhalParser.StartContext;
import ch.kerbtier.phalidator.parser.PhalParser.StringContext;
import ch.kerbtier.phalidator.parser.PhalParser.VariableContext;

public class TreeTranslator extends PhalBaseVisitor<Object> {

  @Override
  public Object visitStart(StartContext ctx) {
    
    Phalidator root = new Phalidator();
    
    for(NamespaceContext ns: ctx.namespace()) {
      root.add((Namespace)visit(ns));
    }
    
    return root;
  }

  @Override
  public Object visitNamespace(NamespaceContext ctx) {
    Namespace namespace = new Namespace(ctx.IDENTIFIER().getText());
    
    for(NamespaceContext ns: ctx.namespace()) {
      namespace.add((Namespace)visit(ns));
    }
    
    for(NormContext ns: ctx.norm()) {
      namespace.add((Rule)visit(ns));
    }
    
    return namespace;
  }

  @Override
  public Object visitNorm(NormContext ctx) {
    Rule rule = new Rule(ctx.IDENTIFIER().getText(), (XBool)visit(ctx.booleanExpression()));
    return rule;
  }

  @Override
  public Object visitBooleanExpression(BooleanExpressionContext ctx) {
    if(ctx.NOT() != null) {
      return new XNot((XBool)visit(ctx.booleanExpression(0)));
    } else if(ctx.AND() != null) {
      return new XAnd((XBool)visit(ctx.booleanExpression(0)), (XBool)visit(ctx.booleanExpression(1)));
    } else if(ctx.OR() != null) {
      return new XOr((XBool)visit(ctx.booleanExpression(0)), (XBool)visit(ctx.booleanExpression(1)));
    } else if(ctx.booleanValue() != null){
      return visit(ctx.booleanValue());
    }
    throw new RuntimeException("invalid boolean expression");
  }

  @Override
  public Object visitBooleanValue(BooleanValueContext ctx) {
    if(ctx.LESS() != null) {
      return new XLess((XPression)visit(ctx.expression(0)), (XPression)visit(ctx.expression(1)));
    } else if(ctx.LESS_EQUAL() != null) {
      return new XLessEqual((XPression)visit(ctx.expression(0)), (XPression)visit(ctx.expression(1)));
    } else if(ctx.MORE() != null) {
      return new XMore((XPression)visit(ctx.expression(0)), (XPression)visit(ctx.expression(1)));
    } else if(ctx.MORE_EQUAL() != null) {
      return new XMoreEqual((XPression)visit(ctx.expression(0)), (XPression)visit(ctx.expression(1)));
    } else if(ctx.EQUAL() != null) {
      return new XEqual((XPression)visit(ctx.expression(0)), (XPression)visit(ctx.expression(1)));
    } else if(ctx.NOT_EQUAL() != null) {
      return new XNot(new XEqual((XPression)visit(ctx.expression(0)), (XPression)visit(ctx.expression(1))));
    } else if(ctx.booleanExpression() != null) {
      return visit(ctx.booleanExpression());
    } else if(ctx.IN() != null) {
      return new XIn((XPression)visit(ctx.expression(0)), (XSet)visit(ctx.set()));
    } else if(ctx.MATCHES() != null) {
      return new XMatches((XPression)visit(ctx.expression(0)), (XPattern)visit(ctx.pattern()));
    }
    throw new RuntimeException("invalid expression");
  }

  @Override
  public Object visitPrimaryExpression(PrimaryExpressionContext ctx) {
    if(ctx.expression() != null) {
      return visit(ctx.expression());
    } else if(ctx.NUMBER() != null) {
      return new XDecimal(Integer.parseInt(ctx.getText()));
    } else if(ctx.string() != null) {
      return visit(ctx.string());
    } else if(ctx.variable() != null) {
      VariableContext fc = ctx.variable();
      OperationContext oc = fc.operation();
      XField xfield = new XField(fc.field() != null ? fc.field().IDENTIFIER().getText() : null);
      XOperation operation = null;
      
      
      
      if(oc == null) {
        operation = new XCharsOperation(xfield);
      } else if(oc.LETTERS() != null) {
        operation = new XLettersOperation(xfield);
      } else if(oc.LENGTH() != null){
        operation = new XLengthOperation(xfield);
      }
      
      return operation;
    }
    throw new RuntimeException("invalid primary expression");
  }

  @Override
  public Object visitExpression(ExpressionContext ctx) {
    // TODO Auto-generated method stub
    return super.visitExpression(ctx);
  }

  @Override
  public Object visitSet(SetContext ctx) {
    if(ctx.RANGE() != null) {
      return new XRange((XPression)visit(ctx.expression(0)), (XPression)visit(ctx.expression(1)));
    }
    return super.visitSet(ctx);
  }

  @Override
  public Object visitString(StringContext ctx) {
    String txt = ctx.QUOTED_STRING().getText();
    txt = txt.substring(1, txt.length() - 1);
    return new XChars(txt);
  }

  @Override
  public Object visitRegexp(RegexpContext ctx) {
    return new XRegexp(ctx.getText());
  }

  @Override
  public Object visitArray(ArrayContext ctx) {
    XArray array = new XArray();
    
    for(ExpressionContext ec: ctx.expression()) {
      array.add((XPression)visit(ec));
    }
    
    return array;
  }
  
  
  
  
  
  
  
}
