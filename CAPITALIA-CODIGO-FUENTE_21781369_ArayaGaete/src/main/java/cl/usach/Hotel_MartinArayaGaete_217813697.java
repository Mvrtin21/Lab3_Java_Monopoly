package cl.usach;

/**
 * Representa un hotel en CAPITALIA. Es una extensión de Propiedad.
 * Al ser un Hotel, reemplaza las casas construidas por la presencia de un hotel.
 */
public class Hotel_MartinArayaGaete_217813697 extends Propiedad_MartinArayaGaete_217813697 {

    public Hotel_MartinArayaGaete_217813697(
            int id,
            String nombre,
            int precio,
            int renta,
            Jugador_MartinArayaGaete_217813697 dueno) {
        // Ahora invocamos el constructor de Propiedad que recibe el 'tipo'
        super(id, nombre, precio, renta, dueno, "Hotel");
        // Un Hotel reemplaza todas las casas: ponemos casas a 0
        setCasas(0);
    }

    @Override
    public String toString() {
        return String.format("Hotel[id=%d, nombre=%s, precio=%d, renta=%d, dueño=%s]",
                getId(), getNombre(), getPrecio(), getRenta(),
                getDueno() != null ? getDueno().getNombre() : "Banco");
    }
}
