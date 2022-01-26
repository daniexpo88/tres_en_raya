package com.ejconcsocket.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JFrame;

import com.ejconcsocket.grafico.partida.MarcoPrincipal;
import com.ejconcsocket.serializer.Serializer;
import com.ejconcsocket.tablero.Tablero;


public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------PROPIEDADES CLIENTE--------------------------//
	char[][] tablero;
	Socket sc;
	Tablero t = new Tablero();
	DataInputStream bufferEntrada;
	DataOutputStream bufferSalida;
	int finalizado;
	// -----------------------------MÉTODOS------------------------------//
	/**
	 * Abre los flujos de datos e inicializa finalizado a false
	 */
	public void abrirFlujos() {
		try {
			System.out.println("Hola");
			sc = new Socket("localhost", 2500);
			System.out.println("HOLA");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finalizado = -1;
	}
	
	/**
	 * Empieza el juego y va preguntando al cliente donde quiere colocar la ficha
	 * y manda el tablero a comprobar al servidor cada vez que se coloca una ficha,
	 * si el servidor devuelve finalizado true el juego acaba
	 * @throws IOException 
	 */
	public int juego(Tablero t) {
		try {
			byte[] byteArr = Serializer.serialize(t.pasarTablero());
			bufferSalida = new DataOutputStream(sc.getOutputStream());
			bufferEntrada = new DataInputStream(sc.getInputStream());
			System.out.println("ANTES DE ESCRIBIR");
			//Serializamos el tablero y lo pasamos a un byte[]
			bufferSalida.writeInt(byteArr.length); // Envío el tamaño del array de bytes.
			System.out.println("ABRE LOS FLUJOS");
			System.out.println("Enviado");
			bufferSalida.write(byteArr); //Envio el byte[]
			
			finalizado = bufferEntrada.readInt(); //Lee el boolean que le manda el servidor que indica si el juego ha finalizado o no.
			return finalizado;
		}catch(Exception e) {
			e.printStackTrace();
			return finalizado;
		}
		
	}
	
	/**
	 * Comprueba quien ha sido el ganador y escribe el mensaje.
	 */

	/**
	 * Este metodo inicia los flujos, a continuación el juego y después declara el ganador.
	 */
	public void iniciar() {
		MarcoPrincipal mp = new MarcoPrincipal(this, sc);
		mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public String getNombre() {
		return String.valueOf(this.hashCode());
	}
	
	//------------------------------------------MAIN---------------------------------//
	public static void main(String[] args)  {
		Cliente c = new Cliente();
		c.iniciar();
	}

}
