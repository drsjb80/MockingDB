package MockingDB;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReadSQLTest {
    static Connection conn = mock(Connection.class);
    static Statement stmt = mock(Statement.class);
    static ResultSet rs = mock(ResultSet.class);

    @org.junit.Test
    public void loadSQLCookies() throws Exception {
        when(conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getString("host")).thenReturn("Host");

        final ReadSQL readSQL = new ReadSQL();
        readSQL.loadSQLCookies(conn);

        assertEquals("Host", readSQL.getCookie().getHost());
    }

}