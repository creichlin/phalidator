package ch.kerbtier.phalidator.rt;

import java.util.Collection;

public interface Validateable {
  boolean isValid();
  Collection<String> getInvalidKeys();
}
