import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
  private int poss = 0;
  
  private int alt = 0;
  
  private int i;
  
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
  
  private String command;
  
  private String startcommand;
  
  private Process process;
  
  private BufferedReader stdInput;
  
  private String s;
  
  private BufferedReader stdError;
  
  @Test
  public void testLoadModel() {
    this.startcommand = "ffmpeg -i ";
    this.command = " 2>&1 | grep \"";
    this.command = (this.command + "Duration\"");
    this.command = ((this.command + "| cut -d \' \' -f 4 | sed s/,// | sed \'s@\\") + "..*@@g\' | awk \'{ split($1, A, \"");
    this.command = (this.command + ":\"");
    this.command = (this.command + "); split(A[3], B, \"");
    this.command = (this.command + ".\"");
    this.command = (this.command + "); print 3600*A[1] + 60*A[2] + B[1] }\'");
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
        for (this.i = 0; (this.i < this.totalvideos); this.i++) {
          MediaDescription _description = ((MandatoryMedia)m).getDescription();
          this.playlists.get(this.i).add(((VideoDescription) _description));
        }
        String _location = ((MandatoryMedia)m).getDescription().getLocation();
        String _plus = (this.playmsg + _location);
        String _plus_1 = (_plus + "\n");
        this.playmsg = _plus_1;
        MediaDescription _description = ((MandatoryMedia)m).getDescription();
        this.longplaylist.add(((VideoDescription) _description));
      }
      if ((m instanceof OptionalMedia)) {
        for (this.i = 0; (this.i < (this.totalvideos / 2)); this.i++) {
          MediaDescription _description_1 = ((OptionalMedia)m).getDescription();
          this.playlists.get(this.i).add(((VideoDescription) _description_1));
        }
        MediaDescription _description_1 = ((OptionalMedia)m).getDescription();
        this.longplaylist.add(((VideoDescription) _description_1));
        String _location_1 = ((OptionalMedia)m).getDescription().getLocation();
        String _plus_2 = (this.playmsg + _location_1);
        String _plus_3 = (_plus_2 + "\n");
        this.playmsg = _plus_3;
      }
      if ((m instanceof AlternativesMedia)) {
        this.alt = ((AlternativesMedia)m).getMedias().size();
        final Consumer<MediaDescription> _function_1 = (MediaDescription alter) -> {
          try {
            for (this.i = this.poss; (this.i < this.totalvideos); this.i = (this.i + this.alt)) {
              {
                this.playlists.get(this.i).add(((VideoDescription) alter));
                String _location_2 = alter.getLocation();
                String _plus_4 = (this.playmsg + _location_2);
                String _plus_5 = (_plus_4 + "\n");
                this.playmsg = _plus_5;
              }
            }
            String _location_2 = alter.getLocation();
            String _plus_4 = (this.startcommand + _location_2);
            String _plus_5 = (_plus_4 + this.command);
            this.s = _plus_5;
            InputOutput.<String>println(this.s);
            this.process = Runtime.getRuntime().exec(this.s);
            InputStream _inputStream = this.process.getInputStream();
            InputStreamReader _inputStreamReader = new InputStreamReader(_inputStream);
            BufferedReader _bufferedReader = new BufferedReader(_inputStreamReader);
            this.stdInput = _bufferedReader;
            InputStream _errorStream = this.process.getErrorStream();
            InputStreamReader _inputStreamReader_1 = new InputStreamReader(_errorStream);
            BufferedReader _bufferedReader_1 = new BufferedReader(_inputStreamReader_1);
            this.stdError = _bufferedReader_1;
            this.process.getOutputStream().toString();
            while (((this.s = this.stdInput.readLine()) != null)) {
              InputOutput.<String>println(("//" + this.s));
            }
            while (((this.s = this.stdError.readLine()) != null)) {
              System.out.println(("Err:" + this.s));
            }
            this.poss++;
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        };
        ((AlternativesMedia)m).getMedias().forEach(_function_1);
        this.poss = 0;
      }
    };
    this.videoGen.getMedias().forEach(_function);
    this.playlist = this.playlists.get(this.random.nextInt(this.totalvideos));
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
  
  public Process createffFile() {
    try {
      Process _xblockexpression = null;
      {
        this.writer.println("#bref file");
        final Consumer<VideoDescription> _function = (VideoDescription video) -> {
          String _location = video.getLocation();
          String _plus = ("file \'" + _location);
          String _plus_1 = (_plus + "\'");
          this.writer.println(_plus_1);
        };
        this.playlist.forEach(_function);
        this.writer.close();
        _xblockexpression = Runtime.getRuntime().exec("ffmpeg -f concat 0 -i ./playlist.txt -c copy ./bref.mp4");
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
}
