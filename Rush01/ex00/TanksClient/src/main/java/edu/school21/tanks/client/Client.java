package edu.school21.tanks.client;

import org.omg.CORBA.portable.UnknownException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

@Component
public class Client {
    private PrintWriter printWriter;

    private void printWriterFlush(String string) {
        printWriter.println(string);
        printWriter.flush();
    }

    public void run(String host, int port) {
        String STOP = "Exit";
        String serverMessage = null;

        while (serverMessage == null || !serverMessage.equals(STOP)) {
            try (Socket socket = new Socket(host, port)) {
                BufferedReader bufferedReaderServer;
                BufferedReader bufferedReaderClient;

                bufferedReaderServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedReaderClient = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    serverMessage = bufferedReaderServer.readLine();

                    if (serverMessage == null) {
                        throw new ConnectionClosed();
                    } else if (serverMessage.equals(STOP)) {
                        break;
                    } else {
                        System.out.println(serverMessage);
                        System.out.print("> ");
                        printWriterFlush(bufferedReaderClient.readLine());
                    }
                }
            } catch (IOException e) {
                System.err.println("Connection error with port=" + port);
                System.out.print("Enter new port: ");
                try {
                    port = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                } catch (IOException | NumberFormatException ignored) {}
            } catch (UnknownException e) {
                System.err.println("Host error");
            } catch (ConnectionClosed e) {
                System.out.println("Server closed connection");
            }
        }
    }
}

class ConnectionClosed extends Exception {}