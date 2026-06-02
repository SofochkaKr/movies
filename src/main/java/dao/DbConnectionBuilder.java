package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionBuilder implements ConnectionBuilder {

    private final String url;
    private final String login;
    private final String password;

    public DbConnectionBuilder(ConnectionProperty prop) {
        try {
            Class.forName(prop.getProperty("db.driver.class"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL driver not found", e);
        }
        this.url = prop.getProperty("db.url");
        this.login = prop.getProperty("db.login");
        this.password = prop.getProperty("db.password");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }
}
