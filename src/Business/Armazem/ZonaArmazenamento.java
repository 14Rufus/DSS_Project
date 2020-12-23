/**
 * Classe que representa a Zona de Armazenamento
 */

package Business.Armazem;
import java.util.HashMap;

import java.util.Map;

public class ZonaArmazenamento extends Zona {
    private String tipoZona;
    private Map<Integer, Prateleira> prateleiras;

    public ZonaArmazenamento(String zonaID,String tipo) {
        super(zonaID);
        tipoZona = tipo;
        prateleiras = new HashMap<>();
        for(int i = 1;i<=5;i++)
            prateleiras.put(i,new Prateleira(i, 5, 0));
    }

    /**
     * Verifica se o Código QR da Palete é válido
     *
     * @param qrCode Código QR
     * @return Validade da Palete
     */
    public boolean isPaleteValida (String qrCode) {
        boolean b = true;
        for (Prateleira p: prateleiras.values())
            if(!p.isPaleteValida(qrCode))
                b = false;
        return b;
    }

    /**
     * Escolhe a Prateleira em qual uma Palete vai ser colocoda
     *
     * @return Prateleira escolhida
     */
    public int escolhePrateleira(){
        Prateleira prateleira = null;
        int max = 0;
        for(Prateleira p : prateleiras.values()){
            if(p.getEspacoLivre() > max){
                prateleira = p;
                max = p.getEspacoLivre();
            }
        }
        return prateleira.getPrateleiraID();
    }

    /**
     * Adiciona uma Palete a uma Prateleira
     *
     * @param zonaID        ID da Zona
     * @param prateleira    Prateleira escolhida
     * @param qrCode        Código QR da Palete
     * @param tipoMaterial  tipo de material que a Palete contém
     */
    public void arrumaPalete (String zonaID,int prateleira,String qrCode, String tipoMaterial) {
        prateleiras.get(prateleira).addPalete(qrCode,tipoMaterial,zonaID);
    }
}
