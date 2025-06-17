package cl.usach;

import java.util.Objects;

/**
 * Representa una propiedad en el juego CAPITALIA.
 * Contiene información de precio, renta, propietario y estado de hipoteca.
 */
public class Propiedad_MartinArayaGaete_217813697 {
    private int id;
    private String nombre;
    private int precio;
    private int renta;
    private Jugador_MartinArayaGaete_217813697 dueño;
    private int casas;
    private boolean estaHipotecada;

    /**
     * Constructor de Propiedad.
     * @param id Identificador único de la propiedad.
     * @param nombre Nombre de la propiedad.
     * @param precio Precio de compra de la propiedad.
     * @param renta Renta base que paga al propietario.
     * @param dueño Jugador que es dueño de la propiedad (null si está sin comprar).
     */
    public Propiedad_MartinArayaGaete_217813697(int id, String nombre, int precio, int renta, Jugador_MartinArayaGaete_217813697 dueño) {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser null");
        this.precio = precio;
        this.renta = renta;
        this.dueño = dueño;
        this.casas = 0;
        this.estaHipotecada = false;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }
    public int getRenta() { return renta; }
    public Jugador_MartinArayaGaete_217813697 getDueño() { return dueño; }
    public int getCasas() { return casas; }
    public boolean isEstaHipotecada() { return estaHipotecada; }

    // Setters
    public void setDueño(Jugador_MartinArayaGaete_217813697 dueño) { this.dueño = dueño; }
    public void setCasas(int casas) { this.casas = casas; }
    public void setEstaHipotecada(boolean estaHipotecada) { this.estaHipotecada = estaHipotecada; }

    @Override
    public String toString() {
        return String.format("Propiedad[id=%d, nombre=%s, precio=%d, renta=%d, dueño=%s, casas=%d, hipotecada=%b]",
                id, nombre, precio, renta,
                dueño != null ? dueño.getNombre() : "Banco",
                casas, estaHipotecada);
    }
}