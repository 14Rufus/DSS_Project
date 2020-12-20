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
                    "Disponivel bit DEFAULT NULL," +
                    "QrCode varchar(10) DEFAULT NULL," +
                    "Prateleira int DEFAULT NULL," +
                    "ZonaID varchar(10) DEFAULT NULL," +
                    "Recolheu bit DEFAULT NULL)";
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
        return false;
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
                InfoTransporte i = new InfoTransporte(rs.getString("QrCode"),
                        rs.getInt("Prateleira"),
                        rs.getString("ZonaID"));
                p = new Robot(rs.getString("RobotId"),
                        rs.getBoolean("Disponivel"),
                        rs.getBoolean("Recolheu"),i);
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
        System.out.println(r.toString());
        Robot res = null;
        InfoTransporte i = r.getInfoTransporte();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            if(i != null) {
                stm.executeUpdate("INSERT INTO robots VALUES ('" + r.getRobotID() + "','" + "0" + "', '" +
                        i.getQrCode() + "', '" + i.getPrateleira() + "', '" +
                        i.getZonaID() + "', '" + r.isPaleteRecolhida() + "') " +
                        "ON DUPLICATE KEY UPDATE Disponibilidade=Values(Disponibilidade), " + "QrCode=Values(QrCode)" +
                        "Tipo=Values(Tipo)" + "Prateleira=Values(Prateleira)" + "ZonaID=Values(ZonaID)");
            }
            else
                stm.executeUpdate("INSERT INTO robots VALUES ('"+r.getRobotID()+"',b'0', '"+
                                null+"','"+0+"', '"+null+"', b'0') ");
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

    /*
    public void setInfoTransporte(String qr, int prat, String zona) {
        infoTransporte = new InfoTransporte(qr,prat,zona);
    }
    */
}
