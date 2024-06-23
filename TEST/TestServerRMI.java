package TEST;

import BUS.RMI.ServerRMI;

public class TestServerRMI {
    public static void main(String[] args) {
        ServerRMI serverRMI = new ServerRMI();

        try {
            // Prueba de inicio de binding
            serverRMI.startBindingOnRmiServer("localhost", 1234);
            System.out.println("Binding iniciado correctamente");

            // Verificar si el binding está activo
            boolean isBinding = serverRMI.getIsBinding();
            System.out.println("Is binding: " + isBinding);

            // Prueba de parada de binding
            serverRMI.stopBinding();
            System.out.println("Binding detenido correctamente");

            // Verificar si el binding está inactivo
            isBinding = serverRMI.getIsBinding();
            System.out.println("Is binding: " + isBinding);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}