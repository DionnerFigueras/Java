package TEST;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class TestRobot {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();

        // Presionar y soltar la tecla Shift
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_SHIFT);

        // Presionar y soltar la tecla A
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        // Presionar y soltar la tecla Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Escribir una cadena de texto
        typeString(robot, "Hello, World!");

        // Presionar y soltar la tecla F5
        robot.keyPress(KeyEvent.VK_F5);
        robot.keyRelease(KeyEvent.VK_F5);
    }

    private static void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }
    }
}