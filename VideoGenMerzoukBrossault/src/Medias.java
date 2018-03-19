import java.util.ArrayList;
import java.util.List;

public class Medias {
	
	List<VideoGenMedia> medias = new ArrayList<VideoGenMedia>();
	private long size = 0;
	
	public List<VideoGenMedia> getMedias() {
		return this.medias;
	}
	
	public void setMedias(List<VideoGenMedia> medias) {
		this.medias = medias;
		for(VideoGenMedia media : medias) {
			this.setSize(this.getSize() + media.getTaille());
		}
	}
	
	public void addMedias(VideoGenMedia media) {
		this.medias.add(media);
	}
	
	public int getNbMedia() {
		return this.medias.size();
	}

	public VideoGenMedia getMedia() {
		return this.medias.get(0);
	}
	
	public List<String> getMediaNames() {
		List<String> names = new ArrayList<String>();
		for(VideoGenMedia media : this.medias) {
			names.add(media.getName());
		}
		return names;
	}
	
	public long getSize() {
		long size = 0;
		for(VideoGenMedia media : this.medias) {
			size += media.getTaille();
		}
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getNbVariantes() {
		int i = 1;
		for(VideoGenMedia media : this.medias) {
			if(media instanceof VideoGenMandatoryMedia) {
				boolean isOptional = ((VideoGenMandatoryMedia) media).isOptional();
				if(isOptional) {
					i = i*2;
				}
			} else if(media instanceof VideoGenAlternativeMedia) {
				i = i*((VideoGenAlternativeMedia) media).getnbAlternative();
			}
		}
		return i;
	}
	
	public Medias copy() {
		Medias copy = new Medias();
		copy.getMedias().addAll(this.medias);
		return copy;
	}
}
