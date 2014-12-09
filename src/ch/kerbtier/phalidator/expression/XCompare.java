package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XCompare implements XBool {
  private XPression left;
  private XPression right;
  
  private Operation operation;
  
  public XCompare(XPression left, XPression right, Operation operation) {
    this.left = left;
    this.right = right;
    this.operation = operation;
  }
  
  public String toString() {
    return "(" + left + " + operation.sign() + " + right + ")";
  }

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
  
  public enum Operation {
    LESS("<"), MORE(">"), LESS_EQUAL("<="), MORE_EQUAL(">="), EQUAL("==");

    private String sign;
    
    private Operation(String sign) {
      this.sign = sign;
    }

    String sign() {
      return sign;
    }
  }

  public XPression getLeft() {
    return left;
  }

  public XPression getRight() {
    return right;
  }

  public Operation getOperation() {
    return operation;
  }
}
