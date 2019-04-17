import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import spark.Spark.*;

public class AgenciesAPI {

    public static void main(String[] args) {
        /*String nameSite;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el pais: ");
        nameSite = scanner.next();
        String code = "";*/
        /*try{
            String url = readUrl("https://api.mercadolibre.com/sites/MLA/payment_methods/rapipago/agencies");
            url = url.substring(url.lastIndexOf('['));
            url = url.substring(0, url.length()-1);

            Agency[] agencies = new Gson().fromJson(url, Agency[].class);
            for(Agency agency : agencies){
                System.out.println(agency.getAddress().getAddress_line());
            }
        } catch(IOException e){
            System.out.println("Ocurrio un error al traer las categorias");
            e.printStackTrace();
        }*/
    }

    //private static String readUrl(String urlString) throws IOException {

        /*BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            int read = 0;
            StringBuffer buffer = new StringBuffer();
            char[] chars = new char[1024];
            while((read = reader.read(chars)) != -1){
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null){
                reader.close();
            }
        }
    }*/
}
