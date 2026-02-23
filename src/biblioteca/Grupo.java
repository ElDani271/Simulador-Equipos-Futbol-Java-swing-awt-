package biblioteca;

import java.util.ArrayList;
import java.util.Collections;

public class Grupo {
    private String nombre;
    private ArrayList<Equipo> equipoList = new ArrayList<>();
    private ArrayList<ArrayList<Duelo>> calendarioList = new ArrayList<>();
    public Interfaz pantalla = new Interfaz();
    

    public ArrayList<ArrayList<Duelo>> getCalendarioList() {
        return calendarioList;
    }
    public void setCalendarioList(ArrayList<ArrayList<Duelo>> calendarioList) {
        this.calendarioList = calendarioList;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ArrayList<Equipo> getEquipoList() {
        return equipoList;
    }
    public void setEquipoList(ArrayList<Equipo> equipoList) {
        this.equipoList = equipoList;
    }
    public Grupo(String nombre, ArrayList<Equipo> equipoList) {
        this.nombre = nombre;
        this.equipoList = equipoList;
    }

    public void generarPartidos() {
        for (int i = 0; i < 18; i++) {
            calendarioList.add(new ArrayList<Duelo>());
        }
        /* Fecha 1 */
        calendarioList.get(0).add(new Duelo(equipoList.get(6), equipoList.get(7))); // Paraguay - Peru
        calendarioList.get(0).add(new Duelo(equipoList.get(4), equipoList.get(9))); // Colombia - Venezuela
        calendarioList.get(0).add(new Duelo(equipoList.get(0), equipoList.get(5))); // Argentina - Ecuador
        calendarioList.get(0).add(new Duelo(equipoList.get(8), equipoList.get(3))); // Uruguay - Chile
        calendarioList.get(0).add(new Duelo(equipoList.get(2), equipoList.get(1))); // Brasil - Bolivia

        /* Fecha 2 */
        calendarioList.get(1).add(new Duelo(equipoList.get(1), equipoList.get(0))); // Bolivia - Argentina
        calendarioList.get(1).add(new Duelo(equipoList.get(5), equipoList.get(8))); // Ecuador - Uruguay
        calendarioList.get(1).add(new Duelo(equipoList.get(9), equipoList.get(6))); // Venezuela - Paraguay
        calendarioList.get(1).add(new Duelo(equipoList.get(3), equipoList.get(4))); // Chile - Colombia
        calendarioList.get(1).add(new Duelo(equipoList.get(7), equipoList.get(2))); // Peru - Brasil

        /* Fecha 3 */
        calendarioList.get(2).add(new Duelo(equipoList.get(4), equipoList.get(8))); // Colombia - Uruguay
        calendarioList.get(2).add(new Duelo(equipoList.get(2), equipoList.get(9))); // Brasil - Venezuela
        calendarioList.get(2).add(new Duelo(equipoList.get(1), equipoList.get(5))); // Bolivia - Ecuador
        calendarioList.get(2).add(new Duelo(equipoList.get(0), equipoList.get(6))); // Argentina - Paraguay
        calendarioList.get(2).add(new Duelo(equipoList.get(3), equipoList.get(7))); // Chile - Peru

        /* Fecha 4 */
        calendarioList.get(3).add(new Duelo(equipoList.get(9), equipoList.get(3))); // Venezuela - Chile
        calendarioList.get(3).add(new Duelo(equipoList.get(6), equipoList.get(1))); // Paraguay - Bolivia
        calendarioList.get(3).add(new Duelo(equipoList.get(5), equipoList.get(4))); // Ecuador - Colombia
        calendarioList.get(3).add(new Duelo(equipoList.get(8), equipoList.get(2))); // Uruguay - Brasil
        calendarioList.get(3).add(new Duelo(equipoList.get(7), equipoList.get(0))); // Peru - Argentina

        /* Fecha 5 */
        calendarioList.get(4).add(new Duelo(equipoList.get(1), equipoList.get(7))); // Bolivia - Peru
        calendarioList.get(4).add(new Duelo(equipoList.get(9), equipoList.get(5))); // Venezuela - Ecuador
        calendarioList.get(4).add(new Duelo(equipoList.get(4), equipoList.get(2))); // Colombia - Brasil
        calendarioList.get(4).add(new Duelo(equipoList.get(0), equipoList.get(8))); // Argentina - Uruguay
        calendarioList.get(4).add(new Duelo(equipoList.get(3), equipoList.get(6))); // Chile - Paraguay

        /* Fecha 6 */
        calendarioList.get(5).add(new Duelo(equipoList.get(6), equipoList.get(4))); // Paraguay - Colombia
        calendarioList.get(5).add(new Duelo(equipoList.get(5), equipoList.get(3))); // Ecuador - Chile
        calendarioList.get(5).add(new Duelo(equipoList.get(8), equipoList.get(1))); // Uruguay - Bolivia
        calendarioList.get(5).add(new Duelo(equipoList.get(2), equipoList.get(0))); // Brasil - Argentina
        calendarioList.get(5).add(new Duelo(equipoList.get(7), equipoList.get(9))); // Peru - Venezuela

        /* Fecha 7 */
        calendarioList.get(6).add(new Duelo(equipoList.get(1), equipoList.get(9))); // Bolivia - Venezuela
        calendarioList.get(6).add(new Duelo(equipoList.get(0), equipoList.get(3))); // Argentina - Chile
        calendarioList.get(6).add(new Duelo(equipoList.get(8), equipoList.get(6))); // Uruguay - Paraguay
        calendarioList.get(6).add(new Duelo(equipoList.get(2), equipoList.get(5))); // Brasil - Ecuador
        calendarioList.get(6).add(new Duelo(equipoList.get(7), equipoList.get(4))); // Peru - Colombia

        /* Fecha 8 */
        calendarioList.get(7).add(new Duelo(equipoList.get(4), equipoList.get(0))); // Colombia - Argentina
        calendarioList.get(7).add(new Duelo(equipoList.get(5), equipoList.get(7))); // Ecuador - Peru
        calendarioList.get(7).add(new Duelo(equipoList.get(3), equipoList.get(1))); // Chile - Bolivia
        calendarioList.get(7).add(new Duelo(equipoList.get(9), equipoList.get(8))); // Venezuela - Uruguay
        calendarioList.get(7).add(new Duelo(equipoList.get(6), equipoList.get(2))); // Paraguay - Brasil

        /* Fecha 9 */
        calendarioList.get(8).add(new Duelo(equipoList.get(1), equipoList.get(4))); // Bolivia - Colombia
        calendarioList.get(8).add(new Duelo(equipoList.get(9), equipoList.get(0))); // Venezuela - Argentina
        calendarioList.get(8).add(new Duelo(equipoList.get(5), equipoList.get(6))); // Ecuador - Paraguay
        calendarioList.get(8).add(new Duelo(equipoList.get(3), equipoList.get(2))); // Chile - Brasil
        calendarioList.get(8).add(new Duelo(equipoList.get(7), equipoList.get(8))); // Peru - Uruguay

        /* Fecha 10 */
        calendarioList.get(9).add(new Duelo(equipoList.get(4), equipoList.get(3))); // Colombia - Chile
        calendarioList.get(9).add(new Duelo(equipoList.get(6), equipoList.get(9))); // Paraguay - Venezuela
        calendarioList.get(9).add(new Duelo(equipoList.get(8), equipoList.get(5))); // Uruguay - Ecuador
        calendarioList.get(9).add(new Duelo(equipoList.get(0), equipoList.get(1))); // Argentina - Bolivia
        calendarioList.get(9).add(new Duelo(equipoList.get(2), equipoList.get(7))); // Brasil - Peru

        /* Fecha 11 */
        calendarioList.get(10).add(new Duelo(equipoList.get(9), equipoList.get(2))); // Venezuela - Brasil
        calendarioList.get(10).add(new Duelo(equipoList.get(6), equipoList.get(0))); // Paraguay - Argentina
        calendarioList.get(10).add(new Duelo(equipoList.get(5), equipoList.get(1))); // Ecuador - Bolivia
        calendarioList.get(10).add(new Duelo(equipoList.get(8), equipoList.get(4))); // Uruguay - Colombia
        calendarioList.get(10).add(new Duelo(equipoList.get(7), equipoList.get(3))); // Peru - Chile

        /* Fecha 12 */
        calendarioList.get(11).add(new Duelo(equipoList.get(1), equipoList.get(6))); // Bolivia - Paraguay
        calendarioList.get(11).add(new Duelo(equipoList.get(4), equipoList.get(5))); // Colombia - Ecuador
        calendarioList.get(11).add(new Duelo(equipoList.get(0), equipoList.get(7))); // Argentina - Peru
        calendarioList.get(11).add(new Duelo(equipoList.get(3), equipoList.get(9))); // Chile - Venezuela
        calendarioList.get(11).add(new Duelo(equipoList.get(2), equipoList.get(8))); // Brasil - Uruguay

        /* Fecha 13 */
        calendarioList.get(12).add(new Duelo(equipoList.get(8), equipoList.get(0))); // Uruguay - Argentina
        calendarioList.get(12).add(new Duelo(equipoList.get(7), equipoList.get(1))); // Peru - Bolivia
        calendarioList.get(12).add(new Duelo(equipoList.get(2), equipoList.get(4))); // Brasil - Colombia
        calendarioList.get(12).add(new Duelo(equipoList.get(6), equipoList.get(3))); // Paraguay - Chile
        calendarioList.get(12).add(new Duelo(equipoList.get(5), equipoList.get(9))); // Ecuador - Venezuela

        /* Fecha 14 */
        calendarioList.get(13).add(new Duelo(equipoList.get(1), equipoList.get(8))); // Bolivia - Uruguay
        calendarioList.get(13).add(new Duelo(equipoList.get(4), equipoList.get(6))); // Colombia - Paraguay
        calendarioList.get(13).add(new Duelo(equipoList.get(9), equipoList.get(7))); // Venezuela - Peru
        calendarioList.get(13).add(new Duelo(equipoList.get(0), equipoList.get(2))); // Argentina - Brasil
        calendarioList.get(13).add(new Duelo(equipoList.get(3), equipoList.get(5))); // Chile - Ecuador

        /* Fecha 15 */
        calendarioList.get(14).add(new Duelo(equipoList.get(4), equipoList.get(7))); // Colombia - Peru
        calendarioList.get(14).add(new Duelo(equipoList.get(9), equipoList.get(1))); // Venezuela - Bolivia
        calendarioList.get(14).add(new Duelo(equipoList.get(6), equipoList.get(8))); // Paraguay - Uruguay
        calendarioList.get(14).add(new Duelo(equipoList.get(3), equipoList.get(0))); // Chile - Argentina
        calendarioList.get(14).add(new Duelo(equipoList.get(5), equipoList.get(2))); // Ecuador - Brasil

        /* Fecha 16 */
        calendarioList.get(15).add(new Duelo(equipoList.get(8), equipoList.get(9))); // Uruguay - Venezuela
        calendarioList.get(15).add(new Duelo(equipoList.get(7), equipoList.get(5))); // Peru - Ecuador
        calendarioList.get(15).add(new Duelo(equipoList.get(2), equipoList.get(6))); // Brasil - Paraguay
        calendarioList.get(15).add(new Duelo(equipoList.get(1), equipoList.get(3))); // Bolivia - Chile
        calendarioList.get(15).add(new Duelo(equipoList.get(0), equipoList.get(4))); // Argentina - Colombia

        /* Fecha 17 */
        calendarioList.get(16).add(new Duelo(equipoList.get(8), equipoList.get(7))); // Uruguay - Peru
        calendarioList.get(16).add(new Duelo(equipoList.get(4), equipoList.get(1))); // Colombia - Bolivia
        calendarioList.get(16).add(new Duelo(equipoList.get(2), equipoList.get(3))); // Brasil - Chile
        calendarioList.get(16).add(new Duelo(equipoList.get(6), equipoList.get(5))); // Paraguay - Ecuador
        calendarioList.get(16).add(new Duelo(equipoList.get(0), equipoList.get(9))); // Argentina - Venezuela

        /* Fecha 18 */
        calendarioList.get(17).add(new Duelo(equipoList.get(7), equipoList.get(6))); // Peru - Paraguay
        calendarioList.get(17).add(new Duelo(equipoList.get(9), equipoList.get(4))); // Venezuela - Colombia
        calendarioList.get(17).add(new Duelo(equipoList.get(1), equipoList.get(2))); // Bolivia - Brasil
        calendarioList.get(17).add(new Duelo(equipoList.get(3), equipoList.get(8))); // Chile - Uruguay
        calendarioList.get(17).add(new Duelo(equipoList.get(5), equipoList.get(0))); // Ecuador - Argentina

    }

    public static void ordenarEquiposBurbuja(ArrayList<Equipo> equipos) {
        int n = equipos.size();
        boolean intercambiado;
        
        for (int i = 0; i < n - 1; i++) {
            intercambiado = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                Equipo equipo1 = equipos.get(j);
                Equipo equipo2 = equipos.get(j + 1);
                
                // Comparar por puntos (descendente)
                if (equipo1.getPuntos() < equipo2.getPuntos()) {
                    Collections.swap(equipos, j, j + 1);
                    intercambiado = true;
                } 
                // Si hay empate en puntos
                else if (equipo1.getPuntos() == equipo2.getPuntos()) {
                    
                    // Comparar por diferencia de goles (descendente)
                    if (equipo1.getDifGol() < equipo2.getDifGol()) {
                        Collections.swap(equipos, j, j + 1);
                        intercambiado = true;
                    }
                    // Si hay empate en diferencia de goles
                    else if (equipo1.getDifGol() == equipo2.getDifGol()) {
                        
                        // Comparar por goles a favor (descendente)
                        if (equipo1.getGolesFavor() < equipo2.getGolesFavor()) {
                            Collections.swap(equipos, j, j + 1);
                            intercambiado = true;
                        }
                        
                    }
                }
            }
            
            // Si no hubo intercambios, el array ya está ordenado
            if (!intercambiado) {
                break;
            }
        }
    }

    @Override
    public ArrayList<Equipo> clone(){
        ArrayList<Equipo> listCopy = new ArrayList<>();
        for (int i = 0; i < this.equipoList.size(); i++) {
            listCopy.add(new Equipo(this.equipoList.get(i).getNombre() + (" (copy)"), 0, this.equipoList.get(i).getConfederacion()));
        }
        return listCopy;
    }
    
    
}
