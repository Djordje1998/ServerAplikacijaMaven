/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ai.ServerAplikacija.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.util.PropertyConst;
import rs.ac.bg.fon.ai.BibliotekaAplikacija.util.PropertyRead;

/**
 *
 * @author DarkForce
 */
public class DbConnectionFactory {

    private static DbConnectionFactory instance;
    private Connection connection;

    public DbConnectionFactory() {
    }

    public static DbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            PropertyRead read = new PropertyRead();
            String url = read.getString(PropertyConst.URL);
            String username = read.getString(PropertyConst.USER);
            String password = read.getString(PropertyConst.PASS);
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }

}
