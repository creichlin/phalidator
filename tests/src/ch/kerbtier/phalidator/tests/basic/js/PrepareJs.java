package ch.kerbtier.phalidator.tests.basic.js;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;

import ch.kerbtier.phalidator.Parse;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.export.js.JsExport;

public class PrepareJs implements Iterable<String> {

  private Map<String, Map<String, Object>> map;
  private Map<String, String> codes = new HashMap<String, String>();

  public PrepareJs() {
    init();
  }

  private void init() {
    Yaml yaml = new Yaml();
    try {
      map = (Map<String, Map<String, Object>>) yaml.load(FileUtils.readFileToString(new File("tests/basicTests.yaml"),
          Charset.forName("utf-8")));

      for (String key : map.keySet()) {
        Phalidator phalidator = Parse.string((String) map.get(key).get("rule"));

        Map<String, List<String>> inputs = (Map<String, List<String>>) map.get(key).get("input");

        if (inputs == null) {
          String jsCode = JsExport.generic();
          jsCode += new JsExport(phalidator, key).export();
          jsCode += "if(phalidator.isValid('" + key + "', '" + key + "', {})) {\n" +
                    "  api.success();\n" +
                    "} else {\n" +
                      "api.failed(phalidator.getInvalidRules('" + key + "', '" + key + "', {}));\n" +
                    "}\n";
          codes.put(key, jsCode);
        } else {
          for (String input : inputs.keySet()) {
            String jsCode = JsExport.generic();
            jsCode += new JsExport(phalidator, key).export();
            
            Collection<String> allRules = Collections2.transform(phalidator.get(key).getRuleNames(), new AsJsString());
            Collection<String> expectedRules = Collections2.transform(inputs.get(input), new AsJsString());
            
            jsCode += "\nvar invalid = phalidator.getInvalidRules('" + key + "', '" + key + "', {input: '" + input + "'});\n" +
                      "var allRules = [" + Joiner.on(", ").join(allRules) + "];\n" +
                      "var expectedRules = [" + Joiner.on(", ").join(expectedRules) + "];\n" +
                      "var failed = [];" +
                      "for(var cnt = 0; cnt < expectedRules.length; cnt++) {\n" +
                      "  var key = allRules[cnt];\n" +
                      "  if(expectedRules.indexOf(key) == -1 || invalid.indexOf(key) > -1) {\n" +
                      "    failed.push(key);\n" +
                      "  }\n" +
                      "}\n" +
                      "api.failed(failed);\n";
            
            System.out.println(addLineNumbers(jsCode));
            
            codes.put(key + " " + input, jsCode);
          }

        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String addLineNumbers(String str) {
    String out = "";
    int cnt = 1;
    for(String line: str.split("\n")) {
      out += cnt + ": " + line + "\n";
      cnt++;
    }
    
    return out;
  }

  @Override
  public Iterator<String> iterator() {
    return codes.keySet().iterator();
  }

  public String get(String test) {
    return codes.get(test);
  }
  
  class AsJsString implements Function<String, String> {

    @Override
    public String apply(String str) {
      return "'" + str + "'";
    }
    
  }

}
