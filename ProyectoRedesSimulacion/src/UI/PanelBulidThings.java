import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBulidThings {
    public JPanel build(){
        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.setBackground(new Color(245, 249, 255));
        panelTotal.setPreferredSize(new Dimension(0, 100));

        JLabel label = new JLabel("Componentes");
        label.setFont(label.getFont().deriveFont(16f));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        
    


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10)); 
        panel.setBackground(new Color(245, 249, 255));

        JButton serverButton = MenuThings.createServerButton();
        JButton clientButton = MenuThings.createClientButton();

        panel.add(serverButton);
        panel.add(clientButton);

        panelTotal.add(label, BorderLayout.NORTH);
        panelTotal.add(panel, BorderLayout.CENTER);

        return panelTotal;
    }
}
