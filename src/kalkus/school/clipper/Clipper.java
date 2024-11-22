package kalkus.school.clipper;

import kalkus.school.objects.Point;
import kalkus.school.objects.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Clipper {
    private Polygon cutResult;

    public Polygon clip(ArrayList<Point> clipperPoints, ArrayList<Point> pointsToClip) {
        ArrayList<Point> cutSubresult = pointsToClip;

        for (int i = 0; i < clipperPoints.size(); i++) {
            cutResult = new Polygon();

            Point cP1 = clipperPoints.get(i);
            Point cP2 = clipperPoints.get((i+1) % clipperPoints.size());
            cutEdge(cP1, cP2, cutSubresult);
            cutSubresult = cutResult;
        }
        return cutResult;
    }

    private void cutEdge(Point cP1, Point cP2, List<Point> pointsToCut) {
        Point t = new Point(cP2.getX() - cP1.getX(), cP2.getY() - cP1.getY());

        Point n = new Point(-t.getY(), t.getX());

        for (int i = 0; i < pointsToCut.size(); i++) {
            Point p1 = pointsToCut.get(i);
            Point p2 = pointsToCut.get((i+1) % pointsToCut.size());

            Point v1 = new Point(p1.getX() - cP1.getX(), p1.getY() - cP1.getY());

            int dotProduct1 = v1.getX()*n.getX() + v1.getY()*n.getY();

            if (dotProduct1 >= 0) {
                cutResult.add(p1);
            }

            Point v2 = new Point(p2.getX() - cP1.getX(), p2.getY() - cP1.getY());
            int dotProduct2 = v2.getX()*n.getX() + v2.getY()*n.getY();

            if ((dotProduct1 >= 0 && dotProduct2 < 0) || (dotProduct1 < 0 && dotProduct2 >= 0)) {
                float k1 = ((float) (cP2.getY() - cP1.getY()) / (cP2.getX() - cP1.getX()));
                float k2 = ((float) (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));
                float q1 = cP1.getY() - k1 * cP1.getX();
                float q2 = p1.getY() - k2 * p1.getX();

                int x = Math.round((q2-q1) / (k1-k2));
                int y = Math.round(k1 * x + q1);
                cutResult.add(new Point(x, y));
            }
        }
    }
}
