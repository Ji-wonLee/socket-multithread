package com.java.thread;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client_th {

	public Client_th() {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 2023);
			System.out.println(
					"클라이언트: Successful Connection to the Server");

			// I/O 스트림
			BufferedReader in_socket = new BufferedReader(
					new InputStreamReader(
						socket.getInputStream(), 
						StandardCharsets.UTF_8));
			PrintWriter out_socket = new PrintWriter(
					new OutputStreamWriter(
						socket.getOutputStream(), 
						StandardCharsets.UTF_8), true);
			
			Scanner keyboard = new Scanner(System.in);
			
			String message = in_socket.readLine();
			System.out.println("클라이언트: 서버왈: " + message);
			message = keyboard.nextLine();
			out_socket.println(message);
			
			keyboard.close();
			socket.close();
			System.out.println("클라이언트: 소켓 종료");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new Client_th();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

