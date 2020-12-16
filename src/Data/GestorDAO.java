package Data;
import Business.Gestor.Gestor;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class GestorDAO implements Map<String, Gestor> {
    private static GestorDAO singleton = null;

    private GestorDAO() {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS gestores (" +
                    "ID int NOT NULL PRIMARY KEY," +
                    "Nome varchar(40) DEFAULT NULL," +
                    "Password int(40) DEFAULT NULL," +
                    "Online bit DEFAULT NULL,";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Gestor get(Object key) {
        Gestor p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM gestores WHERE ID='"+key+"'")) {
            if (rs.next()) {
                p = new Gestor(rs.getInt("ID"),
                        rs.getString("Nome"),
                        rs.getString("Password"),
                        rs.getBoolean("Online"));
            } else {
                p = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public Gestor put(String key, Gestor p) {
        Gestor res = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate("INSERT INTO paletes VALUES ('"+p.getId()+"','"+p.getNome()+"','"+
                    p.getPassword()+"','"+p.isOnline()+"' ");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }


    @Override
    public Gestor remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Gestor> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Gestor> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Gestor>> entrySet() {
        return null;
    }
}
