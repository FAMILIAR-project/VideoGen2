import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.junit.Assert;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

/**
 * Affiche le nombre de variantes possibles
 * Affiche le poid de chaque variantes
 */
@SuppressWarnings("all")
public class NombreEtPoid {
  @Test
  public void testLoadModel() {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("template.videogen"));
    Assert.assertNotNull(videoGen);
    String _authorName = videoGen.getInformation().getAuthorName();
    String _plus = ("Auteur du fichier videogen : " + _authorName);
    InputOutput.<String>println(_plus);
    String _version = videoGen.getInformation().getVersion();
    String _plus_1 = ("Version du fichier videogen : " + _version);
    InputOutput.<String>println(_plus_1);
    ArrayList<Long> listpoid = CollectionLiterals.<Long>newArrayList();
    listpoid.add(Long.valueOf(0l));
    if ((videoGen instanceof VideoGeneratorModel)) {
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        if ((media instanceof MandatoryMedia)) {
          MediaDescription _description = ((MandatoryMedia)media).getDescription();
          if ((_description instanceof MediaDescription)) {
            String _location = ((MandatoryMedia)media).getDescription().getLocation();
            final File file = new File(_location);
            final ArrayList<Long> listpoidtemp = CollectionLiterals.<Long>newArrayList();
            for (final Long i : listpoid) {
              long _length = file.length();
              long _plus_2 = ((i).longValue() + _length);
              listpoidtemp.add(Long.valueOf(_plus_2));
            }
            listpoid = listpoidtemp;
          }
        } else {
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof MediaDescription)) {
              String _location_1 = ((OptionalMedia)media).getDescription().getLocation();
              final File file_1 = new File(_location_1);
              final ArrayList<Long> listpoidtemp_1 = CollectionLiterals.<Long>newArrayList();
              for (final Long i_1 : listpoid) {
                long _length_1 = file_1.length();
                long _plus_3 = ((i_1).longValue() + _length_1);
                listpoidtemp_1.add(Long.valueOf(_plus_3));
              }
              listpoid.addAll(listpoidtemp_1);
            }
          } else {
            if ((media instanceof AlternativesMedia)) {
              ArrayList<Long> listpoidalter = CollectionLiterals.<Long>newArrayList();
              EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
              for (final MediaDescription medialter : _medias_1) {
                {
                  String _location_2 = medialter.getLocation();
                  final File file_2 = new File(_location_2);
                  for (final Long i_2 : listpoid) {
                    long _length_2 = file_2.length();
                    long _plus_4 = ((i_2).longValue() + _length_2);
                    listpoidalter.add(Long.valueOf(_plus_4));
                  }
                }
              }
              listpoid = listpoidalter;
            }
          }
        }
      }
    }
    int _size = listpoid.size();
    String _plus_4 = (Integer.valueOf(_size) + " variantes possible.");
    InputOutput.<String>println(_plus_4);
    Long _max = Collections.<Long>max(listpoid);
    String _plus_5 = ("Poid max : " + _max);
    InputOutput.<String>println(_plus_5);
    Long _min = Collections.<Long>min(listpoid);
    String _plus_6 = ("Poid min : " + _min);
    InputOutput.<String>println(_plus_6);
    InputOutput.<ArrayList<Long>>println(listpoid);
  }
}
