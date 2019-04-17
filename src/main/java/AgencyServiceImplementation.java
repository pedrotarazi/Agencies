import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class AgencyServiceImplementation implements AgencyService{

    ReadURL readUrl = new ReadURL();

    @Override
    public Agency[] readAgencies(String site_id, String payment_method_id, String limit, String offset) {
        try {
            if (limit == null) { limit = ""; }
            if (offset == null) { offset = ""; }
            String url = "https://api.mercadolibre.com/sites/"+site_id+"/payment_methods/"+payment_method_id+
                    "/agencies?limit="+limit+"&offset="+offset;
            String data = readUrl.readUrl(url);
            data = data.substring(data.lastIndexOf('['));
            data = data.substring(0, data.length() - 1);
            Agency[] agencies = new Gson().fromJson(data, Agency[].class);
            return agencies;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }
}
