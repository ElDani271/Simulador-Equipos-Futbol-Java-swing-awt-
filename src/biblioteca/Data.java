package biblioteca;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Equipo> tablaEquipos = new ArrayList<>();
    private static ArrayList<Equipo> equipoList = new ArrayList<>();
    private static ArrayList<ArrayList<Duelo>> calendarioList = new ArrayList<>();
    public static ArrayList<Equipo> getTablaEquipos() {
        return tablaEquipos;
    }
    public static void setTablaEquipos(ArrayList<Equipo> tablaEquipos) {
        Data.tablaEquipos = tablaEquipos;
    }
    public static ArrayList<Equipo> getEquipoList() {
        return equipoList;
    }
    public static void setEquipoList(ArrayList<Equipo> equipoList) {
        Data.equipoList = equipoList;
    }
    public static ArrayList<ArrayList<Duelo>> getCalendarioList() {
        return calendarioList;
    }
    public static void setCalendarioList(ArrayList<ArrayList<Duelo>> calendarioList) {
        Data.calendarioList = calendarioList;
    }
    
    
}
