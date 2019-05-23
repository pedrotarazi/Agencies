/*
* Integrantes:
*  Garbiglia, Diego
*  Morichetti, Cesar
*  Sardoy, Juan
*  Tarazi, Pedro
*
* Funcionamiento:
* El Circuit Breaker entra en funcionamiento cuando no se puede conectar a la API de Mercado Libre.
* Luego de 3 intentos sin conexion, la API devuelve error de conexion, abriendo el circuito.
* Despues de 10 segundos, el circuito se cierra y vuelve a funcionar.
* */

import com.google.gson.Gson;
import net.jodah.failsafe.CircuitBreaker;

import java.time.Duration;
import java.util.Date;

import static spark.Spark.*;

public class API {

    public static void main(String[] args) {
        Duration time = Duration.ofSeconds(10);
        CircuitBreaker breaker = new CircuitBreaker();
        breaker.withTimeout(time);
        breaker.withFailureThreshold(3);
        final AgencyService agencyService = new AgencyServiceImplementation();
        final DataAccess dao = new DataAccess();
        long seconds = 30;
        try {
            dao.new_file("info.log");
        }catch (APIException e){
            System.out.println("Error al crear el archivo");
        }

        get("/agencies", (request, response) -> {
            response.type("application/json");
            String text = new Date() + "\t\t" + request.requestMethod() + "->\t" + request.url() + "?" + request.queryString() + "\n";
            dao.open_file("info.log");
            dao.write_file(text);
            // Obligatorios
            String site_id = request.queryParams("site_id");
            String payment_methods_id = request.queryParams("payment_methods_id");
            String near_to = request.queryParams("near_to");
            // Opcionales
            String limit = request.queryParams("limit");
            String offset = request.queryParams("offset");
            String order_by = request.queryParams("order_by");

            String rta = "";
            try {
                if(breaker.allowsExecution()) {
                    breaker.preExecute();
                    rta = new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,
                            new Gson().toJsonTree(agencyService.readAgencies(site_id, payment_methods_id,
                                    near_to, limit, offset, order_by))));
                    breaker.recordSuccess();
                    return rta;
                }
                else {
                    rta = new Gson().toJson(new StandardResponse(StatusResponse.ERROR_CIRCUIT_BREAKER,
                            StatusResponse.ERROR_CIRCUIT_BREAKER.getStatus()));
                }
            } catch (APIException e){
                if (e.getMessage().equals("ERROR_CRITERIO")) {
                    //breaker.recordFailure();
                    rta = new Gson().toJson(new StandardResponse(StatusResponse.ERROR_CRITERIO,
                            "Error en criterio"));
                }
                else if (e.getMessage().equals("ERROR_COMUNICATION_URL")) {
                    //breaker.recordFailure();
                    rta = new Gson().toJson(new StandardResponse(StatusResponse.ERROR_COMUNICATION_URL,
                            "Error en comunicacion con URL"));
                }
                else if (e.getMessage().equals("ERROR_COMUNICATION_URL_503")) {
                    breaker.recordFailure();
                    response.status(503);
                    rta = new Gson().toJson(new StandardResponse(StatusResponse.ERROR_COMUNICATION_URL_503,
                            "Error en comunicacion con URL. 503"));
                }
                else if (e.getMessage().equals("ERROR_OBLIGATORIOS")){
                    //breaker.recordFailure();
                    rta = new Gson().toJson(new StandardResponse(StatusResponse.ERROR_OBLIGATORIOS,
                            "Faltan parametros obligatorios"));
                }
                else {
                    breaker.recordFailure();
                    //response.status(500);
                    rta = new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Error en API externa"));
                }
            } finally {
                dao.close_file();
                return rta;
            }
        });
    }
}
