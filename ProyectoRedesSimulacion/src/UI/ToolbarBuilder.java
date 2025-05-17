import javax.swing.*;
import java.awt.*;

public class ToolbarBuilder {

    public JPanel build() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(250, 250, 250));
        toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 8));

        JButton btnFile = MenuFactory.createFileButton();
        JButton btnZoom = MenuFactory.createZoomButton();
        JButton btnHerramientas = MenuFactory.createHerramientasButton();

        toolBar.add(btnFile);
        toolBar.add(btnZoom);
        toolBar.add(btnHerramientas);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 900, 30);
        panel.setBackground(new Color(250, 250, 250));
        toolBar.setBounds(0, 0, 900, 30);
        panel.add(toolBar);

        return panel;
    }
}
