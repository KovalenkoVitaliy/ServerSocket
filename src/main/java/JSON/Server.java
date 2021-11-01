package JSON;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

            String request = reader.readLine();
            JSONObject jsonObject = new JSONObject(request);
            System.out.println(request);
            JSONObject jsonObject1 = jsonObject.getJSONObject("User");
            String Message = jsonObject1.getString("Message");
            String Login = jsonObject1.getString("Login");
            String Timestamp = jsonObject1.getString("Timestamp");

            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("Timestamp", "now");
            jsonObject2.put("Login", "true");
            String response = jsonObject2.toString();
            writer.write(response + "\n");
            writer.flush();

            writer.close();
            reader.close();
            clientSocket.close();
        }
    }
}
