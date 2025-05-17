import java.awt.*;
import javax.swing.*;

public class CenterPanelBuilder {
    public JPanel build() {
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panelCentral.setLayout(null);

        int panelWidth = 780;
        int panelHeight = 500;
        int panelX = (900 - panelWidth) / 2;
        int panelY = 120;
        panelCentral.setBounds(panelX, panelY, panelWidth, panelHeight);

        return panelCentral;
    }
}
