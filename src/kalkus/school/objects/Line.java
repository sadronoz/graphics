package kalkus.school.objects;

import kalkus.school.singleton.Singleton;

public class Line {
    private final Point start;
    private final Point end;
    private int color;

    public Line(int x1, int y1, int x2, int y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

//        setColor();
        color = 0x00ff00;
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

//        setColor();
        color = 0x00ff00;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public int getColor() {
        return color;
    }

    public void setColor() {
        this.color = Singleton.getInstance().getColor();
    }

    public boolean isHorizontal(){
        return start.getY() == end.getY();
    }

    public void orientate(){
        if(start.getY() > end.getY()){
            int tmp;
            tmp = start.getX();
            start.setX(end.getX());
            end.setX(tmp);

            tmp = start.getY();
            start.setY(end.getY());
            end.setY(tmp);
        }
    }

    public boolean isIntersection(double y){
        return start.getY() <= y && end.getY() > y;
    }

    public int calculateIntersection(int y){
        int inter;
        if (start.getX()!=end.getX()) {
            float k = (end.getY() - start.getY()) / (float) (end.getX() - start.getX());
            float q = start.getY() - k * start.getX();
            inter = (int) ((y - q) / k);
        }
        else {inter = start.getX();}
        return inter;
    }
}
