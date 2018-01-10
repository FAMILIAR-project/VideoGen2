package utils;

import java.io.File;

@SuppressWarnings("all")
public class CommonUtils {
  public static String getOutPutFileName(final String filename) {
    String _xblockexpression = null;
    {
      int fileoutputIndex = 0;
      final String[] file = filename.replace(".", "@").split("@");
      String _get = file[0];
      String _plus = (_get + "_");
      String _plus_1 = (_plus + Integer.valueOf(fileoutputIndex));
      String _plus_2 = (_plus_1 + ".");
      String _get_1 = file[1];
      String outputFile = (_plus_2 + _get_1);
      while (new File(outputFile).exists()) {
        {
          fileoutputIndex++;
          String _get_2 = file[0];
          String _plus_3 = (_get_2 + "_");
          String _plus_4 = (_plus_3 + Integer.valueOf(fileoutputIndex));
          String _plus_5 = (_plus_4 + ".");
          String _get_3 = file[1];
          String _plus_6 = (_plus_5 + _get_3);
          outputFile = _plus_6;
        }
      }
      _xblockexpression = outputFile;
    }
    return _xblockexpression;
  }
  
  public static String writeFile(final String file) {
    return null;
  }
}
