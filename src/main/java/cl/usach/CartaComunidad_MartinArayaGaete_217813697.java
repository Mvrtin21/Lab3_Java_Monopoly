package cl.usach;

/**
 * Representa una carta de Comunidad en CAPITALIA.
 * Extiende la clase abstracta Carta.
 */
public class CartaComunidad_MartinArayaGaete_217813697 extends Carta_MartinArayaGaete_217813697 {
    private String tipo = "Comunidad";
    private String accion;

    /**
     * Constructor de CartaComunidad.
     * @param id Identificador único de la carta.
     * @param descripcion Descripción de la carta.
     * @param accion Acción a ejecutar (como texto).
     */
    public CartaComunidad_MartinArayaGaete_217813697(int id, String descripcion, String accion) {
        super(id, descripcion);
        this.accion = accion;
    }

    /**
     * Obtiene el tipo de carta: "Comunidad".
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
        Jugador_MartinArayaGaete_217813697 jugador = juego.getJugadores().get(juego.getTurnoActual());
        System.out.println("[Carta Comunidad] " + getDescripcion() + ": " + accion);

        if (accion.startsWith("PAGA")) {
            int monto = Integer.parseInt(accion.split(" ")[1]);
            jugador.decrementarDinero(monto);
            System.out.println(jugador.getNombre() + " pagó $" + monto + ".");
        } else if (accion.startsWith("RECIBE")) {
            int monto = Integer.parseInt(accion.split(" ")[1]);
            jugador.pagaDinero(monto);
            System.out.println(jugador.getNombre() + " recibió $" + monto + ".");
        } else if (accion.startsWith("AVANZA")) {
            int pasos = Integer.parseInt(accion.split(" ")[1]);
            juego.moverJugador(jugador.getId(), pasos);
            System.out.println(jugador.getNombre() + " avanza " + pasos + " casillas.");
            // Procesar casilla destino si quieres
        } else if (accion.equals("CARCEL")) {
            jugador.setEstaEnCarcel(true);
            System.out.println(jugador.getNombre() + " ha sido enviado a la cárcel.");
        } else {
            System.out.println("Acción desconocida: " + accion);
        }
    }
}
