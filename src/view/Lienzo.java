
package view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Lienzo extends Canvas implements MouseListener, MouseMotionListener {
    private Color color = Color.BLACK;
    private int grosorLinea = 1;
    private Font fuente = new Font("Arial", Font.PLAIN, 12);
    private int xi, yi, xf, yf;
    private String opcion = ""; // 0 Linea 1 Cuadrado 2 Rectangulo 3 Triangulo 4 Pentagono 5 Hexagono
    private Graphics g;
    private Graphics2D g2d;

    public Lienzo() {
        super();
        this.setBackground(Color.WHITE);
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setGrosorLinea(int grosorLinea) {
        this.grosorLinea = grosorLinea;
    }

    public int getGrosorLinea() {
        return grosorLinea;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        xi = e.getX();
        yi = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        switch (opcion) {
            case "Trazo":
                int mx = e.getX();
                int my = e.getY();
                g = getGraphics();
                g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(grosorLinea));
                g2d.drawLine(xi, yi, mx, my);
                xi = mx;
                yi = my;
                // g2d.dispose();
                break;
            case "Borrar":
                int mx2 = e.getX();
                int my2 = e.getY();
                g = getGraphics();
                g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(grosorLinea));
                g2d.setColor(Color.WHITE);
                g2d.drawLine(xi, yi, mx2, my2);
                xi = mx2;
                yi = my2;
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
