
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
//Esta es la implementación de la clase Pregunta
class PreguntaImpl extends UnicastRemoteObject implements Pregunta {
    //VARIABLES
    private String pregunta;
	private String respuesta1;
	private String respuesta2;
	private String respuesta3;
    private char respuesta_correcta = 'q';
    private char respuesta_usuario = 'q';

    //CONSTRUCTOR
    PreguntaImpl(String cadena) throws RemoteException {
		String [] arrOfStr = cadena.split(";",5);
		pregunta = arrOfStr[0];
		respuesta1= arrOfStr[1];
		respuesta2= arrOfStr[2];
		respuesta3= arrOfStr[3];
		respuesta_correcta = arrOfStr[4].charAt(0);
    }




//Primero se comprueba que el usuario haya respondido cuando se llama a este método, en caso de no haberlo hecho deberiamos
//pedir que vuelva a responder ( tipico bucle del que no sale hasta que no responda o hasta que pasen 5 iteraciones por ejemplo
//y en ese caso pasa su turno
    public boolean comprueba() throws RemoteException{
	boolean verify = false;
	if ( respuesta_usuario == 'q')
		System.out.println("El usuario no ha respondido aún\n");
	else {
//En caso de haber respondido pues comprobamos que su respuesta respuesta_usuario, se corresponda con la correcta, y lo indicamos.
		if ( respuesta_usuario == respuesta_correcta ){
		    verify = true;
		   }
	        else{
	            verify = false;
		   }
	    }
        return verify;

    }


   
    public char getRespuestaCorrecta() throws RemoteException {
        return respuesta_correcta;
    }
    public String getPregunta() throws RemoteException {
        return pregunta;
    }
    public String getOpciones() throws RemoteException{
        return "   a) "+respuesta1+"\n   b) "+respuesta2+"\n   c) "+respuesta3;
   }

    public void setRespuestaUsuario(char resp) throws RemoteException {
	   respuesta_usuario = resp;
    }

}
