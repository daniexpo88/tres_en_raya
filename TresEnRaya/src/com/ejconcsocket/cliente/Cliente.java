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
	
	private Socket sc;
	private DataInputStream bufferEntrada;
	private DataOutputStream bufferSalida;
	private int finalizado;
	
	// -----------------------------MÉTODOS------------------------------//
	/**
	 * Abre los flujos de datos e inicializa finalizado a false
	 */
	public void abrirFlujos() {
		try {
			sc = new Socket("localhost", 2500);
			finalizado = -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Recibe un tablero, lo serializa, lo envía  al servidor, recibe la validación del servidor y devuelve un numero
	 * @return si ha finalizado devuelve 0 (un jugador ha ganado), 1 (empate) o -1 (la partida no ha finalizado)
	 */
	public int juego(Tablero t) {
		try {
			
			byte[] byteArr = Serializer.serialize(t.pasarTablero());
			bufferSalida = new DataOutputStream(sc.getOutputStream());
			bufferEntrada = new DataInputStream(sc.getInputStream());
			//Serializamos el tablero y lo pasamos a un byte[]
			bufferSalida.writeInt(byteArr.length); // Envío el tamaño del array de bytes.
			bufferSalida.write(byteArr); //Envio el byte[]
			
			finalizado = bufferEntrada.readInt(); //Lee el boolean que le manda el servidor que indica si el juego ha finalizado o no.
			return finalizado;
		}catch(Exception e) {
			e.printStackTrace();
			return finalizado;
		}
		
	}
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
