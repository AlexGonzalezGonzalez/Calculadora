
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.NaN;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase que contiene la interfaz grafica de la calculadora
 *
 * @author agonzalezgonzalez
 */
public final class Vista extends JPanel implements ActionListener {

    //Declaracion de variables
    static char raiz = '\u221A';

    private int x = 10, y = 80;

    //componentes de la ventana
    static JFrame ventana;

    static JLabel txt;

    JLabel titulo;

    static JButton b0 = new JButton("0"), bRetroceso = new JButton("DEL"), bOff = new JButton("OFF"), bAC = new JButton("AC");

    static JButton[] botones = new JButton[]{new JButton("1"), new JButton("4"), new JButton("7"),
        new JButton("2"), new JButton("5"), new JButton("8"),
        new JButton("3"), new JButton("6"), new JButton("9")};

    static JButton[] operaciones = new JButton[]{new JButton("+"), new JButton("-"), new JButton("x"),
        new JButton("/"), new JButton("" + raiz), new JButton(new String("*")), new JButton("=")};

    static JPanel pBotones, pTitulo;

    /*
    Constructor
     */
    Vista() {

        //Instancia de objetos
        ventana = new JFrame("Calculadora");
        pBotones = new JPanel();
        pTitulo = new JPanel();
        titulo = new JLabel("Calculadora");

        //Layout null para posiciones absolutas
        this.setLayout(null);
        
        //Evento de teclado
        this.addKeyListener(new Teclado());

        //Que el panel siempre tenga el focus para el teclado
        this.setFocusable(true);

        //Metodos que posicionan los componentes
        posicionBotones();
        posicionOperaciones();
        posicionCuadroTexto();
        posicionTitulo();

        //JPanel que contienes los botones
        pBotones.setLayout(null);
        pBotones.setBounds(txt.getX(), txt.getY() + txt.getHeight() + 10, txt.getWidth(), operaciones[6].getY() + operaciones[6].getHeight() + 20);
        pBotones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        //JPanel que contiene el JLabel con el titulo `Calculadora´
        pTitulo.setBounds(txt.getX(), 10, txt.getWidth(), txt.getHeight());
        pTitulo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        //Añadimos los paneles al JPanel del JFrame
        this.add(pTitulo);
        this.add(pBotones);

        //Modificaciones del JFrame
        ventana.add(this);
        ventana.setSize(600, 550);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(3);
        //ventana.setUndecorated(true);
        ventana.setVisible(true);
    }

    /**
     * Metodo que llama a los metodos que posicionan los componentes y añade una
     * fuente de texto al JLabel y lo añade al panel del titulo
     */
    public void posicionTitulo() {
        //JLabel que muestra el titulo ``Calculadora´´
        titulo.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 35));
        pTitulo.add(titulo);
    }

    /**
     * Metodo que posiciona los botones del array (botones), b0, bOff y bAC. Tambien les añade el ActionListener, una
     * fuente de texto y los añade al panel botones (pBotones).
     */
    public void posicionBotones() {
        
        
        for (int i = 0; i < botones.length; i++) {
            botones[i].addActionListener(this);
            botones[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));

            switch (i) {
                case 0:
                    break;

                case 3:
                    x += 100;
                    y = 80;
                    break;

                case 6:
                    x += 100;
                    y = 80;
                    break;

                default:
                    y += 63;
                    break;

            }

            botones[i].setBounds(x, y, 80, 45);
            botones[i].setBorder(BorderFactory.createRaisedBevelBorder());
            botones[i].setFocusable(false);
            pBotones.add(botones[i]);

        }

        bRetroceso.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        bRetroceso.setBounds(x + 197, botones[6].getY(), 80, 45);
        bRetroceso.setBorder(BorderFactory.createRaisedBevelBorder());
        bRetroceso.setFocusable(false);
        bRetroceso.addActionListener(this);
        pBotones.add(bRetroceso);

        b0.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        b0.setBounds(10, 80 + 63 * 3, (35 * 2) + (70 * 3), 45);
        b0.setBorder(BorderFactory.createRaisedBevelBorder());
        b0.setFocusable(false);
        b0.addActionListener(this);
        pBotones.add(b0);

        bOff.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        bOff.setBounds(botones[6].getX() + 197, botones[6].getY() - 63, 80, 45);
        bOff.setBorder(BorderFactory.createRaisedBevelBorder());
        bOff.setFocusable(false);
        bOff.addActionListener(this);
        pBotones.add(bOff);

        bAC.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        bAC.setBounds(botones[6].getX() + 100, bOff.getY(), 80, 45);
        bAC.setBorder(BorderFactory.createRaisedBevelBorder());
        bAC.setFocusable(false);
        bAC.addActionListener(this);
        pBotones.add(bAC);

    }

    /**
     * Metodo que posiciona los botones del array (operaciones), la posicion de
     * estos depende del boton '3' (posicion 6 del array), es decir, si mueves
     * el boton '3' mueves todas las operaciones. Tambien les añade el
     * ActionListener, una fuente de texto y los añade al panel botones
     * (pBotones).
     */
    public void posicionOperaciones() {

        x = botones[6].getX() + 100;
        y = botones[6].getY();

        for (int i = 0; i < operaciones.length; i++) {

            operaciones[i].setBounds(x, y, 80, 45);
            operaciones[i].setBorder(BorderFactory.createRaisedBevelBorder());
            operaciones[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
            operaciones[i].setFocusable(false);
            operaciones[i].addActionListener(this);
            pBotones.add(operaciones[i]);

            if (i == 3) {
                x = x + 97;
                y = botones[6].getY();
            }

            y = y + 63;
        }
    }

    /**
     * Metodo que en el label txt, lo instancia, posiciona, añade una fuente de
     * texto, alinea el texto a la derecha, añade un borde,en este label es
     * donde se muestran las operaciones y resultados y añade este al panel
     * principal (this).
     */
    public void posicionCuadroTexto() {

        txt = new JLabel();
        txt.setBounds(50, 75, 500, 60);
        txt.setFont(new Font(Font.MONOSPACED, Font.BOLD, 35));
        txt.setHorizontalAlignment((int) JTextField.RIGHT);
        txt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.add(txt);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (((JButton) e.getSource()).getText()) {

                case "=":
                    Controlador.mostrarResultado();
                    break;

                case "DEL":
                    Controlador.borrar();
                    break;

                case "AC":
                    Controlador.setMensaje("", "");
                    break;

                case "OFF":
                    Controlador.desconexion();
                    break;

                default: {

                    Controlador.setMensaje(txt.getText(), ((JButton) e.getSource()).getText());

                }

            }
        } catch (ExcepcionOperador ex) {
            
        }
    }
}
