import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class VideoGenTest1 {
  private CSVFileWriter csvfile = new CSVFileWriter();
  
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
  
  private PrintWriter writer;
  
  private ArrayList<VideoDescription> longplaylist = new ArrayList<VideoDescription>();
  
  private final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"));
  
  private long longestAltDuration = 0;
  
  private int longestPosition;
  
  private ArrayList<VideoDescription> videos = new ArrayList<VideoDescription>();
  
  private String videoname;
  
  /**
   * The launcher app
   */
  @Test
  public void testLoadModel() {
    Assert.assertNotNull(this.videoGen);
    InputOutput.<String>println(this.videoGen.getInformation().getAuthorName());
    this.totalvideos = this.getPossility();
    InputOutput.<String>println(("total playlists :" + Integer.valueOf(this.totalvideos)));
    ArrayList<ArrayList<VideoDescription>> _arrayList = new ArrayList<ArrayList<VideoDescription>>();
    this.playlists = _arrayList;
    for (this.i = 0; (this.i < this.totalvideos); this.i++) {
      ArrayList<VideoDescription> _arrayList_1 = new ArrayList<VideoDescription>();
      this.playlists.add(_arrayList_1);
    }
    final Consumer<Media> _function = (Media m) -> {
      if ((m instanceof MandatoryMedia)) {
        MediaDescription _description = ((MandatoryMedia)m).getDescription();
        if ((_description instanceof VideoDescription)) {
          MediaDescription _description_1 = ((MandatoryMedia)m).getDescription();
          this.videos.add(((VideoDescription) _description_1));
          for (this.i = 0; (this.i < this.totalvideos); this.i++) {
            MediaDescription _description_2 = ((MandatoryMedia)m).getDescription();
            this.playlists.get(this.i).add(((VideoDescription) _description_2));
          }
          String _location = ((MandatoryMedia)m).getDescription().getLocation();
          String _plus = (this.playmsg + _location);
          String _plus_1 = (_plus + "\n");
          this.playmsg = _plus_1;
          MediaDescription _description_2 = ((MandatoryMedia)m).getDescription();
          this.longplaylist.add(((VideoDescription) _description_2));
        } else {
          MediaDescription _description_3 = ((MandatoryMedia)m).getDescription();
          if ((_description_3 instanceof ImageDescription)) {
            MediaDescription _description_4 = ((MandatoryMedia)m).getDescription();
            String _top = ((ImageDescription) _description_4).getTop();
            String _plus_2 = ("image found :" + _top);
            String _plus_3 = (_plus_2 + "AND ");
            MediaDescription _description_5 = ((MandatoryMedia)m).getDescription();
            String _bottom = ((ImageDescription) _description_5).getBottom();
            String _plus_4 = (_plus_3 + _bottom);
            InputOutput.<String>println(_plus_4);
          }
        }
      }
      if ((m instanceof OptionalMedia)) {
        MediaDescription _description_6 = ((OptionalMedia)m).getDescription();
        if ((_description_6 instanceof VideoDescription)) {
          MediaDescription _description_7 = ((OptionalMedia)m).getDescription();
          this.videos.add(((VideoDescription) _description_7));
          for (this.i = 0; (this.i < (this.totalvideos / 2)); this.i++) {
            MediaDescription _description_8 = ((OptionalMedia)m).getDescription();
            this.playlists.get(this.i).add(((VideoDescription) _description_8));
          }
          MediaDescription _description_8 = ((OptionalMedia)m).getDescription();
          this.longplaylist.add(((VideoDescription) _description_8));
          String _location_1 = ((OptionalMedia)m).getDescription().getLocation();
          String _plus_5 = (this.playmsg + _location_1);
          String _plus_6 = (_plus_5 + "\n");
          this.playmsg = _plus_6;
        } else {
          MediaDescription _description_9 = ((OptionalMedia)m).getDescription();
          if ((_description_9 instanceof ImageDescription)) {
            MediaDescription _description_10 = ((OptionalMedia)m).getDescription();
            String _plus_7 = ("image found :" + ((ImageDescription) _description_10));
            String _plus_8 = (_plus_7 + "AND ");
            MediaDescription _description_11 = ((OptionalMedia)m).getDescription();
            String _plus_9 = (_plus_8 + ((ImageDescription) _description_11));
            InputOutput.<String>println(_plus_9);
          }
        }
      }
      if ((m instanceof AlternativesMedia)) {
        this.alt = ((AlternativesMedia)m).getMedias().size();
        for (this.j = 0; (this.j < ((AlternativesMedia)m).getMedias().size()); this.j++) {
          MediaDescription _get = ((AlternativesMedia)m).getMedias().get(this.j);
          if ((_get instanceof VideoDescription)) {
            MediaDescription _get_1 = ((AlternativesMedia)m).getMedias().get(this.j);
            this.videos.add(((VideoDescription) _get_1));
            for (this.i = this.poss; (this.i < this.totalvideos); this.i = (this.i + this.alt)) {
              {
                MediaDescription _get_2 = ((AlternativesMedia)m).getMedias().get(this.j);
                this.playlists.get(this.i).add(((VideoDescription) _get_2));
                String _location_2 = ((AlternativesMedia)m).getMedias().get(this.j).getLocation();
                String _plus_10 = (this.playmsg + _location_2);
                String _plus_11 = (_plus_10 + "\n");
                this.playmsg = _plus_11;
              }
            }
            long _longValue = Float.valueOf(Util.getFileDuration(((AlternativesMedia)m).getMedias().get(this.j).getLocation())).longValue();
            boolean _greaterThan = (_longValue > this.longestAltDuration);
            if (_greaterThan) {
              this.longestAltDuration = Float.valueOf(Util.getFileDuration(((AlternativesMedia)m).getMedias().get(this.j).getLocation())).longValue();
              this.longestPosition = this.j;
            }
            this.poss++;
          } else {
            MediaDescription _get_2 = ((AlternativesMedia)m).getMedias().get(this.j);
            if ((_get_2 instanceof ImageDescription)) {
              MediaDescription _get_3 = ((AlternativesMedia)m).getMedias().get(this.j);
              String _top_1 = ((ImageDescription) _get_3).getTop();
              String _plus_10 = ("image found :" + _top_1);
              String _plus_11 = (_plus_10 + "AND ");
              MediaDescription _get_4 = ((AlternativesMedia)m).getMedias().get(this.j);
              String _bottom_1 = ((ImageDescription) _get_4).getBottom();
              String _plus_12 = (_plus_11 + _bottom_1);
              InputOutput.<String>println(_plus_12);
            }
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
    InputOutput.<String>println("Creating files ://////////");
    this.creatingVideoFiles();
    this.createCSV();
    Assert.assertEquals(this.totalvideos, this.playlists.size());
  }
  
  /**
   * Get the number of possible playlists from the videogen file
   */
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
  
  /**
   * Create a video file for each playlist to compare
   */
  public void creatingVideoFiles() {
    final Consumer<ArrayList<VideoDescription>> _function = (ArrayList<VideoDescription> playlist) -> {
      this.createfffFile(playlist);
      this.csvfile.addSize();
    };
    this.playlists.forEach(_function);
  }
  
  /**
   * create a playlist.txt file for ffmpeg and concatenate from this file
   */
  public int createfffFile(final ArrayList<VideoDescription> playlist) {
    try {
      int _xblockexpression = (int) 0;
      {
        PrintWriter _printWriter = new PrintWriter("videos/playlist.txt", "UTF-8");
        this.writer = _printWriter;
        this.writer.println("#bref playlist");
        final Consumer<VideoDescription> _function = (VideoDescription video) -> {
          this.videoname = video.getLocation().replace("videos/", "");
          this.writer.println((("file \'" + this.videoname) + "\'"));
        };
        playlist.forEach(_function);
        this.writer.close();
        Runtime.getRuntime().exec("rm bref.mp4");
        Process p = Runtime.getRuntime().exec("ffmpeg -f concat -safe 0 -i videos/playlist.txt -c copy bref.mp4");
        _xblockexpression = p.waitFor();
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Launch vlc media player with the generated video
   */
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
  
  /**
   * Create a CSV file with the size and lenght information
   */
  public void createCSV() {
    this.csvfile.writeCsvFile(this.videos, this.playlists);
  }
  
  /**
   * generate a GIF for the videos in the videogen file
   */
  public int createGIF(final VideoDescription videodescription) {
    try {
      int _xifexpression = (int) 0;
      String _videoid = videodescription.getVideoid();
      boolean _tripleNotEquals = (_videoid != null);
      if (_tripleNotEquals) {
        int _xblockexpression = (int) 0;
        {
          Runtime _runtime = Runtime.getRuntime();
          String _videoid_1 = videodescription.getVideoid();
          String _plus = ("rm " + _videoid_1);
          String _plus_1 = (_plus + ".gif");
          _runtime.exec(_plus_1);
          String _location = videodescription.getLocation();
          String _plus_2 = ("ffmpeg -i " + _location);
          String _plus_3 = (_plus_2 + "  ");
          String _videoid_2 = videodescription.getVideoid();
          String _plus_4 = (_plus_3 + _videoid_2);
          String _plus_5 = (_plus_4 + ".gif");
          InputOutput.<String>println(_plus_5);
          Runtime _runtime_1 = Runtime.getRuntime();
          String _location_1 = videodescription.getLocation();
          String _plus_6 = ("ffmpeg -i " + _location_1);
          String _plus_7 = (_plus_6 + "  ");
          String _videoid_3 = videodescription.getVideoid();
          String _plus_8 = (_plus_7 + _videoid_3);
          String _plus_9 = (_plus_8 + ".gif");
          Process p = _runtime_1.exec(_plus_9);
          _xblockexpression = p.waitFor();
        }
        _xifexpression = _xblockexpression;
      } else {
        int _xblockexpression_1 = (int) 0;
        {
          Runtime _runtime = Runtime.getRuntime();
          String _location = videodescription.getLocation();
          String _plus = ("ffmpeg -i " + _location);
          String _plus_1 = (_plus + ".gif");
          Process p = _runtime.exec(_plus_1);
          _xblockexpression_1 = p.waitFor();
        }
        _xifexpression = _xblockexpression_1;
      }
      return _xifexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * generate GIFs for all the videos
   */
  public String createAllGIT() {
    String _xblockexpression = null;
    {
      final Consumer<VideoDescription> _function = (VideoDescription video) -> {
        this.createGIF(video);
      };
      this.videos.forEach(_function);
      _xblockexpression = InputOutput.<String>println("Gifs created");
    }
    return _xblockexpression;
  }
}
