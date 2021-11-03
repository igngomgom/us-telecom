
import java.rmi.*;

//Interfaz de la clase Pregunta, contendrá un objeto String con el texto de la pregunta y sus opciones, por ejemplo:
//El texto del String sería todo lo siguiente hasta Einstein:
//Quién es el mas cabezón? 
//a: Balón, b: Edu, c:Einstein  

//La pregunta se mete en el mismo constructor, ver constructor en PreguntaImpl.java, a la vez que se le mete la opción 
//correcta, en este caso es la b.
interface Pregunta extends Remote {
    char getRespuestaCorrecta() throws RemoteException; //Devuelve la respuesta correcta (no hay por que usarlo este metodo)
    String getPregunta() throws RemoteException; //Devuelve la pregunta para imprimirla por pantalla en el cliente
	String getOpciones() throws RemoteException;
    void setRespuestaUsuario(char resp) throws RemoteException; //Para meter la respuesta del usuario, leida previamente por teclado en el cliente
    boolean comprueba() throws RemoteException; //Compara la respuesta metida por el usuario y la designada como correcta en su construcción
}
