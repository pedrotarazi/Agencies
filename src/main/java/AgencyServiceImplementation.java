import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;


public class AgencyServiceImplementation implements AgencyService{

    ReadURL readUrl = new ReadURL();

    Agency[] agencies;

    @Override
    public Agency[] readAgencies(String site_id, String payment_method_id, String near_to,
                                 String limit, String offset, String order_by) {
            if (limit == null) { limit = ""; }
            if (offset == null) { offset = ""; }
            if (order_by == null) { order_by = ""; }
        try {
            String url = "https://api.mercadolibre.com/sites/"+site_id+"/payment_methods/"+payment_method_id+
                    "/agencies?"+"near_to="+near_to+"&limit="+limit+"&offset="+offset;
            String data = readUrl.readUrl(url);
            data = data.substring(data.lastIndexOf('['));
            data = data.substring(0, data.length() - 1);
            agencies = new Gson().fromJson(data, Agency[].class);
            if (order_by != ""){
                agencies[0].setCriterio(order_by);
                agencies = Sort.sortByCriteria(agencies);
            }
            return agencies;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}

