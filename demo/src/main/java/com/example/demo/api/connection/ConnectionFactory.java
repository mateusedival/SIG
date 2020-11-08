package com.example.demo.api.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String user = "postgres";
    private static final String password = "db123";
    private static final String url = "jdbc:postgresql://localhost:5432/trabalho";

    public static Connection getConnection() {

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados");
        }

    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com banco de dados");
            }
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt){

        closeConnection(conn);

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com banco de dados");
            }
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){

        closeConnection(conn, stmt);

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com banco de dados");
            }
        }
    }

}
