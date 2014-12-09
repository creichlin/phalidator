package ch.kerbtier.phalidator;

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String desc) {
    super(desc);
  }
}
