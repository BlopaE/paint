
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VentanaPrincipal extends JFrame {

        private JPanel opciones;
        private Lienzo lienzo;
        private JButton linea, texto, borrador, relleno, color, limpiar;
        private JComboBox<ImageIcon> figuras;
        private JComboBox<String> fuentes;
        private JSlider grosor;

        public VentanaPrincipal() {
                this.setSize(1000, 600);
                this.setLocationRelativeTo(null);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setResizable(false);
                this.setTitle("Paint patito");
                this.setLayout(new BorderLayout());
                lienzo = new Lienzo();
                this.add(lienzo, BorderLayout.CENTER);
                lienzo.addMouseListener(lienzo);
                lienzo.addMouseMotionListener(lienzo);
                crearOpciones();
        }

        private void crearOpciones() {
                opciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
                opciones.setBackground(new Color(240, 240, 240));

                Image imagenLapiz = new ImageIcon("src/resources/lapiz.png").getImage().getScaledInstance(24, 24,
                                Image.SCALE_SMOOTH);
                linea = new JButton(new ImageIcon(imagenLapiz));
                linea.setBorder(null);
                linea.setBackground(opciones.getBackground());
                linea.setActionCommand("Trazo");
                linea.addActionListener(new EventoOpcion(linea));
                linea.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

                opciones.add(linea);

                Image imagenBorrador = new ImageIcon("src/resources/borrador.png").getImage().getScaledInstance(24, 24,
                                Image.SCALE_SMOOTH);
                borrador = new JButton(new ImageIcon(imagenBorrador));
                borrador.setBorder(null);
                borrador.setBackground(opciones.getBackground());
                borrador.setActionCommand("Borrar");
                borrador.addActionListener(new EventoOpcion(borrador));
                borrador.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

                opciones.add(borrador);

                JLabel etiquetaFiguras = new JLabel("Figuras: ");
                opciones.add(etiquetaFiguras);

                // Agregar iconos de las figuras
                figuras = new JComboBox<>();
                figuras.addItem(new ImageIcon(
                                new ImageIcon("src/resources/linea.png").getImage().getScaledInstance(24, 24,
                                                Image.SCALE_SMOOTH)));
                figuras.addItem(new ImageIcon(
                                new ImageIcon("src/resources/cuadrado.png").getImage().getScaledInstance(24, 24,
                                                Image.SCALE_SMOOTH)));
                figuras.addItem(new ImageIcon(
                                new ImageIcon("src/resources/rectangulo.png").getImage().getScaledInstance(24, 24,
                                                Image.SCALE_SMOOTH)));
                figuras.addItem(new ImageIcon(
                                new ImageIcon("src/resources/triangulo.png").getImage().getScaledInstance(24, 24,
                                                Image.SCALE_SMOOTH)));
                figuras.addItem(new ImageIcon(
                                new ImageIcon("src/resources/pentagono.png").getImage().getScaledInstance(24, 24,
                                                Image.SCALE_SMOOTH)));
                figuras.addItem(new ImageIcon(
                                new ImageIcon("src/resources/hexagono.png").getImage().getScaledInstance(24, 24,
                                                Image.SCALE_SMOOTH)));
                figuras.setActionCommand("Figura");
                figuras.addActionListener(new EventoOpcion(figuras.getActionCommand()));
                opciones.add(figuras);

                // Boton de texto

                Image imagenTexto = new ImageIcon("src/resources/texto.png").getImage().getScaledInstance(24, 24,
                                Image.SCALE_SMOOTH);
                texto = new JButton(new ImageIcon(imagenTexto));
                texto.setBorder(null);
                texto.setBackground(opciones.getBackground());
                texto.setActionCommand("Texto");
                texto.addActionListener(new EventoOpcion(texto));
                texto.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                opciones.add(texto);

                // Agregar fuentes instaladas en el sistema

                JLabel etiquetaFuentes = new JLabel("Fuente: ");
                opciones.add(etiquetaFuentes);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Font[] fonts = ge.getAllFonts();
                fuentes = new JComboBox<>();

                for (Font f : fonts) {
                        String name = f.getName();
                        fuentes.addItem(name);
                }

                fuentes.setActionCommand("Fuente");
                fuentes.addActionListener(new EventoOpcion(fuentes.getActionCommand()));

                opciones.add(fuentes);

                // Boton relleno
                Image imagenRelleno = new ImageIcon("src/resources/relleno.png").getImage().getScaledInstance(24, 24,
                                Image.SCALE_SMOOTH);
                relleno = new JButton(new ImageIcon(imagenRelleno));
                relleno.setBorder(null);
                relleno.setBackground(opciones.getBackground());
                relleno.setActionCommand("Relleno");
                relleno.addActionListener(new EventoOpcion(relleno));
                relleno.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                opciones.add(relleno);

                JLabel etiquetaColor = new JLabel("Color:");
                opciones.add(etiquetaColor);
                color = new JButton("   ");
                color.setBorder(null);
                color.setBackground(Color.BLACK);
                color.setActionCommand("Color");
                color.addActionListener(new EventoOpcion(color));
                color.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                opciones.add(color);
                color.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                JColorChooser.showDialog(null, "Seleccione un color", null, true);
                        }
                });

                // Grosor
                JLabel etiquetaGrosor = new JLabel("Grosor:");
                opciones.add(etiquetaGrosor);

                grosor = new JSlider(JSlider.HORIZONTAL, 1, 10, 2);
                grosor.addChangeListener(new EventoOpcion("Grosor"));
                opciones.add(grosor);

                this.add(opciones, BorderLayout.NORTH);

                //Limpiar
                Image imagenLimpiar = new ImageIcon("src/resources/limpiar.png").getImage().getScaledInstance(24, 24,
                                Image.SCALE_SMOOTH);
                limpiar = new JButton(new ImageIcon(imagenLimpiar));
                limpiar.setBorder(null);
                limpiar.setBackground(opciones.getBackground());
                limpiar.setActionCommand("Limpiar");
                limpiar.addActionListener(new EventoOpcion(limpiar));
                limpiar.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                opciones.add(limpiar);
        }

        private class EventoOpcion implements ActionListener, ChangeListener {

                private String opcion;

                public EventoOpcion(JButton opc) {
                        opcion = opc.getActionCommand();
                }

                public EventoOpcion(String opc) {
                        opcion = opc;
                }

                @Override
                public void actionPerformed(ActionEvent e) {

                        if (opcion.equals("Limpiar")) {
                                lienzo.repaint();                                
                                return;
                        }

                        String opcionDetallada = opcion;
                        if (opcion.equals("Figura")) {
                                opcionDetallada = String.valueOf(figuras.getSelectedIndex());
                                lienzo.setOpcion(opcionDetallada);
                        } else if (opcion.equals("Fuente")) {
                                opcionDetallada = (String) fuentes.getSelectedItem();
                                lienzo.setFuente(new Font(opcionDetallada, Font.PLAIN, lienzo.getGrosorLinea() * 10));
                        } else {
                                lienzo.setOpcion(opcionDetallada);
                        }
                        System.out.println(opcionDetallada);

                }

                // Este solo funciona con el Slider
                @Override
                public void stateChanged(ChangeEvent e) {
                        lienzo.setGrosorLinea(grosor.getValue());
                        System.out.println(grosor.getValue());
                }
        }

}
