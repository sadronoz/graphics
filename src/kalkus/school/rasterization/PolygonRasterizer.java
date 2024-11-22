package kalkus.school.rasterization;

import kalkus.school.objects.Point;
import kalkus.school.objects.Polygon;

import java.util.ArrayList;

public class PolygonRasterizer {
    private final LineTrivial lineRasterizer;

    public PolygonRasterizer(LineTrivial lineRasterizer){
        this.lineRasterizer = lineRasterizer;
    }

    public void drawPolygon(Polygon points){
        for (int i = 0; i < points.size(); i++) {
            if(i != points.size()-1){
                lineRasterizer.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY(), points.getColor());
            }
        }

        if(points.size() > 2){
            Point firstPoint = points.getFirst();
            Point lastPoint = points.getLast();

            lineRasterizer.drawLine(firstPoint.getX(), firstPoint.getY(), lastPoint.getX(), lastPoint.getY(), points.getColor());
        }
    }
}
