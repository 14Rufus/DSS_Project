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
            }
        } while (menu.getOpcao()!=0); // A opção 0 é usada para sair do menu.
        System.out.println("Até breve!...");
        model.setOnline(false);
    }

    private void introduzirPassword() {
        String pass;
        do {
            menu.executaLogin();
            pass = scin.nextLine();
        } while(!model.validaLoginGestor(pass));

        model.setOnline(true);
    }

    private void registaPalete() {
        try {
            System.out.println("Qr-Code da palete: ");
            String rid = scin.nextLine();
            model.registarPalete(rid,"Materia Perecivel");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void notificarRobot() {
        try {
            System.out.println("Insira um Qr-Code: ");
            String rid = scin.nextLine();
            model.notificarRobot(rid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void recolherPalete(){
        try {
            System.out.println("Insira um robotID: ");
            String rid = scin.nextLine();
            model.recolherPalete(rid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void entregarPalete(){
        try {
            System.out.println("Insira um robotID: ");
            String rid = scin.nextLine();
            model.entregarPalete(rid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
