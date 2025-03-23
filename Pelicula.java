public class Pelicula {

  // Definimos los atributos de la clase
  private String titulo;
  private String genero;
  private Integer calificacion;

  // Definimos el constructor de la clase
  public Pelicula(String titulo, String genero, Integer calificacion) {
    this.titulo = titulo;
    this.genero = genero;
    this.calificacion = calificacion;
  }

  // Definimos los getters y setters de los atributos
  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public Integer getCalificacion() {
    return calificacion;
  }

  public void setCalificacion(Integer calificacion) {
    this.calificacion = calificacion;
  }

  // Metodo toString()
  @Override
  public String toString() {
    return "Pelicula [titulo=" + titulo + ", genero=" + genero + ", calificacion=" + calificacion + "]";
  }
}