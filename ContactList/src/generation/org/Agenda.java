package generation.org;

import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contacto> contactos;
    private int maxContactos;

    // Constructor con tamaño por defecto
    public Agenda() {
        this.maxContactos = 10;
        this.contactos = new ArrayList<>();
    }

    // Constructor con tamaño personalizado
    public Agenda(int maxContactos) {
        this.maxContactos = maxContactos;
        this.contactos = new ArrayList<>();
    }

    public void añadirContacto(Contacto c) {
        if (contactos.size() >= maxContactos) {
            System.out.println("No se puede añadir: la agenda está llena.");
            return;
        }
        if (contactos.contains(c)) {
            System.out.println("No se puede añadir: el contacto ya existe.");
            return;
        }
        contactos.add(c);
        System.out.println("Contacto añadido con éxito.");
    }

    public boolean existeContacto(Contacto c) {
        return contactos.contains(c);
    }

    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        System.out.println("Lista de contactos:");
        for (Contacto c : contactos) {
            System.out.println(c);
        }
    }

    public void buscarContacto(String nombre) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("📲 Teléfono de " + nombre + ": " + c.getTelefono());
                return;
            }
        }
        System.out.println("Contacto no encontrado.");
    }

    public void eliminarContacto(String nombre) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                contactos.remove(c);
                System.out.println("Contacto eliminado con éxito.");
                return;
            }
        }
        System.out.println("No existe un contacto con ese nombre.");
    }

    public void modificarTelefono(String nombre, String nuevoTelefono) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                c.setTelefono(nuevoTelefono);
                System.out.println("Teléfono modificado con éxito.");
                return;
            }
        }
        System.out.println("No existe un contacto con ese nombre.");
    }

    public void espaciosLibres() {
        int libres = maxContactos - contactos.size();
        System.out.println("Espacios libres en la agenda: " + libres);
    }
}
