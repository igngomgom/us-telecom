
import java.io.*;

class Jugador implements Serializable,Comparable<Jugador> {

    private String nombre;
    private int marcador;


    Jugador(String name) {
        nombre = name;
        marcador = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMarcador() {
        return marcador;
    }

    public void acierto(int puntos) {
        marcador+=puntos;
    }

    public String toString() {
        return nombre+ "        " + Integer.toString(marcador)+"\n";
    }

    // Este metodo es de Comparable
    // Gracias a el podemos ordenar los jugadores segun su marcador
    public int compareTo(Jugador comparetu) {
        int compareMarcador=((Jugador)comparetu).getMarcador();
        return compareMarcador-this.marcador;
    }

}
