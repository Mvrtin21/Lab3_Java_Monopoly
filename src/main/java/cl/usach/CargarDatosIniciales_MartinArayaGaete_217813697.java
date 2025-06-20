package cl.usach;

/**
 * Clase responsable de cargar cartas y propiedades iniciales en el tablero del juego CAPITALIA.
 */
public class CargarDatosIniciales_MartinArayaGaete_217813697 {

    public static void cargarCartas(Tablero_MartinArayaGaete_217813697 tablero) {
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(1, "Es tu cumpleaños en Santiago!", "RECIBE 200"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(2, "Caos en la Costanera Norte", "PAGA 100"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(3, "Se cortó la luz en Providencia", "PAGA 50"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(4, "Te gana la micro: llegas tarde", "PAGA 150"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(5, "Encuentras luca en el suelo", "RECIBE 100"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(6, "Tienes que pagar el TAG", "PAGA 120"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(7, "Asado en la casa de un amigo", "PAGA 80"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(8, "Recibes premio de loto", "RECIBE 500"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(9, "Multa por mal estacionarte", "PAGA 200"));
        tablero.agregarCartaSuerte(new CartaSuerte_MartinArayaGaete_217813697(10, "Te traga bus sin vuelto", "PAGA 70"));

        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(1, "Te secuestra un venezolano! Pierdes un turno", "CARCEL"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(2, "Recibe subvención estatal", "RECIBE 300"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(3, "Inauguraron Metro de Rancagua", "AVANZA 3"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(4, "Rompes la alcancía", "PAGA 50"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(5, "Pagas matrícula universitaria", "PAGA 100"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(6, "Encuentras trabajo part-time", "RECIBE 150"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(7, "Junta de vecinos: gastas onces", "PAGA 30"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(8, "Bono por buen contribuyente", "RECIBE 200"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(9, "Inflación repentina", "PAGA 120"));
        tablero.agregarCartaComunidad(new CartaComunidad_MartinArayaGaete_217813697(10, "Donas a la Teletón", "PAGA 100"));
    }

    public static void cargarPropiedades(Tablero_MartinArayaGaete_217813697 tablero) {
        int id = 1;
        String[] comunas = {"Providencia", "Las Condes", "Ñuñoa", "Santiago Centro", "Vitacura",
                "La Reina", "Macul", "Peñalolén", "La Florida", "Maipú",
                "Puente Alto", "Recoleta", "Cerrillos", "Lo Barnechea", "Independencia"};

        for (String comuna : comunas) {
            tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, comuna, 200 + (id * 50), 20 + (id * 5), null));
        }

        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, "Salida", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, "Comunidad", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, "Suerte", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, "Cárcel", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, "Vas a la Cárcel", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, "Estacionamiento Libre", 0, 0, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, "Impuesto Municipal", 0, 300, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
        tablero.agregarPropiedad(new Propiedad_MartinArayaGaete_217813697(id++, "Impuesto Verde", 0, 500, new Jugador_MartinArayaGaete_217813697(-1, "Sistema", 0)));
    }
}
