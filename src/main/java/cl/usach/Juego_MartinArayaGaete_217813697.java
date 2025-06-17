package cl.usach;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una partida de CAPITALIA.
 * Contiene el tablero, jugadores, configuraciones y estado del juego.
 */
public class Juego_MartinArayaGaete_217813697 {
    private List<Jugador_MartinArayaGaete_217813697> jugadores;
    private Tablero_MartinArayaGaete_217813697 tablero;
    private int dineroBanco;
    private int numeroDados;
    private int turnoActual;
    private int tasaImpuesto;
    private int maximoCasas;
    private int maximoHoteles;

    /**
     * Constructor de juego: inicializa parámetros y carga datos iniciales.
     */
    public Juego_MartinArayaGaete_217813697() {
        this.jugadores = new ArrayList<>();
        this.tablero = new Tablero_MartinArayaGaete_217813697();
        this.dineroBanco = 20000;         // Ejemplo: capital del banco
        this.numeroDados = 2;             // Mínimo 2 dados soportados
        this.turnoActual = 0;             // Comienza el primer jugador
        this.tasaImpuesto = 10;           // 10% de impuesto por vuelta completa
        this.maximoCasas = 4;             // Máximo de casas antes de hotel
        this.maximoHoteles = 1;           // Máximo de hoteles por propiedad

        // Carga de datos iniciales
        cargarCartas();
        cargarPropiedades();
    }

    /**
     * Carga 10 cartas de suerte y 10 de comunidad al tablero.
     */
    private void cargarCartas() {
        for (int i = 1; i <= 10; i++) {
            tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(i,
                    "Suerte " + i,
                    "Accion de suerte " + i));
            tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(i,
                    "Comunidad " + i,
                    "Accion de comunidad " + i));
        }
    }

    /**
     * Carga 15 propiedades al tablero.
     */
    private void cargarPropiedades() {
        for (int i = 1; i <= 15; i++) {
            tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                    i,
                    "Propiedad " + i,
                    100 * i,
                    10 * i,
                    null));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Estado del Juego ---\n");
        sb.append("Banco: $" + dineroBanco + "\n");
        sb.append("Dados: " + numeroDados + "   Impuesto: " + tasaImpuesto + "%\n");
        sb.append("Turno actual: Jugador " + (turnoActual + 1) + "\n");
        sb.append("Jugadores:\n");
        for (Jugador_MartinArayaGaete_217813697 j : jugadores) {
            sb.append(j.toString()).append("\n");
        }
        sb.append("Tablero:\n");
        sb.append(tablero.toString());
        return sb.toString();
    }
}
