/**
 * Classe que "gere" o Armazém
 */
package Business.Armazem;

import Data.RobotDAO;

import java.util.Iterator;
import java.util.Map;

public class GestArmazem implements IGestArmazem {
    private Map<String, Robot> robots;
    private ZonaRececao zonaRececao;
    private ZonaArmazenamento zonaArmazenamento;

    public GestArmazem() {
        this.robots = RobotDAO.getInstance();
        zonaRececao = new ZonaRececao("Receção");
        zonaArmazenamento = new ZonaArmazenamento("z1","a");
    }

    /**
     * Adiciona Robot ao Armazém
     *
     * @param robotID do Robot a adicionar
     */
    public void addRobot(String robotID) {
        robots.put(robotID,new Robot(robotID));
    }

    /**
     * Remove Robot do Armazém
     *
     * @param robotID ID do Robot a remover
     */
    public void remRobot(String robotID) {
        robots.remove(robotID);
    }

    /**
     * Devolve todos os Robots do Armazém
     */
    public void getRobots(){
        System.out.println(robots.values().toString());
    }

    /**
     * Verifica se existe uma determinada Palete na ZOna de Receção
     *
     * @param qrCode Código QR da Palete a ser verificada
     * @return Existência da Palete na Zona de Receção
     */
    public boolean existePaleteRececao(String qrCode){
        return zonaRececao.existePalete(qrCode);
    }

    // USE CASE REGISTAR PALETE

    /**
     * Valida uma determinada Palete
     *
     * @param qrCode Código QR da Palete
     * @return Validade da Palete
     */
    private boolean validaPalete (String qrCode) {
        return zonaRececao.isPaleteValida(qrCode) && zonaArmazenamento.isPaleteValida(qrCode);
    }

    /**
     * Regista uma Palete
     *
     * @param qrCode       Código QR da Palete
     * @param tipoMaterial Tipo de Material da Palete
     */
    public void registaPalete (String qrCode, String tipoMaterial) {
        if (validaPalete(qrCode))
            zonaRececao.acrescentaPalete(qrCode,tipoMaterial);
    }
    // ----------------------------

    /**
     * Devolve um Robot disponível
     *
     * @return  Robot disponível
     */
    public Robot getRobotDisponivel (){
        Robot r = null, res = null;
        Boolean isDisponivel = true;  //flag
        Iterator<Robot> it = robots.values().iterator();
        while (it.hasNext() && isDisponivel) {
            r = it.next();
            if (r.isDisponivel()){
                isDisponivel = false;
                res = r;
            }
        }
        return res;
    }

    /**
     * Envia informação a um Robot caso haja algum disponível
     *
     * @param qrCode Código QR da Palete a ser transportada
     */
    public void notRobot(String qrCode){
        Robot r = getRobotDisponivel();
        if(r == null) System.out.println("aaaaa" + existePaleteRececao(qrCode));
        if (!existePaleteRececao(qrCode) || r == null){
            System.out.println("robot/palete não disponivel");//print na view
        }
        else{
            r.setInfoTransporte(qrCode, zonaArmazenamento.escolhePrateleira(),zonaArmazenamento.getZonaID());
            r.setDisponivel(false);
            robots.put(r.getQrCode(),r);
        }
    }

    //  ---------------------------------------------------------

    /**
     * Recolher uma Palete
     *
     * @param robotID ID do Robot que recolherá a Palete
     * @return
     */
    public String recolheP(String robotID){
        Robot r = robots.get(robotID);
        if(r == null)
            return null;

        String qrCode = r.getQrCode();
        if(qrCode == null)
            return null;
        zonaRececao.recolhePalete(qrCode);
        r.setRecolheu(true);
        robots.put(robotID,r);
        return qrCode + ".-1." + robotID;
    }

    /**
     * Entregar uma Palete
     *
     * @param robotID ID do Robot que entegará a Palete
     * @return
     */
    public String entregaP(String robotID) {
        Robot r = robots.get(robotID);
        if(r == null)
            return null;

        String zona = r.getZonaID(), qrCode = r.getQrCode();
        int prateleira = r.getPrateleira();
        if(zona == null || prateleira == -1 || !r.isPaleteRecolhida())
            return null;

        zonaArmazenamento.arrumaPalete(zona,prateleira,qrCode,qrCode); //ver como passar a palete para zonaArmazenamento
        r.setDisponivel(true);
        r.setRecolheu(false);
        r.removeInfo();
        robots.put(robotID,r);

        return qrCode + "." + prateleira + "." + zona;
    }
}
