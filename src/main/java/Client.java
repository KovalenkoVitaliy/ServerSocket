import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);

//        byte[] bytes = new byte[256];
//        clientSocket.getInputStream().read(bytes);
//        System.out.println(new String(bytes));

        OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer.write("Give me some information\n");
        writer.flush();

        String message = reader.readLine();
        System.out.println(message);

        writer.close();
        reader.close();
        clientSocket.close();
    }
}
