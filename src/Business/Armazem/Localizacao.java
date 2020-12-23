package Business.Armazem;

public class Localizacao {
    private int idLocalizacao;
    private int prateleira;
    private String zonaID;

    public Localizacao(int id,int prateleira, String zonaID) {
        idLocalizacao = id;
        this.prateleira = prateleira;
        this.zonaID = zonaID;
    }

    public int getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(int idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
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
