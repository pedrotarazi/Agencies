import java.util.Collection;

public interface AgencyService {

    Agency[] readAgencies(String site_id, String payment_method_id, String limit, String offset);

}
