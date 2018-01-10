package transformations;

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
  }
  
  @Test
  public void makeThumbnails() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"));
    VideoGenPlayTransformations.makeThumbnails(videoGen);
  }
}
