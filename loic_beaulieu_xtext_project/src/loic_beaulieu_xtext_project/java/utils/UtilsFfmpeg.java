package loic_beaulieu_xtext_project.java.utils;

import java.io.File;
import java.io.IOException;


public class UtilsFfmpeg {
	
	public static final String convertToMp4(String path) throws IOException {
		File file = new File(path);
		
		if (!file.exists() || !file.isFile()) {
			throw new IOException();
		}
		String newName = "output_video/format_"+getNameWithoutExtension(path)+".mp4";
		
		
		String command = "ffmpeg -i "+path+" -vcodec copy -acodec copy -y "+newName;
		System.out.println(command);
		Process p = Runtime.getRuntime().exec(command);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newName;
	}
	
	public static final String convertToCorrectResolution(String path) throws IOException {
		File file = new File(path);
		
		if (!file.exists() || !file.isFile()) {
			throw new IOException();
		}
		
		String newName = "output_video/reso_"+file.getName();
		
		String command = "ffmpeg -i "+path+" -vf scale=320:240 -strict -2 -y "+newName;
		System.out.println(command);
		System.out.println(command);
		
		Process p = Runtime.getRuntime().exec(command);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newName;
	}
	
	public static String getNormalizedFormat(String path) throws IOException{
		return convertToCorrectResolution(convertToMp4(path));
	}
	
	
	public static String concatTwoVideo(String path1, String path2) throws IOException {
		File file1 = new File(path1);
		
		if (!file1.exists() || !file1.isFile()) {
			throw new IOException();
		}
		File file2 = new File(path2);
		
		if (!file2.exists() || !file2.isFile()) {
			throw new IOException();
		}
		String newName1 = getNormalizedFormat(path1);
		String newName2 = getNormalizedFormat(path2);
		
		String resultConcat = "output_video/concat"+getNameWithoutExtension(newName1)+"_"+getNameWithoutExtension(newName2)+".mp4";
		
		String command = "ffmpeg -i 'concat:"+newName1+"|"+newName2+"' -codec copy -y "+resultConcat;
		System.out.println(command);
		Process p = Runtime.getRuntime().exec(command);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultConcat;
	}
	
	
	public static String getExtension(String path) {
		String extension = "";

		int i = path.lastIndexOf('.');
		if (i > 0) {
		    extension = path.substring(i+1);
		}
		return extension;
	}
	
	public static String getNameWithoutExtension(String path) {
		return new File(path).getName().replaceFirst("[.][^.]+$", "");
	}
	

}
