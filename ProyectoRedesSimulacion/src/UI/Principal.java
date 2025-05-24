import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class Principal extends JFrame {

    private JPanel panelCentral;
    private JScrollPane scrollPaneCentral;
    private Point lastPoint;
    private double zoomFactor = 1.0;

    public void start() {
        setTitle("Simulador de Redes de Computadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 249, 255));
        setContentPane(mainPanel);

        JPanel toolbarPanel = new ToolbarBuilder(this).build();

        // Panel central y scrollpane
        panelCentral = new JPanel(null);
        panelCentral.setPreferredSize(new Dimension(2000, 1200));
        scrollPaneCentral = new JScrollPane(panelCentral);
        scrollPaneCentral.setPreferredSize(new Dimension(900, 600)); 

        SwingUtilities.invokeLater(() -> {
            int centerX = panelCentral.getPreferredSize().width / 2 - scrollPaneCentral.getViewport().getWidth() / 2;
            int centerY = panelCentral.getPreferredSize().height / 2 - scrollPaneCentral.getViewport().getHeight() / 2;
            scrollPaneCentral.getHorizontalScrollBar().setValue(Math.max(centerX, 0));
            scrollPaneCentral.getVerticalScrollBar().setValue(Math.max(centerY, 0));
        });

        panelCentral.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getPreciseWheelRotation() < 0) {
                    zoomFactor *= 1.1; 
                } else {
                    zoomFactor /= 1.1; 
                }
                zoomFactor = Math.max(0.2, Math.min(zoomFactor, 5.0));
                for (Component comp : panelCentral.getComponents()) {
                    if (comp instanceof JPanel) {
                        JPanel panel = (JPanel) comp;
                        String imagePath = (String) panel.getClientProperty("imagePath");
                        Integer baseW = (Integer) panel.getClientProperty("baseW");
                        Integer baseH = (Integer) panel.getClientProperty("baseH");
                        Integer baseX = (Integer) panel.getClientProperty("baseX");
                        Integer baseY = (Integer) panel.getClientProperty("baseY");
                        if (imagePath != null && baseW != null && baseH != null && baseX != null && baseY != null) {
                            int newW = (int) (baseW * zoomFactor);
                            int newH = (int) (baseH * zoomFactor);
                            int newX = (int) (baseX * zoomFactor);
                            int newY = (int) (baseY * zoomFactor);
                            panel.setBounds(newX, newY, newW, newH + 20);

                            for (Component c : panel.getComponents()) {
                                if (c instanceof JLabel label) {
                                    if (label.getIcon() != null) {
                                        // Es la imagen
                                        ImageIcon originalIcon = new ImageIcon(imagePath);
                                        Image scaledImage = originalIcon.getImage().getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
                                        label.setIcon(new ImageIcon(scaledImage));
                                    } else {
                                        // Es el texto
                                        float baseFontSize = 10f; 
                                        label.setFont(label.getFont().deriveFont(baseFontSize * (float)zoomFactor));
                                    }
                                }
                            }
                        }
                    }
                }
                panelCentral.revalidate();
                panelCentral.repaint();
            }
        });

        panelCentral.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
            }
        });
        panelCentral.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (lastPoint == null) return;
                Point current = e.getPoint();
                int dx = current.x - lastPoint.x;
                int dy = current.y - lastPoint.y;
                for (Component comp : panelCentral.getComponents()) {
                    Point loc = comp.getLocation();
                    comp.setLocation(loc.x + dx, loc.y + dy);
                }
                panelCentral.repaint();
                lastPoint = current;
            }
        });

        PanelBulidThings panelBulidThings = new PanelBulidThings(this, panelCentral);

        mainPanel.add(toolbarPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPaneCentral, BorderLayout.CENTER); 
        mainPanel.add(panelBulidThings.build(), BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel getPanelCentral() {
        return panelCentral;
    }

    public void setPanelCentral(JPanel panel) {
        this.panelCentral = panel;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }
}
