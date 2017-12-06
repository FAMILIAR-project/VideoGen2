package loic_beaulieu_xtext_project.java.media;

public interface IdFile {
	String getId();
	String getPath();
	double getProba();
	
	void setProba(double proba);

	long getSizeVideo();
}
