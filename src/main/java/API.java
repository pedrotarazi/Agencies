import com.google.gson.Gson;

import static spark.Spark.*;

public class API {

    public static void main(String[] args) {

        final AgencyService agencyService = new AgencyServiceImplementation();

        get("/agencies", (request, response) -> {
            response.type("application/json");
            // Obligatorios
            String site_id = request.queryParams("site_id");
            String payment_methods_id = request.queryParams("payment_methods_id");
            String near_to = request.queryParams("near_to");
            // Opcionales
            String limit = request.queryParams("limit");
            String offset = request.queryParams("offset");
            String order_by = request.queryParams("order_by");

            if (site_id == null || payment_methods_id == null || site_id.equals("") ||
                    payment_methods_id.equals("") || near_to == null || near_to.equals("")){
                return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
                        "Faltan parametros obligatorios"));
            }
            else {
                try {
                    return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                            new Gson().toJsonTree(agencyService.readAgencies(site_id, payment_methods_id,
                                    near_to, limit, offset, order_by))));
                } catch (APIException e){
                    if (e.getMessage().equals("ERROR_CRITERIO")) {
                        return new Gson().toJson(new StandardResponse(StatusResponse.ERROR_CRITERIO,
                                "Error en criterio"));
                    }
                    else if (e.getMessage().equals("ERROR_COMUNICATION_URL")) {
                        return new Gson().toJson(new StandardResponse(StatusResponse.ERROR_COMUNICATION_URL,
                                "Error en comunicacion con URL"));
                    }
                    return  new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Error en API externa"));
                }
            }
        });
    }
}
