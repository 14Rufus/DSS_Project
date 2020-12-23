package Business.Armazem;
import Data.PrateleiraDAO;

import java.util.HashMap;

import java.util.Map;

public class ZonaArmazenamento extends Zona {
    private String tipoZona;
    private PrateleiraDAO prateleiras;

    public ZonaArmazenamento(String zonaID,String tipo) {
        super(zonaID);
        tipoZona = tipo;
        prateleiras = new PrateleiraDAO();
    }

    public int escolhePrateleira(){
        return prateleiras.escolhePrateleira();
    }

    public void arrumaPalete (int prateleira,String qrCode) {
        Prateleira p = prateleiras.get(prateleira);
        p.addPalete(qrCode,"Armazenamento");
        prateleiras.updatePrateleira(p);
    }
}
