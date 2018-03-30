package fr.istic.m2il.idm.videogentransformations.helpers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;

/**
 * @author Ramadan Soumaila
 * A helper class to execute command
 */
@SuppressWarnings("all")
public class ProcessHelper {
  /**
   * Executes a list of command son the system
   * @param the list of commands to executes
   */
  public static void execute(final List<String> commands) {
    try {
      final ProcessBuilder processBuilder = new ProcessBuilder(commands);
      processBuilder.inheritIO();
      final Process process = processBuilder.start();
      process.waitFor();
      process.destroy();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Executes a list of command son the system
   * @param the list of commands to executes
   * @return a array of output stream
   */
  public static String[] executeAndGetIOStream(final List<String> commands) {
    try {
      final ProcessBuilder processBuilder = new ProcessBuilder(commands);
      processBuilder.redirectErrorStream(true);
      final Process process = processBuilder.start();
      InputStream _inputStream = process.getInputStream();
      InputStreamReader _inputStreamReader = new InputStreamReader(_inputStream);
      final BufferedReader bufferReader = new BufferedReader(_inputStreamReader);
      String line = "";
      line = null;
      final ArrayList<String> IOStream = new ArrayList<String>();
      while (((line = bufferReader.readLine()) != null)) {
        IOStream.add(line);
      }
      process.waitFor();
      process.destroy();
      return ((String[])Conversions.unwrapArray(IOStream, String.class));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
