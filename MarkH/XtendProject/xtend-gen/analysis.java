import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
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
    HashMap<String, Long> differenteVideo = CollectionLiterals.<String, Long>newHashMap();
    final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("public/template.videogen"));
    String _authorName = videoGen.getInformation().getAuthorName();
    String _plus = ("Auteur du fichier videogen : " + _authorName);
    InputOutput.<String>println(_plus);
    String _version = videoGen.getInformation().getVersion();
    String _plus_1 = ("Version du fichier videogen : " + _version);
    InputOutput.<String>println(_plus_1);
    InputOutput.<String>println("");
    ArrayList<variante> Variantes = CollectionLiterals.<variante>newArrayList();
    variante _variante = new variante();
    Variantes.add(_variante);
    if ((videoGen instanceof VideoGeneratorModel)) {
      EList<Media> _medias = videoGen.getMedias();
      for (final Media media : _medias) {
        if ((media instanceof MandatoryMedia)) {
          MediaDescription _description = ((MandatoryMedia)media).getDescription();
          if ((_description instanceof MediaDescription)) {
            String videoname = ((MandatoryMedia)media).getDescription().getLocation().split("/")[2];
            final File file = new File(("public/OriginVideo/" + videoname));
            boolean _containsKey = differenteVideo.containsKey(videoname);
            boolean _not = (!_containsKey);
            if (_not) {
              differenteVideo.put(videoname, Long.valueOf(file.length()));
            }
            final ArrayList<variante> VariantesTemp = CollectionLiterals.<variante>newArrayList();
            for (final variante i : Variantes) {
              VariantesTemp.add(i.clone().AddVideo(videoname, Long.valueOf(file.length())));
            }
            Variantes = VariantesTemp;
          }
        } else {
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof MediaDescription)) {
              String videoname_1 = ((OptionalMedia)media).getDescription().getLocation().split("/")[2];
              final File file_1 = new File(("public/OriginVideo/" + videoname_1));
              boolean _containsKey_1 = differenteVideo.containsKey(videoname_1);
              boolean _not_1 = (!_containsKey_1);
              if (_not_1) {
                differenteVideo.put(videoname_1, Long.valueOf(file_1.length()));
              }
              final ArrayList<variante> VariantesTemp_1 = CollectionLiterals.<variante>newArrayList();
              for (final variante i_1 : Variantes) {
                VariantesTemp_1.add(i_1.clone().AddVideo(videoname_1, Long.valueOf(file_1.length())));
              }
              Variantes.addAll(VariantesTemp_1);
            }
          } else {
            if ((media instanceof AlternativesMedia)) {
              final ArrayList<variante> VariantesTemp_2 = CollectionLiterals.<variante>newArrayList();
              EList<MediaDescription> _medias_1 = ((AlternativesMedia)media).getMedias();
              for (final MediaDescription medialter : _medias_1) {
                {
                  String videoname_2 = medialter.getLocation().split("/")[2];
                  final File file_2 = new File(("public/OriginVideo/" + videoname_2));
                  boolean _containsKey_2 = differenteVideo.containsKey(videoname_2);
                  boolean _not_2 = (!_containsKey_2);
                  if (_not_2) {
                    differenteVideo.put(videoname_2, Long.valueOf(file_2.length()));
                  }
                  for (final variante i_2 : Variantes) {
                    VariantesTemp_2.add(i_2.clone().AddVideo(videoname_2, Long.valueOf(file_2.length())));
                  }
                }
              }
              Variantes = VariantesTemp_2;
            }
          }
        }
      }
    }
    int _size = differenteVideo.size();
    String _plus_2 = (Integer.valueOf(_size) + " videos utilisées:");
    InputOutput.<String>println(_plus_2);
    Iterator<Map.Entry<String, Long>> it = differenteVideo.entrySet().iterator();
    while (it.hasNext()) {
      {
        Map.Entry<String, Long> pair = it.next();
        String _key = pair.getKey();
        String _plus_3 = (_key + " de ");
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
    long maxduree = 0L;
    long minduree = 99999999999999999L;
    for (final variante c : Variantes) {
      {
        if (((c.TotalSize).longValue() > maxSize)) {
          maxSize = (c.TotalSize).longValue();
        }
        if (((c.TotalSize).longValue() < minSize)) {
          minSize = (c.TotalSize).longValue();
        }
        if (((c.Totalduree).longValue() > maxduree)) {
          maxduree = (c.Totalduree).longValue();
        }
        if (((c.Totalduree).longValue() < minduree)) {
          minduree = (c.Totalduree).longValue();
        }
      }
    }
    int _size_1 = Variantes.size();
    String _plus_3 = (Integer.valueOf(_size_1) + " variantes possible.");
    InputOutput.<String>println(_plus_3);
    InputOutput.<String>println((("Poid max variante: " + Float.valueOf((maxSize / 1048576f))) + " mo"));
    InputOutput.<String>println((("Poid min variante: " + Float.valueOf((minSize / 1048576f))) + " mo"));
    InputOutput.<String>println((("Durée max variante: " + Long.valueOf(maxduree)) + " secondes (non correct)"));
    InputOutput.<String>println((("Durée min variante: " + Long.valueOf(minduree)) + " secondes (non correct)"));
    InputOutput.<String>println("");
    InputOutput.<String>println("liste des poids possible des vidéos en Mega-Octets (estimation avec additions des poids vidéo):");
    for (final variante c_1 : Variantes) {
      float _GetPoidMO = c_1.GetPoidMO();
      String _plus_4 = (Float.valueOf(_GetPoidMO) + "  ");
      InputOutput.<String>print(_plus_4);
    }
    InputOutput.<String>println("");
    InputOutput.<String>println("");
  }
  
  public static Float getDuration(final String path) {
    try {
      String cmd = (("ffprobe -i " + path) + " -show_entries format=duration -v quiet -of csv=\"p=0\"");
      InputOutput.<String>println(cmd);
      Process p = Runtime.getRuntime().exec(cmd);
      InputStream in = p.getInputStream();
      int c = 0;
      while (((c = in.read()) != (-1))) {
        InputOutput.<Integer>println(Integer.valueOf(c));
      }
      in.close();
      return Float.valueOf(0f);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
