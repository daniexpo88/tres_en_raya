package com.ejconcsocket.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	
	private static ServerSocket servaddr;
	private static Socket sc;
	public static int numCon=0;
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		try { 
			servaddr = new ServerSocket(2500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SE INICIA EL SERVIDOR");
		while(true) {
			try {
				sc = servaddr.accept();
			}catch(Exception e) {
				e.printStackTrace();
			}
			TratarPeticion tp = new TratarPeticion(sc);
			tp.start();
			numCon++;
			System.out.println("CONECTADOS: " + numCon);
		}
	}
}
