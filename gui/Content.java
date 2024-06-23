/*
 * package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

import BUS.COMMON.CommonBus;
import GUI.chat.MainChatPanel;
import GUI.client.ClientPanel;
import GUI.common.CommonLabel;
import GUI.server.ServerPanel;

public class MainFrame extends JFrame {
    public final static int WIDTH_FRAME = 400;
    public final static int HEIGHT_FRAME = 420;
    public final static int HEIGHT_TASKBAR = 50;
    public final static String TASKBAR_BACKGROUND = "0x000942";

    private CommonBus commonBus;
    private JPanel taskbarPanel;
    private CommonLabel clientLabel;
    private CommonLabel serverLabel;
    private CommonLabel chatLabel;
    private ClientPanel clientPanel;
    private ServerPanel serverPanel;
    private MainChatPanel mainChatPanel;
    private int focusKey;

    public MainFrame() throws IOException {
        ImageIO.setUseCache(false);

        UIManager.put("Label.disabledForeground", Color.decode("0xD3D3D3"));
        UIManager.put("RadioButton.disabledText", Color.decode("0xD3D3D3"));

        initUI();
    }

    private void initUI() {
        setPreferredSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        pack();
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Remote Desktop Software");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("window_icon.png")).getImage());
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    mainFrameWindowClosing(e);
                } catch (Exception exception) {
                }
            }
        });

        initComponents();
    }

    private void initComponents() {
        commonBus = new CommonBus();
        taskbarPanel = new JPanel();
        clientLabel = new CommonLabel();
        serverLabel = new CommonLabel();
        chatLabel = new CommonLabel();
        clientPanel = new ClientPanel(commonBus);
        serverPanel = new ServerPanel(commonBus);
        mainChatPanel = new MainChatPanel(commonBus);

        commonBus.setMainChatPanel(mainChatPanel);

        focusKey = 1;

        taskbarPanel.setLayout(new GridLayout(1, 3));
        taskbarPanel.setBackground(Color.decode(TASKBAR_BACKGROUND));
        taskbarPanel.setBounds(0, 0, WIDTH_FRAME, HEIGHT_TASKBAR);
        add(taskbarPanel, BorderLayout.NORTH);

        clientLabel.setText("Client");
        clientLabel.setHighlightFont();
        clientLabel.addMouseListener(new TabLabelMouseListener(clientLabel, 1));
        taskbarPanel.add(clientLabel);

        serverLabel.setText("Server");
        serverLabel.addMouseListener(new TabLabelMouseListener(serverLabel, 2));
        taskbarPanel.add(serverLabel);

        chatLabel.setText("Chat");
        chatLabel.addMouseListener(new TabLabelMouseListener(chatLabel, 3));
        taskbarPanel.add(chatLabel);

        clientPanel.setVisible(true);
        serverPanel.setVisible(false);
        mainChatPanel.setVisible(false);
        add(clientPanel, BorderLayout.CENTER);
        add(serverPanel, BorderLayout.CENTER);
        add(mainChatPanel, BorderLayout.CENTER);
    }

    private void mainFrameWindowClosing(WindowEvent e) throws IOException, NotBoundException {
        commonBus.stopServer();
    }

    private class TabLabelMouseListener extends MouseAdapter {
        private CommonLabel label;
        private int key;

        public TabLabelMouseListener(CommonLabel label, int key) {
            this.label = label;
            this.key = key;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (key == focusKey)
                    return;
                JPanel showPanel = getPanel(key);
                JPanel hidePanel = getPanel(focusKey);
                boolean isLeft = key > focusKey;
                showPanelsSlider(showPanel, hidePanel, isLeft);
                label.setText("");
                focusKey = key;
                updateLabels();
            }
        }
    }

    private JPanel getPanel(int key) {
        switch (key) {
            case 1:
                return clientPanel;
            case 2:
                return serverPanel;
            case 3:
                return mainChatPanel;
            default:
                return null;
        }
    }

    private void updateLabels() {
        clientLabel.setNormalFont();
        serverLabel.setNormalFont();
        chatLabel.setNormalFont();
        switch (focusKey) {
            case 1:
                clientLabel.setHighlightFont();
                break;
            case 2:
                serverLabel.setHighlightFont();
                break;
            case 3:
                chatLabel.setHighlightFont();
                break;
        }
    }

    private void showPanelsSlider(JPanel showPanel, JPanel hidePanel, boolean isLeft) {
        showPanel.setVisible(true);

        int xHideLocation = 0;
        int xShowLocation = isLeft ? WIDTH_FRAME : -WIDTH_FRAME;
        int value = isLeft ? -50 : 50;

        @SuppressWarnings("unused")
        Timer timer = new Timer(10, e -> {
            int tempxHideLocation = xHideLocation;
            int tempxShowLocation = xShowLocation;
            hidePanel.setLocation(xHideLocation, HEIGHT_TASKBAR);
            showPanel.setLocation(xShowLocation, HEIGHT_TASKBAR);
            if (xShowLocation == 0) {
                ((Timer) e.getSource()).stop();
                hidePanel.setVisible(false);
            }

            tempxHideLocation += value;
            tempxShowLocation += value;
        });
        timer.start();
    }
}

 */