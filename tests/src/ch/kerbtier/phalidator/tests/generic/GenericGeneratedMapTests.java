package ch.kerbtier.phalidator.tests.generic;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.yaml.snakeyaml.Yaml;

import com.google.common.collect.Sets;

import ch.kerbtier.phalidator.Parse;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.rt.Validateable;

@RunWith(Parameterized.class)
public class GenericGeneratedMapTests {

  private static Map<String, Object> map;

  @SuppressWarnings("unchecked")
  public static void readData() {
    Yaml yaml = new Yaml();
    try {

      map = (Map<String, Object>) yaml.load(FileUtils.readFileToString(new File("tests/genericTests.yaml"),
          Charset.forName("utf-8")));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @SuppressWarnings("unchecked")
  @Parameters(name = "{4}")
  public static Collection<Object[]> generateParams() {
    // @Parameters is run before BeforeClass... it seems
    readData();

    List<Object[]> params = new ArrayList<Object[]>();

    for (String entity : map.keySet()) {
      Map<String, Object> entityMap = (Map<String, Object>) map.get(entity);
      Phalidator phalidator = Parse.string((String) entityMap.get("rule"));

      if (entityMap.containsKey("input")) {
        Map<String, Map<String, Boolean>> inputs = (Map<String, Map<String, Boolean>>) entityMap.get("input");

        for (String input : inputs.keySet()) {
          params.add(new Object[] { entity, phalidator, input, inputs.get(input), entity + "[" + input + "]" });
        }
      } else {
        params.add(new Object[] { entity, phalidator, null, null, entity });
      }
    }

    return params;
  }

  private String entity;
  private Phalidator phalidator;
  private String input;
  private List<String> expected;

  public GenericGeneratedMapTests(String entity, Phalidator phalidator, String input, List<String> expected, String name) {
    this.entity = entity;
    this.phalidator = phalidator;
    this.input = input;
    this.expected = expected;
  }

  @Test
  public void test() {

    Class<Validateable> validateable = GeneratedMapCompiler.compile(phalidator, entity);

    try {
      if (input == null) {
        Validateable validator = validateable.getConstructor(Map.class).newInstance(new HashMap<String, String>());
        assertTrue("entity " + entity + " has invalid rules " + validator.getInvalidKeys(), validator.isValid());
      } else {

        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("input", input);
        Validateable validator = validateable.getConstructor(Map.class).newInstance(inputMap);

        Set<String> invalidRules = new HashSet<String>(validator.getInvalidKeys());
        Set<String> allRules = new HashSet<String>(phalidator.get(entity).getRuleNames());
        Set<String> expectedRules = new HashSet<String>(expected);

        boolean exclusive = Sets.intersection(invalidRules, expectedRules).size() == 0;
        boolean complete = Sets.union(invalidRules, expectedRules).equals(allRules);
        
        
        String description = "entity '" + entity + "' has problems with input '" + input + "'.";
        description += "expected " + expectedRules + " but found " + Sets.difference(allRules, invalidRules);
        
        assertTrue(description, exclusive && complete);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}