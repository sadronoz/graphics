package kalkus.school.fill;

import kalkus.school.objects.Line;
import kalkus.school.objects.Point;
import kalkus.school.objects.Polygon;
import kalkus.school.rasterization.PolygonRasterizer;
import kalkus.school.view.Panel;

import java.util.ArrayList;
import java.util.List;

public class ScanLine {
    private final PolygonRasterizer polygonRasterizer;
    private final Polygon polygon;
    private final Panel panel;

    public ScanLine(Panel panel, PolygonRasterizer polygonRasterizer, Polygon polygon) {
        this.polygonRasterizer = polygonRasterizer;
        this.polygon = polygon;
        this.panel = panel;
    }

    public void fill() {
        ArrayList<Line> Lines = new ArrayList<>();
        for (int i = 0; i < polygon.size(); i++) {
            Point p1 = polygon.get(i);
            Point p2 = polygon.get((i+1) % polygon.size());

            Line Line = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            if(!Line.isHorizontal()){
                Line.orientate();
                Lines.add(Line);
            }
        }
        int yMin = polygon.get(0).getY();
        int yMax = yMin;
        for (int i = 1; i < polygon.size(); i++) {
            int py1 = polygon.get(i).getY();
            if (py1 < yMin) {
                yMin = py1;
            }else if (py1 > yMax) {
                yMax = py1;
            }
        }

        for (int y = yMin; y <= yMax; y++) {
            List<Integer> intersections = new ArrayList<>();
            for (Line Line : Lines) {
                if(Line.isIntersection(y)){
                    int intersectionx = Line.calculateIntersection(y);
                    intersections.add(intersectionx);
                }
            }
            bubbleSort(intersections,intersections.size());
            for (int i = 0; i < intersections.size()-1; i++) {
                if (i % 2 == 0) {
                    for (int j = intersections.get(i); j < intersections.get(i+1); j++) {
                        panel.getRaster().setColor(j,y,0xff0000);
                    }
                }
            }
            intersections.clear();
        }
        polygonRasterizer.drawPolygon(polygon);
    }

    static void bubbleSort(List<Integer> arr, int n){
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    temp = arr.get(j);
                    arr.set(j,arr.get(j + 1));
                    arr.set(j + 1,temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }
}
