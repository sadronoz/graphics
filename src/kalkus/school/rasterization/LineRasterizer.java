package kalkus.school.rasterization;

import kalkus.school.raster.Raster;
import kalkus.school.raster.RasterInterface;

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
     */
    public void drawLine(double x1, double y1, double x2, double y2){
        // empty, as each lineRasterizer class will have each unige algorithm
    }
}
