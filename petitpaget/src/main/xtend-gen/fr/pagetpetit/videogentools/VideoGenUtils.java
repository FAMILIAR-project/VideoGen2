package fr.pagetpetit.videogentools;

import fr.pagetpetit.videogentools.ProcessExec;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoText;

@SuppressWarnings("all")
public class VideoGenUtils {
  public static List<List<MediaDescription>> loadPlaylists(final VideoGeneratorModel videoGen) {
    ArrayList<List<MediaDescription>> playlists = new ArrayList<List<MediaDescription>>();
    ArrayList<MediaDescription> _arrayList = new ArrayList<MediaDescription>();
    playlists.add(_arrayList);
    EList<Media> _medias = videoGen.getMedias();
    for (final Media media : _medias) {
      if ((media instanceof MandatoryMedia)) {
        MediaDescription _description = ((MandatoryMedia)media).getDescription();
        if ((_description instanceof VideoDescription)) {
          for (final List<MediaDescription> playlist : playlists) {
            playlist.add(((MandatoryMedia)media).getDescription());
          }
        }
      } else {
        if ((media instanceof AlternativesMedia)) {
          playlists = VideoGenUtils.populatePlaylists(playlists, ((AlternativesMedia)media));
        } else {
          if ((media instanceof OptionalMedia)) {
            MediaDescription _description_1 = ((OptionalMedia)media).getDescription();
            if ((_description_1 instanceof VideoDescription)) {
              playlists = VideoGenUtils.populatePlaylists(playlists, ((OptionalMedia)media));
            }
          }
        }
      }
    }
    return playlists;
  }
  
  private static ArrayList<List<MediaDescription>> populatePlaylists(final List<List<MediaDescription>> playlists, final OptionalMedia opt) {
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
  
  private static ArrayList<List<MediaDescription>> populatePlaylists(final List<List<MediaDescription>> playlists, final AlternativesMedia alt) {
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
  
  public static void printPlaylist(final List<List<MediaDescription>> playlists) {
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
  
  private static String createCSVLine(final List<MediaDescription> playlist, final int index) {
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
  
  private static String createCSVHeader(final List<List<MediaDescription>> playlists) {
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
  
  public static List<String> createCSV(final VideoGeneratorModel videoGen) {
    return VideoGenUtils.createCSV(VideoGenUtils.loadPlaylists(videoGen));
  }
  
  public static List<String> createCSV(final List<List<MediaDescription>> playlists) {
    ArrayList<String> csv = new ArrayList<String>();
    csv.add(VideoGenUtils.createCSVHeader(playlists));
    int index = 0;
    for (final List<MediaDescription> playlist : playlists) {
      int _plusPlus = index++;
      String _createCSVLine = VideoGenUtils.createCSVLine(playlist, _plusPlus);
      String _plus = (_createCSVLine + "\n");
      csv.add(_plus);
    }
    return csv;
  }
  
  public static int genNbVariant(final VideoGeneratorModel videoGen) {
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
  
  public static String[] getRandomVariant(final VideoGeneratorModel model) {
    final ArrayList<String> variant = new ArrayList<String>();
    EList<Media> _medias = model.getMedias();
    for (final Media media : _medias) {
      if (((media instanceof MandatoryMedia) && (((MandatoryMedia) media).getDescription() instanceof VideoDescription))) {
        variant.add(((MandatoryMedia) media).getDescription().getLocation());
      } else {
        if (((media instanceof OptionalMedia) && (((OptionalMedia) media).getDescription() instanceof VideoDescription))) {
          double _random = Math.random();
          boolean _greaterThan = (_random > 0.5);
          if (_greaterThan) {
            variant.add(((OptionalMedia) media).getDescription().getLocation());
          }
        } else {
          if (((media instanceof AlternativesMedia) && (((AlternativesMedia) media).getMedias().get(0) instanceof VideoDescription))) {
            variant.add(VideoGenUtils.choose(((AlternativesMedia) media)));
          }
        }
      }
    }
    return ((String[])Conversions.unwrapArray(variant, String.class));
  }
  
  public static String choose(final AlternativesMedia media) {
    String chosen = "";
    chosen = null;
    int total = 0;
    final ArrayList<Integer> weights = new ArrayList<Integer>();
    EList<MediaDescription> _medias = media.getMedias();
    for (final MediaDescription desc : _medias) {
      {
        weights.add(Integer.valueOf(total));
        int _tal = total;
        int _probability = ((VideoDescription) desc).getProbability();
        total = (_tal + _probability);
      }
    }
    InputOutput.<String>println(("total = " + Integer.valueOf(total)));
    double _random = Math.random();
    final double random = (_random * total);
    InputOutput.<String>println(("random = " + Double.valueOf(random)));
    boolean bool = true;
    for (int i = 0; ((i < (((Object[])Conversions.unwrapArray(media.getMedias(), Object.class)).length - 1)) && bool); i++) {
      {
        Integer _get = weights.get(i);
        String _plus = ("value = " + _get);
        InputOutput.<String>println(_plus);
        if (((random >= (weights.get(i)).intValue()) && (random < (weights.get((i + 1))).intValue()))) {
          chosen = media.getMedias().get(i).getLocation();
          bool = false;
        }
      }
    }
    if ((chosen == null)) {
      final int length = ((Object[])Conversions.unwrapArray(media.getMedias(), Object.class)).length;
      chosen = media.getMedias().get((length - 1)).getLocation();
      InputOutput.<String>println(("chosen = " + chosen));
    }
    return chosen;
  }
  
  public static String concatVideos(final String[] filenames, final String outputfilename) {
    final ArrayList<String> COMMAND = new ArrayList<String>();
    String filter = "";
    COMMAND.add("ffmpeg");
    COMMAND.add("-y");
    int i = 0;
    for (final String file : filenames) {
      {
        COMMAND.add("-i");
        COMMAND.add(file);
        String _filter = filter;
        filter = (_filter + (((("[" + Integer.valueOf(i)) + ":v:0][") + Integer.valueOf(i)) + ":a:0]"));
        i++;
      }
    }
    COMMAND.add("-filter_complex");
    String _filter = filter;
    filter = (_filter + (("concat=n=" + Integer.valueOf(i)) + ":v=1:a=1[outv][outa]"));
    COMMAND.add(filter);
    COMMAND.add("-map");
    COMMAND.add("[outv]");
    COMMAND.add("-map");
    COMMAND.add("[outa]");
    COMMAND.add(outputfilename);
    for (final String arg : COMMAND) {
      InputOutput.<String>println(arg);
    }
    ProcessExec.executeCommand(((String[])Conversions.unwrapArray(COMMAND, String.class)));
    InputOutput.<String>println((outputfilename + " created"));
    return outputfilename;
  }
  
  public static int[] getResolution(final String videoFile) {
    final ArrayList<String> COMMAND = new ArrayList<String>();
    COMMAND.add("ffmpeg");
    COMMAND.add("-i");
    COMMAND.add(videoFile);
    final String[] IO = ProcessExec.executeCommandIO(((String[])Conversions.unwrapArray(COMMAND, String.class)));
    final ArrayList<Integer> resolution = new ArrayList<Integer>();
    for (final String line : IO) {
      boolean _contains = line.contains("Stream #0:0(und):");
      if (_contains) {
        final String[] res = line.split(", ")[2].split(" ")[0].split("x");
        resolution.add(Integer.valueOf(Integer.parseInt(res[0])));
        resolution.add(Integer.valueOf(Integer.parseInt(res[1])));
      }
    }
    return ((int[])Conversions.unwrapArray(resolution, int.class));
  }
  
  public static String resize(final String filename, final int input_width, final int input_height, final int output_width, final int output_height, final String outputFolder) {
    if (((input_width == output_width) && (input_height == output_height))) {
      InputOutput.<String>println((filename + " already at right size"));
      return filename;
    }
    String[] path = filename.split("/");
    int _length = path.length;
    int _minus = (_length - 1);
    String[] file = path[_minus].replace(".", "@#").split("@#");
    ArrayList<String> COMMAND = new ArrayList<String>();
    COMMAND.add("ffmpeg");
    COMMAND.add("-y");
    COMMAND.add("-i");
    COMMAND.add(filename);
    COMMAND.add("-vf");
    COMMAND.add(((((((("pad=" + Integer.valueOf(output_width)) + ":") + Integer.valueOf(output_height)) + ":") + Integer.valueOf(((output_width - input_width) / 2))) + ":") + Integer.valueOf(((output_height - input_height) / 2))));
    int _length_1 = file.length;
    int _minus_1 = (_length_1 - 2);
    String _get = file[_minus_1];
    String _plus = ((outputFolder + "/") + _get);
    String _plus_1 = (_plus + "_o.");
    int _length_2 = file.length;
    int _minus_2 = (_length_2 - 1);
    String _get_1 = file[_minus_2];
    final String output_file = (_plus_1 + _get_1);
    COMMAND.add(output_file);
    for (final String c : COMMAND) {
      InputOutput.<String>println(c);
    }
    final ArrayList<String> _converted_COMMAND = (ArrayList<String>)COMMAND;
    ProcessExec.executeCommand(((String[])Conversions.unwrapArray(_converted_COMMAND, String.class)));
    InputOutput.<String>println((filename + " resized"));
    return output_file;
  }
  
  public static String generatePlaylist(final String[] videos, final String outputFolder) {
    final ArrayList<int[]> res = new ArrayList<int[]>();
    for (final String video : videos) {
      res.add(VideoGenUtils.getResolution(video));
    }
    int output_width = 0;
    int output_height = 0;
    for (final int[] r : res) {
      {
        int _get = r[0];
        boolean _greaterThan = (_get > output_width);
        if (_greaterThan) {
          output_width = r[0];
        }
        int _get_1 = r[1];
        boolean _greaterThan_1 = (_get_1 > output_height);
        if (_greaterThan_1) {
          output_height = r[1];
        }
      }
    }
    int i = 0;
    final ArrayList<String> playlist = new ArrayList<String>();
    for (final String video_1 : videos) {
      {
        int _plusPlus = i++;
        final int[] r_1 = res.get(_plusPlus);
        playlist.add(VideoGenUtils.resize(video_1, r_1[0], r_1[1], output_width, output_height, outputFolder));
      }
    }
    long _currentTimeMillis = System.currentTimeMillis();
    String _plus = ((outputFolder + "/output_") + Long.valueOf(_currentTimeMillis));
    String _plus_1 = (_plus + ".mp4");
    return VideoGenUtils.concatVideos(((String[])Conversions.unwrapArray(playlist, String.class)), _plus_1);
  }
}
