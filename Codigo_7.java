import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Codigo_7 extends JFrame {

  // ArrayList que contiene las peliculas de la lista
  ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

  // Panel donde se van a almacenar las peliculas del inventario
  JPanel peliculasInventario = new JPanel();

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
    JTextField textoBuscarPelicula = new JTextField(10);
    JButton guardarPeliculas = new JButton("Guardar");

    // Creamos el boton volver para reiniciar el inventario
    JButton volverAtras = new JButton("Volver");
    // El boton al principio será invisible
    volverAtras.setVisible(false);
    JPanel panelBotonVolverAtras = new JPanel();
    panelBotonVolverAtras.add(volverAtras);


    // Crear panel para el título (centrado)
    JPanel tituloHeader = new JPanel();
    tituloHeader.setLayout(new BoxLayout(tituloHeader, BoxLayout.Y_AXIS));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    tituloHeader.add(Box.createVerticalStrut(20));
    tituloHeader.add(titulo);

    // Crear un panel con FlowLayout para los botones (espacio controlado entre
    // ellos)
    JPanel botonesHeader = new JPanel();
    botonesHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    botonesHeader.add(añadirPelicula);
    botonesHeader.add(guardarPeliculas);
    botonesHeader.add(buscarPelicula);
    botonesHeader.add(textoBuscarPelicula);

    // Crear el panel principal que contiene título y botones
    JPanel menuHeader = new JPanel();
    menuHeader.setLayout(new BoxLayout(menuHeader, BoxLayout.Y_AXIS));
    menuHeader.add(tituloHeader);
    menuHeader.add(Box.createVerticalStrut(20)); // Espacio entre el título y los botones
    menuHeader.add(botonesHeader);
    menuHeader.add(Box.createVerticalStrut(20));
    menuHeader.add(panelBotonVolverAtras);

    // Usamos BorderLayout para organizar los componentes
    setLayout(new BorderLayout());

    // Añadir el panel completo a la ventana
    add(menuHeader, BorderLayout.NORTH);
    setVisible(true);

    // Añadimos el panel peliculasInventario a la ventana principal
    peliculasInventario.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
    add(peliculasInventario, BorderLayout.CENTER);

    // Evento para añadir nuevas peliculas al inventario
    añadirPelicula.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mostrarVentanaAñadirPeliculas(); // Llamamos al metodo para mostrar la ventana JDialog
      }
    });

    // Evento para buscar películas por su nombre
    buscarPelicula.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        peliculasInventario.removeAll();
        ArrayList<Pelicula> peliculasEncontradas = new ArrayList<>();
        for (Pelicula i: listaPeliculas) {
          // Buscamos si el título coincide con el introducido por el usuario
          if (textoBuscarPelicula.getText().equals(i.getTitulo())) { 
            peliculasEncontradas.add(i);
          }
        }
        // Comprobamos si los resultados de búsqueda están vacíos
        if (peliculasEncontradas.isEmpty()) {
          JLabel mensajeNoHayPeliculasEncontradas = new JLabel("No hay películas que concuerden con la búsqueda");
          // Si no se encontraron se muestra un mensaje
          peliculasInventario.add(mensajeNoHayPeliculasEncontradas, BorderLayout.CENTER);
          mensajeNoHayPeliculasEncontradas.setVisible(true);
        } else { // Si se encontraron películas se actualiza el inventario
          actualizarPeliculasInventario(peliculasEncontradas);
        }
        peliculasInventario.revalidate(); // Revalidar para actualizar la vista
        peliculasInventario.repaint(); // Redibujar el panel con los nuevos componente

        // Cambiamos la visibilidad del botón para que se vea
        volverAtras.setVisible(true);
        // Si haces click en volver resetea el inventario completo
        volverAtras.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            actualizarPeliculasInventario(listaPeliculas);
            volverAtras.setVisible(false);
          }
        });
      }
    });
    
    // Evento para editar peliculas

  }

  // Metodo para mostrar el formulario de añadir peliculas
  public void mostrarVentanaAñadirPeliculas() {
    // Ventana JDialog
    JDialog formularioAñadirPeliculas = new JDialog(this, "Formulario para añadir películas", true);
    formularioAñadirPeliculas.setSize(300, 300);

    // Componentes de la ventana JDiaLog
    JLabel tituloPelicula = new JLabel("Título de la película:");
    JTextField textoTitulo = new JTextField(20); // Campo para el título
    JLabel generoPelicula = new JLabel("Género de la película:");
    JTextField textoGenero = new JTextField(20); // Campo para el género
    JLabel calificacionPelicula = new JLabel("Calificación de la película:");
    JTextField textoCalificacion = new JTextField(5); // Campo para la calificación
    JButton aceptarAñadirPelicula = new JButton("Aceptar");
    JButton cancelarAñadirPelicula = new JButton("Cancelar");

    // Crear panel para las etiquetas y los campos de texto
    JPanel formularioAñadirPelicula = new JPanel();
    formularioAñadirPelicula.setLayout(new BoxLayout(formularioAñadirPelicula, BoxLayout.Y_AXIS));
    formularioAñadirPelicula.add(tituloPelicula);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15)); // Espaciado entre componentes
    formularioAñadirPelicula.add(textoTitulo);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    formularioAñadirPelicula.add(generoPelicula);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    formularioAñadirPelicula.add(textoGenero);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    formularioAñadirPelicula.add(calificacionPelicula);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    formularioAñadirPelicula.add(textoCalificacion);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    
    // Crear panel para los botones del formulario
    JPanel botonesFormularioAñadirPelicula = new JPanel();
    botonesFormularioAñadirPelicula.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    botonesFormularioAñadirPelicula.add(aceptarAñadirPelicula);
    botonesFormularioAñadirPelicula.add(cancelarAñadirPelicula);

    // Evento de los botones del formulario
    aceptarAñadirPelicula.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Integer textoCalificacionNumero = Integer.valueOf(textoCalificacion.getText());
        Pelicula nuevaPelicula = new Pelicula(textoTitulo.getText(), textoGenero.getText(), textoCalificacionNumero); // Almacenamos la nueva película en un objeto

        // Añadimos la pelicula al ArrayList y actualizamos las peliculas del inventario
        listaPeliculas.add(nuevaPelicula);
        actualizarPeliculasInventario(listaPeliculas);
        formularioAñadirPeliculas.dispose(); // Salimos de la ventana JDialog
      }
    });

    cancelarAñadirPelicula.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        formularioAñadirPeliculas.dispose(); // Salimos de la ventana JDialog
      }
    });

    // Añadir todos los paneles a la ventana JDialog
    formularioAñadirPeliculas.setLayout(new FlowLayout());
    formularioAñadirPeliculas.add(formularioAñadirPelicula);
    formularioAñadirPeliculas.add(botonesFormularioAñadirPelicula);
    formularioAñadirPeliculas.setLocationRelativeTo(this);
    formularioAñadirPeliculas.setVisible(true);
  }

  // Metodo para actualizar las películas del inventario
  public void actualizarPeliculasInventario(ArrayList<Pelicula> peliculas) {
    peliculasInventario.removeAll();
    for (Pelicula i: peliculas) {
      // Creamos el panel de cada película
      JPanel pelicula = new JPanel();
      pelicula.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

      // Cogemos los valores de las peliculas y los almacenamos en etiquetas
      JLabel tituloPelicula = new JLabel(i.getTitulo());
      JLabel generoPelicula = new JLabel(i.getGenero());
      String textoCalificacionPelicula = String.valueOf(i.getCalificacion());
      JLabel calificacionPelicula = new JLabel(textoCalificacionPelicula);

      // Creamos panel de los campos de la pelicula
      JPanel valoresPelicula = new JPanel();
      valoresPelicula.setLayout(new BoxLayout(valoresPelicula, BoxLayout.Y_AXIS));
      valoresPelicula.add(tituloPelicula);
      valoresPelicula.add(generoPelicula);
      valoresPelicula.add(calificacionPelicula);

      // Creamos los botones editar y borrar (PARA CADA PELICULA)
      JButton editarPelicula = new JButton("Editar");
      JButton borrarPelicula = new JButton("Borrar");
      JPanel botonesPelicula = new JPanel();
      botonesPelicula.setLayout(new BoxLayout(botonesPelicula, BoxLayout.Y_AXIS));
      botonesPelicula.add(editarPelicula);
      botonesPelicula.add(Box.createVerticalStrut(7));
      botonesPelicula.add(borrarPelicula);

      // Añadimos el panel botonesPelicula a la pelicula
      pelicula.add(valoresPelicula);
      pelicula.add(botonesPelicula);

      // Añadimos la pelicula al inventario peliculas
      peliculasInventario.add(pelicula);

      // Evento para borrar peliculas
      borrarPelicula.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          peliculas.remove(i); // Borramos la pelicula del array
          actualizarPeliculasInventario(peliculas); // Actualizamos el inventario
        }
      });
    }
    peliculasInventario.revalidate(); // Revalidar para actualizar la vista
    peliculasInventario.repaint(); // Redibujar el panel con los nuevos componente
  }

  public static void main(String[] args) { // Método Main
    new Codigo_7();
  }
}