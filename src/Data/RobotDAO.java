package Data;
import Business.Armazem.InfoTransporte;
import Business.Armazem.Localizacao;
import Business.Armazem.Robot;
import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RobotDAO implements Map<String, Robot> {

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
                     stm.executeQuery("SELECT RobotID FROM Robot WHERE robotID='"+key.toString()+"'")) {
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
             ResultSet rs = stm.executeQuery("SELECT * FROM Robot WHERE RobotID='"+key+"'")) {
            if (rs.next()) {
                boolean disp = false, rec = false;
                if(rs.getInt("Disponivel") == 1)
                    disp = true;
                if(rs.getInt("Recolheu") == 1)
                    rec = true;

                Localizacao l;
                ResultSet rsL = stm.executeQuery(
                        "SELECT * FROM Localizacao WHERE idLocalizacao='"+rs.getInt("Localizacao_idLocalizacao")+"'");
                l = new Localizacao
                        (rsL.getInt("idLocalizacao"),rsL.getInt("Prateleira_prateleiraID"),rsL.getString("zonaID"));

                ResultSet rsI = stm.executeQuery(
                        "SELECT * FROM InfoTransporte WHERE idInfoTransporte='"+rs.getInt("InfoTransporte_idInfoTransporte")+"'");
                InfoTransporte i = null;

                if(rs.getInt("InfoTransporte_idInfoTransporte") != 0)
                    i = new InfoTransporte(rsI.getInt("idInfoTransporte"),
                            rsI.getString("Palete_qrCode"),
                            rsI.getInt("Prateleira_prateleiraID"));

                p = new Robot(rs.getString("RobotId"),disp,rec,i,l);

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
        Localizacao l = r.getLocalizacao();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {

            int disp = 0, rec = 0;
            if(r.isDisponivel() == true)
                disp = 1;
            if(r.isPaleteRecolhida() == true)
                rec = 1;
            stm.executeUpdate("INSERT INTO Localizacao VALUES ("+l.getIdLocalizacao()+",'"+l.getZonaID()+"',"+l.getPrateleira()+")" +
                    "ON DUPLICATE KEY UPDATE zonaID=Values(zonaID), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");

            stm.executeUpdate("INSERT INTO Robot VALUES ('" + r.getRobotID() + "'," + disp + "," + rec + ", " +
                    i.getIdinfoTransporte() + ", " +l.getIdLocalizacao() + ") " +
                    "ON DUPLICATE KEY UPDATE Disponivel=Values(Disponivel), InfoTransporte_idInfoTransporte=Values(InfoTransporte_idInfoTransporte)," +
                    "Localizacao_idLocalizacao=Values(Localizacao_idLocalizacao), Recolheu=Values(Recolheu)");

            if(i != null)
                stm.executeUpdate("INSERT INTO InfoTransporte VALUES ("+i.getIdinfoTransporte()+",'"+i.getQrCode()+"', "+i.getPrateleira()+")" +
                        "ON DUPLICATE KEY UPDATE Palete_qrCode=Values(Palete_qrCode), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");
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
            stm.executeUpdate("DELETE FROM Robot WHERE RobotID='"+key+"'");
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
