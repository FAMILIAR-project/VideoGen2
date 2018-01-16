package fr.istic.m2il.idm.videogentransformations.transformations;

import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenChekerHelper;
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils;
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.Filter;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class VideoGenPlayTransformations {
  public static String generateRandomPlayList(final VideoGeneratorModel videoGen) {
    String _xifexpression = null;
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _xifexpression_1 = null;
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      boolean _tripleNotEquals = (_outPutFoulder != null);
      if (_tripleNotEquals) {
        String _xblockexpression = null;
        {
          List<String> playlist = VideoGenUtils.getRandomPlaylist(videoGen);
          File _outPutFoulder_1 = VideoGenConfigs.getOutPutFoulder();
          String _plus = (_outPutFoulder_1 + "/playlists/playlist.mp4");
          _xblockexpression = VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName(_plus));
        }
        _xifexpression_1 = _xblockexpression;
      }
      _xifexpression = _xifexpression_1;
    } else {
      return null;
    }
    return _xifexpression;
  }
  
  public static List<String> getRandomPlayList(final VideoGeneratorModel videoGen) {
    List<String> _xifexpression = null;
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      List<String> _xifexpression_1 = null;
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      boolean _tripleNotEquals = (_outPutFoulder != null);
      if (_tripleNotEquals) {
        List<String> _xblockexpression = null;
        {
          List<String> playlist = VideoGenUtils.getRandomPlaylist(videoGen);
          File _outPutFoulder_1 = VideoGenConfigs.getOutPutFoulder();
          String _plus = (_outPutFoulder_1 + "/playlists/playlist.mp4");
          VideoGenUtils.makePlaylist(playlist, CommonUtils.getOutPutFileName(_plus));
          _xblockexpression = playlist;
        }
        _xifexpression_1 = _xblockexpression;
      }
      _xifexpression = _xifexpression_1;
    } else {
      return null;
    }
    return _xifexpression;
  }
  
  public static List<String> makeThumbnails(final VideoGeneratorModel videoGen) {
    ArrayList<String> _xifexpression = null;
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      ArrayList<String> _xifexpression_1 = null;
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      boolean _tripleNotEquals = (_outPutFoulder != null);
      if (_tripleNotEquals) {
        ArrayList<String> _xblockexpression = null;
        {
          ArrayList<String> thumbs = new ArrayList<String>();
          EList<Media> _medias = videoGen.getMedias();
          for (final Media media : _medias) {
            {
              if ((media instanceof AlternativesMedia)) {
                EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
                for (final MediaDescription alternativeMedia : _medias_1) {
                  thumbs.add(FFMPEGHelper.generateThumbnail(alternativeMedia.getLocation()));
                }
              }
              if ((media instanceof MandatoryMedia)) {
                thumbs.add(FFMPEGHelper.generateThumbnail(((MandatoryMedia)media).getDescription().getLocation()));
              }
              if ((media instanceof OptionalMedia)) {
                thumbs.add(FFMPEGHelper.generateThumbnail(((OptionalMedia)media).getDescription().getLocation()));
              }
            }
          }
          _xblockexpression = thumbs;
        }
        _xifexpression_1 = _xblockexpression;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public static List<String> makeWebPage(final VideoGeneratorModel videoGen) {
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      boolean _tripleNotEquals = (_outPutFoulder != null);
      if (_tripleNotEquals) {
        ArrayList<String> html = new ArrayList<String>();
        html.add("<div class=\"row\" id=\"gallery\">");
        EList<Media> _medias = videoGen.getMedias();
        for (final Media media : _medias) {
          {
            if ((media instanceof AlternativesMedia)) {
              html.add("  <div class=\"row alternatives\">");
              EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
              for (final MediaDescription alternativeMedia : _medias_1) {
                {
                  if ((alternativeMedia instanceof VideoDescription)) {
                    String _generateThumbnail = FFMPEGHelper.generateThumbnail(((VideoDescription)alternativeMedia).getLocation());
                    String _plus = ("    <div class=\"thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _generateThumbnail);
                    String _plus_1 = (_plus + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
                    html.add(_plus_1);
                  }
                  if ((alternativeMedia instanceof ImageDescription)) {
                    String _location = ((ImageDescription)alternativeMedia).getLocation();
                    String _plus_2 = ("    <div class=\"thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _location);
                    String _plus_3 = (_plus_2 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
                    html.add(_plus_3);
                  }
                  html.add("    </div>");
                }
              }
              html.add("  </div>");
            }
            if ((media instanceof MandatoryMedia)) {
              MediaDescription _description = ((MandatoryMedia)media).getDescription();
              if ((_description instanceof VideoDescription)) {
                String _generateThumbnail = FFMPEGHelper.generateThumbnail(((MandatoryMedia)media).getDescription().getLocation());
                String _plus = ("  <div class=\"row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _generateThumbnail);
                String _plus_1 = (_plus + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
                html.add(_plus_1);
              }
              MediaDescription _description_1 = ((MandatoryMedia)media).getDescription();
              if ((_description_1 instanceof ImageDescription)) {
                String _location = ((MandatoryMedia)media).getDescription().getLocation();
                String _plus_2 = ("  <div class=\"row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _location);
                String _plus_3 = (_plus_2 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
                html.add(_plus_3);
              }
              html.add("  </div>");
            }
            if ((media instanceof OptionalMedia)) {
              MediaDescription _description_2 = ((OptionalMedia)media).getDescription();
              if ((_description_2 instanceof VideoDescription)) {
                String _generateThumbnail_1 = FFMPEGHelper.generateThumbnail(((OptionalMedia)media).getDescription().getLocation());
                String _plus_4 = ("  <div class=\" row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _generateThumbnail_1);
                String _plus_5 = (_plus_4 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
                html.add(_plus_5);
              }
              MediaDescription _description_3 = ((OptionalMedia)media).getDescription();
              if ((_description_3 instanceof ImageDescription)) {
                String _location_1 = ((OptionalMedia)media).getDescription().getLocation();
                String _plus_6 = ("  <div class=\" row thumb\" style=\"width: 104.3px; height: 104.3px; background-image: url(&quot;http://" + _location_1);
                String _plus_7 = (_plus_6 + "&quot;);background-size: 3764.8px 110.729px; background-position: -2549.63px -2.85753px;\">");
                html.add(_plus_7);
              }
              html.add("  </div>");
            }
          }
        }
        html.add("</div>");
        return html;
      }
    } else {
      return null;
    }
    return null;
  }
  
  public static List<String> videoGensToGifs(final VideoGeneratorModel videoGen) {
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      if ((((VideoGenConfigs.getOutPutFoulder() != null) && (VideoGenConfigs.getGifResolutions()[0] > 0)) && (VideoGenConfigs.getGifResolutions()[1] > 0))) {
        ArrayList<String> gifs = new ArrayList<String>();
        final List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
        for (final List<MediaDescription> playlist : playlists) {
          gifs.add(VideoGenPlayTransformations.videoGenToGif(playlist));
        }
        return gifs;
      }
    } else {
      return null;
    }
    return null;
  }
  
  public static String videoGenToGif(final List<MediaDescription> playlist) {
    List<String> filterizedList = VideoGenPlayTransformations.applyFilters(playlist);
    if ((((VideoGenConfigs.getOutPutFoulder() != null) && (VideoGenConfigs.getGifResolutions()[0] > 0)) && (VideoGenConfigs.getGifResolutions()[1] > 0))) {
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus = (_outPutFoulder + "/playlists/playlist.mp4");
      return FFMPEGHelper.videoToGif(
        VideoGenUtils.makePlaylist(filterizedList, 
          CommonUtils.getOutPutFileName(_plus)), 
        VideoGenConfigs.getGifResolutions()[0], 
        VideoGenConfigs.getGifResolutions()[1]);
    } else {
      return null;
    }
  }
  
  public static String videosToGif(final List<String> videos) {
    String _xblockexpression = null;
    {
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus = (_outPutFoulder + "/playlists/playlist.mp4");
      String video = VideoGenUtils.makePlaylist(videos, CommonUtils.getOutPutFileName(_plus));
      _xblockexpression = FFMPEGHelper.videoToGif(video, VideoGenConfigs.getGifResolutions()[0], VideoGenConfigs.getGifResolutions()[1]);
    }
    return _xblockexpression;
  }
  
  public static List<String> applyFilters(final List<MediaDescription> mediaDescriptions) {
    ArrayList<String> _xblockexpression = null;
    {
      ArrayList<String> medias = CollectionLiterals.<String>newArrayList();
      for (final MediaDescription media : mediaDescriptions) {
        if ((media instanceof VideoDescription)) {
          if ((media != null)) {
            Filter _filter = ((VideoDescription)media).getFilter();
            boolean _tripleNotEquals = (_filter != null);
            if (_tripleNotEquals) {
              medias.add(FFMPEGHelper.applyFilter(VideoGenUtils.getFilter(((VideoDescription)media)), ((VideoDescription)media).getLocation()));
            } else {
              medias.add(((VideoDescription)media).getLocation());
            }
          }
        } else {
          if ((((ImageDescription) media) != null)) {
            medias.add(media.getLocation());
          }
        }
      }
      _xblockexpression = medias;
    }
    return _xblockexpression;
  }
}
