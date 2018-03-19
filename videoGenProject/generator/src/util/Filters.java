package util;

import java.io.IOException;

import org.xtext.example.mydsl.videoGen.BlackWhiteFilter;
import org.xtext.example.mydsl.videoGen.Filter;
import org.xtext.example.mydsl.videoGen.FlipFilter;
import org.xtext.example.mydsl.videoGen.NegateFilter;
import org.xtext.example.mydsl.videoGen.VideoText;

public class Filters {
	static Runtime rt = Runtime.getRuntime();

	/**
	 * gestion des filtres
	 * @param filter
	 * @param path
	 * @param generetedFolder permet de savoir si le fichier a traiter et dans le dossier generetedVideos
	 * @return
	 */
	public static String filter(Filter filter, String path, boolean generetedFolder) {
		if (filter != null) {
			String res = "";
			System.out.println("le path dans filter = " + path);
			if (filter instanceof FlipFilter) {
				FlipFilter flip = (FlipFilter) filter;
				res = flipFilter(path, flip.getOrientation(), generetedFolder);
			} else if (filter instanceof BlackWhiteFilter) {
				res = blackWhiteFilter(path, generetedFolder);
			}
			else if(filter instanceof NegateFilter){
				res = negateFilter(path, generetedFolder);
			}
			return res;
		}
		return path;
	}

	/**
	 * cree un nouveau fichier en appliquant le filter negate
	 * 
	 * @param path
	 * @return
	 */
	public static String negateFilter(String path,boolean generatedFolder) {
		String outputPath = "";
		if (generatedFolder) {
			outputPath = path.replace(".mp4", "_neg.mp4");
		}
		else outputPath = path.replace(".mp4", "_neg.mp4").replace("videos/", "generatedVideos/");
		String cmd = "ffmpeg -y -i " + path + " -vf lutrgb=r=negval:g=negval:b=negval -c:a ac3 -strict -2 " + outputPath;
		System.out.println(cmd);
		try {
			rt.exec(cmd).waitFor();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputPath;
	}

	// appliquer le filter b&w au fichier a l'adresse path
	// retourne l'adresse du fichier filtré
	public static String blackWhiteFilter(String path, boolean generetedFolder) {
		String outputPath = "";
		if (generetedFolder) {
			outputPath = path.replace(".mp4", "_bw.mp4");
		}
		else outputPath = path.replace(".mp4", "_bw.mp4").replace("videos/", "generatedVideos/");
		//String cmd = "ffmpeg -y  -i  " + path + " -pix_fmt gray -vcodec rawvideo " + outputPath;
		String cmd = "ffmpeg -y  -i  " + path + " -vf hue=s=0 -c:a ac3 -strict -2 " + outputPath;
		System.out.println(cmd);
		try {
			rt.exec(cmd).waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputPath;
	}

	// FlipFilter, retourne le path du fichier generé
	public static String flipFilter(String path, String type, boolean generatedFolder) {
		String outputPath = "";
		if (generatedFolder) {
			outputPath = path.replace(".mp4", "_"+type.substring(0, 1)+"flip.mp4");
		}
		else outputPath = path.replace(".mp4", "_"+type.substring(0, 1)+"flip.mp4").replace("videos/", "generatedVideos/");
		System.out.println("flip flop "+ type + "  path = " + path);
		String cmd = "";
		if (type.equals("h") || type.equals("horizontal")) {
			cmd = "ffmpeg -i " + path + " -vf hflip -strict -2 " + outputPath;
			System.out.println(cmd);
		} else if (type.equals("v") || type.equals("vertical")) {
			cmd = "ffmpeg -i " + path + " -vf vflip  -strict -2 " + outputPath;
			System.out.println(cmd);
		} else {
			System.err.println("Flip de type " + type + " n'est pas reconnait");
			return null;
		}
		try {
			Runtime.getRuntime().exec(cmd).waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputPath;
	}

	// genere une video avec un text incrusté
	public static String addText(String path, VideoText args, boolean generatedFolder) {
		if (args != null) {
			String outputPath = "";
			if (generatedFolder) {
				outputPath = path.replace(".mp4", "_text.mp4");
			}
			else outputPath = path.replace(".mp4", "_text.mp4").replace("videos/", "generatedVideos/");
		
			String color = "white";
			int size = 20;
			String y = "";
			String content = "'"+args.getContent()+"'";
			switch (args.getPosition()) {
			case "TOP":
				y = "5";
				break;
			case "BOTTOM":
				y = "(h-text_h-line_h)";
				break;
			case "CENTER":
				y = "(h-text_h-line_h)/2";
				break;
			default:
				System.err.println("position inconnue!");
				return null;
			}

			if (args.getColor() != null)
				color = args.getColor();
			if (args.getSize() > 0)
				size = args.getSize();
			String cmd = "ffmpeg -i " + path + " -vf " + '"' + "drawtext=fontsize=" + size + ":fontcolor=" + color
					+ ": fontfile=/usr/share/fonts/truetype/freefont/FreeSerif.ttf" + ":text=" + "'" + content + "'"
					+ ":x=(w-text_w)/2:y=" + y + '"' + " -strict -2 " + outputPath;
			String[] b = new String[] {"/bin/sh", "-c", cmd};
			System.out.println(cmd);
			//String cmd = "ffmpeg -y -i "+path+" -vf drawtext="+content+" -c:a ac3 "+outputPath;
			try {
				rt.exec(b).waitFor();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);

			}
			return outputPath;
		}
		return path;

	}

}
