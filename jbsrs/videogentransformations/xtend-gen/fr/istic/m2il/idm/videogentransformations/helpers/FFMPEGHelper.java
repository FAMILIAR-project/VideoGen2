package fr.istic.m2il.idm.videogentransformations.helpers;

import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs;
import fr.istic.m2il.idm.videogentransformations.helpers.ProcessHelper;
import fr.istic.m2il.idm.videogentransformations.utils.CommonUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.xtext.example.mydsl.videoGen.MediaDescription;

/**
 * @author Ramadan Soumaila
 * A helper class to execute ffmeg commands
 */
@SuppressWarnings("all")
public class FFMPEGHelper {
  /**
   * Creates the thumb of a video/image
   * @param location the location of video/image
   * @return a string which represent the location of generated thumbs if execution is successful, or "" otherwise
   */
  public static String generateThumbnail(final String location) {
    String _xblockexpression = null;
    {
      final ArrayList<String> command = new ArrayList<String>();
      command.add("ffmpeg");
      command.add("-y");
      command.add("-i");
      command.add(location);
      command.add("-r");
      command.add("1");
      command.add("-t");
      command.add("00:00:01");
      command.add("-ss");
      double _random = Math.random();
      int _round = Math.round(Float.parseFloat(FFMPEGHelper.getVideoDurationString(location).split(":")[2]));
      double _multiply = (_random * _round);
      String _plus = ("00:00:0" + Double.valueOf(_multiply));
      command.add(_plus);
      command.add("-f");
      command.add("image2");
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus_1 = (_outPutFoulder + "/thumbs/thumb.png");
      String outputFile = CommonUtils.getOutPutFileName(_plus_1);
      command.add(outputFile);
      ProcessHelper.execute(command);
      _xblockexpression = outputFile;
    }
    return _xblockexpression;
  }
  
  /**
   * Creates a video by concatenate several
   * @param outputfilename the location of generated video
   * @param files the list of videos's locations
   * @return a string which represent the location of generated video if execution is successful, or "" otherwise
   */
  public static String concatVideos(final List<String> files, final String outputfilename) {
    final ArrayList<String> command = new ArrayList<String>();
    String filter = "";
    command.add("ffmpeg");
    command.add("-y");
    int _size = files.size();
    String _plus = ("fi " + Integer.valueOf(_size));
    InputOutput.<String>println(_plus);
    int i = 0;
    for (final String file : files) {
      {
        command.add("-i");
        InputOutput.<String>println(("fi " + file));
        command.add(file);
        if (((((file.replace(".", "@").split("@")[1].equals("jpg") || 
          file.replace(".", "@").split("@")[1].equals("png")) || 
          file.replace(".", "@").split("@")[1].equals("gif")) || 
          file.replace(".", "@").split("@")[1].equals("bpm")) || 
          file.replace(".", "@").split("@")[1].equals("tiff"))) {
          String _filter = filter;
          filter = (_filter + (("[" + Integer.valueOf(i)) + ":v:0][0]"));
        } else {
          String _filter_1 = filter;
          filter = (_filter_1 + (((("[" + Integer.valueOf(i)) + ":v:0][") + Integer.valueOf(i)) + ":a:0]"));
        }
        i++;
      }
    }
    command.add("-filter_complex");
    String _filter = filter;
    filter = (_filter + (("concat=n=" + Integer.valueOf(i)) + ":v=1:a=1[outv][outa]"));
    command.add(filter);
    command.add("-map");
    command.add("[outv]");
    command.add("-map");
    command.add("[outa]");
    command.add("-strict");
    command.add("-2");
    command.add(outputfilename);
    ProcessHelper.execute(command);
    return outputfilename;
  }
  
  /**
   * Gets a video/image resolution
   * @param videoFile the location of video/image
   * @return a array of int which contains the width at first index,
   * and height at second index if execution is successful, or null otherwise
   */
  public static int[] getVideoResolution(final String videoFile) {
    final ArrayList<String> command = new ArrayList<String>();
    command.add("ffmpeg");
    command.add("-i");
    command.add(videoFile);
    final String[] IO = ProcessHelper.executeAndGetIOStream(command);
    final ArrayList<Integer> resolution = new ArrayList<Integer>();
    for (final String line : IO) {
      boolean _contains = line.contains("Stream #0:0");
      if (_contains) {
        Pattern pattern = Pattern.compile(",\\s[0-9]+x[0-9]+");
        Matcher matcher = pattern.matcher(line);
        boolean _find = matcher.find();
        if (_find) {
          final String[] res = matcher.group(0).replace(", ", "").split("x");
          resolution.add(Integer.valueOf(Integer.parseInt(res[0])));
          resolution.add(Integer.valueOf(Integer.parseInt(res[1])));
        }
      }
    }
    return ((int[])Conversions.unwrapArray(resolution, int.class));
  }
  
  /**
   * Changes the resolution of a video/image if the input resolution over the output resolution
   * @param filename location of video/image
   * @param inputWidth the input width resolution to set
   * @param inputHeight the input height resolution to set
   * @param outWidthWidth the output width resolution to set
   * @param outputHeight the output width resolution to set
   * @return a string which represent the location of generated thumbs if execution is successful, or "" otherwise
   */
  public static String homogenizeMediaResolution(final String filename, final int inputWidth, final int inputHeight, final int outputWidth, final int outputHeight) {
    if (((inputWidth == outputWidth) && (inputHeight == outputHeight))) {
      return filename;
    }
    String[] file = filename.replace(".", "@#").split("@#");
    ArrayList<String> command = new ArrayList<String>();
    command.add("ffmpeg");
    command.add("-i");
    command.add(filename);
    command.add("-strict");
    command.add("-2");
    command.add("-vf");
    if (((((file[1].equals("jpg") || file[1].equals("png")) || file[1].equals("bpm")) || file[1].equals("tiff")) || file[1].equals("gif"))) {
      command.add(((("scale=" + Integer.valueOf(outputWidth)) + ":") + Integer.valueOf(outputHeight)));
    } else {
      command.add(((((((("pad=" + Integer.valueOf(outputWidth)) + ":") + Integer.valueOf(outputHeight)) + ":") + Integer.valueOf(((outputWidth - inputWidth) / 2))) + ":") + Integer.valueOf(((outputHeight - inputHeight) / 2))));
    }
    File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
    String _plus = (_outPutFoulder + "/resizes/");
    String _get = IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(new File(filename).getAbsolutePath().replace("\\", "/").split("/")))).replace(".", "@").split("@")[0];
    String _plus_1 = (_plus + _get);
    String _plus_2 = (_plus_1 + "_o.");
    String _get_1 = file[1];
    String _plus_3 = (_plus_2 + _get_1);
    final String outputFile = CommonUtils.getOutPutFileName(_plus_3);
    command.add(outputFile);
    ProcessHelper.execute(command);
    return outputFile;
  }
  
  /**
   * Gets a video's duration of a playlist
   * 
   * @param playlist a list of media description
   * @return a int value of the playlist duration if the command is successful, or 0 otherwise
   */
  public static int getVideoDuration(final List<MediaDescription> playlist) {
    int _xblockexpression = (int) 0;
    {
      int duration = 0;
      int i = 0;
      for (final MediaDescription media : playlist) {
        {
          if ((media != null)) {
            InputOutput.<String>println(("I " + Integer.valueOf(i)));
            int _duration = duration;
            int _videoDuration = FFMPEGHelper.getVideoDuration(media.getLocation());
            duration = (_duration + _videoDuration);
          }
          i++;
        }
      }
      InputOutput.<String>println(("Duration " + Integer.valueOf(duration)));
      _xblockexpression = duration;
    }
    return _xblockexpression;
  }
  
  /**
   * Gets a video's duration
   * 
   * @param videolocation the location of video
   * @return a int value of the video duration if the command is successful, or 0 otherwise
   */
  public static int getVideoDuration(final String videoLocation) {
    int _xblockexpression = (int) 0;
    {
      final ArrayList<String> command = new ArrayList<String>();
      command.add("ffmpeg");
      command.add("-i");
      command.add(videoLocation);
      final String[] IO = ProcessHelper.executeAndGetIOStream(command);
      int duration = 0;
      for (final String line : IO) {
        boolean _contains = line.contains("Duration:");
        if (_contains) {
          final String stringduration = line.trim().split(",")[0].split(" ")[1];
          int _round = Math.round(Float.parseFloat(stringduration.split(":")[0]));
          int _multiply = (3600 * _round);
          int _round_1 = Math.round(Float.parseFloat(stringduration.split(":")[1]));
          int _multiply_1 = (60 * _round_1);
          int _plus = (_multiply + _multiply_1);
          int _round_2 = Math.round(Float.parseFloat(stringduration.split(":")[2]));
          int _plus_1 = (_plus + _round_2);
          duration = _plus_1;
        }
      }
      _xblockexpression = duration;
    }
    return _xblockexpression;
  }
  
  /**
   * Gets a video's duration of a video in string(formated)
   * 
   * @param videoLocation the location of video
   * @return a formated string value of the video of duration if the command is successful, or 0 otherwise
   */
  public static String getVideoDurationString(final String videoLocation) {
    String _xblockexpression = null;
    {
      final ArrayList<String> command = new ArrayList<String>();
      command.add("ffmpeg");
      command.add("-i");
      command.add(videoLocation);
      final String[] IO = ProcessHelper.executeAndGetIOStream(command);
      String duration = "";
      for (final String line : IO) {
        boolean _contains = line.contains("Duration:");
        if (_contains) {
          duration = line.trim().split(",")[0].split(" ")[1];
        }
      }
      _xblockexpression = duration;
    }
    return _xblockexpression;
  }
  
  /**
   * Gets the gif of a video
   * 
   * @param videoLocation the location of video
   * @return a string value of the gif generated if the command is successful, or 0 otherwise
   */
  public static String videoToGif(final String videoLocation, final int width, final int height) {
    String _xblockexpression = null;
    {
      final ArrayList<String> command = new ArrayList<String>();
      String[] file = videoLocation.replace(".", "@#").split("@#");
      command.add("ffmpeg");
      command.add("-i");
      command.add(videoLocation);
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus = (_outPutFoulder + "/gifs/");
      String _get = IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(new File(videoLocation).getAbsolutePath().replace("\\", "/").split("/")))).replace(".", "@").split("@")[0].split("_")[0];
      String _plus_1 = (_plus + _get);
      String _plus_2 = (_plus_1 + ".gif");
      final String outputFile = CommonUtils.getOutPutFileName(_plus_2);
      command.add("-vf");
      command.add(((("scale=" + Integer.valueOf(width)) + ":") + Integer.valueOf(height)));
      command.add(outputFile);
      command.add("-hide_banner");
      ProcessHelper.execute(command);
      _xblockexpression = outputFile;
    }
    return _xblockexpression;
  }
  
  /**
   * Applies a filter on a video
   * 
   * @param fileter the filter to apply
   * @return a string value of the video generated with filter if the command is successful, or "" otherwise
   */
  public static String applyFilter(final String filter, final String location) {
    String _xblockexpression = null;
    {
      final ArrayList<String> filtercommand = new ArrayList<String>();
      filtercommand.add("ffmpeg");
      filtercommand.add("-i");
      filtercommand.add(location);
      filtercommand.add("-strict");
      filtercommand.add("-2");
      filtercommand.add("-vf");
      filtercommand.add(filter);
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus = (_outPutFoulder + "/filtered/");
      String _last = IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(new File(location).getAbsolutePath().replace("\\", "/").split("/"))));
      String _plus_1 = (_plus + _last);
      String outputFile = CommonUtils.getOutPutFileName(_plus_1);
      filtercommand.add(outputFile);
      ProcessHelper.execute(filtercommand);
      _xblockexpression = outputFile;
    }
    return _xblockexpression;
  }
  
  /**
   * Adds some text on a video
   * @param location the video location
   * @param content the content to write
   * @param size the font size of the content
   * @param color the color of the content
   * @param position the position of content
   * @return a string value of the output location if is successful, or "" otherwise
   */
  public static String addTextToVideo(final String location, final String content, final int size, final String color, final String position) {
    String _xblockexpression = null;
    {
      ArrayList<String> commands = CollectionLiterals.<String>newArrayList();
      String color_ = color;
      if ((color_ == "")) {
        color_ = "white";
      }
      int size_ = size;
      if ((size_ == 0)) {
        size_ = 20;
      }
      String position_ = position;
      if ((position == "")) {
        position_ = "BOTTOM";
      }
      String y = null;
      if (position_ != null) {
        switch (position_) {
          case "TOP":
            y = "5";
            break;
          case "BOTTOM":
            y = "(h-text_h-line_h)";
            break;
          case "CENTER":
            y = "(h-text_h-line_h)/2";
            break;
        }
      }
      commands.add("ffmpeg");
      commands.add("-i");
      commands.add(location);
      commands.add("-strict");
      commands.add("-2");
      commands.add("-vf");
      commands.add(((((((((((("drawtext=fontsize=" + Integer.valueOf(size_)) + ":fontcolor=") + color_) + ":fontfile=FreeSerif.ttf") + ":text=") + "\'") + content) + "\'") + ":x=(w-text_w)/2:y=") + y) + "\'"));
      File _outPutFoulder = VideoGenConfigs.getOutPutFoulder();
      String _plus = (_outPutFoulder + "/filtered/");
      String _last = IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(new File(location).getAbsolutePath().replace("\\", "/").split("/"))));
      String _plus_1 = (_plus + _last);
      String output = CommonUtils.getOutPutFileName(_plus_1);
      commands.add(output);
      ProcessHelper.execute(commands);
      _xblockexpression = output;
    }
    return _xblockexpression;
  }
}
