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
    choices.put("generer_playlist", "generateRandomPlayList");
    choices.put("exporter_gifs", "videoToGif");
    choices.put("tailles_variantes", "checksCSVLinesNumber");
    choices.put("durees_variantes", "checksCSVLinesNumber");
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
          case "tailles_variantes":
            String _get_1 = args[3];
            boolean _tripleEquals = (_get_1 == null);
            if (_tripleEquals) {
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
      String output = args[1].replace("\\", "/").replaceAll("/$", "");
      VideoGenConfigs.setOutPutFolder(output);
      System.setProperty("output_folder", output);
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
