package cl.usach;

/**
 * Representa una carta de Suerte en CAPITALIA.
 * Extiende la clase abstracta Carta.
 */
public class CartaSuerte_MartinArayaGaete_217813697 extends Carta_MartinArayaGaete_217813697 {
    private String tipo = "Suerte";
    private String accion;

    /**
     * Constructor de CartaSuerte.
     * @param id Identificador único de la carta.
     * @param descripcion Descripción de la carta.
     * @param accion Acción a ejecutar (como texto).
     */
    public CartaSuerte_MartinArayaGaete_217813697(int id, String descripcion, String accion) {
        super(id, descripcion);
        this.accion = accion;
    }

    /**
     * Obtiene el tipo de carta: "Suerte".
     * @return el tipo de carta.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene la acción asociada a la carta.
     * @return la acción en formato String.
     */
    public String getAccion() {
        return accion;
    }

    @Override
    public void ejecutarAccion(Juego_MartinArayaGaete_217813697 juego) {
        // Implementación básica: muestra la acción.
        System.out.println("[Carta Suerte] " + getDescripcion() + ": " + accion);
        // Aquí podrías parsear 'accion' y modificar el estado del juego.
    }
}
