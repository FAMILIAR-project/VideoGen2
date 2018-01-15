import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
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
  private CSVFileWriter csvfile;
  
  private int poss = 0;
  
  private int alt = 0;
  
  private int i;
  
  private int j;
  
  private final Random random = new Random();
  
  private ArrayList<VideoDescription> playlist;
  
  private ArrayList<ArrayList<VideoDescription>> playlists;
  
  private String playmsg = "";
  
  private int totalvideos = 0;
  
  private int opt = 0;
  
  private PrintWriter writer = new Function0<PrintWriter>() {
    public PrintWriter apply() {
      try {
        PrintWriter _printWriter = new PrintWriter("videos/playlist.txt", "UTF-8");
        return _printWriter;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
  }.apply();
  
  private ArrayList<VideoDescription> longplaylist = new ArrayList<VideoDescription>();
  
  private final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"));
  
  private long longestAltDuration = 0;
  
  private int longestPosition;
  
  private ArrayList<VideoDescription> videos = new ArrayList<VideoDescription>();
  
  @Test
  public void testLoadModel() {
    Assert.assertNotNull(this.videoGen);
    InputOutput.<String>println(this.videoGen.getInformation().getAuthorName());
    this.totalvideos = this.getPossility();
    InputOutput.<String>println(("total :" + Integer.valueOf(this.totalvideos)));
    ArrayList<ArrayList<VideoDescription>> _arrayList = new ArrayList<ArrayList<VideoDescription>>();
    this.playlists = _arrayList;
    for (this.i = 0; (this.i < this.totalvideos); this.i++) {
      ArrayList<VideoDescription> _arrayList_1 = new ArrayList<VideoDescription>();
      this.playlists.add(_arrayList_1);
    }
    final Consumer<Media> _function = (Media m) -> {
      if ((m instanceof MandatoryMedia)) {
        MediaDescription _description = ((MandatoryMedia)m).getDescription();
        this.videos.add(((VideoDescription) _description));
        for (this.i = 0; (this.i < this.totalvideos); this.i++) {
          MediaDescription _description_1 = ((MandatoryMedia)m).getDescription();
          this.playlists.get(this.i).add(((VideoDescription) _description_1));
        }
        String _location = ((MandatoryMedia)m).getDescription().getLocation();
        String _plus = (this.playmsg + _location);
        String _plus_1 = (_plus + "\n");
        this.playmsg = _plus_1;
        MediaDescription _description_1 = ((MandatoryMedia)m).getDescription();
        this.longplaylist.add(((VideoDescription) _description_1));
      }
      if ((m instanceof OptionalMedia)) {
        MediaDescription _description_2 = ((OptionalMedia)m).getDescription();
        this.videos.add(((VideoDescription) _description_2));
        for (this.i = 0; (this.i < (this.totalvideos / 2)); this.i++) {
          MediaDescription _description_3 = ((OptionalMedia)m).getDescription();
          this.playlists.get(this.i).add(((VideoDescription) _description_3));
        }
        MediaDescription _description_3 = ((OptionalMedia)m).getDescription();
        this.longplaylist.add(((VideoDescription) _description_3));
        String _location_1 = ((OptionalMedia)m).getDescription().getLocation();
        String _plus_2 = (this.playmsg + _location_1);
        String _plus_3 = (_plus_2 + "\n");
        this.playmsg = _plus_3;
      }
      if ((m instanceof AlternativesMedia)) {
        this.alt = ((AlternativesMedia)m).getMedias().size();
        for (this.j = 0; (this.j < ((AlternativesMedia)m).getMedias().size()); this.j++) {
          {
            MediaDescription _get = ((AlternativesMedia)m).getMedias().get(this.j);
            this.videos.add(((VideoDescription) _get));
            for (this.i = this.poss; (this.i < this.totalvideos); this.i = (this.i + this.alt)) {
              {
                MediaDescription _get_1 = ((AlternativesMedia)m).getMedias().get(this.j);
                this.playlists.get(this.i).add(((VideoDescription) _get_1));
                String _location_2 = ((AlternativesMedia)m).getMedias().get(this.j).getLocation();
                String _plus_4 = (this.playmsg + _location_2);
                String _plus_5 = (_plus_4 + "\n");
                this.playmsg = _plus_5;
              }
            }
            long _longValue = Float.valueOf(Util.getFileDuration(((AlternativesMedia)m).getMedias().get(this.j).getLocation())).longValue();
            boolean _greaterThan = (_longValue > this.longestAltDuration);
            if (_greaterThan) {
              this.longestAltDuration = Float.valueOf(Util.getFileDuration(((AlternativesMedia)m).getMedias().get(this.j).getLocation())).longValue();
              this.longestPosition = this.j;
            }
            float _fileDuration = Util.getFileDuration(((AlternativesMedia)m).getMedias().get(this.j).getLocation());
            String _plus_4 = ("*****************" + Float.valueOf(_fileDuration));
            InputOutput.<String>println(_plus_4);
            this.poss++;
          }
        }
        MediaDescription _get = ((AlternativesMedia)m).getMedias().get(this.longestPosition);
        this.longplaylist.add(((VideoDescription) _get));
        this.j = 0;
        this.poss = 0;
      }
    };
    this.videoGen.getMedias().forEach(_function);
    InputOutput.<String>println("Debut*************************\n");
    InputOutput.<String>println("playlists   :\n");
    final Consumer<ArrayList<VideoDescription>> _function_1 = (ArrayList<VideoDescription> list) -> {
      InputOutput.<String>println("element:\n");
      final Consumer<VideoDescription> _function_2 = (VideoDescription element) -> {
        String _videoid = element.getVideoid();
        String _plus = (_videoid + ";");
        InputOutput.<String>print(_plus);
      };
      list.forEach(_function_2);
      InputOutput.<String>println("\n");
    };
    this.playlists.forEach(_function_1);
    InputOutput.<String>println("\n");
    InputOutput.<String>println("Fin*************************\n");
    this.playlist = this.playlists.get(this.random.nextInt(this.totalvideos));
    this.createfffFile();
  }
  
  public int getPossility() {
    final Consumer<Media> _function = (Media m) -> {
      if ((m instanceof OptionalMedia)) {
        this.opt = (this.opt + 2);
      }
      if ((m instanceof AlternativesMedia)) {
        if ((this.alt == 0)) {
          int _size = ((AlternativesMedia)m).getMedias().size();
          int _plus = (this.alt + _size);
          this.alt = _plus;
        } else {
          int _size_1 = ((AlternativesMedia)m).getMedias().size();
          int _multiply = (this.alt * _size_1);
          this.alt = _multiply;
        }
      }
    };
    this.videoGen.getMedias().forEach(_function);
    if ((this.opt == 0)) {
      this.opt++;
    }
    if ((this.alt == 0)) {
      this.alt++;
    }
    return (this.opt * this.alt);
  }
  
  public Process createfffFile() {
    try {
      Process _xblockexpression = null;
      {
        this.writer.println("#bref playlist");
        final Consumer<VideoDescription> _function = (VideoDescription video) -> {
          String _location = video.getLocation();
          String _plus = ("file \'" + _location);
          String _plus_1 = (_plus + "\'");
          this.writer.println(_plus_1);
        };
        this.playlist.forEach(_function);
        this.writer.close();
        _xblockexpression = Runtime.getRuntime().exec("ffmpeg -f concat -safe 0 -i ./playlist.txt -c copy ./out.mp4");
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public int executeVLC() {
    try {
      int _xblockexpression = (int) 0;
      {
        Process p = Runtime.getRuntime().exec("vlc  videos/bref.mp4");
        _xblockexpression = p.waitFor();
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public int longest() {
    try {
      int _xblockexpression = (int) 0;
      {
        Process p = Runtime.getRuntime().exec("vlc  videos/long.mp4");
        _xblockexpression = p.waitFor();
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void createCSV() {
    CSVFileWriter _cSVFileWriter = new CSVFileWriter();
    this.csvfile = _cSVFileWriter;
    this.csvfile.writeCsvFile(this.videos, this.playlists);
  }
}
