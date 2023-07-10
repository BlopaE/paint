
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

import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

    public Font getFuente() {
        return fuente;
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
        if (opcion.equals("Texto")) {
            g = getGraphics();
            g2d = (Graphics2D) g;
            g2d.setFont(fuente);
            g2d.setColor(color);
            String string = JOptionPane.showInputDialog(this, "Ingrese el texto", "Texto", JOptionPane.DEFAULT_OPTION);
            g2d.drawString(string, xi, yi);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        xi = e.getX();
        yi = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        g = getGraphics();
        g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(grosorLinea));

        if (opcion.split(" ")[0].equals("Figura")) {

            int lados = Integer.parseInt(opcion.split(" ")[1]);

            if (lados == 0) { // La opcion 0 es la linea recta
                g.drawLine(xi, yi, e.getX(), e.getY());
                return;
            }
            if (lados == 1) { // La opcion 1 es un cuadrado

                dibujarCuadrado(e, g2d);
                return;
            } else if (lados == 2) { // rectangulo

                dibujarRectangulo(e, g2d);
                return;
            } else if (lados == 3) { // triangulo

                dibujarTriangulo(e, g2d);
                return;
            } else if (lados == 4) {// pentagono

                dibujarPentagono(e, g2d);
                return;
            } else if (lados == 5) { // hexagono
                dibujarHexagono(e, g2d);
                return;
            }
        }
    }

    private void dibujarCuadrado(MouseEvent e, Graphics2D g2d) {
        g2d.drawRect(xi, yi, e.getX() - xi, e.getX() - xi);
    }

    private void dibujarRectangulo(MouseEvent e, Graphics2D g2d) {
        g2d.drawRect(xi, yi, e.getX() - xi, e.getY() - yi);
    }

    private void dibujarTriangulo(MouseEvent e, Graphics2D g2d) {

        int verticeArribax = xi + (e.getX() - xi) / 2;
        int xPoints[] = { verticeArribax, e.getX(), xi };
        int yPoints[] = { yi, e.getY(), e.getY() };

        g2d.drawPolygon(xPoints, yPoints, 3);
    }

    private void dibujarPentagono(MouseEvent e, Graphics2D g2d) {

        int width = e.getX() - xi;
        int height = e.getY() - yi;

        int xPoints[] = { width / 2, width, (int) (width * 0.8), (int) (width * 0.2), 0 };
        int yPoints[] = { 0, (int) (height * 0.35), height, height, (int) (height * 0.35) };

        g2d.translate(xi, yi);
        g2d.drawPolygon(xPoints, yPoints, 5);
    }

    private void dibujarHexagono(MouseEvent e, Graphics2D g2d){
        int width = e.getX() - xi;
        int height = e.getY() - yi;

        int xPoints[] = { (int)(width * 0.25), (int)(width*0.75), width, (int)(width*0.75), (int)(width*0.25), 0};
        int yPoints[] = { 0, 0, (int)(height*0.5), height, height, (int)(height*0.5) };

        g2d.translate(xi, yi);
        g2d.drawPolygon(xPoints, yPoints, 6);
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
                g2d.setColor(color);
                g2d.drawLine(xi, yi, mx, my);
                xi = mx;
                yi = my;
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
