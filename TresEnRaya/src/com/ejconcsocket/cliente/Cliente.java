package com.ejconcsocket.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.ejconcsocket.serializer.Serializer;
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

				byte[] byteArr = Serializer.serialize(tablero);

				bufferSalida.writeInt(byteArr.length); // Envío el tamaño del array de bytes.
				System.out.println("Enviado");
				bufferSalida.write(byteArr);

				finalizado = bufferEntrada.readBoolean();
			}
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
	}
	
	private void ganador() {
		
		ValidaTablero.validar(tablero);
		
		try {
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
