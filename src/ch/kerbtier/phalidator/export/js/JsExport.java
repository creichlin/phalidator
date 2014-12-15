package ch.kerbtier.phalidator.export.js;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;

import ch.kerbtier.phalidator.Phalidator;

public class JsExport {
  private Phalidator phalidator;
  private String name;
  
  public JsExport(Phalidator phalidator, String name) {
    this.phalidator = phalidator;
    this.name = name;
  }
  
  public String export() {
    
    JsExportVisitor visitor = new JsExportVisitor(name);
    
    return (String)phalidator.accept(visitor);
  }
  
  public void export(Path path) {
    export(path, Charset.defaultCharset());
  }

  private void export(Path path, Charset charset) {
    try {
      FileOutputStream fop = new FileOutputStream(path.toFile());
      export(fop, charset);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }

  private void export(OutputStream out, Charset charset) {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, charset));
    export(bw);
  }

  private void export(BufferedWriter bw) {
    try {
      bw.append(export());
      bw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String generic() {
    try {
      return IOUtils.toString(JsExport.class.getResourceAsStream("generic.js"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
}
