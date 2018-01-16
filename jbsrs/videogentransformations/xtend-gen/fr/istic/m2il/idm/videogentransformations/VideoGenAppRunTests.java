package fr.istic.m2il.idm.videogentransformations;

import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformationsTest;
import java.util.HashMap;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

@SuppressWarnings("all")
public class VideoGenAppRunTests {
  public static void main(final String[] args) {
    HashMap<String, String> choices = CollectionLiterals.<String, String>newHashMap();
    choices.put("nombre_variants", "checksVariantsNumber");
    choices.put("concatener_videos", "concatVideos");
    choices.put("gerer_probabilites", "generateRandomPlayList");
    choices.put("exporter_gifs", "videoToGif");
    choices.put("tailles_variantes", "checksCSVLinesNumber");
    choices.put("durees_variantes", "checksCSVLinesNumber");
    choices.put("appliquer_filtres", "generateRandomPlayList");
    int _size = ((List<String>)Conversions.doWrapArray(args)).size();
    boolean _lessThan = (_size < 3);
    if (_lessThan) {
      System.err.println("Veuillez indiquez tous les arguments !!!");
      System.exit(0);
    } else {
      String _get = args[0];
      if (_get != null) {
        switch (_get) {
          case "durees_variantes":
            System.setProperty("csv_duration", "true");
            System.setProperty("videogenspecification", args[2]);
            break;
          case "appliquer_filtres":
            String _get_1 = args[3];
            boolean _tripleEquals = (_get_1 == null);
            if (_tripleEquals) {
              System.err.println("Veuillez indiquez tous les arguments !!!");
              System.exit(0);
            } else {
              System.setProperty("file_filter", args[2]);
              System.setProperty("filter", args[3]);
            }
            break;
          case "expoter_gif":
            if (((args[3] == null) && (args[4] != null))) {
              System.err.println("Veuillez indiquez tous les arguments !!!");
              System.exit(0);
            } else {
              System.setProperty("videogenspecification", args[2]);
              System.setProperty("gif_width", args[3]);
              System.setProperty("gif_heigth", args[4]);
            }
            break;
          case "concatener_videos":
            if (((args[3] == null) && (args[4] == null))) {
              System.err.println("Veuillez indiquez tous les arguments !!!");
              System.exit(0);
            } else {
              System.setProperty("concat_format", args[2]);
              String files = args[3];
              String _files = files;
              String _get_2 = args[4];
              String _plus = (" " + _get_2);
              files = (_files + _plus);
              for (int i = 5; (i < args.length); i++) {
                String _files_1 = files;
                String _get_3 = args[i];
                String _plus_1 = (" " + _get_3);
                files = (_files_1 + _plus_1);
              }
              System.setProperty("concat_files", files);
            }
            break;
          case "tailles_variantes":
            String _get_3 = args[3];
            boolean _tripleEquals_1 = (_get_3 == null);
            if (_tripleEquals_1) {
              System.err.println("Veuillez indiquez tous les arguments !!!");
              System.exit(0);
            } else {
              boolean _equals = args[3].equals("gif");
              if (_equals) {
                System.setProperty("csv_type", "true");
                if (((args[4] != null) && (args[5] != null))) {
                  System.setProperty("gif_width", args[4]);
                  System.setProperty("gif_heigth", args[5]);
                  System.setProperty("videogenspecification", args[2]);
                  System.setProperty("csv_duration", "false");
                } else {
                  System.err.println("Veuillez indiquez tous les arguments !!!");
                  System.exit(0);
                }
              }
              boolean _equals_1 = args[3].equals("video");
              if (_equals_1) {
                System.setProperty("csv_type", "false");
                System.setProperty("videogenspecification", args[2]);
                System.setProperty("csv_duration", "false");
              }
            }
            break;
          default:
            System.setProperty("videogenspecification", args[2]);
            break;
        }
      } else {
        System.setProperty("videogenspecification", args[2]);
      }
      final JUnitCore jUnitCore = new JUnitCore();
      String _get_4 = args[1];
      String _plus_1 = ("Outpout " + _get_4);
      System.out.println(_plus_1);
      VideoGenConfigs.setOutPutFolder(args[1]);
      String _get_5 = args[1];
      String _plus_2 = ("Outpout " + _get_5);
      System.out.println(_plus_2);
      System.setProperty("output_folder", args[1]);
      VideoGenConfigs.initSubOutPutFolders();
      Request request = Request.method(VideoGenPlayTransformationsTest.class, choices.get(args[0]));
      final Result result = jUnitCore.run(request);
      List<Failure> _failures = result.getFailures();
      for (final Failure failure : _failures) {
        System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
    }
  }
}
