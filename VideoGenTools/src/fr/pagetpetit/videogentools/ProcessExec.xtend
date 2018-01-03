package fr.pagetpetit.videogentools

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList

class ProcessExec {
	static def void executeCommand(String[] command) {

		val builder = new ProcessBuilder(command)
		builder.inheritIO

		val process = builder.start

		process.waitFor

		process.destroy
	}

	static def String[] executeCommandIO(String[] command) {

		val builder = new ProcessBuilder(command)
		//builder.inheritIO
		builder.redirectErrorStream(true)
		val process = builder.start

		val reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		var line = ""
		line = null
		val IO = new ArrayList
		while ((line = reader.readLine) !== null) {
			IO.add(line)
		}
		
		process.waitFor
		
		process.destroy
		
		return IO;
	}
}
