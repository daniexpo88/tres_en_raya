package com.ejconcsocket.tablero;

public class ManejaTablero {
	
	public static void pintarTablero(char[][] tablero) {
		for(int i = 0; i<3; i++) {
			for(int j= 0; j<3; j++) {
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
}
