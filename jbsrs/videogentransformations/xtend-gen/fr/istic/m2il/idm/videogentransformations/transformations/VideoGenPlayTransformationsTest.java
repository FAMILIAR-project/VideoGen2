package fr.istic.m2il.idm.videogentransformations.transformations;

import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.CSVHelper;
import fr.istic.m2il.idm.videogentransformations.helpers.FFMPEGHelper;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenChekerHelper;
import fr.istic.m2il.idm.videogentransformations.helpers.VideoGenHelper;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenAnalysisTransformations;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformations;
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils;
import fr.istic.m2il.idm.videogentransformations.utils.VideoGenUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class VideoGenPlayTransformationsTest {
  @Before
  public void setUp() {
    String _property = System.getProperty("videogenspecification");
    boolean _tripleEquals = (_property == null);
    if (_tripleEquals) {
      System.setProperty("videogenspecification", "samples/sample1.videogen");
    }
  }
  
  @Test
  public void checksValidVideoGenSpecification() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    String _property = System.getProperty("output_folder");
    boolean _tripleEquals = (_property == null);
    if (_tripleEquals) {
      VideoGenConfigs.setOutPutFoulder("output");
      VideoGenConfigs.initSubOutPutFolders();
    }
    InputOutput.<Boolean>println(Boolean.valueOf(VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen)));
  }
  
  @Test
  public void checksValidFiles() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    String _property = System.getProperty("output_folder");
    boolean _tripleEquals = (_property == null);
    if (_tripleEquals) {
      VideoGenConfigs.setOutPutFoulder("output");
      VideoGenConfigs.initSubOutPutFolders();
    }
    InputOutput.<Boolean>println(Boolean.valueOf(VideoGenChekerHelper.isAllMediasFilesExist(videoGen)));
  }
  
  @Test
  public void checksValidIds() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    String _property = System.getProperty("output_folder");
    boolean _tripleEquals = (_property == null);
    if (_tripleEquals) {
      VideoGenConfigs.setOutPutFoulder("output");
      VideoGenConfigs.initSubOutPutFolders();
    }
    InputOutput.<Boolean>println(Boolean.valueOf(VideoGenChekerHelper.isAllMediasIdIsUnique(videoGen)));
  }
  
  @Test
  public void generateRandomPlayList() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      VideoGenPlayTransformations.generateRandomPlayList(videoGen);
    }
  }
  
  @Test
  public void makeThumbnails() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      final List<String> thumbs = VideoGenPlayTransformations.makeThumbnails(videoGen);
      InputOutput.<Integer>println(Integer.valueOf(thumbs.size()));
    }
  }
  
  @Test
  public void makeWebPage() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      VideoGenConfigs.setGifResolutions(190, 60);
      final List<String> htmls = VideoGenPlayTransformations.makeWebPage(videoGen);
      for (final String html : htmls) {
        InputOutput.<String>println(html);
      }
    }
  }
  
  @Test
  public void videoMaxDuration() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      VideoGenConfigs.setGifResolutions(190, 60);
      InputOutput.<Integer>println(Integer.valueOf(VideoGenAnalysisTransformations.getMaxDuration(videoGen)));
    }
  }
  
  @Test
  public void videoToGif() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      String _property_1 = System.getProperty("gif_width");
      boolean _tripleEquals_1 = (_property_1 == null);
      if (_tripleEquals_1) {
        VideoGenConfigs.setGifResolutions(190, 60);
      } else {
        VideoGenConfigs.setGifResolutions(Integer.parseInt(System.getProperty("gif_width")), Integer.parseInt(System.getProperty("gif_heigth")));
      }
      VideoGenPlayTransformations.videoGensToGifs(videoGen);
    }
  }
  
  @Test
  public void checksVariantsNumber() throws Exception {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      VideoGenConfigs.setGifResolutions(190, 60);
      int _variantNumber = VideoGenUtils.getVariantNumber(videoGen);
      int _size = VideoGenUtils.generatePlaylists(videoGen).size();
      boolean _equals = (_variantNumber == _size);
      Assert.assertTrue(
        "Le nombre de variants trouvées", _equals);
    }
  }
  
  @Test
  public void concatVideos() {
    String _property = System.getProperty("concat_files");
    boolean _tripleNotEquals = (_property != null);
    if (_tripleNotEquals) {
      String _property_1 = System.getProperty("output_folder");
      boolean _tripleEquals = (_property_1 == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
      }
      String[] files = System.getProperty("concat_files").split(" ");
      final ArrayList<int[]> resolutions = CollectionLiterals.<int[]>newArrayList();
      for (final String file : files) {
        if (((file != null) && 
          (((((!file.replace(".", "@").split("@")[1].equals("jpg")) && 
            (!file.replace(".", "@").split("@")[1].equals("png"))) && 
            (!file.replace(".", "@").split("@")[1].equals("gif"))) && 
            (!file.replace(".", "@").split("@")[1].equals("bpm"))) && 
            (!file.replace(".", "@").split("@")[1].equals("tiff"))))) {
          resolutions.add(FFMPEGHelper.getVideoResolution(file));
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
      int index = 0;
      for (final String f : files) {
        {
          files[index] = FFMPEGHelper.homogenizeMediaResolution(f, 
            FFMPEGHelper.getVideoResolution(f)[0], 
            FFMPEGHelper.getVideoResolution(f)[1], maxOutputWidth, maxOutputHeight);
          index++;
        }
      }
      final String[] _converted_files = (String[])files;
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus = (_outPutFoulder + "/concat_videos.");
      String _property_2 = System.getProperty("concat_format");
      String _plus_1 = (_plus + _property_2);
      FFMPEGHelper.concatVideos(((List<String>)Conversions.doWrapArray(_converted_files)), CommonUtils.getOutPutFileName(_plus_1));
    }
  }
  
  @Test
  public void checksCSVLinesNumber() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      boolean csv_type = false;
      boolean isDuration = false;
      String _property_1 = System.getProperty("csv_duration");
      boolean _tripleEquals_1 = (_property_1 == null);
      if (_tripleEquals_1) {
        isDuration = false;
      } else {
        boolean _equals = System.getProperty("csv_duration").equals("true");
        if (_equals) {
          isDuration = true;
        } else {
          isDuration = false;
        }
      }
      String _property_2 = System.getProperty("csv_type");
      boolean _tripleEquals_2 = (_property_2 == null);
      if (_tripleEquals_2) {
        csv_type = false;
      } else {
        boolean _equals_1 = System.getProperty("csv_type").equals("true");
        if (_equals_1) {
          csv_type = true;
        } else {
          csv_type = false;
        }
      }
      String _property_3 = System.getProperty("gif_width");
      boolean _tripleEquals_3 = (_property_3 == null);
      if (_tripleEquals_3) {
        VideoGenConfigs.setGifResolutions(190, 60);
      } else {
        VideoGenConfigs.setGifResolutions(Integer.parseInt(System.getProperty("gif_width")), Integer.parseInt(System.getProperty("gif_heigth")));
      }
      String _property_4 = System.getProperty("videogenspecification");
      String _plus = ("Le fichier CSV produit pour la spécification videogen du fichier " + _property_4);
      String _plus_1 = (_plus + " contient autant de lignes que de nombre de variantes possibles (+ 1).");
      int _variantNumber = VideoGenUtils.getVariantNumber(videoGen);
      int _plus_2 = (_variantNumber + 1);
      int _size = CSVHelper.create(videoGen, isDuration, csv_type).size();
      boolean _equals_2 = (_plus_2 == _size);
      Assert.assertTrue(_plus_1, _equals_2);
    }
  }
  
  @Test
  public void cheksThumbsNumber() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      VideoGenConfigs.setGifResolutions(190, 60);
      String _property_1 = System.getProperty("videogenspecification");
      String _plus = ("Le nombre de vignettes produites par la spécification videogen du fichier " + _property_1);
      String _plus_1 = (_plus + " est bien égal au nombre de medias");
      int _videoGenMediasNumber = VideoGenUtils.getVideoGenMediasNumber(videoGen);
      int _size = VideoGenPlayTransformations.makeThumbnails(videoGen).size();
      boolean _equals = (_videoGenMediasNumber == _size);
      Assert.assertTrue(_plus_1, _equals);
    }
  }
  
  @Test
  public void checksWebPageThumbs() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(System.getProperty("videogenspecification")));
    boolean _isGoodVideoGenSpecification = VideoGenChekerHelper.isGoodVideoGenSpecification(videoGen);
    if (_isGoodVideoGenSpecification) {
      String _property = System.getProperty("output_folder");
      boolean _tripleEquals = (_property == null);
      if (_tripleEquals) {
        VideoGenConfigs.setOutPutFoulder("output");
        VideoGenConfigs.initSubOutPutFolders();
      }
      VideoGenConfigs.setGifResolutions(190, 60);
      String _property_1 = System.getProperty("videogenspecification");
      String _plus = ("Le nombre de vignettes produites par la spécification videogen du fichier " + _property_1);
      String _plus_1 = (_plus + " est bien égal au nombre de medias");
      int _videoGenMediasNumber = VideoGenUtils.getVideoGenMediasNumber(videoGen);
      int _size = VideoGenPlayTransformations.makeThumbnails(videoGen).size();
      boolean _equals = (_videoGenMediasNumber == _size);
      Assert.assertTrue(_plus_1, _equals);
    }
  }
}
