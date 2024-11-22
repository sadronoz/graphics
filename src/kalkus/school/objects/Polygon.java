package kalkus.school.objects;

import java.awt.*;
import java.util.ArrayList;

public class Polygon extends ArrayList<Point> {
    private int color;
    public Polygon() {
        color = 0x00ff00;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
