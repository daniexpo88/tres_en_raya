package com.ejconcsocket.tablero;

import java.io.Serializable;
import java.util.Scanner;

public class Tablero implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char vacio = ' '; // Posición vacía
	private char[][] tablero = new char[3][3]; // Array con tablero 3x3
	Scanner sc = new Scanner(System.in);

	public Tablero() {

	}

	public Tablero(char[][] tablero) {
		this.tablero = tablero;
	}

	public char[][] pasarTablero() {
		return this.tablero;
	}

	public void setTablero(char[][] t) {
		this.tablero = t;
	}

	/**
	 * Empieza el juego y pinta el tablero
	 */
	public void empezarJuego() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tablero[i][j] = vacio;
				System.out.print(Character.toString(tablero[i][j]));
				if (j < 2) {
					System.out.print(" | ");
				} else {
					System.out.println();
					if (i != 2) {
						System.out.println("--+---+--");
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param ficha la ficha a la que le toca poner ficha
	 * @param lugar donde va a colocar la ficha en caso de estar vacio
	 * @return el char[][] tablero
	 */
	
	public char[][] jugar(char ficha, int lugar) {
		if (lugar == 1) {
			this.tablero[0][0] = this.tablero[0][0] == this.vacio ? ficha : this.tablero[0][0];
		} else if (lugar == 2) {
			this.tablero[0][1] = this.tablero[0][1] == this.vacio ? ficha : this.tablero[0][1];
		} else if (lugar == 3) {
			this.tablero[0][2] = this.tablero[0][2] == this.vacio ? ficha : this.tablero[0][2];
		} else if (lugar == 4) {
			this.tablero[1][0] = this.tablero[1][0] == this.vacio ? ficha : this.tablero[1][0];
		} else if (lugar == 5) {
			this.tablero[1][1] = this.tablero[1][1] == this.vacio ? ficha : this.tablero[1][1];
		} else if (lugar == 6) {
			this.tablero[1][2] = this.tablero[1][2] == this.vacio ? ficha : this.tablero[1][2];
		} else if (lugar == 7) {
			this.tablero[2][0] = this.tablero[2][0] == this.vacio ? ficha : this.tablero[2][0];
		} else if (lugar == 8) {
			this.tablero[2][1] = this.tablero[2][1] == this.vacio ? ficha : this.tablero[2][1];
		} else if (lugar == 9) {
			this.tablero[2][2] = this.tablero[2][2] == this.vacio ? ficha : this.tablero[2][2];
		}
		
		return this.tablero;
	}

}
