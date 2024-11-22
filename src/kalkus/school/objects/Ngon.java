package kalkus.school.objects;

public class Ngon extends Polygon {
    private int n;
    private float angIncrement;
    private final float r;
    private final Point start;
    private final Point end;
    private float startAng;

    public Ngon(int n, Point start, Point end) {
        super();
        this.n = n; // Use the parameter value here
        this.start = start;
        this.end = end;
        this.r = calcR();
        this.startAng = calcStartAngle();
        this.angIncrement = calcAngInc();
        completeNGon();
    }

    public Ngon(int n, int x1, int y1, int x2, int y2) {
        super();
        this.n = n; // Use the parameter value here
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.r = calcR();
        this.startAng = calcStartAngle();
        this.angIncrement = calcAngInc();
        completeNGon();
    }

    private void completeNGon() {
        clear();// Clear any existing points
        for (int i = 0; i < n; i++) {
            double currentAng = startAng + i * angIncrement;
            int x = (int) (start.getX() + r * Math.cos(currentAng));
            int y = (int) (start.getY() + r * Math.sin(currentAng));
            add(new Point(x, y));
        }
    }

    private float calcStartAngle() {
        double x = start.getX() - end.getX();
        double y = start.getY() - end.getY();
        return (float) Math.atan2(y, x);
    }

    private float calcR() {
        double x = end.getX() - start.getX();
        double y = end.getY() - start.getY();
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private float calcAngInc() {
        return (float) (2 * Math.PI / n);
    }

    public int getN() {
        return n;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setN(int n) {
        clear();
        this.n = n;
        this.startAng = calcStartAngle();
        this.angIncrement = calcAngInc();
        completeNGon();
    }
}