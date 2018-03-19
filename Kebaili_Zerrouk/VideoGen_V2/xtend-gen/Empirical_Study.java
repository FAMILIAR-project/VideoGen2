import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

/**
 * Displays :
 * Authors/Version of the model
 * List of the input videos for the generator (dispalying the size of each video)
 * Displays the number of possibilities of generated videos
 * Max & Min size of the possibly generated video
 */
@SuppressWarnings("all")
public class Empirical_Study {
  public static void main(final String[] args) {
    HashMap<String, Long> playList = CollectionLiterals.<String, Long>newHashMap();
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("Videos_Elements/InputFiles/Input_ModelsPlaylist/model.videogen"));
    String _authorName = videoGen.getInformation().getAuthorName();
    String _plus = ("Model Author: " + _authorName);
    InputOutput.<String>println(_plus);
    String _version = videoGen.getInformation().getVersion();
    String _plus_1 = ("Model Version : " + _version);
    InputOutput.<String>println(_plus_1);
    InputOutput.<String>println("");
    ArrayList<variety> VariantesList = CollectionLiterals.<variety>newArrayList();
    variety _variety = new variety();
    VariantesList.add(_variety);
    if ((videoGen instanceof VideoGeneratorModel)) {
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        if ((media instanceof MandatoryMedia)) {
          MediaDescription _description = ((MandatoryMedia)media).getDescription();
          if ((_description instanceof MediaDescription)) {
            String videoname = ((MandatoryMedia)media).getDescription().getLocation().split("/")[2];
            final File file = new File(("Videos_Elements/InputFiles/InputVideos/" + videoname));
            boolean _containsKey = playList.containsKey(videoname);
            boolean _not = (!_containsKey);
            if (_not) {
              playList.put(videoname, Long.valueOf(file.length()));
            }
            final ArrayList<variety> VariantesTemp = CollectionLiterals.<variety>newArrayList();
            for (final variety i : VariantesList) {
              VariantesTemp.add(i.clone().AddVideo(videoname, Long.valueOf(file.length())));
            }
            VariantesList = VariantesTemp;
          }
        } else {
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof MediaDescription)) {
              String videoname_1 = ((OptionalMedia)media).getDescription().getLocation().split("/")[2];
              final File file_1 = new File(("Videos_Elements/InputFiles/InputVideos/" + videoname_1));
              boolean _containsKey_1 = playList.containsKey(videoname_1);
              boolean _not_1 = (!_containsKey_1);
              if (_not_1) {
                playList.put(videoname_1, Long.valueOf(file_1.length()));
              }
              final ArrayList<variety> VariantesTemp_1 = CollectionLiterals.<variety>newArrayList();
              for (final variety i_1 : VariantesList) {
                VariantesTemp_1.add(i_1.clone().AddVideo(videoname_1, Long.valueOf(file_1.length())));
              }
              VariantesList.addAll(VariantesTemp_1);
            }
          } else {
            if ((media instanceof AlternativesMedia)) {
              final ArrayList<variety> VariantesTemp_2 = CollectionLiterals.<variety>newArrayList();
              EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
              for (final MediaDescription medialter : _medias_1) {
                {
                  String videoname_2 = medialter.getLocation().split("/")[2];
                  final File file_2 = new File(("Videos_Elements/InputFiles/InputVideos/" + videoname_2));
                  boolean _containsKey_2 = playList.containsKey(videoname_2);
                  boolean _not_2 = (!_containsKey_2);
                  if (_not_2) {
                    playList.put(videoname_2, Long.valueOf(file_2.length()));
                  }
                  for (final variety i_2 : VariantesList) {
                    VariantesTemp_2.add(i_2.clone().AddVideo(videoname_2, Long.valueOf(file_2.length())));
                  }
                }
              }
              VariantesList = VariantesTemp_2;
            }
          }
        }
      }
    }
    int _size = playList.size();
    String _plus_2 = ("Nombre de séquences videos utilisées pour consituer une playliste : " + Integer.valueOf(_size));
    InputOutput.<String>println(_plus_2);
    Iterator<Map.Entry<String, Long>> it = playList.entrySet().iterator();
    while (it.hasNext()) {
      {
        Map.Entry<String, Long> pair = it.next();
        String _key = pair.getKey();
        String _plus_3 = (_key + " qui pèse ");
        Long _value = pair.getValue();
        float _divide = ((_value).longValue() / 1048576f);
        String _plus_4 = (_plus_3 + Float.valueOf(_divide));
        String _plus_5 = (_plus_4 + " mo");
        System.out.println(_plus_5);
      }
    }
    InputOutput.<String>println("");
    long maxSize = 0L;
    long minSize = 9999999999999L;
    for (final variety c : VariantesList) {
      {
        if (((c.TotalSize).longValue() > maxSize)) {
          maxSize = (c.TotalSize).longValue();
        }
        if (((c.TotalSize).longValue() < minSize)) {
          minSize = (c.TotalSize).longValue();
        }
      }
    }
    int _size_1 = VariantesList.size();
    String _plus_3 = (Integer.valueOf(_size_1) + " variantes possible.");
    InputOutput.<String>println(_plus_3);
    InputOutput.<String>println((("Le poid maximum d\'une variante : " + Float.valueOf((maxSize / 1048576f))) + " mo"));
    InputOutput.<String>println((("Le poid minimum d\'une variante : " + Float.valueOf((minSize / 1048576f))) + " mo"));
  }
}
