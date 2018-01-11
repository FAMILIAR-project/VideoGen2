package transformations;

import configs.VideoGenConfigs;
import helpers.FFMPEGHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import utils.CommonUtils;
import utils.VideoGenUtils;

@SuppressWarnings("all")
public class VideoGenPlayTransformations {
  public static String generateRandomPlayList(final VideoGeneratorModel videoGen) {
    String _xblockexpression = null;
    {
      ArrayList<MediaDescription> playlist = new ArrayList<MediaDescription>();
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        {
          if ((media instanceof MandatoryMedia)) {
            playlist.add(((MandatoryMedia)media).getDescription());
          }
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description = ((OptionalMedia)media).getDescription();
            if ((_description instanceof ImageDescription)) {
              double _random = Math.random();
              double _multiply = (_random * 2);
              boolean _lessThan = (_multiply < 1);
              if (_lessThan) {
                playlist.add(((OptionalMedia)media).getDescription());
              }
            }
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof VideoDescription)) {
              ArrayList<VideoDescription> list = new ArrayList<VideoDescription>();
              MediaDescription _description_2 = ((OptionalMedia)media).getDescription();
              final VideoDescription optionalVideo = ((VideoDescription) _description_2);
              list.add(optionalVideo);
              final VideoDescription video = VideoGenUtils.getRandom(list);
              if ((video != null)) {
                playlist.add(video);
              }
            }
          }
          if ((media instanceof AlternativesMedia)) {
            boolean isImageDescription = false;
            MediaDescription _get = ((AlternativesMedia)media).getMedias().get(0);
            if ((_get instanceof ImageDescription)) {
              isImageDescription = true;
            }
            if (isImageDescription) {
              double _random_1 = Math.random();
              int _size = ((AlternativesMedia)media).getMedias().size();
              double _multiply_1 = (_random_1 * _size);
              int alternativesIndex = ((int) _multiply_1);
              MediaDescription _get_1 = ((AlternativesMedia)media).getMedias().get(alternativesIndex);
              final MediaDescription mdescription = ((MediaDescription) _get_1);
              playlist.add(mdescription);
            } else {
              ArrayList<VideoDescription> list_1 = new ArrayList<VideoDescription>();
              EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
              for (final MediaDescription alternative : _medias_1) {
                {
                  final VideoDescription alternaiveVideo = ((VideoDescription) alternative);
                  list_1.add(alternaiveVideo);
                }
              }
              playlist.add(VideoGenUtils.getRandom(list_1));
            }
          }
        }
      }
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus = (_outPutFoulder + "/playlists/playlist.mp4");
      _xblockexpression = VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName(_plus));
    }
    return _xblockexpression;
  }
  
  public static HashMap<String, List<String>> makeThumbnails(final VideoGeneratorModel videoGen) {
    HashMap<String, List<String>> _xblockexpression = null;
    {
      HashMap<String, List<String>> thumbs = new HashMap<String, List<String>>();
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        {
          if ((media instanceof AlternativesMedia)) {
            EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
            for (final MediaDescription alternativeMedia : _medias_1) {
              if ((alternativeMedia instanceof VideoDescription)) {
                List<String> _get = thumbs.get("Alternatives");
                boolean _tripleNotEquals = (_get != null);
                if (_tripleNotEquals) {
                  List<String> _get_1 = thumbs.get("Alternatives");
                  List<String> list = ((List<String>) _get_1);
                  list.add(FFMPEGHelper.generateThumbnail(((VideoDescription)alternativeMedia).getLocation()));
                } else {
                  ArrayList<String> list_1 = new ArrayList<String>();
                  list_1.add(FFMPEGHelper.generateThumbnail(((VideoDescription)alternativeMedia).getLocation()));
                  thumbs.put("Alternatives", list_1);
                }
              }
            }
          }
          if ((media instanceof MandatoryMedia)) {
            MediaDescription _description = ((MandatoryMedia)media).getDescription();
            if ((_description instanceof VideoDescription)) {
              List<String> _get_2 = thumbs.get("Mandatory");
              boolean _tripleNotEquals_1 = (_get_2 != null);
              if (_tripleNotEquals_1) {
                List<String> _get_3 = thumbs.get("Mandatory");
                List<String> list_2 = ((List<String>) _get_3);
                list_2.add(FFMPEGHelper.generateThumbnail(((MandatoryMedia)media).getDescription().getLocation()));
              } else {
                ArrayList<String> list_3 = new ArrayList<String>();
                list_3.add(FFMPEGHelper.generateThumbnail(((MandatoryMedia)media).getDescription().getLocation()));
                thumbs.put("Mandatory", list_3);
              }
            }
          }
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof VideoDescription)) {
              List<String> _get_4 = thumbs.get("Optional");
              boolean _tripleNotEquals_2 = (_get_4 != null);
              if (_tripleNotEquals_2) {
                List<String> _get_5 = thumbs.get("Optional");
                List<String> list_4 = ((List<String>) _get_5);
                list_4.add(FFMPEGHelper.generateThumbnail(((OptionalMedia)media).getDescription().getLocation()));
              } else {
                ArrayList<String> list_5 = new ArrayList<String>();
                list_5.add(FFMPEGHelper.generateThumbnail(((OptionalMedia)media).getDescription().getLocation()));
                thumbs.put("Optional", list_5);
              }
            }
          }
        }
      }
      _xblockexpression = thumbs;
    }
    return _xblockexpression;
  }
  
  public static List<String> makeWebPage(final VideoGeneratorModel videoGen) {
    ArrayList<String> _xblockexpression = null;
    {
      ArrayList<String> html = new ArrayList<String>();
      html.add("<div id=\"gallery\">");
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        {
          if ((media instanceof AlternativesMedia)) {
            html.add("  <div class=\"row alternatives\">");
            EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
            for (final MediaDescription alternativeMedia : _medias_1) {
              {
                if ((alternativeMedia instanceof VideoDescription)) {
                  String _serverIP = VideoGenConfigs.getServerIP();
                  String _plus = ("    <div class=\"thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _serverIP);
                  String _generateThumbnail = FFMPEGHelper.generateThumbnail(((VideoDescription)alternativeMedia).getLocation());
                  String _plus_1 = (_plus + _generateThumbnail);
                  String _plus_2 = (_plus_1 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
                  html.add(_plus_2);
                }
                if ((alternativeMedia instanceof ImageDescription)) {
                  String _serverIP_1 = VideoGenConfigs.getServerIP();
                  String _plus_3 = ("    <div class=\"thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _serverIP_1);
                  String _location = ((ImageDescription)alternativeMedia).getLocation();
                  String _plus_4 = (_plus_3 + _location);
                  String _plus_5 = (_plus_4 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
                  html.add(_plus_5);
                }
                html.add("    </div>");
              }
            }
            html.add("  </div>");
          }
          if ((media instanceof MandatoryMedia)) {
            MediaDescription _description = ((MandatoryMedia)media).getDescription();
            if ((_description instanceof VideoDescription)) {
              String _serverIP = VideoGenConfigs.getServerIP();
              String _plus = ("  <div class=\"row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _serverIP);
              String _generateThumbnail = FFMPEGHelper.generateThumbnail(((MandatoryMedia)media).getDescription().getLocation());
              String _plus_1 = (_plus + _generateThumbnail);
              String _plus_2 = (_plus_1 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
              html.add(_plus_2);
            }
            MediaDescription _description_1 = ((MandatoryMedia)media).getDescription();
            if ((_description_1 instanceof ImageDescription)) {
              String _serverIP_1 = VideoGenConfigs.getServerIP();
              String _plus_3 = ("  <div class=\"row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _serverIP_1);
              String _location = ((MandatoryMedia)media).getDescription().getLocation();
              String _plus_4 = (_plus_3 + _location);
              String _plus_5 = (_plus_4 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
              html.add(_plus_5);
            }
            html.add("  </div>");
          }
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_2 = ((OptionalMedia)media).getDescription();
            if ((_description_2 instanceof VideoDescription)) {
              String _serverIP_2 = VideoGenConfigs.getServerIP();
              String _plus_6 = ("  <div class=\" row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _serverIP_2);
              String _generateThumbnail_1 = FFMPEGHelper.generateThumbnail(((OptionalMedia)media).getDescription().getLocation());
              String _plus_7 = (_plus_6 + _generateThumbnail_1);
              String _plus_8 = (_plus_7 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
              html.add(_plus_8);
            }
            MediaDescription _description_3 = ((OptionalMedia)media).getDescription();
            if ((_description_3 instanceof ImageDescription)) {
              String _serverIP_3 = VideoGenConfigs.getServerIP();
              String _plus_9 = ("  <div class=\" row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _serverIP_3);
              String _location_1 = ((OptionalMedia)media).getDescription().getLocation();
              String _plus_10 = (_plus_9 + _location_1);
              String _plus_11 = (_plus_10 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
              html.add(_plus_11);
            }
            html.add("  </div>");
          }
        }
      }
      html.add("</div>");
      _xblockexpression = html;
    }
    return _xblockexpression;
  }
  
  public static List<String> videoGensToGifs(final VideoGeneratorModel videoGen) {
    ArrayList<String> _xblockexpression = null;
    {
      ArrayList<String> gifs = new ArrayList<String>();
      final List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
      for (final List<MediaDescription> playlist : playlists) {
        gifs.add(VideoGenPlayTransformations.videoGenToGif(playlist));
      }
      _xblockexpression = gifs;
    }
    return _xblockexpression;
  }
  
  public static String videoGenToGif(final List<MediaDescription> playlist) {
    File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
    String _plus = (_outPutFoulder + "/playlists/playlist.mp4");
    return FFMPEGHelper.videoToGif(
      VideoGenUtils.makePlaylist(playlist, 
        CommonUtils.getOutPutFileName(_plus)), 
      VideoGenConfigs.getGifResolutions()[0], 
      VideoGenConfigs.getGifResolutions()[1]);
  }
}
