package bll.helpers;
/**
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

//https://www.w3schools.com/java/java_files_create.asp
//https://stackoverflow.com/questions/6366743/saving-files-to-a-specific-directory-in-java
//https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
public class LogFile {
    private final FileWriter fw = new FileWriter("/gui/view/LogFile/ActionsLogFile");
    public LogFile() throws IOException {


    }

    private final Logger logger = Logger.getLogger(LogFile.class.getName());
    private FileHandler fh = null;

    public LogFile(String name) throws IOException {
        //just to make our log file nicer :)
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler("/gui/view/LogFile/ActionsLogFile"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                SimpleDateFormat logTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                Calendar cal = new GregorianCalendar();
                cal.setTimeInMillis(record.getMillis());
                return record.getLevel()
                        + logTime.format(cal.getTime())
                        + " || "
                        + record.getSourceClassName().substring(
                        record.getSourceClassName().lastIndexOf(".")+1)
                        + "."
                        + record.getSourceMethodName()
                        + "() : "
                        + record.getMessage() + "\n";
            }
        });
        logger.addHandler(fh);
    }

    public void saveLogin() throws IOException {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage i = robot.createScreenCapture(new Rectangle(1920, 1080));
        File output = new File("./save.png");
        try {
            ImageIO.write(i, "png", output);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void writeToLog(){
        logger.info("Hello");
    }

    public static void main(String[] args) throws IOException {
        LogFile logFile = new LogFile("job");
        logFile.writeToLog();

    }
}
 */
