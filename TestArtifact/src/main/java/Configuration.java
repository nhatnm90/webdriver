import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {

    private static Properties getConfiguration() {
        URL resource = Configuration.class.getResource("config.properties");
        File file  = null;
        try {
            file = Paths.get(resource.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getHostName() {
        Properties prop = Configuration.getConfiguration();
        return prop.getProperty("host");
    }
}
