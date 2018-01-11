package utils;

import configs.VideoGenConfigs;
import helpers.FFMPEGHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class VideoGenUtils {
  public static List<List<MediaDescription>> generatePlaylists(final VideoGeneratorModel videoGen) {
    ArrayList<List<MediaDescription>> playlists = new ArrayList<List<MediaDescription>>();
    ArrayList<MediaDescription> _arrayList = new ArrayList<MediaDescription>();
    playlists.add(_arrayList);
    EList<Media> _medias = videoGen.getMedias();
    for (final Media media : _medias) {
      if ((media instanceof MandatoryMedia)) {
        for (final List<MediaDescription> playlist : playlists) {
          playlist.add(((MandatoryMedia)media).getDescription());
        }
      } else {
        if ((media instanceof AlternativesMedia)) {
          playlists = VideoGenUtils.populatePlaylists(playlists, ((AlternativesMedia)media));
        } else {
          if ((media instanceof OptionalMedia)) {
            playlists = VideoGenUtils.populatePlaylists(playlists, ((OptionalMedia)media));
          }
        }
      }
    }
    return playlists;
  }
  
  private static ArrayList<List<MediaDescription>> populatePlaylists(final List<List<MediaDescription>> playlists, final OptionalMedia opt) {
    final ArrayList<List<MediaDescription>> newPlaylists = new ArrayList<List<MediaDescription>>();
    for (final List<MediaDescription> playlist : playlists) {
      {
        ArrayList<MediaDescription> list1 = new ArrayList<MediaDescription>(playlist);
        list1.add(opt.getDescription());
        newPlaylists.add(list1);
        ArrayList<MediaDescription> list2 = new ArrayList<MediaDescription>(playlist);
        list2.add(null);
        newPlaylists.add(list2);
      }
    }
    return newPlaylists;
  }
  
  private static ArrayList<List<MediaDescription>> populatePlaylists(final List<List<MediaDescription>> playlists, final AlternativesMedia alt) {
    final ArrayList<List<MediaDescription>> newPlaylists = new ArrayList<List<MediaDescription>>();
    for (final List<MediaDescription> playlist : playlists) {
      {
        int _size = alt.eContents().size();
        int size = (_size - 1);
        int index = 0;
        EList<EObject> _eContents = alt.eContents();
        for (final EObject desc : _eContents) {
          {
            ArrayList<MediaDescription> list = new ArrayList<MediaDescription>(playlist);
            for (int i = 0; (i < index); i++) {
              list.add(null);
            }
            list.add(((MediaDescription) desc));
            for (int i = 0; (i < size); i++) {
              list.add(null);
            }
            newPlaylists.add(list);
            size--;
            index++;
          }
        }
      }
    }
    return newPlaylists;
  }
  
  public static String makePlaylist(final List<MediaDescription> medias, final String playlistName) {
    final ArrayList<int[]> resolutions = new ArrayList<int[]>();
    for (final MediaDescription media : medias) {
      if (((media != null) && (!media.getLocation().replace(".", "@").split("@")[1].equals("jpg")))) {
        resolutions.add(FFMPEGHelper.getVideoResolution(media.getLocation()));
      }
    }
    int maxOutputWidth = 0;
    int maxOutputHeight = 0;
    for (final int[] resolution : resolutions) {
      {
        int _get = resolution[0];
        boolean _greaterThan = (_get > maxOutputWidth);
        if (_greaterThan) {
          maxOutputWidth = resolution[0];
        }
        int _get_1 = resolution[1];
        boolean _greaterThan_1 = (_get_1 > maxOutputHeight);
        if (_greaterThan_1) {
          maxOutputHeight = resolution[1];
        }
      }
    }
    int i = 0;
    final ArrayList<MediaDescription> playlist = new ArrayList<MediaDescription>();
    for (final MediaDescription media_1 : medias) {
      if ((media_1 != null)) {
        media_1.setLocation(FFMPEGHelper.homogenizeMediaResolution(
          media_1.getLocation(), 
          FFMPEGHelper.getVideoResolution(media_1.getLocation())[0], 
          FFMPEGHelper.getVideoResolution(media_1.getLocation())[1], maxOutputWidth, maxOutputHeight));
        playlist.add(media_1);
      }
    }
    return FFMPEGHelper.concatVideos(playlist, playlistName);
  }
  
  public static double getVideoSize(final MediaDescription media) {
    return VideoGenUtils.getVideoSize(media.getLocation());
  }
  
  public static double getVideoSize(final String location) {
    return new File(location).length();
  }
  
  public static int getVariantNumber(final VideoGeneratorModel videoGen) {
    int variantNumber = 1;
    EList<Media> _medias = videoGen.getMedias();
    for (final Media media : _medias) {
      if ((media instanceof OptionalMedia)) {
        int _variantNumber = variantNumber;
        variantNumber = (_variantNumber * 2);
      } else {
        if ((media instanceof AlternativesMedia)) {
          int _variantNumber_1 = variantNumber;
          int _size = ((AlternativesMedia)media).getMedias().size();
          variantNumber = (_variantNumber_1 * _size);
        }
      }
    }
    return variantNumber;
  }
  
  public static List<String> getGif(final VideoGeneratorModel videoGen, final int width, final int heigth) {
    ArrayList<String> _xblockexpression = null;
    {
      ArrayList<String> playlistsGif = new ArrayList<String>();
      List<List<MediaDescription>> playlists = VideoGenUtils.generatePlaylists(videoGen);
      int playlistIndex = 0;
      for (final List<MediaDescription> playlist : playlists) {
        File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
        String _plus = (_outPutFoulder + "/gifs/playlist_");
        String _plus_1 = (_plus + Integer.valueOf(playlistIndex));
        playlistsGif.add(VideoGenUtils.getGif(playlist, _plus_1, width, heigth));
      }
      _xblockexpression = playlistsGif;
    }
    return _xblockexpression;
  }
  
  public static String getGif(final List<MediaDescription> playlist, final String playlistName, final int width, final int heigth) {
    return FFMPEGHelper.videoToGif(VideoGenUtils.makePlaylist(playlist, playlistName), width, heigth);
  }
  
  public static VideoDescription getRandom(final List<VideoDescription> videos) {
    VideoDescription _xblockexpression = null;
    {
      Random random = new Random();
      int totalSum = 0;
      for (final VideoDescription video : videos) {
        int _talSum = totalSum;
        int _probability = video.getProbability();
        totalSum = (_talSum + _probability);
      }
      if ((totalSum == 0)) {
        int _size = videos.size();
        int _minus = (_size - 1);
        VideoDescription _get = videos.get(_minus);
        if ((_get instanceof AlternativesMedia)) {
          double _random = Math.random();
          int _size_1 = videos.size();
          double _multiply = (_random * _size_1);
          int alternativesIndex = ((int) _multiply);
          return videos.get(alternativesIndex);
        } else {
          double _random_1 = Math.random();
          double _multiply_1 = (_random_1 * 2);
          boolean _lessThan = (_multiply_1 < 1);
          if (_lessThan) {
            return videos.get(0);
          } else {
            return null;
          }
        }
      }
      int index = random.nextInt(totalSum);
      int sum = 0;
      int i = 0;
      while ((sum < index)) {
        int _plusPlus = i++;
        int _probability_1 = videos.get(_plusPlus).getProbability();
        int _plus = (sum + _probability_1);
        sum = _plus;
      }
      _xblockexpression = videos.get(Math.max(0, (i - 1)));
    }
    return _xblockexpression;
  }
}
