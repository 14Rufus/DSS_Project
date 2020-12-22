package Data;

import Business.Gestor.Localizacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class LocalizacaoDAO implements Map<String, Localizacao> {
    private static LocalizacaoDAO singleton = null;

    private LocalizacaoDAO() {
        try (Connection conn =
                     DriverManager.getConnection(DAOconfig.URL+DAOconfig.CREDENTIALS);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS localizacoes (" +
                    "QrCode varchar(10) NOT NULL PRIMARY KEY," +
                    "Prateleira int DEFAULT 0," +
                    "ZonaID varchar(10) DEFAULT NULL," +
                    "RobotID varchar(10) DEFAULT NULL)";
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
    public static LocalizacaoDAO getInstance() {
        if (LocalizacaoDAO.singleton == null) {
            LocalizacaoDAO.singleton = new LocalizacaoDAO();
        }
        return LocalizacaoDAO.singleton;
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
    public Localizacao get(Object key) {
        return null;
    }

    @Override
    public Localizacao put(String key, Localizacao value) {
        return null;
    }

    @Override
    public Localizacao remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Localizacao> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Localizacao> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Localizacao>> entrySet() {
        return null;
    }
}
