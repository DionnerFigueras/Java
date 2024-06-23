package BUS.COMMON;

import java.awt.AWTException;
import java.io.IOException;
import java.rmi.NotBoundException;

import BUS.RMI.RemoteDesktopClient;
import BUS.RMI.ServerRMI;
import BUS.TCP.TcpClient;
import BUS.TCP.TcpServer;
import gui.chat.MainChatPanel;

public class CommonBus {
    private TcpServer tcpServer;
    private ServerRMI rmiServer;
    private TcpClient tcpClient;
    private RemoteDesktopClient rmiClient;
    private MainChatPanel mainChatPanel;

    public CommonBus() {
        this.rmiServer = new ServerRMI();
        this.rmiClient = new RemoteDesktopClient();
    }

    public void setMainChatPanel(MainChatPanel mainChatPanel) {
        this.mainChatPanel = mainChatPanel;
        this.tcpServer = new TcpServer(this.mainChatPanel);
        this.tcpClient = new TcpClient(this.mainChatPanel);
    }

    public TcpServer getTcpServer() {
        return tcpServer;
    }

    public ServerRMI getRmiServer() {
        return rmiServer;
    }

    public TcpClient getTcpClient() {
        return tcpClient;
    }

    public RemoteDesktopClient getRmiClient() {
        return rmiClient;
    }

    public void startServer(String host, int port, String password) throws IOException, AWTException {
        if (!tcpServer.isListening() && !rmiServer.getIsBinding()) {
            tcpServer.startListening(host, port, password);
            rmiServer.startBindingOnRmiServer(host, port + 1);
        }
    }

    public void stopServer() throws IOException, NotBoundException {
        if (tcpServer.isListening() && rmiServer.getIsBinding()) {
            tcpServer.stopListening();
            rmiServer.stopBinding();
        }
    }

    public void connectToServer(String host, int port, String password) throws Exception {
        if (tcpServer.isListening()) {
            String serverIp = tcpServer.getServerSocket().getInetAddress().getHostAddress();
            if (host.equals(serverIp)) {
                throw new Exception("Can't remote yourself!");
            }
        }
        if (tcpClient.isConnected()) {
            throw new Exception("You are already remoting!");
        }
        tcpClient.connectToTcpServer(host, port, password);
        rmiClient.startConnectingToRmiServer(host, port + 1);
    }
}