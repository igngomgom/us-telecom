
import java.util.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
//Implementación, ver explicacion de cada método en su interfaz: Juego.java
class JuegoImpl extends UnicastRemoteObject implements Juego {

	List<Jugador> lj;
	List<Pregunta> lp;
	int turnos;
	int numJugadores;
	int numJugadoresListos;


    JuegoImpl() throws RemoteException, FileNotFoundException, IOException{

		lj=new LinkedList<Jugador>();
		lp=new LinkedList<Pregunta>();

		BufferedReader bf = new BufferedReader(new FileReader("preguntas.txt"));
		
		String sCadena;
		Pregunta p;
		turnos = 0;
		numJugadores = 0;
		numJugadoresListos = 0;

		while ((sCadena = bf.readLine())!=null) {
			p = new PreguntaImpl(sCadena);
			lp.add(p);
			turnos++;
		}
		bf.close();
    }

	public void crearUsuario(String u) throws RemoteException {
        Jugador j = new Jugador(u);
        lj.add(j);
        numJugadores++;
    }
	



	//lanza la pregunta
	public String getPregunta(int turno) throws RemoteException { 
		Pregunta p = lp.get(turno);
		return p.getPregunta()+"\n"+p.getOpciones();
	}

	public boolean setRespuesta(String nombreJugador, int turno, char c, int puntos) throws RemoteException{
		System.out.println("\n"+nombreJugador+" responde a la pregunta "+(turno+1)+" la opcion "+String.valueOf(c));
		Pregunta p = lp.get(turno);
		p.setRespuestaUsuario(c);
		boolean acierto = p.comprueba();
		if(acierto){
			// Si ha acertado, actualizamos el marcador del jugador
			for(int i=0;i<numJugadores;i++){
				if(lj.get(i).getNombre().equals(nombreJugador)){
					lj.get(i).acierto(puntos);
					System.out.println("\n"+nombreJugador+" acierta y consigue "+puntos+" puntos.");
				}
			}
		}
		return acierto;
	}

	public String getRespuestaCorrecta(int turno) throws RemoteException { 
		Pregunta p = lp.get(turno);//preguntasHechas=i+1-1=i
		return String.valueOf(p.getRespuestaCorrecta());
	}

	public int getTurnos(){
		return this.turnos;
	}

    public void sortRanking() throws RemoteException {
    	Collections.sort(this.lj);
    	for(int i=0; i<lj.size(); i++){
    		lj.toString();
    	}
    }

    public void listo(String nombreJugador) throws RemoteException {
    	System.out.println("\n"+nombreJugador+" listo.");
    	numJugadoresListos++;
    }
    public boolean espera() throws RemoteException {
    	boolean todosListos = false;
    	if((this.numJugadoresListos%this.numJugadores)==0){
    		todosListos=true;
    		sortRanking();
    	}
    	return todosListos;
    }

	public int getNumJugadores() throws RemoteException{
		return numJugadores;
	}

	public String getNombreJugador(int posicion) throws RemoteException{
		return this.lj.get(posicion).getNombre();
	}

	public int getMarcadorJugador(int posicion) throws RemoteException{
		return this.lj.get(posicion).getMarcador();
	}

	public int getMarcadorJugador(String nombre) throws RemoteException{
		int marcador = -1;
		for(int i=0;i<lj.size();i++){
			if(nombre.equals(lj.get(i).getNombre())){
				marcador = lj.get(i).getMarcador();
			}
		}
		return marcador;
	}
}
