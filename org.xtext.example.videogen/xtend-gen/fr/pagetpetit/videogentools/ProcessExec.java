package fr.pagetpetit.videogentools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class ProcessExec {
  public static void executeCommand(final String[] command) {
    try {
      final ProcessBuilder builder = new ProcessBuilder(command);
      builder.inheritIO();
      final Process process = builder.start();
      process.waitFor();
      process.destroy();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static String[] executeCommandIO(final String[] command) {
    try {
      final ProcessBuilder builder = new ProcessBuilder(command);
      builder.redirectErrorStream(true);
      final Process process = builder.start();
      InputStream _inputStream = process.getInputStream();
      InputStreamReader _inputStreamReader = new InputStreamReader(_inputStream);
      final BufferedReader reader = new BufferedReader(_inputStreamReader);
      String line = "";
      line = null;
      final ArrayList<String> IO = new ArrayList<String>();
      while (((line = reader.readLine()) != null)) {
        IO.add(line);
      }
      process.waitFor();
      process.destroy();
      return ((String[])Conversions.unwrapArray(IO, String.class));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
