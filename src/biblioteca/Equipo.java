package biblioteca;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Equipo {
    private String nombre;
    private int partidosJugados = 0, partidosGanados = 0, partidosEmpatados = 0, partidosPerdidos = 0, golesFavor = 0, golesContra = 0, difGol = 0, puntos;
    
    private int posActual = -1, posAnterior = -1;
    private ImageIcon estado;
    private LinkedList<ImageIcon> ultimosResultados = new LinkedList<>();
    
    
    public Equipo(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
        ImageIcon iconoOriginal = new ImageIcon(Interfaz.class.getResource("/images/sinjugar.png"));
        Image imgEscalada = iconoOriginal.getImage().getScaledInstance(13,13, Image.SCALE_SMOOTH);
        for (int i = 0; i < 5; i++) {
            this.ultimosResultados.add(new ImageIcon(imgEscalada));
        }
    }
    public static void statsGoles(Equipo equipo, int goles, int golesContra) {
        equipo.golesFavor = equipo.golesFavor + goles;
        equipo.golesContra = equipo.golesContra + golesContra;
        equipo.difGol = equipo.golesFavor - equipo.golesContra;
    }

    public static void jugarPartido(Equipo local, Equipo visitante, int golesLocal, int golesVisitante) {
        local.partidosJugados++;
        visitante.partidosJugados++;
        if (golesLocal > golesVisitante) {
            local.partidosGanados++;
            visitante.partidosPerdidos++;
            local.puntos+=3;
            local.actualizarUltimosResultados("gano");
            visitante.actualizarUltimosResultados("perdio");
        } else if (golesLocal < golesVisitante) {
            visitante.partidosGanados++;
            local.partidosPerdidos++;
            visitante.puntos+=3;
            visitante.actualizarUltimosResultados("gano");
            local.actualizarUltimosResultados("perdio");
        } else {
            local.partidosEmpatados++;
            visitante.partidosEmpatados++;
            local.puntos++;
            visitante.puntos++;
            local.actualizarUltimosResultados("empato");
            visitante.actualizarUltimosResultados("empato");
        }
        statsGoles(local, golesLocal, golesVisitante);
        statsGoles(visitante, golesVisitante, golesLocal);
    }
    
    public void actualizarEstado() {
        if (this.partidosJugados != 0) {
            String rutaIcono;
            
            if (this.posActual < this.posAnterior) {
                rutaIcono = "/images/subio.png";
            } else if (this.posActual > this.posAnterior) {
                rutaIcono = "/images/bajo.png";
            } else {
                rutaIcono = "/images/mantuvo.png";
            }
            
            // Crear ImageIcon directamente
            ImageIcon iconoOriginal = new ImageIcon(Interfaz.class.getResource(rutaIcono));
            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(13,13, Image.SCALE_SMOOTH);
            this.estado = new ImageIcon(imgEscalada);
        }
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
                        // Si hay empate en goles a favor
                        else if (equipo1.getGolesFavor() == equipo2.getGolesFavor()) {
                            
                            // Comparar por nombre (ascendente - alfabético)
                            if (equipo1.getNombre().compareToIgnoreCase(equipo2.getNombre()) > 0) {
                                Collections.swap(equipos, j, j + 1);
                                intercambiado = true;
                            }
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
    public void actualizarUltimosResultados(String resultadoActual) {
        String rutaIcono = "";
        boolean error = false;
        if (resultadoActual.equals("gano")) {
            rutaIcono = "/images/gano.png";
        } else if (resultadoActual.equals("perdio")) {
            rutaIcono = "/images/perdio.png";
        } else if (resultadoActual.equals("empato")) {
            rutaIcono = "/images/empato.png";
        } else {
            error = true;
        }
        if (!error) {
            // Crear ImageIcon directamente
            ImageIcon iconoOriginal = new ImageIcon(Interfaz.class.getResource(rutaIcono));
            Image imgEscalada = iconoOriginal.getImage().getScaledInstance(13,13, Image.SCALE_SMOOTH);
            this.ultimosResultados.add(new ImageIcon(imgEscalada));
            this.ultimosResultados.removeFirst();
        }
            
    }
        

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPartidosJugados() {
        return partidosJugados;
    }
    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }
    public int getPartidosGanados() {
        return partidosGanados;
    }
    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }
    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }
    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }
    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }
    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }
    public int getGolesFavor() {
        return golesFavor;
    }
    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }
    public int getGolesContra() {
        return golesContra;
    }
    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }
    public int getDifGol() {
        return difGol;
    }
    public void setDifGol(int difGol) {
        this.difGol = difGol;
    }
    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    public int getPosActual() {
        return posActual;
    }
    public void setPosActual(int posActual) {
        this.posActual = posActual;
    }
    public int getPosAnterior() {
        return posAnterior;
    }
    public void setPosAnterior(int posAnterior) {
        this.posAnterior = posAnterior;
    }
    public ImageIcon getEstado() {
        return estado;
    }
    public void setEstado(ImageIcon estado) {
        this.estado = estado;
    }
    public LinkedList<ImageIcon> getUltimosResultados() {
        return ultimosResultados;
    }
    public void setUltimosResultados(LinkedList<ImageIcon> ultimosResultados) {
        this.ultimosResultados = ultimosResultados;
    }
    
} 
    