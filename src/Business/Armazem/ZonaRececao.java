package Business.Armazem;
import Data.PaleteDAO;

import java.util.Collection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ZonaRececao extends Zona {
    private Map<String,Palete> paletes;

    public ZonaRececao(String zonaID) {
        super(zonaID);
        paletes = PaleteDAO.getInstance();
    }

    public boolean isPaleteValida (String qrCode) {
        return !paletes.containsKey(qrCode);
    }

    public void acrescentaPalete (String qrCode, String tipoMaterial) {
        Palete palete = new Palete(qrCode, tipoMaterial, 0, super.getZonaID());
        paletes.put(qrCode, palete);
    }

    public Boolean existePalete (String qrCode){
        return paletes.containsKey(qrCode);
    }

    public void recolhePalete(String qrCode){
        paletes.get(qrCode).setZonaID(null);
    }

}
