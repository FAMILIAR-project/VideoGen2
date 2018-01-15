package fr.istic.generateur.idm;

import java.util.ArrayList
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel

class VideoGenUtils {
	
    /**
     * @param videos : la variante a jouer
     * @param outputFolder : le dossier où la stocker
     * @return le nom de la video generer
     */
    static def String genererPlayList(String[] videos, String outputFolder){
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

        return VideoGenUtils.concatenerVideos(playlist, outputFolder + "/", "video_" + System.currentTimeMillis + ".mp4")  
    }
    
    /**
     * permet de concatener des videos grace a ffmpeg
     * @return : le nom du fichier
     */
     private static def String concatenerVideos(String[] filenames, String outputfilename, String videoName){
        val COMMAND = new ArrayList<String>
        var filter = ""
        val video = outputfilename + videoName
        COMMAND.add("ffmpeg")
        COMMAND.add("-y")
        var i = 0
        for(file : filenames){
            COMMAND.add("-i")
            COMMAND.add(file)
            filter += "[" + i + ":v:0][" + i + ":a:0]"
            i++
        }
        COMMAND.add("-strict")
        COMMAND.add("-2")
        COMMAND.add("-filter_complex")
        filter += "concat=n=" + i + ":v=1:a=1[outv][outa]"
        COMMAND.add(filter)
        COMMAND.add("-map")
        COMMAND.add("[outv]")
        COMMAND.add("-map")
        COMMAND.add("[outa]")
        COMMAND.add(video)
        
        for(arg : COMMAND){
            println(arg)
        }
        ProcessExec.executeCommand(COMMAND)        
        return videoName
    }
    
    /**
     * @Param : VideoGeneratorModel
     * @return : retourne une variante aleatoire
     */
    static def String[] getRandomVariant(VideoGeneratorModel model){
        val variant = new ArrayList<String>
        for(media : model.medias){
            if(media instanceof MandatoryMedia && (media as MandatoryMedia).description instanceof VideoDescription){
                variant.add((media as MandatoryMedia).description.location)
            }else if(media instanceof OptionalMedia && (media as OptionalMedia).description instanceof VideoDescription){
                if(Math.random > 0.5){
                    variant.add((media as OptionalMedia).description.location)
                }
            }else if(media instanceof AlternativesMedia && (media as AlternativesMedia).medias.get(0) instanceof VideoDescription){
                variant.add(selectAlternativeVideo(media as AlternativesMedia))
            }
        }

        return variant
    }
    
    /**
     * @return : le media choisi parmi les alternatives
     */
    private def static String selectAlternativeVideo(AlternativesMedia media) {
        var chosen = ""
        chosen = null
        var total = 0
        val weights = new ArrayList<Integer>
        for(desc : media.medias){
            weights.add(total)
            total += (desc as VideoDescription).probability
        }
        if(total == 0){
            val random = Math.random * media.medias.length
            chosen = media.medias.get(random as int).location
        }else{
            val random = Math.random * total
            var bool = true
            for(var i = 0; i < media.medias.length - 1 && bool; i++){
                if(random >= weights.get(i) && random < weights.get(i+1)){
                    chosen = media.medias.get(i).location
                    bool = false
                }
            }
            if(chosen === null){
                val length = media.medias.length
                chosen = media.medias.get(length - 1).location
            }
        }
        return chosen
    }
    
    /**
     * @Param : VideoGeneratorModel
     * @return : le nombre de variante du model
     */
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

    
    /**
     * @return: renvoi la resolution d'une video
     */
    private static def int[] getResolution(String videoFile){
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
    
    /**
     * @return : retourne la duree de video
     */
    private static def int[] getDuration(String videoFile){
        val COMMAND = new ArrayList
        COMMAND.add("ffmpeg")
        COMMAND.add("-i")
        COMMAND.add(videoFile)
        val IO = ProcessExec.executeCommandIO(COMMAND)
        val duration = new ArrayList
        for(line : IO){
            if(line.contains("Duration:")){
                val res = line.split(", ").get(0).split(": ").get(1).split(":")
                duration.add(Integer.parseInt(res.get(0)))
                duration.add(Integer.parseInt(res.get(1)))
                duration.add(Integer.parseInt(res.get(2).replace(".","@#").split("@#").get(0)))
            }
        }
        return duration
    }
    
    /**
     * permet de redimensionner une video pour regler les problemes lors de la concat
     */
    private static def String resize(String filename, int input_width, int input_height, int output_width, int output_height, String outputFolder){
        if(input_width == output_width && input_height == output_height){
            return filename
        }
        var path = filename.split("/")
        var file = path.get(path.length - 1).replace(".","@#").split("@#")
        var COMMAND = new ArrayList
        COMMAND.add("ffmpeg")
        COMMAND.add("-y")
        COMMAND.add("-i")
        COMMAND.add(filename)
        COMMAND.add("-strict")
        COMMAND.add("-2")
        COMMAND.add("-vf")
        COMMAND.add("pad=" + output_width + ":" + output_height + ":" + (output_width - input_width) / 2 + ":" + (output_height - input_height) / 2)
        val output_file = outputFolder + "/" + file.get(file.length - 2) + "_o." + file.get(file.length - 1)
        COMMAND.add(output_file)
        for(c : COMMAND){
            println(c)
        }
        ProcessExec.executeCommand(COMMAND)
        return output_file
    }
    
    /**
     * permet de creer des vignettes de video
     */
    static def String[] generateThumbnails(VideoGeneratorModel model, String thumbnailOutputFolder){
        val thumbnails = new ArrayList<String>
        for(media : model.medias){
            if(media instanceof MandatoryMedia && (media as MandatoryMedia).description instanceof VideoDescription){
                thumbnails.add(createThumbnail((media as MandatoryMedia).description.location, thumbnailOutputFolder))
            }else if(media instanceof OptionalMedia && (media as OptionalMedia).description instanceof VideoDescription){
                thumbnails.add(createThumbnail((media as OptionalMedia).description.location, thumbnailOutputFolder))
            }else if(media instanceof AlternativesMedia && (media as AlternativesMedia).medias.get(0) instanceof VideoDescription){
                for(description : (media as AlternativesMedia).medias){
                    thumbnails.add(createThumbnail(description.location, thumbnailOutputFolder))
                }
            }
        }
        return thumbnails
    }
    
    /**
     * prend une vignete à partir une durér donner d'une video
     */
    private static def String createThumbnail(String videoPath, String thumbnailOutputFolder){
        val duration = getDuration(videoPath)
        var h = intToStringWith2Digit(duration.get(0) / 2)
        var m = intToStringWith2Digit(duration.get(1) / 2)
        var s = intToStringWith2Digit(duration.get(2) / 2)
        var COMMAND = new ArrayList
        COMMAND.add("ffmpeg")
        COMMAND.add("-y")
        COMMAND.add("-i")
        COMMAND.add(videoPath)
        COMMAND.add("-r")
        COMMAND.add("1")
        COMMAND.add("-t")
        COMMAND.add("00:00:01")
        COMMAND.add("-ss")
        COMMAND.add(h + ":" + m + ":" + s)
        var path = videoPath.split("/")
        var file = path.get(path.length - 1).replace(".","@#").split("@#")
        val outputFile = thumbnailOutputFolder + "/" + file.get(0) + ".png"
        COMMAND.add(outputFile)
        
        ProcessExec.executeCommand(COMMAND)
        
        return outputFile
    }
    
    /**
     * 
     */
    private static def String intToStringWith2Digit(int input){
        if(input < 10){
            return "0" + input
        }else{
            return "" + input
        }
    }

}