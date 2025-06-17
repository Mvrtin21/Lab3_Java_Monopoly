package cl.usach;

import java.util.Scanner;

/**
 * Clase principal que inicia el juego CAPITALIA y maneja el menú de interacción por consola.
 */
public class Main_MartinArayaGaete_217813697 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Juego_MartinArayaGaete_217813697 juego = null;
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n===== CAPITALIA - Menú Principal =====");
            System.out.println("1. Crear nueva partida");
            System.out.println("2. Mostrar estado actual del juego");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            int opcion = -1;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debes ingresar un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    // Inicializa el juego (carga tablero, propiedades y cartas)
                    juego = new Juego_MartinArayaGaete_217813697();
                    System.out.println("Partida creada exitosamente. ¡A jugar!");
                    break;

                case 2:
                    if (juego != null) {
                        // Muestra el estado completo del juego (jugadores, tablero, dinero)
                        System.out.println(juego);
                    } else {
                        System.out.println("No existe una partida activa. Primero crea una nueva partida.");
                    }
                    break;

                case 3:
                    System.out.println("Gracias por jugar CAPITALIA. ¡Hasta la próxima!");
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }
        }

        sc.close();
    }
}
