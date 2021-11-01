package Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    public static void main(String[] args) throws IOException {
        int count = 0;
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("-------------------------------");
            boolean flag = true;
            System.out.println("client accepted " + ++count);

            int number = new Random().nextInt(10);
            System.out.println("Zagadannoe chislo is = " + number);
            OutputStreamWriter writer = null;
            BufferedReader reader = null;
            BufferedReader zagadai =null;

            while (flag) {
                writer = new OutputStreamWriter(clientSocket.getOutputStream());
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String request = reader.readLine();
                System.out.println("request = " + request);
                String response = null;
                if (request.equals(Integer.toString(number))) {
                    response = "true" + "\n";
                    flag = false;
                } else {
                    response = "false" + "\n";
                }
                writer.write(response);
                writer.flush();
            }

            writer.close();
            reader.close();
            clientSocket.close();
        }
    }
}
