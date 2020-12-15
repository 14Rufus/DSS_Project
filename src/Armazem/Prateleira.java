package Armazem;

import java.util.HashMap;
import java.util.Map;

public class Prateleira {
    private String prateleiraID;
    private int capacidade;
    private int ocupacao;
    private Map<String,Palete> paletes;

    public Prateleira(String prateleiraID, int capacidade, int ocupacao) {
        this.prateleiraID = prateleiraID;
        this.capacidade = capacidade;
        this.ocupacao = ocupacao;
        paletes = new HashMap<>();
    }

    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }
}
