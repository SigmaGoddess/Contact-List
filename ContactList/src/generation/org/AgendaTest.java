package generation.org;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// *Clase Contacto simplificada para fines de la prueba*
// *En un proyecto real, estaría en su propio archivo*
class Contacto {
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        // La regla dice que un contacto es igual a otro cuando sus nombres son iguales.
        return nombre.equalsIgnoreCase(contacto.nombre);
    }
    // No necesitamos hashCode() para estas pruebas, pero es buena práctica en producción.
}

public class AgendaTest {

    private Agenda agenda;

    // Se ejecuta antes de cada prueba para tener una Agenda limpia
    @BeforeEach
    void setUp() {
        // Asumiendo que el constructor por defecto usa el tamaño 10
        agenda = new Agenda();
    }

    // --- Prueba: añadirContacto() ---
    
    @Test
    @DisplayName("Debe añadir un contacto exitosamente")
    void testAñadirContacto_Exito() {
        Contacto c1 = new Contacto("Alice", "111");
        agenda.añadirContacto(c1);
        // Verificamos que se haya añadido y no esté lleno
        assertTrue(agenda.existeContacto(c1), "El contacto Alice debería existir.");
        assertEquals(1, agenda.getContactos().length, "Debe haber 1 contacto.");
        assertFalse(agenda.estaLlena(), "La agenda no debe estar llena.");
    }

    @Test
    @DisplayName("No debe añadir un contacto si la agenda está llena")
    void testAñadirContacto_AgendaLlena() {
        // Llenar la agenda (usando el tamaño por defecto de 10)
        for (int i = 0; i < agenda.getTamañoMaximo(); i++) {
            agenda.añadirContacto(new Contacto("Contacto" + i, "900" + i));
        }

        assertTrue(agenda.estaLlena(), "La agenda debe estar llena.");

        // Intentar añadir uno más
        Contacto cExtra = new Contacto("Extra", "999");
        agenda.añadirContacto(cExtra);

        // Verificamos que el contacto extra no se haya añadido
        assertFalse(agenda.existeContacto(cExtra), "El contacto Extra no debería haberse añadido.");
        assertEquals(agenda.getTamañoMaximo(), agenda.getContactos().length, "El número de contactos debe ser el máximo.");
    }

    @Test
    @DisplayName("No debe añadir contactos duplicados por nombre")
    void testAñadirContacto_Duplicado() {
        Contacto c1 = new Contacto("Bob", "222");
        Contacto c2_duplicado = new Contacto("bob", "333"); // Mismo nombre, distinto teléfono

        agenda.añadirContacto(c1);
        agenda.añadirContacto(c2_duplicado); // Debería fallar

        // Verificamos que solo esté el primero (o uno) y que no haya 2
        assertEquals(1, agenda.getContactos().length, "Solo se debe haber añadido un contacto.");
        // Verificamos que el teléfono sea el del primero
        assertEquals("222", agenda.buscarContacto("Bob").getTelefono(), "El teléfono debe ser el del primer contacto añadido.");
    }

    @Test
    @DisplayName("No debe añadir contactos con nombre vacío")
    void testAñadirContacto_NombreVacio() {
        Contacto cVacio = new Contacto(" ", "444"); // Nombre con solo espacio
        agenda.añadirContacto(cVacio);
        assertEquals(0, agenda.getContactos().length, "No se debe añadir un contacto con nombre vacío.");
    }

    // --- Prueba: existeContacto() ---

    @Test
    @DisplayName("Debe indicar que un contacto existe (ignora mayúsculas/minúsculas)")
    void testExisteContacto_Exito() {
        agenda.añadirContacto(new Contacto("Charlie", "555"));
        Contacto cBusqueda = new Contacto("cHArlie", "cualquiertelefono"); // Misma lógica en equals
        assertTrue(agenda.existeContacto(cBusqueda), "Charlie debería existir.");
    }
    
    // --- Prueba: eliminarContacto() ---

    @Test
    @DisplayName("Debe eliminar un contacto exitosamente")
    void testEliminarContacto_Exito() {
        Contacto c1 = new Contacto("David", "666");
        Contacto c2 = new Contacto("Eva", "777");
        agenda.añadirContacto(c1);
        agenda.añadirContacto(c2);

        agenda.eliminarContacto("David");

        assertFalse(agenda.existeContacto(c1), "David no debería existir después de la eliminación.");
        assertTrue(agenda.existeContacto(c2), "Eva debería seguir existiendo.");
        assertEquals(1, agenda.getContactos().length, "Debe quedar 1 contacto.");
    }

    @Test
    @DisplayName("Debe fallar al intentar eliminar un contacto que no existe")
    void testEliminarContacto_NoExiste() {
        agenda.añadirContacto(new Contacto("Felix", "888"));
        
        agenda.eliminarContacto("Gabo"); // No existe

        assertEquals(1, agenda.getContactos().length, "El número de contactos no debe cambiar.");
    }

    // --- Prueba: modificarTelefono() ---
    
    @Test
    @DisplayName("Debe modificar el teléfono de un contacto existente")
    void testModificarTelefono_Exito() {
        String nombre = "Hector";
        agenda.añadirContacto(new Contacto(nombre, "101"));
        
        agenda.modificarTelefono(nombre, "999");
        
        // Buscamos el contacto y verificamos el nuevo teléfono
        Contacto c = agenda.buscarContacto(nombre);
        assertNotNull(c);
        assertEquals("999", c.getTelefono(), "El teléfono debe ser 999.");
    }

    @Test
    @DisplayName("Debe fallar al intentar modificar el teléfono de un contacto que no existe")
    void testModificarTelefono_NoExiste() {
        agenda.modificarTelefono("Inés", "123");
        
        assertNull(agenda.buscarContacto("Inés"), "El contacto Inés no debería existir.");
        assertEquals(0, agenda.getContactos().length, "La agenda debe seguir vacía.");
    }
    
    // --- Prueba: espaciosLibres() ---
    
    @Test
    @DisplayName("Debe indicar correctamente los espacios libres")
    void testEspaciosLibres() {
        assertEquals(10, agenda.espaciosLibres(), "Al inicio, debe haber 10 espacios libres.");
        agenda.añadirContacto(new Contacto("Juan", "404"));
        assertEquals(9, agenda.espaciosLibres(), "Después de 1, debe haber 9 espacios libres.");
    }
}