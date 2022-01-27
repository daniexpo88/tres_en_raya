package com.ejconcsocket.servidor;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.ejconcsocket.serializer.Serializer;
import com.ejconcsocket.tablero.ValidaTablero;

public class TratarPeticion extends Thread{
	private ValidaTablero vt;
	private Socket sc; 
	private int tam;
	private DataInputStream bufferEntrada = null;
	private DataOutputStream bufferSalida = null;
	private char[][] tablero = null;
	private int finalizado = -1;
	private byte[] arrByte;
	
	/**
	 * @param sc Recibe el socket del servidor
	 */
	public TratarPeticion(Socket sc) {
			this.sc = sc;
			vt = new ValidaTablero();
	}
	/**
	 * Abre los flujos de datos
	 */
	private void abrirFlujos() {
        try {
        	bufferSalida = new DataOutputStream(sc.getOutputStream());
            bufferEntrada = new DataInputStream(sc.getInputStream());
            
            System.out.println("Abre flujos de la petición "+Servidor.numCon);
        } catch (IOException e) {
            System.err.println("ERROR EN LA APERTURA DE FLUJOS");
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
			System.err.println("SE HA CERRADO LA PARTIDA");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Valida el tablero y manda si la partida ha finalizado o no al cliente
	 * @return Devuelve true si la partida ha finalizado
	 */
	private int validacion() {
		try {
			finalizado = vt.validar(tablero);
			bufferSalida.writeInt(finalizado);
			bufferSalida.flush();
		}catch(IOException ioe) {
			System.err.println("SE CIERRA LA CONEXIÓN");
			return 1;
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
			while(finalizado==-1) {
				leerTablero();
				finalizado = validacion();
			}
			Servidor.numCon --;
			System.out.println("CONECTADOS: "+Servidor.numCon);
	}
}
