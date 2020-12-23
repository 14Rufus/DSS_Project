package Business.Gestor;

import java.util.Objects;

public class Gestor {
    private int idGestor;
    private String nome;
    private String password;
    private boolean online;

    public Gestor(int id, String nome, String password, boolean online) {
        this.idGestor = id;
        this.nome = nome;
        this.password = password;
        this.online = online;
    }

    public int getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(int idGestor) {
        this.idGestor = idGestor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean validaCredenciaisGestor(String password) {
        System.out.println(this.password);
        return (this.password.equals(password));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gestor gestor = (Gestor) o;
        return online == gestor.online && nome.equals(gestor.nome) && password.equals(gestor.password);
    }
}
