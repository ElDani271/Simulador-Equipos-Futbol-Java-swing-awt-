package biblioteca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class Bib {
    public static int buscarIndiceEquipo(String nombre, ArrayList<Equipo> equipos) { //nombre del equipo que juega, se compara los nombres de los equipos registrados y se encuentra su índice
        for (int i = 0; i < equipos.size(); i++) {
            if (nombre == equipos.get(i).getNombre()) { // comparar contenido del String
                return i;
            }
        }
        return -1; // si no lo encuentra
    }

    public static void asignarGolesADuelo(Equipo local, Equipo visit, int fecha, int golesL, int golesV) { //nombre del equipo que juega, se compara los nombres de los equipos registrados y se encuentra su índice
        for (int i = 0; i < Data.getCalendarioList().get(fecha).size(); i++) {
            if (Data.getCalendarioList().get(fecha).get(i).getlocal() == local && Data.getCalendarioList().get(fecha).get(i).getvisitante() == visit) {
                int indice = i;
                Data.getCalendarioList().get(fecha).get(indice).setgLoc(golesL);
                Data.getCalendarioList().get(fecha).get(indice).setgVis(golesV);
            }
        }
    }

    public static void botonFecha(ArrayList<Duelo> fechaListDuelos, ArrayList<Equipo> equipos, JLabel[][] tablaLabels, JPanel tabla, int fecha, JButton[] botonesFechas){ //contenido al presionar el boton de la fecha
        JFrame nuevaFechaFrame = new JFrame("Jugar fecha " + (fecha + 1)); //Se crea la ventana de los partidos

        nuevaFechaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuevaFechaFrame.setSize(280, 200);
        nuevaFechaFrame.setResizable(false);
        nuevaFechaFrame.setLocationRelativeTo(null);

        FondoPanel img = new FondoPanel("/images/fondo_fecha.png");
        nuevaFechaFrame.setContentPane(img);

        nuevaFechaFrame.setLayout(new BorderLayout());

        JPanel pPartidos = new JPanel();
        pPartidos.setLayout(new GridBagLayout());
        pPartidos.setBorder(new EmptyBorder(30,25,0,25));
        JPanel pSouth = new JPanel();;

        pPartidos.setOpaque(false);
        pSouth.setOpaque(false);

        pSouth.setLayout(new FlowLayout());

        GridBagConstraints rules = new GridBagConstraints();

        rules.gridx = 0;//en la casilla 0,0
        rules.gridy = 5;
        rules.gridheight = 1;// ocupa 1 casilla de alto y 5 de ancho
        rules.gridwidth = 5;
        rules.weightx = 1.0;
        rules.weighty = 1.0;
        JButton calcular = new JButton("Calcular");
        pSouth.add(calcular);

        nuevaFechaFrame.add(pSouth, BorderLayout.CENTER);

        JPanel[] partidosPanel1 = new JPanel[equipos.size()/2];

        for (int i = 0; i < partidosPanel1.length; i++) {
            partidosPanel1[i] = new JPanel();
            partidosPanel1[i].setLayout(new GridLayout(1, 5, 0, 0));
            partidosPanel1[i].removeAll();
        }
        generarPartidos(pPartidos, calcular, fechaListDuelos, equipos, tablaLabels, tabla, nuevaFechaFrame, fecha, botonesFechas, rules);

        nuevaFechaFrame.add(pPartidos, BorderLayout.NORTH);
        nuevaFechaFrame.setVisible(true);
    }

    public static void generarPartidos(JPanel pPartidos, JButton calcular, ArrayList<Duelo> fechaNNpartidos, ArrayList<Equipo> equipos, JLabel[][] tablaLabels, JPanel tabla, JFrame nuevaFechaFrame, int fecha, JButton[] botonesFechas, GridBagConstraints rules) {
        ArrayList<JSpinner> spinnersLocal = new ArrayList<>();
        ArrayList<JSpinner> spinnersVisitante = new ArrayList<>();
        ArrayList<String> nombresLocal = new ArrayList<>();
        ArrayList<String> nombresVisitante = new ArrayList<>();

        for (int i = 0; i < fechaNNpartidos.size(); i++) {
            rules.gridy = i;
            rules.gridheight = 1;
            rules.gridwidth = 1;
            rules.weighty = 1.0;
            
            // Equipo local
            rules.gridx = 0;
            rules.weightx = 1.0;
            rules.anchor = GridBagConstraints.EAST;
            String nombreLocal = fechaNNpartidos.get(i).getlocal().getNombre();
            JLabel lLocal = new JLabel(nombreLocal);
            lLocal.setForeground(Color.WHITE);
            pPartidos.add(lLocal, rules);
            nombresLocal.add(nombreLocal);

            // Spinner goles local
            rules.gridx = 1;
            rules.weightx = 1.0;
            rules.anchor = GridBagConstraints.CENTER;
            JSpinner golesLocal = new JSpinner(new SpinnerNumberModel(0,0,15,1));
            pPartidos.add(golesLocal, rules);
            spinnersLocal.add(golesLocal);

            // Guión
            rules.gridx = 2;
            rules.weightx = 1.0;
            JLabel lGuion = new JLabel(" - ");
            lGuion.setForeground(Color.WHITE);
            pPartidos.add(lGuion, rules);

            // Spinner goles visitante
            rules.gridx = 3;
            rules.weightx = 1.0;
            JSpinner golesVisitante = new JSpinner(new SpinnerNumberModel(0,0,15,1));
            pPartidos.add(golesVisitante, rules);
            spinnersVisitante.add(golesVisitante);

            // Equipo visitante
            rules.gridx = 4;
            rules.weightx = 1.0;
            rules.anchor = GridBagConstraints.WEST;
            String nombreVisitante = fechaNNpartidos.get(i).getvisitante().getNombre();
            JLabel lVisit = new JLabel(nombreVisitante);
            lVisit.setForeground(Color.WHITE); 
            pPartidos.add(lVisit, rules);
            nombresVisitante.add(nombreVisitante);
            
        }
        calcular.addActionListener(_ -> {
            try {
                    // Procesar todos los partidos
            for (int i = 0; i < fechaNNpartidos.size(); i++) {
                int golesL = (Integer) spinnersLocal.get(i).getValue();
                int golesV = (Integer) spinnersVisitante.get(i).getValue();
                
                // Buscar equipos
                int idxLocal = Bib.buscarIndiceEquipo(nombresLocal.get(i), equipos);
                int idxVisit = Bib.buscarIndiceEquipo(nombresVisitante.get(i), equipos);
                
                // Procesar partido
                Bib.asignarGolesADuelo(equipos.get(idxLocal), equipos.get(idxVisit), fecha, golesL, golesV);
                Equipo.jugarPartido(equipos.get(idxLocal), equipos.get(idxVisit), golesL, golesV);
                
                // Resetear spinners
                spinnersLocal.get(i).setValue(0);
                spinnersVisitante.get(i).setValue(0);
            }
            
            // Solo continuar si no hubo errores
            nuevaFechaFrame.removeAll();
            nuevaFechaFrame.setVisible(false);
            
            botonesFechas[fecha].setVisible(false);

            if (fecha < Data.getCalendarioList().size()-1) { //si llegamos a la ultima fecha dejan de aparecer botones de nueva fecha
                botonesFechas[fecha+1].setVisible(true);
            }
            Interfaz.ordenarTabla(equipos, tablaLabels, tabla, Interfaz.mostrarMasMenosButton);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese números válidos", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        
        
    });
        
    }

    public static Equipo eqRandom(ArrayList<Equipo> list) {
        int num = (int)(Math.random() * list.size());
        Equipo eq = list.get(num);
        return eq;
    }

    public static void generarPartidos(ArrayList<ArrayList<Duelo>> calendarioList, Equipo[] equipos) {
        for (int i = 0; i < 18; i++) {
            calendarioList.add(new ArrayList<Duelo>());
        }
        /* Fecha 1 */
        calendarioList.get(0).add(new Duelo(equipos[6], equipos[7])); // Paraguay - Peru
        calendarioList.get(0).add(new Duelo(equipos[4], equipos[9])); // Colombia - Venezuela
        calendarioList.get(0).add(new Duelo(equipos[0], equipos[5])); // Argentina - Ecuador
        calendarioList.get(0).add(new Duelo(equipos[8], equipos[3])); // Uruguay - Chile
        calendarioList.get(0).add(new Duelo(equipos[2], equipos[1])); // Brasil - Bolivia

        /* Fecha 2 */
        calendarioList.get(1).add(new Duelo(equipos[1], equipos[0])); // Bolivia - Argentina
        calendarioList.get(1).add(new Duelo(equipos[5], equipos[8])); // Ecuador - Uruguay
        calendarioList.get(1).add(new Duelo(equipos[9], equipos[6])); // Venezuela - Paraguay
        calendarioList.get(1).add(new Duelo(equipos[3], equipos[4])); // Chile - Colombia
        calendarioList.get(1).add(new Duelo(equipos[7], equipos[2])); // Peru - Brasil

        /* Fecha 3 */
        calendarioList.get(2).add(new Duelo(equipos[4], equipos[8])); // Colombia - Uruguay
        calendarioList.get(2).add(new Duelo(equipos[2], equipos[9])); // Brasil - Venezuela
        calendarioList.get(2).add(new Duelo(equipos[1], equipos[5])); // Bolivia - Ecuador
        calendarioList.get(2).add(new Duelo(equipos[0], equipos[6])); // Argentina - Paraguay
        calendarioList.get(2).add(new Duelo(equipos[3], equipos[7])); // Chile - Peru

        /* Fecha 4 */
        calendarioList.get(3).add(new Duelo(equipos[9], equipos[3])); // Venezuela - Chile
        calendarioList.get(3).add(new Duelo(equipos[6], equipos[1])); // Paraguay - Bolivia
        calendarioList.get(3).add(new Duelo(equipos[5], equipos[4])); // Ecuador - Colombia
        calendarioList.get(3).add(new Duelo(equipos[8], equipos[2])); // Uruguay - Brasil
        calendarioList.get(3).add(new Duelo(equipos[7], equipos[0])); // Peru - Argentina

        /* Fecha 5 */
        calendarioList.get(4).add(new Duelo(equipos[1], equipos[7])); // Bolivia - Peru
        calendarioList.get(4).add(new Duelo(equipos[9], equipos[5])); // Venezuela - Ecuador
        calendarioList.get(4).add(new Duelo(equipos[4], equipos[2])); // Colombia - Brasil
        calendarioList.get(4).add(new Duelo(equipos[0], equipos[8])); // Argentina - Uruguay
        calendarioList.get(4).add(new Duelo(equipos[3], equipos[6])); // Chile - Paraguay

        /* Fecha 6 */
        calendarioList.get(5).add(new Duelo(equipos[6], equipos[4])); // Paraguay - Colombia
        calendarioList.get(5).add(new Duelo(equipos[5], equipos[3])); // Ecuador - Chile
        calendarioList.get(5).add(new Duelo(equipos[8], equipos[1])); // Uruguay - Bolivia
        calendarioList.get(5).add(new Duelo(equipos[2], equipos[0])); // Brasil - Argentina
        calendarioList.get(5).add(new Duelo(equipos[7], equipos[9])); // Peru - Venezuela

        /* Fecha 7 */
        calendarioList.get(6).add(new Duelo(equipos[1], equipos[9])); // Bolivia - Venezuela
        calendarioList.get(6).add(new Duelo(equipos[0], equipos[3])); // Argentina - Chile
        calendarioList.get(6).add(new Duelo(equipos[8], equipos[6])); // Uruguay - Paraguay
        calendarioList.get(6).add(new Duelo(equipos[2], equipos[5])); // Brasil - Ecuador
        calendarioList.get(6).add(new Duelo(equipos[7], equipos[4])); // Peru - Colombia

        /* Fecha 8 */
        calendarioList.get(7).add(new Duelo(equipos[4], equipos[0])); // Colombia - Argentina
        calendarioList.get(7).add(new Duelo(equipos[5], equipos[7])); // Ecuador - Peru
        calendarioList.get(7).add(new Duelo(equipos[3], equipos[1])); // Chile - Bolivia
        calendarioList.get(7).add(new Duelo(equipos[9], equipos[8])); // Venezuela - Uruguay
        calendarioList.get(7).add(new Duelo(equipos[6], equipos[2])); // Paraguay - Brasil

        /* Fecha 9 */
        calendarioList.get(8).add(new Duelo(equipos[1], equipos[4])); // Bolivia - Colombia
        calendarioList.get(8).add(new Duelo(equipos[9], equipos[0])); // Venezuela - Argentina
        calendarioList.get(8).add(new Duelo(equipos[5], equipos[6])); // Ecuador - Paraguay
        calendarioList.get(8).add(new Duelo(equipos[3], equipos[2])); // Chile - Brasil
        calendarioList.get(8).add(new Duelo(equipos[7], equipos[8])); // Peru - Uruguay

        /* Fecha 10 */
        calendarioList.get(9).add(new Duelo(equipos[4], equipos[3])); // Colombia - Chile
        calendarioList.get(9).add(new Duelo(equipos[6], equipos[9])); // Paraguay - Venezuela
        calendarioList.get(9).add(new Duelo(equipos[8], equipos[5])); // Uruguay - Ecuador
        calendarioList.get(9).add(new Duelo(equipos[0], equipos[1])); // Argentina - Bolivia
        calendarioList.get(9).add(new Duelo(equipos[2], equipos[7])); // Brasil - Peru

        /* Fecha 11 */
        calendarioList.get(10).add(new Duelo(equipos[9], equipos[2])); // Venezuela - Brasil
        calendarioList.get(10).add(new Duelo(equipos[6], equipos[0])); // Paraguay - Argentina
        calendarioList.get(10).add(new Duelo(equipos[5], equipos[1])); // Ecuador - Bolivia
        calendarioList.get(10).add(new Duelo(equipos[8], equipos[4])); // Uruguay - Colombia
        calendarioList.get(10).add(new Duelo(equipos[7], equipos[3])); // Peru - Chile

        /* Fecha 12 */
        calendarioList.get(11).add(new Duelo(equipos[1], equipos[6])); // Bolivia - Paraguay
        calendarioList.get(11).add(new Duelo(equipos[4], equipos[5])); // Colombia - Ecuador
        calendarioList.get(11).add(new Duelo(equipos[0], equipos[7])); // Argentina - Peru
        calendarioList.get(11).add(new Duelo(equipos[3], equipos[9])); // Chile - Venezuela
        calendarioList.get(11).add(new Duelo(equipos[2], equipos[8])); // Brasil - Uruguay

        /* Fecha 13 */
        calendarioList.get(12).add(new Duelo(equipos[8], equipos[0])); // Uruguay - Argentina
        calendarioList.get(12).add(new Duelo(equipos[7], equipos[1])); // Peru - Bolivia
        calendarioList.get(12).add(new Duelo(equipos[2], equipos[4])); // Brasil - Colombia
        calendarioList.get(12).add(new Duelo(equipos[6], equipos[3])); // Paraguay - Chile
        calendarioList.get(12).add(new Duelo(equipos[5], equipos[9])); // Ecuador - Venezuela

        /* Fecha 14 */
        calendarioList.get(13).add(new Duelo(equipos[1], equipos[8])); // Bolivia - Uruguay
        calendarioList.get(13).add(new Duelo(equipos[4], equipos[6])); // Colombia - Paraguay
        calendarioList.get(13).add(new Duelo(equipos[9], equipos[7])); // Venezuela - Peru
        calendarioList.get(13).add(new Duelo(equipos[0], equipos[2])); // Argentina - Brasil
        calendarioList.get(13).add(new Duelo(equipos[3], equipos[5])); // Chile - Ecuador

        /* Fecha 15 */
        calendarioList.get(14).add(new Duelo(equipos[4], equipos[7])); // Colombia - Peru
        calendarioList.get(14).add(new Duelo(equipos[9], equipos[1])); // Venezuela - Bolivia
        calendarioList.get(14).add(new Duelo(equipos[6], equipos[8])); // Paraguay - Uruguay
        calendarioList.get(14).add(new Duelo(equipos[3], equipos[0])); // Chile - Argentina
        calendarioList.get(14).add(new Duelo(equipos[5], equipos[2])); // Ecuador - Brasil

        /* Fecha 16 */
        calendarioList.get(15).add(new Duelo(equipos[8], equipos[9])); // Uruguay - Venezuela
        calendarioList.get(15).add(new Duelo(equipos[7], equipos[5])); // Peru - Ecuador
        calendarioList.get(15).add(new Duelo(equipos[2], equipos[6])); // Brasil - Paraguay
        calendarioList.get(15).add(new Duelo(equipos[1], equipos[3])); // Bolivia - Chile
        calendarioList.get(15).add(new Duelo(equipos[0], equipos[4])); // Argentina - Colombia

        /* Fecha 17 */
        calendarioList.get(16).add(new Duelo(equipos[8], equipos[7])); // Uruguay - Peru
        calendarioList.get(16).add(new Duelo(equipos[4], equipos[1])); // Colombia - Bolivia
        calendarioList.get(16).add(new Duelo(equipos[2], equipos[3])); // Brasil - Chile
        calendarioList.get(16).add(new Duelo(equipos[6], equipos[5])); // Paraguay - Ecuador
        calendarioList.get(16).add(new Duelo(equipos[0], equipos[9])); // Argentina - Venezuela

        /* Fecha 18 */
        calendarioList.get(17).add(new Duelo(equipos[7], equipos[6])); // Peru - Paraguay
        calendarioList.get(17).add(new Duelo(equipos[9], equipos[4])); // Venezuela - Colombia
        calendarioList.get(17).add(new Duelo(equipos[1], equipos[2])); // Bolivia - Brasil
        calendarioList.get(17).add(new Duelo(equipos[3], equipos[8])); // Chile - Uruguay
        calendarioList.get(17).add(new Duelo(equipos[5], equipos[0])); // Ecuador - Argentina

    }

    public static void generarPartidosRand(ArrayList<ArrayList<Duelo>> calendarioList, Equipo[] eqList) {
        ArrayList<Duelo> duelosProgramadosList = new ArrayList<>();
        for (int i = 0; i < eqList.length-1 ; i++) {// -1 hace referencia a la cantidad de jornadas, ej: para 10 eq hay 9 jornadas de todos contra todos
            calendarioList.add(new ArrayList<Duelo>());
            ArrayList<Equipo> eqListTemp = new ArrayList<>();
            for (Equipo eq : eqList) {
                eqListTemp.add(eq);
            }

            int intentosFallidos = 0;
            int maxIntentos = 1000; // Límite para evitar ciclo infinito
            
            while (!eqListTemp.isEmpty()) {
                Equipo eq1 = eqRandom(eqListTemp);
                Equipo eq2 = eqRandom(eqListTemp);

                if (eq1 != eq2) { //se evita que salga un equipo duplicado
                    Duelo dueloActual = new Duelo(eq1, eq2);
                    boolean dueloExiste = false;
                    for (Duelo duelo : duelosProgramadosList) {
                        if (dueloActual.mismoDuelo(duelo)) {
                            dueloExiste = true;
                            break;
                        }
                    }
                    if (!dueloExiste) { //se evita que se repitan encuentros
                        calendarioList.get(i).add(dueloActual); 
                        eqListTemp.remove(eq1);
                        eqListTemp.remove(eq2);
                        duelosProgramadosList.add(dueloActual);
                        intentosFallidos = 0; // Reiniciar contador cuando se encuentra un duelo válido
                    } else {
                        intentosFallidos++;
                    }
                } else {
                    intentosFallidos++;
                }
                
                // Si hay muchos intentos fallidos, romper el ciclo para evitar infinito
                if (intentosFallidos > maxIntentos) {
                    System.out.println("Advertencia: No se pudieron parear todos los equipos en la fecha " + (i+1));
                    break;
                }
            }
        }

        /* 
        //con las 9 primeras fechas listas, se toman estas, se duplican, se invierte la localía y se organizan al azar, excepto la última fecha que debe ser igual que la primera
        ArrayList<ArrayList<Duelo>> segundaVueltaList = new ArrayList<>();
        // para no copiar como "new ArrayList<>(calendarioList)" por problemas de que toma la misma memoria
        for (ArrayList<Duelo> fecha : calendarioList) {
            ArrayList<Duelo> clonedList = new ArrayList<>();
            for (Duelo duelo : fecha) {
            clonedList.add(new Duelo(duelo.getlocal(), duelo.getvisitante()));
            }
            segundaVueltaList.add(clonedList);
        }

        ArrayList<Duelo> primeraFecha = segundaVueltaList.get(0);
        segundaVueltaList.remove(0);

        
        for (Duelo duelo : primeraFecha){
            Duelo.intercambiarLocalia(duelo, duelo.getlocal(), duelo.getvisitante());
        }
        
        for (ArrayList<Duelo> fecha : segundaVueltaList) {
            for (Duelo duelo : fecha) {
                Duelo.intercambiarLocalia(duelo, duelo.getlocal(), duelo.getvisitante());
            }
        }
        Collections.shuffle(segundaVueltaList);
        calendarioList.addAll(segundaVueltaList);
        calendarioList.add(primeraFecha);

        for (ArrayList<Duelo> fecha : calendarioList) {
            for (Duelo duelo : fecha) {
                System.out.println("FECHA " + (calendarioList.indexOf(fecha)+1) + ": " + duelo.getlocal().getNombre() + " - " + duelo.getvisitante().getNombre());
            }
        }



        Boolean repeticion = false;
        int repeticiones = 0;
        for (ArrayList<Duelo> fecha1 : calendarioList) {
            for (Duelo duelo1 : fecha1) {
                for (ArrayList<Duelo> fecha2 : calendarioList) {
                    for (Duelo duelo2 : fecha2) {
                        if (duelo1.equals(duelo2) && duelo1 != duelo2) { //mismos atributos pero diferente objeto = duplicado
                            repeticion = true;
                            repeticiones++;
                        }
                    }
                }
            }
        }
        if (repeticion) {
            System.out.println("Se repiten duelos: " + repeticiones);
        } else {
            System.out.println("Ningún duelo se repite");
        }
        
    }*/
}}
