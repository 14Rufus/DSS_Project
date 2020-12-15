package Business.Armazem;

import java.util.Map;

public class ZonaArmazenamento extends Zona{
    private String tipoZona;
    private Map<String,Palete> paletes;

    public ZonaArmazenamento(String zonaID,String tipo) {
        super(zonaID);
        tipoZona = tipo;
    }
}
