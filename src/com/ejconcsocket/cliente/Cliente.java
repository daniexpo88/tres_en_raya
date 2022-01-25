package com.ejconcsocket.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.ejconcsocket.serializer.Serializer;
import com.ejconcsocket.tablero.ManejaTablero;
import com.ejconcsocket.tablero.Tablero;
import com.ejconcsocket.tablero.ValidaTablero;

public class Cliente {
	// --------------------------PROPIEDADES CLIENTE--------------------------//
	char[][] tablero;
	Socket sc;
	Tablero t = new Tablero();
	DataInputStream bufferEntrada;
	DataOutputStream bufferSalida;
	boolean finalizado;
	// -----------------------------MÉTODOS------------------------------//
	/**
	 * Abre los flujos de datos e inicializa finalizado a false
	 */
	private void abrirFlujos() {
		try {
			sc = new Socket("localhost", 2500);
			bufferSalida = new DataOutputStream(sc.getOutputStream());
			bufferEntrada = new DataInputStream(sc.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finalizado = false;
	}
	
	/**
	 * Empieza el juego y va preguntando al cliente donde quiere colocar la ficha
	 * y manda el tablero a comprobar al servidor cada vez que se coloca una ficha,
	 * si el servidor devuelve finalizado true el juego acaba
	 */
	private void juego() {
			try {
				t.empezarJuego();
				while (!finalizado) {
					tablero = t.jugar();
					byte[] byteArr = Serializer.serialize(tablero); //Serializamos el tablero y lo pasamos a un byte[]
					bufferSalida.writeInt(byteArr.length); // Envío el tamaño del array de bytes.
					System.out.println("Enviado");
					bufferSalida.write(byteArr); //Envio el byte[]
					guardarTablero(byteArr); //Guarda el tablero en su correspondiente archivo
					finalizado = bufferEntrada.readBoolean(); //Lee el boolean que le manda el servidor que indica si el juego ha finalizado o no.
					t.setTablero(cargarTablero("temp\\fichero"+sc.hashCode()+".txt"));
					
				}	
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
	}
	/**
	 * Este método va a guardar el tablero en forma de array de bytes en un archivo temporal.
	 */
	private void guardarTablero(byte[] tablero) {
		
		File file = new File("temp\\fichero"+sc.hashCode()+".txt");
		try {

			// Con este código se agregan los bytes al archivo.
			FileOutputStream fileOuputStream = new FileOutputStream(file);
			fileOuputStream.write(tablero);
			fileOuputStream.close();
			System.out.println("GUARDADO");
		} catch (Exception e) {
			//Manejar Error
		}
	}
	
	/**
	 * Este método va a cargar el char[][] tablero que ha guardado previamente.
	 */
	private char[][] cargarTablero(String fichero) {
		char[][] tableroChar = null;
		FileInputStream fileInputStream = null;
		File file = new File(fichero);
		byte[] fileArray = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(fileArray);
			System.out.println("Holaksaak");
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
	
	/**
	 * Comprueba quien ha sido el ganador y escribe el mensaje.
	 */
	private void ganador() {
		
		ValidaTablero.validar(tablero);
		
		try {
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Este metodo inicia los flujos, a continuación el juego y después declara el ganador.
	 */
	public void iniciar() {
		abrirFlujos();
		juego();
		ganador();
	}
	
	
	//------------------------------------------MAIN---------------------------------//
	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.iniciar();
	}

}
