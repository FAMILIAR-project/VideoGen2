package loic_beaulieu_xtext_project.java.media.video;

import java.io.File;

import loic_beaulieu_xtext_project.java.Constante;
import loic_beaulieu_xtext_project.java.media.IdFile;

public class IdVideo implements IdFile {

	private String id;
	private String path;
	private long sizeVideo;
	private double proba;
	private Integer duration;
	private String description;
	
	private boolean inBlackAndWhite;
	private boolean inNegateFilter;
	private String flipFilter;
	
	private TextVideo videoText;
	
	public IdVideo(String id, String path, double proba, Integer duration, String description, TextVideo videoText) {
		super();
		this.id = id;
		this.path = Constante.originalPathDirectory+"/"+path;
		this.proba = proba;
		this.duration = duration;
		this.description = description;
		this.videoText = videoText;
	}
	

	public TextVideo getVideoText() {
		return videoText;
	}


	public void setVideoText(TextVideo videoText) {
		this.videoText = videoText;
	}


	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isInBlackAndWhite() {
		return inBlackAndWhite;
	}

	public void setInBlackAndWhite(boolean inBlackAndWhite) {
		this.inBlackAndWhite = inBlackAndWhite;
	}

	public boolean isInNegateFilter() {
		return inNegateFilter;
	}

	public void setInNegateFilter(boolean inNegateFilter) {
		this.inNegateFilter = inNegateFilter;
	}

	public String getFlipFilter() {
		return flipFilter;
	}

	public void setFlipFilter(String flipFilter) {
		this.flipFilter = flipFilter;
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
		return "IdVideo [path=" + path + "]";
	}


	@Override
	public boolean isVideo() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isImage() {
		// TODO Auto-generated method stub
		return false;
	}
	
	



	
}
