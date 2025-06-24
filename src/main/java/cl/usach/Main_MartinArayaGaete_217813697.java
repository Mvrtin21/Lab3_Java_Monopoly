package cl.usach;

import java.util.Scanner;

public class Main_MartinArayaGaete_217813697 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Juego_MartinArayaGaete_217813697 juego = null;
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n### CAPITALIA - Menú Principal ###");
            System.out.println("1. Crear nueva partida");
            System.out.println("2. Visualizar estado actual del juego");
            System.out.println("3. Jugar turno");
            System.out.println("4. Construir casa");
            System.out.println("5. Construir hotel");
            System.out.println("6. Hipotecar propiedad");
            System.out.println("7. Deshipotecar propiedad");
            System.out.println("8. Salir");
            System.out.print("Seleccione opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            switch (opcion) {
                case 1:
                    // 1. Crear partida
                    System.out.println("\n--- Creando nueva partida ---");
                    juego = new Juego_MartinArayaGaete_217813697();

                    System.out.print("¿Cuántos jugadores? (mínimo 2): ");
                    int nJug = Integer.parseInt(sc.nextLine());
                    for (int i = 1; i <= nJug; i++) {
                        System.out.print("Nombre jugador " + i + ": ");
                        String nombre = sc.nextLine();
                        juego.agregarJugador(nombre, i);
                    }

                    System.out.print("¿Cuántos dados por turno? (1-4): ");
                    int dados = Integer.parseInt(sc.nextLine());
                    juego.setNumeroDados(dados);

                    System.out.println("Partida creada. ¡A jugar!");
                    break;

                case 2:
                    // 2. Mostrar estado
                    if (juego == null) {
                        System.out.println("Primero crea una partida.");
                    } else {
                        System.out.println(juego);
                    }
                    break;

                case 3:
                    // 3. Jugar turno completo
                    if (juego == null) {
                        System.out.println("Primero crea una partida.");
                    } else {
                        juego.jugarTurno(sc);
                    }
                    break;

                case 4:
                    // 4. Construir casa
                    if (juego == null) {
                        System.out.println("Primero crea una partida.");
                    } else {
                        System.out.print("ID jugador: ");
                        int idJc = Integer.parseInt(sc.nextLine());
                        System.out.print("ID propiedad: ");
                        int idPc = Integer.parseInt(sc.nextLine());

                        Jugador_MartinArayaGaete_217813697 jugC = null;
                        for (Jugador_MartinArayaGaete_217813697 j : juego.getJugadores()) {
                            if (j.getId() == idJc) {
                                jugC = j;
                                break;
                            }
                        }

                        if (jugC != null) {
                            Propiedad_MartinArayaGaete_217813697 propC =
                                    juego.getTablero().getPropiedades().get(idPc);
                            juego.construirCasa(propC);
                        } else {
                            System.out.println("Jugador no encontrado.");
                        }
                    }
                    break;

                case 5:
                    // 5. Construir hotel
                    if (juego == null) {
                        System.out.println("Primero crea una partida.");
                    } else {
                        System.out.print("ID jugador: ");
                        int idJh = Integer.parseInt(sc.nextLine());
                        System.out.print("ID propiedad: ");
                        int idPh = Integer.parseInt(sc.nextLine());

                        // No necesitas buscar jugador porque hotel no depende de él en tu lógica
                        Propiedad_MartinArayaGaete_217813697 propH =
                                juego.getTablero().getPropiedades().get(idPh);
                        juego.construirHotel(propH);
                    }
                    break;

                case 6:
                    // 6. Hipotecar propiedad
                    if (juego == null) {
                        System.out.println("Primero crea una partida.");
                    } else {
                        System.out.print("ID jugador: ");
                        int idJhip = Integer.parseInt(sc.nextLine());
                        System.out.print("ID propiedad: ");
                        int idPhip = Integer.parseInt(sc.nextLine());

                        Jugador_MartinArayaGaete_217813697 jugHip = null;
                        for (Jugador_MartinArayaGaete_217813697 j : juego.getJugadores()) {
                            if (j.getId() == idJhip) {
                                jugHip = j;
                                break;
                            }
                        }

                        if (jugHip != null) {
                            Propiedad_MartinArayaGaete_217813697 propHip =
                                    juego.getTablero().getPropiedades().get(idPhip);
                            juego.hipotecarPropiedad(jugHip, propHip);
                        } else {
                            System.out.println("Jugador no encontrado.");
                        }
                    }
                    break;

                case 7:
                    // 7. Deshipotecar propiedad
                    if (juego == null) {
                        System.out.println("Primero crea una partida.");
                    } else {
                        System.out.print("ID jugador: ");
                        int idJdes = Integer.parseInt(sc.nextLine());
                        System.out.print("ID propiedad: ");
                        int idPdes = Integer.parseInt(sc.nextLine());

                        Jugador_MartinArayaGaete_217813697 jugDes = null;
                        for (Jugador_MartinArayaGaete_217813697 j : juego.getJugadores()) {
                            if (j.getId() == idJdes) {
                                jugDes = j;
                                break;
                            }
                        }

                        if (jugDes != null) {
                            Propiedad_MartinArayaGaete_217813697 propDes =
                                    juego.getTablero().getPropiedades().get(idPdes);
                            juego.deshipotecarPropiedad(jugDes, propDes);
                        } else {
                            System.out.println("Jugador no encontrado.");
                        }
                    }
                    break;

                case 8:
                    // 8. Salir
                    System.out.println("¡Gracias por jugar CAPITALIA!");
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}
