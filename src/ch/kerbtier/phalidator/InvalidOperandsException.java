package ch.kerbtier.phalidator;

@SuppressWarnings("serial")
public class InvalidOperandsException extends RuntimeException {
  public InvalidOperandsException(String desc) {
    super(desc);
  }
}
