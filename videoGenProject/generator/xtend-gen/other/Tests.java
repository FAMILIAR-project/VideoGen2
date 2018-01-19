package other;

import java.io.File;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import other.VideoGenHelper;
import transformation.Etude;
import transformation.Html;
import transformation.LongestVariant;
import transformation.PlayList;
import transformation.VideoToGif;
import util.Filters;
import util.Utils;

@SuppressWarnings("all")
public class Tests {
  private final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("variante.videogen"));
  
  @Before
  public void befor() {
    InputOutput.<String>println("Ne pas oublier de nettoyer les dossiers avant de lancer de nouvelles generations de videos");
  }
  
  @Test
  public void testPlayListGENERATOR() {
    Utils.cleanTestFiles();
    final String variant = PlayList.generate(this.videoGen, "");
    Utils.toFile(variant, "textFiles/variante.txt");
    Utils.concat("textFiles/variante", "generatedVideos/variante");
    Assert.assertTrue(Utils.isFileExist("generatedVideos/variante.mp4"));
  }
  
  @Test
  public void testLongestFfmpeg() {
    final String logestVariant = LongestVariant.toLongestPlayList(this.videoGen);
    Utils.toFile(logestVariant, "logestVariante.txt");
    Assert.assertTrue(Utils.isFileExist("logestVariante.txt"));
  }
  
  @Test
  public void testHtmlPage() {
    final String htmlPage = Html.toHtml(this.videoGen);
    Utils.toFile(htmlPage, "textFiles/vignette.html");
    Assert.assertTrue(Utils.isFileExist("textFiles/vignette.html"));
  }
  
  @Test
  public void testVariantSizes() {
    Etude.getAllVariants(this.videoGen);
    Utils.isFileExist("textFiles/tab.csv");
  }
  
  @Test
  public void testGifExporter() {
    final VideoToGif toGif = new VideoToGif();
    toGif.videoToGif("videos/1.mp4", "gifs/gif_exported.gif", 200, 200);
    Utils.isFileExist("gifs/gif_exported.gif");
  }
  
  @Test
  public void testFilterBlackWhite() {
    Filters.blackWhiteFilter("videos/1.mp4", false);
    Assert.assertTrue(Utils.isFileExist("generatedVideos/1_bw.mp4"));
  }
  
  @Test
  public void testFilterNegate() {
    Filters.negateFilter("videos/1.mp4", false);
    Assert.assertTrue(Utils.isFileExist("generatedVideos/1_neg.mp4"));
  }
  
  @Test
  public void testFilterFlip() {
    Filters.flipFilter("videos/1.mp4", "h", false);
    Assert.assertTrue(Utils.isFileExist("generatedVideos/1_hflip.mp4"));
  }
  
  @Test
  public void testClean() {
    Utils.cleanTestFiles();
    File generatedVideos = new File("generatedVideos/");
    File images = new File("images/");
    File gifs = new File("gifs/");
    Assert.assertTrue(((List<String>)Conversions.doWrapArray(generatedVideos.list())).isEmpty());
    Assert.assertTrue(((List<String>)Conversions.doWrapArray(images.list())).isEmpty());
    Assert.assertTrue(((List<String>)Conversions.doWrapArray(gifs.list())).isEmpty());
  }
}
