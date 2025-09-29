package generation.org;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un contacto en la agenda.
 * Incluye una lista estática con contactos precargados para pruebas.
 */
public class Contacto {

    private String nombre;
    private String telefono;

    // Lista pública de contactos precargados
    public static List<Contacto> listaContactos = new ArrayList<>();

    // Bloque estático para inicializar la lista
    static {
        listaContactos.add(new Contacto("Alice", "111"));
        listaContactos.add(new Contacto("Bob", "222"));
        listaContactos.add(new Contacto("Charlie", "555"));
        listaContactos.add(new Contacto("David", "666"));
        listaContactos.add(new Contacto("Eva", "777"));
        listaContactos.add(new Contacto("Felix", "888"));
        listaContactos.add(new Contacto("Hector", "101"));
        listaContactos.add(new Contacto("Inés", "123"));
        listaContactos.add(new Contacto("Juan", "404"));
    }

    public Contacto(String nombre, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre.trim();
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String nuevoTelefono) {
        this.telefono = nuevoTelefono;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contacto) {
            Contacto otro = (Contacto) obj;
            return this.nombre.equalsIgnoreCase(otro.nombre);
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre + " - " + telefono;
    }
}