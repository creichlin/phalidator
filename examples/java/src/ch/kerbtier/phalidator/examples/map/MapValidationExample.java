package ch.kerbtier.phalidator.examples.map;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import ch.kerbtier.phalidator.Parse;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.map.MapValidator;

public class MapValidationExample {
  public static void main(String[] args) {

    Map<String, String> input = new HashMap<String, String>();
    input.put("name", "");
    input.put("lastName", "Doe");
    input.put("email", "john.doe@example.com");
    input.put("city", "Marodesia");
    input.put("zip", "34s89");

    Phalidator phalidator = Parse.resource(MapValidationExample.class, "rules.phal", Charset.forName("utf-8"));

    MapValidator mapValidator = new MapValidator(phalidator, "user", input);

    for (String key : mapValidator.getInvalidRules()) {
      System.out.println("rule " + key + " is not valid");
    }
    // will print:
    // rule name is not valid
    // rule zip is not valid
  }
}
