package cl.usach;
import cl.usach.Jugador_MartinArayaGaete_217813697;

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

    // nuevo
    private String tipo;
    // se espera: "Propiedad", "Impuesto", "Suerte", "Comunidad", "Carcel", "Salida", etc...

    private boolean esHotel;
    private boolean estaHipotecada;

    public Propiedad_MartinArayaGaete_217813697(
            int id, String nombre, int precio, int renta,
            Jugador_MartinArayaGaete_217813697 dueño,
            String tipo) {
        this.id = id;
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser null");
        this.precio = precio;
        this.renta = renta;
        this.dueño = dueño;
        this.casas = 0;
        this.esHotel = false;
        this.estaHipotecada = false;
        this.tipo = Objects.requireNonNull(tipo, "tipo no puede ser null");
    }
    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " (" + tipo + ") - Precio: $" + precio
                + ", Renta: $" + renta + ", EsHotel?: $" + esHotel +", Dueño: "
                + (dueño != null ? dueño.getNombre() : "ninguno");
    }

    // Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Nombre de propiedad
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Precio
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    // Renta
    public int getRenta() {
        return renta;
    }
    public void setRenta(int renta) {
        this.renta = renta;
    }

    // Dueño
    public Jugador_MartinArayaGaete_217813697 getDueño() {
        return dueño;
    }
    public void setDueño(Jugador_MartinArayaGaete_217813697 dueño) {
        this.dueño = dueño;
    }

    // Casas
    public int getCasas() {
        return casas;
    }
    public void setCasas(int casas) {
        this.casas = casas;
    }

    // Hotel?
    public boolean isEsHotel() {
        return esHotel;
    }
    public void setEsHotel(boolean esHotel) {
        this.esHotel = esHotel;
    }

    // Hipotecada?
    public boolean isEstaHipotecada() {
        return estaHipotecada;
    }
    public void setEstaHipotecada(boolean estaHipotecada) {
        this.estaHipotecada = estaHipotecada;
    }

    // Tipo
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}