package cl.usach;

import java.util.Scanner;
public class Main_MartinArayaGaete_217813697 {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opt;
        do {
            System.out.println("=== CAPITALIA ===");
            System.out.println("1. Nueva Partida");
            System.out.println("2. Mostrar Estado");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opt = Integer.parseInt(sc.nextLine());
            switch (opt) {
                case 1:
                    System.out.println("-> Iniciando nueva partida...");
                    // Game game = new Game_MartinArayaGaete_217813697();
                    break;
                case 2:
                    System.out.println("-> Estado del juego:");
                    // System.out.println(game);
                    break;
                case 3:
                    System.out.println("¡Gracias por jugar CAPITALIA!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opt != 3);
    }
}
