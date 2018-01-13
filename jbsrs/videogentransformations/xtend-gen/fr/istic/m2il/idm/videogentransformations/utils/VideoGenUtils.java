package fr.istic.m2il.idm.videogentransformations.utils;

import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper;
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.ImageDescription;
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
    VideoGenUtils.writePlaylistsFile(playlists);
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
  
  public static String makePlaylist(final List<String> locations, final String playlistName) {
    final ArrayList<int[]> resolutions = new ArrayList<int[]>();
    for (final String location : locations) {
      if (((location != null) && 
        (((((!location.replace(".", "@").split("@")[1].equals("jpg")) && 
          (!location.replace(".", "@").split("@")[1].equals("png"))) && 
          (!location.replace(".", "@").split("@")[1].equals("gif"))) && 
          (!location.replace(".", "@").split("@")[1].equals("bpm"))) && 
          (!location.replace(".", "@").split("@")[1].equals("tiff"))))) {
        resolutions.add(FFMPEGHelper.getVideoResolution(location));
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
    final ArrayList<String> playlist = new ArrayList<String>();
    for (final String location_1 : locations) {
      if ((location_1 != null)) {
        playlist.add(
          FFMPEGHelper.homogenizeMediaResolution(location_1, 
            FFMPEGHelper.getVideoResolution(location_1)[0], 
            FFMPEGHelper.getVideoResolution(location_1)[1], maxOutputWidth, maxOutputHeight));
      }
    }
    ArrayList<String> playlistWrite = CollectionLiterals.<String>newArrayList();
    for (final String p : playlist) {
      playlistWrite.add(((("file \'" + p) + "\'") + "\n"));
    }
    File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
    String _plus = (_outPutFoulder + "/playlists/playlist.txt");
    final ArrayList<String> _converted_playlistWrite = (ArrayList<String>)playlistWrite;
    CommonUtils.writeFileOnDisk(CommonUtils.getOutPutFileName(_plus), ((String[])Conversions.unwrapArray(_converted_playlistWrite, String.class)));
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
    return FFMPEGHelper.videoToGif(VideoGenUtils.makePlaylist(VideoGenUtils.getMediaDescriptionsLocation(playlist), playlistName), width, heigth);
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
  
  public static int getVideoGenMediasNumber(final VideoGeneratorModel videoGen) {
    int _xblockexpression = (int) 0;
    {
      int mediasNumber = 0;
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        {
          if ((media instanceof MandatoryMedia)) {
            mediasNumber++;
          }
          if ((media instanceof OptionalMedia)) {
            mediasNumber++;
          }
          if ((media instanceof AlternativesMedia)) {
            EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
            for (final MediaDescription m : _medias_1) {
              mediasNumber++;
            }
          }
        }
      }
      _xblockexpression = mediasNumber;
    }
    return _xblockexpression;
  }
  
  public static void writePlaylistsFile(final List<List<MediaDescription>> playlists) {
    final ArrayList<String> files = new ArrayList<String>();
    for (final List<MediaDescription> p : playlists) {
      {
        for (final MediaDescription media : p) {
          if ((media != null)) {
            String _location = media.getLocation();
            String _plus = ("file \'" + _location);
            String _plus_1 = (_plus + "\'");
            String _plus_2 = (_plus_1 + "\n");
            files.add(_plus_2);
          }
        }
        File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
        String _plus_3 = (_outPutFoulder + "/playlists/playlist.txt");
        CommonUtils.writeFileOnDisk(CommonUtils.getOutPutFileName(_plus_3), ((String[])Conversions.unwrapArray(files, String.class)));
      }
    }
  }
  
  public static List<String> getMediaDescriptionsLocation(final List<MediaDescription> playlist) {
    ArrayList<String> _xblockexpression = null;
    {
      ArrayList<String> playlistlocations = CollectionLiterals.<String>newArrayList();
      for (final MediaDescription media : playlist) {
        if ((media instanceof AlternativesMedia)) {
          EList<MediaDescription> _medias = ((AlternativesMedia)media).getMedias();
          for (final MediaDescription alt : _medias) {
            if ((media != null)) {
              playlistlocations.add(media.getLocation());
            }
          }
        } else {
          if ((media != null)) {
            playlistlocations.add(media.getLocation());
          }
        }
      }
      _xblockexpression = playlistlocations;
    }
    return _xblockexpression;
  }
  
  public static List<String> getRandomPlaylist(final VideoGeneratorModel videoGen) {
    List<String> _xblockexpression = null;
    {
      ArrayList<MediaDescription> playlist = CollectionLiterals.<MediaDescription>newArrayList();
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
      _xblockexpression = VideoGenUtils.getMediaDescriptionsLocation(playlist);
    }
    return _xblockexpression;
  }
}
