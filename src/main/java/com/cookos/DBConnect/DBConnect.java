package com.cookos.DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PrimitiveIterator;

public class DBConnect {
    private static String HOST = "127.0.0.1";
    private static int PORT = 3306;
    private static String DB_NAME = "stock";
    private static String USERNAME = "root";
    private static String PASSWORD = "datode14";
    private static Connection connection = null;

    public static Connection getConnect() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
        return connection;
    }
}
