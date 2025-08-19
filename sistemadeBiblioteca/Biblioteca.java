import java.util.*;
import java.time.*;

class Libro {
    private String titulo;
    private String autor;
    private String codigo;
    private boolean disponible;

    public Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponible = true;
    }

    public void mostrarDatos() {
        System.out.println("Código: " + codigo + " | Título: " + titulo + " | Autor: " + autor + " | Disponible: " + (disponible ? "Sí" : "No"));
    }

    public void marcarPrestado() {
        disponible = false;
    }

    public void marcarDisponible() {
        disponible = true;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }
}

class Usuario {
    private String nombre;
    private String idUsuario;
    private List<Libro> librosPrestados = new ArrayList<>();

    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    public void mostrarDatos() {
        System.out.println("ID: " + idUsuario + " | Nombre: " + nombre + " | Libros prestados: " + librosPrestados.size());
    }

    public void agregarPrestamo(Libro libro) {
        if (librosPrestados.size() >= 3) {
            System.out.println("El usuario ya tiene 3 libros prestados.");
            return;
        }
        librosPrestados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }
}

class Prestamo {
    Libro libro;
    Usuario usuario;
    LocalDate fechaInicio;
    LocalDate fechaLimite;

    public Prestamo(Libro libro, Usuario usuario, LocalDate inicio, LocalDate limite) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaInicio = inicio;
        this.fechaLimite = limite;
    }

    public void mostrarPrestamo() {
        System.out.println(
            "Usuario: " + usuario.getNombre() + " (" + usuario.getIdUsuario() + ")"
            + " | Libro: " + libro.getCodigo() + " - " + libro.getTitulo()
            + " | Inicio: " + fechaInicio
            + " | Vencimiento: " + fechaLimite
        );
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }
}

class SistemaBiblioteca {
    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Prestamo> prestamos = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void cargarDatosPrueba() {
        libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "L001"));
        libros.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "L002"));
        libros.add(new Libro("La sombra del viento", "Carlos Ruiz Zafón", "L003"));
        usuarios.add(new Usuario("Ana", "U001"));
        usuarios.add(new Usuario("Luis", "U002"));
        usuarios.add(new Usuario("Marta", "U003"));
    }

    public void registrarLibro() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        libros.add(new Libro(titulo, autor, codigo));
        System.out.println("Libro registrado correctamente.");
    }

    public void registrarUsuario() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("ID de Usuario: ");
        String id = sc.nextLine();
        usuarios.add(new Usuario(nombre, id));
        System.out.println("Usuario registrado correctamente.");
    }

    public void mostrarLibrosDisponibles() {
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                libro.mostrarDatos();
            }
        }
    }

    public void mostrarUsuarios() {
        for (Usuario usuario : usuarios) {
            usuario.mostrarDatos();
        }
    }

    public void mostrarHistorialPrestamos() {
        for (Prestamo p : prestamos) {
            p.mostrarPrestamo();
        }
    }

    private Usuario buscarUsuario(String id) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario().equals(id)) {
                return u;
            }
        }
        return null;
    }

    private Libro buscarLibro(String codigo) {
        for (Libro l : libros) {
            if (l.getCodigo().equals(codigo)) {
                return l;
            }
        }
        return null;
    }

    public void prestarLibro() {
        System.out.print("ID Usuario: ");
        String id = sc.nextLine();
        Usuario usuario = buscarUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.print("Código Libro: ");
        String codigo = sc.nextLine();
        Libro libro = buscarLibro(codigo);
        if (libro == null || !libro.isDisponible()) {
            System.out.println("Libro no disponible.");
            return;
        }

        if (usuario.getLibrosPrestados().size() >= 3) {
            System.out.println("El usuario ya tiene 3 libros prestados.");
            return;
        }
        usuario.agregarPrestamo(libro);
        libro.marcarPrestado();
        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(7);
        prestamos.add(new Prestamo(libro, usuario, hoy, limite));
        System.out.println(
            "Préstamo realizado: Usuario: " + usuario.getNombre() + " (" + usuario.getIdUsuario() + ")"
            + " | Libro: " + libro.getCodigo() + " - " + libro.getTitulo()
            + " | Inicio: " + hoy
            + " | Vencimiento: " + limite
        );
    }

    public void devolverLibro() {
        System.out.print("ID Usuario: ");
        String id = sc.nextLine();
        Usuario usuario = buscarUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.print("Código Libro: ");
        String codigo = sc.nextLine();
        Libro libro = buscarLibro(codigo);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        Prestamo prestamo = null;
        for (Prestamo p : prestamos) {
            if (p.getLibro().equals(libro) && p.getUsuario().equals(usuario)) {
                prestamo = p;
                break;
            }
        }

        if (prestamo == null) {
            System.out.println("No se encontró un préstamo activo de este libro por este usuario.");
            return;
        }

        usuario.devolverLibro(libro);
        libro.marcarDisponible();
        prestamos.remove(prestamo);

        LocalDate hoy = LocalDate.now();
        long diasRetraso = Duration.between(prestamo.getFechaLimite().atStartOfDay(), hoy.atStartOfDay()).toDays();
        if (diasRetraso > 0) {
            long multa = diasRetraso * 500;
            System.out.println("¡Libro devuelto con retraso! Días de retraso: " + diasRetraso + " | Multa: $" + multa);
        } else {
            System.out.println("Libro devuelto a tiempo. ¡Gracias!");
        }
    }
}

public class Biblioteca {
    public static void main(String[] args) {
        SistemaBiblioteca biblioteca = new SistemaBiblioteca();
        Scanner sc = new Scanner(System.in);
        int opcion;

        biblioteca.cargarDatosPrueba();
        System.out.println("Se cargaron 3 libros y 3 usuarios de prueba.");

        do {
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios");
            System.out.println("7. Mostrar historial de préstamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    biblioteca.registrarLibro();
                    break;
                case 2:
                    biblioteca.registrarUsuario();
                    break;
                case 3:
                    biblioteca.prestarLibro();
                    break;
                case 4:
                    biblioteca.devolverLibro();
                    break;
                case 5:
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                case 6:
                    biblioteca.mostrarUsuarios();
                    break;
                case 7:
                    biblioteca.mostrarHistorialPrestamos();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}

