
public class VideoGenAlternativeMedia extends VideoGenMedia {

	private Medias alternativeMedias = new Medias();

	public VideoGenAlternativeMedia() {
	}
	
	public VideoGenAlternativeMedia(String name, int type) {
		super(name, type);
		VideoGenMandatoryMedia media = new VideoGenMandatoryMedia(name, type);
		this.alternativeMedias.addMedias(media);
	}
	
	public void addAlternative(String name, int type) {
		VideoGenMandatoryMedia media = new VideoGenMandatoryMedia(name, type);
		this.alternativeMedias.addMedias(media);
	}
	
	public int getnbAlternative() {
		return this.alternativeMedias.getNbMedia();
	}
	
	public Medias getAlternatives() {
		return this.alternativeMedias;
	}
}
