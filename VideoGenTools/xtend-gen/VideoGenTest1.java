import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class VideoGenTest1 {
  @Test
  public void testLoadModel() {
    ArrayList<String> videosFiles = CollectionLiterals.<String>newArrayList("example1.videogen", "example2.videogen");
    for (final String file : videosFiles) {
      {
        final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(file));
        List<String> csv = VideoGenUtils.createCSV(videoGen);
        int variants = VideoGenUtils.genNbVariant(videoGen);
        final List<String> _converted_csv = (List<String>)csv;
        int _length = ((Object[])Conversions.unwrapArray(_converted_csv, Object.class)).length;
        int _minus = (_length - 1);
        String _plus = ((file + " : csv size -> ") + Integer.valueOf(_minus));
        String _plus_1 = (_plus + " nbVariants -> ");
        String _plus_2 = (_plus_1 + Integer.valueOf(variants));
        InputOutput.<String>println(_plus_2);
        final List<String> _converted_csv_1 = (List<String>)csv;
        int _length_1 = ((Object[])Conversions.unwrapArray(_converted_csv_1, Object.class)).length;
        int _minus_1 = (_length_1 - 1);
        boolean _equals = (_minus_1 == variants);
        Assert.assertTrue(_equals);
      }
    }
  }
}
