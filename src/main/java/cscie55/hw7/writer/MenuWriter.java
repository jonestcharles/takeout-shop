package cscie55.hw7.writer;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import cscie55.hw7.foodservice.Dish;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;


/**
 * MenuWriter class generates file output.
 */

public class MenuWriter {

    /**
     *
     * @param menuFileName - a string used as file name, must include extension
     *   with or w/o a file extension, it will publish a json file
     * @param menu - A List<Dish> object that can serve as a menu. Maybe more
     */
    public static void publish(String menuFileName, List<Dish> menu) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
// as a default, we'll use menu.json as the file name
        Path interPath = Paths.get("src", "main", "resources");
        String fileName = (menuFileName.equals(null)|| menuFileName.equals("") || menuFileName.equals(" ") ) ? "menu.json" : menuFileName;
        Path path = Paths.get(System.getProperty("user.dir"), interPath.toString(), fileName);

        File f = new File(path.toString());
            try {
                if(f.exists())
                    f.delete();
                f.createNewFile();
                String item = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(menu);
                Files.write(new File(path.toString()).toPath(), Arrays.asList(item), StandardOpenOption.APPEND);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

    }
}
