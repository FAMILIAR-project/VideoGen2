import java.io.PrintWriter
import java.util.ArrayList
import java.util.Random
import org.eclipse.emf.common.util.URI
import org.junit.Test
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.ImageDescription
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription

import static org.junit.Assert.*

class VideoGenTest1 {

	CSVFileWriter csvfile=new CSVFileWriter
	int poss = 0
	int alt = 0
	int i
	int j
	val random = new Random()
	ArrayList<VideoDescription> playlist
	ArrayList<ArrayList<VideoDescription>> playlists
	String playmsg = ""
	int totalvideos = 0
	int opt = 0
	PrintWriter writer
	ArrayList<VideoDescription> longplaylist = new ArrayList<VideoDescription>
	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"))
	long longestAltDuration = 0
	int longestPosition
	ArrayList<VideoDescription> videos = new ArrayList<VideoDescription>
	String videoname;

	/**
	 * The launcher app
	 * 
	 */
	@Test
	def void testLoadModel() {
		assertNotNull(videoGen)
		println(videoGen.information.authorName)
		totalvideos = getPossility()

		println("total playlists :" + totalvideos)

		playlists = new ArrayList<ArrayList<VideoDescription>>
		for (i = 0; i < totalvideos; i++) {

			playlists.add(new ArrayList<VideoDescription>)

		}

		videoGen.medias.forEach [ m |

			if (m instanceof MandatoryMedia) {

				if (m.description instanceof VideoDescription) {
					// if video
					videos.add(m.description as VideoDescription)
					for (i = 0; i < totalvideos; i++) {

						playlists.get(i).add(m.description as VideoDescription)
					// println(m.description.location)
					}
					playmsg = playmsg + m.description.location + "\n"
					longplaylist.add(m.description as VideoDescription)

				} else { // if image
					if (m.description instanceof ImageDescription) {
						println("image found :" + (m.description as ImageDescription).top + "AND " +
							(m.description as ImageDescription).bottom)
					}
				}
			}
			if (m instanceof OptionalMedia) {
				if (m.description instanceof VideoDescription) {

					// if video
					videos.add(m.description as VideoDescription)
					for (i = 0; i < totalvideos / 2; i++) {

						playlists.get(i).add(m.description as VideoDescription)

					}
					longplaylist.add(m.description as VideoDescription)
					playmsg = playmsg + m.description.location + "\n"
				} else { // if image
					if (m.description instanceof ImageDescription) {
						println("image found :" + (m.description as ImageDescription) + "AND " +
							(m.description as ImageDescription))

					}

				}

			}
			if (m instanceof AlternativesMedia) {
				alt = m.medias.size
				for (j = 0; j < m.medias.size; j++) {
					if (m.medias.get(j) instanceof VideoDescription) {
						// if video
						videos.add(m.medias.get(j) as VideoDescription)

						for (i = poss; i < totalvideos; i = i + alt) {

							playlists.get(i).add(m.medias.get(j) as VideoDescription)
							playmsg = playmsg + m.medias.get(j).location + "\n"

						}

						if (Util.getFileDuration(m.medias.get(j).location).longValue > longestAltDuration) {
							longestAltDuration = Util.getFileDuration(m.medias.get(j).location).longValue
							longestPosition = j
						}
						poss++
					} else { // if image
						if (m.medias.get(j) instanceof ImageDescription) {
							println("image found :" + (m.medias.get(j) as ImageDescription).top + "AND " +
								(m.medias.get(j) as ImageDescription).bottom)

						}

					}

				}
				longplaylist.add(m.medias.get(longestPosition) as VideoDescription)

				j = 0
				poss = 0
			}
		]

		// display of the playlists with the videos in them
		println("Debut*************************\n")
		println("playlists   :\n")
		playlists.forEach [ list |
			println("element:\n")
			list.forEach [ element |

				print(element.videoid + ";")

			]
			println("\n")
		]
		println("\n")

		println("Fin*************************\n")

		// get a random playlist from the playlists
		playlist = playlists.get(random.nextInt(totalvideos))
		println("Creating files ://////////")
		creatingVideoFiles()
		 createCSV()
		// executeVLC()
		assertEquals(totalvideos, playlists.size)
	// createAllGIT()
	}

	/**
	 * Get the number of possible playlists from the videogen file
	 * 
	 */
	def int getPossility() {
		videoGen.medias.forEach [ m |

			if (m instanceof OptionalMedia) {

				opt = opt + 2
			}
			if (m instanceof AlternativesMedia) {

				if (alt == 0) {
					alt = alt + m.medias.size()
				} else {
					alt = alt * m.medias.size()
				}

			}
		]
		if(opt == 0) opt++
		if(alt == 0) alt++

		return opt * alt

	}

	def creatingVideoFiles() {
		playlists.forEach [ playlist |

			createfffFile(playlist)
			csvfile.addSize()

		]

	}

	/**
	 * create a playlist.txt file for ffmpeg and concatenate from this file
	 */
	def createfffFile(ArrayList<VideoDescription> playlist) {
		// create file
		writer = new PrintWriter("videos/playlist.txt", "UTF-8")
		writer.println("#bref playlist");
		playlist.forEach [ video |
			videoname = video.location.replace("videos/", "")
			writer.println("file '" + videoname + "'");

		]
		writer.close();

		// run ffmpeg to concatenate
		Runtime.runtime.exec("rm bref.mp4")
		// println("ffmpeg -f concat -safe 0 -i videos/playlist.txt -c copy bref.mp4")
		var p=Runtime.runtime.exec("ffmpeg -f concat -safe 0 -i videos/playlist.txt -c copy bref.mp4")
		p.waitFor
	}

	/**
	 * Launch vlc media player with the generated video
	 */
	def executeVLC() {
		// run vlc player
		var p = Runtime.runtime.exec("vlc  videos/bref.mp4")
		p.waitFor
	}

	def longest() {

		// launch the longest video with vlc
		var p = Runtime.runtime.exec("vlc  videos/long.mp4")
		p.waitFor

	}

	/**
	 * Create a CSV file with the size and lenght information
	 */
	def createCSV() {
		// create a CSV file
		
		csvfile.writeCsvFile(videos, playlists)

	}

	/**
	 * generate a GIF for the videos in the videogen file
	 */
	def createGIF(VideoDescription videodescription) {

		// create a gif for the video
		if (videodescription.videoid !== null) {
			Runtime.runtime.exec("rm " + videodescription.videoid + ".gif")
			println("ffmpeg -i " + videodescription.location + "  " + videodescription.videoid + ".gif")
			var p = Runtime.runtime.exec(
				"ffmpeg -i " + videodescription.location + "  " + videodescription.videoid + ".gif")
			p.waitFor
		} else {
			var p = Runtime.runtime.exec("ffmpeg -i " + videodescription.location + ".gif")
			p.waitFor
		}
	}

	def createAllGIT() {
		videos.forEach [ video |

			createGIF(video)

		]
		println("Gifs created")

	}

}
