import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ToolbarBuilder {
    private JFrame frame;
    private List<JPanel[]> conexiones;
    private JPanel centralPanel;

    public ToolbarBuilder(JFrame frame, List<JPanel[]> conexiones, JPanel centralPanel) {
        this.frame = frame;
        this.conexiones = conexiones;
        this.centralPanel = centralPanel;
    }

    public JPanel build() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(250, 250, 250));
        toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 8));

        JButton btnFile = MenuFactory.createFileButton(conexiones, centralPanel);
        JButton herramientasBtn = MenuFactory.createHerramientasButton(frame);

        toolBar.add(btnFile);
        toolBar.add(herramientasBtn);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(250, 250, 250));
        panel.add(toolBar, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(0, 38)); 

        return panel;
    }
}
