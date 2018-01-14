package loic_beaulieu_xtext_project.java.media.image;

import java.io.File;

import loic_beaulieu_xtext_project.java.Constante;
import loic_beaulieu_xtext_project.java.media.IdFile;

public class IdImage implements IdFile {

	private String id;
	private String path;
	private long sizeVideo;
	private double proba;
	
	private String topText;
	private String botText;
	

	
	public IdImage(String id, String path, double proba, String topText, String botText) {
		super();
		this.id = id;
		this.path = Constante.originalPathDirectory+"/"+path;
		this.proba = proba;
		this.topText = topText;
		this.botText = botText;
	}

	public String getTopText() {
		return topText;
	}

	public void setTopText(String topText) {
		this.topText = topText;
	}

	public String getBotText() {
		return botText;
	}

	public void setBotText(String botText) {
		this.botText = botText;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getSizeVideo() {
		return sizeVideo;
	}
	public void setSizeVideo(long sizeVideo) {
		this.sizeVideo = sizeVideo;
	}
	public double getProba() {
		return proba;
	}
	public void setProba(double proba) {
		this.proba = proba;
	}

	@Override
	public String toString() {
		return "IdImage [path=" + path + "]";
	}

	@Override
	public boolean isVideo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isImage() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
