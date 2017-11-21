import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
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
import org.xtext.example.mydsl.videoGen.VideoText;

@SuppressWarnings("all")
public class VideoGenTest1 {
  public List<List<MediaDescription>> loadPlaylists(final VideoGeneratorModel videoGen) {
    Assert.assertNotNull(videoGen);
    ArrayList<List<MediaDescription>> playlists = new ArrayList<List<MediaDescription>>();
    int nbPlaylists = 1;
    int nbVideos = 0;
    ArrayList<MediaDescription> _arrayList = new ArrayList<MediaDescription>();
    playlists.add(_arrayList);
    EList<Media> _medias = videoGen.getMedias();
    for (final Media media : _medias) {
      if ((media instanceof MandatoryMedia)) {
        MediaDescription _description = ((MandatoryMedia)media).getDescription();
        if ((_description instanceof VideoDescription)) {
          nbVideos++;
          for (final List<MediaDescription> playlist : playlists) {
            playlist.add(((MandatoryMedia)media).getDescription());
          }
        }
      } else {
        if ((media instanceof AlternativesMedia)) {
          int _nbPlaylists = nbPlaylists;
          int _size = ((AlternativesMedia)media).eContents().size();
          nbPlaylists = (_nbPlaylists * _size);
          int _nbVideos = nbVideos;
          int _size_1 = ((AlternativesMedia)media).eContents().size();
          nbVideos = (_nbVideos + _size_1);
          playlists = this.populatePlaylists(playlists, ((AlternativesMedia)media));
        } else {
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof VideoDescription)) {
              int _nbPlaylists_1 = nbPlaylists;
              nbPlaylists = (_nbPlaylists_1 * 2);
              nbVideos++;
              playlists = this.populatePlaylists(playlists, ((OptionalMedia)media));
            }
          }
        }
      }
    }
    return playlists;
  }
  
  @Test
  public void testLoadModel() {
    ArrayList<String> videosFiles = CollectionLiterals.<String>newArrayList("example1.videogen", "example2.videogen");
    for (final String file : videosFiles) {
      {
        final VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(file));
        final List<List<MediaDescription>> playlists = this.loadPlaylists(videoGen);
        List<String> csv = this.createCSV(playlists);
        int variants = this.genNbVariant(videoGen);
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
  
  public ArrayList<List<MediaDescription>> populatePlaylists(final List<List<MediaDescription>> playlists, final OptionalMedia opt) {
    final ArrayList<List<MediaDescription>> newPlaylists = new ArrayList<List<MediaDescription>>();
    for (final List<MediaDescription> playlist : playlists) {
      {
        ArrayList<MediaDescription> list1 = new ArrayList<MediaDescription>(playlist);
        list1.add(opt.getDescription());
        newPlaylists.add(list1);
        ArrayList<MediaDescription> list2 = new ArrayList<MediaDescription>(playlist);
        list2.add(null);
        newPlaylists.add(list2);
      }
    }
    return newPlaylists;
  }
  
  public ArrayList<List<MediaDescription>> populatePlaylists(final List<List<MediaDescription>> playlists, final AlternativesMedia alt) {
    final ArrayList<List<MediaDescription>> newPlaylists = new ArrayList<List<MediaDescription>>();
    for (final List<MediaDescription> playlist : playlists) {
      {
        int _size = alt.eContents().size();
        int size = (_size - 1);
        int index = 0;
        EList<EObject> _eContents = alt.eContents();
        for (final EObject desc : _eContents) {
          {
            ArrayList<MediaDescription> list = new ArrayList<MediaDescription>(playlist);
            for (int i = 0; (i < index); i++) {
              list.add(null);
            }
            list.add(((MediaDescription) desc));
            newPlaylists.add(list);
            for (int i = 0; (i < size); i++) {
              list.add(null);
            }
            size--;
            index++;
          }
        }
      }
    }
    return newPlaylists;
  }
  
  public void printPlaylist(final List<List<MediaDescription>> playlists) {
    int i = 0;
    for (final List<MediaDescription> playlist : playlists) {
      {
        int _plusPlus = i++;
        String _plus = ("Playlist " + Integer.valueOf(_plusPlus));
        InputOutput.<String>println(_plus);
        for (final MediaDescription media : playlist) {
          InputOutput.<MediaDescription>println(media);
        }
      }
    }
  }
  
  public String createCSVLine(final List<MediaDescription> playlist, final int index) {
    String line = (Integer.valueOf(index) + ";");
    int size = 0;
    for (final MediaDescription desc : playlist) {
      if ((desc != null)) {
        VideoText _text = ((VideoDescription) desc).getText();
        boolean _tripleNotEquals = (_text != null);
        if (_tripleNotEquals) {
          int _size = size;
          int _size_1 = ((VideoDescription) desc).getText().getSize();
          size = (_size + _size_1);
        }
        String _line = line;
        line = (_line + ("TRUE" + ";"));
      } else {
        String _line_1 = line;
        line = (_line_1 + ("FALSE" + ";"));
      }
    }
    return ((line + Integer.valueOf(size)) + ";");
  }
  
  public String createCSVHeader(final List<List<MediaDescription>> playlists) {
    final ArrayList<String> headerList = new ArrayList<String>();
    List<MediaDescription> _get = playlists.get(0);
    for (final MediaDescription mediaDesc : _get) {
      headerList.add(null);
    }
    for (final List<MediaDescription> playlist : playlists) {
      {
        int index = 0;
        for (final MediaDescription mediaDesc_1 : playlist) {
          {
            if (((headerList.get(index) == null) && (mediaDesc_1 != null))) {
              headerList.set(index, ((VideoDescription) mediaDesc_1).getVideoid());
            }
            index++;
          }
        }
      }
    }
    String header = "id;";
    for (final String str : headerList) {
      String _header = header;
      header = (_header + (str + ";"));
    }
    String _header_1 = header;
    header = (_header_1 + "size;");
    return header;
  }
  
  public List<String> createCSV(final List<List<MediaDescription>> playlists) {
    ArrayList<String> csv = new ArrayList<String>();
    csv.add(this.createCSVHeader(playlists));
    int index = 0;
    for (final List<MediaDescription> playlist : playlists) {
      int _plusPlus = index++;
      String _createCSVLine = this.createCSVLine(playlist, _plusPlus);
      String _plus = (_createCSVLine + "\n");
      csv.add(_plus);
    }
    return csv;
  }
  
  public int genNbVariant(final VideoGeneratorModel videoGen) {
    int variant = 1;
    EList<Media> _medias = videoGen.getMedias();
    for (final Media media : _medias) {
      if ((media instanceof OptionalMedia)) {
        int _variant = variant;
        variant = (_variant * 2);
      } else {
        if ((media instanceof AlternativesMedia)) {
          int _variant_1 = variant;
          int _size = ((AlternativesMedia)media).getMedias().size();
          variant = (_variant_1 * _size);
        }
      }
    }
    return variant;
  }
}
