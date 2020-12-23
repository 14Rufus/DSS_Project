package Data;
import Business.Gestor.Gestor;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class GestorDAO {

    public Gestor get(Object key) {
        Gestor p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM gestores WHERE ID='"+key+"'")) {
            if (rs.next()) {
                int on = rs.getInt("Online");
                boolean online = false;
                if(on == 1)
                    online = true;

                p = new Gestor(rs.getInt("idGestor"),
                        rs.getString("Nome"),
                        rs.getString("Password"),
                        online);
            } else {
                p = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public void updateGestor(String key, Gestor p) {
        Gestor res = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            int on = 0;
            if(p.isOnline())
                on = 1;

            stm.executeUpdate("INSERT INTO paletes VALUES ('"+p.getIdGestor()+"','"+p.getNome()+"','"+
                    p.getPassword()+"',"+on+")" +
                    "ON DUPLICATE KEY UPDATE Nome=Values(Nome), Password=Values(Password),Online=Values(Online)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

}
