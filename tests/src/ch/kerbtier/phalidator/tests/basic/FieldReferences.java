package ch.kerbtier.phalidator.tests.basic;

import static ch.kerbtier.phalidator.tests.util.PropertiesMap.read;
import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.kerbtier.phalidator.Parse;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.map.MapValidator;

public class FieldReferences {
  
  private Phalidator phalidator;
  
  
  @Before
  public void setUp() throws Exception {
    phalidator = Parse.resource(this.getClass(), "entities.phal", Charset.forName("utf-8"));
  }

  @After
  public void tearDown() throws Exception {
    phalidator = null;
  }

  @Test
  public void tests() {
    MapValidator mapValidator = new MapValidator(phalidator, "fieldReferences", read(this.getClass(), "fieldReferences"));
    assertTrue(mapValidator.isValid());
  }
}
