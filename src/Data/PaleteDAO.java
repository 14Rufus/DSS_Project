package Data;

import Business.Armazem.Palete;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PaleteDAO{

    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT QrCode FROM paletes WHERE QrCode='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public Palete get(Palete key) {
        Palete p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM paletes WHERE QrCode='"+key+"'")) {
            if (rs.next()) {
                p = new Palete(rs.getString("QrCode"),
                        rs.getString("TipoMaterial"),
                        rs.getInt("Prateleira"),
                        rs.getString("ZonaID"));
            } else {
                p = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public Palete put(String key, Palete p) {
        Palete res = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate("INSERT INTO paletes VALUES ('"+p.getQrCode()+"','"+p.getTipoMaterial()+"',"+p.getPrateleira()+",'"+p.getZonaID()+"')" +
                    "ON DUPLICATE KEY UPDATE TipoMaterial=Values(TipoMaterial), Prateleira=Values(Prateleira),ZonaID=Values(ZonaID)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }
}
