import javax.swing.*;
import java.awt.*;

public class ImageDisplayer {
    private JFrame frame;
    private JLabel label;

    public ImageDisplayer(String imagePath) {
        frame = new JFrame("Image Displayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel();
        label.setIcon(new ImageIcon(imagePath));

        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String imagePath = "IMAGES/send_icon.png";
        new ImageDisplayer(imagePath);
    }
}