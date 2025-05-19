import java.awt.*;
import javax.swing.*;

public class Principal extends JFrame {

    public void start() {
        setTitle("Simulador de Redes de Computadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 249, 255));
        setContentPane(mainPanel);

        JPanel toolbarPanel = new ToolbarBuilder().build();
        JPanel panelCentral = new CenterPanelBuilder().build();
        JPanel panelBulidThings = new PanelBulidThings().build();

        mainPanel.add(toolbarPanel, BorderLayout.NORTH);
        mainPanel.add(panelCentral, BorderLayout.CENTER);
        mainPanel.add(panelBulidThings, BorderLayout.SOUTH);

        setVisible(true);
    }
}
