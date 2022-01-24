package com.ejconcsocket.grafico.partida;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MarcoPrincipal extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MarcoPrincipal() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = tk.getScreenSize();
		setSize(600,600);
		
		setLocation(tamanoPantalla.width*1/3 ,tamanoPantalla.height*1/5 );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(anadirCapa(new CuerpoPrincipal()));
		pack();
		setVisible(true);
	}
	
	public JPanel anadirCapa(JPanel jp) {
		return jp;
	}
	public void juego() {
		
	}
}