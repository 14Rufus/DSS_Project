package Business;

import Exceptions.*;

import java.util.List;

public interface ISGS {
    void registarPalete(String qrCode) throws PaleteInvalidaException;
    String notificarRobot(String qrCode) throws RobotNaoDisponivelException, PaleteNaoExisteException, ArmazemCheioException;
    String recolherPalete(String robotID) throws RobotNaoDisponivelException;
    String entregarPalete(String robotID) throws RobotNaoDisponivelException, PaleteNaoRecolhidaException;
    List<String> consultarListagem() throws ListagemVaziaException;
    boolean isOnline();
    void setOnline(boolean online);
    boolean validaLoginGestor (String password);
}
