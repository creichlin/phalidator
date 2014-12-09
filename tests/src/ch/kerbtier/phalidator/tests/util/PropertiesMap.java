package ch.kerbtier.phalidator.tests.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesMap {
  public static Map<String, String> read(Class<?> type, String name) {
    try {
      Map<String, String> values = new HashMap<String, String>();
      Properties properties = new Properties();
      properties.load(type.getResourceAsStream(name + ".properties"));

      for (String key : properties.stringPropertyNames()) {
        values.put(key, properties.get(key).toString());
      }

      return values;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
