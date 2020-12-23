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
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM Palete WHERE QrCode='"+key+"'")) {
            if (rs.next()) {
                Localizacao l;
                ResultSet rsL = stm.executeQuery("SELECT * FROM Localizacao WHERE idLocalizacao='"+rs.getInt("Localizacao_idLocalizacao")+"'");
                l = new Localizacao(rsL.getInt("idLocalizacao"),rsL.getInt("Prateleira_prateleiraID"),rsL.getString("zonaID"));
                p = new Palete(rs.getString("qrCode"),
                        rs.getString("tipoMaterial"),l);
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
        Localizacao l = p.getLocalizacao();
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate("INSERT INTO Palete VALUES ('"+p.getQrCode()+"','"+p.getTipoMaterial()+"',"+p.getLocalizacaoID()+")" +
                    "ON DUPLICATE KEY UPDATE TipoMaterial=Values(TipoMaterial), Localizacao_idLocalizacao=Values(Localizacao_idLocalizacao)");
            stm.executeUpdate("INSERT INTO Localizacao VALUES ("+l.getIdLocalizacao()+",'"+l.getZonaID()+"',"+l.getPrateleira()+")" +
                    "ON DUPLICATE KEY UPDATE zonaID=Values(zonaID), Prateleira_prateleiraID=Values(Prateleira_prateleiraID)");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }
}
