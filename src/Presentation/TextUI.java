/**
 * Calsase que representa a interação enter sistema e utilizador
 */
package Presentation;
import Business.ISGS;
import Business.SGS;

import java.util.Scanner;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    private ISGS model;

    // Menus da aplicação
    private Menu menu;

    // Scanner para leitura
    private Scanner scin;

    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */

    public TextUI() {
        // Criar o menu
        String[] opcoes = {
                "Registar palete",
                "Notificar Robot",
                "Recolher Palete",
                "Entregar Palete",
                "Consultar Listagem"};
        this.menu = new Menu(opcoes);
        this.model = new SGS();
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        if(!model.isOnline()) {
            introduzirPassword();
        }
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    registaPalete();
                    break;
                case 2:
                    notificarRobot();
                    break;
                case 3:
                    recolherPalete();
                    break;
                case 4:
                    entregarPalete();
                    break;
                case 5:
                    consultarListagem();
                    break;
            }
        } while (menu.getOpcao()!=0); // A opção 0 é usada para sair do menu.
        System.out.println("Até breve!...");
        model.setOnline(false);
    }

    /**
     * Lê a password introduzida e efetua o Login do Gestor
     */
    private void introduzirPassword() {
        String pass;
        do {
            menu.executaLogin();
            pass = scin.nextLine();
        } while(!model.validaLoginGestor(pass));

        model.setOnline(true);
    }

    /**
     * Regista uma Palete
     */
    private void registaPalete() {
        try {
            String qrCode = menu.lerString("Qr-Code da palete: ");
            menu.notRegistaPalete(qrCode,model.registarPalete(qrCode));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Notifica o Robot
     */
    private void notificarRobot() {
        try {
            String qrCode = menu.lerString("Insira um Qr-Code: ");
            menu.notRobot(qrCode,model.notificarRobot(qrCode));
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Recolhe uma Palete
     */
    private void recolherPalete(){
        try {
            String robotid = menu.lerString("Insira um robotID: ");
            menu.notRecolherPalete(model.recolherPalete(robotid),robotid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * entrega uma Palete
     */
    private void entregarPalete(){
        try {
            String robotid = menu.lerString("Insira um robotID: ");
            menu.notEntregarPalete(model.entregarPalete(robotid),robotid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Consulta a Listagem das Localizações
     */
    private void consultarListagem(){
        menu.imprimeListagem(model.consultarListagem());
    }
}
