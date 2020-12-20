package Business;
import Business.Armazem.GestArmazem;
import Business.Armazem.IGestArmazem;
import Business.Gestor.GestGestor;
import Data.ISGS;

import java.util.List;

public class SGS implements ISGS {
    private IGestArmazem gestArmazem;
    private GestGestor gestGestor;

    public SGS() {
    }

    public void registarPalete(String qrCode,String tipoMaterial){
        registarPalete(qrCode,tipoMaterial);
    }

    public void notificarRobot(String qrCode) {
        gestArmazem.notRobot(qrCode);
    }

    public void recolherPalete(String robotID){
        gestArmazem.recolheP(robotID);
    }

    public void entregarPalete(String robotID){
        gestArmazem.entregaP(robotID);
    }

    public List<String> consultarListagem() {
        return gestGestor.consultarL();
    }
}
