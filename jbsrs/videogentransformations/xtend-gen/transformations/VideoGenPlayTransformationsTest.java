package transformations;

import configs.VideoGenConfigs;
import helpers.VideoGenHelper;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import transformations.VideoGenPlayTransformations;

@SuppressWarnings("all")
public class VideoGenPlayTransformationsTest {
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }
  
  @Test
  public void generateRandomPlayList() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("samples/sample9.videogen"));
    VideoGenConfigs.setOutPutFoulder("output");
    VideoGenConfigs.setGifResolutions(190, 60);
    VideoGenPlayTransformations.generateRandomPlayList(videoGen);
  }
  
  @Test
  public void makeThumbnails() {
  }
  
  @Test
  public void makeWebPage() {
  }
  
  @Test
  public void videoMaxDuration() {
  }
  
  @Test
  public void videoToGif() {
  }
  
  @Test
  public void checksVariantsNumber() {
  }
  
  @Test
  public void checksCSVLinesNumber() {
  }
  
  @Test
  public void cheksThumbsNumber() {
  }
  
  @Test
  public void checksWebPageThums() {
  }
}
