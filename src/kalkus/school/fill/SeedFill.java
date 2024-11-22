package kalkus.school.fill;

import kalkus.school.objects.Point;
import kalkus.school.raster.Raster;

import java.util.Optional;
import java.util.Stack;

public class SeedFill
{
    private final Raster raster;
    private final int fillColor;

    public SeedFill(Raster raster, int fillColor) {
        this.raster = raster;
        this.fillColor = fillColor;

    }

    public void seedFill(int x, int y){
        Optional<Integer> backroundColor = raster.getColor(x, y);
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x, y));

        while (!stack.isEmpty() && stack.size() <= 250000) { //Pokud by jsme chtěli vyplnit celou obrazovku program by nespadl ale velice by zatěžoval procesor
            Point p = stack.pop();
            int px = p.getX();
            int py = p.getY();

            if (px >= 0 && py >= 0 && px < raster.getWidth() && py < raster.getHeight()
                    && raster.getColor(px, py).get().equals(backroundColor.get())) {

                raster.setColor(px, py, fillColor);

                stack.push(new Point(px + 1, py));
                stack.push(new Point(px - 1, py));
                stack.push(new Point(px, py + 1));
                stack.push(new Point(px, py - 1));
            }
        }
    }
}
