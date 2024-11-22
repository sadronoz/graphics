package kalkus.school.view;

import kalkus.school.fill.ScanLine;
import kalkus.school.objects.Line;
import kalkus.school.objects.Point;
import kalkus.school.objects.Polygon;
import kalkus.school.raster.Raster;
import kalkus.school.singleton.Singleton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    private final Raster raster;
    private final ArrayList<Line> lines = new ArrayList<>();
    private final ArrayList<Polygon> polygons = new ArrayList<>();
    private final ArrayList<Point> seedFill = new ArrayList<>();
    private final ArrayList<ScanLine> scanLine = new ArrayList<>();

    /**
     * Panel that holds our main window that we work with. This panel is inside JFrame.
     * @param height sets the height of the panel
     * @param width sets the width of the panel
     */
    public Panel(int width, int height) {
        this.raster = new Raster(width, height);
        setPreferredSize(new Dimension(raster.getWidth(), raster.getHeight()));
        setVisible(true);
        setFocusable(true);
        grabFocus();
    }

    public Raster getRaster() {
        return raster;
    }

    public void clear() {
        raster.clear();
        lines.clear();
        polygons.clear();
        seedFill.clear();
        scanLine.clear();
        repaint();
        System.out.println(Singleton.getInstance().ANSI_RED + "--> Panel refreshed" + Singleton.getInstance().ANSI_RESET);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        raster.preset(g);
    }

    public void addLine(Line line) {lines.add(line);}
    public void addPolygon(Polygon polygon) {polygons.add(polygon);}
    public void addPoint(Point point) {seedFill.add(point);}
    public void addScanLine(ScanLine line) {scanLine.add(line);}

    public ArrayList<Line> getLines() {return lines;}
    public ArrayList<Polygon> getPolygons() {return polygons;}
    public ArrayList<Point> getSeedFill() {return seedFill;}
    public ArrayList<ScanLine> getScanLine() {return scanLine;}
}
