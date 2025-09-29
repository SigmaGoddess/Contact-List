📒 Agenda Telefónica en Java

Este proyecto implementa una agenda telefónica en Java que permite gestionar contactos utilizando un ArrayList.
Cada contacto está definido por un nombre y un teléfono.


🚀 Características

		Añadir un contacto nuevo.
		
		Verificar si un contacto existe.
		
		Listar todos los contactos de la agenda.
		
		Buscar un contacto por nombre.
		
		Eliminar un contacto existente.
		
		Modificar el teléfono de un contacto.
		
		Mostrar cuántos espacios libres quedan en la agenda.

La agenda tiene un tamaño máximo de contactos (10 por defecto, pero puede configurarse).

No se permite:

		Nombres vacíos.
		
		Contactos duplicados.
		
		Exceder la capacidad máxima.

🛠️ Tecnologías utilizadas

Java 8+

Colección ArrayList (para almacenar los contactos de forma dinámica)

Scanner (para la entrada de datos por consola)

📂 Estructura del proyecto
src/
 └── generation/org/
       ├── Menu.java        # Clase que despliega y valida el menú de opciones
       ├── Contacto.java    # Clase que define un contacto (nombre, teléfono)
       ├── Agenda.java      # Clase que gestiona los contactos con ArrayList
       └── Main.java        # Clase principal que conecta el menú con la agenda

▶️ Ejecución

Clona el repositorio o descarga el código fuente.

git clone https://github.com/tu-usuario/agenda-java.git
cd agenda-java


Compila los archivos .java:

javac src/generation/org/*.java


Ejecuta el programa:

java src/generation/org/Main

📋 Ejemplo de uso
Bienvenido al directorio de contactos
		¿Qué deseas hacer? Ingrese el número de opción
		1.- Añadir Contacto
		2.- Saber si un contacto existe
		3.- Mostrar Contactos
		4.- Buscar Contacto
		5.- Eliminar Contacto
		6.- Modificar Télefono
		7.- Saber cuántos espacios me quedan

👥 Integrantes del equipo
●  Mariana Islas
● Gerardo Alberto López García
● Nicteha Fragoso Trigueros
● Erik Cornelio Hernández
● Sheila Miranda Lagunas

