package generation.org;

public class MenuTest {
    public static void main(String[] args) {

        Menu menu = new Menu();
        int opcion;

        do {
            menu.desplegarMenu();
            opcion = menu.registrarOpcion();
            opcion = menu.validarEntrada(opcion);

            switch (opcion) {
                case 1:
                    // menu.añadirContacto();
                    break;
                case 2:
                    // menu.existeContacto();
                    break;
                case 3:
                    // menu.listarContactos();
                    break;
                case 4:
                    // menu.buscarContacto();
                    break;
                case 5:
                    // menu.eliminarContacto();
                    break;
                case 6:
                    // menu.modificarTelefono();
                    break;
                case 7:
                    // menu.espaciosLibres();
                    break;
            }

        } while (opcion != 3);

    }

}