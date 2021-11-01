package Threads;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Phone {
    private int port;
    private ServerSocket server;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Phone(Phone phone){
        this.server = phone.server;
    }

    public Phone(String port) {
        this.port = Integer.parseInt(port);
        try {
            this.server = new ServerSocket(this.port);
        } catch (IOException e) {
        }
    }

    public Phone(String ip, String port) {
        try {
            clientSocket = new Socket(ip, Integer.parseInt(port));
            createStreams();
        } catch (IOException e) {
        }
    }

    public void accept() {
        try {
            clientSocket = server.accept();
            createStreams();
        } catch (IOException e) {
        }
    }

    private void createStreams(){
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
        }
    }

    public String readLine() {
        String message = "";
        try {
           message = reader.readLine();
        } catch (IOException e) {

        }
        return message;
    }

    public void writeLine(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
        }
    }

    public void close() {
        try {
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {

        }
    }
}
