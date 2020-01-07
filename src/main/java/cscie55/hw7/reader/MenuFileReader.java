package cscie55.hw7.reader;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import cscie55.hw7.foodservice.Dish;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class MenuFileReader {

    public List<Dish> read(String fileName){

        ObjectMapper mapper = new ObjectMapper();
        Path interPath = Paths.get("src", "main", "resources");
        Path path = Paths.get(System.getProperty("user.dir"), interPath.toString(), fileName);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        File f = new File(path.toString());
        List<Dish> menu = null;
        if(f.exists()){
            // uses file system path. Works when adding the resource dir to pom.
            try {
                Dish[] obj = mapper.readValue(new File(path.toString()), Dish[].class);
                menu = Arrays.asList(obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return menu;
    }
}
