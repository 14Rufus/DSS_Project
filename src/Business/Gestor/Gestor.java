package Business.Gestor;

public class Gestor {
    private String nome;
    private String password;
    private boolean online;

    public Gestor(String nome, String password, boolean online) {
        this.nome = nome;
        this.password = password;
        this.online = online;
    }
}
