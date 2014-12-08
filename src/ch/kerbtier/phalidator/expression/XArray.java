package ch.kerbtier.phalidator.expression;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

public class XArray implements XSet, XList {
  private List<XPression> array = new ArrayList<XPression>();
  
  
  public String toString() {
    return "[" + Joiner.on(", ").join(array) + "]";
  }


  public void add(XPression expression) {
    array.add(expression);
  }
  
  
}
