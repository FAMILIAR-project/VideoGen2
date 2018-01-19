import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {
	/**
	 * get the video file duration using ffmpeg
	 * 
	 * @param path
	 *            to the file
	 * @return the duration of the video
	 */
	static float getFileDuration(String path) {
		String command;
		String startcommand;
		Process process;
		@SuppressWarnings("unused")
		BufferedReader stdInput;
		String s;
		BufferedReader stdError;
		startcommand = "ffmpeg -i ";
		command = "";

		s = startcommand + path + command;
		// s="cat /home/kristof/Bureau/TPIDM/Video/duree"+command
		// System.out.println(s);
		try {
			process = Runtime.getRuntime().exec(s);
			stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			// while ((s = stdInput.readLine()) != null) {
			// System.out.println("//"+s);
			// }
			while ((s = stdError.readLine()) != null) {
				if (s.indexOf("Duration") >= 0) {
					return parseDuration(s);
				}
				// System.out.println("Err:"+s);
			}
			stdError.close();
			stdInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// startcommand+alter.location+command
		// println(startcommand+alter.location+command)

		// process.getOutputStream().toString

		return 0;

	}

	/**
	 * Parsing the duration
	 * 
	 * @param s
	 * @return
	 */
	private static float parseDuration(String s) {
		// System.out.println("parsingggggggggggggggg"+s);
		String tmp = s.substring(s.indexOf(": ") + 2, s.indexOf(", s"));
		// System.out.println(tmp);

		String strh = tmp.substring(0, tmp.indexOf(":"));
		// System.out.println("h="+ strh);
		int h = Integer.parseInt(strh);
		// System.out.println(h);

		String strmin = tmp.substring(tmp.indexOf(":") + 1, tmp.lastIndexOf(":"));
		// System.out.println("m="+strmin);
		int m = Integer.parseInt(strmin);
		// System.out.println(m);

		String strsec = tmp.substring(tmp.lastIndexOf(":") + 1);
		// System.out.println("sec="+strsec);
		float sec = Float.parseFloat(strsec);
		// System.out.println(sec);
		return h * 3600 + m * 60 + sec;
	}
}
