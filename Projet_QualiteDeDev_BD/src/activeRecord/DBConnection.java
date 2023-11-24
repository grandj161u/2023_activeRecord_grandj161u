package activeRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe DBConnection qui retourne un objet pour se connecter à la base de donnée
 */
public class DBConnection {

    /**
     * Attribut connexion retourné par getConnection()
     */
    private static Connection connexion;

    /**
     * Attributs de connexion à la base de données
     */
    private static final String userName = "root";
    private static final String password = "";
    private static final String portNumber = "3306";
    private static final String serverName = "localhost";
    private static String dbName = "testpersonne";

    /**
     * Méthode getConnexion qui retourne un objet connexion s'il existe
     *
     * @return Connection
     */
    public static Connection getConnection() {
        String nomDB = null;

        if (connexion != null) {
            try {
                nomDB = connexion.getCatalog();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (connexion == null || !dbName.equals(nomDB)) {
            //Si l'objet connexion est null on le rempli
            Properties connectionProps = new Properties();
            connectionProps.put("user", userName);
            connectionProps.put("password", password);
            String urlDB = "jdbc:mysql://" + serverName + ":";
            urlDB += portNumber + "/" + dbName;

            try {
                connexion = DriverManager.getConnection(urlDB, connectionProps);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connexion;
    }

    /**
     * Méthode setDbName qui modifie le nom de la base de données
     * @param nomDB
     */
    public static void setDbName(String nomDB) {
        dbName = nomDB;
    }

    public static String getDbName() {
        try {
            return connexion.getCatalog();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
