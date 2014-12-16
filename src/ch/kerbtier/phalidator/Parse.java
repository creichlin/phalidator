package ch.kerbtier.phalidator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Path;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ch.kerbtier.phalidator.parser.PhalLexer;
import ch.kerbtier.phalidator.parser.PhalParser;
import ch.kerbtier.phalidator.parser.TreeTranslator;
import ch.kerbtier.phalidator.parser.PhalParser.StartContext;

public class Parse {
  
  public static Phalidator path(Path path) {
    try {
      return stream(new FileInputStream(path.toFile()));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static Phalidator resource(Class<?> type, String name, Charset charset) {
    InputStream is = type.getResourceAsStream(name);
    return stream(is, charset);
  }

  public static Phalidator resource(Class<?> type, String name) {
    InputStream is = type.getResourceAsStream(name);
    return stream(is);
  }

  public static Phalidator string(String input) {
    Reader r = new StringReader(input);
    return reader(r);
  }
  
  public static Phalidator stream(InputStream in) {
    return stream(in, null);
  }

  public static Phalidator stream(InputStream in, Charset charset) {
    Reader reader = new InputStreamReader(in, charset == null ? Charset.defaultCharset() : charset);
    return reader(reader);
  }
  
  public static Phalidator reader(Reader reader) {
    try {
      ANTLRInputStream input = new ANTLRInputStream(reader);

      PhalLexer lexer = new PhalLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      PhalParser parser = new PhalParser(tokens);

      StartContext tree = parser.start();

      reader.close();

      TreeTranslator tt = new TreeTranslator();

      Phalidator ph = (Phalidator) tree.accept(tt);
      return ph;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
