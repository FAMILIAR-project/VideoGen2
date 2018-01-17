package fr.istic.m2il.idm.videogentransformations.helpers

import java.util.List
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList

/**
 * @author Ramadan Soumaila
 * A helper class to execute command
 */
class ProcessHelper {

	/**
	 * Executes a list of command son the system
	 * @param the list of commands to executes
	 * 
	 */
	static def void execute(List<String> commands){
		val processBuilder = new ProcessBuilder(commands);
		processBuilder.inheritIO
		val process = processBuilder.start
		process.waitFor
		process.destroy
	}
	
	/**
	 * Executes a list of command son the system
	 * @param the list of commands to executes
	 * @return a array of output stream
	 */
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