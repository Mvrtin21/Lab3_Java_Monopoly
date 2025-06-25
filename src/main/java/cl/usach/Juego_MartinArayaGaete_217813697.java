package cl.usach;

import java.util.Scanner;
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

    // RF18. (0.1 pts) Calcular Renta Propiedad. Calcular la renta de una propiedad sumando todas las casas y hoteles que tenga.
    public int calcularRentaPropiedad(Propiedad_MartinArayaGaete_217813697 propiedad) {
        // Si la propiedad está hipotecada o no tiene dueño, no se cobra renta
        if (propiedad.isEstaHipotecada() || propiedad.getDueño() == null || propiedad.getDueño().getId() == -1) {
            return 0;
        }

        int rentaBase = propiedad.getRenta();
        int casas = propiedad.getCasas();
        boolean esHotel = propiedad instanceof Hotel_MartinArayaGaete_217813697;

        // Renta base si no tiene construcciones
        if (casas == 0 && !esHotel) {
            return rentaBase;
        }

        // Aumento por casas
        if (!esHotel) {
            double aumento = rentaBase * (1 + (0.2 * casas));
            return (int) Math.round(aumento);
        }

        // Si es hotel, renta es el doble de la renta con el máximo de casas
        int rentaMaxCasas = (int) Math.round(rentaBase * (1 + (0.2 * maximoCasas)));
        return rentaMaxCasas * 2;
    }

    // RF19. (0.1 pts) Calcular Renta Jugador. Calcular la renta de las propiedades de un jugador.
    public int calcularRentaJugador(Jugador_MartinArayaGaete_217813697 jugador) {
        int rentaTotal = 0;

        for (Propiedad_MartinArayaGaete_217813697 propiedad : jugador.getPropiedades()) {
            int renta = calcularRentaPropiedad(propiedad); // Reutiliza RF18
            rentaTotal += renta;
        }

        // Aplica impuesto
        double impuesto = (rentaTotal * tasaImpuesto) / 100.0;

        return (int) Math.round(impuesto);
    }

    // RF20. (0.2 pts) Construir Hotel. Construir Hotel en una Propiedad
    public boolean construirHotel(Jugador_MartinArayaGaete_217813697 jugador, Propiedad_MartinArayaGaete_217813697 propiedad) {
        if (propiedad.getDueño() == null || propiedad.getDueño().getId() != jugador.getId()) {
            System.out.println("No puedes construir un hotel en \"" + propiedad.getNombre() + "\" porque no eres el dueño.");
            return false;
        }

        if (propiedad.isEsHotel()) {
            System.out.println("La propiedad \"" + propiedad.getNombre() + "\" ya tiene un hotel.");
            return false;
        }

        if (propiedad.getCasas() < maximoCasas) {
            System.out.println("No se puede construir hotel. Se requieren " + maximoCasas + " casas.");
            return false;
        }

        propiedad.setEsHotel(true);
        propiedad.setCasas(0);

        System.out.println("¡" + jugador.getNombre() + " ha construido un hotel en \"" + propiedad.getNombre() + "\"!");
        return true;
    }


    // RF20.1. (? pts) Construir Casa. Construir Casa en una Propiedad
    public boolean construirCasa(Jugador_MartinArayaGaete_217813697 jugador, Propiedad_MartinArayaGaete_217813697 propiedad) {

        if (propiedad.getDueño() == null || !propiedad.getDueño().equals(jugador)) {
            System.out.println("Error: El jugador \"" + jugador.getNombre() + "\" no es el dueño de \"" + propiedad.getNombre() + "\".");
            return false;
        }

        if (propiedad.isEsHotel()) {
            System.out.println("No se puede construir casas. La propiedad \"" + propiedad.getNombre() + "\" ya tiene un hotel.");
            return false;
        }

        if (propiedad.getCasas() >= maximoCasas) {
            System.out.println("La propiedad \"" + propiedad.getNombre() + "\" ya tiene el máximo de casas permitido.");
            return false;
        }

        int precioCasa = propiedad.getPrecio() / 3;

        if (jugador.getDinero() < precioCasa) {
            System.out.println("El jugador \"" + jugador.getNombre() + "\" no tiene suficiente dinero para construir una casa. Requiere $" + precioCasa);
            return false;
        }

        // Realizar la construcción
        propiedad.setCasas(propiedad.getCasas() + 1);
        jugador.setDinero(jugador.getDinero() - precioCasa);

        System.out.println("✅ Se construyó una casa en \"" + propiedad.getNombre() + "\". Total casas: " + propiedad.getCasas());
        return true;
    }

    // 21. RF21. (0.2 pts) Pagar Renta. Jugador paga renta a otro.
    public boolean pagarRenta(Jugador_MartinArayaGaete_217813697 quienPaga, Propiedad_MartinArayaGaete_217813697 propiedad) {
        Jugador_MartinArayaGaete_217813697 dueño = propiedad.getDueño();

        if (dueño == null || dueño == quienPaga) {
            // No se paga renta si no hay dueño o si es suya
            return false;
        }

        int renta = propiedad.getRenta();

        if (quienPaga.getDinero() < renta) {
            System.out.println("¡" + quienPaga.getNombre() + " no tiene suficiente dinero para pagar la renta de $" + renta + "!");
            System.out.println("Se declara bancarrota. Todas sus propiedades pasarán a " + dueño.getNombre() + ".");

            // Transferencia de propiedades
            for (Propiedad_MartinArayaGaete_217813697 prop : quienPaga.getPropiedades()) {
                prop.setDueño(dueño);
                dueño.agregarPropiedad(prop);
            }

            // Limpiar jugador en bancarrota
            quienPaga.getPropiedades().clear();
            quienPaga.setDinero(0);

            System.out.println(quienPaga.getNombre() + " ha quedado en bancarrota. RIP.");
            return false;
        }

        // Pago normal
        quienPaga.setDinero(quienPaga.getDinero() - renta);
        dueño.setDinero(dueño.getDinero() + renta);

        System.out.println(quienPaga.getNombre() + " pagó $" + renta + " a " + dueño.getNombre() + " por la propiedad \"" + propiedad.getNombre() + "\".");
        return true;
    }

    // RF22. (0.2 pts) Hipotecar Propiedad. Hipotecar una propiedad
    public boolean hipotecarPropiedad(Jugador_MartinArayaGaete_217813697 jugador, Propiedad_MartinArayaGaete_217813697 propiedad) {
        // Verifica que el jugador sea el dueño
        if (propiedad.getDueño() == null || propiedad.getDueño() != jugador) {
            System.out.println("No puedes hipotecar una propiedad que no te pertenece.");
            return false;
        }

        if (propiedad.isEstaHipotecada()) {
            System.out.println("La propiedad \"" + propiedad.getNombre() + "\" ya está hipotecada.");
            return false;
        }

        int prestamo = calcularRentaPropiedad(propiedad); // usamos método reutilizable
        propiedad.setEstaHipotecada(true);
        jugador.setDinero(jugador.getDinero() + prestamo);

        System.out.println("Has hipotecado \"" + propiedad.getNombre() + "\" y recibido $" + prestamo + ".");
        return true;
    }

    // RF22.1. (? pts) Hipotecar Propiedad. Hipotecar una propiedad
    public boolean deshipotecarPropiedad(Jugador_MartinArayaGaete_217813697 jugador, Propiedad_MartinArayaGaete_217813697 propiedad) {
        if (propiedad.getDueño() == null || propiedad.getDueño() != jugador) {
            System.out.println("No puedes deshipotecar una propiedad que no te pertenece.");
            return false;
        }

        if (!propiedad.isEstaHipotecada()) {
            System.out.println("La propiedad \"" + propiedad.getNombre() + "\" no está hipotecada.");
            return false;
        }

        int penalizacion = calcularRentaPropiedad(propiedad) * 2;

        if (jugador.getDinero() < penalizacion) {
            System.out.println("No tienes suficiente dinero para deshipotecar \"" + propiedad.getNombre() + "\". Requiere $" + penalizacion + ".");
            return false;
        }

        jugador.setDinero(jugador.getDinero() - penalizacion);
        propiedad.setEstaHipotecada(false);

        System.out.println("Has deshipotecado \"" + propiedad.getNombre() + "\" pagando $" + penalizacion + ".");
        return true;
    }

    // RF23. (0.2 pts) Extraer Carta. Extraer una carta del mazo.
    public CartaSuerte_MartinArayaGaete_217813697 extraerCartaSuerte() {
        List<CartaSuerte_MartinArayaGaete_217813697> cartasSuerte = tablero.getCartasSuerte();
        if (cartasSuerte.isEmpty()) {
            System.out.println("¡El mazo de cartas de Suerte está vacío!");
            return null;
        }

        int indice = new Random().nextInt(cartasSuerte.size());
        return cartasSuerte.remove(indice);
    }


    public CartaComunidad_MartinArayaGaete_217813697 extraerCartaComunidad() {
        List<CartaComunidad_MartinArayaGaete_217813697> cartasComunidad = tablero.getCartasComunidad();
        if (cartasComunidad.isEmpty()) {
            System.out.println("¡El mazo de cartas de Comunidad está vacío!");
            return null;
        }

        int indice = new Random().nextInt(cartasComunidad.size());
        return cartasComunidad.remove(indice);
    }

    // RF24. (0.1 pts) Verificar bancarrota. Verificar si un jugador se encuentra en bancarrota (sin dinero).
    public boolean verificarBancarrota(Jugador_MartinArayaGaete_217813697 jugador) {
        return jugador.getDinero() <= 0;
    }

    // 25. RF25. (1 pts) Jugar Turno. Ejecuta un turno completo aplicando todas las reglas del juego
    public void jugarTurno(Scanner sc) {
        // 1) Obtener jugador actual
        Jugador_MartinArayaGaete_217813697 jugador = jugadores.get(turnoActual);
        System.out.println("\n--- Turno de " + jugador.getNombre() + " ---");

        // 2) Lanzar dados y mostrar resultados
        List<Integer> resultados = lanzarDados();
        System.out.print("Dados: ");
        for (int d : resultados) {
            System.out.print(d + " ");
        }
        System.out.println();

        // 3) Calcular suma y mover
        int suma = 0;
        for (int d : resultados) suma += d;
        moverJugador(jugador.getId(), suma);

        // 4) Procesar casilla de destino
        int pos = jugador.getPosicionActual();
        Propiedad_MartinArayaGaete_217813697 casilla = tablero.getPropiedades().get(pos);
        System.out.println("Caíste en casilla [" + pos + "]: " + casilla.getNombre());

        // 5) Si es propiedad sin dueño, ofrecer compra
        if (casilla.getDueño() == null && casilla.getPrecio() > 0) {
            System.out.print("¿Comprar \"" + casilla.getNombre() + "\" por $" + casilla.getPrecio() + "? (S/N): ");
            String resp = sc.nextLine();
            if (resp.equalsIgnoreCase("S")) {
                comprarPropiedad(jugador, casilla);
            } else {
                System.out.println("No compraste la propiedad.");
            }
        }
        // 6) Si tiene dueño distinto, pagar renta
        else if (casilla.getDueño() != null && casilla.getDueño() != jugador) {
            pagarRenta(jugador, casilla);
        }
        // casillas especiales (Impuesto, Suerte, Comunidad, Cárcel…)
        else {
            String tipo = casilla.getTipo();
            if (tipo.equals("Impuesto")) {
                System.out.println("Debes pagar impuesto de $" + casilla.getRenta());
                pagarRenta(jugador, casilla);
            } else if (tipo.equals("Suerte")) {
                CartaSuerte_MartinArayaGaete_217813697 c = tablero.extraerCartaSuerte();
                if (c != null) c.ejecutarAccion(this);
            } else if (tipo.equals("Comunidad")) {
                CartaComunidad_MartinArayaGaete_217813697 c = tablero.extraerCartaComunidad();
                if (c != null) c.ejecutarAccion(this);
            } else if (tipo.equals("Carcel")) {
                System.out.println("¡Vas a la cárcel!");
                jugador.setEstaEnCarcel(true);
            }
            // Salida, Estacionamiento, etc. no hacen nada aquí
        }

        // 7) Avanzar turno
        turnoActual = (turnoActual + 1) % jugadores.size();
        System.out.println("Turno para: " + jugadores.get(turnoActual).getNombre());
    }


}
