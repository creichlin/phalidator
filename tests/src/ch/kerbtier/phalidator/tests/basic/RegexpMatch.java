package ch.kerbtier.phalidator.tests.basic;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.kerbtier.phalidator.Parse;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.map.MapValidator;

public class RegexpMatch {
  
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
    MapValidator mapValidator = new MapValidator(phalidator, "regexpMatch", new HashMap<String, String>());
    assertTrue(mapValidator.isValid());
  }
}
