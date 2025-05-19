import java.awt.*;
import javax.swing.*;

public class Principal extends JFrame {

    private JPanel panelCentral; // <-- Agrega esto

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
        panelCentral = new CenterPanelBuilder().build(); // <-- Usa el atributo
        JPanel panelBulidThings = new PanelBulidThings(this).build(); // <-- Pasa la instancia de Principal

        mainPanel.add(toolbarPanel, BorderLayout.NORTH);
        mainPanel.add(panelCentral, BorderLayout.CENTER);
        mainPanel.add(panelBulidThings, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel getPanelCentral() {
        return panelCentral;
    }
}
