
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
//Típico servidor de las practicas que simplemente conecta cliente con servidor
class ServidorJuego  {
    static public void main (String args[]) {
       if (args.length!=1) {
            System.err.println("Uso: ServidorJuego numPuertoJuego");
            return;
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            JuegoImpl srv = new JuegoImpl();
            Naming.rebind("rmi://localhost:" + args[0] + "/Juego", srv);
        }
//Tenemos que meterle que cuando los usuarios respondan, archive las puntuaciones y alfinal las muestre,
//Tiene que contar las rondas, tiene que identificar a cada usuario ( tenemos que meter por línea de comandos cuando
//ejecutamos los usuarios, cada uno su nombre o un número que lo identifique, y así los identificaremos unequívocamente
//El servidor tiene que observar cuando los usuarios responden a cada pregunta, y así permitir mandar la siguiente, todo
//esto hay que pensarlo bien y hacerlo
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en ServidorJuego:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
