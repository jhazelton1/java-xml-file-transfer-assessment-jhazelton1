package com.cooksys.xml_file_transfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static void main(String[] args) {

		try (ServerSocket ss = new ServerSocket(8083);) {
			Socket socket = null;

			while (true) {
				socket = ss.accept();

				System.out.println(socket.getPort() + " Connected.");

//				DataInputStream reader = new DataInputStream(socket.getInputStream());
//				DataOutputStream writer = new DataOutputStream(socket.getOutputStream());

				ClientHandler client = new ClientHandler(socket);
				Thread t = new Thread(client);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
