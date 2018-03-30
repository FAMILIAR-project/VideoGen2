package fr.istic.m2il.idm.videogentransformations.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.eclipse.xtext.xbase.lib.Exceptions;

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
  
  public static Object writeFileOnDisk(final String file, final String[] contents) {
    try {
      Object _xblockexpression = null;
      {
        File f = null;
        boolean _exists = new File(file).exists();
        boolean _not = (!_exists);
        if (_not) {
          File _file = new File(file);
          f = _file;
          f.getParentFile().mkdirs();
          f.createNewFile();
        }
        String _absolutePath = f.getAbsolutePath();
        FileWriter fwriter = new FileWriter(_absolutePath);
        BufferedWriter bwriter = new BufferedWriter(fwriter);
        Object _xtrycatchfinallyexpression = null;
        try {
          for (final String content : contents) {
            bwriter.write(content);
          }
        } catch (final Throwable _t) {
          if (_t instanceof IOException) {
            final IOException e = (IOException)_t;
            _xtrycatchfinallyexpression = null;
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        } finally {
          try {
            if ((bwriter != null)) {
              bwriter.close();
            }
            if ((fwriter != null)) {
              fwriter.close();
            }
          } catch (final Throwable _t_1) {
            if (_t_1 instanceof IOException) {
              final IOException ex = (IOException)_t_1;
              ex.printStackTrace();
            } else {
              throw Exceptions.sneakyThrow(_t_1);
            }
          }
        }
        _xblockexpression = _xtrycatchfinallyexpression;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
