import java.io.PrintWriter
import java.util.ArrayList
import java.util.Random
import org.eclipse.emf.common.util.URI
import org.junit.Test
import org.xtext.example.mydsl.videoGen.AlternativesMedia
import org.xtext.example.mydsl.videoGen.MandatoryMedia
import org.xtext.example.mydsl.videoGen.OptionalMedia
import org.xtext.example.mydsl.videoGen.VideoDescription

import static org.junit.Assert.*

class VideoGenTest1 {

	CSVFileWriter csvfile
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
	var writer = new PrintWriter("videos/playlist.txt", "UTF-8")
	ArrayList<VideoDescription> longplaylist = new ArrayList<VideoDescription>
	val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"))
	long longestAltDuration = 0
	int longestPosition
	ArrayList<VideoDescription> videos = new ArrayList<VideoDescription>

	@Test
	def void testLoadModel() {
		assertNotNull(videoGen)
		println(videoGen.information.authorName)
		// println(startcommand+command)
		// and then visit the model
		// eg access video sequences: videoGen.videoseqs
		// getPossiility()
		totalvideos = getPossility()

		println("total :" + totalvideos)

		playlists = new ArrayList<ArrayList<VideoDescription>>
		for (i = 0; i < totalvideos; i++) {

			playlists.add(new ArrayList<VideoDescription>)

		// println("1")		
		}
		videoGen.medias.forEach [ m |

			if (m instanceof MandatoryMedia) {
				videos.add(m.description as VideoDescription)
				for (i = 0; i < totalvideos; i++) {

					playlists.get(i).add(m.description as VideoDescription)
				}
				playmsg = playmsg + m.description.location + "\n"
				longplaylist.add(m.description as VideoDescription)

			// medias.add (new Media(m.description.location,1 ))
			}
			if (m instanceof OptionalMedia) {
				videos.add(m.description as VideoDescription)
				// medias.add (new Media(m.description.location,2 ))
				for (i = 0; i < totalvideos / 2; i++) {

					playlists.get(i).add(m.description as VideoDescription)

				}
				longplaylist.add(m.description as VideoDescription)
				playmsg = playmsg + m.description.location + "\n"

			}
			if (m instanceof AlternativesMedia) {
				// medtmp= new Media ("",3) 
				alt = m.medias.size
				for (j = 0; j < m.medias.size; j++) {
					videos.add(m.medias.get(j) as VideoDescription)
					// altmedia.medias.add (new Media (alter.location.toString  )); 
					// medtmp.altmedias.add (new Media(alter.location,1 ))
					for (i = poss; i < totalvideos; i = i + alt) {

						playlists.get(i).add(m.medias.get(j) as VideoDescription)
						playmsg = playmsg + m.medias.get(j).location + "\n"

					}
					// println("Here is the standard output of the command:\n");
					if (Util.getFileDuration(m.medias.get(j).location).longValue > longestAltDuration) {
						longestAltDuration = Util.getFileDuration(m.medias.get(j).location).longValue
						longestPosition = j
					}
					println("*****************" + Util.getFileDuration(m.medias.get(j).location))
					poss++

				}
				longplaylist.add(m.medias.get(longestPosition) as VideoDescription)

				j = 0
				poss = 0
			}
		]

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

		// println(random.nextInt(totalvideos))
		playlist = playlists.get(random.nextInt(totalvideos))
		createfffFile()
		//executeVLC()
		//createCSV()

	}

	def int getPossility() {
		videoGen.medias.forEach [ m |

			if (m instanceof OptionalMedia) {
				// random
				// idem
				opt = opt + 2
			}
			if (m instanceof AlternativesMedia) {
				// random with choice
				// idem
				if (alt == 0) {
					alt = alt + m.medias.size()
				} else {
					alt = alt * m.medias.size()
				}

			// println("alternative "+m.medias.size)		
			}
		]
		if(opt == 0) opt++
		if(alt == 0) alt++

		return opt * alt

	}

	def createfffFile() {
		writer.println("#bref playlist");
		playlist.forEach [ video |
			writer.println("file '" + video.location + "'");

		]
		writer.close();

		Runtime.runtime.exec("ffmpeg -f concat -safe 0 -i ./playlist.txt -c copy ./out.mp4")
	}

	def executeVLC() {
		// file:///home/chak/Documents/IDM/projet/VideoGen2/CbenUnique/bref.mp4
		var p = Runtime.runtime.exec("vlc  videos/bref.mp4")
		p.waitFor
	}

	def longest() {
		var p = Runtime.runtime.exec("vlc  videos/long.mp4")
		p.waitFor

	}

	def createCSV() {
		csvfile = new CSVFileWriter
		csvfile.writeCsvFile(videos, playlists)

	}
}
