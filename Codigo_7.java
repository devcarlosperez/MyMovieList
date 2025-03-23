import javax.swing.*;
import java.awt.Component;
import java.awt.*;

public class Codigo_7 extends JFrame {

  // Definimos el constructor de la clase
  public Codigo_7() {
    initComponents();
  }

  // Definimos el metodo donde están los componentes de la ventana
  public void initComponents() {

    // Valores iniciales de la ventana
    setSize(600, 600);
    setTitle("MyMovieList");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Componentes del header
    JLabel titulo = new JLabel("MyMovieList");
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el título
    JButton añadirPelicula = new JButton("Añadir");
    JButton buscarPelicula = new JButton("Buscar");
    JButton guardarPeliculas = new JButton("Guardar");

    // Crear panel para el título (centrado)
    JPanel tituloHeader = new JPanel();
    tituloHeader.setLayout(new BoxLayout(tituloHeader, BoxLayout.Y_AXIS));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    tituloHeader.add(Box.createVerticalStrut(20));
    tituloHeader.add(titulo);

    // Crear un panel con FlowLayout para los botones (espacio controlado entre ellos)
    JPanel botonesHeader = new JPanel();
    botonesHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    botonesHeader.add(añadirPelicula);
    botonesHeader.add(buscarPelicula);
    botonesHeader.add(guardarPeliculas);

    // Crear el panel principal que contiene título y botones
    JPanel menuHeader = new JPanel();
    menuHeader.setLayout(new BoxLayout(menuHeader, BoxLayout.Y_AXIS));
    menuHeader.add(tituloHeader);
    menuHeader.add(Box.createVerticalStrut(20)); // Espacio entre el título y los botones
    menuHeader.add(botonesHeader);

    // Añadir el panel completo a la ventana
    add(menuHeader);
    setVisible(true);
  }

  public static void main(String[] args) {
    new Codigo_7();
  }
}