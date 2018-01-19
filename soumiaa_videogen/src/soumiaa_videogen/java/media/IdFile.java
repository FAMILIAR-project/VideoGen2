package soumiaa_videogen.java.media;

public interface IdFile {
	String getId();
	String getPath();
	double getProba();
	
	boolean isVideo();
	boolean isImage();
	
	void setProba(double proba);

	long getSizeVideo();
}
