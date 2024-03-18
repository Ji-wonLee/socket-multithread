
package com.java.thread;

import java.net.ServerSocket; 
import java.net.Socket;

public class ServerMain {

    private ServerSocket serverSocket;
    private int clientNumber = 0;

    public ServerMain() throws Exception {
        serverSocket = new ServerSocket(2023);
        System.out.println("2023포트 오픈됨");

        while (true) {
            Socket socket = serverSocket.accept();
            
            if(socket != null) {
            	String ipaddr = socket.getInetAddress().toString(); //호스트 ip가져와서 출력
            	System.out.println(ipaddr);
//            	if(ipaddr.contains("127,0,0,1")) {
//            		PrintWriter out_socket = 	
//            				new 
//            				
//            	}
            }
            ServerThread serverThread = new ServerThread(socket, this);
            Thread thread = new Thread(serverThread);
            thread.start();
        }
    }

    public synchronized int addClient() {
        clientNumber++;
        System.out.println("현재 연결된 클라이언트 수: " + clientNumber);
        return clientNumber;
    }

    public synchronized void removeClient() {
        clientNumber--;
        System.out.println("현재 연결된 클라이언트 수: " + clientNumber);
    }

    public static void main(String[] args) {
        try {
            new ServerMain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
