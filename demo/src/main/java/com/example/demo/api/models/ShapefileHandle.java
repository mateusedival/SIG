package com.example.demo.api.models;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.api.connection.ConnectionFactory;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.text.WKTParser;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.geometry.primitive.Point;

public class ShapefileHandle {

    public static void read() throws IOException {
        File file = new File("/home/andre/Documentos/si/plantio.shp");
        Map<String, Object> map = new HashMap<>();  
        map.put("url", file.toURI().toURL());

        DataStore dataStore = DataStoreFinder.getDataStore(map);
        String typeName = dataStore.getTypeNames()[0];

        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM, 10,20,30,40)")

        List<Plantio> plantios = new ArrayList<>();

        FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
        try (FeatureIterator<SimpleFeature> features = collection.features()) {
            while (features.hasNext()) {
                SimpleFeature feature = features.next();

                int id = 0;
                String nome = feature.getAttribute("nome").toString();
                String nomeCientifico = feature.getAttribute("nome_cient").toString();

                WKTParser parser = new WKTParser(new GeometryBuilder(DefaultGeographicCRS.WGS84));
                Point ponto = (Point) parser.parse(feature.getDefaultGeometryProperty().getValue().toString());

                Date data = (Date) feature.getAttribute("data");
                int idAluno = 0;

                Plantio plantio = new Plantio(id, nome, nomeCientifico, ponto, data, idAluno);
                plantios.add(plantio);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // System.out.println(plantios);
        Connection conn = ConnectionFactory.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO plantio (nome, nome_cientifico, ponto, data) VALUES (?, ?, ST_GeomFromText(?), ?)");

            stmt.setString(1, plantios.get(0).getNome());
            stmt.setString(2, plantios.get(0).getNomeCientifico());
            stmt.setString(3, plantios.get(0).getPonto().toString());
            stmt.setDate(4, new java.sql.Date(plantios.get(0).getData().getTime()));

            stmt.executeUpdate();

            stmt = conn.prepareStatement(
                    "SELECT id, nome, nome_cientifico, id_aluno, data, ST_asText(ponto) FROM plantio");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("nome"));
                System.out.println(rs.getString("nome_cientifico"));

                WKTParser parser = new WKTParser(new GeometryBuilder(DefaultGeographicCRS.WGS84));
                Point ponto = (Point) parser.parse(rs.getString("st_astext"));

                System.out.println(ponto);

                System.out.println(new Date(rs.getDate("data").getTime()));

            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

    }

}
