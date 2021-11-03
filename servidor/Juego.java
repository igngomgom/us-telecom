
import java.rmi.*;
import java.util.*;

//Interfaz principal del servidor, contiene una lista de preguntas, cada pregunta tiene métodos de manejo, mirar en archivo: Pregunta.java y PreguntaImpl.java.
interface Juego extends Remote {

	void crearUsuario(String u) throws RemoteException;
	int getTurnos() throws RemoteException;
	String getPregunta(int turno) throws RemoteException;
	boolean setRespuesta(String nombreJugador,int turno, char c, int puntos) throws RemoteException;
	String getRespuestaCorrecta(int turno) throws RemoteException;
	void listo(String nombreJugador) throws RemoteException;
	boolean espera() throws RemoteException;
	int getNumJugadores() throws RemoteException;
	String getNombreJugador(int posicion) throws RemoteException;
	int getMarcadorJugador(int posicion) throws RemoteException;
	int getMarcadorJugador(String nombre) throws RemoteException;
}
