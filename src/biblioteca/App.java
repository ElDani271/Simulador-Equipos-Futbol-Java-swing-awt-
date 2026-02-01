package biblioteca;


public class App {
    
    public static void main(String[] args) {
        // Definir arreglo de 10 equipos con sus atributos iniciales
        //Lista "equipos": 10 equipos de la conmebol
        Equipo[] equipos = new Equipo[10];
        equipos[0] = new Equipo("Argentina", 0);
        equipos[1] = new Equipo("Bolivia", 0);
        equipos[2] = new Equipo("Brasil", 0);
        equipos[3] = new Equipo("Chile", 0);
        equipos[4] = new Equipo("Colombia", 0);
        equipos[5] = new Equipo("Ecuador", 0);
        equipos[6] = new Equipo("Paraguay", 0);
        equipos[7] = new Equipo("Perú", 0);
        equipos[8] = new Equipo("Uruguay", 0);
        equipos[9] = new Equipo("Venezuela", 0);

        //Lista "equipos2": 4 equipos, simulacion de un grupo en el mundial
        Equipo[] equipos2 = new Equipo[4];
        equipos2[0] = (new Equipo("Portugal", 0));
        equipos2[1] = (new Equipo("Colombia", 0));
        equipos2[2] = (new Equipo("Uzbekistan", 0));
        equipos2[3] = (new Equipo("Jamaica", 0));
        
        // Agregar equipos a Data
        for (Equipo equipo : equipos) {//CAMBIAR "equipos" POR "equipos2" PARA CORRER EL PROGRAMA CON LOS EQUIPOS DE LA LISTA 2, TAMBIEN CAMBIAR EN LINEA 33
            Data.getTablaEquipos().add(equipo);
        }
        // Inicializar 18 fechas en Data.calendarioList
        Bib.generarPartidos(Data.getCalendarioList(), equipos); //CAMBIAR "equipos" POR "equipos2" PARA CORRER EL PROGRAMA CON LOS EQUIPOS DE LA LISTA 2 Y CAMBIAR "generarPartidos" POR "generarPartidosRand" PARA EVITAR ERRORES
        Interfaz.generarInterfaz(Data.getTablaEquipos());
    }
}
