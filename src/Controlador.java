
import java.util.ArrayList;
import java.util.Arrays;
import static jdk.nashorn.internal.objects.Global.Infinity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author agonzalezgonzalez
 */
public class Controlador {

    private static Cliente c;
    private static String mensaje = "";
    private static String lengh;
    private static byte[] arr;
    private static boolean marcaRaiz = false, marcaOp = false;

    Controlador() {
        
        //Creamos el cliente
        c = new Cliente();
        System.out.println("Iniciando interfaz");

        //Iniciamos la interfaz grafica
        Vista i = new Vista();
    }

    public static void mostrarResultado() {
        
        //Ponemos los separadores al mensaje para que el servidor pueda splitearlo
        Controlador.escribir(Controlador.separadores());
        
        //Leemos el mensaje que envio el servidor
        String msg = Controlador.lectura();
        System.out.println(msg);
        //Ponemos una marca para saber que se esta mostrando unicamente el resultado enviado por el servidor
        marcaRaiz = true;
        
        //Si contiene Infinity entonces pone el char del infinito, sino el resultado tal cual
        if (msg.contains("Infinity")) {
            Vista.txt.setText("" + '\u221E');
        } else {
            Vista.txt.setText(Controlador.getMensaje());
        }
    }

    private static void escribir(String msgSeparado) {
        
        //Si contiene el char del infinito lo reemplaza por Infinity para que el servidor lo reconozca
        Controlador.mensaje = msgSeparado.replaceAll("" + '\u221E', "Infinity");
        
        //Escribe el mensaje al servidor 
        Controlador.c.clienteEscribir(Controlador.getMensaje());
        System.out.println("Cliente escribio: " + Controlador.getMensaje());
    }

    private static String lectura() {
        
        //Lee el mensaje del servidor y lo retorna
        Controlador.mensaje = Controlador.c.clienteLeerResultado();
        System.out.println("Cliente recibio: " + Controlador.getMensaje());
        return Controlador.getMensaje();
    }

    private static String separadores() {
        //Metodo que se encarga de añadir los separadores G
        System.out.println("Cliente añadiendo separadores");
        
        //Recogemos el mensaje con las operaciones
        String msg = Controlador.getMensaje();
        
        //Recorremos el array de operaciones para añadir separadores entre los operadores
        for (int i = 0; i < Vista.operaciones.length; i++) {
            
            //Si es una raiz
            if (i == 4) {
                
                //Si despues de la raiz hay un numero negativo reemplaza los separadores del -
                if (msg.contains('\u221A' + "G-")) {
                    msg = msg.replace(Vista.operaciones[i].getText() + "G-G", Vista.operaciones[i].getText() + "G-");
                    
                //Si en un positivo no añade la G a la izquierda
                } else {
                    msg = msg.replace(Vista.operaciones[i].getText(), Vista.operaciones[i].getText() + "G");
                }
                
            //Si no es una raiz pone una G a cada lado del operador
            } else {
                msg = msg.replace(Vista.operaciones[i].getText(), "G" + Vista.operaciones[i].getText() + "G");
            }
        }
        
        //Si el primer numero es negativo entonces se eliminan los separadores de ese -
        if (msg.startsWith("G-")) {
            msg = msg.replaceFirst("G", "").replaceFirst("G", "");
        }
        
        //Añadimos una marca de fin de mensaje
        msg = msg + "#";
        return msg;
    }

    public static void desconexion() {
        
        //Cierra el socket del cliente enviando un mensaje de cierre al servidor y acaba el programa
        System.out.println("Cliente desconectando");
        c.desconexion();
        System.exit(0);
    }

    public static void borrar() throws ExcepcionOperador {
        
        //Elimina el ultimo caracter del mensaje
        Controlador.setMensaje(Controlador.getMensaje().substring(0, Vista.txt.getText().length() - 1), "");
    }

    public static void setMensaje(String mensaje, String boton) throws ExcepcionOperador {
        
        //Asigna el mensaje con el mensaje que ya habia mas el del boton presionado
        Controlador.mensaje = mensaje + boton;

        //Si es un operador
        if (boton.equalsIgnoreCase("+") || boton.equalsIgnoreCase("-") || boton.equalsIgnoreCase("/") || boton.equalsIgnoreCase("x") || boton.equalsIgnoreCase("*")) {
            
            //Si no se pulso antes pone la marcaOp a true
            if (marcaOp == false) {
                marcaOp = true;
                
            //Si la marcaOp ya estaba a true entonces lanza una excepcion
            } else {
                throw new ExcepcionOperador("No se puede escribir dos operadores seguidos");
            }
            
        //Si se pulso un numero la marcaOp se pone a false
        } else {
            marcaOp = false;
        }
        
        //La marca raiz es true cuando solo se esta mostrando el resultado del servidor
        //Justo despues de pulsar el =
        //Entonces se pondra la raiz a la izquierda en ese case en vez de añadirla a la derecha
        if (marcaRaiz == true && boton.equalsIgnoreCase("" + '\u221A')) {
            Controlador.mensaje = boton + mensaje;
            marcaRaiz = false;
        }
        
        //Pone el texto en la JLabel
        Vista.txt.setText(Controlador.getMensaje());
    }

    public static String getMensaje() {
        return mensaje;
    }

}
