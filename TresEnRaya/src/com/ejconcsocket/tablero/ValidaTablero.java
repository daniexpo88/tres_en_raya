package com.ejconcsocket.tablero;

public class ValidaTablero {
	private char vacio = ' ';
	/**
	 * Recibe un char[][] con los datos de un tablero, valida si alguien ha ganado.
	 * @param tablero
	 * @return 0 si gana alguien, 1 si hay empate, -1 no ha finalizado
	 */
	public int validar(char[][] tablero) {
	
		if (tablero[0][0] != vacio && tablero[0][0] == tablero[0][1] && tablero[0][1] == tablero[0][2]) {
			
			System.out.println("Ganador " + tablero[0][0]);
			
			return 0;
		} else if (tablero[0][0] != vacio && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
			
			System.out.println("Ganador " + tablero[0][0]);
			
			return 0;
		} else if (tablero[0][0] != vacio && tablero[0][0] == tablero[1][0] && tablero[1][0] == tablero[2][0]) {
			
			System.out.println("Ganador " + tablero[0][0]);
			
			return 0;
		} else if (tablero[0][1] != vacio && tablero[0][1] == tablero[1][1] && tablero[1][1] == tablero[2][1]) {
			
			System.out.println("Ganador " + tablero[0][1]);
			
			return 0;
		} else if (tablero[0][2] != vacio && tablero[0][2] == tablero[1][2] && tablero[1][2] == tablero[2][2]) {
			
			System.out.println("Ganador " + tablero[0][2]);
			
			return 0;
		} else if (tablero[0][2] != vacio && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
			
			System.out.println("Ganador " + tablero[0][2]);
			
			return 0;
		} else if (tablero[1][0] != vacio && tablero[1][0] == tablero[1][1] && tablero[1][1] == tablero[1][2]) {
			
			System.out.println("Ganador " + tablero[1][0]);
			
			return 0;
		} else if (tablero[2][0] != vacio && tablero[2][0] == tablero[2][1] && tablero[2][1] == tablero[2][2]) {
			
			System.out.println("Ganador " + tablero[2][0]);
			
			return 0;
		}
		else if (lleno(tablero)) {
			System.out.println("La partida ha acabado en tablas");
			return 1;
		}else {
			return -1;
		}
	}
	/**
	 * Comprueba si está lleno
	 * @param tablero
	 * @return
	 */
	private boolean lleno(char[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if (tablero[i][j] == vacio) {
					return false;
				}
			}
		}
		return true;
	}
}
