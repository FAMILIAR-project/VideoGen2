package soumiaa_videogen.java.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import soumiaa_videogen.java.Constante;


public class UtilsFfmpeg {	
	
	/**
	 * tranforme une image en video mp4 de 3 seconde
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static final String imageToVideo(String path, String topText, String botText) throws IOException {
		File file = new File(path);
		
		if (!file.exists() || !file.isFile()) {
			throw new IOException();
		}
		
	
		String newName = Constante.tmpDirectoryImg+"/"+getNameWithoutExtension(path)+".mp4";
	
		if (!new File(newName).exists()) {
			//String command = "ffmpeg -loop 1 -i " + path + " -f lavfi -i anullsrc=r=48000:cl=stereo -vf scale=\"800:-2\" -t 3 -strict -2 -y " + newName;;
			String command = "ffmpeg -loop 1 -f image2 -i "+path+" -vf scale=\"800:-2\" -t 3 -strict -2 -y "+newName;
			
			Process p = Runtime.getRuntime().exec(command);
			System.out.println(command);
			clearBufferExecute(p);
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return newName;
	}
	
	/**
	 * convertie une vidéo en mp4
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static final String convertToMp4(String path) throws IOException {
		File file = new File(path);
		
		if (!file.exists() || !file.isFile()) {
			throw new IOException();
		}
		String newName = Constante.tmpDirectory+"/f_"+getNameWithoutExtension(path)+".mp4";
		
		
		String command = "ffmpeg -i "+path+" -vcodec mpeg4 -strict -2 -acodec aac "+newName;
		System.out.println(command);
		Process p = Runtime.getRuntime().exec(command);
		clearBufferExecute(p);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newName;
	}
	
	/**
	 * convertie une vidéo en une certainne résolution. Cela permet de mettre toute les vidéo au même format et ainsi évité d'avoir des problème plus tard lors de la concaténation
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static final String convertToCorrectResolution(String path) throws IOException {
		File file = new File(path);
		
		if (!file.exists() || !file.isFile()) {
			throw new IOException();
		}
		
		String newName = Constante.tmpDirectory+"/r_"+file.getName();
		
		String command = "ffmpeg -i "+path+" -strict -2 -vf scale=\"800:-2\" "+newName+"  -y -hide_banner\n";
		Process p = Runtime.getRuntime().exec(command);
		System.out.println(command);
		clearBufferExecute(p);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newName;
	}
	
	/**
	 * convertie une video en mp4 et la mets dans une bonne résolution pour pouvoir avoir des vidéos de même format et de même résolution lors de la concaténation
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String getNormalizedFormat(String path) throws IOException{
		//String mp4Format = convertToMp4(path);
		//String correctResolution = convertToCorrectResolution(path);
		return path;
	}
	
	/**
	 * concatène 2 vidéos
	 * @param path1
	 * @param path2
	 * @return
	 * @throws IOException
	 */
	public static String concatListVideo(List<String> pathVideo, String nameResult) throws IOException {
		for (String path : pathVideo) {
			File file = new File(path);
			if (!file.exists() || !file.isFile()) {
				throw new IOException("Le fichier "+file.getPath()+" n'existe pas");
			}
		}
		
		
		
		File fileListConcat = new File(Constante.tmpDirectory+"/"+getNameWithoutExtension(nameResult)+".txt");
		if (!fileListConcat.exists()) {
			fileListConcat.createNewFile();
			
			PrintWriter writer = new PrintWriter(fileListConcat, "UTF-8");
			for (String path : pathVideo) {
				writer.println("file '../../"+path+"'");
			}
			writer.close();
		}
		
		String resultConcat = Constante.outputVideoGenerated+"/"+nameResult;
		
		String command = "ffmpeg -f concat -safe 0 -i " + fileListConcat + " -c copy -y "+resultConcat;
		
		
		System.out.println(command);
		
		Process p = Runtime.getRuntime().exec(command);
		clearBufferExecute(p);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultConcat;
	}
	
	/**
	 * renvois l'extension d'un fichier
	 * @param path
	 * @return
	 */
	public static String getExtension(String path) {
		String extension = "";

		int i = path.lastIndexOf('.');
		if (i > 0) {
		    extension = path.substring(i+1);
		}
		return extension;
	}
	
	/**
	 * retourne le nom du fichier sans son extension
	 * @param path
	 * @return
	 */
	public static String getNameWithoutExtension(String path) {
		return new File(path).getName().replaceFirst("[.][^.]+$", "");
	}
	

	private static void clearBufferExecute(Process proc) {
		new Thread(()->{
			InputStream errStream = proc.getErrorStream();
			try {
				while(errStream.read() != -1) {
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		
		new Thread(()->{
			InputStream stdStream = proc.getInputStream();
			try {
				while(stdStream.read() != -1) {
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
	}
}
