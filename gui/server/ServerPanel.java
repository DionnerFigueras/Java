package gui.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BUS.COMMON.CommonBus;
import gui.client.ClientPanel;
import gui.common.CommonLabel;
import gui.common.CommonPanel;

public class ServerPanel extends JPanel implements Runnable {
    public final static String CONNECTED_FOREGROUND = "0x008000";
    public final static String DISCONNECTED_FOREGROUND = "0x0042A7";

    private CommonPanel main_panel;
    private JLabel status_label;
    private CommonLabel connect_label;
    private CommonLabel disconnect_label;

    private CommonBus remote_desktop_bus;

    private Thread connection_thread;

    public ServerPanel(CommonBus remote_desktop_bus) {
        
        this.remote_desktop_bus = remote_desktop_bus;
        
        this.initComponents();
    }

    private void initComponents() {
        
        this.main_panel = new CommonPanel();
        this.status_label = new JLabel();
        this.connect_label = new CommonLabel();
        this.disconnect_label = new CommonLabel();

        
        this.connect_label.setText("Connect");
        this.connect_label.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("IMAGES/connect_icon.png")));
        this.connect_label.setBounds(50, 290, 150, 50);
        this.connect_label.setForeground(Color.decode(ClientPanel.FOREGROUND_COLOR));
        this.connect_label.setFont(new Font("segoe ui", Font.PLAIN, 15));
        this.connect_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connectLabelMousePressed(e);
            }
        });
        this.add(this.connect_label);

        this.disconnect_label.setText("Disconnect");
        this.disconnect_label.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("IMAGES/connect_icon.png")));
        this.disconnect_label.setBounds(220, 290, 150, 50);
        this.disconnect_label.setForeground(Color.decode(ClientPanel.FOREGROUND_COLOR));
        this.disconnect_label.setFont(new Font("segoe ui", Font.PLAIN, 15));
        this.disconnect_label.setEnabled(false);
        this.disconnect_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                disconnectLabelMousePressed(e);
            }
        });
        this.add(this.disconnect_label);
    }

    private void connectLabelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && this.connect_label.isEnabled()) {
            try {
                String host = this.main_panel.getServerField().getText().toString();
                int port = Integer.parseInt(this.main_panel.getPortField().getText().trim());
                String password = this.main_panel.getPasswordField().toString();
                this.remote_desktop_bus.startServer(host, port, password);

                
                this.connection_thread = new Thread(this);
                this.connection_thread.setDaemon(true);
                this.connection_thread.start();

                
                this.main_panel.setEnabled(false);
                this.connect_label.setNormalFont();
                this.connect_label.setEnabled(false);
                this.disconnect_label.setEnabled(true);
                this.status_label.setText("Status: Connected");
                this.status_label.setForeground(Color.decode(ServerPanel.CONNECTED_FOREGROUND));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Can't connect to remote desktop:\n" + exception.getMessage());
            }
        }
    }

    private void disconnectLabelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && this.disconnect_label.isEnabled()) {
            try {
                this.remote_desktop_bus.stopServer();

                this.connection_thread.interrupt();

                this.main_panel.setEnabled(true);
                this.disconnect_label.setNormalFont();
                this.disconnect_label.setEnabled(false);
                this.connect_label.setEnabled(true);
                this.status_label.setText("Status: Disconnected");
                this.status_label.setForeground(Color.decode(ServerPanel.DISCONNECTED_FOREGROUND));
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                JOptionPane.showMessageDialog(this, "Can't disconnect from remote desktop:\n" + exception.getMessage());
            }
        }
    }

    @Override
    public void run() {
        while (this.remote_desktop_bus.getTcpServer().isListening()) {
            try {
                this.remote_desktop_bus.getTcpServer();
            } catch (Exception e) {
            }
        }
    }
}