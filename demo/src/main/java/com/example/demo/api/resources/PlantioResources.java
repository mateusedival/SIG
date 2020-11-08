package com.example.demo.api.resources;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.api.dao.PlantioDao;
import com.example.demo.api.models.Plantio;
import com.example.demo.api.responses.CheckResponse;
import com.example.demo.api.responses.PlantioPoint;
import com.example.demo.api.responses.PlantioResponse;
import com.example.demo.api.responses.PlantioUpdate;
import com.example.demo.api.connection.ConnectionFactory;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.text.WKTParser;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.opengis.filter.Filter;
import org.opengis.geometry.primitive.Point;

/**
 * PlantioResources
 */
@RestController
public class PlantioResources {

    @GetMapping("/plantio")
    public List<PlantioResponse> get() {

        List<PlantioResponse> response = new ArrayList<>();

        for (Plantio plantio : PlantioDao.read()) {
            response.add(new PlantioResponse(plantio));
        }

        return response;

    }

    @GetMapping("/plantio/{id}")
    public PlantioResponse get(@PathVariable(value = "id") int id) {
        Plantio plantio = PlantioDao.read(id);

        if (plantio == null) {
            return null;
        } else {
            return new PlantioResponse(PlantioDao.read(id));
        }
    }

    @PostMapping("/plantio")
    public void post() throws IOException {
        File file = new File("/home/andre/Documentos/si/demo/pontos/pontos.shp");
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

        Connection conn = ConnectionFactory.getConnection();

        try {

            for (Plantio plantio : plantios) {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO plantio (nome, nome_cientifico, ponto, data) VALUES (?, ?, ST_GeomFromText(?), ?)");

                stmt.setString(1, plantio.getNome());
                stmt.setString(2, plantio.getNomeCientifico());
                stmt.setString(3, plantio.getPonto().toString());
                stmt.setDate(4, new java.sql.Date(plantio.getData().getTime()));

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @PutMapping("/plantio")
    public PlantioUpdate put(@RequestBody PlantioUpdate plantioUpdate) {
        return PlantioDao.update(plantioUpdate);
    }

    @DeleteMapping("/plantio")
    public void delete(@RequestBody PlantioResponse plantioResponse) {
        PlantioDao.delete(plantioResponse);
    }

    @GetMapping("/plantio/check")
    public CheckResponse checkPoint(@RequestBody PlantioPoint plantioPoint){
        File file = new File("/home/andre/Documentos/si/demo/poligono/poligono.shp");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("url", file.toURI().toURL());
        } catch (MalformedURLException e) {
        }
        Boolean response = null;
        DataStore dataStore;
        try {
            dataStore = DataStoreFinder.getDataStore(map);
            String typeName = dataStore.getTypeNames()[0];

            FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
            Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM, 10,20,30,40)")

            FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
            try (FeatureIterator<SimpleFeature> features = collection.features()) {
                while (features.hasNext()) {
                    SimpleFeature feature = features.next();

                    MultiPolygon mp = (MultiPolygon) feature.getDefaultGeometry();
                    int n = mp.getNumGeometries();

                    Polygon poly = null;
                    for (int i = 0; i < n; i++) {
                        poly = (Polygon) mp.getGeometryN(i);
                    }

                    GeometryFactory gf = new GeometryFactory();

                    Coordinate coord = new Coordinate(plantioPoint.getPontoX(), plantioPoint.getPontoY());
                    org.locationtech.jts.geom.Point ponto = gf.createPoint(coord);

                    response = poly.contains(ponto);
                    
                }
            }

        } catch (IOException e) {
            System.out.println("Erro na operação");
        }

        return new CheckResponse(response);
    }

}