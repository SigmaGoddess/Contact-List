package generation.org;

import java.util.Scanner;

public class MenuTest {
    public static void main(String[] args) {
        Menu menu = new Menu();         // Muestra el menú y valida opciones
        Agenda agenda = new Agenda();   // Maneja los contactos
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            menu.desplegarMenu();                          // Mostrar opciones
            opcion = menu.validarEntrada(menu.registrarOpcion()); // Capturar y validar

            sc.nextLine(); // Limpieza de buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();
                    agenda.añadirContacto(new Contacto(nombre, telefono));
                    break;

                case 2:
                    System.out.print("Nombre a verificar: ");
                    String nombreVerificar = sc.nextLine();
                    boolean existe = agenda.existeContacto(new Contacto(nombreVerificar, ""));
                    System.out.println(existe ? "El contacto existe." : "El contacto NO existe.");
                    break;

                case 3:
                    agenda.listarContactos();
                    break;

                case 4:
                    System.out.print("Nombre a buscar: ");
                    String nombreBuscar = sc.nextLine();
                    agenda.buscarContacto(nombreBuscar);
                    break;

                case 5:
                    System.out.print("Nombre a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    agenda.eliminarContacto(nombreEliminar);
                    break;

                case 6:
                    System.out.print("Nombre a modificar: ");
                    String nombreModificar = sc.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String nuevoTel = sc.nextLine();
                    agenda.modificarTelefono(nombreModificar, nuevoTel);
                    break;

                case 7:
                    System.out.println("Espacios libres: " + agenda.espaciosLibres());
                    break;
            }

        } while (true); // Puedes cambiar esto por una condición de salida si lo permiten

        // sc.close(); ← solo si agregas una opción para salir
    }
}