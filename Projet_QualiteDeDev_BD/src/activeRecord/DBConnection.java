package activeRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

    public static Connection getConnection() throws SQLException {
        if (connexion == null) {
            Properties connectionProps = new Properties();
            connectionProps.put("user", userName);
            connectionProps.put("password", password);
            String urlDB = "jdbc:mysql://" + serverName + ":";
            urlDB += portNumber + "/" + dbName;
            connexion = DriverManager.getConnection(urlDB, connectionProps);
        }
        return connexion;
    }

    public void setNomDB(String nomDB) {
        dbName = nomDB;
    }
}
