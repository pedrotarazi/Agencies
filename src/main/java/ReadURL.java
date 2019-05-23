import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class ReadURL {

    private String url;

    public ReadURL(){}

    public String readUrl(String urlString) throws IOException {
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            //URLConnection connection = url.openConnection();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            System.out.println(code);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            int read = 0;
            StringBuffer buffer = new StringBuffer();
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } catch (UnknownHostException e){
            throw new IOException("503");
        } catch (IOException e){
            System.out.println("LA"+e);
            throw new IOException("algo");
        } finally {
            if (reader != null){
                reader.close();
            }
        }
    }
}
