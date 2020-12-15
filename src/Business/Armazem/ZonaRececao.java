package Business.Armazem;
import Business.Armazem.Zona;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ZonaRececao extends Zona {
    private Map<String,Palete> paletes;

    public ZonaRececao(String zonaID) {
        super(zonaID);
        paletes = new HashMap<>();
    }
}
