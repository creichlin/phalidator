package ch.kerbtier.phalidator.expression;

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
  
}
