import java.io.File;

public final class VideoGenMandatoryMedia extends VideoGenMedia {

	private File file;
	
	public VideoGenMandatoryMedia(String name, int type) {
		super(name, type);
		this.file = new File(name);
		this.taille = this.file.length();
	}

}
