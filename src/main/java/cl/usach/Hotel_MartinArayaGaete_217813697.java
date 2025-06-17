package cl.usach;

/**
 * Representa un hotel en CAPITALIA. Es una extensión de Propiedad.
 * Al ser un Hotel, reemplaza las casas construidas por la presencia de un hotel.
 */
public class Hotel_MartinArayaGaete_217813697 extends Propiedad_MartinArayaGaete_217813697 {

    /**
     * Constructor de Hotel.
     * @param id Identificador único del hotel.
     * @param nombre Nombre del hotel (puede coincidir con la propiedad base).
     * @param precio Precio de conversión de casas a hotel (puede ser el precio base).
     * @param renta Renta base cuando existe un hotel (suele ser doble de la renta máxima con casas).
     * @param dueño Jugador que construye el hotel.
     */
    public Hotel_MartinArayaGaete_217813697(int id, String nombre, int precio, int renta, Jugador_MartinArayaGaete_217813697 dueño) {
        super(id, nombre, precio, renta, dueño);
        // Un Hotel reemplaza todas las casas: ponemos casas a 0
        setCasas(0);
    }

    @Override
    public String toString() {
        return String.format("Hotel[id=%d, nombre=%s, precio=%d, renta=%d, dueño=%s]",
                getId(), getNombre(), getPrecio(), getRenta(),
                getDueño() != null ? getDueño().getNombre() : "Banco");
    }
}
