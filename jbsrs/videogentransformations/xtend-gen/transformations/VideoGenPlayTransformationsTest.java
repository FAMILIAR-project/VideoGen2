package transformations;

import configs.VideoGenConfigs;
import helpers.VideoGenHelper;
import java.util.HashMap;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.InputOutput;
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
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("samples/example3.videogen"));
    VideoGenConfigs.setOutPutFoulder("output");
    VideoGenConfigs.setGifResolutions(190, 60);
    final HashMap<String, List<String>> thumbs = VideoGenPlayTransformations.makeThumbnails(videoGen);
    InputOutput.<Integer>println(Integer.valueOf(thumbs.size()));
    int _size = thumbs.get("Alternatives").size();
    String _plus = ("Alt size" + Integer.valueOf(_size));
    InputOutput.<String>println(_plus);
    int _size_1 = thumbs.get("Mandatory").size();
    String _plus_1 = ("Man size" + Integer.valueOf(_size_1));
    InputOutput.<String>println(_plus_1);
    int _size_2 = thumbs.get("Optional").size();
    String _plus_2 = ("Op size" + Integer.valueOf(_size_2));
    InputOutput.<String>println(_plus_2);
  }
  
  @Test
  public void videoMaxDuration() {
  }
  
  @Test
  public void videoToGif() {
  }
}
