package Business.Armazem;
import java.util.Collection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ZonaEntrega extends Zona {
    private Map<String,Palete> paletes;

    public ZonaEntrega(String zonaID) {
        super(zonaID);
        paletes = new HashMap<>();
    }
}
