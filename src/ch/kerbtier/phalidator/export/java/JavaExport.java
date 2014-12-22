package ch.kerbtier.phalidator.export.java;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.output.NullOutputStream;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JPackage;

import ch.kerbtier.phalidator.Parse;
import ch.kerbtier.phalidator.Phalidator;

public class JavaExport {
  private JavaExportVisitor visitor;
  private JCodeModel jcm;
  private List<File> lastFiles;
  
  public JavaExport(Phalidator phalidator, String name, String pack) {
    visitor = new JavaExportVisitor(name, pack);
    jcm = (JCodeModel)phalidator.accept(visitor);
  }
  
  public void export(final Path path) {
    try {
      lastFiles = new ArrayList<File>();
      jcm.build(new CodeWriter() {
        
        @Override
        public OutputStream openBinary(JPackage pckg, String name) throws IOException {
          String canName = pckg.name().replace(".", "/") + "/" + name;
          Path canPath = path.resolve(canName);
          
          lastFiles.add(canPath.toFile());
        
          return new NullOutputStream();
        }
        
        @Override
        public void close() throws IOException {

        }
      });
      
      jcm.build (path.toFile());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
  
  public static void main(String[] args) {
    JavaExport jx = new JavaExport(Parse.path(Paths.get("examples/javascript/rules.phal")), "Dada", "antes.porta");
    
    jx.export(Paths.get("src"));
    
  }

  public Iterable<File> getSourceFiles() {
    return lastFiles;
  }
}
