# MyMovieList

## Descripción

MyMovieList es una aplicación de escritorio desarrollada en Java que permite a los usuarios gestionar su propia lista de películas. Los usuarios pueden añadir, buscar, editar y eliminar películas de su inventario, así como guardar y cargar la lista de películas desde un archivo CSV. La interfaz gráfica está construida utilizando Swing, lo que proporciona una experiencia de usuario intuitiva y amigable.

## Características

- **Añadir Películas**: Permite a los usuarios agregar nuevas películas a su lista, especificando el título, género y calificación.
- **Buscar Películas**: Los usuarios pueden buscar películas por su título.
- **Editar Películas**: Permite a los usuarios modificar los detalles de una película existente.
- **Eliminar Películas**: Los usuarios pueden eliminar películas de su lista.
- **Guardar y Cargar**: La lista de películas se guarda en un archivo CSV, lo que permite la persistencia de datos entre sesiones.

## Requisitos

- Java Development Kit (JDK) 8 o superior.
- Un entorno de desarrollo integrado (IDE) como IntelliJ IDEA, Eclipse o NetBeans.

## Instalación

1. Clona este repositorio o descarga el archivo ZIP.
2. Asegúrate de tener el JDK instalado en tu máquina.
3. Abre el proyecto en tu IDE favorito.
4. Compila y ejecuta la clase `Codigo_7`.

## Uso

1. Al iniciar la aplicación, se cargará automáticamente la lista de películas desde el archivo `peliculas.csv` (si existe).
2. Utiliza el botón "Añadir" para agregar nuevas películas.
3. Usa el campo de búsqueda y el botón "Buscar" para encontrar películas por título.
4. Selecciona una película y utiliza los botones "Editar" o "Borrar" para modificar o eliminar la película seleccionada.
5. Haz clic en "Guardar" para guardar la lista de películas en el archivo `peliculas.csv`.

## Estructura del Proyecto

- **Codigo_7.java**: Clase principal que contiene la lógica de la aplicación y la interfaz gráfica.
- **Pelicula.java**: Clase que representa una película, con atributos como título, género y calificación.
- **peliculas.csv**: Archivo donde se almacenan las películas en formato CSV.

## Autor

Carlos Luis Pérez Santana