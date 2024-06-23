package TEST;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TestTcpClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 9999;

        Socket clientSocket = new Socket(host, port);
        System.out.println("Connected to server at " + host + ":" + port);

        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

        boolean isConnected = true;
        while (isConnected) {
            try {
                String message = "Hello, server!";
                outputStream.writeUTF(message);
                System.out.println("Sent message to server: " + message);
                Thread.sleep(1000); // wait 1 second before sending next message
            } catch (Exception e) {
                System.out.println("Error sending message: " + e.getMessage());
                isConnected = false;
            }
        }

        clientSocket.close();
        System.out.println("Disconnected from server");
    }
}