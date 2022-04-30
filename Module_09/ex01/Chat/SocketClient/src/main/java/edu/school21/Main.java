package edu.school21;

import java.io.*;
import java.net.Socket;

public class Main {
    public static class ClientServer {
        private Socket socket;
        private PrintWriter printWriter;
        private BufferedReader bufferedReader;
        private BufferedReader bufferedReaderClient;

        public void startConnection(String ip, int port) {
            try {
                socket = new Socket(ip, port);
                printWriter = new PrintWriter(socket.getOutputStream(), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedReaderClient = new BufferedReader(new InputStreamReader(System.in));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void work() {
            String messageFromServer, msgClient;
            while (true) {
                try {
                    messageFromServer = bufferedReader.readLine();
                    System.out.println(messageFromServer);
                    if (messageFromServer.equals("Successful!")) {
                        break;
                    }
                    msgClient = bufferedReaderClient.readLine();
                    if (msgClient.equals("Exit")) {
                        printWriter.println(msgClient);
                        break;
                    }
                    printWriter.println(msgClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private String sendMessage(String msg) {
            printWriter.println(msg);
            String resp = null;
            try {
                resp = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resp;
        }

        public void stopConnection() {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("ERROR! Expected --server-port='port'");
            System.exit(1);
        }
        if (!args[0].startsWith("--server-port=")) {
            System.err.println("ERROR! Expected --server-port='port'");
            System.exit(1);
        }
        int port = 0;
        try {
            port = Integer.parseInt(args[0].substring(14));
        } catch (NumberFormatException e) {
            System.err.println("port must be number");
            System.exit(1);
        }
        ClientServer clientServer = new ClientServer();
        clientServer.startConnection("localhost", port);
        clientServer.work();
        clientServer.stopConnection();
    }
}
