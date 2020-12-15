package Model.Armazem;

import java.util.Collection;
import java.util.TreeSet;

public class ZonaRececao extends Zona{
    private Collection<Palete> paletes;

    public ZonaRececao(String zonaID) {
        super(zonaID);
        paletes = new TreeSet<>();
    }
}
