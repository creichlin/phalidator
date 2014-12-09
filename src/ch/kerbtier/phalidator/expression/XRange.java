package ch.kerbtier.phalidator.expression;

import ch.kerbtier.phalidator.PhalidatorVisitor;

public class XRange implements XSet {
  private XPression start;
  private XPression end;
  
  public XRange(XPression start, XPression end) {
    this.start = start;
    this.end = end;
  }
  
  
  public String toString(){
    return start + ".." + end;
  }
  
  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }


  public XPression getStart() {
    return start;
  }

  public XPression getEnd() {
    return end;
  }
}
