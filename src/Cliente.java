
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agonzalezgonzalez
 */
public class Cliente {

    InputStream is;
    OutputStream os;
    InetSocketAddress addr;
    Socket clienteSocket;
    byte[] mensaje;

    //Constructor
    Cliente() {
        
        //Nos conectamos al servidor
        conexion();
        System.out.println("Cliente conectado");
    }

    /**
     * Metodo que lee el mensaje que envio el servidor.
     *
     * @return Devuelve un String que es el resultado de la operacion.
     */
    public String clienteLeerResultado() {
        
        //Leemos el mensaje y lo devolvemos en un String
        //Si da error muestra un mensaje
        try {
            mensaje = new byte[50];
            is.read(mensaje);
            

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Vista.ventana, "Error al recibir el mensaje");
        }
        return new String(mensaje);
    }

    /**
     * Metodo que envia la operacion completa al servidor
     *
     * @param msg String que contiene la operacion que se le quiere enviar al
     * servidor.
     */
    public void clienteEscribir(String msg) {
        
        //Escribimos el mensaje que recibimos
        //Si da error muestra un mensaje
        try {
            
            os.write(msg.getBytes());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Vista.ventana, "Error al enviar el mensaje");
        }
    }

    /**
     * Metodo que conecta con el servidor usando socket stream y inicia el
     * inputStream y outputStream
     */
    public void conexion() {

        //Creamos un socket y nos conectamos a la ip y puerto recibidos
        //Si falla la conexion es que el servidor esta cerrado
        try {
            String conexion=JOptionPane.showInputDialog(null, "Ingrese IP:Puerto, Ejemplo 10.0.9.5:4000");
            String[] conn=conexion.split(":");
            
            clienteSocket = new Socket();
            addr = new InetSocketAddress(conn[0], Integer.parseInt(conn[1]));
            clienteSocket.connect(addr);

            is = clienteSocket.getInputStream();
            os = clienteSocket.getOutputStream();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Vista.ventana, "Servidor Cerrado");
            System.exit(0);
        }
    }

    public void desconexion() {
        //Enviamos un mensaje al servidor de cierre y cerramos todo
        //Si falla muestra un mensaje
        try {
            os.write("OFF#".getBytes());
            if (is != null) {
                is.close();
            }

            if (os != null) {
                os.close();
            }

            if (clienteSocket != null) {
                clienteSocket.close();
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Vista.ventana, "Error en el cierre del Socket");
        }
    }
}
