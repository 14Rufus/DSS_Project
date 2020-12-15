package Business.Armazem;

public class Localizacao {
    private String prateleira;
    private String zonaID;

    public Localizacao(String prateleira, String zonaID) {
        this.prateleira = prateleira;
        this.zonaID = zonaID;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public String getZonaID() {
        return zonaID;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public void setZonaID(String zonaID) {
        this.zonaID = zonaID;
    }
}
