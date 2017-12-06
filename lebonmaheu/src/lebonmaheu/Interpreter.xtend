package lebonmaheu

import static org.junit.Assert.*
import org.eclipse.emf.common.util.URI

class Interpreter {
	def public static void main(String[] args) {
		val videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"))
		assertNotNull(videoGen)
		println(videoGen.information.authorName)
	}
}