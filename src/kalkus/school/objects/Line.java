package kalkus.school.objects;

import kalkus.school.singleton.Singleton;

public class Line {
    Point start;
    Point end;
    int color;

    public Line(int x1, int y1, int x2, int y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

        this.color = getCurrentColor();
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        this.color = getCurrentColor();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    private int getCurrentColor() {
        return Singleton.getInstance().getColor();
    }
}
