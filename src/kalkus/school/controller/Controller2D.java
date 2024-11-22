package kalkus.school.controller;

import kalkus.school.clipper.Clipper;
import kalkus.school.fill.ScanLine;
import kalkus.school.fill.SeedFill;
import kalkus.school.objects.Line;
import kalkus.school.objects.Ngon;
import kalkus.school.objects.Point;
import kalkus.school.objects.Polygon;
import kalkus.school.rasterization.LineTrivial;
import kalkus.school.rasterization.PolygonRasterizer;
import kalkus.school.singleton.Singleton;
import kalkus.school.tools.LineType;
import kalkus.school.view.Panel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller2D {
    private final Panel panel;
    private final LineTrivial lineTrivial;
    private final PolygonRasterizer polygonRasterizer;

    private Polygon polygon = new Polygon();
    private int x1, y1, x2, y2;

    private boolean shiftHeld = false;
    private boolean altHeld = false;


    public Controller2D(Panel panel) {
        this.panel = panel;
        lineTrivial = new LineTrivial(panel.getRaster());
        polygonRasterizer = new PolygonRasterizer(lineTrivial);
        initListeners();
    }

    private void redrawEverything() {
        panel.getRaster().clear();

        polygonRasterizer.drawPolygon(polygon);
        for (Line l : panel.getLines()){lineTrivial.drawLine(l);}
        for (Polygon p : panel.getPolygons()){polygonRasterizer.drawPolygon(p);}
        for (Point p : panel.getSeedFill()){new SeedFill(panel.getRaster(), 0x00ff00).seedFill(p.getX(), p.getY());}
        for (ScanLine s : panel.getScanLine()){s.fill();}

        panel.repaint();
    }

    public void initListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(!shiftHeld){
                    x2 = e.getX();
                    y2 = e.getY();
                }

                if(e.getButton() == MouseEvent.BUTTON1) { // left mouse click
                    Line line = new Line(x1, y1, x2, y2);
                    panel.addLine(line);
                }
                if(e.getButton() == MouseEvent.BUTTON2) { // middle mouse click
                    panel.addPoint(new Point(e.getX(), e.getY()));
                }
                if(e.getButton() == MouseEvent.BUTTON3) { // right mouse click
                    if (altHeld) {
                        polygon = new Ngon(5, x1, y1, x2, y2);
                        panel.addPolygon(polygon);
                        polygon = new Polygon();
                    }
                    else {
                        if(!polygon.isEmpty()){
                            x1 = polygon.getLast().getX();
                            y1 = polygon.getLast().getY();
                        }
                        else{
                            polygon.add(new Point(x1, y1));
                        }
                        polygon.add(new Point(x2, y2));
                    }

                }
                redrawEverything();

            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                panel.getRaster().clear();
                redrawEverything();

                if(shiftHeld){
                    switch (getClosestLineType(x1, y1, x2, y2)){
                        case DIAGONAL: switchPoints(); break;
                        case VERTICAL : y2 = y1; break;
                        case HORIZONTAL : x2 = x1; break;
                    }
                }

                if (SwingUtilities.isLeftMouseButton(e)) {
                    lineTrivial.drawLine(x1, y1, x2, y2, 0x00ff00);
                }

                if (SwingUtilities.isRightMouseButton(e)) {
                    if(altHeld){
                        polygon = new Ngon(5, x1, y1, x2, y2);
                        polygonRasterizer.drawPolygon(polygon);
                    }
                    else{
                        if(!polygon.isEmpty()){
                            Point firstPoint = polygon.getFirst();
                            x1 = firstPoint.getX();
                            y1 = firstPoint.getY();
                            lineTrivial.drawLine(x1, y1, x2, y2, 0xff0000);

                            Point lastPoint = polygon.getLast();
                            x1 = lastPoint.getX();
                            y1 = lastPoint.getY();
                        }

                        lineTrivial.drawLine(x1, y1, x2, y2, 0xff0000);
                        polygonRasterizer.drawPolygon(polygon);
                    }

                }
                panel.repaint();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_C){
                    polygon = new Polygon();
                    panel.getRaster().clear();
                    panel.clear();
                }

                if(e.getKeyCode() == KeyEvent.VK_P){
                    panel.addPolygon(polygon);
                    polygon = new Polygon();
                }

                if (e.getKeyCode() == KeyEvent.VK_V){
                    if(polygon.size() > 2){
                        ScanLine scanLine = new ScanLine(panel,polygonRasterizer,polygon);
                        scanLine.fill();
                        panel.addScanLine(scanLine);
                        panel.repaint();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_X){
                    polygon = new Clipper().clip(polygon, panel.getPolygons().getLast());
                    panel.getPolygons().removeLast();
                    panel.getPolygons().add(polygon);
                    polygon = new Polygon();
                    redrawEverything();
                }

                if(e.getKeyCode() == KeyEvent.VK_SHIFT){
                    shiftHeld = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_ALT){
                    altHeld = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SHIFT){
                    shiftHeld = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_ALT){
                    altHeld = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    System.out.println("VK_LEFT");
//                    Singleton.getInstance().setColor(Singleton.getInstance().ColorToLeft()); TODO in future add switching of colors by arrow keys
                }

                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    System.out.println("VK_RIGHT");
//                    Singleton.getInstance().setColor(Singleton.getInstance().ColorToRight()); TODO in future add switching of colors by arrow keys
                }
            }
        });
    }

    public LineType getClosestLineType(int x1, int y1, int x2, int y2) {
        double dx = Math.abs(x2 - x1);
        double dy = Math.abs(y2 - y1);

        double threshold = 0.5;

        if (Math.abs(dx - dy) <= threshold * Math.max(dx, dy)) {
            return LineType.DIAGONAL;
        } else if ( dx < dy) {
            return LineType.HORIZONTAL;
        } else {
            return LineType.VERTICAL;
        }
    }

    public void switchPoints(){
        int[] newPoint = calculateDiagonalEndPoint(x1, y1, x2, y2);
        x2 = newPoint[0];
        y2 = newPoint[1];
    }

    public static int[] calculateDiagonalEndPoint(int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        int newX2, newY2;

        if (Math.abs(dx) > Math.abs(dy)) {
            newX2 = x2;
            newY2 = (int) (y1 + Math.signum(dy) * Math.abs(dx));
        } else {
            newX2 = (int) (x1 + Math.signum(dx) * Math.abs(dy));
            newY2 = y2;
        }

        return new int[]{newX2, newY2};
    }
}
