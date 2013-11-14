import com.pgiletich.graphics.ui.MainWindow;

import javax.swing.*;

/**
 * @author Гилетич Павел
 * @created 10.10.2013
 */
public class Main {
    public static void main(String... args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("System theme cannot be initialised, using java look & feel");
        }
        JFrame mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
