package com.example.demo.api.models;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

public class polygonHandle {

    public static void read() {
        File file = new File("/home/andre/Documentos/si/demo/poligono/poligono.shp");
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("url", file.toURI().toURL());
        } catch (MalformedURLException e) {
        }

        DataStore dataStore;
        try {
            dataStore = DataStoreFinder.getDataStore(map);
            String typeName = dataStore.getTypeNames()[0];

            double pontoX = -6074312.30790697;
            double pontoY = -2937600.65557604;

            //Point ponto = builder.createPoint(pontoX, pontoY);

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

                    Coordinate coord = new Coordinate(pontoX, pontoY);
                    Point ponto = gf.createPoint(coord);

                    System.out.println(poly.contains(ponto));


                }
            }

        } catch (IOException e) {

        }

    }

}
