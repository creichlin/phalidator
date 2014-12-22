package ch.kerbtier.phalidator.tests.generic;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

import com.google.common.base.CaseFormat;

import ch.kerbtier.phalidator.Phalidator;
import ch.kerbtier.phalidator.export.java.JavaExport;
import ch.kerbtier.phalidator.rt.Validateable;

public class GeneratedMapCompiler {

  @SuppressWarnings("unchecked")
  public static Class<Validateable> compile(Phalidator phalidator, String name) {
    
    JavaExport jx = new JavaExport(phalidator, name, "phalidator.tests");
    
    try {
      Path tmpBase = Paths.get("tmp");
      Files.createDirectories(tmpBase);
      Path tmp = Files.createTempDirectory(tmpBase, "mapctests");
      jx.export(tmp);
      
      
      
      JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
      
      StandardJavaFileManager fileManager =
          jc.getStandardFileManager(null, null, null);

      fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
                              Arrays.asList(tmp.toFile()));
      // Compile the file
      jc.getTask(null,
                 fileManager,
                 null,
                 null,
                 null,
                 fileManager.getJavaFileObjectsFromFiles(jx.getSourceFiles()))
              .call();
      fileManager.close();
      
      
      
      URLClassLoader classLoader = new URLClassLoader(new URL[]{tmp.toUri().toURL()});
      
      Class<Validateable> cl = (Class<Validateable>)classLoader.loadClass("phalidator.tests." + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name));
      
      classLoader.close();
      
      FileUtils.deleteDirectory(tmpBase.toFile());
      return cl;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
