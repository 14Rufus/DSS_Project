/**
 * Classe que representa a Zona de receção
 */

package Business.Armazem;
import Data.PaleteDAO;

import java.util.Collection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ZonaRececao extends Zona {
    private PaleteDAO paletes;

    public ZonaRececao(String zonaID) {
        super(zonaID);
        paletes = new PaleteDAO();
    }

    /**
     * Verifica se a Palete é válida
     *
     * @param qrCode Código QR da Palete
     * @return  Validade da Palete
     */
    public boolean isPaleteValida (String qrCode) {
        return !paletes.containsKey(qrCode);
    }

    /**
     * Acrescenta uma Palete à Zona de Receção
     *
     * @param qrCode        Código QR da Palete
     * @param tipoMaterial  Tipo de Material da Palete
     */
    public void acrescentaPalete (String qrCode, String tipoMaterial) {
        int n = paletes.sizeLocalizacao();
        Palete palete = new Palete(qrCode, tipoMaterial, 0, super.getZonaID(),n + 1);
        paletes.put(palete);
    }

    /**
     * Verifica se uma determinada Palete existe na Zona de receção
     *
     * @param qrCode Código QR da Palete
     * @return  Existência da Palete
     */
    public Boolean existePalete (String qrCode){
        return paletes.containsKey(qrCode);
    }

    /**
     * Recolha de uma Palete da Zona de Receção
     *
     * @param qrCode Código QR da Palete
     */
    public void recolhePalete(String qrCode){
        paletes.get(qrCode).setZonaID(null);
    }

}
