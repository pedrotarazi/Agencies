import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;


public class AgencyServiceImplementation implements AgencyService{

    ReadURL readUrl = new ReadURL();

    Agency[] agencies;

    @Override
    public Agency[] readAgencies(String site_id, String payment_method_id, String near_to,
                                 String limit, String offset, String order_by) throws APIException{
        if (limit == null) { limit = ""; }
        if (offset == null) { offset = ""; }
        if (order_by == null) { order_by = ""; }
        if (site_id == null || payment_method_id == null || site_id.equals("") ||
                payment_method_id.equals("") || near_to == null || near_to.equals("")) {
            throw new APIException("ERROR_OBLIGATORIOS");
        }
        try {
            String url = "https://api.mercadolibre.com/sites/"+site_id+"/payment_methods/"+payment_method_id+
                    "/agencies?"+"near_to="+near_to+"&limit="+limit+"&offset="+offset;
            String data = readUrl.readUrl(url);
            data = data.substring(data.lastIndexOf('['));
            data = data.substring(0, data.length() - 1);
            agencies = new Gson().fromJson(data, Agency[].class);
            if (order_by != ""){
                if ( !(order_by.equals(OrderCriterio.ADDRESS_LINE.getCriterio()) ||
                    order_by.equals(OrderCriterio.DISTANCE.getCriterio()) ||
                    order_by.equals(OrderCriterio.AGENCY_CODE.getCriterio())) ){
                    throw new APIException("ERROR_CRITERIO");
                }
                agencies[0].setCriterio(order_by);
                agencies = Sort.sortByCriteria(agencies);
            }
            return agencies;
        } catch (IOException e){
            throw new APIException("ERROR_COMUNICATION_URL");
        }
    }

}

