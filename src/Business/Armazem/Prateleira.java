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

    /**
     * Altera ID da Prateleira
     *
     * @param prateleiraID novo ID da Prateleria
     */
    public void setPrateleiraID(int prateleiraID) {
        this.prateleiraID = prateleiraID;
    }

    /**
     * Devolve a capacidade da Prateleira
     *
     * @return Capacidade da Prateleira
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Altera a capacidade da Prateleira
     *
     * @param capacidade nova capacidade da Prateleira
     */
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * Devolve a ocupação da Prateleira
     *
     * @return Ocupação da Prateleira
     */
    public int getOcupacao() {
        return ocupacao;
    }

    /**
     * Adiciona uma Palete
     *
     * @param qrCode       Código QR da Palete
     * @param zona         Zona da Prateleira
     */
    public void addPalete (String qrCode, String zona) {
        int n = paletes.sizeLocalizacao();
        paletes.put(new Palete(qrCode, prateleiraID, zona,n+1));
        ocupacao++;
    }

    /**
     * Devolve o espaço livre da Prateleira
     *
     * @return Espaço livre
     */
    public int getEspacoLivre(){
        return (capacidade - ocupacao);
    }

}
