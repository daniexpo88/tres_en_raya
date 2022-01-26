package com.ejconcsocket.tablero;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;


import com.ejconcsocket.serializer.Serializer;

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
	
	
	/**
	 * Este método va a guardar el tablero en forma de array de bytes en un archivo temporal.
	 */
	public static void guardarTablero(char[][] tableroChar) {
		byte[] tablero;
		try {
			tablero = Serializer.serialize(tableroChar);
			File file = new File("temp\\fichero.txt");
			FileOutputStream fileOuputStream = new FileOutputStream(file);
			fileOuputStream.write(tablero);
			fileOuputStream.close();
			System.out.println("GUARDADO");
			ManejaTablero.pintarTablero(ManejaTablero.cargarTablero());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Este método va a cargar el char[][] tablero que ha guardado previamente.
	 */
	public static char[][] cargarTablero() {
		char[][] tableroChar = null;
		FileInputStream fileInputStream = null;
		File file = new File("temp\\fichero.txt");
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
//	public static void guardarTablero(Tablero t) {
//		try {
//			FileWriter csvWriter = new FileWriter("partidas.csv");
//			
//			char[] arr =t.pasarTablero().toString().toCharArray();
//			
//			csvWriter.append(arr.toString());
//			csvWriter.append(',');
//			csvWriter.append(LocalDate.now().toString());
//			csvWriter.close();
//			System.out.println("GUARDADO");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
//	public static boolean comprobarSiExiste(Tablero t) {
//		
//		char[] arr;
//		try {
//			arr =t.pasarTablero().toString().toCharArray();
//			BufferedReader csvReader;
//			csvReader = new BufferedReader(new FileReader("partidas.csv"));
//		
//			String row;
//			boolean exists = false;
//			while ((row = csvReader.readLine()) != null) {
//			    String[] data = row.split(",");
//			    if(data[0].equals(arr.toString())){
//			    	exists = true;
//			    	System.out.println("ENCONTRADO");
//			    	
//			    }
//			}
//			csvReader.close();
//			return exists;
//			}catch (Exception e) {
//				System.out.println("NO ENCONTRADO");
//				return false;
//			}
//	}
	
	public static void main(String[] args) {
		
	}
}
