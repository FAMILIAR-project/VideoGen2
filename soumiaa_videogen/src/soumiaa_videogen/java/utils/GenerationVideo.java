package soumiaa_videogen.java.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

import soumiaa_videogen.java.exception.BadProba;
import soumiaa_videogen.java.media.IdFile;
import soumiaa_videogen.java.media.image.IdImage;
import soumiaa_videogen.java.media.video.IdVideo;
import soumiaa_videogen.java.media.video.TextPosition;
import soumiaa_videogen.java.media.video.TextVideo;

public class GenerationVideo {
	private String pathVideoGenFile;
	
	public GenerationVideo(String pathToVideoGenFile) throws IOException {
		File fileTmp = new File(pathToVideoGenFile);
		if (!fileTmp.exists() || !fileTmp.isFile()) {
			throw new IOException(pathToVideoGenFile+" doesn't exist or is not a file");
		}
		
		pathVideoGenFile = pathToVideoGenFile;
		
	}
	
	
	public CalculeVariante generateVariante() throws BadProba {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(pathVideoGenFile));
		
		CalculeVariante variantes = new CalculeVariante();

		EList<Media> medias = videoGen.getMedias();

		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				MandatoryMedia mandaMedia = (MandatoryMedia) media;
				MediaDescription desc = mandaMedia.getDescription();

				if (desc instanceof VideoDescription) {
					VideoDescription videoDesc = (VideoDescription) desc;
					String pathVideo = videoDesc.getLocation();
					String id = videoDesc.getVideoid();
					int duration = videoDesc.getDuration();
					String description = videoDesc.getDescription();
					
					TextVideo videoText;
					if (videoDesc.getText() == null) {
						videoText = null;
					}else {
						String content = videoDesc.getText().getContent();
						TextPosition position = TextPosition.stringToPosition(videoDesc.getText().getPosition());
						int videoTextDuration = videoDesc.getDuration();
						String color = videoDesc.getText().getColor();
						
						videoText = new TextVideo(content, position, videoTextDuration, color);
					}
					
					
					
					variantes.addMandatoryVideo(new IdVideo(id, pathVideo, 1, duration, description,videoText));
				}else if(desc instanceof ImageDescription) {
					ImageDescription imageDesc = (ImageDescription) desc;
					String pathVideo = imageDesc.getLocation();
					String id = imageDesc.getImageid();
					String botText = imageDesc.getBottom();
					String topText = imageDesc.getTop();
					
					variantes.addOptionnalVideo(new IdImage(id, pathVideo,1,botText,topText));
				}
			} else if (media instanceof OptionalMedia) {
				OptionalMedia optionMedia = (OptionalMedia) media;
				MediaDescription desc = optionMedia.getDescription();

				if (desc instanceof VideoDescription) {
					VideoDescription videoDesc = (VideoDescription) desc;
					String pathVideo = videoDesc.getLocation();
					String id = videoDesc.getVideoid();
					
					int proba = videoDesc.getProbability();
					
					if(proba < 0 || proba > 100) {
						throw new BadProba("Mauvaise probabilité, "+proba+" trouver, alors qu'une proba doit être entre 0 et 100");
					}
					
					if(proba == 0) {
						proba = 50;
					}
					
					double realProba = (double)proba/100.0;
					int duration = videoDesc.getDuration();
					String description = videoDesc.getDescription();
				
					TextVideo videoText;
					if (videoDesc.getText() == null) {
						videoText = null;
					}else {
						String content = videoDesc.getText().getContent();
						TextPosition position = TextPosition.stringToPosition(videoDesc.getText().getPosition());
						int videoTextDuration = videoDesc.getDuration();
						String color = videoDesc.getText().getColor();
						
						videoText = new TextVideo(content, position, videoTextDuration, color);
					}
					
					variantes.addOptionnalVideo(new IdVideo(id, pathVideo,realProba,duration,description,videoText));					
				}else if(desc instanceof ImageDescription) {
					ImageDescription imageDesc = (ImageDescription) desc;
					String pathVideo = imageDesc.getLocation();
					String id = imageDesc.getImageid();
					String botText = imageDesc.getBottom();
					String topText = imageDesc.getTop();
					
					variantes.addOptionnalVideo(new IdImage(id, pathVideo,0.5,botText,topText));
				}
			} else if (media instanceof AlternativesMedia) {
				AlternativesMedia altMedia = (AlternativesMedia) media;
				EList<MediaDescription> mediaList = altMedia.getMedias();
				List<IdFile> listVideo = new ArrayList<>();
				
				
				for (MediaDescription mediaDesc : mediaList) {
					if (mediaDesc instanceof VideoDescription) {
						VideoDescription videoDesc = (VideoDescription) mediaDesc;
						String pathVideo = videoDesc.getLocation();
						String id = videoDesc.getVideoid();
						
						double proba = videoDesc.getProbability();
						
						if(proba < 0 || proba > 100) {
							throw new BadProba("Mauvaise probabilité, "+proba+" trouver, alors qu'une proba doit être entre 0 et 100");
						}
						
						proba /=100;
						
						int duration = videoDesc.getDuration();
						String description = videoDesc.getDescription();
						
						TextVideo videoText;
						if (videoDesc.getText() == null) {
							videoText = null;
						}else {
							String content = videoDesc.getText().getContent();
							TextPosition position = TextPosition.stringToPosition(videoDesc.getText().getPosition());
							int videoTextDuration = videoDesc.getDuration();
							String color = videoDesc.getText().getColor();
							
							videoText = new TextVideo(content, position, videoTextDuration, color);
						}
						
						listVideo.add(new IdVideo(id, pathVideo,proba,duration,description,videoText));
					}else if(mediaDesc instanceof ImageDescription) {
						ImageDescription imageDesc = (ImageDescription) mediaDesc;
						String pathVideo = imageDesc.getLocation();
						String id = imageDesc.getImageid();
						String botText = imageDesc.getBottom();
						String topText = imageDesc.getTop();
						
						variantes.addOptionnalVideo(new IdImage(id, pathVideo,0,botText,topText));
					}
				}
				double defaultProba = 1/(double)listVideo.size();
				
				double totalProba = 0;
				for (IdFile idFile : listVideo) {
					if (idFile.getProba() == 0) {
						idFile.setProba(defaultProba);
						
						totalProba += defaultProba;
					}
					totalProba += idFile.getProba();
				}
				
				
				if (totalProba > 1) {
					throw new BadProba("Le total des probabilités de video alternative est supérieurs à 1, proba trouver :"+totalProba);
				}
				
				if (totalProba < 0.99) {
					throw new BadProba("Le total des probabilités de video alternative est inférieurs à 1, proba trouver :"+totalProba);
				}
				
				variantes.addAlternativeVideo(listVideo);
			}
		}		
		return variantes;
	}
}
