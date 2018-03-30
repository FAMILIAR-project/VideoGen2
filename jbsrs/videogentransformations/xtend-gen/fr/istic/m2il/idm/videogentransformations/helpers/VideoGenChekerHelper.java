package fr.istic.m2il.idm.videogentransformations.helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

/**
 * @author Ramadan Soumaila
 * A helper class to check the validity of a videoGen specification
 */
@SuppressWarnings("all")
public class VideoGenChekerHelper {
  /**
   * Checks if a optional video description's probability is valid
   * the probability must less than or equal to 1 or 100%
   * 
   * @param video the video description to check
   * @return a boolean value which is true if the probability is valid , else otherwise
   */
  public static boolean isGoodOptionalVideoProbality(final VideoDescription video) {
    return VideoGenChekerHelper.isGoodVideoProbability(video);
  }
  
  /**
   * Checks if the probabilities of a alternatives medias are valid
   * the sum of his medias's probabilities can't over 100%
   * 
   * @param videos the list of alternattives's video description to check
   * @return a boolean value which is true if the sum of probabilities is valid,or else otherwise
   */
  public static boolean isGoodAlternativesVideosProbabilties(final List<VideoDescription> videos) {
    boolean _xblockexpression = false;
    {
      boolean isGood = true;
      int probabities = 0;
      for (final VideoDescription video : videos) {
        {
          boolean _isGoodVideoProbability = VideoGenChekerHelper.isGoodVideoProbability(video);
          boolean _not = (!_isGoodVideoProbability);
          if (_not) {
            return false;
          }
          int _probabities = probabities;
          int _probability = video.getProbability();
          probabities = (_probabities + _probability);
        }
      }
      if (((probabities < 0) || (probabities > 100))) {
        isGood = false;
      }
      _xblockexpression = isGood;
    }
    return _xblockexpression;
  }
  
  /**
   * Checks the validity of probabilities in a videoGen specification
   * for alternatives media the sum of probabilities must less than or equal to 100%
   * for optional media the probability must less or equal to 1 or 100%
   * 
   * @param videoGen the VideoGeneratorModel to check
   * @return a boolean value, which is true if all medias's probabilities are valid
   */
  public static boolean isGoodMediasProbabilities(final VideoGeneratorModel videoGen) {
    boolean _xblockexpression = false;
    {
      boolean isGoodMediasProbabilities = true;
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        {
          if ((media instanceof AlternativesMedia)) {
            ArrayList<VideoDescription> list = new ArrayList<VideoDescription>();
            EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
            for (final MediaDescription m : _medias_1) {
              if ((m instanceof VideoDescription)) {
                list.add(((VideoDescription)m));
              }
            }
            boolean _isGoodAlternativesVideosProbabilties = VideoGenChekerHelper.isGoodAlternativesVideosProbabilties(list);
            boolean _not = (!_isGoodAlternativesVideosProbabilties);
            if (_not) {
              return false;
            }
          }
          if ((media instanceof OptionalMedia)) {
            if ((media instanceof VideoDescription)) {
              boolean _isGoodOptionalVideoProbality = VideoGenChekerHelper.isGoodOptionalVideoProbality(((VideoDescription)media));
              boolean _not_1 = (!_isGoodOptionalVideoProbality);
              if (_not_1) {
                return false;
              }
            }
          }
        }
      }
      _xblockexpression = isGoodMediasProbabilities;
    }
    return _xblockexpression;
  }
  
  /**
   * Checks if the videoGen specification is valid :
   * All probabilities must be coherent :
   * a optional media's probability can't over 1 or 100%
   * the sum of alternatives media's probabilities must less than or equal 100%
   * All medias's identifier must be unique
   * The media's files (images or videos) specified must existed
   * @param videoGen the videoGenerator specification to check
   * @retun a boolean value which is true if the specification is valid, or else otherwise
   */
  public static boolean isGoodVideoGenSpecification(final VideoGeneratorModel videoGen) {
    boolean _xblockexpression = false;
    {
      boolean isGood = true;
      boolean _isNotEmptyVideoGen = VideoGenChekerHelper.isNotEmptyVideoGen(videoGen);
      if (_isNotEmptyVideoGen) {
        return false;
      }
      boolean _isGoodMediasProbabilities = VideoGenChekerHelper.isGoodMediasProbabilities(videoGen);
      boolean _not = (!_isGoodMediasProbabilities);
      if (_not) {
        return false;
      }
      boolean _isAllMediasIdIsUnique = VideoGenChekerHelper.isAllMediasIdIsUnique(videoGen);
      boolean _not_1 = (!_isAllMediasIdIsUnique);
      if (_not_1) {
        return false;
      }
      boolean _isAllMediasFilesExist = VideoGenChekerHelper.isAllMediasFilesExist(videoGen);
      boolean _not_2 = (!_isAllMediasFilesExist);
      if (_not_2) {
        return false;
      }
      _xblockexpression = isGood;
    }
    return _xblockexpression;
  }
  
  /**
   * Checks if a VideoDescription has a valid probability
   * 
   * @param video the videoDescription to check
   * @return the boolean value, which is true if the specification is valid, or else otherwise
   */
  public static boolean isGoodVideoProbability(final VideoDescription video) {
    boolean _xblockexpression = false;
    {
      boolean isGood = true;
      if (((video.getProbability() < 0) || (video.getProbability() > 100))) {
        isGood = false;
      }
      _xblockexpression = isGood;
    }
    return _xblockexpression;
  }
  
  /**
   * Checks the uniqueness of medias's id in a VideoGen specification.
   * 
   * @param videoGen the videoGenerator specification to check
   * @return a boolean value, which is true if all medias's id are unique , or false otherwise
   */
  public static boolean isAllMediasIdIsUnique(final VideoGeneratorModel videoGen) {
    boolean _xblockexpression = false;
    {
      boolean isAllIdUnique = true;
      ArrayList<String> idList = new ArrayList<String>();
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        {
          if ((media instanceof AlternativesMedia)) {
            idList.add(((AlternativesMedia)media).getId().toString());
            EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
            for (final MediaDescription m : _medias_1) {
              {
                if ((m instanceof VideoDescription)) {
                  idList.add(((VideoDescription)m).getVideoid().toString());
                }
                if ((m instanceof ImageDescription)) {
                  idList.add(((ImageDescription)m).getImageid().toString());
                }
              }
            }
          }
          if ((media instanceof MandatoryMedia)) {
            MediaDescription _description = ((MandatoryMedia)media).getDescription();
            if ((_description instanceof VideoDescription)) {
              MediaDescription _description_1 = ((MandatoryMedia)media).getDescription();
              idList.add(((VideoDescription) _description_1).getVideoid().toString());
            }
            MediaDescription _description_2 = ((MandatoryMedia)media).getDescription();
            if ((_description_2 instanceof ImageDescription)) {
              MediaDescription _description_3 = ((MandatoryMedia)media).getDescription();
              idList.add(((ImageDescription) _description_3).getImageid().toString());
            }
          }
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_4 = ((OptionalMedia)media).getDescription();
            if ((_description_4 instanceof VideoDescription)) {
              MediaDescription _description_5 = ((OptionalMedia)media).getDescription();
              idList.add(((VideoDescription) _description_5).getVideoid().toString());
            }
            MediaDescription _description_6 = ((OptionalMedia)media).getDescription();
            if ((_description_6 instanceof ImageDescription)) {
              MediaDescription _description_7 = ((OptionalMedia)media).getDescription();
              idList.add(((ImageDescription) _description_7).getImageid().toString());
            }
          }
        }
      }
      final HashSet<String> set = CollectionLiterals.<String>newHashSet();
      set.addAll(idList);
      int _size = set.size();
      int _size_1 = idList.size();
      boolean _lessThan = (_size < _size_1);
      if (_lessThan) {
        isAllIdUnique = false;
      }
      _xblockexpression = isAllIdUnique;
    }
    return _xblockexpression;
  }
  
  /**
   * Checks the if videoGen medias's files existed
   * 
   * @param videoGen the videoGen specification to check
   * @return a boolean value which is true if all medias's file exist, or else otherwise
   */
  public static boolean isAllMediasFilesExist(final VideoGeneratorModel videoGen) {
    boolean _xblockexpression = false;
    {
      boolean isallMediaFilesExist = true;
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        {
          if ((media instanceof AlternativesMedia)) {
            EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
            for (final MediaDescription m : _medias_1) {
              {
                if ((m instanceof VideoDescription)) {
                  String _location = ((VideoDescription)m).getLocation();
                  boolean _exists = new File(_location).exists();
                  boolean _not = (!_exists);
                  if (_not) {
                    return false;
                  }
                }
                if ((m instanceof ImageDescription)) {
                  String _location_1 = ((ImageDescription)m).getLocation();
                  boolean _exists_1 = new File(_location_1).exists();
                  boolean _not_1 = (!_exists_1);
                  if (_not_1) {
                    return false;
                  }
                }
              }
            }
          }
          if ((media instanceof MandatoryMedia)) {
            if ((media instanceof VideoDescription)) {
              String _location = ((VideoDescription)media).getLocation();
              boolean _exists = new File(_location).exists();
              boolean _not = (!_exists);
              if (_not) {
                return false;
              }
            }
            if ((media instanceof ImageDescription)) {
              String _location_1 = ((ImageDescription)media).getLocation();
              boolean _exists_1 = new File(_location_1).exists();
              boolean _not_1 = (!_exists_1);
              if (_not_1) {
                return false;
              }
            }
          }
          if ((media instanceof OptionalMedia)) {
            if ((media instanceof VideoDescription)) {
              String _location_2 = ((VideoDescription)media).getLocation();
              boolean _exists_2 = new File(_location_2).exists();
              boolean _not_2 = (!_exists_2);
              if (_not_2) {
                return false;
              }
            }
            if ((media instanceof ImageDescription)) {
              String _location_3 = ((ImageDescription)media).getLocation();
              boolean _exists_3 = new File(_location_3).exists();
              boolean _not_3 = (!_exists_3);
              if (_not_3) {
                return false;
              }
            }
          }
        }
      }
      _xblockexpression = isallMediaFilesExist;
    }
    return _xblockexpression;
  }
  
  /**
   * Checks if a videoGen specification is not empty
   * 
   * @param videoGen the videoGen specification to check
   * @return a boolean value which is true if is not empty, or else otherwise
   */
  public static boolean isNotEmptyVideoGen(final VideoGeneratorModel videoGen) {
    return videoGen.getMedias().isEmpty();
  }
}
