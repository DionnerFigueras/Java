package TEST;

import java.util.concurrent.CountDownLatch;

import BUS.RMI.RemoteDesktopClient;
import BUS.RMI.RemoteDesktopInterface;
import BUS.RMI.ServerRMI;

public class TestClientRmi {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch serverStarted = new CountDownLatch(1);

        // Iniciar servidor RMI en un hilo separado
        Thread serverThread = new Thread(() -> {
            ServerRMI serverRMI = new ServerRMI();
            try {
                serverRMI.startBindingOnRmiServer("localhost", 1099);
                System.out.println("Servidor RMI iniciado en localhost:1099");
                System.out.println("Esperando conexiones...");
                serverStarted.countDown(); // Notificar que el servidor está iniciado
                while (true) {
                    // Bucle infinito para mantener el servidor en ejecución
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.err.println("Error al iniciar servidor RMI: " + e.getMessage());
            }
        });
        serverThread.start();

        // Esperar a que el servidor RMI esté iniciado
        serverStarted.await();

        // Conectar cliente RMI
        RemoteDesktopClient client = new RemoteDesktopClient();
        try {
            client.startConnectingToRmiServer("localhost", 1099);
            System.out.println("Conectado al servidor RMI en localhost:1099");

            // Llamada a un método remoto para probar la conexión
            RemoteDesktopInterface remoteObj = client.getRemoteObject();
            String message = remoteObj.getMessage();
            System.out.println("Mensaje del servidor: " + message);

            // Cerrar la conexión
            client.stopConnectingToRmiServer();
            System.out.println("Desconectado del servidor RMI");
        } catch (Exception e) {
            System.err.println("Error al conectar al servidor RMI: " + e.getMessage());
        }
    }

}