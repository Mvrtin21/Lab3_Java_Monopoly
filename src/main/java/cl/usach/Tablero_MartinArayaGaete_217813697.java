package cl.usach;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el tablero de juego en CAPITALIA.
 * Contiene las propiedades, cartas y configuración de casillas especiales.
 */
public class Tablero_MartinArayaGaete_217813697 {
    private List<Propiedad_MartinArayaGaete_217813697> propiedades;
    private List<CartaSuerte_MartinArayaGaete_217813697> cartasSuerte;
    private List<CartaComunidad_MartinArayaGaete_217813697> cartasComunidad;

    /**
     * Constructor del tablero. Inicializa las listas vacías.
     */
    public Tablero_MartinArayaGaete_217813697() {
        this.propiedades = new ArrayList<>();
        this.cartasSuerte = new ArrayList<>();
        this.cartasComunidad = new ArrayList<>();
    }

    public void agregarPropiedad(Propiedad_MartinArayaGaete_217813697 propiedad) {
        propiedades.add(propiedad);
    }

    public void agregarCartaSuerte(CartaSuerte_MartinArayaGaete_217813697 carta) {
        cartasSuerte.add(carta);
    }

    public void agregarCartaComunidad(CartaComunidad_MartinArayaGaete_217813697 carta) {
        cartasComunidad.add(carta);
    }

    public List<Propiedad_MartinArayaGaete_217813697> getPropiedades() {
        return propiedades;
    }

    public List<CartaSuerte_MartinArayaGaete_217813697> getCartasSuerte() {
        return cartasSuerte;
    }

    public List<CartaComunidad_MartinArayaGaete_217813697> getCartasComunidad() {
        return cartasComunidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Propiedades:\n");
        for (Propiedad_MartinArayaGaete_217813697 p : propiedades) {
            sb.append(p.toString()).append("\n");
        }
        sb.append("Cartas Suerte: ").append(cartasSuerte.size()).append(" disponibles\n");
        sb.append("Cartas Comunidad: ").append(cartasComunidad.size()).append(" disponibles\n");
        return sb.toString();
    }

    public void setPropiedades(List<Propiedad_MartinArayaGaete_217813697> propiedades) {
        this.propiedades = propiedades;
    }

    public void setCartasSuerte(List<CartaSuerte_MartinArayaGaete_217813697> cartasSuerte) {
        this.cartasSuerte = cartasSuerte;
    }

    public void setCartasComunidad(List<CartaComunidad_MartinArayaGaete_217813697> cartasComunidad) {
        this.cartasComunidad = cartasComunidad;
    }
}
