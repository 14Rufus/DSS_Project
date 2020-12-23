package Data;

import Business.Armazem.Localizacao;
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
                     stm.executeQuery("SELECT QrCode FROM Palete WHERE QrCode='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public Palete get(String key) {
        Palete p = null;
        Localizacao l = null;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Palete WHERE QrCode='"+key+"'")) {
            if (rs.next()) {
                String qr = rs.getString("qrCode");
                String tipo = rs.getString("tipoMaterial");

                ResultSet rsL = stm.executeQuery("SELECT * FROM Localizacao WHERE idLocalizacao=" + rs.getInt("Localizacao_idLocalizacao") + "");
                if (rsL.next()){
                    l = new Localizacao(rsL.getInt("idLocalizacao"), rsL.getInt("Prateleira_prateleiraID"), rsL.getString("zonaID"));
                }
                p = new Palete(qr, tipo, l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    public Palete put(Palete p) {
        Palete res = null;
        Localizacao l = p.getLocalizacao();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            if(l.getPrateleira() == 0)
                stm.executeUpdate("INSERT INTO Localizacao VALUES ("+l.getIdLocalizacao()+",'"+l.getZonaID()+"',null)" +
                        "ON DUPLICATE KEY UPDATE zonaID=Values(zonaID), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");
            else
                stm.executeUpdate("INSERT INTO Localizacao VALUES ("+l.getIdLocalizacao()+",'"+l.getZonaID()+"',"+l.getPrateleira()+")" +
                        "ON DUPLICATE KEY UPDATE zonaID=Values(zonaID), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");

            stm.executeUpdate("INSERT INTO Palete VALUES ('"+p.getQrCode()+"','"+p.getTipoMaterial()+"',"+l.getIdLocalizacao()+")" +
                    "ON DUPLICATE KEY UPDATE TipoMaterial=Values(TipoMaterial), Localizacao_idLocalizacao=Values(Localizacao_idLocalizacao)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    public int sizeLocalizacao() {
        int i = 0;
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM Localizacao")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

}
