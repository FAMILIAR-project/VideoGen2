package configs;

import java.io.File;

@SuppressWarnings("all")
public class VideoGenConfigs {
  private static File outPutFolder;
  
  public static File setOutPutFoulder(final String outPutFolder) {
    File _file = new File(outPutFolder);
    return VideoGenConfigs.outPutFolder = _file;
  }
  
  public static File getOutPutFoulder() {
    return VideoGenConfigs.outPutFolder;
  }
}
