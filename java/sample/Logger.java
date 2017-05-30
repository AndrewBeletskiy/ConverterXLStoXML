package sample;

import com.sun.xml.internal.ws.api.message.Message;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import javafx.scene.shape.Path;

import java.io.*;

/**
 * Created by Андрій on 22.11.2016.
 */
public class Logger {
    private BufferedWriter file;

    public Logger(String fileName) {
        BufferedWriter writer = null;
        try {
            File logFile = new File(fileName);
            if (!logFile.exists())
                logFile.createNewFile();
            file = new BufferedWriter(new FileWriter(logFile));

        } catch(Exception ex) {

        }

    }
    public void write(String logText) {
        try {

            file.write(logText);
            file.close();
        } catch(Exception ex) {

        }
    }

}
