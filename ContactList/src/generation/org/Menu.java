package generation.org;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

	void desplegarMenu() {
		System.out.println("Bienvenido a tu Agenda de Contactos");
		System.out.println("Qué deseas hacer? Ingrese el número de opción");
		System.out.println("1.- Añadir Contacto");
		System.out.println("2.- Saber si un contacto existe");
		System.out.println("3.- Mostrar Contactos");
		System.out.println("4.- Buscar Contacto");
		System.out.println("5.- Eliminar Contacto");
		System.out.println("6.- Modificar Télefono");
		System.out.println("7.- Saber cuántos espacios me quedan");

	}

	int registrarOpcion() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	int validarEntrada(int opcion) {
		while (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4 && opcion != 5 && opcion != 6 && opcion != 7) {
			System.out.println("Error. ¡Opción inválida!");
			desplegarMenu();
			opcion = registrarOpcion();
		}
		return opcion;
	}
}