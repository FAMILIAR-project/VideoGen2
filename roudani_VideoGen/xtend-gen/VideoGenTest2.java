import org.eclipse.emf.common.util.URI;
import org.junit.Assert;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import transformation.Ffmpeg;
import transformation.Html;
import transformation.LongestVariant;
import transformation.VariantSizes;
import util.Utils;

@SuppressWarnings("all")
public class VideoGenTest2 {
  private final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"));
  
  @Test
  public void testVariantFfmpeg() {
    final String variant = Ffmpeg.toFfmpeg(this.videoGen);
    Assert.assertTrue(Utils.validator(this.videoGen));
  }
  
  @Test
  public void testLongestFfmpeg() {
    final String logestVariant = LongestVariant.toLongestVar(this.videoGen);
    Utils.toFile(logestVariant, "logestVariant.txt");
  }
  
  @Test
  public void testHtmlPage() {
    final String htmlPage = Html.toHtml(this.videoGen);
    Utils.toFile(htmlPage, "vignette.html");
  }
  
  @Test
  public void testVariantSizes() {
    final String variantSizes = VariantSizes.getAllVariants(this.videoGen);
  }
  
  @Test
  public void testVideoToGif() {
    Utils.videoToGif("output.mp4", "gifFromVideo", 50, 50);
  }
}
