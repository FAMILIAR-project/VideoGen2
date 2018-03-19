import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.impl.AlternativesMediaImpl;
import org.xtext.example.mydsl.videoGen.impl.MandatoryMediaImpl;
import org.xtext.example.mydsl.videoGen.impl.OptionalMediaImpl;

public class VideoGenToolBox {
	
	private static final String VLC_COMMAND = "vlc --play-and-exit ";
	
	//Get author
	public String getAuthor(VideoGeneratorModel videoGen) {
		if(videoGen.getInformation().getAuthorName() != null) {
			return videoGen.getInformation().getAuthorName();
		}
		return "";
	}
	
	//Get version
	public String getVersion(VideoGeneratorModel videoGen) {
		if(videoGen.getInformation().getVersion() != null) {
			return videoGen.getInformation().getVersion();
		}
		return "";
	}
	
	//Get creation date
	public String getCreationDate(VideoGeneratorModel videoGen) {
		if(videoGen.getInformation().getCreationDate() != null) {
			return videoGen.getInformation().getVersion();
		}
		return "";
	}
	
	//play all media with vlc
	public void getMedias(VideoGeneratorModel videoGen) throws IOException, InterruptedException {
		for(Media media : videoGen.getMedias()) {
			MediaDescription description;
			if(media instanceof MandatoryMediaImpl) {
				description = ((MandatoryMediaImpl) media).getDescription();
				Process command = Runtime.getRuntime().exec(VLC_COMMAND + description.getLocation());
				command.waitFor();
			} else if(media instanceof AlternativesMediaImpl) {
				EList<MediaDescription> alternativesMedias = ((AlternativesMediaImpl) media).getMedias();
				for(MediaDescription alternativeMediaDescription : alternativesMedias){
					Process command = Runtime.getRuntime().exec(VLC_COMMAND + alternativeMediaDescription.getLocation());
					command.waitFor();
				}
			} else {
				description = ((OptionalMediaImpl) media).getDescription();
				Process command = Runtime.getRuntime().exec(VLC_COMMAND + description.getLocation());
				command.waitFor();
			}
		}
	}
	
	//Get all media and save it in model
	public Medias getAllMedias(VideoGeneratorModel videoGen) {
		Medias medias = new Medias();
		VideoGenMedia previousMedia = null;
		for(Media media : videoGen.getMedias()) {
			MediaDescription description;
			if(media instanceof MandatoryMediaImpl) {
				description = ((MandatoryMediaImpl) media).getDescription();
				int type = VideoGenMedia.TYPE_VIDEO;
				if(description instanceof VideoDescription) {
					type = VideoGenMedia.TYPE_VIDEO;
				} else if(description instanceof ImageDescription) {
					type = VideoGenMedia.TYPE_IMAGE;
				}
				VideoGenMedia currentMedia = new VideoGenMandatoryMedia(description.getLocation(), type);
				if(previousMedia != null) {
					previousMedia.setNextMedia(currentMedia);
				}
				medias.addMedias(currentMedia);
				previousMedia = currentMedia;
			} else if(media instanceof AlternativesMediaImpl) {
				VideoGenMedia currentMedia = new VideoGenAlternativeMedia();
				EList<MediaDescription> alternativesMedias = ((AlternativesMediaImpl) media).getMedias();
				for(MediaDescription alternativeMediaDescription : alternativesMedias){
					int type = VideoGenMedia.TYPE_VIDEO;
					if(alternativeMediaDescription instanceof VideoDescription) {
						type = VideoGenMedia.TYPE_VIDEO;
					} else if(alternativeMediaDescription instanceof ImageDescription) {
						type = VideoGenMedia.TYPE_IMAGE;
					}
					((VideoGenAlternativeMedia) currentMedia).addAlternative(alternativeMediaDescription.getLocation(), type);
				}
				if(previousMedia != null) {
					previousMedia.setNextMedia(currentMedia);
				}
				medias.addMedias(currentMedia);
				previousMedia = currentMedia;
			} else {
				description = ((OptionalMediaImpl) media).getDescription();
				int type = VideoGenMedia.TYPE_VIDEO;
				if(description instanceof VideoDescription) {
					type = VideoGenMedia.TYPE_VIDEO;
				} else if(description instanceof ImageDescription) {
					type = VideoGenMedia.TYPE_IMAGE;
				}
				VideoGenMedia currentMedia = new VideoGenMandatoryMedia(description.getLocation(), type);
				currentMedia.setOptional(true);
				if(previousMedia != null) {
					previousMedia.setNextMedia(currentMedia);
				}
				medias.addMedias(currentMedia);
				previousMedia = currentMedia;
			}
		}
		return medias;
	}
	
	//Get all possibles variante
	public List<Medias> getAllVariantes(VideoGeneratorModel videoGen) {
		VideoGenMedia media = getAllMedias(videoGen).getMedia();
		List<Medias> variantes = new ArrayList<>();
		Medias medias = new Medias();
		getVariante(media, medias, variantes);
		return variantes;
	}
	
	//Generate variantes
	public void getVariante(VideoGenMedia media, Medias medias, List<Medias> variantes) {
		if(media!=null) {
			if(media instanceof VideoGenMandatoryMedia) {
				if(media.isOptional()) {
					Medias mediaVariante = medias.copy();
					mediaVariante.addMedias(media);
					getVariante(media.getNextMedia(), medias, variantes);
					getVariante(media.getNextMedia(), mediaVariante, variantes);
				} else {
					medias.addMedias(media);
					getVariante(media.getNextMedia(), medias, variantes);
				}
			} else {
				for(VideoGenMedia alternateMedia : ((VideoGenAlternativeMedia) media).getAlternatives().getMedias()) {
					Medias mediaVariante = medias.copy();
					mediaVariante.addMedias(alternateMedia);
					getVariante(media.getNextMedia(), mediaVariante, variantes);
				}
			}
		} else {
			variantes.add(medias);
		}
	}
	
	//Create the mp4 file for a selected variante
	public void generateMp4FileForOneVariante(Medias variante, String name) throws IOException, InterruptedException {
		List<String> videoElements = new ArrayList<String>();
		for(VideoGenMedia media : variante.getMedias()) {
			String mediaName = media.getName().replace(".mp4", "").replace(".png", "");
			if(media.getType() == VideoGenMedia.TYPE_VIDEO) {
				//if video : prepare a .ts file
				FfmpegHelper.prepareMp4ToConcat(mediaName);
			} else {
				//if picture : Convert to mp4
				FfmpegHelper.convertPngToMp4(mediaName);
				//then create the corresponding .ts
				FfmpegHelper.prepareMp4ToConcat(mediaName);
			}
			videoElements.add(mediaName);
		}
		//concat .ts files
		FfmpegHelper.concatTsToMp4(videoElements, name);
	}
	
	//Compute and generate all video
	public void generateAllVariantesWithMp4Files(VideoGeneratorModel videoGen) throws IOException, InterruptedException {
		List<Medias> variantes = getAllVariantes(videoGen);
		int i = 0;
		for(Medias variante : variantes) {
			i++;
			generateMp4FileForOneVariante(variante, "destination"+i);
		}
	}
	
	//run a video with vlc
	public void runVideo(String media) throws IOException, InterruptedException {
		Process command = Runtime.getRuntime().exec(VLC_COMMAND+media+".gif");
		command.waitFor();
	}
	
	//get maximum number of media for the longer generated video
	public Medias getMoreLongerVideo(List<Medias> variantes) {
		Medias video = null;
		int nbMedia = 0;
		for(Medias variante : variantes) {
			if(variante.getNbMedia()>nbMedia) {
				video = variante;
			}
		}
		return video;
	}
}
