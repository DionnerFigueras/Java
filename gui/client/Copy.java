package gui.client;

public class Copy {

}

/*
 * package gui.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import BUS.COMMON.CommonBus;
import gui.MainFrame;
import gui.common.CommonLabel;
import gui.common.CommonPanel;
import gui.remote.RemoteFrame;

public class ClientPanel extends JPanel {
    public final static String BACKGROUND_COLOR = "0x00A571";
    public final static String FOREGROUND_COLOR = "0x003927";

    private CommonPanel mainPanel;
    private CommonLabel connectLabel;
    private ButtonGroup qualityGroup;
    private JRadioButton lowQualityRadio;
    private JRadioButton highQualityRadio;
    private JLabel loaderLabel;

    private CommonBus customBus;

    public ClientPanel(CommonBus customBus) {
        this.customBus = customBus;
        this.setSize(0, MainFrame.HEIGHT_TASKBAR);
        this.setBackground(Color.decode(ClientPanel.BACKGROUND_COLOR));
        this.initComponents();
    }

    private void initComponents() {
        mainPanel = new CommonPanel();
        connectLabel = new CommonLabel();
        qualityGroup = new ButtonGroup();
        lowQualityRadio = new JRadioButton("Low quality");
        highQualityRadio = new JRadioButton("High quality");
        loaderLabel = new JLabel();

        mainPanel.setBorder(BorderFactory.createTitledBorder(null, "Connect to Server",
            TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION,
            new Font("segoe ui", Font.BOLD, 16),
            Color.decode(ClientPanel.FOREGROUND_COLOR))
        );

        mainPanel.getServerLabel().setText("Remote IP:");
        mainPanel.getServerField().setVisible(true);
        mainPanel.getPortField().setVisible(true);

        mainPanel.getPortLabel().setText("Remote port:");
        mainPanel.getPasswordLabel().setText("Password:");
        mainPanel.getPasswordField().setVisible(false);
        mainPanel.getPasswordField().setVisible(true);

        mainPanel.getHelpLabel().setText("<html>>>Help: Enter a name or an IP address and port of server which you want to remote.<br>>>Example: 192.168.0.1:9999</html>");

        connectLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("IMAGES/connect_icon.png")));
        connectLabel.setText("Connect now");
        connectLabel.setBounds(220, 290, 150, 50);
        connectLabel.setForeground(Color.decode(FOREGROUND_COLOR));
        connectLabel.setFont(new Font("segoe ui", Font.PLAIN, 15));
        connectLabel.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
                connectLabelMousePressed(e);
            }
        });

        lowQualityRadio.setBounds(60, 290, 100, 30);
        lowQualityRadio.setOpaque(false);
        lowQualityRadio.setSelected(true);
        qualityGroup.add(lowQualityRadio);

        highQualityRadio.setBounds(60, 310, 100, 30);
        highQualityRadio.setOpaque(false);
        qualityGroup.add(highQualityRadio);

        loaderLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("IMAGES/loader_icon.gif")));
        loaderLabel.setBounds(340, 307, 16, 16);
        loaderLabel.setVisible(false);

        
        this.add(mainPanel.getServerLabel());
        this.add(mainPanel.getServerField());
        this.add(mainPanel.getPortLabel());
        this.add(mainPanel.getPortField());
        this.add(mainPanel.getPasswordLabel());
        this.add(mainPanel.getPasswordField());
        this.add(mainPanel.getHelpLabel());
        this.add(connectLabel);
        this.add(lowQualityRadio);
        this.add(highQualityRadio);
        this.add(loaderLabel);
    }

    @Override
    public void setEnabled(boolean b) {
        mainPanel.setEnabled(b);
        lowQualityRadio.setEnabled(b);
        highQualityRadio.setEnabled(b);
        connectLabel.setEnabled(b);
    }

    private boolean isValidIpv4(String host) {
        int count = 0;
        for (int i = 0; i < host.length(); ++i) {
            if (host.charAt(i) == '.') ++count;
        }
        return count == 3 || count == 0;
    }

    private void connectLabelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && connectLabel.isEnabled()) {
            this.setEnabled(false);
            loaderLabel.setVisible(true);

            Thread connectThread = new Thread(() -> {
                try {
                    String host = mainPanel.getServerField().getText().trim();
                    int port = Integer.parseInt(mainPanel.getPortField().getText().trim());
                    String password = mainPanel.getPasswordLabel().getText().trim();

                    if (!isValidIpv4(host)) throw new Exception("Wrong format IPV4");

                    customBus.connectToServer(host, port, password);

                    EventQueue.invokeLater(() -> {
                        try {
                            if (lowQualityRadio.isSelected()) {
                                new RemoteFrame(this, customBus, "jpg");
                            } else if (highQualityRadio.isSelected()) {
                                new RemoteFrame(this, customBus, "png");
                            }
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(this, "Can't start remoting to server:\n" + exception.getMessage());
                        }
                    });
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, "Can't connect to server:\n" + exception.getMessage());
                }
                this.setEnabled(true);
                loaderLabel.setVisible(false);
            });
            connectThread.setDaemon(true);
            connectThread.start();
        }
    }

    public static void main(String[] args) {
    // Crear un objeto CommonBus para pasar como parámetro
    CommonBus commonBus = new CommonBus();

    // Crear un objeto JFrame para contener el panel
    JFrame frame = new JFrame("Remote Desktop Client");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Crear un objeto ClientPanel y agregarlo al frame
    ClientPanel clientPanel = new ClientPanel(commonBus);
    frame.getContentPane().add(clientPanel);

    // Configurar el tamaño y la visibilidad del frame
    frame.setSize(400, 400);
    frame.setVisible(true);
}
}
 */