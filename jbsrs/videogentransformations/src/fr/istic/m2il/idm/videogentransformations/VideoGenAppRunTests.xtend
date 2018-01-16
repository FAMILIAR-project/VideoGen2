package fr.istic.m2il.idm.videogentransformations

import fr.istic.m2il.idm.videogentransformations.transformations.VideoGenPlayTransformationsTest
import org.junit.runner.JUnitCore
import org.junit.runner.Request
import org.junit.runner.notification.Failure
import fr.istic.m2il.idm.videogentransformations.configs.VideoGenConfigs

class VideoGenAppRunTests {
	
	static def void main(String[] args){
		var choices = newHashMap
		
		choices.put("nombre_variants", "checksVariantsNumber")
		choices.put("concatener_videos", "concatVideos")
		choices.put("gerer_probabilites", "generateRandomPlayList")
		choices.put("exporter_gifs", "videoToGif")
		choices.put("tailles_variantes", "checksCSVLinesNumber")
		choices.put("durees_variantes", "checksCSVLinesNumber")
		choices.put("appliquer_filtres", "generateRandomPlayList")
		if(args.size < 3){
			System.err.println("Veuillez indiquez tous les arguments !!!")
			System.exit(0)
		}
		else{
			switch args.get(0) {
				case "durees_variantes":{
					System.setProperty("csv_duration", "true")
					System.setProperty("videogenspecification", args.get(2))
				}
					
				case "appliquer_filtres":
				if(args.get(3) === null){
					System.err.println("Veuillez indiquez tous les arguments !!!") 
					System.exit(0)	
				}
				else{
					System.setProperty("file_filter", args.get(2))
					System.setProperty("filter", args.get(3))
				}
				
				case "expoter_gif":
					if(args.get(3) === null && args.get(4) !== null){
						System.err.println("Veuillez indiquez tous les arguments !!!") 
						System.exit(0)
					}
					else{
						System.setProperty("videogenspecification", args.get(2))
						System.setProperty("gif_width", args.get(3))
						System.setProperty("gif_heigth", args.get(4))
					}
				
				case "concatener_videos":
					if(args.get(3) === null && args.get(4) === null){
						System.err.println("Veuillez indiquez tous les arguments !!!") 
						System.exit(0)
					}
					else{
						System.setProperty("concat_format", args.get(2))
						var files = args.get(3)
						files += " " + args.get(4)
						
						for (var i = 5 ; i < args.length ; i++) {
  							files += " " + args.get(i)
						}
						
						System.setProperty("concat_files", files);
						
					}
				case "tailles_variantes":
					if(args.get(3) === null){
						System.err.println("Veuillez indiquez tous les arguments !!!") 
						System.exit(0)
					}
					else{
						if(args.get(3).equals("gif")){
							System.setProperty("csv_type", "true")
							if(args.get(4) !== null && args.get(5) !== null){
								System.setProperty("gif_width", args.get(4))
								System.setProperty("gif_heigth", args.get(5))
								System.setProperty("videogenspecification", args.get(2))
								System.setProperty("csv_duration", "false")
							}
							else{
								System.err.println("Veuillez indiquez tous les arguments !!!") 
								System.exit(0)
							}
						}
						if(args.get(3).equals("video")){
							System.setProperty("csv_type", "false")
							System.setProperty("videogenspecification", args.get(2))
							System.setProperty("csv_duration", "false")
						}
					}
					
					
				default: System.setProperty("videogenspecification", args.get(2))
			}
			val jUnitCore = new JUnitCore()
			
			System.out.println("Outpout " + args.get(1))
			VideoGenConfigs.setOutPutFolder(args.get(1))
			System.out.println("Outpout " + args.get(1))
			System.setProperty("output_folder", args.get(1))
			VideoGenConfigs.initSubOutPutFolders
			
			var request = Request.method(VideoGenPlayTransformationsTest, choices.get(args.get(0)))
			val result =  jUnitCore.run(request)
			for (Failure failure : result.getFailures()) {
         		System.out.println(failure.toString());
      		}
		
      		System.out.println(result.wasSuccessful());
		}
	}
	
}