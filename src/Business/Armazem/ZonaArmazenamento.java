package Business.Armazem;
import java.util.HashMap;

import java.util.Map;

public class ZonaArmazenamento extends Zona {
    private String tipoZona;
    private Map<String, Prateleira> prateleiras;

    public ZonaArmazenamento(String zonaID,String tipo) {
        super(zonaID);
        tipoZona = tipo;
        prateleiras = new HashMap<>();
    }

    public boolean isPaleteValida (String qrCode) {
        boolean b = false;
        for (Prateleira p: prateleiras.values())
            b = b || p.isPaleteValida(qrCode);
        return b;
    }

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

//    -------------------------
//    falta definir em qual prateleira colocar
    public void arrumaPalete (String zonaID,int prateleira,String qrCode, String tipoMaterial) {
        prateleiras.get(prateleira).addPalete(qrCode,tipoMaterial,zonaID);
    }
}
