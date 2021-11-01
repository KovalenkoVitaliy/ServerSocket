package JSON;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm dd.MM.yyyy");
        Date date = new Date();
        User user = new User("Admin", simpleDateFormat.format(date),"Hello serak");
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("Login", user.getLogin());
        jsonObject.put("Message", user.getMessage());
        jsonObject.put("Timestamp", user.getTimestamp());
        jsonObject1.put("User", jsonObject);

        OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String request = jsonObject1.toString();
        writer.write(request + "\n");
        writer.flush();

        String message = reader.readLine();
        System.out.println(message);

        writer.close();
        reader.close();
        clientSocket.close();
    }
}
