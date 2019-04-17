import java.util.Collection;

public interface AgencyService {

    Agency[] readAgencies(String site_id, String payment_method_id, String near_to,
                          String limit, String offset, String order_by);

}
