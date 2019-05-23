import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class DataAccess {

    FileWriter fw;

    public void new_file(String name) throws APIException{
        try{
            fw = new FileWriter(name);
        }catch (IOException e){
            throw new APIException("ERROR");
        }
    }

    public void open_file(String name) throws APIException{
        try{
            fw = new FileWriter(name, true);
        }catch (IOException e){
            throw new APIException("ERROR");
        }
    }

    public void write_file(String text) throws APIException{
        try {
            fw.write(text);
        }catch (IOException e){
            throw new APIException("ERROR");
        }
    }

    public void close_file() throws APIException{
        try{
            fw.close();
        }catch (IOException e){
            throw new APIException("ERROR");
        }
    }
}
