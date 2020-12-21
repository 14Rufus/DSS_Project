package Business;
import Business.Armazem.GestArmazem;
import Business.Armazem.IGestArmazem;
import Business.Gestor.GestGestor;

import java.util.List;

public class SGS implements ISGS {
    private IGestArmazem gestArmazem;
    private IGestGestor gestGestor;

    public SGS() {
        gestArmazem = new GestArmazem();
        gestGestor = new GestGestor();
    }

    public void registarPalete(String qrCode,String tipoMaterial){
        gestArmazem.registaPalete(qrCode,tipoMaterial);
        gestGestor.addLocalizacao(qrCode+".0.Rececao");
    }

    public void notificarRobot(String qrCode) {
        gestArmazem.notRobot(qrCode);
    }

    public void recolherPalete(String robotID){
        String l;
        l = gestArmazem.recolheP(robotID);
        if(l != null)
            gestGestor.addLocalizacao(l);
    }

    public void entregarPalete(String robotID){
        String l;
        l = gestArmazem.entregaP(robotID);
        if(l != null)
            gestGestor.addLocalizacao(l);
    }

    public List<String> consultarListagem() {
        return gestGestor.consultarL();
    }

    public boolean validaLoginGestor (int id, String password) { return gestGestor.validaLoginGestor(id, password); }
}
