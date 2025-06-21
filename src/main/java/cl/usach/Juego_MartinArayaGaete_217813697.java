package cl.usach;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representa una partida de CAPITALIA.
 * Contiene el tablero, jugadores, configuraciones y estado del juego.
 */
public class Juego_MartinArayaGaete_217813697 {
    private List<Jugador_MartinArayaGaete_217813697> jugadores;
    private Tablero_MartinArayaGaete_217813697 tablero;
    private int dineroBanco;
    private int numeroDados;
    private int turnoActual;
    private int tasaImpuesto;
    private int maximoCasas;
    private int maximoHoteles;

    /**
     * Constructor de juego: inicializa parámetros y carga datos iniciales.
     */
    public Juego_MartinArayaGaete_217813697() {
        this.jugadores = new ArrayList<>();
        this.tablero = new Tablero_MartinArayaGaete_217813697();
        this.dineroBanco = 20000;         // Ejemplo: capital del banco
        this.numeroDados = 2;             // Mínimo 2 dados soportados
        this.turnoActual = 0;             // Comienza el primer jugador
        this.tasaImpuesto = 10;           // 10% de impuesto por vuelta completa
        this.maximoCasas = 4;             // Máximo de casas antes de hotel
        this.maximoHoteles = 1;           // Máximo de hoteles por propiedad

        // Carga de datos iniciales
        cargarCartas();
        cargarPropiedades();
    }

    /**
     * RF11. (0.1 pts) Cargar datos iniciales. Al momento de iniciar el juego ya debe existir
     * un tablero cargado con propiedades, cartas suerte y comunidad.

     * Carga 10 cartas de suerte y 10 de comunidad al tablero con temáticas chilenas.
     */
    private void cargarCartas() {
        // Cartas de Suerte
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(1,
                "Es tu cumpleaños en Santiago!", "RECIBE 200"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(2,
                "Caos en la Costanera Norte", "PAGA 100"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(3,
                "Se cortó la luz en Providencia", "PAGA 50"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(4,
                "Te gana la micro: llegas tarde", "PAGA 150"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(5,
                "Encuentras luca en el suelo", "RECIBE 100"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(6,
                "Tienes que pagar el TAG", "PAGA 120"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(7,
                "Asado en la casa de un amigo", "PAGA 80"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(8,
                "Recibes premio de loto", "RECIBE 500"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(9,
                "Multa por mal estacionarte", "PAGA 200"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(10,
                "Te traga bus sin vuelto", "PAGA 70"));

        // Cartas de Comunidad
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(1,
                "Te secuestra un venezolano! Pierdes un turno", "CARCEL"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(2,
                "Recibe subvención estatal", "RECIBE 300"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(3,
                "Inauguraron Metro de Rancagua", "AVANZA 3"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(4,
                "Rompes la alcancía", "PAGA 50"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(5,
                "Pagas matrícula universitaria", "PAGA 100"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(6,
                "Encuentras trabajo part-time", "RECIBE 150"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(7,
                "Junta de vecinos: gastas onces", "PAGA 30"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(8,
                "Bono por buen contribuyente", "RECIBE 200"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(9,
                "Inflación repentina", "PAGA 120"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(10,
                "Donas a la Teletón", "PAGA 100"));
    }

    /**
     * Carga propiedades y casillas especiales del tablero como objetos Propiedad.
     */
    private void cargarPropiedades() {
        int id = 1;

        // Propiedades comprables
        String[] comunas = {"Providencia", "Las Condes", "Ñuñoa", "Santiago Centro", "Vitacura",
                "La Reina", "Macul", "Peñalolén", "La Florida", "Maipú",
                "Puente Alto", "Recoleta", "Cerrillos", "Lo Barnechea", "Independencia"};
        for (int i = 0; i < comunas.length; i++, id++) {
            tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                    id,
                    comunas[i],
                    200 + (i * 50),    // Precio base creciente
                    20 + (i * 5),      // Renta base creciente
                    null
            ));
        }

        // Casillas especiales
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(++id, "Salida", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(++id, "Comunidad", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(++id, "Suerte", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(++id, "Cárcel", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(++id, "Vas a la Cárcel", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(++id, "Estacionamiento Libre", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(++id, "Impuesto Municipal", 0, 300, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(++id, "Impuesto Verde", 0, 500, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Estado del Juego ---\n");
        sb.append("Banco: $" + dineroBanco + "\n");
        sb.append("Dados: " + numeroDados + "   Impuesto: " + tasaImpuesto + "%\n");
        sb.append("Turno actual: Jugador " + (turnoActual + 1) + "\n");
        sb.append("Jugadores:\n");
        for (Jugador_MartinArayaGaete_217813697 j : jugadores) {
            sb.append(j.toString()).append("\n");
        }
        sb.append("Tablero:\n");
        sb.append(tablero.toString());
        return sb.toString();
    }

    public void setJugadores(List<Jugador_MartinArayaGaete_217813697> jugadores) {
        this.jugadores = jugadores;
    }

    public void setTablero(Tablero_MartinArayaGaete_217813697 tablero) {
        this.tablero = tablero;
    }

    public void setDineroBanco(int dineroBanco) {
        this.dineroBanco = dineroBanco;
    }

    public void setNumeroDados(int numeroDados) {
        this.numeroDados = numeroDados;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    public void setTasaImpuesto(int tasaImpuesto) {
        this.tasaImpuesto = tasaImpuesto;
    }

    public void setMaximoCasas(int maximoCasas) {
        this.maximoCasas = maximoCasas;
    }

    public void setMaximoHoteles(int maximoHoteles) {
        this.maximoHoteles = maximoHoteles;
    }

    public List<Jugador_MartinArayaGaete_217813697> getJugadores() {
        return jugadores;
    }

    public Tablero_MartinArayaGaete_217813697 getTablero() {
        return tablero;
    }

    public int getDineroBanco() {
        return dineroBanco;
    }

    public int getNumeroDados() {
        return numeroDados;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public int getTasaImpuesto() {
        return tasaImpuesto;
    }

    public int getMaximoCasas() {
        return maximoCasas;
    }

    public int getMaximoHoteles() {
        return maximoHoteles;
    }


    // RF12. (0.1 pts) Agregar Propiedad. Agregar propiedades al tablero.
    public void agregarPropiedadAlTablero(Propiedad_MartinArayaGaete_217813697 propiedad) {
        tablero.agregarPropiedad(propiedad);
    }

    // RF13. (0.1 pts) Agregar Jugador. Agregar un jugador a la partida.
    public void agregarJugador(String nombre, int id) {
        int capitalInicial = 1500;

        if (dineroBanco >= capitalInicial) {
            Jugador_MartinArayaGaete_217813697 jugador = new Jugador_MartinArayaGaete_217813697(id, nombre, capitalInicial);
            jugadores.add(jugador);
            dineroBanco -= capitalInicial;
        } else {
            System.out.println("¡No hay suficiente dinero en el banco para crear otro jugador!");
        }
    }

    // RF14. (0.1 pts) Obtener Jugador Actual. Obtener el jugador cuyo turno se encuentra en curso (jugador actual)
    public Jugador_MartinArayaGaete_217813697 getJugadorActual() {
        if (jugadores.isEmpty()) {
            return null;
        }
        return jugadores.get(turnoActual);
    }

    // RF15. (0.1 pts) Lanzar dados. Simular el lanzamiento de N dados
    public List<Integer> lanzarDados() {
        List<Integer> resultados = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numeroDados; i++) {
            int dado = random.nextInt(6) + 1; // Valor entre 1 y 6
            resultados.add(dado);
        }

        return resultados;
    }

    // RF16. (0.1 pts) Mover Jugador. Mover al jugador en el tablero
    public void moverJugador(int idJugador, int pasos) {
        for (Jugador_MartinArayaGaete_217813697 jugador : jugadores) {
            if (jugador.getId() == idJugador) {
                int posicionActual = jugador.getPosicionActual();
                int tamañoTablero = tablero.getPropiedades().size();

                int nuevaPosicion = (posicionActual + pasos) % tamañoTablero;
                jugador.setPosicionActual(nuevaPosicion);

                System.out.println("Jugador " + jugador.getNombre() + " se ha movido a la casilla " + nuevaPosicion +
                        " (" + tablero.getPropiedades().get(nuevaPosicion).getNombre() + ").");
                break;
            }
        }
    }

    //RF17. (0.1 pts) Comprar Propiedad. Permite comprar una propiedad
    public boolean comprarPropiedad(Jugador_MartinArayaGaete_217813697 jugador, Propiedad_MartinArayaGaete_217813697 propiedad) {
        // Verificar si la propiedad ya tiene dueño
        if (propiedad.getDueño() != null) {
            System.out.println("La propiedad \"" + propiedad.getNombre() + "\" ya tiene dueño.");
            return false;
        }

        // Verificar si está hipotecada
        if (propiedad.isEstaHipotecada()) {
            System.out.println("La propiedad \"" + propiedad.getNombre() + "\" está hipotecada. No se puede comprar.");
            return false;
        }

        // Verificar si el jugador tiene suficiente dinero
        if (jugador.getDinero() < propiedad.getPrecio()) {
            System.out.println("No tienes suficiente dinero para comprar \"" + propiedad.getNombre() + "\".");
            return false;
        }

        // Realizar la compra
        jugador.setDinero(jugador.getDinero() - propiedad.getPrecio());
        jugador.agregarPropiedad(propiedad);
        propiedad.setDueño(jugador);

        System.out.println("¡" + jugador.getNombre() + " ha comprado \"" + propiedad.getNombre() + "\" por $" + propiedad.getPrecio() + "!");
        return true;
    }


}
