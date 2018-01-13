package fr.istic.m2il.idm.videogentransformations.helpers;

import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenAnalysisTransformations;
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils;
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoText;

@SuppressWarnings("all")
public class CSVHelper {
  private static String addLine(final List<MediaDescription> playlist, final int index, final boolean isDuration, final boolean isGif) {
    String line = (Integer.valueOf(index) + ";");
    double size = 0.0;
    for (final MediaDescription mediaDescription : playlist) {
      if ((mediaDescription != null)) {
        if ((mediaDescription instanceof VideoDescription)) {
          if (isDuration) {
            VideoText _text = ((VideoDescription) mediaDescription).getText();
            boolean _tripleNotEquals = (_text != null);
            if (_tripleNotEquals) {
              double _size = size;
              int _duration = ((VideoDescription) mediaDescription).getDuration();
              size = (_size + _duration);
            } else {
              double _size_1 = size;
              int _videoDuration = FFMPEGHelper.getVideoDuration(((VideoDescription)mediaDescription).getLocation());
              size = (_size_1 + _videoDuration);
            }
          } else {
            VideoText _text_1 = ((VideoDescription) mediaDescription).getText();
            boolean _tripleNotEquals_1 = (_text_1 != null);
            if (_tripleNotEquals_1) {
              double _size_2 = size;
              int _size_3 = ((VideoDescription) mediaDescription).getText().getSize();
              size = (_size_2 + _size_3);
            } else {
              double _size_4 = size;
              double _videoSize = VideoGenUtils.getVideoSize(mediaDescription);
              size = (_size_4 + _videoSize);
            }
          }
        } else {
          if (isDuration) {
            double _size_5 = size;
            int _videoDuration_1 = FFMPEGHelper.getVideoDuration(mediaDescription.getLocation());
            size = (_size_5 + ((double) _videoDuration_1));
          } else {
            double _size_6 = size;
            double _videoSize_1 = VideoGenUtils.getVideoSize(mediaDescription);
            size = (_size_6 + _videoSize_1);
          }
        }
        String _line = line;
        line = (_line + ("TRUE" + ";"));
      } else {
        String _line_1 = line;
        line = (_line_1 + ("FALSE" + ";"));
      }
    }
    if (isDuration) {
      int _videoDuration_2 = FFMPEGHelper.getVideoDuration(playlist);
      return (((line + Double.valueOf(size)) + ";") + Integer.valueOf(_videoDuration_2));
    } else {
      if (isGif) {
        double _realSize = VideoGenAnalysisTransformations.getRealSize(playlist);
        return (((line + Double.valueOf(size)) + ";") + Double.valueOf(_realSize));
      } else {
        File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
        String _plus = (_outPutFoulder + "/playlists/playlist.mp4");
        double _realSize_1 = VideoGenAnalysisTransformations.getRealSize(VideoGenUtils.getGif(playlist, CommonUtils.getOutPutFileName(_plus), 190, 60));
        return (((line + Double.valueOf(size)) + ";") + Double.valueOf(_realSize_1));
      }
    }
  }
  
  private static String addHeader(final List<List<MediaDescription>> playlists, final boolean isDuration) {
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
              if ((mediaDescription_1 instanceof VideoDescription)) {
                headers.set(index, ((VideoDescription) mediaDescription_1).getVideoid());
              }
              if ((mediaDescription_1 instanceof ImageDescription)) {
                headers.set(index, ((ImageDescription) mediaDescription_1).getImageid());
              }
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
    if (isDuration) {
      String _header_1 = header;
      header = (_header_1 + "duration;");
      String _header_2 = header;
      header = (_header_2 + "realDuration");
    } else {
      String _header_3 = header;
      header = (_header_3 + "size;");
      String _header_4 = header;
      header = (_header_4 + "realSize");
    }
    return header;
  }
  
  public static List<String> create(final VideoGeneratorModel videoGen, final boolean isDuration, final boolean isGif) {
    return CSVHelper.create(VideoGenUtils.generatePlaylists(videoGen), isDuration, isGif);
  }
  
  public static List<String> create(final List<List<MediaDescription>> playlists, final boolean isDuration, final boolean isGif) {
    ArrayList<String> csvContent = new ArrayList<String>();
    String _addHeader = CSVHelper.addHeader(playlists, isDuration);
    String _plus = (_addHeader + "\n");
    csvContent.add(_plus);
    int index = 0;
    for (final List<MediaDescription> playlist : playlists) {
      int _plusPlus = index++;
      String _addLine = CSVHelper.addLine(playlist, _plusPlus, isDuration, isGif);
      String _plus_1 = (_addLine + "\n");
      csvContent.add(_plus_1);
    }
    File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
    String _plus_2 = (_outPutFoulder + "/analysis.csv");
    final ArrayList<String> _converted_csvContent = (ArrayList<String>)csvContent;
    CommonUtils.writeFileOnDisk(CommonUtils.getOutPutFileName(_plus_2), ((String[])Conversions.unwrapArray(_converted_csvContent, String.class)));
    return csvContent;
  }
}
