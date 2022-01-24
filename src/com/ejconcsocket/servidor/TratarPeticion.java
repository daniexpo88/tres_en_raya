package com.ejconcsocket.servidor;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.ejconcsocket.serializer.Serializer;
import com.ejconcsocket.tablero.ValidaTablero;

public class TratarPeticion extends Thread{
	private Socket sc; 
	private int tam;
	private DataInputStream bufferEntrada = null;
	private DataOutputStream bufferSalida = null;
	private char[][] tablero = null;
	private boolean finalizado = false;
	private byte[] arrByte;
	/**
	 * 
	 * @param sc Recibe el socket del servidor
	 */
	public TratarPeticion(Socket sc) {
			this.sc = sc;
	}
	/**
	 * Abre los flujos de datos
	 */
	public void abrirFlujos() {
        try {
        	bufferSalida = new DataOutputStream(sc.getOutputStream());
            bufferEntrada = new DataInputStream(sc.getInputStream());
        } catch (IOException e) {
            System.err.println("Error en la apertura de flujos");
        }
    }
	/**
	 * Lee el tablero que envía el cliente
	 * @return devuelve el tablero
	 */
	private void leerTablero(){
		try {
			tam = bufferEntrada.readInt();
			if(tam > 0) {
				arrByte = new byte[tam];
				bufferEntrada.readFully(arrByte, 0, arrByte.length);
			}
			tablero = (char[][])Serializer.deserialize(arrByte);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Valida el tablero y manda si la partida ha finalizado o no al cliente
	 * @return Devuelve true si la partida ha finalizado
	 */
	private boolean validacion() {
		try {
			finalizado = ValidaTablero.validar(tablero);
			bufferSalida.writeBoolean(finalizado);
			bufferSalida.flush();
		}catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		return finalizado;
	}
	//----------------------------------RUN--------------------------------//
	/**
	 * Lee el char[][] que le envía el cliente, lo valida
	 * una vez validado envía al cliente si la partida ha finalizado
	 */
	public void run() {
		abrirFlujos();
			while(!finalizado) {
				leerTablero();
				finalizado = validacion();
			}
			Servidor.numCon --;
			System.out.println("CONECTADOS: "+Servidor.numCon);
	}
	
	
}
