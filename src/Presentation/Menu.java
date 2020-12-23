package Presentation;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    // Varíavel de classe para suportar leitura
    private static Scanner is = new Scanner(System.in);
    // variáveis de instância
    private List<String> opcoes;
    private int op;

    /**
     * Constructor for objects of class Menu
     */
    public Menu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.op = 0;
    }

    /**
     * Método para apresentar o menu e ler uma opção.
     */
    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }

    /** Apresentar o menu */
    private void showMenu() {
        System.out.println("\n *** Menu *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }

    public String lerString(String message) {
        String line;

        do{
            System.out.println(message);
            line = is.nextLine();
        } while (line.isEmpty());

        return line;
    }

    /** Ler uma opção válida */
    private int lerOpcao() {
        int op;

        System.out.print("Opção: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Não foi inscrito um int
            op = -1;
            System.out.println(e.toString());
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }

    /**
     * Método para obter a última opção lida
     */
    public int getOpcao() {
        return this.op;
    }

    public void executaLogin() {
        System.out.println("\n *** Login *** ");

        System.out.print("Introduza password: ");
    }

    public void notRegistaPalete(String qrCode,boolean b) {
        String msg;
        if(b)
            msg = "A palete com o Qr-Code: " + qrCode + ", foi registada com sucesso.";
        else
            msg = "A palete com o Qr-Code: " + qrCode + ", não foi registada com sucesso.";

        System.out.println(msg);
    }

    public void notRobot(String qrCode,String robot) {
        String msg;
        if(robot == null)
            msg = "O robot com o robotID: " + robot + ", não foi notificado com sucesso.";
        else
            msg = "O robot com o robotID: " + robot + ", foi notificado para recolher a palete: " + qrCode+ ".";

        System.out.println(msg);
    }

    public void notRecolherPalete(String qrCode,String robot) {
        String msg;
        if(robot == null)
            msg = "O robot com o robotID: " + robot + ", não recolheu a palete com sucesso.";
        else
            msg = "O robot com o robotID: " + robot + ", recolheu a palete: " + qrCode+ " com sucesso.";

        System.out.println(msg);
    }

    public void notEntregarPalete(String qrCode,String robot) {
        String msg;
        if(robot == null)
            msg = "O robot com o robotID: " + robot + ", não entregou a palete com sucesso.";
        else
            msg = "O robot com o robotID: " + robot + ", entregou a palete: " + qrCode+ " com sucesso.";

        System.out.println(msg);
    }

    public void imprimeListagem(List<String> l){
        for(String s:l)
            System.out.println(s);
    }
}
