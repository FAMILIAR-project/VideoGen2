import java.io.IOException;
import java.util.List;

public class FfmpegHelper {
	
	//ffmpeg command to convert png to mp4
	private static final String FFMPEG_CONVERT_PNG_TO_VIDEO_PART1 = "ffmpeg -y -loop 1 -f image2 -i ";
	private static final String FFMPEG_CONVERT_PNG_TO_VIDEO_PART2 = ".png -t 2 ";
	private static final String FFMPEG_CONVERT_PNG_TO_VIDEO_PART3 = ".mp4";
	//exemple : ffmpeg -loop 1 -f image2 -i catday.png -t 10 out.mp4

	//ffmpeg command to convert mp4 to ts for concatenation
	private static final String FFMPEG_PREPARE_MP4_PART1 = "ffmpeg -y -i ";
	private static final String FFMPEG_PREPARE_MP4_PART2 = ".mp4 -an -c copy -bsf:v h264_mp4toannexb -f mpegts ";
	private static final String FFMPEG_PREPARE_MP4_PART3 = ".ts";
	//exemple : ffmpeg -y -i "out.mp4" -c copy -bsf:v h264_mp4toannexb -f mpegts "source1.ts"

	//ffmpeg command to concatenate several mp4 videos
	private static final String FFMPEG_CONCAT_TS_TO_MP4_PART1 = "ffmpeg -y -i concat:";
	private static final String FFMPEG_CONCAT_TS_TO_MP4_PART2 = ".ts|";
	private static final String FFMPEG_CONCAT_TS_TO_MP4_PART3 = ".ts -an -absf aac_adtstoasc -strict -2 -f mov ";
	private static final String FFMPEG_CONCAT_TS_TO_MP4_PART4 = ".gif";
	//exemple : ffmpeg -y -i concat:"source1.ts|source2.ts" -absf aac_adtstoasc -f mov "destination.mp4"

		

	//convert png to video using ffmpeg
	public static void convertPngToMp4(String mediaName) throws IOException, InterruptedException {
		String command = FFMPEG_CONVERT_PNG_TO_VIDEO_PART1 + mediaName + FFMPEG_CONVERT_PNG_TO_VIDEO_PART2 + mediaName + FFMPEG_CONVERT_PNG_TO_VIDEO_PART3;
		Process execCommand = Runtime.getRuntime().exec(command);
		execCommand.waitFor();
	}
	
	//convert mp4 to ts using ffmpeg
	public static void prepareMp4ToConcat(String mediaName) throws IOException, InterruptedException {
		String command = FFMPEG_PREPARE_MP4_PART1 + mediaName + FFMPEG_PREPARE_MP4_PART2 + mediaName + FFMPEG_PREPARE_MP4_PART3;
		Process execCommand = Runtime.getRuntime().exec(command);
		execCommand.waitFor();
	}
	
	//concat several ts to mp4 using ffmpeg
	public static void concatTsToMp4(List<String> in, String out) throws IOException, InterruptedException {
		String command = FFMPEG_CONCAT_TS_TO_MP4_PART1;
		for(int i = 0; i<in.size(); i++) {
			if(i == in.size()-1) {
				command += in.get(i) + FFMPEG_CONCAT_TS_TO_MP4_PART3;
			} else {
				command += in.get(i) + FFMPEG_CONCAT_TS_TO_MP4_PART2;
			}
		}
		command += out + FFMPEG_CONCAT_TS_TO_MP4_PART4;
		Process execCommand = Runtime.getRuntime().exec(command);
		execCommand.waitFor();
	}
}
