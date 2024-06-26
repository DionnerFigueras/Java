package TEST;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTcpServer extends Thread{
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        Socket client = server.accept();
        DataInputStream dis = new DataInputStream(client.getInputStream());

        boolean isConnected = true;
        while(isConnected) {
            try {
                System.out.println(dis.readUTF());
            }
            catch(Exception e) {
                System.out.println("Server closed");
                isConnected = false;
            }
        }
        server.close();
    }
}
