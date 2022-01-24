package com.ejconcsocket.grafico.partida;

import java.awt.BorderLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Menu() {

		//MENÚ PARTIDA
		JMenu partida = new JMenu("Partida");
		JMenuItem partidaNueva = new JMenuItem("Partida nueva");
		JMenuItem partidaCargar = new JMenuItem("Cargar partida");
		JMenuItem historial = new JMenuItem("Historial");
		partida.add(partidaNueva);
		partida.add(partidaCargar);
		partida.add(historial);
		//MENU OPCIONES
		JMenu opciones = new JMenu("Opciones");
		JMenuItem colorFondo = new JMenuItem("Color del fondo");
		JMenuItem salir = new JMenuItem("Salir");
		opciones.add(colorFondo);
		opciones.add(salir);
		//AÑADO LOS MENU AL MENUBAR
		add(partida);
		add(opciones);
	}
	
	public Menu getMenu() {
		return this;
	}
}
