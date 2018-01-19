package soumiaa_videogen.java.media;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import soumiaa_videogen.java.Constante;
import soumiaa_videogen.java.media.image.IdImage;
import soumiaa_videogen.java.media.video.IdVideo;
import soumiaa_videogen.java.utils.UtilsFfmpeg;

public class VarianteVideo {

	private double proba;
	private List<IdFile> listIdFile;
	
	public VarianteVideo() {
		proba = 1;
		listIdFile = new ArrayList<>();
	}
	
	public void addFile(IdFile file) {
		listIdFile.add(file);
	}

	public double getProba() {
		return proba;
	}

	public void setProba(double proba) {
		this.proba = proba;
	}

	
	public VarianteVideo copy() {
		VarianteVideo copy = new VarianteVideo();
		
		for (IdFile idFile : listIdFile) {
			copy.addFile(idFile);
		}
		copy.proba = this.proba;
		
		return copy;
	}
	
	/**
	 * génère la video en fonction de sa liste de video/image
	 * @return
	 * @throws IOException
	 */
	public String generateVideo() throws IOException {

		String stringForMd5 = "";
		for (IdFile idFile : listIdFile) {
			File file = new File(idFile.getPath());
			if (!file.isFile()) {
				throw new IOException(file.getAbsolutePath()+" is not a file");
			}
			if (!file.exists()) {
				throw new IOException(file.getAbsolutePath()+" doesn't exist !");
			}
			
			stringForMd5 += idFile.getPath();
		}
		
		String md5 = stringForMd5.hashCode()+"";
		
		
		String pathToFinalVideoGenerated = md5+".mp4";
		if (new File(pathToFinalVideoGenerated).exists()) {
			return pathToFinalVideoGenerated;
		}
		
		
		List<String> videoToConcat = new ArrayList<>();
		for (IdFile idFile : listIdFile) {
			String pathVideo = idFile.getPath();
			if (idFile.isImage()) {
				IdImage image = (IdImage) idFile;
				pathVideo = UtilsFfmpeg.imageToVideo(image.getPath(),image.getTopText(),image.getBotText());
			}else {
				IdVideo video = (IdVideo) idFile;
			}	
			videoToConcat.add(pathVideo);
		}
		
		String pathToFinalVideo = UtilsFfmpeg.concatListVideo(videoToConcat, pathToFinalVideoGenerated);

		if (!new File(pathToFinalVideo).exists()) {
			throw new IOException("La concaténation à échoué pour une raison inconnue");
		}
		
		return pathToFinalVideo;
	}

	@Override
	public String toString() {
		return "VarianteVideo [proba=" + proba + ", listIdFile=" + listIdFile + "]";
	}
	
	
}
