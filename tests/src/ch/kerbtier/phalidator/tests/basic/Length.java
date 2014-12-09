package ch.kerbtier.phalidator.tests.basic;

import static org.junit.Assert.*;
import static ch.kerbtier.phalidator.tests.util.PropertiesMap.read;

import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.kerbtier.phalidator.Parse;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.map.MapValidator;

public class Length {
  
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
  public void testValidLengths() {
    MapValidator mapValidator = new MapValidator(phalidator, "length", read(this.getClass(), "valid_lengths"));
    assertTrue(mapValidator.isValid());
  }

  @Test
  public void testInvalidLengths() {
    MapValidator mapValidator = new MapValidator(phalidator, "length", read(this.getClass(), "invalid_lengths"));
    assertFalse(mapValidator.isValid());
    assertEquals(6, mapValidator.getInvalidRules().size());
  }
}
