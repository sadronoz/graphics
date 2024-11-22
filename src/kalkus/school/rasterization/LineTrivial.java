package kalkus.school.rasterization;

import kalkus.school.objects.Line;
import kalkus.school.raster.Raster;

public class LineTrivial extends LineRasterizer {
    public LineTrivial(Raster raster) {
        super(raster);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double steps = Math.max(Math.abs(dx), Math.abs(dy));

        double xIncrement = dx / steps;
        double yIncrement = dy / steps;

        double x = x1;
        double y = y1;

        for (int i = 0; i <= steps; i++) {
            raster.setColor((int) Math.round(x), (int) Math.round(y), color);
            x += xIncrement;
            y += yIncrement;
        }
    }

    @Override
    public void drawLine(Line line) {
        if(line != null) drawLine(line.getStart().getX(), line.getStart().getY(), line.getEnd().getX(), line.getEnd().getY(), line.getColor());
    }
}
