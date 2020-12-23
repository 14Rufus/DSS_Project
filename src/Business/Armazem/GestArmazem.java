/**
 * Classe que "gere" o Armazém
 */
package Business.Armazem;

import Data.RobotDAO;

import java.util.Iterator;
import java.util.List;

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
     */
    public boolean registaPalete (String qrCode) {
        if (validaPalete(qrCode)) {
            zonaRececao.acrescentaPalete(qrCode);
            return true;
        }

        return false;
    }
    // ----------------------------

    /**
     * Devolve a distância a que um Robot se encontra da Zona de Receção
     *
     * @param r Robot
     * @return  Distância a que o Robot se encontra
     */
    public int distancia(Robot r) {
        String zona = r.getLocZona();
        int prat = r.getLocPrat();

        if(zona.equals("Rececao"))
            return 0;
        else {
            if(prat < 6)
                return 2 + (prat * 4);
            else
                return 7 + ((prat-5) * 4);
        }
    }

    /**
     * Devolve um Robot disponível
     *
     * @return  Robot disponível
     */
    public Robot getRobotDisponivel (){
        Robot r, res = null;
        int dist, min = Integer.MAX_VALUE;
        Iterator<Robot> it = robots.values().iterator();

        while (it.hasNext()) {
            r = it.next();
            dist = distancia(r);
            if (r.isDisponivel() && dist < min){
                res = r;
                min = dist;
            }
        }
        return res;
    }

    /**
     * Envia informação a um Robot caso haja algum disponível
     *
     * @param qrCode Código QR da Palete a ser transportada
     */
    public String notRobot(String qrCode){
        Robot r = getRobotDisponivel();
        if (!existePaleteRececao(qrCode) || r == null){
            //System.out.println("robot/palete não disponivel");
            return null;
        } else {
            int n = robots.sizeInfo();
            r.setInfoTransporte(n + 1,qrCode, zonaArmazenamento.escolhePrateleira()); //ver quando n tem espaço
            r.setDisponivel(false);
            robots.put(r);
            return r.getRobotID();
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
        if(r == null || r.isDisponivel())
            return null;

        String qrCode = r.getQrCode();
        zonaRececao.recolhePalete(qrCode);
        r.setRecolheu(true);
        r.setLocalizacao("Rececao", 0);
        robots.put(r);
        return qrCode;
    }

    /**
     * Entregar uma Palete
     *
     * @param robotID ID do Robot que entegará a Palete
     * @return
     */
    public String entregaP(String robotID) {
        Robot r = robots.get(robotID);
        if(r == null || r.isDisponivel() || !r.isPaleteRecolhida())
            return null;

        String qrCode = r.getQrCode();
        int prateleira = r.getPrateleira();

        zonaArmazenamento.arrumaPalete(prateleira,qrCode);
        r.setDisponivel(true);
        r.setRecolheu(false);
        r.setLocalizacao("Armazenamento", r.getPrateleira());
        r.removeInfo();
        robots.put(r);

        return qrCode;
    }

    /**
     * Listagem das Localizações das Paletes
     *
     * @return Listagem das Localizações
     */
    public List<String> getListagem(){
        return zonaRececao.getListagem();
    }

}
