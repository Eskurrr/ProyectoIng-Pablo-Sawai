package connect;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Scanner;

import graph.Graph;

import java.util.ArrayList;

public class Server {
	public static Scanner teclado = new Scanner(System.in);
    protected int stp = 22;
	public int getStp() {
		return stp;
	}
	public void setStp(int stp) {
		this.stp = stp;
	}





	public List<Integer> ServerC() {
		Graph graph = new Graph();
		try {
			System.out.println("LocalHost = " + InetAddress.getLocalHost().toString());
		} catch (UnknownHostException uhe) {
			System.err.println("No puedo saber la dirección IP local : " + uhe);
		}

		ServerSocket ss = null;
		List<Integer> receivedIntegers = new ArrayList<>(); // Declare a list to store received integers

		try {
			ss = new ServerSocket(1235);
		} catch (IOException ioe) {
			System.err.println("Error al abrir el socket de servidor : " + ioe);
			System.exit(0);
		}
		boolean continiou = true;
		// Bucle infinito
		while (continiou) {
			try {
				// Esperamos a que alguien se conecte a nuestro
				Socket sckt = ss.accept();
				DataInputStream dis = new DataInputStream(sckt.getInputStream());

				// Continuously read and print integers sent by the client
				boolean stop = false;
				while (!stop) {
					try {
						new Thread(() -> {
							try {
								int receivedInteger = dis.readInt();
								if (receivedInteger != 0) {
									// Print the received integer
									System.out.println("Received integer from client: " + receivedInteger);

									// Add the received integer to the list
									receivedIntegers.add(receivedInteger);
								    setStp(graph.addData(receivedInteger));
								}
							} catch (IOException e) {
								e.printStackTrace(); 
							}
						}).start();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (getStp() == 11) {
						System.out.println("Data collection stopped");
						dis.close();
						ss.close();
						continiou = false;
						stop = true;
					}
				}

			} catch (Exception e) {
				continiou = false;
				System.err.println("Se ha producido la excepción : " + e);
				System.out.println("Conexion cerrada");
			}

		}
		return receivedIntegers;
	}
}