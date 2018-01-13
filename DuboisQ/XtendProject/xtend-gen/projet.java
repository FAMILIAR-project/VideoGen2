import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class projet {
  public static void main(final String[] args) {
    try {
      final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("public/template.videogen"));
      String filelist = "";
      if ((videoGen instanceof VideoGeneratorModel)) {
        EList<Media> _medias = videoGen.getMedias();
        for (final Media media : _medias) {
          if ((media instanceof MandatoryMedia)) {
            MediaDescription _description = ((MandatoryMedia)media).getDescription();
            if ((_description instanceof MediaDescription)) {
              String _location = ((MandatoryMedia)media).getDescription().getLocation();
              String _plus = ((filelist + "file \'") + _location);
              String _plus_1 = (_plus + "\'\n");
              filelist = _plus_1;
            }
          } else {
            if ((media instanceof OptionalMedia)) {
              MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
              if ((_description_1 instanceof MediaDescription)) {
                int _nextInt = new Random().nextInt(100);
                boolean _greaterThan = (_nextInt > 50);
                if (_greaterThan) {
                  String _location_1 = ((OptionalMedia)media).getDescription().getLocation();
                  String _plus_2 = ((filelist + "file \'") + _location_1);
                  String _plus_3 = (_plus_2 + "\'\n");
                  filelist = _plus_3;
                }
              }
            } else {
              if ((media instanceof AlternativesMedia)) {
                int alternativesize = ((AlternativesMedia)media).getMedias().size();
                final MediaDescription md = ((AlternativesMedia)media).getMedias().get(new Random().nextInt(alternativesize));
                if ((md instanceof MediaDescription)) {
                  String _location_2 = md.getLocation();
                  String _plus_4 = ((filelist + "file \'") + _location_2);
                  String _plus_5 = (_plus_4 + "\'\n");
                  filelist = _plus_5;
                }
              }
            }
          }
        }
      }
      int name = new Random().nextInt(99999999);
      Writer writer = null;
      try {
        FileOutputStream _fileOutputStream = new FileOutputStream((("public/TempTextFile/" + Integer.valueOf(name)) + ".txt"));
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
      String command = (((("ffmpeg -f concat -safe 0 -i public/TempTextFile/" + Integer.valueOf(name)) + ".txt -y -c copy public/GeneratedVideo/") + Integer.valueOf(name)) + ".mp4");
      Process p = Runtime.getRuntime().exec(command);
      p.waitFor();
      final File file = new File((("public/TempTextFile/" + Integer.valueOf(name)) + ".txt"));
      file.delete();
      String _plus_6 = (Integer.valueOf(name) + ".mp4");
      InputOutput.<String>println(_plus_6);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
