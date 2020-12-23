/**
 * Classe que representa uma Prateleira
 */

package Business.Armazem;
import Business.Armazem.Palete;
import Data.PaleteDAO;

import java.util.HashMap;
import java.util.Map;

public class Prateleira {
    private int prateleiraID;
    private int capacidade;
    private int ocupacao;
    private PaleteDAO paletes;

    public Prateleira(int prateleiraID,int capacidade,int ocupacao) {
        this.prateleiraID = prateleiraID;
        this.capacidade = capacidade;
        this.ocupacao = ocupacao;
        paletes = new PaleteDAO();
    }

    /**
     * Devolve o ID da Prateleira
     *
     * @return ID da Prateleira
     */
    public int getPrateleiraID() {
        return prateleiraID;
    }

    /**
     * Altera ocupação da Prateleira
     *
     * @param ocupacao Ocupação da Prateleira
     */
    public void setOcupacao(int ocupacao) {
        this.ocupacao = ocupacao;
    }

    /**
     * Verifica se uma dada Palete é válida
     *
     * @param qrCode Código QR da Palete
     * @return Validade da Palete
     */
    public boolean isPaleteValida (String qrCode) {
        return !paletes.containsKey(qrCode);
    }

    public void setPrateleiraID(int prateleiraID) {
        this.prateleiraID = prateleiraID;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getOcupacao() {
        return ocupacao;
    }

    /**
     * Adiciona uma Palete
     *
     * @param qrCode       Código QR da Palete
     * @param tipoMaterial Tipo de Material da Palete
     * @param zona         Zona da Prateleira
     */
    public void addPalete (String qrCode, String tipoMaterial, String zona) {
        int n = paletes.sizeLocalizacao();
        paletes.put(new Palete(qrCode, tipoMaterial, prateleiraID, zona,n+1));
        ocupacao++;
    }

    /**
     * Devovle o espaço livre da Prateleira
     *
     * @return Espaço livre
     */
    public int getEspacoLivre(){
        return (capacidade - ocupacao);
    }

}
