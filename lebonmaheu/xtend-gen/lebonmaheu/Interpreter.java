package lebonmaheu;

import lebonmaheu.VideoGenHelper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class Interpreter {
  public static void main(final String[] args) {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"));
    Assert.assertNotNull(videoGen);
    InputOutput.<String>println(videoGen.getInformation().getAuthorName());
  }
}
