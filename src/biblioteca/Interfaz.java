package biblioteca;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Interfaz {
    public static JButton mostrarMasMenosButton = new JButton("Ver +");
    public static JPanel pCentral = new JPanel(new BorderLayout());
    public static JFrame ventanaPrincipal = new JFrame("Tabla de Posiciones - Eliminatorias");

    public static void generarInterfaz(ArrayList<Equipo> equipos){
        FondoPanel panel = new FondoPanel("/images/balon.png");
        ventanaPrincipal.setContentPane(panel);

        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setLayout(new BorderLayout());
        
        JPanel pTabla = new JPanel();
        pTabla.setLayout(new GridBagLayout());
        
        // Panel para los botones
        JPanel panelBotones = new JPanel();
        
        // Arreglo de botones para cada fecha
        JButton[] botonesFechas = new JButton[18];
        
        // Arreglo 2D para los labels de la tabla
        JLabel[][] tablaLabels = new JLabel[10][14];
        
            // Inicializar la tabla
            ordenarTabla(equipos, tablaLabels, pTabla, mostrarMasMenosButton);
            ventanaPrincipal.setSize(270,400);
            ventanaPrincipal.setLocationRelativeTo(null);
            ventanaPrincipal.repaint();
            
        // Crear botones "Nueva Fecha" para cada fecha
        for (int i = 0; i < 18; i++) {
            final int fecha = i;
            botonesFechas[i] = new JButton("Fecha " + (i + 1));
            botonesFechas[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Bib.botonFecha(Data.getCalendarioList().get(fecha), equipos, tablaLabels, pTabla, fecha, botonesFechas);
                }
            });
            panelBotones.add(botonesFechas[i]);
            botonesFechas[i].setVisible(false);
        }

        //Boton de calendario
        JButton calendarioButton = new JButton("Calendario");
        panelBotones.add(calendarioButton);
        calendarioButton.addActionListener(_->{
            mostrarCalendario(Data.getCalendarioList());
        });
        botonesFechas[0].setVisible(true);

        //boton ver +/-
        mostrarMasMenosButton.addActionListener(_ -> {
            verMasMenos(tablaLabels, pTabla, equipos, mostrarMasMenosButton, ventanaPrincipal);
        });

        //Panel sur
        JPanel pSouth = new JPanel();
            crearPanelSur(pSouth);
        
        // Agregar paneles al frame principal
        panelBotones.add(mostrarMasMenosButton);
        

        //pCentral.add(pColorsMain, BorderLayout.WEST);
        pCentral.add(pTabla, BorderLayout.CENTER);

        pTabla.setBackground(new Color(255,255,255, 150));
        pSouth.setBackground(new Color(0,0,0, 50));
        panelBotones.setOpaque(false);

        ventanaPrincipal.add(panelBotones, BorderLayout.NORTH);
        ventanaPrincipal.add(pCentral, BorderLayout.CENTER);
        ventanaPrincipal.add(pSouth, BorderLayout.SOUTH);
        
        
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setLocationRelativeTo(null);
    }

    public static void mostrarCalendario(ArrayList<ArrayList<Duelo>> calendarioList){
        JFrame calendarioFrame = new JFrame("Calendario - Eliminatorias");
        calendarioFrame.setSize(250, 300);

        JPanel[] fechasPanel = new JPanel[Data.getCalendarioList().size()];//cuantos cuadros de fecha se generan / cuantas fechas hay

        JPanel fechas = new JPanel(new GridLayout(fechasPanel.length,1));

        for (int i = 0; i < calendarioList.size(); i++) {
            fechasPanel[i] = new JPanel();
            fechasPanel[i].setLayout(new GridLayout(Data.getCalendarioList().get(i).size()+1,1)); //se generan los partidos de la fecha + el titulo
            JLabel labelfecha = new JLabel("FECHA " + (i+1), SwingConstants.CENTER);
            labelfecha.setOpaque(true);
            labelfecha.setBackground(new Color(193,193,193));
            fechasPanel[i].add(labelfecha);
            
            for (Duelo duelo : calendarioList.get(i)) {
                if (duelo.getgLoc() == -1) {//goles de local -1 es el valor predeterminado antes de jugar el partido
                    fechasPanel[i].add(new JLabel(duelo.getlocal().getNombre() + " - " + duelo.getvisitante().getNombre(), SwingConstants.CENTER));
                } else {
                    if (duelo.getgLoc() > duelo.getgVis()) {
                        fechasPanel[i].add(new JLabel("👑 " + duelo.getlocal().getNombre() + " " + duelo.getgLoc() + " - " + duelo.getgVis() + " " + duelo.getvisitante().getNombre(), SwingConstants.CENTER));
                    } else if (duelo.getgLoc() < duelo.getgVis()) {
                        fechasPanel[i].add(new JLabel(duelo.getlocal().getNombre() + " " + duelo.getgLoc() + " - " + duelo.getgVis() + " " + duelo.getvisitante().getNombre() + " 👑", SwingConstants.CENTER));
                    } else {
                        fechasPanel[i].add(new JLabel(duelo.getlocal().getNombre() + " " + duelo.getgLoc() + " - " + duelo.getgVis() + " " + duelo.getvisitante().getNombre(), SwingConstants.CENTER));
                    }
                }
            }
            fechas.add(fechasPanel[i]);
        }
        JScrollPane scrollPane = new JScrollPane(fechas);
        calendarioFrame.add(scrollPane);
        calendarioFrame.setVisible(true);
        calendarioFrame.setLocationRelativeTo(null);
    }

    public static void verMasMenos(JLabel[][] tablaLabels, JPanel pTabla, ArrayList<Equipo> equipos, JButton btn, JFrame ventanaPrincipal){
        if (btn.getText().equals("Ver +")) {// intercambiar texto del boton
                btn.setText("Ver -");
            } else if (btn.getText().equals("Ver -")) {
                btn.setText("Ver +");
            }
            mostrarTabla(tablaLabels, pTabla, equipos, btn);
                pTabla.revalidate();
                pTabla.repaint();
                ventanaPrincipal.setLocationRelativeTo(null);
                ventanaPrincipal.repaint();

            if (btn.getText().equals("Ver -")) {  
                ventanaPrincipal.setSize(400,400);
                ventanaPrincipal.setLocationRelativeTo(null);    
            } else if (btn.getText().equals("Ver +")) {
                ventanaPrincipal.setSize(270,400); 
                ventanaPrincipal.setLocationRelativeTo(null);   
            }
    }

    public static void ordenarTabla(ArrayList<Equipo> equipos, JLabel[][] tablaLabels, JPanel tabla, JButton maxOMini) {
        // Guardar posición anterior
        for (int i = 0; i < equipos.size(); i++) {
            equipos.get(i).setPosAnterior(i);
        }
        Equipo.ordenarEquiposBurbuja(equipos);
        // Actualizar posiciones DESPUÉS de ordenar y asignar estado
        for (int i = 0; i < equipos.size(); i++) {
            equipos.get(i).setPosActual(i);
            equipos.get(i).actualizarEstado();
            
        }
        mostrarTabla(tablaLabels, tabla, equipos, maxOMini);
    }

    public static void mostrarTabla(JLabel[][] tablaLabels, JPanel tabla, ArrayList<Equipo> equipos, JButton maxOMini){
        
        GridBagConstraints rules = new GridBagConstraints();

        JLabel[] titulosLabel = new JLabel[10]; //son los titulos de las columnas
        tabla.removeAll(); //reordenar la tabla dinámica
        ordenarColores(equipos, tabla);

        rules.gridx = 0;
        rules.gridy = 0;
        rules.gridheight = 1;
        rules.gridwidth = 1;

        if (maxOMini.getText().equals("Ver -")) { //si el boton dice ver menos entonces esta seleccionada la tabla grande
                titulosLabel[0] = new JLabel("Equipo", 0);
                titulosLabel[1] = new JLabel("PJ", 0);
                titulosLabel[2] = new JLabel("PG", 0);
                titulosLabel[3] = new JLabel("PE", 0);
                titulosLabel[4] = new JLabel("PP", 0);
                titulosLabel[5] = new JLabel("GF", 0);
                titulosLabel[6] = new JLabel("GC", 0);
                titulosLabel[7] = new JLabel("DG", 0);
                titulosLabel[8] = new JLabel("Puntos", 0);
                titulosLabel[9] = new JLabel("Ultimos 5", 0);
            for (int i = 0; i < titulosLabel.length; i++) {
                if (i != 9) {
                    rules.gridx = i;
                    rules.weightx = 1.0;
                    rules.fill = GridBagConstraints.BOTH;
                    titulosLabel[i].setBackground(new Color(75,75,75));
                    titulosLabel[i].setOpaque(true);
                    titulosLabel[i].setForeground(Color.white);
                    tabla.add(titulosLabel[i], rules);
                } else if (i == 9) {
                    rules.gridx = i;
                    rules.gridwidth = 5;
                    rules.weightx = 1.0;
                    rules.fill = GridBagConstraints.BOTH;
                    titulosLabel[i].setBackground(new Color(75,75,75));
                    titulosLabel[i].setOpaque(true);
                    titulosLabel[i].setForeground(Color.white);
                    tabla.add(titulosLabel[i], rules);
                    rules.gridwidth = 1;
                }
                
            }

            for (int i = 0; i < equipos.size(); i++) {

            tablaLabels[i][0] = new JLabel(equipos.get(i).getNombre());
            tablaLabels[i][0].setIcon(equipos.get(i).getEstado());

            tablaLabels[i][1] = new JLabel(String.valueOf(equipos.get(i).getPartidosJugados()), 0); 
            tablaLabels[i][2] = new JLabel(String.valueOf(equipos.get(i).getPartidosGanados()), 0); 
            tablaLabels[i][3] = new JLabel(String.valueOf(equipos.get(i).getPartidosEmpatados()), 0); 
            tablaLabels[i][4] = new JLabel(String.valueOf(equipos.get(i).getPartidosPerdidos()), 0); 
            tablaLabels[i][5] = new JLabel(String.valueOf(equipos.get(i).getGolesFavor()), 0); 
            tablaLabels[i][6] = new JLabel(String.valueOf(equipos.get(i).getGolesContra()), 0); 
            tablaLabels[i][7] = new JLabel(String.valueOf(equipos.get(i).getDifGol()), 0); 
            tablaLabels[i][8] = new JLabel(String.valueOf(equipos.get(i).getPuntos()), 0);

            tablaLabels[i][9] = new JLabel(equipos.get(i).getUltimosResultados().get(0), 0);
            tablaLabels[i][10] = new JLabel(equipos.get(i).getUltimosResultados().get(1), 0);
            tablaLabels[i][11] = new JLabel(equipos.get(i).getUltimosResultados().get(2), 0);
            tablaLabels[i][12] = new JLabel(equipos.get(i).getUltimosResultados().get(3), 0);
            tablaLabels[i][13] = new JLabel(equipos.get(i).getUltimosResultados().get(4), 0);
            
            for (int j = 0; j < 14; j++) {
                rules.gridx = j;
                rules.gridwidth = 1;
                rules.gridheight = 1;
                if (j == 0) {
                    rules.anchor = GridBagConstraints.WEST;
                    rules.insets = new Insets(5, 10, 5, 5);
                    rules.gridy = i + 1;
                    rules.weightx = 1.0;
                    tabla.add(tablaLabels[i][j], rules);
                    rules.anchor = GridBagConstraints.CENTER;
                    rules.insets = new Insets(0,0,0,0);
                } else {
                    tabla.add(tablaLabels[i][j], rules);
                }
            }
            }
        } else if (maxOMini.getText().equals("Ver +")) { //si el boton dice ver mas entonces esta seleccionada la opcion de tabla pequeña
            titulosLabel[0] = new JLabel("Equipo", 0);
            titulosLabel[1] = new JLabel("DG", 0);
            titulosLabel[2] = new JLabel("Puntos", 0);
            titulosLabel[3] = new JLabel("Ultimos 5", 0);
            for (int i = 0; i < 4; i++) {
                if (i != 3) {
                    rules.gridx = i;
                    rules.weightx = 1.0;
                    rules.fill = GridBagConstraints.BOTH;
                    titulosLabel[i].setBackground(new Color(75,75,75));
                    titulosLabel[i].setOpaque(true);
                    titulosLabel[i].setForeground(Color.white);
                    tabla.add(titulosLabel[i], rules);
                } else if (i == 3) {
                    rules.gridx = i;
                    rules.gridwidth = 5;
                    rules.weightx = 1.0;
                    rules.fill = GridBagConstraints.BOTH;
                    titulosLabel[i].setBackground(new Color(75,75,75));
                    titulosLabel[i].setOpaque(true);
                    titulosLabel[i].setForeground(Color.white);
                    tabla.add(titulosLabel[i], rules);

                    rules.gridwidth = 1;
                }
            }
            for (int i = 0; i < equipos.size(); i++) {

            tablaLabels[i][0] = new JLabel(equipos.get(i).getNombre());
            tablaLabels[i][0].setIcon(equipos.get(i).getEstado());

            tablaLabels[i][1] = new JLabel(String.valueOf(equipos.get(i).getDifGol()), 0); 
            tablaLabels[i][2] = new JLabel(String.valueOf(equipos.get(i).getPuntos()), 0);

            tablaLabels[i][3] = new JLabel(equipos.get(i).getUltimosResultados().get(0), 0);
            tablaLabels[i][4] = new JLabel(equipos.get(i).getUltimosResultados().get(1), 0);
            tablaLabels[i][5] = new JLabel(equipos.get(i).getUltimosResultados().get(2), 0);
            tablaLabels[i][6] = new JLabel(equipos.get(i).getUltimosResultados().get(3), 0);
            tablaLabels[i][7] = new JLabel(equipos.get(i).getUltimosResultados().get(4), 0);
            
            for (int j = 0; j < 8; j++) {
                rules.gridx = j;
                rules.gridwidth = 1;
                rules.gridheight = 1;
                if (j == 0) {
                    rules.anchor = GridBagConstraints.WEST;
                    rules.insets = new Insets(5, 10, 5, 5);
                    rules.gridy = i + 1;
                    rules.weightx = 1.0;
                    tabla.add(tablaLabels[i][j], rules);
                    rules.anchor = GridBagConstraints.CENTER;
                    rules.insets = new Insets(0,0,0,0);
                } else {
                    tabla.add(tablaLabels[i][j], rules);
                }
                
            }
            }
        }
        pCentral.add(tabla, BorderLayout.CENTER);
        
        tabla.revalidate();
        tabla.repaint();

        ventanaPrincipal.revalidate();
        ventanaPrincipal.repaint();
    }

    public static void ordenarColores(ArrayList<Equipo> equipos, JPanel pTabla){

        JPanel[] pColors = new JPanel[equipos.size() + 1];

        GridBagConstraints rules = new GridBagConstraints();
        rules.gridx = 0;
        rules.gridy = 0;
        rules.gridwidth = 1;
        rules.gridheight = 1;
        rules.fill = GridBagConstraints.VERTICAL; // Llenar verticalmente
        rules.anchor = GridBagConstraints.WEST; // Pegar a la izquierda
        rules.weightx = 1.0;
        rules.weighty = 1.0;

        for (int i = 0; i < pColors.length; i++) {
            if (equipos.size() == 10) {
                rules.gridy = i;
                if (i == 0) {
                    pColors[i] = new JPanel();
                    pColors[i].setOpaque(true);
                    pColors[i].setBackground(new Color(75,75,75));
                    pTabla.add(pColors[i], rules);
                } else if (i > 0 && i < 7) {
                    pColors[i] = new JPanel();
                    pColors[i].setOpaque(true);
                    pColors[i].setBackground(new Color(0, 146, 255));
                    pTabla.add(pColors[i], rules);
                } else if (i == 7) {
                    pColors[i] = new JPanel();
                    pColors[i].setOpaque(true);
                    pColors[i].setBackground(new Color(255, 178, 0));
                    pTabla.add(pColors[i], rules);
                } else {
                    pColors[i] = new JPanel();
                    pColors[i].setOpaque(true);
                    pColors[i].setBackground(new Color(255, 0, 0)); 
                    pTabla.add(pColors[i], rules);  
                }
            } else if (equipos.size() == 4) {
                rules.gridy = i;
                if (i == 0) {
                    pColors[i] = new JPanel();
                    pColors[i].setOpaque(true);
                    pColors[i].setBackground(new Color(75,75,75));
                    pTabla.add(pColors[i], rules);
                } else if (i >= 0 && i < 3) {
                    pColors[i] = new JPanel();
                    pColors[i].setOpaque(true);
                    pColors[i].setBackground(new Color(0, 146, 255));
                    pTabla.add(pColors[i], rules);
                } else if (i == 3) {
                    pColors[i] = new JPanel();
                    pColors[i].setOpaque(true);
                    pColors[i].setBackground(new Color(255, 178, 0));
                    pTabla.add(pColors[i], rules);
                } else {
                    pColors[i] = new JPanel();
                    pColors[i].setOpaque(true);
                    pColors[i].setBackground(new Color(255, 0, 0)); 
                    pTabla.add(pColors[i], rules);  
                }
            }
        }
    }

    public static void crearPanelSur(JPanel panel){
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(new EmptyBorder(10, 10, 10,10));
        JLabel[] lbl = new JLabel[8];
        lbl[0] = new JLabel("Clasificación:");
        lbl[1] = new JLabel("Últimos 5 partidos:");
        Image img = new ImageIcon(Interfaz.class.getResource("/images/clasifica.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lbl[2] = new JLabel("Siguiente ronda");
        lbl[2].setIcon(new ImageIcon(img));
        img = new ImageIcon(Interfaz.class.getResource("/images/gano.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lbl[3] = new JLabel("Ganó");
        lbl[3].setIcon(new ImageIcon(img));
        img = new ImageIcon(Interfaz.class.getResource("/images/repechaje.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lbl[4] = new JLabel("Repechaje");
        lbl[4].setIcon(new ImageIcon(img));
        img = new ImageIcon(Interfaz.class.getResource("/images/perdio.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lbl[5] = new JLabel("Perdió");
        lbl[5].setIcon(new ImageIcon(img));
        img = new ImageIcon(Interfaz.class.getResource("/images/eliminado.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lbl[6] = new JLabel("Eliminado");
        lbl[6].setIcon(new ImageIcon(img));
        img = new ImageIcon(Interfaz.class.getResource("/images/empato.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lbl[7] = new JLabel("Empató");
        lbl[7].setIcon(new ImageIcon(img));
        for (JLabel jL : lbl) {
            jL.setForeground(Color.WHITE);
            panel.add(jL);
        }
    }
}
