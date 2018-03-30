package fr.istic.idm.ffmpeg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Here to handle result stream of FFMPEG Subprocesses called by Java
 * @author Antoine Charpentier
 *
 */
public class StreamHandler implements Runnable {
	private static Logger log = LoggerFactory.getLogger(StreamHandler.class);
	
	private InputStream stream;
	private String type;
	
	private String content;

	public StreamHandler(InputStream stream, String type) {
		this.stream = stream;
		this.type = type;
		this.content = "";
	}

	@Override
	public void run() {
		try
		{
			InputStreamReader streamReader = new InputStreamReader(stream);
			BufferedReader reader = new BufferedReader(streamReader);
			String line = null;
			
			while ( (line = reader.readLine()) != null) {
				content += line;
				log.info("[FFMPEG][{}] {}", type, line);
			}
			
		} catch (IOException e) {
			log.warn(e.getMessage());
			
			if(log.isWarnEnabled())
				e.printStackTrace();  
		}
	}

	public String getContent() {
		return content;
	}

}
