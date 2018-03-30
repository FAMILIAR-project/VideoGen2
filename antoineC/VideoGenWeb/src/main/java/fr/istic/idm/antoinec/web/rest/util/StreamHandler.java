package fr.istic.idm.antoinec.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        try {
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line = null;

            while ((line = reader.readLine()) != null) {
                content += line;
                log.debug("[VIDEOGENCOMPILER][{}] {}", type, line);
            }

        } catch (IOException e) {
            log.warn(e.getMessage());

            if (log.isWarnEnabled())
                e.printStackTrace();
        }
    }

    public String getContent() {
        return content;
    }
}
