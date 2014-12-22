package ch.kerbtier.phalidator.export;

public class Parameters {

  private String file;
  private String target;
  private String out;
  private String pack;

  public void apply(String[] args) {
    if(args.length < 3) {
      throw new RuntimeException("Needs at least three parameters.");
    }
    
    file = args[0];
    target = args[1];
    
    if("js-obj".equals(target)) {
      out = args[2];
    } else if("java-map".equals(target)) {
      pack = args[2];
      out = args[3];
    } else {
      throw new RuntimeException("Invalid target " + target + ".");
    }
  }

  public String getFile() {
    return file;
  }

  public String getTarget() {
    return target;
  }

  public String getOut() {
    return out;
  }
  
  public String getPackage() {
    return pack;
  }
}
