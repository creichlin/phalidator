package ch.kerbtier.phalidator.export;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import ch.kerbtier.phalidator.Parse;
import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.export.java.JavaExport;
import ch.kerbtier.phalidator.export.js.JsExport;

public class Export {
  public static void main(String[] args) {
    Parameters params = new Parameters();
    params.apply(args);

    String input = params.getFile();
    Phalidator phalidator = Parse.path(Paths.get(input));

    String name = getName(input);

    String target = params.getTarget();
    String output = params.getOut();

    if ("js-obj".equals(target)) {
      JsExport jsex = new JsExport(phalidator, name);
      String code = JsExport.generic();
      code += "\n" + jsex.export();
      Path p = Paths.get(output);
      try {
        FileUtils.writeStringToFile(p.toFile(), code);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else if("java-map".equals(target)) {
      JavaExport jx = new JavaExport(phalidator, name, params.getPackage());
      Path tg = Paths.get(params.getOut());
      try {
        Files.createDirectories(tg);
        jx.export(tg);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private static String getName(String input) {
    String name = input;
    name = name.substring(name.lastIndexOf("/") + 1);
    if (name.contains(".")) {
      name = name.substring(0, name.lastIndexOf("."));
    }
    return name;
  }
}
