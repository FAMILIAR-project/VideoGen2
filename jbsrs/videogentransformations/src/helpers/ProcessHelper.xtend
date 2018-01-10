package helpers

import java.util.List
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList

class ProcessHelper {

	static def void execute(List<String> commands){
		val processBuilder = new ProcessBuilder(commands);
		processBuilder.inheritIO
		val process = processBuilder.start
		process.waitFor
		process.destroy
	}
	
	static def String[] executeAndGetIOStream(List<String> commands) {

		val processBuilder = new ProcessBuilder(commands)
		processBuilder.redirectErrorStream(true)
		val process = processBuilder.start

		val bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		var line = ""
		line = null
		val IOStream = new ArrayList
		while ((line = bufferReader.readLine) !== null) {
			IOStream.add(line)
		}
		
		process.waitFor
		
		process.destroy
		
		return IOStream;
	}
}