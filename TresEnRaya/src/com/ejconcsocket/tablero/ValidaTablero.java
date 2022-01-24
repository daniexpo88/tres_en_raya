package com.ejconcsocket.tablero;

public class ValidaTablero {
	static boolean full = true;
	static char vacio = ' ';
	static char fichaGanadora;
	public static boolean validar(char[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if (tablero[i][j] == vacio) {
					full = false;
				}
			}
		}
		if (full) {
			System.out.println("La partida ha acabado en tablas");
			return true;
		}
		if (tablero[0][0] != vacio && tablero[0][0] == tablero[0][1] && tablero[0][1] == tablero[0][2] && tablero[0][0] == tablero[0][0]) {
			
			System.out.println("Ganador " + tablero[0][0]);
			
			return true;
		} else if (tablero[0][0] != vacio && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] == tablero[0][0]) {
			
			System.out.println("Ganador " + tablero[0][0]);
			
			return true;
		} else if (tablero[0][0] != vacio && tablero[0][0] == tablero[1][0] && tablero[1][0] == tablero[2][0] && tablero[0][0] == tablero[0][0]) {
			
			System.out.println("Ganador " + tablero[0][0]);
			
			return true;
		} else if (tablero[0][1] != vacio && tablero[0][1] == tablero[1][1] && tablero[1][1] == tablero[2][1] && tablero[0][1] == tablero[0][0]) {
			
			System.out.println("Ganador " + tablero[0][1]);
			
			return true;
		} else if (tablero[0][2] != vacio && tablero[0][2] == tablero[1][2] && tablero[1][2] == tablero[2][2] && tablero[0][2] == tablero[0][0]) {
			
			System.out.println("Ganador " + tablero[0][2]);
			
			return true;
		} else if (tablero[0][2] != vacio && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] == tablero[0][0]) {
			
			System.out.println("Ganador " + tablero[0][2]);
			
			return true;
		} else if (tablero[1][0] != vacio && tablero[1][0] == tablero[1][1] && tablero[1][1] == tablero[1][2] && tablero[1][0] == tablero[0][0]) {
			
			System.out.println("Ganador " + tablero[1][0]);
			
			return true;
		} else if (tablero[2][0] != vacio && tablero[2][0] == tablero[2][1] && tablero[2][1] == tablero[2][2] && tablero[2][0] == tablero[0][0]) {
			
			System.out.println("Ganador " + tablero[2][0]);
			
			return true;
		} else {
			return false;
		}
	}
}
