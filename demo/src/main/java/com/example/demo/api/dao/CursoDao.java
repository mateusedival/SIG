package com.example.demo.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.api.connection.ConnectionFactory;
import com.example.demo.api.models.Curso;

public class CursoDao {

    public static Curso create(Curso curso) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO cursos (nome) VALUES (?)");
            stmt.setString(1, curso.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return curso;
    }

    public static List<Curso> read() {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Curso> cursos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM cursos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Curso curso = new Curso();

                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));

                cursos.add(curso);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return cursos;

    }

    public static Curso read(int id) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Curso curso = null;

        try {
            stmt = conn.prepareStatement("SELECT * FROM cursos WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {

                curso = new Curso();

                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return curso;

    }

    public static Curso update(Curso curso) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("UPDATE cursos SET nome = ? WHERE id = ?");
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return curso;
    }

    public static Curso delete(Curso curso) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM cursos WHERE id = ?");
            stmt.setInt(1, curso.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return curso;

    }

}