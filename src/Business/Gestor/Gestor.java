package Business.Gestor;

import java.util.Objects;

public class Gestor {
    private int id;
    private String nome;
    private String password;
    private boolean online;

    public Gestor(int id, String nome, String password, boolean online) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean validaCredenciaisGestor(int id, String password) {
        return (id == this.id && this.password.equals(password));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gestor gestor = (Gestor) o;
        return id == gestor.id && online == gestor.online && nome.equals(gestor.nome) && password.equals(gestor.password);
    }
}
