package ch.kerbtier.phalidator;

public class Phalidator extends Namespace {

  public Phalidator() {
    super(null);
  }

  @Override
  public Object accept(PhalidatorVisitor visitor) {
    return visitor.visit(this);
  }
  
  
}
