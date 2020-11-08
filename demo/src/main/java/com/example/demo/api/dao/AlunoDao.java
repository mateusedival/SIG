package com.example.demo.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.api.connection.ConnectionFactory;
import com.example.demo.api.models.Aluno;

public class AlunoDao {

    public static Aluno create(Aluno aluno) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO alunos (nome, idade, serie, id_curso) VALUES (?, ?, ?, ?)");
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setInt(3, aluno.getSerie());
            stmt.setInt(4, aluno.getIdCurso());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return aluno;
    }

    public static List<Aluno> read() {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> alunos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM alunos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setSerie(rs.getInt("serie"));
                aluno.setIdCurso(rs.getInt("id_curso"));

                alunos.add(aluno);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return alunos;

    }

    public static Aluno read(int id) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aluno aluno = null;

        try {
            stmt = conn.prepareStatement("SELECT * FROM alunos WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {

                aluno = new Aluno();

                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setSerie(rs.getInt("serie"));
                aluno.setIdCurso(rs.getInt("id_curso"));

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return aluno;

    }

    public static Aluno update(Aluno aluno) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("UPDATE alunos SET nome = ?, idade = ?, serie = ?, id_curso = ? WHERE id = ?");
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setInt(3, aluno.getSerie());
            stmt.setInt(4, aluno.getIdCurso());
            stmt.setInt(5, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return aluno;
    }

    public static Aluno delete(Aluno aluno) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM alunos WHERE id = ?");
            stmt.setInt(1, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return aluno;

    }

}