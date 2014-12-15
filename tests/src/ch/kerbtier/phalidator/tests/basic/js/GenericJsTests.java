package ch.kerbtier.phalidator.tests.basic.js;

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

@RunWith(Parameterized.class)
public class GenericJsTests {

  @Parameters
  public static Collection<Object[]> generateParams() {
    PrepareJs prepare = new PrepareJs();
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
      for(String r: rules) {
        assertTrue("in " + name + " rule " + r + " failed", false);
      }
      called = true;
    }
    
    public void check() {
      if (!called) {
        assertTrue("script not run... " + name, false);
      }
    }
  }
}
