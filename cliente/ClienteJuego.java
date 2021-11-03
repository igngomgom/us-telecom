
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.lang.*;

class ClienteJuego {
    static public void main (String args[]) {
        if (args.length!=3) {
            System.err.println("Uso: ClienteJuego hostregistro numPuertoRegistro jugador");
            return;
        }

       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            Juego juego = (Juego) Naming.lookup("rmi://" + args[0] + ":" + args[1] + "/Juego");
            titulo();
            System.in.read();
            String nombreJugador = args[2];
			juego.crearUsuario(nombreJugador);
			
			
			for(int turno=0;turno<juego.getTurnos();turno++){
				clear();
				System.out.println("\n   "+(turno+1)+". "+juego.getPregunta(turno)+"\n");
				long tiempo_inicial = System.currentTimeMillis();
				System.out.println("   Respuesta: "); //le pedimos que responda
				Scanner teclado = new Scanner(System.in);
				char c = teclado.next().charAt(0);
				// Esta variable sirve para comprobar que el usuario meta correctamente la respuesta
				boolean caracterCorrecto = false;
				while(!caracterCorrecto){
					if ( c == 'a' || c == 'b' || c == 'c' ){
						caracterCorrecto = true;

						long tiempo_final = System.currentTimeMillis();
						int puntos = calcularPuntos(tiempo_final, tiempo_inicial);

						boolean resultado= juego.setRespuesta(nombreJugador,turno,c,puntos);
                        clear();
                        System.out.println("\n\n");
						if (resultado == true){
							System.out.println("\n   ¡Correcto! ¡Has ganado "+puntos+" puntos!\n");
						}else{
							System.out.println("   ¡Mal!, respuesta correcta: "+juego.getRespuestaCorrecta(turno)+"\n");
						}
						System.out.println("   Puntuación: "+juego.getMarcadorJugador(nombreJugador));
						// Aquí comprobamos que todos los usuarios hayan respondido para seguir jugando
						// Excepto en el ultimo turno porque ha acabado el juego

						juego.listo(nombreJugador);
						boolean todosListos = false;
						System.out.println("   Esperando al resto de jugadores...");
                        esperando();
                        Thread.sleep(2000);
						while(!todosListos){
							todosListos=juego.espera();
							Thread.sleep(100);
						}
						// Por ultimo imprimimos la tabla de puntuaciones
						clear();
						CommandLineTable st = new CommandLineTable();
				        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
				        st.setHeaders("POSICIÓN", "JUGADOR", "MARCADOR");//optional - if not used then there will be no header and horizontal lines
				        for(int i=0;i<juego.getNumJugadores();i++){
				        	st.addRow(Integer.toString(i+1), juego.getNombreJugador(i), Integer.toString(juego.getMarcadorJugador(i)));
				        }
                        System.out.println("\n  TABLA DE PUNTUACIONES TURNO "+(turno+1)+"\n");
				        st.print();
                        System.out.println("\n Pulsa una tecla para continuar.");
						System.in.read();
						
					} else {
						System.out.println("   \""+String.valueOf(c)+"\" no es una respuesta válida. Inténtelo de nuevo: ");
						c = teclado.next().charAt(0);
					}
				}
			}
			System.out.println("\n   ¡El ganador es "+juego.getNombreJugador(0)+"!");
			System.out.println("\n   Gracias por jugar.\n");
            congrats();
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Excepcion en ClienteJuego:");
            e.printStackTrace();
        }
    }

    public static int calcularPuntos(long tiempo_final, long tiempo_inicial){
    	long tiempo_respuesta = tiempo_final-tiempo_inicial;
    	double puntos = (100000.0/tiempo_respuesta);
    	return (int)puntos;
    }

    public static void clear(){
    	System.out.print("\033[H\033[2J");
    	System.out.flush();
    }

    public static void titulo(){
    	clear();
    	System.out.println("                                                                            ");
    	System.out.println("                                                                            ");
    	System.out.println("                                                                            ");
    	System.out.println("                                                                            ");
    	System.out.println("    ██                                                         █            ");
    	System.out.println("    ██      ██              ██                                ██         ███");
    	System.out.println("    ██     ██               ██                           ███████████     ███");
    	System.out.println("    ██    ██                ██                                ██         ███");
    	System.out.println("    ██   ██                 ██                                ██         ███");
    	System.out.println("    ██  ██                  ██                                ██         ███");
    	System.out.println("    █████                   ██                                ██         ███");
    	System.out.println("    ████                    ██                                ██         ███");
    	System.out.println("    █████        ██████     ████████     ██████     ██████    ██         ███");
    	System.out.println("    ██  ██      ██    ██    ███   ███   ██    ██   ██    ██   ██         ███");
    	System.out.println("    ██   ██     ██    ██    ██     ██   ██    ██   ██    ██   ██          ██");
    	System.out.println("    ██    ██    ██    ██    ██     ██   ██    ██   ██    ██   ██          ██");
    	System.out.println("    ██     ██    ███████    ██     ██    ██████     ██████    ██          ██");
    	System.out.println("                       ██                                      ██         ██");
    	System.out.println("                                                                █           ");
    	System.out.println("                                                                          ██");
    	System.out.println("                     ¡Pulsa una tecla para empezar!                         ");
    	System.out.println("                                                                            ");
    	System.out.println("                                                                            ");
    }

    public static void esperando(){
        System.out.println("                                                  ██████████████████████    ");
        System.out.println("                                                   ██   ██  ██  ██   ██     ");
        System.out.println("                                                   ██     ██  ██     ██     ");
        System.out.println("                                                   ███      ██      ███     ");
        System.out.println("                                                     ███          ███       ");
        System.out.println("                                                       ███  ██  ███         ");
        System.out.println("                                                         ███  ███           ");
        System.out.println("                                                       ███      ███         ");
        System.out.println("                                                     ███    ██    ███       ");
        System.out.println("                                                   ███              ███     ");
        System.out.println("                                                   ██       ██       ██     ");
        System.out.println("                                                   ██     ██  ██     ██     ");
        System.out.println("                                                  ██████████████████████    ");
    }

    public static void congrats(){
        System.out.println("                                                                  _         ");
        System.out.println("                                                                 | |        ");
        System.out.println("                                   ___ ___  _ __   __ _ _ __ __ _| |_ ___   ");
        System.out.println("                                  / __/ _ \\| '_ \\ / _` | '__/ _` | __/ __|  ");
        System.out.println("                                 | (_| (_) | | | | (_| | | | (_| | |_\\__ \\  ");
        System.out.println("                                  \\___\\___/|_| |_|\\__, |_|  \\__,_|\\__|___/  ");
        System.out.println("                                                   __| |                    ");
        System.out.println("                                                  |___/                     ");
        System.out.println("");               
    }
}