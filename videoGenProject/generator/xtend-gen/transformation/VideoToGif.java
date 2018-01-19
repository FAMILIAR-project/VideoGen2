package transformation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.Filter;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoText;
import util.Filters;
import util.Utils;

@SuppressWarnings("all")
public class VideoToGif {
  /**
   * generer gif pour une video donnée
   */
  public int videoToGif(final String path, final String output, final int height, final int width) {
    try {
      int _xblockexpression = (int) 0;
      {
        final String cmd = ((((((((("ffmpeg -i " + path) + " -vf ") + "scale=") + Integer.valueOf(height)) + ":") + Integer.valueOf(width)) + " ") + output) + " -hide_banner");
        _xblockexpression = Runtime.getRuntime().exec(cmd).waitFor();
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * generer gif pour chaque video choisie dans le model
   */
  public boolean modelToGifs(final VideoGeneratorModel videoGen, final int height, final int width) {
    EList<Media> _medias = videoGen.getMedias();
    for (final Media media : _medias) {
      if ((media instanceof MandatoryMedia)) {
        final MandatoryMedia manMedia = ((MandatoryMedia) media);
        final MediaDescription manMediaDesc = manMedia.getDescription();
        if ((manMediaDesc instanceof VideoDescription)) {
          final Filter filter = ((VideoDescription)manMediaDesc).getFilter();
          final VideoText videoText = ((VideoDescription)manMediaDesc).getText();
          String path = Filters.addText(((VideoDescription)manMediaDesc).getLocation(), videoText, false);
          boolean _equals = path.equals(((VideoDescription)manMediaDesc).getLocation());
          final boolean locationChanged = (!_equals);
          path = Filters.filter(filter, path, locationChanged);
          this.videoToGif(path, Utils.rename(path, "videos", "gifs", "mp4", "gif"), height, width);
        }
      } else {
        if ((media instanceof OptionalMedia)) {
          final OptionalMedia opMedia = ((OptionalMedia) media);
          final MediaDescription opMediaDesc = opMedia.getDescription();
          if ((opMediaDesc instanceof VideoDescription)) {
            final VideoDescription opVideoDesc = ((VideoDescription) opMediaDesc);
            double probability = 0.5;
            int _probability = opVideoDesc.getProbability();
            boolean _greaterThan = (_probability > 0);
            if (_greaterThan) {
              int _probability_1 = opVideoDesc.getProbability();
              double _multiply = (0.01 * _probability_1);
              probability = _multiply;
            }
            double _random = Math.random();
            boolean _lessThan = (_random < probability);
            if (_lessThan) {
              final Filter filter_1 = opVideoDesc.getFilter();
              final VideoText videoText_1 = opVideoDesc.getText();
              String path_1 = Filters.addText(opVideoDesc.getLocation(), videoText_1, false);
              boolean _equals_1 = path_1.equals(opVideoDesc.getLocation());
              final boolean locationChanged_1 = (!_equals_1);
              path_1 = Filters.filter(filter_1, path_1, locationChanged_1);
              this.videoToGif(path_1, Utils.rename(path_1, "videos", "gifs", "mp4", "gif"), height, width);
            }
          }
        } else {
          if ((media instanceof AlternativesMedia)) {
            final EList<MediaDescription> altMedia = ((AlternativesMedia)media).getMedias();
            final MediaDescription choice = Utils.choose(altMedia);
            final Filter filter_2 = ((VideoDescription) choice).getFilter();
            final VideoText videoText_2 = ((VideoDescription) choice).getText();
            String path_2 = Filters.addText(choice.getLocation(), videoText_2, false);
            boolean _equals_2 = path_2.equals(choice.getLocation());
            final boolean locationChanged_2 = (!_equals_2);
            path_2 = Filters.filter(filter_2, path_2, locationChanged_2);
            this.videoToGif(path_2, Utils.rename(path_2, "videos", "gifs", "mp4", "gif"), height, width);
          }
        }
      }
    }
    return true;
  }
}
