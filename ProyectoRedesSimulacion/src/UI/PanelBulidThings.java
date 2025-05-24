import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelBulidThings {
    private Principal principal;
    private int routerCount = 1;
    private int computerCount = 1;
    private Dimension routerCurrentSize = null;
    private Dimension computerCurrentSize = null;

    public PanelBulidThings(Principal principal) {
        this.principal = principal;
    }

    public JPanel build() {
        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.setBackground(new Color(245, 249, 255));
        panelTotal.setPreferredSize(new Dimension(0, 100));

        JLabel label = new JLabel("Componentes");
        label.setFont(label.getFont().deriveFont(16f));
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panel.setBackground(new Color(245, 249, 255));

        JButton routerButton = MenuThings.createServerButton();
        JButton clientButton = MenuThings.createClientButton();

        routerButton.addActionListener(e -> {
            JPanel central = principal.getPanelCentral();

            String imagePath = "src\\Images\\router.png";
            int baseW = 60, baseH = 60;
            // Si ya existe un router, usa su tamaño actual
            if (routerCurrentSize != null) {
                baseW = routerCurrentSize.width;
                baseH = routerCurrentSize.height;
            }
            int baseX = 20 * routerCount, baseY = 20 * routerCount;

            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(baseW, baseH, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JPanel routerPanel = new JPanel();
            routerPanel.setLayout(new BoxLayout(routerPanel, BoxLayout.Y_AXIS));
            routerPanel.setOpaque(false);

            JLabel serverLabel = new JLabel(scaledIcon);
            serverLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            JLabel nameLabel = new JLabel("Router " + routerCount);
            nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            nameLabel.setForeground(Color.BLACK);
            float baseFontSize = 10f;
            nameLabel.setFont(nameLabel.getFont().deriveFont(baseFontSize * (float)principal.getZoomFactor()));

            routerPanel.add(serverLabel);
            routerPanel.add(nameLabel);

            routerPanel.setBounds(baseX, baseY, baseW, baseH + 20);

            routerPanel.putClientProperty("imagePath", imagePath);
            routerPanel.putClientProperty("baseW", baseW);
            routerPanel.putClientProperty("baseH", baseH);
            routerPanel.putClientProperty("baseX", baseX);
            routerPanel.putClientProperty("baseY", baseY);

            routerPanel.addMouseListener(new MouseAdapter() {
                Point offset;
                public void mousePressed(MouseEvent evt) {
                    offset = evt.getPoint();
                    central.revalidate();
                    central.repaint();
                    // Mostrar menú de opciones con click derecho
                    if (SwingUtilities.isRightMouseButton(evt)) {
                        JPopupMenu menu = new JPopupMenu();
                        JMenuItem eliminar = new JMenuItem("Eliminar");
                        eliminar.addActionListener(ae -> {
                            central.remove(routerPanel);
                            central.revalidate();
                            central.repaint();
                        });
                        menu.add(eliminar);
                        // Puedes agregar más opciones aquí
                        menu.show(routerPanel, evt.getX(), evt.getY());
                    }
                }
                public void mouseReleased(MouseEvent evt) {
                    routerPanel.putClientProperty("baseX", routerPanel.getX());
                    routerPanel.putClientProperty("baseY", routerPanel.getY());
                    central.revalidate();
                    central.repaint();
                }
            });
            routerPanel.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    int x = routerPanel.getX() + evt.getX() - routerPanel.getWidth() / 2;
                    int y = routerPanel.getY() + evt.getY() - routerPanel.getHeight() / 2;
                    routerPanel.setLocation(x, y);
                    central.revalidate();
                    central.repaint();
                }
            });

            central.add(routerPanel);
            central.revalidate();
            central.repaint();

            // Guarda el tamaño actual del primer router
            if (routerCurrentSize == null) {
                routerCurrentSize = new Dimension(baseW, baseH);
            }
            routerCount++;
        });

        clientButton.addActionListener(e -> {
            JPanel central = principal.getPanelCentral();

            String imagePath = "src\\Images\\computer.png";
            int baseW = 80, baseH = 80;
            // Si ya existe una computadora, usa su tamaño actual
            if (computerCurrentSize != null) {
                baseW = computerCurrentSize.width;
                baseH = computerCurrentSize.height;
            }
            int baseX = 100 + 20 * computerCount, baseY = 20 * computerCount;

            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(baseW, baseH, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JPanel clientPanel = new JPanel();
            clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
            clientPanel.setOpaque(false);

            JLabel clientLabel = new JLabel(scaledIcon);
            clientLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            JLabel nameLabel = new JLabel("PC " + computerCount);
            nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            nameLabel.setForeground(Color.BLACK);
            float baseFontSize = 10f;
            nameLabel.setFont(nameLabel.getFont().deriveFont(baseFontSize * (float)principal.getZoomFactor()));

            clientPanel.add(clientLabel);
            clientPanel.add(nameLabel);

            clientPanel.setBounds(baseX, baseY, baseW, baseH + 20);

            clientPanel.putClientProperty("imagePath", imagePath);
            clientPanel.putClientProperty("baseW", baseW);
            clientPanel.putClientProperty("baseH", baseH);
            clientPanel.putClientProperty("baseX", baseX);
            clientPanel.putClientProperty("baseY", baseY);

            clientPanel.addMouseListener(new MouseAdapter() {
                Point offset;
                public void mousePressed(MouseEvent evt) {
                    offset = evt.getPoint();
                    central.revalidate();
                    central.repaint();
                    // Mostrar menú de opciones con click derecho
                    if (SwingUtilities.isRightMouseButton(evt)) {
                        JPopupMenu menu = new JPopupMenu();
                        JMenuItem eliminar = new JMenuItem("Eliminar");
                        eliminar.addActionListener(ae -> {
                            central.remove(clientPanel);
                            central.revalidate();
                            central.repaint();
                        });
                        menu.add(eliminar);
                        // Puedes agregar más opciones aquí
                        menu.show(clientPanel, evt.getX(), evt.getY());
                    }
                }
                public void mouseReleased(MouseEvent evt) {
                    clientPanel.putClientProperty("baseX", clientPanel.getX());
                    clientPanel.putClientProperty("baseY", clientPanel.getY());
                    central.revalidate();
                    central.repaint();
                }
            });
            clientPanel.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    int x = clientPanel.getX() + evt.getX() - clientPanel.getWidth() / 2;
                    int y = clientPanel.getY() + evt.getY() - clientPanel.getHeight() / 2;
                    clientPanel.setLocation(x, y);
                    central.revalidate();
                    central.repaint();
                }
            });

            central.add(clientPanel);
            central.revalidate();
            central.repaint();

            // Guarda el tamaño actual del primer PC
            if (computerCurrentSize == null) {
                computerCurrentSize = new Dimension(baseW, baseH);
            }
            computerCount++;
        });

        panel.add(routerButton);
        panel.add(clientButton);

        panelTotal.add(label, BorderLayout.NORTH);
        panelTotal.add(panel, BorderLayout.CENTER);

        return panelTotal;
    }
}
