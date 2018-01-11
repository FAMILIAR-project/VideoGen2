package configs;

import java.io.File;

@SuppressWarnings("all")
public class VideoGenConfigs {
  private static File outPutFolder;
  
  private static int[] gifResolutions = new int[2];
  
  private static String serverIP = "";
  
  public static void setOutPutFoulder(final String outPutFolder) {
    File _file = new File(outPutFolder);
    VideoGenConfigs.outPutFolder = _file;
  }
  
  public static File getOutPutFoulder() {
    return VideoGenConfigs.outPutFolder;
  }
  
  public static void setGifResolutions(final int width, final int heigth) {
    VideoGenConfigs.gifResolutions[0] = width;
    VideoGenConfigs.gifResolutions[1] = heigth;
  }
  
  public static int[] getGifResolutions() {
    return VideoGenConfigs.gifResolutions;
  }
  
  public static void setServerIP(final String ip) {
    VideoGenConfigs.serverIP = ip;
  }
  
  public static String getServerIP() {
    return VideoGenConfigs.serverIP;
  }
}
