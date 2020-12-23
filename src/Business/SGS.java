package Business;
import Business.Armazem.GestArmazem;
import Business.Armazem.IGestArmazem;
import Business.Gestor.GestGestor;
import Business.Gestor.IGestGestor;

import java.util.List;

public class SGS implements ISGS {
    private IGestArmazem gestArmazem;
    private IGestGestor gestGestor;

    public SGS() {
        gestArmazem = new GestArmazem();
        gestGestor = new GestGestor();
    }

    public boolean registarPalete(String qrCode,String tipoMaterial){
        boolean res = gestArmazem.registaPalete(qrCode,tipoMaterial);
        //gestGestor.addNotRegisto();
        return res;
    }

    public String notificarRobot(String qrCode) {
        String res = gestArmazem.notRobot(qrCode);
        return res;
    }

    public String recolherPalete(String robotID){
        String res = gestArmazem.recolheP(robotID);
        //if(l != null)
            //gestGestor.addLocalizacao(l);
        return res;
    }

    public String entregarPalete(String robotID){
        String res = gestArmazem.entregaP(robotID);
        //if(l != null)
            //gestGestor.addLocalizacao(l);
        return res;
    }

    public List<String> consultarListagem() {
        return gestArmazem.getListagem();
    }

    public boolean isOnline() {
        return gestGestor.isOnline();
    }

    public void setOnline(boolean online) {
        gestGestor.setOnline(online);
    }

    public boolean validaLoginGestor (String password) { return gestGestor.validaLoginGestor(password); }
}
