package activeRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class PrincipaleJDBC {

    // IL FAUT PENSER A AJOUTER MYSQLCONNECTOR AU CLASSPATH

    public static void main(String[] args) throws SQLException {

        String tableName = "personne";

        Connection connect = DBConnection.getConnection();

        // recuperation de toutes les personnes + affichage
        {
            System.out.println("4) Recupere les personnes de la table Personne");
            ArrayList<Personne> listPers = Personne.findAll();

            for (Personne p : listPers){
                System.out.println(p);
            }
        }

//        // suppression de la personne 1
//        {
//            PreparedStatement prep = connect.prepareStatement("DELETE FROM Personne WHERE id=?");
//            prep.setInt(1, 1);
//            prep.execute();
//            System.out.println("5) Suppression personne id 1 (Spielberg)");
//            System.out.println();
//        }
//
//        // recuperation de la seconde personne + affichage
//        {
//            System.out.println("6) Recupere personne d'id 2");
//            String SQLPrep = "SELECT * FROM Personne WHERE id=?;";
//            PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
//            prep1.setInt(1, 2);
//            prep1.execute();
//            ResultSet rs = prep1.getResultSet();
//            // s'il y a un resultat
//            if (rs.next()) {
//                String nom = rs.getString("nom");
//                String prenom = rs.getString("prenom");
//                int id = rs.getInt("id");
//                System.out.println("  -> (" + id + ") " + nom + ", " + prenom);
//            }
//            System.out.println();
//        }
//
//        // met a jour personne 2
//        {
//            String SQLprep = "update Personne set nom=?, prenom=? where id=?;";
//            PreparedStatement prep = connect.prepareStatement(SQLprep);
//            prep.setString(1, "S_c_o_t_t");
//            prep.setString(2, "R_i_d_l_e_y");
//            prep.setInt(3, 2);
//            prep.execute();
//            System.out.println("7) Effectue modification Personne id 2");
//            System.out.println();
//        }
//
//        // recuperation de la seconde personne + affichage
//        {
//            System.out.println("8) Affiche Personne id 2 apres modification");
//            String SQLPrep = "SELECT * FROM Personne WHERE id=?;";
//            PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
//            prep1.setInt(1, 2);
//            prep1.execute();
//            ResultSet rs = prep1.getResultSet();
//            // s'il y a un resultat
//            if (rs.next()) {
//                String nom = rs.getString("nom");
//                String prenom = rs.getString("prenom");
//                int id = rs.getInt("id");
//                System.out.println("  -> (" + id + ") " + nom + ", " + prenom);
//            }
//            System.out.println();
//        }
//
//        // suppression de la table personne
//        {
//            String drop = "DROP TABLE Personne";
//            Statement stmt = connect.createStatement();
//            stmt.executeUpdate(drop);
//            System.out.println("9) Supprime table Personne");
//        }

    }

}