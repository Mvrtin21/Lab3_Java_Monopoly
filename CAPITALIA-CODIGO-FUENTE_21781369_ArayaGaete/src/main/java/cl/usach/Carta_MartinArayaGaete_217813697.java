package cl.usach;

/**
 * Clase abstracta que representa una carta en el juego CAPITALIA.
 * Cada carta tiene un identificador único y una descripción.
 */
public abstract class Carta_MartinArayaGaete_217813697 {
    private int id;
    private String descripcion;

    /**
     * Constructor de la clase abstracta Carta.
     * @param id Identificador único de la carta.
     * @param descripcion Texto que describe la carta.
     */
    public Carta_MartinArayaGaete_217813697(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador de la carta.
     * @return el id de la carta.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene la descripción de la carta.
     * @return la descripción de la carta.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Acción que debe ejecutarse cuando se usa la carta.
     * Se deja abstracto para que las subclases definan su comportamiento.
     * @param juego Objeto juego     */
    public abstract void ejecutarAccion(Juego_MartinArayaGaete_217813697 juego);
}
