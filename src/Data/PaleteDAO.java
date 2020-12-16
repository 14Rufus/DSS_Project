package Data;

import Business.Armazem.Palete;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PaleteDAO implements Map<String, Palete> {
    private static PaleteDAO singleton = null;

    private PaleteDAO() {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS paletes (" +
                    "QrCode varchar(10) NOT NULL PRIMARY KEY," +
                    "TipoMaterial varchar(30) DEFAULT NUL," +
                    "Prateleira int DEFAULT NULL," +
                    "ZonaID varchar(10) DEFAULT NULL)";
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
    public Palete get(Object key) {
        Palete p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM paletes WHERE QrCode='"+key+"'")) {
            if (rs.next()) {
                p = new Palete(rs.getString("QrCode"),
                        rs.getString("TipoMaterial"),
                        rs.getInt("Prateleira"),
                        rs.getString("Zona"));
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
    public Palete put(String key, Palete p) {
        Palete res = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate("INSERT INTO paletes VALUES ('"+p.getQrCode()+"','"+p.getTipoMaterial()+"','"+p.getPrateleira()+"','"+p.getZonaID()+"')");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }


    @Override
    public Palete remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Palete> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Palete> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Palete>> entrySet() {
        return null;
    }
}
