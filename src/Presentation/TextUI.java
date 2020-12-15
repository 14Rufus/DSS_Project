package Presentation;
import Business.Armazem.GestArmazem;
import java.util.Scanner;

public class TextUI {
    // O model tem a 'lógica de negócio'.
    //private ITurmasFacade model;
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
                "rem robot"};
        this.menu = new Menu(opcoes);
        this.model = new GestArmazem();
        scin = new Scanner(System.in);
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
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
            }
        } while (menu.getOpcao()!=0); // A opção 0 é usada para sair do menu.
        System.out.println("Até breve!...");
    }

    private void trataListarRobots() {
        //Scanner scin = new Scanner(System.in);
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
}
