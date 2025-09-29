package generation.org;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Gestiona un conjunto de contactos con un tamaño máximo.
 * Utiliza un Map para asegurar la unicidad por nombre y optimizar la búsqueda.
 */
public class Agenda {
    private final Map<String, Contacto> contactos;
    private final int capacidadMaxima;
    private static final int CAPACIDAD_POR_DEFECTO = 10;

    // Constructor con tamaño personalizado
    public Agenda(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva.");
        }
        this.capacidadMaxima = capacidad;
        // Inicializa el Map
        this.contactos = new HashMap<>(capacidad);
    }

    // Constructor por defecto (tamaño 10)
    public Agenda() {
        this(CAPACIDAD_POR_DEFECTO);
    }

    // --- Métodos de la Agenda ---

    /**
     * Añade un contacto a la agenda.
     */
    public void anadirContacto(Contacto c) {
        // 1. Verificar si la agenda está llena
        if (contactos.size() >= capacidadMaxima) {
            System.out.println("❌ ERROR: La agenda está llena. No se puede añadir a " + c.getNombre() + ".");
            return;
        }

        // 2. Verificar si el contacto ya existe (uso la clave del Map)
        if (existeContacto(c)) {
            System.out.println("❌ ERROR: El contacto " + c.getNombre() + " ya existe en la agenda.");
            return;
        }

        // 3. Añadir el contacto
        // Usamos el nombre como clave para asegurar unicidad y rapidez en la búsqueda
        contactos.put(c.getNombre().toLowerCase(), c);
        System.out.println("✅ ÉXITO: Contacto " + c.getNombre() + " añadido correctamente.");
    }

    /**
     * Indica si el contacto (basado en el nombre) existe o no.
     */
    public boolean existeContacto(Contacto c) {
        // El Map nos permite una búsqueda O(1) basada en la clave (nombre)
        return contactos.containsKey(c.getNombre().toLowerCase());
    }
    
    /**
     * Indica si un contacto con un nombre específico existe.
     */
    private boolean existeContacto(String nombre) {
        if (nombre == null) return false;
        return contactos.containsKey(nombre.trim().toLowerCase());
    }


    /**
     * Lista todos los contactos de la agenda.
     */
    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("ℹ️ La agenda está vacía.");
            return;
        }
        System.out.println("\n--- LISTADO DE CONTACTOS (" + contactos.size() + "/" + capacidadMaxima + ") ---");
        // Iteramos sobre los VALUES del Map
        for (Contacto c : contactos.values()) {
            System.out.println(c);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Busca un contacto por su nombre y muestra su teléfono.
     */
    public void buscaContacto(String nombre) {
        String nombreNormalizado = nombre.trim().toLowerCase();
        Contacto c = contactos.get(nombreNormalizado);

        if (c != null) {
            System.out.println("📞 Contacto encontrado: " + c.getNombre() + ", Teléfono: " + c.getTelefono());
        } else {
            System.out.println("❌ Contacto no encontrado: No existe ningún contacto llamado " + nombre + ".");
        }
    }

    /**
     * Elimina el contacto correspondiente al nombre.
     */
    public void eliminarContacto(String nombre) {
        String nombreNormalizado = nombre.trim().toLowerCase();
        // Remove devuelve el valor (Contacto) si existía, o null si no
        Contacto cEliminado = contactos.remove(nombreNormalizado);

        if (cEliminado != null) {
            System.out.println("🗑️ ÉXITO: Contacto " + cEliminado.getNombre() + " eliminado exitosamente.");
        } else {
            System.out.println("❌ ERROR: No se puede eliminar. El contacto " + nombre + " no existe.");
        }
    }

    /**
     * Permite modificar el teléfono de un contacto existente.
     * (El apellido no es necesario, solo el nombre es clave).
     */
    public void modificarTelefono(String nombre, String nuevoTelefono) {
        String nombreNormalizado = nombre.trim().toLowerCase();
        Contacto c = contactos.get(nombreNormalizado);

        if (c != null) {
            c.setTelefono(nuevoTelefono);
            System.out.println("✍️ ÉXITO: Teléfono de " + c.getNombre() + " modificado a " + nuevoTelefono + ".");
        } else {
            System.out.println("❌ ERROR: No se puede modificar. El contacto " + nombre + " no existe.");
        }
    }

    /**
     * Indica cuántos contactos más se pueden ingresar.
     */
    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    public void mostrarEspaciosLibres() {
        int libres = espaciosLibres();
        System.out.println("ℹ️ Espacios libres en la agenda: " + libres + " de " + capacidadMaxima + ".");
    }
}