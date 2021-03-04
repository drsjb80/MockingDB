package MockingDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CookiesTest {
    static Connection conn = mock(Connection.class);
    static Statement stmt = mock(Statement.class);
    static ResultSet rs = mock(ResultSet.class);

    @org.junit.Test
    public void loadSQLCookies() throws Exception {
        when(conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getString("host")).thenReturn("www.msudenver.edu");

        final Cookies cookies = new Cookies();
        cookies.loadSQLCookies(conn);

        assertEquals("www.msudenver.edu",
            cookies.getCookies().get(0).getHost());
    }
}
