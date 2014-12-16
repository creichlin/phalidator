package ch.kerbtier.phalidator.export;

public class Parameters {

  private String file;
  private String target;
  private String out;

  public void apply(String[] args) {
    if(args.length < 3) {
      throw new RuntimeException("Needs at least three parameters.");
    }
    
    file = args[0];
    target = args[1];
    
    if("js-obj".equals(target)) {
      out = args[2];
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
}
