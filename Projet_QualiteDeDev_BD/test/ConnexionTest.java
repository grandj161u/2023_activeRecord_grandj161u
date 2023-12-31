import activeRecord.DBConnection;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnexionTest {

    @Test
    public void testSingletonConnexion() throws SQLException {
        Connection c1 = DBConnection.getConnection();
        Connection c2 = DBConnection.getConnection();

        assertEquals(c1, c2);
    }

    @Test
    public void testBaseDiff() throws SQLException {
        Connection c1 = DBConnection.getConnection();
        Connection c2 = DBConnection.getConnection();

        assertEquals("testpersonne", DBConnection.getDbName());

    }
}
