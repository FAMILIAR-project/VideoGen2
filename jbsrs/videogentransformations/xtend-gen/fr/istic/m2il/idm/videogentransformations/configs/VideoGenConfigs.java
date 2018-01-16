package fr.istic.m2il.idm.videogentransformations.configs;

import java.io.File;

@SuppressWarnings("all")
public class VideoGenConfigs {
  private static File outPutFolder;
  
  private static int[] gifResolutions = new int[2];
  
  private static String serverIP = "localhost:8080/";
  
  public static void setOutPutFolder(final String outPut) {
    File _file = new File(outPut);
    VideoGenConfigs.outPutFolder = _file;
    boolean _exists = VideoGenConfigs.outPutFolder.exists();
    boolean _not = (!_exists);
    if (_not) {
      VideoGenConfigs.outPutFolder = null;
    }
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
  
  public static void initSubOutPutFolders() {
    if ((VideoGenConfigs.outPutFolder != null)) {
      String _absolutePath = VideoGenConfigs.outPutFolder.getAbsolutePath();
      String _plus = (_absolutePath + "/filtered/");
      new File(_plus).mkdir();
      String _absolutePath_1 = VideoGenConfigs.outPutFolder.getAbsolutePath();
      String _plus_1 = (_absolutePath_1 + "/resizes/");
      new File(_plus_1).mkdir();
      String _absolutePath_2 = VideoGenConfigs.outPutFolder.getAbsolutePath();
      String _plus_2 = (_absolutePath_2 + "/gifs/");
      new File(_plus_2).mkdir();
      String _absolutePath_3 = VideoGenConfigs.outPutFolder.getAbsolutePath();
      String _plus_3 = (_absolutePath_3 + "/thumbs/");
      new File(_plus_3).mkdir();
      String _absolutePath_4 = VideoGenConfigs.outPutFolder.getAbsolutePath();
      String _plus_4 = (_absolutePath_4 + "/playlists/");
      new File(_plus_4).mkdir();
    }
  }
}
