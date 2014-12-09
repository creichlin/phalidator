package ch.kerbtier.phalidator.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.kerbtier.phalidator.PhalidatorVisitor;

import com.google.common.base.Joiner;

public class XArray implements XSet, XList, Iterable<XPression> {
  private List<XPression> array = new ArrayList<XPression>();
  
  
  public String toString() {
    return "[" + Joiner.on(", ").join(array) + "]";
  }


  public void add(XPression expression) {
    array.add(expression);
  }


  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }


  @Override
  public Iterator<XPression> iterator() {
    return array.iterator();
  }
}
