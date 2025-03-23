import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.*;

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
    JButton añadirPelicula = new JButton("Añadir");
    JButton buscarPelicula = new JButton("Buscar");
    JButton guardarPeliculas = new JButton("Guardar");

    // Panel que compone el titulo del header
    JPanel tituloHeader = new JPanel();
    tituloHeader.setLayout(new BoxLayout(tituloHeader, BoxLayout.Y_AXIS));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    tituloHeader.add(titulo);

    // Panel que compone los botones del header
    JPanel botonesHeader = new JPanel();
    botonesHeader.setLayout(new BoxLayout(botonesHeader, BoxLayout.X_AXIS));
    botonesHeader.add(añadirPelicula);
    botonesHeader.add(buscarPelicula);
    botonesHeader.add(guardarPeliculas);

    //Panel que contiene los dos paneles del header
    JPanel menuHeader = new JPanel();
    menuHeader.setLayout(new BoxLayout(menuHeader, BoxLayout.Y_AXIS));
    menuHeader.add(tituloHeader);
    menuHeader.add(botonesHeader);

    add(menuHeader);
    setVisible(true);
  }

  public static void main(String[] args) {
    new Codigo_7();
  }
}