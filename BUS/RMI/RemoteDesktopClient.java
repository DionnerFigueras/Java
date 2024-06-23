package BUS.RMI;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

public class RemoteDesktopClient {
    private RemoteDesktopInterface remote_obj;
    private boolean is_remote_server;

    public RemoteDesktopClient() {
        this.remote_obj = null;
        this.is_remote_server = false;
    }

    public void startConnectingToRmiServer(String host, int port) throws RemoteException, NotBoundException, MalformedURLException {
        if (!this.is_remote_server) {
            String url = "rmi://" + host + ":" + port + "/RemoteDesktop";
            this.remote_obj = (RemoteDesktopInterface) Naming.lookup(url);
            this.is_remote_server = true;
        }
    }

    public void stopConnectingToRmiServer() {
        if (this.is_remote_server) {
            this.remote_obj = null;
            this.is_remote_server = false;
        }
    }

    public RemoteDesktopInterface getRemoteObject() {
        return this.remote_obj;
    }

    public boolean isRemoteServer() {
        return this.is_remote_server;
    }

    public void setRemoteServer(boolean b) {
        this.is_remote_server = b;
    }

}
