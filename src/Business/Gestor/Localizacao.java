package Business.Gestor;

public class Localizacao {
    private int prateleira;
    private String zonaID;
    private String robotID;

    public Localizacao(int prateleira, String zonaID) {
        this.prateleira = prateleira;
        this.zonaID = zonaID;
        robotID = "";
    }

    public int getPrateleira() {
        return prateleira;
    }

    public String getZonaID() {
        return zonaID;
    }

    public void setPrateleira(int prateleira) {
        this.prateleira = prateleira;
    }

    public void setZonaID(String zonaID) {
        this.zonaID = zonaID;
    }
}
