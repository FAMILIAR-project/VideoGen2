package ffmpegUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FfmpegUtils {

	public static String mkFFMPEGPlaylist(String mpegPlaylistFile, String outputPath) {
		return "ffmpeg -y -f concat -safe 0 -i " + mpegPlaylistFile + "" + " -c copy " + outputPath;
	}
	
	public static String mkFFMPEGConvertVideo(String inputVideo, String outputVideo) {
		return "ffmpeg -y -i "+ inputVideo +" -vf scale=film " + outputVideo;
	}	
	
	public static String mkFFMPEGConvertImage(String inputImage, String outputVideo) {
		return "ffmpeg -y -loop 1 -i " + inputImage + " -c:v libx264 -t 10 -pix_fmt yuv420p " + outputVideo;
	}
	
	public static String mkFFMPEGFilerGrayscale(String inputVideo, String outputVideo) {
		return "ffmpeg -y -i " + inputVideo + " -strict -2 -vf hue=s=0 " + outputVideo;
	}
	
	public static String mkFFMPEGFilerNegate(String inputVideo, String outputVideo) {
		return "ffmpeg -y -i " + inputVideo + " -strict -2 -af areverse -vf reverse " + outputVideo;
	}
	
	public static String mkFFMPEGFilerFlipHorizontal(String inputVideo, String outputVideo) {
		return "ffmpeg -y -i " + inputVideo + " -strict -2 -vf hflip " + outputVideo;
	}
	
	public static String mkFFMPEGFilterFlipVertical(String inputVideo, String outputVideo) {
		return "ffmpeg -y -i " + inputVideo + " -strict -2 -vf vflip " + outputVideo;
	}
	
	public static String mkFFMPEGAddText(String inputVideo, String outputVideo, String text, String color,String size,String relativePosX,String relativePosY) {
		return "ffmpeg -y -i " + inputVideo + " -vf drawtext=fontfile=/usr/share/fonts/truetype/ubuntu-font-family/UbuntuMono-R.ttf:text='" 
					+ text + "':fontcolor=" + color + ":fontsize=" + size + ":x=((w-text_w)/100)*" + relativePosX 
					+ ":y=((h-text_h)/100)*" + relativePosY + " -codec:a copy "+ outputVideo;
	}
	
	public static void runCommand(String command) {
		String commandOutput = new String("");
		System.out.println("Execute : " + command);

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				commandOutput += s;
			}
			p.waitFor();
			if(p.exitValue()!=0){
				System.out.println("Error : "+commandOutput);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
