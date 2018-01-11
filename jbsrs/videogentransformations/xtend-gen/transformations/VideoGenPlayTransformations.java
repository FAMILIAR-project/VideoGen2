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
            MediaDescription _description = ((MandatoryMedia)media).getDescription();
            if ((_description instanceof ImageDescription)) {
              playlist.add(((MandatoryMedia)media).getDescription());
            }
            MediaDescription _description_1 = ((MandatoryMedia)media).getDescription();
            if ((_description_1 instanceof VideoDescription)) {
              playlist.add(((MandatoryMedia)media).getDescription());
            }
          }
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_2 = ((OptionalMedia)media).getDescription();
            if ((_description_2 instanceof ImageDescription)) {
              double _random = Math.random();
              double _multiply = (_random * 2);
              boolean _lessThan = (_multiply < 1);
              if (_lessThan) {
                playlist.add(((OptionalMedia)media).getDescription());
              }
            }
            MediaDescription _description_3 = ((OptionalMedia)media).getDescription();
            if ((_description_3 instanceof VideoDescription)) {
              MediaDescription _description_4 = ((OptionalMedia)media).getDescription();
              final VideoDescription vdescription = ((VideoDescription) _description_4);
              double _random_1 = Math.random();
              double _multiply_1 = (_random_1 * 2);
              boolean _lessThan_1 = (_multiply_1 < 1);
              if (_lessThan_1) {
                playlist.add(((OptionalMedia)media).getDescription());
              }
            }
          }
          if ((media instanceof AlternativesMedia)) {
            boolean isImageDescription = false;
            MediaDescription _get = ((AlternativesMedia)media).getMedias().get(0);
            if ((_get instanceof ImageDescription)) {
              isImageDescription = true;
            }
            double _random_2 = Math.random();
            int _size = ((AlternativesMedia)media).getMedias().size();
            double _multiply_2 = (_random_2 * _size);
            int alternativesIndex = ((int) _multiply_2);
            MediaDescription _get_1 = ((AlternativesMedia)media).getMedias().get(alternativesIndex);
            final MediaDescription mdescription = ((MediaDescription) _get_1);
            playlist.add(mdescription);
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
  
  public static String makeWebPage(final VideoGeneratorModel videoGen) {
    throw new Error("Unresolved compilation problems:"
      + "\nno viable alternative at input \'}\'");
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
