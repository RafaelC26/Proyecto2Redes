import javax.swing.*;
import java.awt.*;

public class NetworkSimulator {
    private final JFrame frame;
    private final NetworkPanel networkPanel;

    public NetworkSimulator() {
        frame = new JFrame("Simulación de Red");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        networkPanel = new NetworkPanel();
        frame.add(networkPanel, BorderLayout.CENTER);
    }

    public void show() {
        frame.setVisible(true);
    }
}
