package fr.istic.idm.antoinec.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileUploader {
    private static Logger log = LoggerFactory.getLogger(FileUploader.class);

    boolean upload(String rawBase64File, File dest) {
        try {
            if(!dest.createNewFile())
                throw new IOException("Unable to create file " + dest.getPath());
            String base64Binary = rawBase64File.split(",")[1];
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Binary);

            FileOutputStream stream = new FileOutputStream(base64Binary);
            stream.write(imageBytes);
            stream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }

        return true;

    }
}
