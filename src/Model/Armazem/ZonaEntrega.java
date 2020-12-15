package Model.Armazem;

import java.util.Collection;
import java.util.TreeSet;

public class ZonaEntrega extends Zona{
    private Collection<Palete> paletes;

    public ZonaEntrega(String zonaID) {
        super(zonaID);
        paletes = new TreeSet<>();
    }
}
