package fr.istic.m2il.idm.videogenapp.service.util;

import java.io.*;

/**
 * @author ismael
 */

public class DevUtils {

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            //is.close();
            //os.close();
        }
    }
}
