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
}
