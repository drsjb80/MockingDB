package MockingDB;

import lombok.Getter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.LinkedList;

public class Cookies {
    @Getter private final List<Cookie> cookies = new LinkedList<>();

    static class Cookie {
        @Getter private final String host;

        Cookie(final String host) {
            this.host = host;
        }
    }

    public void loadSQLCookies(final Connection conn) {
        try {
            try (Statement stmt = conn.createStatement()) {
                final String sql = "SELECT * FROM moz_cookies";
                final ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    cookies.add(new Cookie(rs.getString("host")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
