import java.awt.*;
import javax.swing.*;

public class Principal extends JFrame {

    public void start() {
        setTitle("Simulador de Redes de Computadores");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(245, 249, 255));
        setContentPane(mainPanel);

        // Composición con clases especializadas
        JPanel toolbarPanel = new ToolbarBuilder().build();
        JLabel titulo = new TitleBuilder().build();
        JPanel panelCentral = new CenterPanelBuilder().build();

        mainPanel.add(toolbarPanel);
        mainPanel.add(titulo);
        mainPanel.add(panelCentral);

        setVisible(true);
    }
}
