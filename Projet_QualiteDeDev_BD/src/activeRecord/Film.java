package activeRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Film {

    private String titre;

    private int id;

    private int id_real;

    public Film(String chFilm, Personne real) {
        this.titre = chFilm;
        this.id = -1;
        this.id_real = real.getId();
    }

    private Film(int id, String titre, int id_real) {
        this.titre = titre;
        this.id_real = id_real;
        this.id = id;
    }

    public static Film findById(int id) {
        Film film = null;
        Connection connection = DBConnection.getConnection();

        String SQLprep = "SELECT * FROM Film WHERE id = ?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();

            if (rs.next()) {
                int idFilm = rs.getInt("id");
                String titreFilm = rs.getString("titre");
                int idReal = rs.getInt("id_rea");

                film = new Film(idFilm, titreFilm, idReal);
                film.setId(idFilm);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return film;
    }

    public Personne getRealisateur(){
        return Personne.findById(id_real);
    }

    public static void createTable() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "CREATE TABLE `Film` ( " +
                "  `id` int(11) NOT NULL AUTO_INCREMENT, " +
                "  `titre` varchar(40) NOT NULL, " +
                "  `id_rea` int(11) DEFAULT NULL, " +
                "  PRIMARY KEY (`id`) " +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTable() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "DROP TABLE Film";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "DELETE FROM Film WHERE titre = ?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setString(1, this.titre);
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setId(-1);
    }

    public void save() throws RealisateurAbsentException {
        if (getId_real() != -1) {
            if (getId() == -1) {
                this.saveNew();
            } else {
                this.update();
            }
        }else{
            throw new RealisateurAbsentException();
        }
    }

    private void saveNew() {
        Connection connection = DBConnection.getConnection();

        String SQLprep = "INSERT INTO `Film` (`titre`, `id_rea`) VALUES (?, ?)";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setString(1, getTitre());
            prep.setInt(2, getId_real());
            prep.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SQLprep = "SELECT id FROM Film WHERE titre = ?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setString(1, getTitre());
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

        String SQLprep = "UPDATE `Film` SET titre=?, id_rea=? WHERE id=?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setString(1, getTitre());
            prep.setInt(2, getId_real());
            prep.setInt(3, getId());
            prep.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Film> findByRealisateur(Personne p){
        ArrayList<Film> listRes = null;
        Connection connection = DBConnection.getConnection();

        String SQLprep = "SELECT * FROM Film WHERE id_rea = ?";
        try {
            PreparedStatement prep = connection.prepareStatement(SQLprep);
            prep.setInt(1, p.getId());
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                int id_real = rs.getInt("id_rea");
                Film film = new Film(titre, Personne.findById(id_real));
                film.id = id;
                listRes.add(film);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listRes;

    }

    public String toString(){
            String ch = "";
            ch += "######################\n";
            ch += "# Id du film : " + getId() + "\n";
            ch += "# Titre du film : " + getTitre() + "\n";
            ch += "# Id du réalisateur : " + getId_real() + "\n";
            ch += "######################\n";
            return ch;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_real(int id_real) {
        this.id_real = id_real;
    }

    public int getId() {
        return id;
    }

    public int getId_real() {
        return id_real;
    }

    public String getTitre() {
        return titre;
    }
}
