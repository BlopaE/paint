
package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Lienzo extends Canvas implements MouseListener, MouseMotionListener {
    private Color color;
    private int grosorLinea;
    private int xi, yi, xf, yf;
    private String opcion = "";

    public Lienzo() {
        super();
        this.setBackground(Color.WHITE);
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
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
    public void paint(Graphics g) {
        super.paint(g);

        switch (opcion) {
            case "Linea":
                dibujarLinea(g, xi, yi, xf, yf);
                break;
            case "Borrar":
                break;
            default:
                break;
        }
    }

    private void dibujarLinea(Graphics g, int xi,int yi,int xf,int yf){
        g.drawLine(xi, yi, xf, yf);
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
        // xf = e.getX();
        // yf = e.getY();
        // update(getGraphics());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        int mx = e.getX();
        int my = e.getY();

        System.out.println(mx + " " + my);

        Graphics g = getGraphics();
        g.drawLine(xi, yi, mx, my);
        xi = mx;
        yi = my;
        g.dispose();
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
