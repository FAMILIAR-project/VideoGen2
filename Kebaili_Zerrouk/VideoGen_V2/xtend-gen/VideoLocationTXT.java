import java.util.Random;
import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class VideoLocationTXT {
  public static String fromModelToTXTFile(final VideoGeneratorModel videoGen) {
    String filelist = "";
    if ((videoGen instanceof VideoGeneratorModel)) {
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        if ((media instanceof MandatoryMedia)) {
          MediaDescription _description = ((MandatoryMedia)media).getDescription();
          if ((_description instanceof MediaDescription)) {
            String _location = ((MandatoryMedia)media).getDescription().getLocation();
            String _plus = ((filelist + "file \'") + _location);
            String _plus_1 = (_plus + "\'\n");
            filelist = _plus_1;
          }
        } else {
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof MediaDescription)) {
              int _nextInt = new Random().nextInt(100);
              boolean _greaterThan = (_nextInt > 50);
              if (_greaterThan) {
                String _location_1 = ((OptionalMedia)media).getDescription().getLocation();
                String _plus_2 = ((filelist + "file \'") + _location_1);
                String _plus_3 = (_plus_2 + "\'\n");
                filelist = _plus_3;
              }
            }
          } else {
            if ((media instanceof AlternativesMedia)) {
              int alternativesize = ((AlternativesMedia)media).getMedias().size();
              final MediaDescription random_alt = ((AlternativesMedia)media).getMedias().get(new Random().nextInt(alternativesize));
              if ((random_alt instanceof MediaDescription)) {
                String _location_2 = random_alt.getLocation();
                String _plus_4 = ((filelist + "file \'") + _location_2);
                String _plus_5 = (_plus_4 + "\'\n");
                filelist = _plus_5;
              }
            }
          }
        }
      }
    }
    return filelist;
  }
}
