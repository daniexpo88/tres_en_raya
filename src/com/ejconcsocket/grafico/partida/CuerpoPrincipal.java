package com.ejconcsocket.grafico.partida;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CuerpoPrincipal extends JPanel{
	JButton jugar;
	JButton historial;
	GridLayout gl;
	JLabel titulo;
	Menu menu;
	Font fuente_titulo = new Font("Algerian", Font.BOLD, 16);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CuerpoPrincipal() {
		gl = new GridLayout(5, 3,2,0);
		setLayout(gl);
		setBackground(Color.BLACK);
		setForeground(Color.GREEN);
		//MENU
		menu = new Menu();
		add(menu);
		//TITULO
		titulo = new JLabel("Tres en raya");
		titulo.setForeground(Color.GREEN);
		titulo.setFont(fuente_titulo);
		//BOTON NUEVA PARTIDA
		
		ButtonsListener bl = new ButtonsListener();
		jugar = new JButton("Jugar");
		jugar.setBackground(Color.BLACK);
		jugar.setForeground(Color.GREEN);
		jugar.addActionListener(bl);
		
		//Boton historial
		historial = new JButton("Historial");
		historial.setBackground(Color.BLACK);
		historial.setForeground(Color.GREEN);
		historial.addActionListener(bl);
		
		add(titulo);
		add(jugar);
		add(historial);
	}
	
	class ButtonsListener implements ActionListener {
		JButton boton;
		boolean pulsado= false;
        public void actionPerformed( ActionEvent e ) {
        	if(e.getSource().equals(historial)) {
        		boton = historial;
        		pulsado = boton.getBackground().equals(Color.GREEN) ? true : false;
        	}else if(e.getSource().equals(jugar)) {
        		boton = jugar;
        		pulsado = boton.getBackground().equals(Color.GREEN) ? true : false;
        	}
        	
            if(!pulsado) {
            	boton.setBackground(Color.GREEN);
            	boton.setForeground(Color.BLACK);
            	pulsado= true;
            }else if(pulsado){
            	boton.setBackground(Color.BLACK);
            	boton.setForeground(Color.GREEN);
            	pulsado=false;
            }
        }

    }
}
