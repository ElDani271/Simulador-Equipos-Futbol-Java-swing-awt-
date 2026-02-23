import java.util.ArrayList;

import biblioteca.Data;
import biblioteca.Equipo;
import biblioteca.Grupo;
import biblioteca.Interfaz;

public class App {
    
    public static void main(String[] args) {
        // Definir arreglo de 10 equipos con sus atributos iniciales
        //Lista "equipos": 10 equipos de la conmebol
        Grupo gr = new Grupo("CONMEBOL", new ArrayList<>());
        gr.getEquipoList().add(new Equipo("Argentina", 0,2));
        gr.getEquipoList().add(new Equipo("Bolivia", 0,76));
        gr.getEquipoList().add(new Equipo("Brasil", 0,5));
        gr.getEquipoList().add(new Equipo("Chile", 0,55));
        gr.getEquipoList().add(new Equipo("Colombia", 0,14));
        gr.getEquipoList().add(new Equipo("Ecuador", 0,23));
        gr.getEquipoList().add(new Equipo("Paraguay", 0,40));
        gr.getEquipoList().add(new Equipo("Perú", 0,53));
        gr.getEquipoList().add(new Equipo("Uruguay", 0,17));
        gr.getEquipoList().add(new Equipo("Venezuela", 0,50));

        gr.pantalla = new Interfaz(gr);

        //Lista "equipos2": 4 equipos, simulacion de un grupo en el mundial
        
        // Agregar equipos a Data
        for (Equipo equipo : gr.getEquipoList()) {//CAMBIAR "equipos" POR "equipos2" PARA CORRER EL PROGRAMA CON LOS EQUIPOS DE LA LISTA 2, TAMBIEN CAMBIAR EN LINEA 33
            Data.getTablaEquipos().add(equipo);
        }
        // Inicializar 18 fechas en Data.calendarioList
        gr.generarPartidos(); //CAMBIAR "equipos" POR "equipos2" PARA CORRER EL PROGRAMA CON LOS EQUIPOS DE LA LISTA 2 Y CAMBIAR "generarPartidos" POR "generarPartidosRand" PARA EVITAR ERRORES
        gr.pantalla.setVisible(true);
    }
}
