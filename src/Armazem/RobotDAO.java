import Armazem.InfoTransporte;
import Armazem.Palete;
import Armazem.Robot;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class RobotDAO implements Map<String, Robot> {
    private static RobotDAO singleton = null;

    private static final String USERNAME = "jfc"; //TODO: alterar
    private static final String PASSWORD = "jfc"; //TODO: alterar
    private static final String CREDENTIALS = "?user="+USERNAME+"&password="+PASSWORD;
    private static final String DATABASE = "localhost:3306/turmas3l";

    private RobotDAO() {
//        Driver é carregado automaticamente quando se abre uma conexão
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//        }
//        catch (ClassNotFoundException e) {
//            // Driver não disponível
//            e.printStackTrace();
//            throw new NullPointerException(e.getMessage());
//        }
        try (Connection conn =
                     DriverManager.getConnection("jdbc:mysql://"+DATABASE+CREDENTIALS);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS robots (" +
                    "RobotId varchar(10) NOT NULL PRIMARY KEY," +
                    "Disponivel bit DEFAULT NULL," +
                    "QrCode varchar(10) DEFAULT NUL," +
                    "Tipo varchar(10) DEFAULT NULL," +
                    "Prateleira varchar(10) DEFAULT NULL," +
                    "ZonaID varchar(10) DEFAULT NULL," +
                    "Recolheu bit DEFAULT NULL,";
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
    public Robot get(Object key) {
        Robot p = null;
        try (Connection conn =
                     DriverManager.getConnection("jdbc:mariadb://"+DATABASE+CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM robots WHERE RobotId='"+key+"'")) {
            if (rs.next()) {
                String sql = "SELECT * FROM robots WHERE RobotId='"+(String)key+"'";
                try (ResultSet rsa = stm.executeQuery(sql)) {
                    if (rsa.next()) {
                        InfoTransporte i = new InfoTransporte(rsa.getString("QrCode"),
                                rsa.getString("Tipo"),
                                rsa.getString("Prateleira"),
                                rsa.getString("ZonaID"));
                        p = new Robot(rs.getString("RobotId"),
                                rsa.getBoolean("Disponivel"),
                                rsa.getBoolean("Recolheu"),i);
                    } else {
                        p = null;
                    }
                }
            }
        } catch (SQLException e) {
            // Database error!
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
                     DriverManager.getConnection("jdbc:mariadb://"+DATABASE+CREDENTIALS);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate(
                    "INSERT INTO robots VALUES ('"+r.getRobotID()+"','"+r.isDisponibilidade()+"', '"+
                            i.getQrCode()+ "', '"+ i.getTipo()+"', '"+ i.getPrateleira()+"', '"+
                            i.getZonaID()+"', '"+ r.isPaleteRecolhida()+"') " +
                            "ON DUPLICATE KEY UPDATE Disponibilidade=Values(Disponibilidade), " + "QrCode=Values(QrCode)" +
                            "Tipo=Values(Tipo)" + "Prateleira=Values(Prateleira)" + "ZonaID=Values(ZonaID)");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Robot remove(Object key) {
        return null;
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
        return null;
    }

    @Override
    public Set<Entry<String, Robot>> entrySet() {
        return null;
    }
}
