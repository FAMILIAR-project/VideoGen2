package helpers;

import configs.VideoGenConfigs;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoText;
import transformations.VideoGenAnalysisTransformation;
import utils.CommonUtils;
import utils.VideoGenUtils;

@SuppressWarnings("all")
public class CSVHelper {
  private static String addLine(final List<MediaDescription> playlist, final int index, final boolean isGif) {
    String line = (Integer.valueOf(index) + ";");
    double size = 0.0;
    for (final MediaDescription mediaDescription : playlist) {
      if ((mediaDescription != null)) {
        VideoText _text = ((VideoDescription) mediaDescription).getText();
        boolean _tripleNotEquals = (_text != null);
        if (_tripleNotEquals) {
          double _size = size;
          double _videoSize = VideoGenUtils.getVideoSize(mediaDescription);
          size = (_size + _videoSize);
        }
        String _line = line;
        line = (_line + ("TRUE" + ";"));
      } else {
        String _line_1 = line;
        line = (_line_1 + ("FALSE" + ";"));
      }
    }
    if (isGif) {
      double _realSize = VideoGenAnalysisTransformation.getRealSize(playlist);
      return (((line + Double.valueOf(size)) + ";") + Double.valueOf(_realSize));
    } else {
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus = (_outPutFoulder + "/playlists/playlist.mp4");
      double _realSize_1 = VideoGenAnalysisTransformation.getRealSize(VideoGenUtils.getGif(playlist, CommonUtils.getOutPutFileName(_plus), 190, 60));
      return (((line + Double.valueOf(size)) + ";") + Double.valueOf(_realSize_1));
    }
  }
  
  private static String addHeader(final List<List<MediaDescription>> playlists) {
    final ArrayList<String> headers = new ArrayList<String>();
    List<MediaDescription> _get = playlists.get(0);
    for (final MediaDescription mediaDescription : _get) {
      headers.add(null);
    }
    for (final List<MediaDescription> playlist : playlists) {
      {
        int index = 0;
        for (final MediaDescription mediaDescription_1 : playlist) {
          {
            if (((headers.get(index) == null) && (mediaDescription_1 != null))) {
              headers.set(index, ((VideoDescription) mediaDescription_1).getVideoid());
            }
            index++;
          }
        }
      }
    }
    String header = "id;";
    for (final String str : headers) {
      String _header = header;
      header = (_header + (str + ";"));
    }
    String _header_1 = header;
    header = (_header_1 + "size;");
    String _header_2 = header;
    header = (_header_2 + "realSize");
    return header;
  }
  
  public static List<String> create(final VideoGeneratorModel videoGen, final boolean isGif) {
    return CSVHelper.create(VideoGenUtils.generatePlaylists(videoGen), isGif);
  }
  
  public static List<String> create(final List<List<MediaDescription>> playlists, final boolean isGif) {
    ArrayList<String> csvContent = new ArrayList<String>();
    csvContent.add(CSVHelper.addHeader(playlists));
    int index = 0;
    for (final List<MediaDescription> playlist : playlists) {
      int _plusPlus = index++;
      String _addLine = CSVHelper.addLine(playlist, _plusPlus, isGif);
      String _plus = (_addLine + "\n");
      csvContent.add(_plus);
    }
    return csvContent;
  }
}
