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
            System.out.println("\n### CAPITALIA - Menú Principal ###");
            System.out.println("Bienvenido al juego CAPITALIA");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Jugar");
            System.out.println("2. Visualizar estado actual del tablero");
            System.out.println("3. Salir del juego");
            System.out.print("Ingrese su opción: ");

            int opcion = -1;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debes ingresar un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n### Crear Nuevo Juego ###");

                    juego = new Juego_MartinArayaGaete_217813697();

                    System.out.print("Ingrese cantidad de jugadores (mínimo 2): ");
                    int cantidad = Integer.parseInt(sc.nextLine());
                    for (int i = 1; i <= cantidad; i++) {
                        System.out.println("\n--- Configuración Jugador " + i + " ---");
                        System.out.print("Ingrese nombre del jugador " + i + ": ");
                        String nombre = sc.nextLine();
                        Jugador_MartinArayaGaete_217813697 jugador = new Jugador_MartinArayaGaete_217813697(i, nombre, 1500);
                        juego.getJugadores().add(jugador);
                    }

                    System.out.println("\n--- Configuración del Juego ---");
                    System.out.print("Ingrese cantidad de dados por jugador (entre 1 y 4): ");
                    int dados = Integer.parseInt(sc.nextLine());
                    juego.setNumeroDados(dados);

                    System.out.println("\n¡Juego creado exitosamente!");
                    System.out.println("Comienza el juego…\n");
                    break;

                case 2:
                    if (juego != null) {
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