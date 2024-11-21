package kalkus.school.raster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class Raster implements RasterInterface {
    int width, height;
    BufferedImage img;

    public Raster(int width, int height){
        this.width = width;
        this.height = height;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setColor(int c, int r, int color) {
        if(c < width && c >= 0 && r < height && r >= 0) img.setRGB(c, r, color);
    }

    @Override
    public Optional<Integer> getColor(int c, int r) {
        if(c < width && c >= 0 && r < height && r >= 0){
            return Optional.of(img.getRGB(c, r));
        }
        else{
            return Optional.empty();
        }
    }

    public void clear(){
        Graphics g = img.getGraphics();
        g.setColor(new Color(0x2f2f2f));
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    public void preset (Graphics g){
        if(g != null){
            g.drawImage(img, 0, 0, null);
        }
    }
}
