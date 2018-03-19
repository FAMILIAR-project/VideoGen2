import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class FileWriter {
  /**
   * @param : filelist which contains the location of the videos
   * @return : the name of the txt file that contains filelist content, the txt file will be in the accepted format
   * for ffmpeg
   */
  public static String FileWriter(final String filelist) {
    String _string = Integer.valueOf(new Random().nextInt(1000)).toString();
    String name = ("video" + _string);
    Writer writer = null;
    try {
      FileOutputStream _fileOutputStream = new FileOutputStream((("Videos_Elements/InputFiles/Input_TextPlaylist/" + name) + ".txt"));
      OutputStreamWriter _outputStreamWriter = new OutputStreamWriter(_fileOutputStream, "utf-8");
      BufferedWriter _bufferedWriter = new BufferedWriter(_outputStreamWriter);
      writer = _bufferedWriter;
      writer.write(filelist);
      writer.close();
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException ex = (IOException)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    } finally {
      try {
      } catch (final Throwable _t_1) {
        if (_t_1 instanceof Exception) {
          final Exception ex_1 = (Exception)_t_1;
        } else {
          throw Exceptions.sneakyThrow(_t_1);
        }
      }
    }
    return name;
  }
}
