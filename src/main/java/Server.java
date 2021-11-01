import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        int count = 0;
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("client accepted " + ++count);
            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            writer.write("HTTP/1.0 200 OK\n\r" +
//                    "Content-type: text/html\n\r" +
//                    "\n\r"+
//                    "<h1>Hello</h1>\n\r");
            String request = reader.readLine();
            String response ="#" + count + " your message length is " + request.length() +"\n";
            writer.write(response);
            writer.flush();

            writer.close();
            reader.close();
            clientSocket.close();
        }
    }
}
