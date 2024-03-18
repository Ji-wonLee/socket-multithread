package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client2 {

	public Client2() {
		try (Socket socket = new Socket("localhost", 10001);
				BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
				Scanner sc = new Scanner(System.in)) {

			System.out.println("클라이언트: Successful Connection to the Server");

			String serverMessage = in_socket.readLine();
			System.out.println("서버: " + serverMessage);
			
			while (!serverMessage.equals("정답입니다!")) {

				System.out.print("숫자를 입력하세요: ");
				String userInput = sc.nextLine();
				out_socket.println(userInput);
				serverMessage = in_socket.readLine();
				System.out.println("서버: " + serverMessage);

			}
			socket.close();
			System.out.println("클라이언트: 소켓 종료");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new Client2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
