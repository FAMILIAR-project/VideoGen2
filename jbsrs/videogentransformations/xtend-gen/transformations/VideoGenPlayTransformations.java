package transformations;

import helpers.FFMPEGHelper;
import java.util.ArrayList;
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
      _xblockexpression = VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName("output/playlists/playlist.mp4"));
    }
    return _xblockexpression;
  }
  
  public static String getMaxDuration(final VideoGeneratorModel videoGen) {
    float maxDuration = 0f;
    EList<Media> _medias = videoGen.getMedias();
    for (final Media media : _medias) {
      {
        if ((media instanceof AlternativesMedia)) {
          AlternativesMedia alternativeMedia = ((AlternativesMedia) media);
          float maxAlternativeDuration = 0f;
          EList<MediaDescription> _medias_1 = alternativeMedia.getMedias();
          for (final MediaDescription alternative : _medias_1) {
            if ((alternative instanceof VideoDescription)) {
              VideoDescription videoDescription = ((VideoDescription) alternative);
              float alternativeDuration = FFMPEGHelper.getVideoDuration(videoDescription.getLocation());
              if ((maxAlternativeDuration < alternativeDuration)) {
                maxAlternativeDuration = alternativeDuration;
              }
            }
          }
          float _maxDuration = maxDuration;
          maxDuration = (_maxDuration + maxAlternativeDuration);
        }
        if ((media instanceof MandatoryMedia)) {
          MediaDescription _description = ((MandatoryMedia)media).getDescription();
          if ((_description instanceof VideoDescription)) {
            MediaDescription _description_1 = ((MandatoryMedia)media).getDescription();
            VideoDescription videoDescription_1 = ((VideoDescription) _description_1);
            float _maxDuration_1 = maxDuration;
            float _videoDuration = FFMPEGHelper.getVideoDuration(videoDescription_1.getLocation());
            maxDuration = (_maxDuration_1 + _videoDuration);
          }
        }
        if ((media instanceof OptionalMedia)) {
          MediaDescription _description_2 = ((OptionalMedia)media).getDescription();
          if ((_description_2 instanceof VideoDescription)) {
            MediaDescription _description_3 = ((OptionalMedia)media).getDescription();
            VideoDescription videoDescription_2 = ((VideoDescription) _description_3);
            float _maxDuration_2 = maxDuration;
            float _videoDuration_1 = FFMPEGHelper.getVideoDuration(videoDescription_2.getLocation());
            maxDuration = (_maxDuration_2 + _videoDuration_1);
          }
        }
      }
    }
    double minutesDuration = Math.floor((maxDuration / 60));
    double secondeDuration = ((maxDuration / 60) - minutesDuration);
    String _string = Double.valueOf(minutesDuration).toString();
    String _plus = (_string + " mn ");
    String _string_1 = Double.valueOf(secondeDuration).toString();
    String _plus_1 = (_plus + _string_1);
    String stringDuration = (_plus_1 + " s");
    return stringDuration;
  }
  
  public static List<String> makeThumbnails(final VideoGeneratorModel videoGen) {
    ArrayList<String> _xblockexpression = null;
    {
      final List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
      ArrayList<String> thumbs = new ArrayList<String>();
      for (final List<MediaDescription> playlist : playlists) {
        thumbs.add(FFMPEGHelper.generateThumbnail(VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName("output/playlists/playlist.mp4"))));
      }
      _xblockexpression = thumbs;
    }
    return _xblockexpression;
  }
  
  public static void makeWebPage(final VideoGeneratorModel videoGen) {
  }
  
  public static void videoGenToGif(final VideoGeneratorModel videoGen) {
  }
}
