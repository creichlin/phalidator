package ch.kerbtier.phalidator.tests.generic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Joiner;

@RunWith(Parameterized.class)
public class GenericJsTests {

  @Parameters(name = "{0}")
  public static Collection<Object[]> generateParams() {
    JsTestBuilder prepare = new JsTestBuilder();
    List<Object[]> parameters = new ArrayList<Object[]>();
    
    for(String test: prepare) {
      parameters.add(new Object[]{test, prepare.get(test)});
    }
    
    return parameters;
  }
  
  private String name;
  private String code;
  
  public GenericJsTests(String name, String code) {
    this.name = name;
    this.code = code;
  }
  
  @Test
  public void jsTests() {
    ScriptEngineManager factory = new ScriptEngineManager();
    ScriptEngine engine = factory.getEngineByName("JavaScript");
    Bindings b = engine.createBindings();
    b.put("api", new Api());
    try {
      engine.eval(code, b);
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    ((Api)b.get("api")).check();
  }
  
  public class Api {
    
    private boolean called = false;
    
    public void success() {
      assertTrue(true);
      called = true;
    }
    
    public void failed(Collection<String> rules) {
      if(rules.size() > 0) {
        System.out.println(code);
        assertTrue("in " + name + " rule " + Joiner.on(", ").join(rules) + " failed", false);
      } else {
        assertTrue(true);
      }
      called = true;
    }
    
    public void check() {
      if (!called) {
        System.out.println(code);
        assertTrue("script not run... " + name, false);
      }
    }
  }
  
  protected String addLineNumbers(String str) {
    String out = "";
    int cnt = 1;
    for(String line: str.split("\n")) {
      out += cnt + ": " + line + "\n";
      cnt++;
    }
    
    return out;
  }
}
