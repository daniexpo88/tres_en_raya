package com.ejconcsocket.tablero;

import java.util.Scanner;

public class Tablero {
	private char vacio = ' '; // Posición vacía
	private char ficha = ' '; // Ficha que le toca el turno
	private final char ficha1 = 'X'; // Ficha X
	private final char ficha2 = 'O'; // Ficha 0
	private char[][] tablero = new char[3][3]; // Array con tablero 3x3
	private int numTurno = 0;
	private int ultimoTurno;
	Scanner sc = new Scanner(System.in);
	public char[][] pasarTablero() {
		return this.tablero;
	}
	
	
	public void empezarJuego() {
		for(int i = 0; i<3; i++) {
			for(int j= 0; j<3; j++) {
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
	 // Número de turnos para conocer a quien le toca colocar ficha

	public char[][] jugar() {
		boolean ocupada = false;
		if (this.numTurno % 2 == 0) {
			this.ficha = this.ficha1;
		} else {
			this.ficha = this.ficha2;
		}
		int lugar;
		do {
		System.out.println("Turno jugador " + this.ficha);
		System.out.println("Donde desea colocar la ficha (1-9)");
		lugar = sc.nextInt();
		if(lugar > 9 || lugar < 1) {
			System.err.println("INTRODUCE UNA POSICIÓN VÁLIDA");
			ManejaTablero.pintarTablero(pasarTablero());
		}
		}while(lugar > 9 || lugar < 1);
		
			if (lugar == 1) {
				if (this.tablero[0][0] == this.ficha1 || this.tablero[0][0] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[0][0] = this.ficha;
				}
			} else if (lugar == 2) {
				if (this.tablero[0][1] == this.ficha1 || this.tablero[0][1] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[0][1] = this.ficha;
				}
			} else if (lugar == 3) {
				if (this.tablero[0][2] == this.ficha1 || this.tablero[0][2] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[0][2] = this.ficha;
				}
			} else if (lugar == 4) {
				if (this.tablero[1][0] == this.ficha1 || this.tablero[1][0] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[1][0] = this.ficha;
				}
			} else if (lugar == 5) {
				if (this.tablero[1][1] == this.ficha1 || this.tablero[1][1] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[1][1] = this.ficha;
				}
			} else if (lugar == 6) {
				if (this.tablero[1][2] == this.ficha1 || this.tablero[1][2] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[1][2] = this.ficha;
				}
			} else if (lugar == 7) {
				if (this.tablero[2][0] == this.ficha1 || this.tablero[2][0] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[2][0] = this.ficha;
				}
			} else if (lugar == 8) {
				if (this.tablero[2][1] == this.ficha1 || this.tablero[2][1] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[2][1] = this.ficha;
				}
			} else if (lugar == 9) {
				if (this.tablero[2][2] == this.ficha1 || this.tablero[2][2] == this.ficha2) {
					System.err.println("Ya hay una ficha colocada ahí");
					ocupada = true;
				} else {
					this.tablero[2][2] = this.ficha;
				}
			}
		ManejaTablero.pintarTablero(pasarTablero());
		this.ultimoTurno = this.numTurno%2;
		if(!ocupada)
		this.numTurno++;
		else jugar();
		return this.tablero;
	}


	public int getUltimoTurno() {
		return ultimoTurno;
	}

}
