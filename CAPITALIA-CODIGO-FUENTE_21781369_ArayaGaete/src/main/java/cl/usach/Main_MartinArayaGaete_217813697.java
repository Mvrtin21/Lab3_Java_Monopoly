package cl.usach;

import java.util.List;
import java.util.Scanner;

public class Main_MartinArayaGaete_217813697 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Juego_MartinArayaGaete_217813697 juego = null;
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n### CAPITALIA - Menú Principal ###");
            if (juego == null) {
                System.out.println("1. Crear nueva partida");
                System.out.println("8. Salir");
                System.out.println("9. Importar partida desde archivo");
            } else {
                Jugador_MartinArayaGaete_217813697 jugador_actual = juego.getJugadorActual();
                System.out.println("Turno de " + jugador_actual.getNombre() + ":");
                System.out.println("1. Crear nueva partida");
                System.out.println("2. Visualizar estado actual del juego");
                System.out.println("3. Jugar turno");
                System.out.println("4. Construir casa");
                System.out.println("5. Construir hotel");
                System.out.println("6. Hipotecar propiedad");
                System.out.println("7. Deshipotecar propiedad");
                System.out.println("8. Salir");
                System.out.println("9. Exportar partida a archivo");
                // Si el jugador actual está en la cárcel, añadimos opciones extra:
                if (jugador_actual.isEstaEnCarcel()) {
                    System.out.println("10. Pagar multa de $500");                   // o el monto que hayas definido
                    System.out.println("11. Usar tarjeta comodín \"Salir de la carcel\"");
                }
            }
            System.out.print("Seleccione opcion: ");

            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida.");
                continue;
            }

            if (opcion == 1) {
                System.out.println("\n--- Creando nueva partida ---");
                juego = Juego_MartinArayaGaete_217813697.crearPartida(sc);
                continue;
            }

            // 2) Procesa IMPORTAR/EXPORTAR SIEMPRE, independientemente de si ya hay partida o no
            if (opcion == 9) {
                if (juego == null) {
                    System.out.print("Ruta del archivo JSON a importar: ");
                    String rutaIn = sc.nextLine().trim();
                    juego = Juego_MartinArayaGaete_217813697.importar(rutaIn);
                    if (juego != null) {
                        System.out.println("¡Partida importada correctamente!");
                    } else {
                        System.out.println("No se pudo importar la partida.");
                    }
                } else {
                    System.out.print("Ruta para guardar el archivo (ej: partida.json): ");
                    String rutaOut = sc.nextLine().trim();
                    juego.exportar(rutaOut);
                }
                continue;
            }

            if (juego == null) {
                System.out.println("Primero crea una partida.");
                continue;
            }

            switch (opcion) {
                case 2:
                    System.out.println(juego);
                    break;

                case 3:
                    juego.jugarTurno(sc);
                    if (juego.isTerminado()) {
                        // Ya imprimió "¡El juego ha terminado!…" dentro de avanzarYVerificarFin()
                        ejecutando = false;
                    }
                    break;

                case 4:
                    gestionarPropiedad(sc, juego, 4);
                    break;

                case 5:
                    gestionarPropiedad(sc, juego, 5);
                    break;

                case 6:
                    gestionarPropiedad(sc, juego, 6);
                    break;

                case 7:
                    gestionarPropiedad(sc, juego, 7);
                    break;

                case 8:
                    System.out.println("¡Gracias por jugar CAPITALIA!");
                    ejecutando = false;
                    break;

                case 9:
                    if (juego == null) {
                        System.out.print("Ruta del archivo JSON a importar: ");
                        String rutaIn = sc.nextLine().trim();
                        juego = Juego_MartinArayaGaete_217813697.importar(rutaIn);
                        if (juego != null) {
                            System.out.println("¡Partida importada correctamente!");
                        } else {
                            System.out.println("No se pudo importar la partida.");
                        }
                    } else {
                        System.out.print("Ruta para guardar el archivo (ej: partida.json): ");
                        String ruta = sc.nextLine().trim();
                        juego.exportar(ruta);
                    }
                    break;

                case 10:
                    Jugador_MartinArayaGaete_217813697 jugadorMulta = juego.getJugadorActual();
                    if (jugadorMulta.isEstaEnCarcel()) {
                        final int MULTA = 500;
                        if (jugadorMulta.getDinero() >= MULTA) {
                            jugadorMulta.decrementarDinero(MULTA);
                            jugadorMulta.setEstaEnCarcel(false);
                            jugadorMulta.setContadorCarcel(0);
                            System.out.println("Pagaste la multa de $" + MULTA + " y saliste de la carcel.");
                        } else {
                            System.out.println("No tienes suficiente dinero para pagar la multa.");
                        }
                    } else {
                        System.out.println("No estas en la cárcel.");
                    }
                    break;

                case 11:
                    Jugador_MartinArayaGaete_217813697 jugadorComodin = juego.getJugadorActual();
                    if (jugadorComodin.isEstaEnCarcel()) {
                        if (jugadorComodin.getTotalCartasSalirCarcel() > 0) {
                            jugadorComodin.decrementarCartaSalirCarcel();
                            jugadorComodin.setEstaEnCarcel(false);
                            jugadorComodin.setContadorCarcel(0);
                            System.out.println("Usaste una carta 'Salir de la cárcel' y quedaste libre.");
                        } else {
                            System.out.println("No tienes cartas comodin disponibles.");
                        }
                    } else {
                        System.out.println("No estás en la carcel.");
                    }
                    break;

                default:
                    System.out.println("Opción invalida.");
            }
        }
        sc.close();
    }

    private static void gestionarPropiedad(Scanner sc, Juego_MartinArayaGaete_217813697 juego, int tipoAccion) {
        // Mostrar jugadores
        System.out.println("\nJugadores disponibles:");
        juego.getJugadores().forEach(j ->
                System.out.println(j.getId() + ". " + j.getNombre())
        );
        System.out.print("Selecciona ID de jugador: ");
        int idJugador = Integer.parseInt(sc.nextLine().trim());
        Jugador_MartinArayaGaete_217813697 jugador = juego.getJugadores().stream()
                .filter(j -> j.getId() == idJugador).findFirst().orElse(null);
        if (jugador == null) {
            System.out.println("Jugador no encontrado.");
            return;
        }

        // Mostrar propiedades
        List<Propiedad_MartinArayaGaete_217813697> props = jugador.getPropiedades();
        if (props.isEmpty()) {
            System.out.println("Este jugador no posee propiedades.");
            return;
        }

        System.out.println("\nPropiedades de " + jugador.getNombre() + ":");
        props.forEach(p -> System.out.println(p.getId() + ". " + p.getNombre()
                + " (Precio: $" + p.getPrecio()
                + ", Renta: $" + p.getRenta()
                + ", Casas: " + p.getCasas()
                + ", Hipotecada: " + p.isEstaHipotecada() + ")"));

        System.out.print("Selecciona ID de propiedad: ");
        int idProp = Integer.parseInt(sc.nextLine().trim());
        Propiedad_MartinArayaGaete_217813697 propiedad = props.stream()
                .filter(p -> p.getId() == idProp).findFirst().orElse(null);
        if (propiedad == null) {
            System.out.println("Propiedad no encontrada.");
            return;
        }

        // Ejecutar acción según número
        switch (tipoAccion) {
            case 4: // Construir casa
                juego.construirCasa(jugador, propiedad);
                break;
            case 5: // Construir hotel
                juego.construirHotel(jugador, propiedad);
                break;
            case 6: // Hipotecar
                juego.hipotecarPropiedad(jugador, propiedad);
                break;
            case 7: // Deshipotecar
                juego.deshipotecarPropiedad(jugador, propiedad);
                break;
        }
    }

}
