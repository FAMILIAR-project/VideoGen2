import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
 * Affiche le nombre de variantes possibles
 * Affiche le poid de chaque variantes
 */
@SuppressWarnings("all")
public class analysis {
  public static void main(final String[] args) {
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("public/template.videogen"));
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
            String _plus_2 = ("public/OriginVideo/" + _location);
            final File file = new File(_plus_2);
            final ArrayList<Long> listpoidtemp = CollectionLiterals.<Long>newArrayList();
            for (final Long i : listpoid) {
              long _length = file.length();
              long _plus_3 = ((i).longValue() + _length);
              listpoidtemp.add(Long.valueOf(_plus_3));
            }
            listpoid = listpoidtemp;
          }
        } else {
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof MediaDescription)) {
              String _location_1 = ((OptionalMedia)media).getDescription().getLocation();
              String _plus_4 = ("public/OriginVideo/" + _location_1);
              final File file_1 = new File(_plus_4);
              final ArrayList<Long> listpoidtemp_1 = CollectionLiterals.<Long>newArrayList();
              for (final Long i_1 : listpoid) {
                long _length_1 = file_1.length();
                long _plus_5 = ((i_1).longValue() + _length_1);
                listpoidtemp_1.add(Long.valueOf(_plus_5));
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
                  String _plus_6 = ("public/OriginVideo/" + _location_2);
                  final File file_2 = new File(_plus_6);
                  for (final Long i_2 : listpoid) {
                    long _length_2 = file_2.length();
                    long _plus_7 = ((i_2).longValue() + _length_2);
                    listpoidalter.add(Long.valueOf(_plus_7));
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
    String _plus_6 = (Integer.valueOf(_size) + " variantes possible.");
    InputOutput.<String>println(_plus_6);
    Long _max = Collections.<Long>max(listpoid);
    String _plus_7 = ("Poid max variante: " + _max);
    String _plus_8 = (_plus_7 + "octets soit ");
    Long _max_1 = Collections.<Long>max(listpoid);
    float _divide = ((_max_1).longValue() / 1048576f);
    String _plus_9 = (_plus_8 + Float.valueOf(_divide));
    String _plus_10 = (_plus_9 + "mo");
    InputOutput.<String>println(_plus_10);
    Long _min = Collections.<Long>min(listpoid);
    String _plus_11 = ("Poid min variante: " + _min);
    String _plus_12 = (_plus_11 + "octets soit ");
    Long _min_1 = Collections.<Long>min(listpoid);
    float _divide_1 = ((_min_1).longValue() / 1048576f);
    String _plus_13 = (_plus_12 + Float.valueOf(_divide_1));
    String _plus_14 = (_plus_13 + "mo");
    InputOutput.<String>println(_plus_14);
    InputOutput.<String>println("");
    InputOutput.<String>println("liste des poid possible des vidéos en Octets :");
    InputOutput.<ArrayList<Long>>println(listpoid);
    InputOutput.<String>println("");
    ArrayList<Float> listpoidmo = CollectionLiterals.<Float>newArrayList();
    for (final Long c : listpoid) {
      listpoidmo.add(Float.valueOf(((c).longValue() / 1048576f)));
    }
    InputOutput.<String>println("liste des poid possible des vidéos en Mega-Octets :");
    InputOutput.<ArrayList<Float>>println(listpoidmo);
    InputOutput.<String>println("");
  }
}
