package fr.pagetpetit.videogentools

import java.util.ArrayList
import java.util.List
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.MediaDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel

class VideoGenUtils {
	
	static def List<List<MediaDescription>> loadPlaylists(VideoGeneratorModel videoGen){
		
		var playlists = new ArrayList<List<MediaDescription>>
		playlists.add(new ArrayList<MediaDescription>())
		for(media : videoGen.medias){
			if (media instanceof MandatoryMedia){
				if(media.description instanceof VideoDescription){
					for(playlist : playlists){
						playlist.add(media.description);
					}
				}
			}else if(media instanceof AlternativesMedia){
					playlists = populatePlaylists(playlists, media)
				
			}else if(media instanceof OptionalMedia){
				if(media.description instanceof VideoDescription){
					playlists = populatePlaylists(playlists, media)
				}		
			}
		}
		return playlists
	}
	
	static private def ArrayList<List<MediaDescription>> populatePlaylists(List<List<MediaDescription>> playlists, OptionalMedia opt){
		val newPlaylists = new ArrayList<List<MediaDescription>>
		for(playlist : playlists){
			var list1 = new ArrayList<MediaDescription>(playlist)
			list1.add(opt.description)
			newPlaylists.add(list1)
			var list2 = new ArrayList<MediaDescription>(playlist)
			list2.add(null)
			newPlaylists.add(list2)
		}		
		return newPlaylists
	}
	
	static private def ArrayList<List<MediaDescription>> populatePlaylists(List<List<MediaDescription>> playlists, AlternativesMedia alt){
		val newPlaylists = new ArrayList<List<MediaDescription>>
		for(playlist : playlists){
			var size = alt.eContents.size - 1
			var index = 0
			for(desc : alt.eContents){
				var list = new ArrayList<MediaDescription>(playlist)
				for(var i = 0; i < index; i++){
					list.add(null);
				}
				list.add(desc as MediaDescription)
				newPlaylists.add(list)
				for(var i = 0; i < size; i++){
					list.add(null);
				}
				size--;
				index++;
			}
		}
		return newPlaylists
	}
	
	static def void printPlaylist(List<List<MediaDescription>> playlists){
		var i = 0;
		for(playlist : playlists){
			println('Playlist ' + i++)
			for(media : playlist){
				println(media)
			}
		}
	}
	
	static private def String createCSVLine(List<MediaDescription> playlist, int index){
		var line = index + ';'
		var size = 0;
		for(desc : playlist){
			if(desc !== null){
				if((desc as VideoDescription).text !== null){
					size += (desc as VideoDescription).text.size
				}
				line += 'TRUE' + ';'
			}else{
				line += 'FALSE' + ';'
			}
		}
		return line + size + ';'
	}
	
	static private def String createCSVHeader(List<List<MediaDescription>> playlists){
		val headerList = new ArrayList<String>()
		for(mediaDesc : playlists.get(0)){
			headerList.add(null)
		}
		for(playlist : playlists){
			var index = 0
			for(mediaDesc : playlist){
				if(headerList.get(index) === null && mediaDesc !== null){
					headerList.set(index, (mediaDesc as VideoDescription).videoid)
				}
				index++
			}
		}
		var header = 'id;'
		for(str : headerList){
			header += str + ';'
		}
		header += 'size;'
		return header
	}
	
	static def List<String> createCSV(VideoGeneratorModel videoGen){
		return createCSV(loadPlaylists(videoGen))
	}
	
	static def List<String> createCSV(List<List<MediaDescription>> playlists){
		var csv = new ArrayList<String>()
		csv.add(createCSVHeader(playlists))
		var index = 0
		for(playlist : playlists){
			csv += createCSVLine(playlist, index++) + '\n'
		}
		return csv
	}
	
	static def int genNbVariant(VideoGeneratorModel videoGen){
		var variant = 1
		for(media : videoGen.medias){
			if(media instanceof OptionalMedia){
				variant *= 2
			}else if(media instanceof AlternativesMedia){
				variant *= media.medias.size
			}
		}
		return variant
	}
	
	static def String concatVideos(String[] filenames, String outputfilename){
		val COMMAND = new ArrayList<String>
		var filter = ""
		COMMAND.add("ffmpeg")
		COMMAND.add("-y")
		var i = 0
		for(file : filenames){
			COMMAND.add("-i")
			COMMAND.add(file)
			filter += "[" + i + ":v:0][" + i + ":a:0]"
			i++
		}
		COMMAND.add("-filter_complex")
		filter += "concat=n=" + i + ":v=1:a=1[outv][outa]"
		COMMAND.add(filter)
		COMMAND.add("-map")
		COMMAND.add("[outv]")
		COMMAND.add("-map")
		COMMAND.add("[outa]")
		COMMAND.add(outputfilename)
		
		for(arg : COMMAND){
			println(arg)
		}
		
		ProcessExec.executeCommand(COMMAND)
		println(outputfilename + " created")
		
		return outputfilename
	}
	
	static def int[] getResolution(String videoFile){
		val COMMAND = new ArrayList
		COMMAND.add("ffmpeg")
		COMMAND.add("-i")
		COMMAND.add(videoFile)
		val IO = ProcessExec.executeCommandIO(COMMAND)
		val resolution = new ArrayList
		for(line : IO){
			if(line.contains("Stream #0:0(und):")){
				val res = line.split(", ").get(2).split(" ").get(0).split("x")
				resolution.add(Integer.parseInt(res.get(0)))
				resolution.add(Integer.parseInt(res.get(1)))
			}
		}
		
		return resolution
	}
	
	static def String resize(String filename, int input_width, int input_height, int output_width, int output_height, String outputFolder){
		if(input_width == output_width && input_height == output_height){
			println(filename + " already at right size")
			return filename
		}
		var path = filename.split("/")
		var file = path.get(path.length - 1).replace(".","@#").split("@#")
		var COMMAND = new ArrayList
		COMMAND.add("ffmpeg")
		COMMAND.add("-y")
        COMMAND.add("-i")
        COMMAND.add(filename)
        COMMAND.add("-vf")
        COMMAND.add("pad=" + output_width + ":" + output_height + ":" + (output_width - input_width) / 2 + ":" + (output_height - input_height) / 2)
        val output_file = outputFolder + "/" + file.get(file.length - 2) + "_o." + file.get(file.length - 1)
        COMMAND.add(output_file)
        for(c : COMMAND){
        	println(c)
        }
        ProcessExec.executeCommand(COMMAND)
        println(filename + " resized")
        return output_file
	}
	
	static def String generatePlaylist(String[] videos, String outputFolder){
		val res = new ArrayList
		
		for(video : videos){
			res.add(VideoGenUtils.getResolution(video))
		}
		
		var output_width = 0
		var output_height = 0
		
		for(r : res){
			if(r.get(0) > output_width){
				output_width = r.get(0)
			}
			if(r.get(1) > output_height){
				output_height = r.get(1)
			}
		}
		var i = 0;
		val playlist = new ArrayList
		for(video : videos){
			val r = res.get(i++)
			playlist.add(VideoGenUtils.resize(video, r.get(0), r.get(1), output_width, output_height, outputFolder))
		}
		
		return VideoGenUtils.concatVideos(playlist, outputFolder + "/output.mp4")
		
	}
}
		