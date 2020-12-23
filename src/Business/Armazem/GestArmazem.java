/**
 * Classe que "gere" o Armazém
 */
package Business.Armazem;

import Data.RobotDAO;

import java.util.Iterator;

public class GestArmazem implements IGestArmazem {
    private RobotDAO robots;
    private ZonaRececao zonaRececao;
    private ZonaArmazenamento zonaArmazenamento;

    public GestArmazem() {
        this.robots = new RobotDAO();
        zonaRececao = new ZonaRececao("Receção");
        zonaArmazenamento = new ZonaArmazenamento("z1","a");
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
        return zonaRececao.isPaleteValida(qrCode);
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
            int n = robots.sizeInfo();
            r.setInfoTransporte(n + 1,qrCode, zonaArmazenamento.escolhePrateleira()); //ver quando n tem espaço
            r.setDisponivel(false);
            robots.put(r);
        }
    }

    //  ---------------------------------------------------------

    /**
     * Recolher uma Palete
     *
     * @param robotID ID do Robot que recolherá a Palete
     * @return
     */
    public boolean recolheP(String robotID){
        Robot r = robots.get(robotID);
        if(r == null || r.isDisponivel())
            return false;

        String qrCode = r.getQrCode();
        zonaRececao.recolhePalete(qrCode);
        r.setRecolheu(true);
        r.setLocalizacao("Rececao", 0);
        robots.put(r);
        return true;
    }

    /**
     * Entregar uma Palete
     *
     * @param robotID ID do Robot que entegará a Palete
     * @return
     */
    public boolean entregaP(String robotID) {
        Robot r = robots.get(robotID);
        if(r == null || r.isDisponivel() || !r.isPaleteRecolhida())
            return false;

        String qrCode = r.getQrCode();
        int prateleira = r.getPrateleira();

        zonaArmazenamento.arrumaPalete(prateleira,qrCode);
        r.setDisponivel(true);
        r.setRecolheu(false);
        r.setLocalizacao("Armazenamento", r.getPrateleira());
        r.removeInfo();
        robots.put(r);

        return true;
    }
}
