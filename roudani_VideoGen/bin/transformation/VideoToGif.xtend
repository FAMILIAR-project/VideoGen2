package transformation

import org.xtext.example.mydsl.videoGen.VideoGeneratorModel
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.VideoDescription
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import util.Filters
import util.Utils

class VideoToGif {

	/**
	 * generer gif pour une video donnée
	 */
	def videoToGif(String path, String output, int height, int width) {
		val cmd = "ffmpeg -i " + path + " -vf " + "scale=" + height + ':' + width + ' ' + output + " -hide_banner"
		Runtime.getRuntime().exec(cmd).waitFor
	}

	/**
	 * generer gif pour chaque video choisie dans le model
	 */
	def boolean modelToGifs(VideoGeneratorModel videoGen, int height, int width) {

		for (media : videoGen.medias) {

			if (media instanceof MandatoryMedia) {

				val manMedia = media as MandatoryMedia
				val manMediaDesc = manMedia.description
				if (manMediaDesc instanceof VideoDescription) {
					val filter = manMediaDesc.filter
					val videoText = manMediaDesc.text
					var path = Filters.addText(manMediaDesc.location, videoText,false)
					val locationChanged = (!path.equals(manMediaDesc.location))
					path = Filters.filter(filter, path, locationChanged)
					videoToGif(path, Utils.rename(path, "videos", "gifs", "mp4", "gif"), height, width)

				}

			} else if (media instanceof OptionalMedia) {
				val opMedia = media as OptionalMedia
				val opMediaDesc = opMedia.description
				if (opMediaDesc instanceof VideoDescription) {
					val opVideoDesc = opMediaDesc as VideoDescription
					var probability = 0.5
					if (opVideoDesc.probability > 0)
						probability = 0.01 * opVideoDesc.probability
					if (Math.random < probability) {
						val filter = opVideoDesc.filter
						val videoText = opVideoDesc.text
						var path = Filters.addText(opVideoDesc.location, videoText, false)
						val locationChanged = (!path.equals(opVideoDesc.location))
						path = Filters.filter(filter, path, locationChanged)
						videoToGif(path, Utils.rename(path, "videos", "gifs", "mp4", "gif"), height, width)
					}

				}

			} else if (media instanceof AlternativesMedia) {
				val altMedia = media.medias
				val choice = Utils.choose(altMedia)
				val filter = (choice as VideoDescription).filter
				val videoText = (choice as VideoDescription).getText
				var path = Filters.addText(choice.location, videoText,false)
				val locationChanged = (!path.equals(choice.location))
				path = Filters.filter(filter, path, locationChanged)
				videoToGif(path, Utils.rename(path, "videos", "gifs", "mp4", "gif"), height, width)
			}
		}
		return true;
	}
}
