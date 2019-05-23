public enum StatusResponse {

    SUCCESS ("Success"),
    ERROR ("Error"),
    ERROR_CRITERIO ("Criterio erroneo"),
    ERROR_COMUNICATION_URL ("Error en comunicacion con API externa"),
    ERROR_COMUNICATION_URL_503 ("Error en comunicacion con API externa. 503"),
    ERROR_CIRCUIT_BREAKER ("Circuit Breaker evitó consultar la API Externa"),
    ERROR_OBLIGATORIOS ("En parametros obligatorios");


    private String status;

    StatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
