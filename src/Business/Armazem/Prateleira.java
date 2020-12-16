package Business.Armazem;
import Business.Armazem.Palete;

import java.util.HashMap;
import java.util.Map;

public class Prateleira {
    private int prateleiraID;
    private int capacidade;
    private int ocupacao;
    private Map<String, Palete> paletes;

    public Prateleira(int prateleiraID, int capacidade, int ocupacao) {
        this.prateleiraID = prateleiraID;
        this.capacidade = capacidade;
        this.ocupacao = ocupacao;
        paletes = new HashMap<>();
    }

    public int getPrateleiraID() {
        return prateleiraID;
    }

    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    public boolean isPaleteValida (String qrCode) {
        return paletes.containsKey(qrCode);
    }

    public void addPalete (String qrCode, String tipoMaterial, String zona) {
        paletes.put(qrCode, new Palete(qrCode, tipoMaterial, prateleiraID, zona));
    }
}
