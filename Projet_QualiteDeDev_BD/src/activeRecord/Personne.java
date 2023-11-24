package activeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Personne {

    private int id;

    private String nom;

    private String prenom;

    public Personne(String nomP, String prenomP) {
        this.id = -1;
        this.nom = nomP;
        this.prenom = prenomP;
    }

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

    public String toString(){
        String ch = "";
        ch += "######################\n";
        ch += "# Id : " + getId() + "\n";
        ch += "# Nom : " + getNom() + "\n";
        ch += "# Prenom : " + getPrenom() + "\n";
        ch += "######################\n";
        return ch;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }


}
