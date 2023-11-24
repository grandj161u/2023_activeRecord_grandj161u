import activeRecord.DBConnection;
import activeRecord.Personne;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConnexion {

    @Test
    public void testSingletonConnexion() throws SQLException {
        Connection c1 = DBConnection.getConnection();
        Connection c2 = DBConnection.getConnection();

        assertEquals(c1, c2);
    }

    @Test
    public void testBaseDiff() throws SQLException {
        Connection c1 = DBConnection.getConnection();
        DBConnection.setDbName("testbdpersonne");
        Connection c2 = DBConnection.getConnection();

        assertEquals("testbdpersonne", DBConnection.getDbName());

        DBConnection.setDbName("testpersonne");
    }

    @Test
    public void testFindAll() throws SQLException {
        ArrayList<Personne> listTest = Personne.findAll();

        ArrayList<Personne> listQuiTest = new ArrayList<>();

        Personne pers1 = new Personne("Spielberg", "Steven");
        Personne pers2 = new Personne("Scott", "Ridley");
        Personne pers3 = new Personne("Kubrick", "Stanley");
        Personne pers4 = new Personne("Fincher", "David");

        pers1.setId(1);
        pers2.setId(2);
        pers3.setId(3);
        pers4.setId(4);

        listQuiTest.add(pers1);
        listQuiTest.add(pers2);
        listQuiTest.add(pers3);
        listQuiTest.add(pers4);

        assertEquals(listTest.toString(), listQuiTest.toString());
    }

    @Test
    public void testFindById() throws SQLException {
        Personne persTest = Personne.findById(2);

        Personne persQuiTest = new Personne("Scott", "Ridley");
        persQuiTest.setId(2);

        assertEquals(persTest.toString(), persQuiTest.toString());
    }
}
