package transformations;

import java.util.List;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import utils.VideoGenUtils;

@SuppressWarnings("all")
public class VideoGenAnalysisTransformation {
  public static double getMinimalSize(final VideoGeneratorModel videoGen) {
    double _xblockexpression = (double) 0;
    {
      List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
      double minimalSize = VideoGenAnalysisTransformation.getPlayListSize(playlists.get(0));
      for (final List<MediaDescription> playlist : playlists) {
        {
          double playlistSize = VideoGenAnalysisTransformation.getPlayListSize(playlist);
          if ((playlistSize < minimalSize)) {
            minimalSize = playlistSize;
          }
        }
      }
      _xblockexpression = minimalSize;
    }
    return _xblockexpression;
  }
  
  public static double getMaximalSize(final VideoGeneratorModel videoGen) {
    double _xblockexpression = (double) 0;
    {
      double maximalSize = 0.0;
      List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
      for (final List<MediaDescription> playlist : playlists) {
        {
          double playlistSize = VideoGenAnalysisTransformation.getPlayListSize(playlist);
          if ((playlistSize > maximalSize)) {
            maximalSize = playlistSize;
          }
        }
      }
      _xblockexpression = maximalSize;
    }
    return _xblockexpression;
  }
  
  private static double getPlayListSize(final List<MediaDescription> playlist) {
    double _xblockexpression = (double) 0;
    {
      double playlistSize = 0.0;
      for (final MediaDescription video : playlist) {
        {
          final VideoDescription videoDescription = ((VideoDescription) video);
          double _playlistSize = playlistSize;
          double _videoSize = VideoGenUtils.getVideoSize(videoDescription);
          playlistSize = (_playlistSize + _videoSize);
        }
      }
      _xblockexpression = playlistSize;
    }
    return _xblockexpression;
  }
  
  public static double getAverageSize(final VideoGeneratorModel videoGen) {
    double _xblockexpression = (double) 0;
    {
      double averageSize = 0.0;
      List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
      for (final List<MediaDescription> playlist : playlists) {
        {
          double playlistSize = VideoGenAnalysisTransformation.getPlayListSize(playlist);
          double _averageSize = averageSize;
          averageSize = (_averageSize + playlistSize);
        }
      }
      int _size = playlists.size();
      _xblockexpression = (averageSize / _size);
    }
    return _xblockexpression;
  }
  
  public static double getStandardDeviation(final VideoGeneratorModel videoGen) {
    double _xblockexpression = (double) 0;
    {
      double standardDeviation = 0.0;
      List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
      for (final List<MediaDescription> playlist : playlists) {
        double _standardDeviation = standardDeviation;
        double _playListSize = VideoGenAnalysisTransformation.getPlayListSize(playlist);
        double _averageSize = VideoGenAnalysisTransformation.getAverageSize(videoGen);
        double _minus = (_playListSize - _averageSize);
        double _pow = Math.pow(_minus, 2);
        standardDeviation = (_standardDeviation + _pow);
      }
      int _size = playlists.size();
      double _divide = (standardDeviation / _size);
      _xblockexpression = Math.sqrt(_divide);
    }
    return _xblockexpression;
  }
  
  public static double getRealZise(final VideoGeneratorModel videoGen) {
    double _xblockexpression = (double) 0;
    {
      double realSize = 0.0;
      List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
      int playlistIndex = 0;
      for (final List<MediaDescription> playlist : playlists) {
        {
          double _realSize = realSize;
          double _realSize_1 = VideoGenAnalysisTransformation.getRealSize(playlist, ("playlist_" + Integer.valueOf(playlistIndex)));
          realSize = (_realSize + _realSize_1);
          playlistIndex++;
        }
      }
      _xblockexpression = realSize;
    }
    return _xblockexpression;
  }
  
  public static double getRealSize(final List<MediaDescription> playlist, final String playlistName) {
    return VideoGenUtils.getVideoSize(VideoGenUtils.makePlaylist(playlist, playlistName));
  }
  
  public static double getRealSize(final String playlistLocation) {
    return VideoGenUtils.getVideoSize(playlistLocation);
  }
}
