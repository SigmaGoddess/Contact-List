package generation.org;

import java.util.Arrays;

public class Agenda {
    private static final int tamañoPorDefecto = 10;
    private Contacto[] contactos;
    private int indiceContactos;

    public Agenda() {
        this.contactos = new Contacto[tamañoPorDefecto];
        this.indiceContactos = 0;

        for (Contacto c : Contacto.listaContactos) {
            añadirContacto(c);
        }
    }

    public boolean estaLlena() {
        return this.indiceContactos == this.contactos.length;
    }

    public int espaciosLibres() {
        return this.contactos.length - this.indiceContactos;
    }

    public boolean existeContacto(Contacto c) {
        if (c == null) return false;
        for (int i = 0; i < this.indiceContactos; i++) {
            if (this.contactos[i].equals(c)) return true;
        }
        return false;
    }

    public void añadirContacto(Contacto c) {
        if (c == null || c.getNombre() == null || c.getNombre().trim().isEmpty()) {
            System.out.println("ERROR: El nombre no puede estar vacío.");
            return;
        }
        if (estaLlena()) {
            System.out.println("ERROR: La agenda está llena.");
            return;
        }
        if (existeContacto(c)) {
            System.out.println("ERROR: Ya existe un contacto con ese nombre.");
            return;
        }
        this.contactos[this.indiceContactos++] = c;
        System.out.println("ÉXITO: Contacto añadido correctamente.");
    }

    public void listarContactos() {
        if (this.indiceContactos == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }
        System.out.println("\n--- Lista de Contactos ---");
        for (int i = 0; i < this.indiceContactos; i++) {
            System.out.println((i + 1) + ". " + contactos[i]);
        }
    }

    public Contacto buscarContacto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("ERROR: El nombre no puede estar vacío.");
            return null;
        }
        Contacto temp = new Contacto(nombre, "");
        for (int i = 0; i < this.indiceContactos; i++) {
            if (this.contactos[i].equals(temp)) {
                System.out.println("Contacto encontrado: " + contactos[i]);
                return contactos[i];
            }
        }
        System.out.println("No se encontró el contacto.");
        return null;
    }

    public void eliminarContacto(String nombre) {
        Contacto temp = new Contacto(nombre, "");
        for (int i = 0; i < this.indiceContactos; i++) {
            if (this.contactos[i].equals(temp)) {
                for (int j = i; j < this.indiceContactos - 1; j++) {
                    this.contactos[j] = this.contactos[j + 1];
                }
                this.contactos[--this.indiceContactos] = null;
                System.out.println("Contacto eliminado.");
                return;
            }
        }
        System.out.println("No se encontró el contacto para eliminar.");
    }

    public void modificarTelefono(String nombre, String nuevoTelefono) {
        Contacto temp = new Contacto(nombre, "");
        for (int i = 0; i < this.indiceContactos; i++) {
            if (this.contactos[i].equals(temp)) {
                contactos[i].setTelefono(nuevoTelefono);
                System.out.println("Teléfono modificado.");
                return;
            }
        }
        System.out.println("No se encontró el contacto para modificar.");
    }

    public Contacto[] getContactos() {
        return Arrays.copyOf(this.contactos, this.indiceContactos);
    }

    public int getTamañoMaximo() {
        return this.contactos.length;
    }
}