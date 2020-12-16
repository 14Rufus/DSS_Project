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

//    -------------------------
//    falta definir em qual prateleira colocar
    public void registaPalete (String qrCode, String tipoMaterial) {
    }
}
