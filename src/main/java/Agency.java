public class Agency implements Comparable<Agency>{

    private Address address;
    private String agency_code;
    private String correspondent_id;
    private String description;
    private String disable;
    private String distance;
    private String id;
    private String payment_method_id;
    private String phone;
    private String site_id;
    private String terminal;

    private static OrderCriterio criterio;

    public Agency(){}

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAgency_code() {
        return agency_code;
    }

    public void setAgency_code(String agency_code) {
        this.agency_code = agency_code;
    }

    public String getCorrespondent_id() {
        return correspondent_id;
    }

    public void setCorrespondent_id(String correspondent_id) {
        this.correspondent_id = correspondent_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public OrderCriterio getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        if (criterio.equals(OrderCriterio.ADDRESS_LINE.getCriterio())) {
            this.criterio = OrderCriterio.ADDRESS_LINE;
        }
        else if (criterio.equals(OrderCriterio.AGENCY_CODE.getCriterio())){
            this.criterio = OrderCriterio.AGENCY_CODE;
        }
        else if (criterio.equals(OrderCriterio.DISTANCE.getCriterio())){
            this.criterio = OrderCriterio.DISTANCE;
        }
    }

    @Override
    public int compareTo(Agency o) {
        switch (getCriterio().getCriterio()){
            case ("address_line"): {
                if (o.getAddress().getAddress_line().compareTo(getAddress().getAddress_line()) < 0){
                    return 1;
                }
                else if (o.getAddress().getAddress_line().compareTo(getAddress().getAddress_line()) > 0){
                    return -1;
                }
                else {
                    return 0;
                }
            }
            case ("agency_code"): {
                if (new Integer(o.getAgency_code()) < new Integer(getAgency_code())) {
                    return 1;
                } else if (new Integer(o.getAgency_code()) > new Integer(getAgency_code())) {
                    return -1;
                } else {
                    return 0;
                }
            }
            case ("distance"): {
                if ((new Double(o.getDistance())) < (new Double(getDistance()))) {
                    return 1;
                } else if (new Double(o.getDistance()) > (new Double(getDistance()))) {
                    return -1;
                } else {
                    return 0;
                }
            }
            default: return 0;
        }
    }
}
