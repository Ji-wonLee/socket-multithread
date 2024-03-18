package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.nio.charset.StandardCharsets;

public class Server {

    public Server() throws Exception {
        ServerSocket server_socket = new ServerSocket(10001);
        System.out.println("서버: 10001포트가 열림");

        while (true) { // 서버를 계속 실행 상태로 유지합니다.
            Socket socket = server_socket.accept();
            System.out.println("서버: 클라이언트: " + socket.getInetAddress() + " 연결됨.");

            BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            // 랜덤 수 생성
            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1; // 1~10 사이의 랜덤 수
            int attempts = 0;
            boolean IsCorrect = false;

            out_socket.println("황영!  1에서 10 사이의 숫자를 맞춰보세요. 5번의 기회가 있습니다.");

            while (attempts < 5 && !IsCorrect) {
                String clientGuess = in_socket.readLine();
                int guess;
                try {
                    guess = Integer.parseInt(clientGuess);
                } catch (NumberFormatException e) {
                    out_socket.println("숫자를 입력해주세요.");
                    continue;
                }

                attempts++;

                if (guess == randomNumber) {
                    out_socket.println("정답입니다!");
                    IsCorrect = true;
                } else if (attempts < 5) {
                    out_socket.println("다시");
                } else {
                    out_socket.println("기회를 모두 소진하였습니다. 정답은 " + randomNumber + "였습니다. 까비~ ");
                }
            }
            socket.close();
            //server_socket.close();
            System.out.println("서버: 클라이언트와의 연결을 종료합니다.");
        }
        // server_socket.close(); 
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
