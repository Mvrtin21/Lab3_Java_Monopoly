package cl.usach;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un jugador en el juego CAPITALIA.
 * Contiene información de identidad, patrimonio y estado en el tablero.
 */
public class Jugador_MartinArayaGaete_217813697 {
    private int id;
    private String nombre;
    private int dinero;
    private List<Propiedad_MartinArayaGaete_217813697> propiedades;
    private int posicionActual;
    private boolean estaEnCarcel;
    private int totalCartasSalirCarcel;

    /**
     * Constructor de Jugador.
     * @param id Identificador único del jugador.
     * @param nombre Nombre del jugador.
     * @param dinero Capital inicial del jugador.
     */
    public Jugador_MartinArayaGaete_217813697(int id, String nombre, int dinero) {
        this.id = id;
        this.nombre = nombre;
        this.dinero = dinero;
        this.propiedades = new ArrayList<>();
        this.posicionActual = 0; // Salida
        this.estaEnCarcel = false;
        this.totalCartasSalirCarcel = 0;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getDinero() { return dinero; }
    public List<Propiedad_MartinArayaGaete_217813697> getPropiedades() { return propiedades; }
    public int getPosicionActual() { return posicionActual; }
    public boolean isEstaEnCarcel() { return estaEnCarcel; }
    public int getTotalCartasSalirCarcel() { return totalCartasSalirCarcel; }

    // Setters / Modificadores
    public void setDinero(int dinero) { this.dinero = dinero; }
    public void setPosicionActual(int posicionActual) { this.posicionActual = posicionActual; }
    public void setEstaEnCarcel(boolean estaEnCarcel) { this.estaEnCarcel = estaEnCarcel; }
    public void setTotalCartasSalirCarcel(int totalCartasSalirCarcel) { this.totalCartasSalirCarcel = totalCartasSalirCarcel; }

    /**
     * Añade una propiedad al jugador.
     * @param propiedad La propiedad a añadir.
     */
    public void agregarPropiedad(Propiedad_MartinArayaGaete_217813697 propiedad) {
        propiedades.add(propiedad);
    }

    /**
     * Elimina una propiedad del jugador.
     * @param propiedad La propiedad a eliminar.
     */
    public void eliminarPropiedad(Propiedad_MartinArayaGaete_217813697 propiedad) {
        propiedades.remove(propiedad);
    }

    @Override
    public String toString() {
        return String.format(
                "Jugador[id=%d, nombre=%s, dinero=%d, posicion=%d, enCarcel=%b, cartasSalirCarcel=%d, numPropiedades=%d]",
                id, nombre, dinero, posicionActual, estaEnCarcel, totalCartasSalirCarcel, propiedades.size()
        );
    }
}
