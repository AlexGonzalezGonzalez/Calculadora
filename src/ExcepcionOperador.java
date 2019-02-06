
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *Clase que se encarga de mostrar todos los errores que se pueden cometer en esta aplicacion
 * y los muestra con un JOptionPane
 * @author agonzalezgonzalez
 */
public class ExcepcionOperador extends Exception {

    ExcepcionOperador() {
        
        //Si no se envia mensaje de error, se muestra este por defecto
        JOptionPane.showMessageDialog(Vista.pBotones.getParent(), "Error en la calculadora");
    }

    ExcepcionOperador(String mensaje) {
        
        //Si se envia mensaje de error lo muestra en el JOptionPane
        JOptionPane.showMessageDialog(Vista.pBotones.getParent(), mensaje);
    }

}
