package kalkus.school.rasterization;

import kalkus.school.objects.Line;
import kalkus.school.raster.Raster;

public abstract class LineRasterizer {
     protected final Raster raster;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    /**
     * Rasterization of line in our panel. Using trivial algorithm to draw pixel by pixel from point1 to point2.
     * @param x1 starting x position of rasterization
     * @param y1 starting y position of rasterization
     * @param x2 ending x position of rasterization
     * @param y2 ending y position of rasterization
     * @param color color of our line
     */
    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        // empty, as each lineRasterizer class will have each unige algorithm
    }

    /**
     * Rasterization of line in our panel. Using trivial algorithm to draw pixel by pixel from point1 to point2.
     * @param line holds information of starting and ending positions of line, plus color
     */
    public void drawLine(Line line) {
        // empty, as each lineRasterizer class will have each unige algorithm
    }
}
