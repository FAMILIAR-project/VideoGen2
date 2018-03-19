import java.io.File;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class mainLauncher {
  public static void main(final String[] args) {
    try {
      final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("Videos_Elements/InputFiles/Input_ModelsPlaylist/model.videogen"));
      String filelist = VideoLocationTXT.fromModelToTXTFile(videoGen);
      String fileName = FileWriter.FileWriter(filelist);
      String command = (((("ffmpeg -f concat -safe 0 -i Videos_Elements/InputFiles/Input_TextPlaylist/" + fileName) + ".txt -y -c copy Videos_Elements/GeneratedVideo/") + fileName) + ".mp4");
      Process p = Runtime.getRuntime().exec(command);
      p.waitFor();
      final File file = new File((("Videos_Elements/Input_TextPlaylist/" + fileName) + ".txt"));
      file.delete();
      InputOutput.<String>println((fileName + ".mp4"));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
