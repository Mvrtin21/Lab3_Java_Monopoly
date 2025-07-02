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
    private int contadorCarcel;

    public Jugador_MartinArayaGaete_217813697(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.dinero = 1500;
        this.propiedades = new ArrayList<>();
        this.posicionActual = 0; // Salida
        this.estaEnCarcel = false;
        this.totalCartasSalirCarcel = 0;
        this.contadorCarcel = 0;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getDinero() { return dinero; }
    public List<Propiedad_MartinArayaGaete_217813697> getPropiedades() { return propiedades; }
    public int getPosicionActual() { return posicionActual; }
    public boolean isEstaEnCarcel() { return estaEnCarcel; }
    public int getTotalCartasSalirCarcel() { return totalCartasSalirCarcel; }
    public int getContadorCarcel() {
        return contadorCarcel;
    }

    // Setters / Modificadores
    public void setDinero(int dinero) { this.dinero = dinero; }
    public void setPosicionActual(int posicionActual) { this.posicionActual = posicionActual; }
    public void setEstaEnCarcel(boolean estaEnCarcel) { this.estaEnCarcel = estaEnCarcel; }
    public void setTotalCartasSalirCarcel(int totalCartasSalirCarcel) { this.totalCartasSalirCarcel = totalCartasSalirCarcel; }
    public void setContadorCarcel(int contadorCarcel) {
        this.contadorCarcel = contadorCarcel;
    }

    public void agregarPropiedad(Propiedad_MartinArayaGaete_217813697 propiedad) {
        propiedades.add(propiedad);
    }

    public void eliminarPropiedad(Propiedad_MartinArayaGaete_217813697 propiedad) {
        propiedades.remove(propiedad);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "Jugador[id=%d, nombre=%s, dinero=%d, posicion=%d, enCarcel=%b, cartasSalirCarcel=%d]%n",
                id, nombre, dinero, posicionActual, estaEnCarcel, totalCartasSalirCarcel
        ));
        sb.append("Propiedades del jugador:\n");
        if (propiedades.isEmpty()) {
            sb.append("  (ninguna)\n");
        } else {
            for (Propiedad_MartinArayaGaete_217813697 prop : propiedades) {
                sb.append("  - ").append(prop.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    public void decrementarDinero(int monto) {
        if (monto < 0) {
            System.out.println("Error: No puedes decrementar un monto negativo.");
            return;
        }
        this.dinero -= monto;
        System.out.println(nombre + " ha pagado $" + monto + ". Dinero restante: $" + this.dinero);

        if (this.dinero < 0) {
            System.out.println(nombre + " está en bancarrota.");
            // Aquí podrías implementar la lógica para eliminar al jugador o traspasar sus propiedades
        }
    }

    public void pagaDinero(int monto) {
        this.dinero -= monto;
        System.out.println(nombre + (monto >= 0 ? " pago $" + monto : " recibio $" + (-monto)) + ".");
    }

    /**
     * Disminuye en 1 el contador de cartas "Salir de la cárcel".
     * Asegúrate de que totalCartasSalirCarcel nunca sea negativo.
     */
    public void decrementarCartaSalirCarcel() {
        if (this.totalCartasSalirCarcel > 0) {
            this.totalCartasSalirCarcel--;
        }
    }


}
