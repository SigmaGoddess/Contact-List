package generation.org;

import java.util.Arrays;



public class Agenda {

    private static final int tamañoPorDefecto = 10;
    private Contacto[] contactos;
    private int indiceContactos; // Rastrea cuántos contactos hay actualmente

    /**
     * Constructor por defecto. Crea una agenda con el tamaño por defecto (10).
     */
    public Agenda() {
        this.contactos = new Contacto[tamañoPorDefecto];
        this.indiceContactos = 0;
    }

    /**
     * Verifica si la agenda está llena.
     * @return true si la agenda está llena, false en caso contrario.
     */
    public boolean estaLlena() {
        return this.indiceContactos == this.contactos.length;
    }

    /**
     * Indica cuántos contactos más se pueden ingresar en la agenda.
     * @return El número de espacios libres.
     */
    public int espaciosLibres() {
        return this.contactos.length - this.indiceContactos;
    }

    /**
     * Indica si el contacto pasado existe o no (basado en el nombre).
     * @param c El contacto a buscar.
     * @return true si el contacto existe, false en caso contrario.
     */
    public boolean existeContacto(Contacto c) {
        if (c == null) {
            return false;
        }
        // Recorre solo los contactos que han sido añadidos (hasta indiceContactos)
        for (int i = 0; i < this.indiceContactos; i++) {
            if (this.contactos[i].equals(c)) {
                return true;
            }
        }
        return false;
        // Alternativamente, se puede usar Arrays.asList(contactos).contains(c) si el array está lleno
    }

    /**
     * Añade un contacto a la agenda.
     * @param c El contacto a añadir.
     */
    public void añadirContacto(Contacto c) {
        if (c == null) {
            System.out.println("ERROR: El contacto no puede ser nulo.");
            return;
        }

        if (c.getNombre() == null || c.getNombre().trim().isEmpty()) {
            System.out.println("ERROR: El nombre y/o apellido del contacto no puede estar vacío.");
            return;
        }

        if (estaLlena()) {
            System.out.println("ERROR: La agenda está llena. No se puede añadir a " + c.getNombre() + ".");
            return;
        }

        if (existeContacto(c)) {
            System.out.println("ERROR: Ya existe un contacto con el nombre " + c.getNombre() + ".");
            return;
        }

        // Si pasa todas las validaciones, se añade el contacto al siguiente índice libre.
        this.contactos[this.indiceContactos] = c;
        this.indiceContactos++;
        System.out.println("ÉXITO: Contacto " + c.getNombre() + " se ha añadido correctamente.");
    }

    /**
     * Lista todos los contactos de la agenda.
     */
    public void listarContactos() {
        if (this.indiceContactos == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }

        System.out.println("\n--- Lista de Contactos (" + this.indiceContactos + " / " + this.contactos.length + ") ---");
        for (int i = 0; i < this.indiceContactos; i++) {
            Contacto c = this.contactos[i];
            System.out.println((i + 1) + ". Nombre: " + c.getNombre() + ", Teléfono: " + c.getTelefono());
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Busca un contacto por su nombre y muestra su teléfono.
     * @param nombre El nombre del contacto a buscar.
     * @return El contacto encontrado o null si no existe.
     */
    public Contacto buscarContacto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("ERROR: El nombre de búsqueda no puede ser vacío.");
            return null;
        }

        // Creamos un contacto temporal para aprovechar el método equals por nombre
        Contacto contactoBusqueda = new Contacto(nombre, "");

        for (int i = 0; i < this.indiceContactos; i++) {
            if (this.contactos[i].equals(contactoBusqueda)) {
                Contacto c = this.contactos[i];
                System.out.println("Contacto encontrado: Nombre: " + c.getNombre() + ", Teléfono: " + c.getTelefono());
                return c;
            }
        }

        System.out.println("Contacto no encontrado con el nombre: " + nombre);
        return null;
    }

    /**
     * Elimina el contacto correspondiente al nombre.
     * @param nombre El nombre del contacto a eliminar.
     */
    public void eliminarContacto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("ERROR: El nombre a eliminar no puede ser vacío.");
            return;
        }

        Contacto contactoBusqueda = new Contacto(nombre, "");

        for (int i = 0; i < this.indiceContactos; i++) {
            if (this.contactos[i].equals(contactoBusqueda)) {
                // Contacto encontrado. Para eliminar en un array, debemos mover los elementos
                // siguientes una posición hacia adelante (sobrescribir el eliminado).

                // 1. Mover los elementos (Shift Izquierda)
                for (int j = i; j < this.indiceContactos - 1; j++) {
                    this.contactos[j] = this.contactos[j + 1];
                }

                // 2. Limpiar la última posición (opcional, pero buena práctica)
                this.contactos[this.indiceContactos - 1] = null;

                // 3. Decrementar el contador de contactos
                this.indiceContactos--;

                System.out.println("ÉXITO: Contacto con nombre '" + nombre + "' eliminado correctamente.");
                return;
            }
        }

        System.out.println("ERROR: No se encontró ningún contacto con el nombre '" + nombre + "' para eliminar.");
    }

    /**
     * Permite modificar el teléfono de un contacto existente.
     * @param nombre El nombre del contacto a modificar.
     * @param nuevoTelefono El nuevo número de teléfono.
     */
    public void modificarTelefono(String nombre, String nuevoTelefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("ERROR: El nombre del contacto no puede ser vacío.");
            return;
        }

        Contacto contactoBusqueda = new Contacto(nombre, "");

        for (int i = 0; i < this.indiceContactos; i++) {
            if (this.contactos[i].equals(contactoBusqueda)) {
                String telefonoAnterior = this.contactos[i].getTelefono();
                this.contactos[i].setTelefono(nuevoTelefono);
                System.out.println("ÉXITO: Teléfono de " + nombre + " modificado de " + telefonoAnterior + " a " + nuevoTelefono + ".");
                return;
            }
        }

        System.out.println("ERROR: No se encontró ningún contacto con el nombre '" + nombre + "' para modificar.");
    }

    // --- Métodos de apoyo para Pruebas (Opcional pero muy útil) ---
    public Contacto[] getContactos() {
        return Arrays.copyOf(this.contactos, this.indiceContactos); // Devuelve solo los contactos no nulos
    }

    public int getTamañoMaximo() {
        return this.contactos.length;
    }
}