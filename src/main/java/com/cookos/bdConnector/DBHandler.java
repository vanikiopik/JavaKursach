package com.cookos.bdConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs{
    static Connection dbConnection;

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
}

