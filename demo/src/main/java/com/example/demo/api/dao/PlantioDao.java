package com.example.demo.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.api.connection.ConnectionFactory;
import com.example.demo.api.models.Plantio;
import com.example.demo.api.responses.PlantioResponse;
import com.example.demo.api.responses.PlantioUpdate;

import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.text.WKTParser;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.primitive.Point;

public class PlantioDao {

    public static Plantio create(Plantio plantio) {
        return null;
    }

    public static List<Plantio> read() {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Plantio> plantios = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(
                    "SELECT id, nome, nome_cientifico, id_aluno, data, ST_asText(ponto) FROM plantio");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Plantio plantio = new Plantio();

                plantio.setId(rs.getInt("id"));
                plantio.setNome(rs.getString("nome"));
                plantio.setNomeCientifico(rs.getString("nome_cientifico"));

                WKTParser parser = new WKTParser(new GeometryBuilder(DefaultGeographicCRS.WGS84));
                Point ponto = (Point) parser.parse(rs.getString("st_astext"));

                // plantio.setPonto(ponto);

                Date data = new Date(rs.getDate("data").getTime());

                plantio.setData(data);
                plantio.setIdAluno(rs.getInt("id_aluno"));
                plantio.setPonto(ponto);

                plantios.add(plantio);
            }

        } catch (SQLException | ParseException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return plantios;

    }

    public static Plantio read(int id) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Plantio plantio = null;

        try {
            stmt = conn.prepareStatement(
                    "SELECT id, nome, nome_cientifico, id_aluno, data, ST_asText(ponto) FROM plantio WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {

                plantio = new Plantio();

                plantio.setId(rs.getInt("id"));
                plantio.setNome(rs.getString("nome"));
                plantio.setNomeCientifico(rs.getString("nome_cientifico"));

                WKTParser parser = new WKTParser(new GeometryBuilder(DefaultGeographicCRS.WGS84));
                Point ponto = (Point) parser.parse(rs.getString("st_astext"));

                // plantio.setPonto(ponto);

                Date data = new Date(rs.getDate("data").getTime());

                plantio.setData(data);
                plantio.setIdAluno(rs.getInt("id_aluno"));
                plantio.setPonto(ponto);

            }

        } catch (SQLException | ParseException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return plantio;

    }

    public static PlantioUpdate update(PlantioUpdate plantioUpdate) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (plantioUpdate.getIdAluno() == 0) {
                stmt = conn.prepareStatement(
                        "UPDATE plantio SET nome = ?, nome_cientifico = ?, data = ?, id_aluno = null WHERE id = ?");

                stmt.setString(1, plantioUpdate.getNome());
                stmt.setString(2, plantioUpdate.getNomeCientifico());
                stmt.setDate(3, new java.sql.Date(plantioUpdate.getData().getTime()));
                stmt.setInt(4, plantioUpdate.getId());
            } else {
                stmt = conn.prepareStatement(
                        "UPDATE plantio SET nome = ?, nome_cientifico = ?, data = ?, id_aluno = ? WHERE id = ?");

                stmt.setString(1, plantioUpdate.getNome());
                stmt.setString(2, plantioUpdate.getNomeCientifico());
                stmt.setDate(3, new java.sql.Date(plantioUpdate.getData().getTime()));
                stmt.setInt(4, plantioUpdate.getIdAluno());
                stmt.setInt(5, plantioUpdate.getId());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return plantioUpdate;
    }

    public static void delete(PlantioResponse plantioResponse) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM plantio WHERE id = ?");
            stmt.setInt(1, plantioResponse.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro em operação no banco de dados");
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

    }

}