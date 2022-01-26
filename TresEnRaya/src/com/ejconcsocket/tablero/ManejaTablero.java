package com.ejconcsocket.tablero;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import com.ejconcsocket.serializer.Serializer;

public class ManejaTablero implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void pintarTablero(char[][] tablero) {
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
	
	
	/**
	 * Este método va a guardar el tablero en forma de array de bytes en un archivo temporal.
	 */
	public void guardarTablero(char[][] tableroChar, String cliente) {
		byte[] tablero;
		try {
			tablero = Serializer.serialize(tableroChar);
			File file = new File("temp\\fichero"+cliente+".txt");
			FileOutputStream fileOuputStream = new FileOutputStream(file);
			fileOuputStream.write(tablero);
			fileOuputStream.close();
			System.out.println("GUARDADO");
			pintarTablero(cargarTablero(cliente));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Este método va a cargar el char[][] tablero que ha guardado previamente.
	 */
	public char[][] cargarTablero(String cliente) {
		char[][] tableroChar = null;
		FileInputStream fileInputStream = null;
		File file = new File("temp\\fichero"+cliente+".txt");
		byte[] fileArray = new byte[(int) file.length()+1];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileArray);
			fileInputStream.close();
			tableroChar = (char[][]) Serializer.deserialize(fileArray);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tableroChar;
	}
	
	public void guardarEnElHistorial(String ganador) {
		try {
			File file = new File("temp\\historial.txt");
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(ganador);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String cargarHistorial() {
		
		try {
			File file = new File("temp\\historial.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			int contadorX=0;
			int contadorO=0;
			while((linea = br.readLine())!=null) {
				if(linea.equals("X"))contadorX++;
				if(linea.equals("O"))contadorO++;
				System.out.println(linea);
			}
			String historial = contadorX+" | "+contadorO;
			return historial;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}
	public static void main(String[] args) {
		ManejaTablero mt = new ManejaTablero();
		System.out.println(mt.cargarHistorial());
	}

}
