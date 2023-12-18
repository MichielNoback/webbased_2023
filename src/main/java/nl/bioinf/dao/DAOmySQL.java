package nl.bioinf.dao;

import nl.bioinf.model.User;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOmySQL implements DAO{

    private final String url;
    private final String dbUser;
    private final String dbPassword;
    private Connection connection;

    public DAOmySQL(String url, String username, String password) {
        this.url = url;
        this.dbUser = username;
        this.dbPassword = password;

        //make the connection
        connect();
    }

    private void connect() throws DatabaseException {
        try {
            //load driver class
            Class.forName("com.mysql.jdbc.Driver");
            //create connection
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
            //..which is risky
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Something is wrong with the database, see cause Exception",
                    e.getCause());
        }
    }

    @Override
    public User getUser(String username, String password) {
        return null;
    }
}
