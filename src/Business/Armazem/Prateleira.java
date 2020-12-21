package Business.Armazem;
import Business.Armazem.Palete;
import Data.PaleteDAO;

import java.util.HashMap;
import java.util.Map;

public class Prateleira {
    private int prateleiraID;
    private int capacidade;
    private int ocupacao;
    private Map<String, Palete> paletes;

    public Prateleira(int prateleiraID) {
        this.prateleiraID = prateleiraID;
        this.capacidade = 5;
        this.ocupacao = 0;
        paletes = PaleteDAO.getInstance();
    }

    public int getPrateleiraID() {
        return prateleiraID;
    }

    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    public boolean isPaleteValida (String qrCode) {
        return !paletes.containsKey(qrCode);
    }

    public void addPalete (String qrCode, String tipoMaterial, String zona) {
        paletes.put(qrCode, new Palete(qrCode, tipoMaterial, prateleiraID, zona));
        ocupacao++;
    }

    public int getEspacoLivre(){
        return (capacidade - ocupacao);
    }

}
