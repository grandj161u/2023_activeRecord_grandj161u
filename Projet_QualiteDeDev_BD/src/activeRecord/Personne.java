package activeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe Personne qui représente une personne par rapport à la base de données
 */
public class Personne {

    /**
     * Attributs de la classe Personne
     */
    private int id;
    private String nom;
    private String prenom;

    /**
     * Constructeur de la classe Personne
     *
     * @param nomP
     * @param prenomP
     */
    public Personne(String nomP, String prenomP) {
        this.id = -1;
        this.nom = nomP;
        this.prenom = prenomP;
    }

    /**
     * Méthode findAll qui retourne une liste de personnes de toutes les personnes de la base de données
     *
     * @return ArrayList<Personne>
     */
    public static ArrayList<Personne> findAll() {

        ArrayList<Personne> listRes = new ArrayList<>();
        Connection connection = DBConnection.getConnection();

        String SQLprep = "SELECT * FROM Personne";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.execute();
            ResultSet rs = prep.getResultSet();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                int id = rs.getInt("id");
                Personne personne = new Personne(nom, prenom);
                personne.id = id;
                listRes.add(personne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listRes;
    }

    /**
     * Méthode findById qui retourne une personne de la base de données en fonction de son id
     *
     * @param id
     * @return Personne
     */
    public static Personne findById(int id) {
        Personne persRes = null;
        Connection connection = DBConnection.getConnection();

        String SQLprep = "SELECT * FROM Personne WHERE id = ?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                int idPers = rs.getInt("id");

                persRes = new Personne(nom, prenom);
                persRes.setId(idPers);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return persRes;
    }

    /**
     * Méthode findByName qui retourne une personne de la base de données en fonction de son nom
     *
     * @param nom
     * @return Personne
     */
    public static Personne findByName(String nom) {
        Personne persRes = null;
        Connection connection = DBConnection.getConnection();

        String SQLprep = "SELECT * FROM Personne WHERE nom = ?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setString(1, nom);
            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                String nomPers = rs.getString("nom");
                String prenomPers = rs.getString("prenom");
                int idPers = rs.getInt("id");

                persRes = new Personne(nomPers, prenomPers);
                persRes.setId(idPers);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return persRes;
    }

    public static void createTable() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "CREATE TABLE `Personne` ( " +
                "  `id` int(11) NOT NULL AUTO_INCREMENT, " +
                "  `nom` varchar(40) NOT NULL, " +
                "  `prenom` varchar(40) NOT NULL, " +
                "  PRIMARY KEY (`id`) " +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTable() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "DROP TABLE Personne";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "DELETE FROM Personne WHERE nom = ? AND prenom = ?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setString(1, this.nom);
            prep.setString(2, this.prenom);
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setId(-1);
    }

    public void save() {
        if (getId() == -1) {
            this.saveNew();
        } else {
            this.update();
        }
    }

    private void saveNew() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "INSERT INTO `Personne` (`nom`, `prenom`) VALUES (?, ?)";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setString(1, getNom());
            prep.setString(2, getPrenom());
            prep.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SQLprep = "SELECT id FROM Personne WHERE nom = ?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setString(1, getNom());
            ResultSet resultSet = prep.executeQuery();

            if (resultSet.next()) {
                int idRecup = resultSet.getInt("id");
                setId(idRecup);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void update() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "INSERT INTO `Personne` (`id`, `nom`, `prenom`) VALUES (?, ?, ?)";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setInt(1, getId());
            prep.setString(2, getNom());
            prep.setString(3, getPrenom());
            prep.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode toString qui retourne une chaîne de caractères représentant une personne
     *
     * @return String
     */
    public String toString() {
        String ch = "";
        ch += "######################\n";
        ch += "# Id : " + getId() + "\n";
        ch += "# Nom : " + getNom() + "\n";
        ch += "# Prenom : " + getPrenom() + "\n";
        ch += "######################\n";
        return ch;
    }

    /**
     * Méthode getId qui retourne l'id d'une personne
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Methode getNom qui retourne le nom d'une personne
     *
     * @return String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode getPrenom qui retourne le prénom d'une personne
     *
     * @return String
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Méthode setId qui modifie l'id d'une personne
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
