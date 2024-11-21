package kalkus.school.raster;

import java.util.Optional;

public interface RasterInterface {
    /**
     * return the number of columns in this raster
     * @return number of columns
     */
    int getWidth();
    /**
     * return the number of rows in this raster
     * @return number of rows
     */
    int getHeight();

    /**
     * Sets the provided color on the pixel specified by the given pixel adress
     * If the provided adress is invalid, doesn't change the color
     * @param c column pixel adress
     * @param r row pixel adress
     * @param color new color
     */
    void setColor(int c, int r, int color);

    /**
     * Returns the provided color on the pixel specified by the given pixel adress
     * @param c column pixel adress
     * @param r row pixel adress
     * @return color value; ... if the current
     */
    Optional<Integer> getColor(int c, int r);
}
