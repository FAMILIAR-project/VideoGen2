public class VideoGenMedia {
	
	public static final int TYPE_VIDEO = 0;
	public static final int TYPE_IMAGE = 1;
	
	long taille = 0;
	String name;
	boolean optional = false;
	int type;
	VideoGenMedia nextMedia;

	public VideoGenMedia() {
		this.name = "";
	}
	
	public VideoGenMedia(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	public long getTaille() {
		return this.taille;
	}

	public void setTaille(long taille) {
		this.taille = taille;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public VideoGenMedia getNextMedia() {
		return nextMedia;
	}

	public void setNextMedia(VideoGenMedia nextMedia) {
		this.nextMedia = nextMedia;
	}
}
