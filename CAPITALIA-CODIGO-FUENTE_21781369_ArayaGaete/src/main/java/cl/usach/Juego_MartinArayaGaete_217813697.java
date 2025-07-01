package cl.usach;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.FileReader;

import java.io.IOException;

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

    private boolean terminado = false;
    public boolean isTerminado() {
        return terminado;
    }
    private void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

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
     * RF11. (0.1 pts) Cargar datos iniciales del juego.
     *
     * Este metodo inicializa el tablero agregando 10 cartas de Suerte y
     * 10 de Comunidad con temáticas chilenas. Las cartas se almacenan en las
     * estructuras del tablero para ser usadas durante el juego.
     *
     * No requiere parámetros ni retorna valores. Es llamado automáticamente
     * al crear una nueva partida.
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
                "Te secuestra un venezolano! Piden 100$ por tu rescate", "PAGA 100"));
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
     * RF11. (0.1 pts) Carga inicial de casillas mezclando Propiedades y especiales.
     *
     * Este metodo inicializa el tablero agregando, en orden:
     *   - La casilla "Salida" id = 0.
     *   - Un bloque de propiedades de comunas chilenas, con precio y renta crecientes.
     *   - Una casilla “Comunidad” cada 3 propiedades.
     *   - Una casilla “Suerte” cada 4 propiedades.
     *   - Las casillas especiales finales: “Cárcel”, “Vas a la Cárcel”,
     *     “Estacionamiento Libre”, “Impuesto Municipal” e “Impuesto Verde”.
     *
     * No recibe parámetros ni devuelve valor.
     * Se invoca al crear una nueva partida para preparar el tablero.
     */
    private void cargarPropiedades() {
        int id = 0;
        // Salida
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                id++,
                "Salida",
                0, 0,
                new Jugador_MartinArayaGaete_217813697(-1, "Sistema"),
                "Salida"
        ));

        // Bloque de comunas (Propiedades)
        String[] comunas = {
                "Providencia", "Las Condes", "Ñuñoa", "Santiago Centro", "Vitacura",
                "La Reina", "Macul", "Peñalolén", "La Florida", "Maipú",
                "Puente Alto", "Recoleta", "Cerrillos", "Lo Barnechea", "Independencia"
        };
        for (int i = 0; i < comunas.length; i++) {
            tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                    id++,
                    comunas[i],
                    200 + i * 50,      // Precio creciente
                    20 + i * 5,        // Renta creciente
                    null,
                    "Propiedad"
            ));
            // Cada 3 propiedades inserto una carta comunidad o suerte
            if ((i + 1) % 3 == 0) {
                tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                        id++,
                        "Comunidad",
                        0, 0,
                        new Jugador_MartinArayaGaete_217813697(-1, "Sistema"),
                        "Comunidad"
                ));
            }
            if ((i + 2) % 4 == 0) {
                tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                        id++,
                        "Suerte",
                        0, 0,
                        new Jugador_MartinArayaGaete_217813697(-1, "Sistema"),
                        "Suerte"
                ));
            }
        }

        // Últimas casillas especiales restantes
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                id++, "Cárcel", 0, 0,
                new Jugador_MartinArayaGaete_217813697(-1, "Sistema"),
                "Carcel"
        ));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                id++, "Vas a la Cárcel", 0, 0,
                new Jugador_MartinArayaGaete_217813697(-1, "Sistema"),
                "Carcel"
        ));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                id++, "Estacionamiento Libre", 0, 0,
                new Jugador_MartinArayaGaete_217813697(-1, "Sistema"),
                "Estacionamiento"
        ));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                id++, "Impuesto Municipal", 0, 300,
                new Jugador_MartinArayaGaete_217813697(-1, "Sistema"),
                "Impuesto"
        ));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(
                id++, "Impuesto Verde", 0, 500,
                new Jugador_MartinArayaGaete_217813697(-1, "Sistema"),
                "Impuesto"
        ));
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


    /**
     * RF12. (0.1 pts) Agregar Propiedad al tablero.
     *
     * Este método recibe una instancia de Propiedad y la añade
     * a la lista de casillas del tablero, ampliando así el conjunto
     * de propiedades disponibles en la partida.
     *
     * @param propiedad la propiedad que se incorporará al tablero
     */
    public void agregarPropiedadAlTablero(Propiedad_MartinArayaGaete_217813697 propiedad) {
        tablero.agregarPropiedad(propiedad);
    }

    /**
     * RF13. (0.1 pts) Agregar Jugador a la partida.
     *
     * Este método crea un nuevo jugador con el nombre e ID proporcionados,
     * le asigna un capital inicial de 1500 si el banco dispone de fondos,
     * lo añade a la lista de jugadores y descuenta el capital del banco.
     * Si no hay suficiente dinero en el banco, muestra un mensaje de error.
     *
     * @param nombre el nombre del jugador a agregar
     * @param id     el identificador único del jugador
     */
    public void agregarJugador(String nombre, int id) {
        int capitalInicial = 1500;

        if (dineroBanco >= capitalInicial) {
            Jugador_MartinArayaGaete_217813697 jugador = new Jugador_MartinArayaGaete_217813697(id, nombre);
            jugadores.add(jugador);
            dineroBanco -= capitalInicial;
        } else {
            System.out.println("¡No hay suficiente dinero en el banco para crear otro jugador!");
        }
    }

    /**
     * RF14. (0.1 pts) Obtener Jugador Actual.
     *
     * Este método devuelve el jugador cuyo turno está en curso.
     * Si no hay jugadores en la partida, retorna null.
     *
     * @return el jugador actual o null si no existe ninguno
     */
    public Jugador_MartinArayaGaete_217813697 getJugadorActual() {
        if (jugadores.isEmpty()) {
            return null;
        }
        return jugadores.get(turnoActual);
    }

    /**
     * RF15. (0.1 pts) Lanzar dados.
     *
     * Este método simula el lanzamiento de N dados, obteniendo un valor
     * aleatorio entre 1 y 6 para cada uno, donde N es el número de dados
     * configurado en la partida.
     *
     * @return lista con los resultados de cada dado lanzado
     */
    public List<Integer> lanzarDados() {
        List<Integer> resultados = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numeroDados; i++) {
            int dado = random.nextInt(6) + 1; // Valor entre 1 y 6
            resultados.add(dado);
        }

        return resultados;
    }

    /**
     * RF16. (0.1 pts) Mover Jugador en el tablero.
     *
     * Este método desplaza al jugador con el ID indicado una cantidad de pasos
     * sobre el tablero, calculando su nueva posición de forma circular. Luego,
     * actualiza su posición y muestra un mensaje con la casilla alcanzada.
     *
     * @param idJugador el identificador del jugador a mover
     * @param pasos     la cantidad de casillas que debe avanzar
     */
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

    /**
     * RF17. (0.1 pts) Comprar Propiedad.
     *
     * Este método permite a un jugador adquirir una propiedad si cumple con
     * las condiciones: que la propiedad no tenga dueño, no esté hipotecada
     * y el jugador tenga suficiente dinero. Si la compra es exitosa, se
     * descuenta el dinero del jugador, se le asigna la propiedad y se
     * muestra un mensaje de confirmación.
     *
     * @param jugador    el jugador que desea comprar la propiedad
     * @param propiedad  la propiedad que se desea adquirir
     * @return true si la compra fue exitosa; false en caso contrario
     */
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

    /**
     * RF18. (0.1 pts) Calcular Renta de Propiedad.
     *
     * Este método calcula la renta que debe pagar un jugador al caer en una propiedad,
     * considerando si tiene casas o un hotel. Si la propiedad está hipotecada o no tiene
     * dueño válido, la renta es cero. Si tiene casas, la renta aumenta un 20% por cada una.
     * Si es un hotel, la renta es el doble de la renta máxima con casas.
     *
     * @param propiedad la propiedad cuya renta se desea calcular
     * @return el monto total de renta a pagar
     */
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

    /**
     * RF19. (0.1 pts) Calcular Renta del Jugador.
     *
     * Este método calcula el total de renta generada por todas las propiedades
     * que posee un jugador, aplicando una tasa de impuesto sobre la suma.
     * Reutiliza el cálculo de renta individual definido en RF18.
     *
     * @param jugador el jugador cuyas propiedades se evaluarán
     * @return el monto total de renta con impuesto aplicado
     */
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

    /**
     * RF20. (0.2 pts) Construir Hotel en una Propiedad.
     *
     * Este método permite a un jugador construir un hotel en una propiedad
     * si cumple con las condiciones: ser el dueño, que la propiedad no tenga
     * ya un hotel y que posea el número máximo de casas requerido. Al construir
     * el hotel, se eliminan las casas existentes y se actualiza el estado de la propiedad.
     *
     * @param jugador   el jugador que desea construir el hotel
     * @param propiedad la propiedad sobre la cual se construirá el hotel
     * @return true si la construcción fue exitosa; false en caso contrario
     */
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

    /**
     * RF20.1. (? pts) Construir Casa en una Propiedad.
     *
     * Este método permite a un jugador construir una casa en una propiedad
     * si cumple con las condiciones: ser el dueño, que la propiedad no tenga
     * un hotel, que no haya alcanzado el máximo de casas y que el jugador
     * tenga suficiente dinero. El costo de la casa es un tercio del precio
     * de la propiedad. Si se construye con éxito, se actualiza la cantidad
     * de casas y se descuenta el dinero correspondiente.
     *
     * @param jugador   el jugador que desea construir la casa
     * @param propiedad la propiedad sobre la cual se construirá la casa
     * @return true si la construcción fue exitosa; false en caso contrario
     */
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

    /**
     * RF21. (0.2 pts) Pagar Renta entre jugadores.
     *
     * Este método permite que un jugador pague la renta correspondiente al dueño
     * de una propiedad. Si el jugador no tiene suficiente dinero, se declara en
     * bancarrota: pierde todas sus propiedades, las cuales se transfieren al dueño,
     * y su dinero se reduce a cero. Si tiene fondos suficientes, se realiza el pago
     * y se actualizan los saldos de ambos jugadores.
     *
     * @param quienPaga el jugador que debe pagar la renta
     * @param propiedad la propiedad que genera la renta
     * @return true si el pago se realizó con éxito; false si no se pagó o hubo bancarrota
     */
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

    /**
     * RF22. (0.2 pts) Hipotecar Propiedad.
     *
     * Este método permite a un jugador hipotecar una propiedad de su propiedad,
     * siempre que no esté ya hipotecada. Al hacerlo, recibe un préstamo equivalente
     * a la renta calculada de la propiedad, y esta queda marcada como hipotecada.
     *
     * @param jugador   el jugador que desea hipotecar la propiedad
     * @param propiedad la propiedad que se desea hipotecar
     * @return true si la hipoteca fue realizada con éxito; false en caso contrario
     */
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

    /**
     * RF22.1. (? pts) Deshipotecar Propiedad.
     *
     * Este método permite a un jugador deshipotecar una propiedad que le pertenece,
     * siempre que esté hipotecada y tenga suficiente dinero para pagar la penalización,
     * que equivale al doble de la renta calculada de la propiedad. Si se cumple,
     * se descuenta el dinero y se actualiza el estado de la propiedad.
     *
     * @param jugador   el jugador que desea deshipotecar la propiedad
     * @param propiedad la propiedad que se desea liberar de hipoteca
     * @return true si la deshipoteca fue exitosa; false en caso contrario
     */
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


    /**
     * RF24. (0.1 pts) Verificar bancarrota.
     *
     * Este método determina si un jugador se encuentra en bancarrota,
     * es decir, si su dinero disponible es menor o igual a cero.
     *
     * @param jugador el jugador cuya situación financiera se evaluará
     * @return true si el jugador está en bancarrota; false en caso contrario
     */
    public boolean verificarBancarrota(Jugador_MartinArayaGaete_217813697 jugador) {
        return jugador.getDinero() <= 0;
    }

    /**
     * Inicializa una nueva partida de CAPITALIA.
     *
     * Este método interactúa con el usuario mediante consola para configurar
     * la partida: solicita la cantidad de jugadores (mínimo 2), sus nombres
     * y la cantidad de dados por turno (entre 1 y 4). Luego, crea una instancia
     * del juego, agrega los jugadores, configura los dados y deja el turno
     * inicial en cero.
     *
     * @param sc el escáner utilizado para leer entradas desde consola
     * @return una instancia de Juego_MartinArayaGaete_217813697 lista para comenzar
     */
    public static Juego_MartinArayaGaete_217813697 crearPartida(Scanner sc) {
        Juego_MartinArayaGaete_217813697 juego = new Juego_MartinArayaGaete_217813697();

        int n;
        while (true) {
            System.out.print("¿Cuántos jugadores? (mínimo 2): ");
            try {
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < 2) {
                    System.out.println("¡Oye compadre! Al menos tienen que ser 2 jugadores.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número válido. Intenta de nuevo.");
            }
        }

        for (int i = 1; i <= n; i++) {
            String nombre;
            do {
                System.out.print("Nombre jugador " + i + ": ");
                nombre = sc.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.out.println("No podís dejar el nombre en blanco, po.");
                }
            } while (nombre.isEmpty());

            juego.agregarJugador(nombre, i);
        }

        int dados;
        while (true) {
            System.out.print("¿Cuántos dados por turno? (1-4): ");
            try {
                dados = Integer.parseInt(sc.nextLine().trim());
                if (dados < 1 || dados > 4) {
                    System.out.println("¿Y voh querí tirar " + dados + " dados? No po... elige entre 1 y 4.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número válido. Intenta de nuevo.");
            }
        }

        juego.setNumeroDados(dados);
        juego.setTurnoActual(0);

        System.out.println("Partida creada. ¡A jugar!");
        return juego;
    }

    /**
     * RF25. (1 pts) Jugar Turno completo.
     *
     * Este método ejecuta un turno completo para el jugador actual, aplicando
     * todas las reglas del juego: verifica bancarrota, maneja la lógica de cárcel,
     * lanza los dados, mueve al jugador, procesa casillas especiales (como Suerte,
     * Comunidad, Impuestos o Cárcel), permite comprar propiedades o pagar renta,
     * y gestiona el avance del turno. También transfiere al banco el dinero acumulado
     * por el jugador "Sistema".
     *
     * @param sc el escáner utilizado para leer decisiones del jugador desde consola
     */
    public void jugarTurno(Scanner sc) {
        Jugador_MartinArayaGaete_217813697 jugador = getJugadorActual();
        System.out.println("\n--- Turno de " + jugador.getNombre() + " ---");

        // 0) ¿Está en bancarrota? -> salta turno
        if (verificarBancarrota(jugador)) {
            System.out.println("¡" + jugador.getNombre() + " está en bancarrota y pierde su turno!");
            avanzarYVerificarFin();
            return;
        }

        // Si está en la cárcel
        if (jugador.isEstaEnCarcel()) {
            System.out.println("Estás en la cárcel.");

            // Ofrecer usar carta de salida si tiene
            if (jugador.getTotalCartasSalirCarcel() > 0) {
                System.out.print("¿Usar carta \"Salir de la cárcel\"? (S/N): ");
                if (sc.nextLine().trim().equalsIgnoreCase("S")) {
                    jugador.setEstaEnCarcel(false);
                    jugador.decrementarCartaSalirCarcel();
                    System.out.println("¡Has salido de la cárcel usando la carta!");
                    // continúa con turno normal
                } else {
                    System.out.println("Decidiste no usar carta.");
                }
            }

            // Tirar dados para intentar salir
            List<Integer> resultados = lanzarDados();
            System.out.print("Dados: ");
            resultados.forEach(d -> System.out.print(d + " "));
            System.out.println();

            // ¿Sacos dobles/triples?
            boolean todosIguales = resultados.stream().distinct().count() == 1;
            if (todosIguales) {
                jugador.setContadorCarcel(jugador.getContadorCarcel() - 1);
                System.out.println("¡Dobles! vas por " + jugador.getContadorCarcel() + " seguidas y repites turno.");
                if (jugador.getContadorCarcel() <= -3) {
                    jugador.setEstaEnCarcel(false);
                    System.out.println("¡Lograste salir de la cárcel por perseverancia!");
                } else {
                    // repite turno sin mover
                    return;
                }
            } else {
                jugador.setContadorCarcel(0);
                System.out.println("No sacaste dobles.");
                // fin de turno en cárcel: solo puede hipotecar pero no mover
                turnoActual = (turnoActual + 1) % jugadores.size();
                System.out.println("Turno para: " + jugadores.get(turnoActual).getNombre());
                return;
            }
        }

        // --- FUERA DE LA CÁRCEL ---
        List<Integer> resultados = lanzarDados();
        System.out.print("Dados: ");
        resultados.forEach(d -> System.out.print(d + " "));
        System.out.println();

        // 2) Mover y pagar impuestos al pasar Salida
        int pasos = resultados.stream().mapToInt(Integer::intValue).sum();
        int posInicial = jugador.getPosicionActual();
        int tamañoTablero = tablero.getPropiedades().size();
        boolean pasoSalida = (posInicial + pasos) >= tamañoTablero;
        moverJugador(jugador.getId(), pasos);
        if (pasoSalida) {
            int impuestos = calcularRentaJugador(jugador);
            jugador.decrementarDinero(impuestos);
            System.out.println("Pasaste por Salida y pagaste impuestos de $" + impuestos + ".");

            // RF‑XX: liberar a TODOS los jugadores que estén en cárcel
            for (Jugador_MartinArayaGaete_217813697 j : jugadores) {
                if (j.isEstaEnCarcel()) {
                    j.setEstaEnCarcel(false);
                    System.out.println(">> " + j.getNombre()
                            + " sale de la cárcel porque alguien cruzó por Salida.");
                }
            }
        }
        // 3) Detectar “n”-ples iguales (dobles/triples/cuádruples…)
        boolean todosIguales = resultados.stream().distinct().count() == 1;
        if (todosIguales) {
            // Incrementa contador positivo
            jugador.setContadorCarcel(jugador.getContadorCarcel() + 1);
            System.out.println("¡Sacaste dados iguales! Contador cárcel = " + jugador.getContadorCarcel());

            if (jugador.getContadorCarcel() >= 3) {
                // Tres veces sacaste dobles: vas a la cárcel
                System.out.println("¡Tres dobles consecutivos! Vas directo a la cárcel.");
                jugador.setEstaEnCarcel(true);
                jugador.setPosicionActual(tablero.getIndiceCarcel());
                jugador.setContadorCarcel(0);
                // pasa al siguiente jugador
                turnoActual = (turnoActual + 1) % jugadores.size();
                System.out.println("Turno para: " + jugadores.get(turnoActual).getNombre());
                return;
            } else {
                // repites turno: NO incrementamos turnoActual aquí
                System.out.println("Repite turno (avanzaste y juegas de nuevo).");
                return;
            }
        }

        // 4) Si no sacaste todos iguales, resetea el contador de dobles
        jugador.setContadorCarcel(0);

        // 3) Procesar casilla destino
        int pos = jugador.getPosicionActual();
        Propiedad_MartinArayaGaete_217813697 casilla = tablero.getPropiedades().get(pos);
        System.out.println("Caíste en casilla [" + pos + "]: " + casilla.getNombre());

        // 4) Casillas especiales
        String tipo = casilla.getTipo();
        boolean especial = true;
        switch (tipo) {
            case "Impuesto":
                System.out.println("Debes pagar impuesto de $" + casilla.getRenta());
                pagarRenta(jugador, casilla);
                break;
            case "Suerte":
                CartaSuerte_MartinArayaGaete_217813697 cS = tablero.extraerCartaSuerte();
                if (cS != null) cS.ejecutarAccion(this);
                break;
            case "Comunidad":
                CartaComunidad_MartinArayaGaete_217813697 cC = tablero.extraerCartaComunidad();
                if (cC != null) cC.ejecutarAccion(this);
                break;
            case "Carcel":
                System.out.println("¡Estas de vísita en la carcel!");
                break;
            case "Vas a la Cárcel":
                System.out.println("¡Vas directo a la cárcel!");
                jugador.setEstaEnCarcel(true);
                jugador.setPosicionActual(tablero.getIndiceCarcel());  // manda a la casilla cárcel
                jugador.setContadorCarcel(0);  // reinicia contador
                break;
            default:
                especial = false;
        }

        // 5) Propiedades normales (compra/renta)
        if (!especial && casilla.getPrecio() > 0) {
            try {
                if (casilla.getDueño() == null) {
                    System.out.print("¿Comprar \"" + casilla.getNombre() + "\" por $" + casilla.getPrecio() + "? (S/N): ");
                    String resp = sc.nextLine().trim();
                    if (resp.equalsIgnoreCase("S")) {
                        comprarPropiedad(jugador, casilla);
                    } else {
                        System.out.println("No compraste la propiedad.");
                    }
                } else if (casilla.getDueño() != jugador) {
                    pagarRenta(jugador, casilla);
                }
            } catch (Exception e) {
                System.out.println("Error procesando propiedad: " + e.getMessage());
            }
        }

        // 6) Avanzar turno
        turnoActual = (turnoActual + 1) % jugadores.size();
        System.out.println("Turno para: " + jugadores.get(turnoActual).getNombre());

        //Transfiere todo_ lo que el “Sistema” (jugador con ID = -1) haya cobrado al banco del juego
        Jugador_MartinArayaGaete_217813697 sistema = jugadores.stream()
                .filter(j -> j.getId() == -1)
                .findFirst()
                .orElse(null);
        if (sistema != null) {
            int ganancia = sistema.getDinero();
            sistema.setDinero(0);
            this.dineroBanco += ganancia;
            System.out.println("Se transfirieron $" + ganancia + " del Sistema al banco del juego.");
        }

    }

    private void avanzarYVerificarFin() {
        // Avanza al siguiente jugador
        turnoActual = (turnoActual + 1) % jugadores.size();

        // Comprueba si solo queda uno sin bancarrota
        long activos = jugadores.stream()
                .filter(p -> !verificarBancarrota(p))
                .count();
        if (activos == 1) {
            Jugador_MartinArayaGaete_217813697 ganador = jugadores.stream()
                    .filter(p -> !verificarBancarrota(p))
                    .findFirst().get();
            System.out.println("\n¡El juego ha terminado! El ganador es " + ganador.getNombre() + " 🎉🤑🤑🤑");
            // **Marca el juego como terminado**
            setTerminado(true);
        } else {
            System.out.println("Turno para: " + jugadores.get(turnoActual).getNombre());
        }
    }

    /**
     * RF27. (2 pts) Exportar partida actual a un archivo.
     *
     * Este método permite guardar el estado actual de la partida en un archivo
     * JSON en la ruta especificada. Utiliza la biblioteca Gson para serializar
     * el objeto del juego con formato legible. Si ocurre un error de escritura,
     * se muestra un mensaje informativo.
     *
     * @param ruta la ruta del archivo donde se exportará la partida
     */
    public void exportar(String ruta) {
        try (FileWriter writer = new FileWriter(ruta)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
            System.out.println("Partida exportada a: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al exportar partida: " + e.getMessage());
        }
    }

    /**
     * RF28. (2 pts) Importar partida desde un archivo.
     *
     * Este método permite cargar una partida previamente guardada desde un archivo
     * JSON ubicado en la ruta especificada. Utiliza la biblioteca Gson para
     * deserializar el contenido y reconstruir el objeto del juego. Si ocurre un
     * error de lectura, se muestra un mensaje y se retorna null.
     *
     * @param ruta la ruta del archivo desde el cual se importará la partida
     * @return una instancia de Juego_MartinArayaGaete_217813697 cargada desde el archivo, o null si falla
     */
    public static Juego_MartinArayaGaete_217813697 importar(String ruta) {
        try (FileReader reader = new FileReader(ruta)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Juego_MartinArayaGaete_217813697.class);
        } catch (IOException e) {
            System.out.println("Error al importar partida: " + e.getMessage());
            return null;
        }
    }
}
