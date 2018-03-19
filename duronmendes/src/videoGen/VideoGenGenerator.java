package videoGen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.AlternativesMedia;
import org.xtext.example.mydsl.videoGen.BlackWhiteFilter;
import org.xtext.example.mydsl.videoGen.Filter;
import org.xtext.example.mydsl.videoGen.FlipFilter;
import org.xtext.example.mydsl.videoGen.ImageDescription;
import org.xtext.example.mydsl.videoGen.MandatoryMedia;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.MediaDescription;
import org.xtext.example.mydsl.videoGen.NegateFilter;
import org.xtext.example.mydsl.videoGen.OptionalMedia;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoText;

import randomUtils.RandomSelector;
import static ffmpegUtils.FfmpegUtils.*;

public class VideoGenGenerator {
	
	private List<MediaDescription> playlist;

	public VideoGenGenerator() {
		super();
		this.playlist = new ArrayList<MediaDescription>();
	}

	public void genPlaylist(VideoGeneratorModel videoGen) {
		
		EList<Media> medias = videoGen.getMedias();
		
		for (Media media : medias) {
			if (media instanceof MandatoryMedia) {
				playlist.add(((MandatoryMedia) media).getDescription());
			} else if (media instanceof OptionalMedia) {
				MediaDescription optional = ((OptionalMedia) media).getDescription();
				if (optional instanceof VideoDescription) {
					VideoDescription vid = (VideoDescription) optional;
					if (ThreadLocalRandom.current().nextInt(0, 100 + 1) <= vid.getProbability()) {
						playlist.add(((OptionalMedia) media).getDescription());
					}
				}
			} else if (media instanceof AlternativesMedia) {
				AlternativesMedia alternative = ((AlternativesMedia) media);
				RandomSelector randomSelector = new RandomSelector(alternative);
				playlist.add(alternative.getMedias().get(randomSelector.getRandom()));
			}
		}
	}
	
	public void genVideo() throws IOException {
		String res = "";
		BufferedWriter writer = new BufferedWriter(new FileWriter("playlist.txt"));
		for (MediaDescription mediaDescription : playlist) {
			if(mediaDescription instanceof VideoDescription){
				VideoDescription videoDescription = (VideoDescription) mediaDescription;
				processVideo(videoDescription);
				//write video to playlist
				res += "file '" + "tmp/" + videoDescription.getVideoid() + ".mp4" + "'\n";
			}
			if(mediaDescription instanceof ImageDescription) {
				ImageDescription imageDescription = (ImageDescription) mediaDescription;
				processImage(imageDescription);
				//write video to playlist
				res += "file '" + "tmp/" + imageDescription.getImageid() + ".mp4" + "'\n";
			}
		}
		writer.write(res);
		writer.close();
		
		String command = mkFFMPEGPlaylist("playlist.txt", "out/result.mp4");
		runCommand(command);
	}
	
	private void processVideo(VideoDescription videoDescription) {
		videoDescription.getVideoid();
		String oldPath = videoDescription.getLocation();
		String newPath = "tmp/"+videoDescription.getVideoid() + ".mp4";
		String tmpPath = "tmp/tmpVid.mp4";
		
		//force format
		runCommand(mkFFMPEGConvertVideo(oldPath, newPath));
		
		//apply filters
		Filter filter = videoDescription.getFilter();
		processFilter(filter,newPath,tmpPath);
		
		//apply text
		VideoText videoText = videoDescription.getText();
		processVideoText(videoText, newPath, tmpPath);

	}
	
	private void processImage(ImageDescription imageDescription) {
		imageDescription.getImageid();
		String oldPath = imageDescription.getLocation();
		String newPath = "tmp/"+imageDescription.getImageid() + ".mp4";
		String tmpPath = "tmp/tmpVid.mp4";

		//convert to video
		runCommand(mkFFMPEGConvertImage(oldPath,newPath));
		
		//reformat video
		runCommand(mkFFMPEGConvertVideo(newPath, tmpPath));
		replaceAndRemoveVideo(newPath, tmpPath);

		//apply text
		processImageText(newPath,tmpPath,imageDescription.getTop(),imageDescription.getBottom());	
	}

	private void processFilter(Filter filter,String newPath, String tmpPath) {
		if(filter != null) {
			if(filter instanceof BlackWhiteFilter) {
				runCommand(mkFFMPEGFilerGrayscale(newPath, tmpPath));
				replaceAndRemoveVideo(newPath, tmpPath);
			}
			else if(filter instanceof NegateFilter){
				runCommand(mkFFMPEGFilerNegate(newPath, tmpPath));
				replaceAndRemoveVideo(newPath, tmpPath);
			}else if(filter instanceof FlipFilter) {
				String orientation = ((FlipFilter) filter).getOrientation();
				if(orientation.equals("h") || orientation.equals("horizontal")) {
					runCommand(mkFFMPEGFilerFlipHorizontal(newPath, tmpPath));
					replaceAndRemoveVideo(newPath, tmpPath);
				}else if(orientation.equals("v") || orientation.equals("vertical")) {
					runCommand(mkFFMPEGFilterFlipVertical(newPath, tmpPath));
					replaceAndRemoveVideo(newPath, tmpPath);
				}
			}
		}
	}
	
	private void processVideoText(VideoText videoText,String newPath, String tmpPath) {
		if(videoText!=null) {
			int positionX = 0;
			int positionY = 0;
			if(videoText.getPosition().equals("TOP")) {
				positionX = 50;
				positionY = 20;
			}else if(videoText.getPosition().equals("CENTER")){
				positionX = 50;
				positionY = 50;
			}else if(videoText.getPosition().equals("BOTTOM")) {
				positionX = 50;
				positionY = 80;
			}
			runCommand(mkFFMPEGAddText(newPath,tmpPath,videoText.getContent(),videoText.getColor(),
					Integer.toString(videoText.getSize()),Integer.toString(positionX),Integer.toString(positionY)));
			replaceAndRemoveVideo(newPath, tmpPath);
		}
	}
	
	private void processImageText(String newPath,String tmpPath,String topText,String bottomText) {
		//Add top text
		runCommand(mkFFMPEGAddText(newPath,tmpPath,topText,"white","32","50","20"));
		replaceAndRemoveVideo(newPath, tmpPath);

		//Add bottom text
		runCommand(mkFFMPEGAddText(newPath,tmpPath,bottomText,"white","32","50","80"));
		replaceAndRemoveVideo(newPath, tmpPath);
	}
	
	private void replaceAndRemoveVideo(String newPath,String tmpPath) {
		//Remove tmp video
		runCommand("cp " +tmpPath+ " " + newPath);
		runCommand("rm " + tmpPath );
	}
}
