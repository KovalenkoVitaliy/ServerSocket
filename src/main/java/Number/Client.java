package Number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);
        boolean flag = true;
        int popytka = 0;

        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        BufferedReader zagadai =null;
        while (flag) {
            zagadai = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Zagadai chislo ot 1 do 10");
            String number = zagadai.readLine();

            writer = new OutputStreamWriter(clientSocket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer.write(number +"\n");
            writer.flush();

            String message = reader.readLine();
            System.out.println(message);
            popytka++;
             if (message.equals("true")) {
                 flag = false;
                 System.out.println("Kolichestvo popytok ygadat = " + popytka);
             }

        }

        writer.close();
        reader.close();
        zagadai.close();
        clientSocket.close();
    }
}
