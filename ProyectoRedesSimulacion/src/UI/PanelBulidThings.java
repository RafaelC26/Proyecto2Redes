import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBulidThings {
    private Principal principal;

    public PanelBulidThings(Principal principal) {
        this.principal = principal;
    }

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

        serverButton.addActionListener(e -> {
            JPanel central = principal.getPanelCentral();

            String imagePath = "src\\Images\\server.png";
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel serverLabel = new JLabel(scaledIcon);
            serverLabel.setBounds(10, 10, 60, 60); 

            serverLabel.addMouseListener(new MouseAdapter() {
                Point offset;
                public void mousePressed(MouseEvent evt) {
                    offset = new Point(evt.getPoint());
                }
            });
            serverLabel.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    int x = serverLabel.getX() + evt.getX() - serverLabel.getWidth() / 2;
                    int y = serverLabel.getY() + evt.getY() - serverLabel.getHeight() / 2;
                    serverLabel.setLocation(x, y);
                    central.repaint();
                }
            });

            central.add(serverLabel);
            central.repaint();
        });

        clientButton.addActionListener(e -> {
            JPanel central = principal.getPanelCentral();

            String imagePath = "src\\Images\\computer.png";
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel clientLabel = new JLabel(scaledIcon);
            clientLabel.setBounds(10, 10, 80, 80); 

            clientLabel.addMouseListener(new MouseAdapter() {
                Point offset;
                public void mousePressed(MouseEvent evt) {
                    offset = new Point(evt.getPoint());
                }
            });
            clientLabel.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    int x = clientLabel.getX() + evt.getX() - clientLabel.getWidth() / 2;
                    int y = clientLabel.getY() + evt.getY() - clientLabel.getHeight() / 2;
                    clientLabel.setLocation(x, y);
                    central.repaint();
                }
            });

            central.add(clientLabel);
            central.repaint();
        });

        panel.add(serverButton);
        panel.add(clientButton);

        panelTotal.add(label, BorderLayout.NORTH);
        panelTotal.add(panel, BorderLayout.CENTER);

        return panelTotal;
    }
}
