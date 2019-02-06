
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase que gestiona las entradas por teclado
 * @author agonzalezgonzalez
 */
public class Teclado extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        try{
            
        if (e.getKeyChar() == '1') {
            Controlador.setMensaje(Controlador.getMensaje() + "1","");
        }
        if (e.getKeyChar() == '2') {
            Controlador.setMensaje(Controlador.getMensaje() + "2","");
        }
        if (e.getKeyChar() == '3') {
            Controlador.setMensaje(Controlador.getMensaje() + "3","");
        }
        if (e.getKeyChar() == '4') {
            Controlador.setMensaje(Controlador.getMensaje() + "4","");
        }
        if (e.getKeyChar() == '5') {
            Controlador.setMensaje(Controlador.getMensaje() + "5","");
        }
        if (e.getKeyChar() == '6') {
            Controlador.setMensaje(Controlador.getMensaje() + "6","");
        }
        if (e.getKeyChar() == '7') {
            Controlador.setMensaje(Controlador.getMensaje() + "7","");
        }
        if (e.getKeyChar() == '8') {
            Controlador.setMensaje(Controlador.getMensaje() + "8","");
        }
        if (e.getKeyChar() == '9') {
            Controlador.setMensaje(Controlador.getMensaje() + "9","");
        }
        if (e.getKeyChar() == '0') {
            Controlador.setMensaje(Controlador.getMensaje() + "0","");
        }
        if (e.getKeyChar() == '/') {
            Controlador.setMensaje(Controlador.getMensaje() + "/","");
        }
        if (e.getKeyChar() == '*') {
            Controlador.setMensaje(Controlador.getMensaje() + "*","");
        }
        if (e.getKeyChar() == 'x') {
            Controlador.setMensaje(Controlador.getMensaje() + "x","");
        }
        if (e.getKeyChar() == '+') {
            Controlador.setMensaje(Controlador.getMensaje() + "+","");
        }
        if (e.getKeyChar() == '-') {
            Controlador.setMensaje(Controlador.getMensaje() + "-","");
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            Controlador.mostrarResultado();
        }
        }catch(ExcepcionOperador ex){
            
        }

    }
}
