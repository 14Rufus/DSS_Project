package Business.Armazem;

import Data.RobotDAO;

import java.util.Iterator;
import java.util.Map;

public class GestArmazem {
    private Map<String, Robot> robots;
    private ZonaRececao zonaRececao;
    private ZonaArmazenamento zonaArmazenamento;

    public GestArmazem() {
        this.robots = RobotDAO.getInstance();
    }

    public void addRobot(String robotID) {
        robots.put(robotID,new Robot(robotID));
    }

    public void remRobot(String robotID) {
        robots.remove(robotID);
    }

    public void getRobots(){
        System.out.println(robots.values().toString());
    }

    public Palete getPalete (){
        return zonaRececao.getPalete();
    }

    public boolean validaPalete (String qrCode) {
        return zonaRececao.isPaleteValida(qrCode) && zonaArmazenamento.isPaleteValida(qrCode);
    }

    public void registaPalete (String qrCode, String tipoMaterial) {
        if (validaPalete(qrCode)) {

        }
    }

    public Robot getRobotDisponivel (){
        Robot r = null;
        Boolean isDisponivel = true;  //flag
        Iterator<Robot> it = robots.values().iterator();
        while (it.hasNext() && isDisponivel) {
            r = it.next();
            if (r.isDisponivel()){
                isDisponivel = false;
            }
        }
        return r;
    }




    public void enviaInformacaoTransporte(){
        if (getPalete() == null || getRobotDisponivel() == null){
            System.out.println("robot/palete não disponivel");
        }
        else{
            Palete p = getPalete();
            getRobotDisponivel().setInfoTransporte(p.getQrCode(), p.getTipoMaterial(), zonaArmazenamento.escolhePrateleira(),"armazenamento");
        }
    }
}
