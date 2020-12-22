package Presentation;
import Business.Armazem.GestArmazem;
import Business.Armazem.IGestArmazem;
import Business.ISGS;
import Business.SGS;

import java.util.Scanner;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    //private ITurmasFacade model;
    private ISGS model2;
    private GestArmazem model;

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
                "listar robots",
                "add robot",
                "rem robot",
                "Registar palete",
                "Notificar Robot",
                "Recolher Palete",
                "Entregar Palete"};
        this.menu = new Menu(opcoes);
        this.model = new GestArmazem();
        this.model2 = new SGS();
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        if(!model2.isOnline()) {
            introduzirPassword();
        }
        do {
            menu.executa();
            switch (menu.getOpcao()) {
                case 1:
                    trataListarRobots();
                    break;
                case 2:
                    addRobot();
                    break;
                case 3:
                    remRobot();
                    break;
                case 4:
                    registaPalete();
                    break;
                case 5:
                    notificarRobot();
                    break;
                case 6:
                    recolherPalete();
                    break;
                case 7:
                    entregarPalete();
                    break;
            }
        } while (menu.getOpcao()!=0); // A opção 0 é usada para sair do menu.
        System.out.println("Até breve!...");
        model2.setOnline(false);
    }

    private void introduzirPassword() {
        String pass;
        do {
            menu.executaLogin();
            pass = scin.nextLine();
        } while(!model2.validaLoginGestor(pass));

        model2.setOnline(true);
    }

    private void trataListarRobots() {
        try {
            this.model.getRobots();
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addRobot() {
        try {
            System.out.println("ID robot: ");
            String rid = scin.nextLine();
            model.addRobot(rid);
        }
        catch (NullPointerException e) {
            System.out.println("bbb");
            System.out.println(e.getMessage());
        }
    }

    private void remRobot() {
        try {
            System.out.println("ID robot: ");
            String rid = scin.nextLine();
            model.remRobot(rid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registaPalete() {
        try {
            System.out.println("Qr-Code da palete: ");
            String rid = scin.nextLine();
            model2.registarPalete(rid,"Materia Perecivel");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void notificarRobot() {
        try {
            System.out.println("Insira um Qr-Code: ");
            String rid = scin.nextLine();
            model2.notificarRobot(rid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void recolherPalete(){
        try {
            System.out.println("Insira um robotID: ");
            String rid = scin.nextLine();
            model2.recolherPalete(rid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void entregarPalete(){
        try {
            System.out.println("Insira um robotID: ");
            String rid = scin.nextLine();
            model2.entregarPalete(rid);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}
