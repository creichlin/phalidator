package ch.kerbtier.phalidator;

public interface TreeElement {
  Object accept(PhalidatorVisitor visitor);
}
