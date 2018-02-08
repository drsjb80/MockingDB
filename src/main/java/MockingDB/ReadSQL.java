package MockingDB;

import lombok.Getter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Cookie {
    @Getter private String host;

    Cookie(final String host) {
        this.host = host;
    }
}

public class ReadSQL {
    @Getter Cookie cookie;

    public void loadSQLCookies(final Connection conn) {
        try {
            final String sql = "SELECT * FROM moz_cookies";

            final Statement stmt = conn.createStatement();
            final ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                final String host = rs.getString("host");
                System.out.println(host);

                cookie = new Cookie(host);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void bar() {}

}
