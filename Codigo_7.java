import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Codigo_7 extends JFrame {

  // ArrayList que contiene las peliculas de la lista
  ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

  // Archivo csv que va a contener las peliculas guardadas
  File archivoPeliculas = new File("peliculas.csv");

  // Metodo para cargar las peliculas del archivo en el ArrayList
  public void cargarPeliculas() {
    try {
      BufferedReader archivoPeliculasLeer = new BufferedReader(new FileReader(archivoPeliculas));
      // Recorremos el archivo linea por linea
      String linea;
      while ((linea = archivoPeliculasLeer.readLine()) != null) {
        String[] datos = linea.split(",");
        String titulo = datos[0];
        String genero = datos[1];
        Integer calificacion = Integer.valueOf(datos[2]);
        // Almacenamos los valores del array en un nuevo objeto
        Pelicula pelicula = new Pelicula(titulo, genero, calificacion);
        listaPeliculas.add(pelicula); // Añadimos el objeto a la lista
      }
      actualizarPeliculasInventario(listaPeliculas); // Llamamos al metodo para mostrar el inventario
      archivoPeliculasLeer.close();
    } catch (IOException error) {
      System.out.println("Error: " + error.getMessage());
    }
  }

  // Metodo para mostrar ventana de confirmación guardar peliculas
  public void mostrarVentanaGuardarPeliculas() {
    // Ventana de confirmacion peliculas guardadas
    JDialog confirmacionPeliculasGuardadas = new JDialog(this, "Confirmación peliculas guardadas", true);
    confirmacionPeliculasGuardadas.setSize(300, 150);
    // Etiqueta confirmacion
    JLabel mensajeConfirmacionPeliculasGuardadas = new JLabel("Las peliculas se han guardado exitosamente");
    // Boton aceptar
    JButton aceptarConfirmacionPeliculasGuardadas = new JButton("Aceptar");
    aceptarConfirmacionPeliculasGuardadas.setBackground(Color.decode("#47a1ec"));

    // Evento para cerrar la ventana
    aceptarConfirmacionPeliculasGuardadas.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        confirmacionPeliculasGuardadas.dispose();
      }
    });
    // Creamos los paneles para los contenidos de la ventana
    mensajeConfirmacionPeliculasGuardadas.setAlignmentX(CENTER_ALIGNMENT);
    JPanel panelMensajeConfirmacion = new JPanel();
    panelMensajeConfirmacion.setLayout(new BoxLayout(panelMensajeConfirmacion, BoxLayout.Y_AXIS));
    panelMensajeConfirmacion.add(Box.createVerticalStrut(10));
    panelMensajeConfirmacion.add(mensajeConfirmacionPeliculasGuardadas);

    JPanel panelBotonConfirmacion = new JPanel();
    panelBotonConfirmacion.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    panelBotonConfirmacion.add(aceptarConfirmacionPeliculasGuardadas);

    JPanel ventanaConfirmacion = new JPanel();
    ventanaConfirmacion.setLayout(new BoxLayout(ventanaConfirmacion, BoxLayout.Y_AXIS));
    ventanaConfirmacion.add(panelMensajeConfirmacion);
    ventanaConfirmacion.add(Box.createVerticalStrut(20));
    ventanaConfirmacion.add(panelBotonConfirmacion);

    // Añadimos todo a la ventana JDialog
    confirmacionPeliculasGuardadas.add(ventanaConfirmacion);
    confirmacionPeliculasGuardadas.setLocationRelativeTo(this);
    confirmacionPeliculasGuardadas.setVisible(true);
  }

  // Metodo para mostrar ventana de confirmacion borrar peliculas
  public void mostrarVentanaBorrarPeliculas(Pelicula pelicula) {
    // Ventana de confirmacion peliculas borradas
    JDialog confirmacionPeliculasBorradas = new JDialog(this, "Confirmación peliculas borradas", true);
    confirmacionPeliculasBorradas.setSize(350, 150);
    // Etiqueta confirmacion
    JLabel mensajeConfirmacionPeliculasBorradas = new JLabel("¿Estas seguro/a que quieres eliminar esta pelicula?");
    // Boton aceptar
    JButton aceptarConfirmacionPeliculasBorradas = new JButton("Aceptar");
    JButton cancelarConfirmacionPeliculasBorradas = new JButton("Cancelar");
    aceptarConfirmacionPeliculasBorradas.setBackground(Color.decode("#e04545"));
    cancelarConfirmacionPeliculasBorradas.setBackground(Color.decode("#d4d3d3"));

    // Eventos para aceptar o cancelar la eliminacion de la pelicula
    aceptarConfirmacionPeliculasBorradas.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        listaPeliculas.remove(pelicula); // Borramos la pelicula del array
        actualizarPeliculasInventario(listaPeliculas); // Actualizamos el inventario
        confirmacionPeliculasBorradas.dispose();
      }
    });
    cancelarConfirmacionPeliculasBorradas.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        confirmacionPeliculasBorradas.dispose();
      }
    });
    // Creamos los paneles para los contenidos de la ventana
    mensajeConfirmacionPeliculasBorradas.setAlignmentX(CENTER_ALIGNMENT);
    JPanel panelMensajeConfirmacion = new JPanel();
    panelMensajeConfirmacion.setLayout(new BoxLayout(panelMensajeConfirmacion, BoxLayout.Y_AXIS));
    panelMensajeConfirmacion.add(Box.createVerticalStrut(10));
    panelMensajeConfirmacion.add(mensajeConfirmacionPeliculasBorradas);

    JPanel panelBotonesConfirmacion = new JPanel();
    panelBotonesConfirmacion.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    panelBotonesConfirmacion.add(aceptarConfirmacionPeliculasBorradas);
    panelBotonesConfirmacion.add(cancelarConfirmacionPeliculasBorradas);

    JPanel ventanaConfirmacion = new JPanel();
    ventanaConfirmacion.setLayout(new BoxLayout(ventanaConfirmacion, BoxLayout.Y_AXIS));
    ventanaConfirmacion.add(panelMensajeConfirmacion);
    ventanaConfirmacion.add(Box.createVerticalStrut(20));
    ventanaConfirmacion.add(panelBotonesConfirmacion);

    // Añadimos todo a la ventana JDialog
    confirmacionPeliculasBorradas.add(ventanaConfirmacion);
    confirmacionPeliculasBorradas.setLocationRelativeTo(this);
    confirmacionPeliculasBorradas.setVisible(true);
  }

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

    // Llamamos al metodo cargarPeliculas
    cargarPeliculas();

    // Componentes del header
    JLabel titulo = new JLabel("MyMovieList");
    // Estilos al titulo de la aplicacion
    titulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el título
    JButton añadirPelicula = new JButton("Añadir");
    JButton buscarPelicula = new JButton("Buscar");
    JTextField textoBuscarPelicula = new JTextField(10);
    JButton guardarPeliculas = new JButton("Guardar");
    JButton limpiarBusqueda = new JButton("Limpiar");

    // Creamos el boton volver para reiniciar el inventario
    JButton volverAtras = new JButton("Volver");
    // El boton al principio será invisible
    volverAtras.setVisible(false);
    JPanel panelBotonVolverAtras = new JPanel();
    panelBotonVolverAtras.add(volverAtras);

    // Estilos para los botones del header
    añadirPelicula.setBackground(Color.decode("#47a1ec"));
    añadirPelicula.setFont(new Font("Segoe Ui", Font.BOLD, 13));
    buscarPelicula.setBackground(Color.decode("#47a1ec"));
    buscarPelicula.setFont(new Font("Segoe Ui", Font.BOLD, 13));
    guardarPeliculas.setBackground(Color.decode("#47a1ec"));
    guardarPeliculas.setFont(new Font("Segoe Ui", Font.BOLD, 13));
    limpiarBusqueda.setBackground(Color.decode("#47a1ec"));
    limpiarBusqueda.setFont(new Font("Segoe Ui", Font.BOLD, 13));
    volverAtras.setBackground(Color.decode("#e04545"));
    volverAtras.setFont(new Font("Segoe Ui", Font.BOLD, 13));

    // Crear panel para el título (centrado)
    JPanel tituloHeader = new JPanel();
    tituloHeader.setLayout(new BoxLayout(tituloHeader, BoxLayout.Y_AXIS));
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
    botonesHeader.add(limpiarBusqueda);

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
        for (Pelicula i : listaPeliculas) {
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

    // Evento para limpiar la busqueda del usuario
    limpiarBusqueda.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textoBuscarPelicula.setText(""); // Limpiamos el texto de busqueda
        // Actualizamos el panel y ocultamos el boton
        actualizarPeliculasInventario(listaPeliculas);
        volverAtras.setVisible(false);
      }
    });

    // Evento para guardar las peliculas
    guardarPeliculas.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          BufferedWriter archivoPeliculasEscribir = new BufferedWriter(new FileWriter(archivoPeliculas));
          for (Pelicula i : listaPeliculas) { // Recorremos la lista de peliculas
            // Escribir los valores de cada pelicula en el archivo
            archivoPeliculasEscribir.write(i.getTitulo() + "," + i.getGenero() + "," + i.getCalificacion());
            archivoPeliculasEscribir.newLine();
          }
          archivoPeliculasEscribir.close();
          mostrarVentanaGuardarPeliculas();
        } catch (IOException error) {
          System.out.println("Error: " + error.getMessage());
        }
      }
    });
  }

  // Metodo para mostrar el formulario de añadir peliculas
  public void mostrarVentanaAñadirPeliculas() {
    // Ventana JDialog
    JDialog formularioAñadirPeliculas = new JDialog(this, "Formulario para añadir películas", true);
    formularioAñadirPeliculas.setSize(320, 350);

    // Componentes de la ventana JDiaLog
    JLabel tituloPelicula = new JLabel("Título de la película:");
    JTextField textoTitulo = new JTextField(20); // Campo para el título
    JLabel campoObligatorioTitulo = new JLabel("Campo Obligatorio *");
    campoObligatorioTitulo.setForeground(Color.decode("#e04545"));
    campoObligatorioTitulo.setVisible(false);

    JLabel generoPelicula = new JLabel("Género de la película:");
    JTextField textoGenero = new JTextField(20); // Campo para el género
    JLabel campoObligatorioGenero = new JLabel("Campo Obligatorio *");
    campoObligatorioGenero.setVisible(false);
    campoObligatorioGenero.setForeground(Color.decode("#e04545"));

    JLabel calificacionPelicula = new JLabel("Calificación de la película:");
    JTextField textoCalificacion = new JTextField(5); // Campo para la calificación
    JLabel campoObligatorioCalificacion = new JLabel("Campo Obligatorio *");
    campoObligatorioCalificacion.setVisible(false);
    campoObligatorioCalificacion.setForeground(Color.decode("#e04545"));
    JLabel errorCalificacionNumero = new JLabel("La calificación debe ser un número");
    errorCalificacionNumero.setForeground(Color.decode("#e04545"));
    errorCalificacionNumero.setVisible(false);

    JButton aceptarAñadirPelicula = new JButton("Aceptar");
    JButton cancelarAñadirPelicula = new JButton("Cancelar");
    aceptarAñadirPelicula.setBackground(Color.decode("#47a1ec"));
    cancelarAñadirPelicula.setBackground(Color.decode("#d4d3d3"));

    // Crear panel para las etiquetas y los campos de texto
    JPanel formularioAñadirPelicula = new JPanel();
    formularioAñadirPelicula.setLayout(new BoxLayout(formularioAñadirPelicula, BoxLayout.Y_AXIS));
    formularioAñadirPelicula.add(tituloPelicula);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15)); // Espaciado entre componentes
    formularioAñadirPelicula.add(textoTitulo);
    formularioAñadirPelicula.add(campoObligatorioTitulo);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    formularioAñadirPelicula.add(generoPelicula);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    formularioAñadirPelicula.add(textoGenero);
    formularioAñadirPelicula.add(campoObligatorioGenero);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    formularioAñadirPelicula.add(calificacionPelicula);
    formularioAñadirPelicula.add(Box.createVerticalStrut(15));
    formularioAñadirPelicula.add(textoCalificacion);
    formularioAñadirPelicula.add(campoObligatorioCalificacion);
    formularioAñadirPelicula.add(errorCalificacionNumero);
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
        // Variable para ver si los campos no estan vacios
        boolean camposVacios = false;

        // Variable para comprobar que la calificacion es un numero
        boolean calificacionEsNumero = false;

        // Comprobamos si los campos no estan vacios
        if (textoTitulo.getText().trim().isEmpty()) {
          campoObligatorioTitulo.setVisible(true);
          camposVacios = true;
        } else {
          campoObligatorioTitulo.setVisible(false);
        }

        if (textoGenero.getText().trim().isEmpty()) {
          campoObligatorioGenero.setVisible(true);
          camposVacios = true;
        } else {
          campoObligatorioGenero.setVisible(false);
        }

        if (textoCalificacion.getText().trim().isEmpty()) {
          campoObligatorioCalificacion.setVisible(true);
          camposVacios = true;
        } else {
          campoObligatorioCalificacion.setVisible(false);
        }

        // Declaramos la variable para pasar la calificacion a texto
        Integer textoCalificacionNumero = null;

        // Comprobamos la validacion
        if (!textoCalificacion.getText().trim().isEmpty()) {
          try {
            textoCalificacionNumero = Integer.valueOf(textoCalificacion.getText());
            calificacionEsNumero = true;
          } catch (NumberFormatException ex) {
            errorCalificacionNumero.setVisible(true);
          }
        }

        // Si no hay campos vacios se ejecuta el añadir pelicula
        if (camposVacios == false && calificacionEsNumero == true) {
          Pelicula nuevaPelicula = new Pelicula(textoTitulo.getText(), textoGenero.getText(), textoCalificacionNumero);
          // Añadimos la pelicula al ArrayList y actualizamos las peliculas del inventario
          listaPeliculas.add(nuevaPelicula);
          actualizarPeliculasInventario(listaPeliculas);
          formularioAñadirPeliculas.dispose(); // Salimos de la ventana JDialog
        }
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
    peliculasInventario.setOpaque(true);
    peliculasInventario.setBackground(Color.decode("#f5f5f5"));
    for (Pelicula i : peliculas) {
      // Creamos el panel de cada película
      JPanel pelicula = new JPanel();
      pelicula.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

      // Cogemos los valores de las peliculas y los almacenamos en etiquetas
      JLabel tituloPelicula = new JLabel(i.getTitulo());
      JLabel generoPelicula = new JLabel(i.getGenero());
      String textoCalificacionPelicula = String.valueOf(i.getCalificacion());
      JLabel calificacionPelicula = new JLabel(textoCalificacionPelicula);

      // Estilos para las etiquetas
      tituloPelicula.setFont(new Font("Segoe UI", Font.BOLD, 18));
      generoPelicula.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      calificacionPelicula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
      calificacionPelicula.setForeground(Color.decode("#dfc209"));

      // Creamos panel de los campos de la pelicula
      JPanel valoresPelicula = new JPanel();
      valoresPelicula.setLayout(new BoxLayout(valoresPelicula, BoxLayout.Y_AXIS));
      valoresPelicula.add(tituloPelicula);
      valoresPelicula.add(generoPelicula);
      valoresPelicula.add(calificacionPelicula);

      // Creamos los botones editar y borrar (PARA CADA PELICULA)
      JButton editarPelicula = new JButton("Editar");
      JButton borrarPelicula = new JButton("Borrar");
      editarPelicula.setBackground(Color.decode("#67ff9e"));
      borrarPelicula.setBackground(Color.decode("#e04545"));
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
          mostrarVentanaBorrarPeliculas(i);
        }
      });

      // Evento para editar peliculas
      editarPelicula.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          mostrarVentanaEditarPeliculaa(i); // Llamamos al método para mostrar la ventana JDialog
        }
      });
    }
    peliculasInventario.revalidate(); // Revalidar para actualizar la vista
    peliculasInventario.repaint(); // Redibujar el panel con los nuevos componente
  }

  // Metodo para mostrar el formulario para editar películas
  public void mostrarVentanaEditarPeliculaa(Pelicula pelicula) {
    // Ventana JDialog
    JDialog formularioEditarPeliculas = new JDialog(this, "Formulario para editar películas", true);
    formularioEditarPeliculas.setSize(320, 350);

    // Componentes de la ventana JDiaLog
    JLabel tituloPelicula = new JLabel("Título de la película:");
    JTextField textoTitulo = new JTextField(20); // Campo para el título
    JLabel campoObligatorioTitulo = new JLabel("Campo Obligatorio *");
    campoObligatorioTitulo.setForeground(Color.decode("#e04545"));
    campoObligatorioTitulo.setVisible(false);
    textoTitulo.setText(pelicula.getTitulo()); // Titulo de la pelicula anterior

    JLabel generoPelicula = new JLabel("Género de la película:");
    JTextField textoGenero = new JTextField(20); // Campo para el género
    JLabel campoObligatorioGenero = new JLabel("Campo Obligatorio *");
    campoObligatorioGenero.setForeground(Color.decode("#e04545"));
    campoObligatorioGenero.setVisible(false);
    textoGenero.setText(pelicula.getGenero()); // Genero de la pelicula anterior

    JLabel calificacionPelicula = new JLabel("Calificación de la película:");
    JTextField textoCalificacion = new JTextField(5); // Campo para la calificación
    JLabel campoObligatorioCalificacion = new JLabel("Campo Obligatorio *");
    campoObligatorioCalificacion.setForeground(Color.decode("#e04545"));
    campoObligatorioCalificacion.setVisible(false);
    JLabel errorCalificacionNumero = new JLabel("La calificación debe ser un número");
    errorCalificacionNumero.setForeground(Color.decode("#e04545"));
    errorCalificacionNumero.setVisible(false);
    String calificacionString = String.valueOf(pelicula.getCalificacion());
    textoCalificacion.setText(calificacionString);

    JButton aceptarEditarPelicula = new JButton("Aceptar");
    JButton cancelarEditarPelicula = new JButton("Cancelar");
    aceptarEditarPelicula.setBackground(Color.decode("#47a1ec"));
    cancelarEditarPelicula.setBackground(Color.decode("#d4d3d3"));

    // Crear panel para las etiquetas y los campos de texto
    JPanel formularioEditarPelicula = new JPanel();
    formularioEditarPelicula.setLayout(new BoxLayout(formularioEditarPelicula, BoxLayout.Y_AXIS));
    formularioEditarPelicula.add(tituloPelicula);
    formularioEditarPelicula.add(Box.createVerticalStrut(15)); // Espaciado entre componentes
    formularioEditarPelicula.add(textoTitulo);
    formularioEditarPelicula.add(campoObligatorioTitulo);
    formularioEditarPelicula.add(Box.createVerticalStrut(15));
    formularioEditarPelicula.add(generoPelicula);
    formularioEditarPelicula.add(Box.createVerticalStrut(15));
    formularioEditarPelicula.add(textoGenero);
    formularioEditarPelicula.add(campoObligatorioGenero);
    formularioEditarPelicula.add(Box.createVerticalStrut(15));
    formularioEditarPelicula.add(calificacionPelicula);
    formularioEditarPelicula.add(Box.createVerticalStrut(15));
    formularioEditarPelicula.add(textoCalificacion);
    formularioEditarPelicula.add(campoObligatorioCalificacion);
    formularioEditarPelicula.add(errorCalificacionNumero);
    formularioEditarPelicula.add(Box.createVerticalStrut(15));

    // Crear panel para los botones del formulario
    JPanel botonesFormularioEditarPelicula = new JPanel();
    botonesFormularioEditarPelicula.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    botonesFormularioEditarPelicula.add(aceptarEditarPelicula);
    botonesFormularioEditarPelicula.add(cancelarEditarPelicula);

    // Evento de los botones del formulario
    aceptarEditarPelicula.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Variable para ver si los campos estan rellenados
        boolean camposVacios = false;

        // Variable para comprobar que la calificacion es un numero
        boolean calificacionEsNumero = false;

        // Comprobamos si los campos no estan vacios
        if (textoTitulo.getText().trim().isEmpty()) {
          campoObligatorioTitulo.setVisible(true);
          camposVacios = true;
        } else {
          campoObligatorioTitulo.setVisible(false);
        }

        if (textoGenero.getText().trim().isEmpty()) {
          campoObligatorioGenero.setVisible(true);
          camposVacios = true;
        } else {
          campoObligatorioGenero.setVisible(false);
        }

        if (textoCalificacion.getText().trim().isEmpty()) {
          campoObligatorioCalificacion.setVisible(true);
          camposVacios = true;
        } else {
          campoObligatorioCalificacion.setVisible(false);
        }

        // Declaramos la variable para pasar la calificacion a texto
        Integer nuevaCalificacionPelicula = null;

        // Comprobamos la validacion
        if (!textoCalificacion.getText().trim().isEmpty()) {
          try {
            nuevaCalificacionPelicula = Integer.valueOf(textoCalificacion.getText());
            calificacionEsNumero = true;
          } catch (NumberFormatException ex) {
            errorCalificacionNumero.setVisible(true);
          }
        }

        // Si no hay campos vacios se ejecuta el añadir pelicula
        if (camposVacios == false && calificacionEsNumero == true) {
          // Obtenemos los nuevos valores de los campos de texto
          String nuevoTituloPelicula = textoTitulo.getText();
          String nuevoGeneroPelicula = textoGenero.getText();

          // Buscamos en la lista el mismo titulo y cambiamos los valores
          for (Pelicula i : listaPeliculas) {
            if (i.getTitulo().equals(pelicula.getTitulo())) {
              i.setTitulo(nuevoTituloPelicula);
              i.setGenero(nuevoGeneroPelicula);
              i.setCalificacion(nuevaCalificacionPelicula);
            }
          }
          actualizarPeliculasInventario(listaPeliculas);
          formularioEditarPeliculas.dispose(); // Salimos de la ventana JDialog
        }
      }
    });

    cancelarEditarPelicula.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        formularioEditarPeliculas.dispose(); // Salimos de la ventana JDialog
      }
    });

    // Añadir todos los paneles a la ventana JDialog
    formularioEditarPeliculas.setLayout(new FlowLayout());
    formularioEditarPeliculas.add(formularioEditarPelicula);
    formularioEditarPeliculas.add(botonesFormularioEditarPelicula);
    formularioEditarPeliculas.setLocationRelativeTo(this);
    formularioEditarPeliculas.setVisible(true);
  }

  public static void main(String[] args) { // Método Main
    new Codigo_7();
  }
}