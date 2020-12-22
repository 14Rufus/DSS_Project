package Data;
import Business.Armazem.InfoTransporte;
import Business.Armazem.Robot;
import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RobotDAO implements Map<String, Robot> {
    private static RobotDAO singleton = null;

    private RobotDAO() {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS robots (" +
                    "RobotId varchar(10) NOT NULL PRIMARY KEY," +
                    "Disponivel int DEFAULT 0," +
                    "QrCode varchar(10) DEFAULT NULL," +
                    "Prateleira int DEFAULT 0," +
                    "ZonaID varchar(10) DEFAULT NULL," +
                    "Recolheu int DEFAULT 0)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Implementação do padrão Singleton
     *
     * @return devolve a instância única desta classe
     */
    public static RobotDAO getInstance() {
        if (RobotDAO.singleton == null) {
            RobotDAO.singleton = new RobotDAO();
        }
        return RobotDAO.singleton;
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
        boolean r;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT robotId FROM robots WHERE robotId='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Robot get(Object key) {
        Robot p = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM robots WHERE RobotId='"+key+"'")) {
            if (rs.next()) {
                boolean disp = false, rec = false;
                if(rs.getInt("Disponivel") == 1)
                    disp = true;
                if(rs.getInt("Recolheu") == 1)
                    rec = true;

                if(rs.getString("QrCode") != "") {
                    InfoTransporte i = new InfoTransporte(rs.getString("QrCode"),
                            rs.getInt("Prateleira"),
                            rs.getString("ZonaID"));
                    p = new Robot(rs.getString("RobotId"),
                            disp,rec, i);
                }
                else
                    p = new Robot(rs.getString("RobotId"),
                        disp,rec,null);
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
    public Robot put(String key, Robot r) {
        Robot res = null;
        InfoTransporte i = r.getInfoTransporte();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            if(i != null) {
                int disp = 0, rec = 0;
                if(r.isDisponivel() == true)
                    disp = 1;
                if(r.isPaleteRecolhida() == true)
                    rec = 1;

                stm.executeUpdate("INSERT INTO robots VALUES ('" + r.getRobotID() + "'," + disp + ", '" +i.getQrCode() + "', " +
                        i.getPrateleira() + ", '" +i.getZonaID() + "', " + rec + ") " +
                        "ON DUPLICATE KEY UPDATE Disponivel=Values(Disponivel), QrCode=Values(QrCode)," +
                        "Prateleira=Values(Prateleira) ,ZonaID=Values(ZonaID) , Recolheu=Values(Recolheu)");
            }
            else
                stm.executeUpdate("INSERT INTO robots VALUES ('" + r.getRobotID()+ "',1, '"+""+"' ,0, '"+""+"' , 0) " +
                        "ON DUPLICATE KEY UPDATE Disponivel=Values(Disponivel), QrCode=Values(QrCode)," +
                        "Prateleira=Values(Prateleira) ,ZonaID=Values(ZonaID) , Recolheu=Values(Recolheu)");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Robot remove(Object key) {
        Robot t = this.get(key);
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM robots WHERE RobotId='"+key+"'");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return t;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Robot> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Robot> values() {
        Collection<Robot> col = new HashSet<>();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT RobotId FROM robots")) {
            while (rs.next()) {
                col.add(this.get(rs.getString("RobotId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return col;
    }

    @Override
    public Set<Entry<String, Robot>> entrySet() {
        return null;
    }
}
