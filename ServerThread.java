package com.java.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerThread implements Runnable {
    private Socket socket;
    private ServerMain serverMain;

    public ServerThread(Socket socket, ServerMain serverMain) {
        this.socket = socket;
        this.serverMain = serverMain;
        serverMain.addClient(); // 클라이언트 추가 시 호출
    }

    @Override
    public void run() {
        try {
            BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter outSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            outSocket.println("환영합니다! 당신의 이름은?");
            String message = inSocket.readLine();
            System.out.println("Client says: " + message);

            socket.close();
            System.out.println("서버: 소켓 종료");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverMain.removeClient(); // 클라이언트 연결 종료 시 호출
        }
    }
}
