
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

    private JPanel opciones;
    private Lienzo lienzo;
    private JButton linea, cuadrado, rectangulo, triangulo, pentagono, hexagono, borrador, color;
    private JColorChooser selectorColor;

    public VentanaPrincipal() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Paint chido");
        this.setLayout(new BorderLayout());
        lienzo = new Lienzo();
        this.add(lienzo, BorderLayout.CENTER);
        lienzo.addMouseListener(lienzo);
        lienzo.addMouseMotionListener(lienzo);
        crearOpciones();
    }

    private void crearOpciones() {
        JPanel opciones = new JPanel(new FlowLayout(FlowLayout.LEFT));

        Image imagenBorrador = new ImageIcon("src/resources/borrador.png").getImage().getScaledInstance(24, 24,
                Image.SCALE_SMOOTH);
        borrador = new JButton("Borrar", new ImageIcon(imagenBorrador));
        borrador.setBorder(null);
        borrador.setBackground(null);
        borrador.addActionListener(new EventoOpcion(borrador.getActionCommand()));

        opciones.add(borrador);

        linea = new JButton("Linea");
        linea.setBorder(null);
        linea.setBackground(null);
        linea.addActionListener(new EventoOpcion(linea.getActionCommand()));
        opciones.add(linea);
        linea.addActionListener(null);

        this.add(opciones, BorderLayout.NORTH);
    }

    private class EventoOpcion implements ActionListener {

        private String opcion;

        public EventoOpcion(String opc) {
            opcion = opc;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            lienzo.setOpcion(opcion);
            System.out.println(opcion);
        }
    }

}
