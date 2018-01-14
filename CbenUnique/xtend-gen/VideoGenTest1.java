import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class VideoGenTest1 {
  private final ArrayList<VideoDescription> playlist = CollectionLiterals.<VideoDescription>newArrayList();
  
  private final ArrayList<Object> descriptions = CollectionLiterals.<Object>newArrayList();
  
  private String Playlist;
  
  private List<String> msg = new ArrayList<String>();
  
  private int alt = 0;
  
  private int opt = 0;
  
  private int poss = 0;
  
  private final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"));
  
  private int i;
  
  private String saut;
  
  @Test
  public void testLoadModel() {
    Assert.assertNotNull(this.videoGen);
    InputOutput.<String>println(this.videoGen.getInformation().getAuthorName());
    final Consumer<Media> _function = (Media m) -> {
      if ((m instanceof MandatoryMedia)) {
        MediaDescription _description = ((MandatoryMedia)m).getDescription();
        if ((_description instanceof VideoDescription)) {
        }
      }
      if ((m instanceof OptionalMedia)) {
      }
      if ((m instanceof AlternativesMedia)) {
      }
    };
    this.videoGen.getMedias().forEach(_function);
    this.getSequences();
  }
  
  public List<String> getSequences() {
    List<String> _xblockexpression = null;
    {
      this.saut = "\n";
      final Consumer<Media> _function = (Media m) -> {
        if ((m instanceof MandatoryMedia)) {
          for (this.i = 0; (this.i < 3); this.i++) {
            {
              this.msg.add(((MandatoryMedia)m).getDescription().getLocation());
              this.Playlist = "2";
              InputOutput.<String>println((this.Playlist + "xxxxxx"));
            }
          }
        }
        if ((m instanceof OptionalMedia)) {
        }
        if ((m instanceof AlternativesMedia)) {
        }
      };
      this.videoGen.getMedias().forEach(_function);
      _xblockexpression = InputOutput.<List<String>>println(this.msg);
    }
    return _xblockexpression;
  }
  
  public void copmpilePlaylist(final ArrayList<MediaDescription> l) {
    for (final Object description : this.descriptions) {
    }
  }
  
  public int getPossiility() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"));
    Assert.assertNotNull(videoGen);
    InputOutput.<String>println(videoGen.getInformation().getAuthorName());
    final Consumer<Media> _function = (Media m) -> {
      if ((m instanceof OptionalMedia)) {
        this.opt = (this.opt + 2);
      }
      if ((m instanceof AlternativesMedia)) {
        int _size = ((AlternativesMedia)m).getMedias().size();
        int _plus = (this.alt + _size);
        this.alt = _plus;
      }
    };
    videoGen.getMedias().forEach(_function);
    if ((this.opt == 0)) {
      this.opt++;
    }
    if ((this.alt == 0)) {
      this.alt++;
    }
    this.poss = (this.opt * this.alt);
    return this.poss;
  }
  
  public Object compileffFile() {
    return null;
  }
  
  public int executeVLC(final VideoDescription d) {
    try {
      int _xblockexpression = (int) 0;
      {
        Runtime _runtime = Runtime.getRuntime();
        String _location = d.getLocation();
        String _plus = ("vlc " + _location);
        Process p = _runtime.exec(_plus);
        _xblockexpression = p.waitFor();
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
