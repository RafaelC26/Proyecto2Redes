import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PanelBulidThings {
    private Principal principal;
    private int routerCount = 1;
    private int serverCount = 1;
    private int computerCount = 1;
    private Dimension routerCurrentSize = null;
    private Dimension computerCurrentSize = null;
    private Dimension serverCurrentSize = null;

    // Variables para manejar la conexión
    private JPanel firstSelectedPanel = null;
    private List<JPanel[]> conexiones = new ArrayList<>();

    private double zoomFactor = 1.0;

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
        label.setHorizontalAlignment(JLabel.LEFT); 
        label.setAlignmentX(Component.LEFT_ALIGNMENT); 

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panel.setBackground(new Color(245, 249, 255));

        JButton routerButton = MenuThings.createRouterButton();
        JButton clientButton = MenuThings.createClientButton();
        JButton serverButton = MenuThings.createServerButton();

        // Panel central
        JPanel centralPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(2));
                g2.setColor(Color.BLUE);
                for (JPanel[] par : conexiones) {
                    Point p1 = getPanelCenter(par[0]);
                    Point p2 = getPanelCenter(par[1]);
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        };
        centralPanel.setLayout(null);
        principal.setPanelCentral(centralPanel);

        // ---- SERVER ----
        serverButton.addActionListener(e -> {
            int cantidad = 1;
            boolean valido = false;
            while (!valido) {
                String input = JOptionPane.showInputDialog(null, "¿Cuántos servidores desea agregar?", "Agregar Servidores", JOptionPane.QUESTION_MESSAGE);
                if (input == null) return; // Cancelado
                try {
                    cantidad = Integer.parseInt(input);
                    if (cantidad < 1) throw new NumberFormatException();
                    valido = true;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número entero mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            for (int i = 0; i < cantidad; i++) {
                String imagePath = "src\\Images\\server.png";
                int baseW = 60, baseH = 60;
                if (serverCurrentSize != null) {
                    baseW = serverCurrentSize.width;
                    baseH = serverCurrentSize.height;
                }
                int baseX = 20 * serverCount, baseY = 20 * serverCount;

                ImageIcon originalIcon = new ImageIcon(imagePath);

                JPanel serverPanel = new JPanel();
                serverPanel.setLayout(new BoxLayout(serverPanel, BoxLayout.Y_AXIS));
                serverPanel.setOpaque(false);

                JLabel serverLabel = new JLabel(new ImageIcon(originalIcon.getImage().getScaledInstance((int)(baseW * zoomFactor), (int)(baseH * zoomFactor), Image.SCALE_SMOOTH)));
                serverLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                JLabel nameLabel = new JLabel("Servidor " + serverCount);
                nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                nameLabel.setForeground(Color.BLACK);
                float baseFontSize = 10f;
                nameLabel.setFont(nameLabel.getFont().deriveFont(baseFontSize * (float)zoomFactor));

                serverPanel.add(serverLabel);
                serverPanel.add(nameLabel);

                serverPanel.setBounds((int)(baseX * zoomFactor), (int)(baseY * zoomFactor), (int)(baseW * zoomFactor), (int)(baseH * zoomFactor) + 20);

                serverPanel.putClientProperty("imagePath", imagePath);
                serverPanel.putClientProperty("baseW", baseW);
                serverPanel.putClientProperty("baseH", baseH);
                serverPanel.putClientProperty("baseX", baseX);
                serverPanel.putClientProperty("baseY", baseY);

                serverPanel.addMouseListener(new MouseAdapter() {
                    Point offset;
                    public void mousePressed(MouseEvent evt) {
                        offset = evt.getPoint();
                        centralPanel.revalidate();
                        centralPanel.repaint();
                        // Mostrar menú de opciones con click derecho
                        if (SwingUtilities.isRightMouseButton(evt)) {
                            JPopupMenu menu = new JPopupMenu();
                            JMenuItem eliminar = new JMenuItem("Eliminar");
                            JMenuItem conexion = new JMenuItem("Crear Conexión");
                            JMenuItem Econexion = new JMenuItem("Eliminar Conexión");
                            Econexion.addActionListener(ae -> {
                                if (firstSelectedPanel != null && firstSelectedPanel != serverPanel) {
                                    for (JPanel[] par : conexiones) {
                                        if ((par[0] == firstSelectedPanel && par[1] == serverPanel) || (par[0] == serverPanel && par[1] == firstSelectedPanel)) {
                                            conexiones.remove(par);
                                            break;
                                        }
                                    }
                                    firstSelectedPanel = null;
                                    centralPanel.repaint();
                                }
                            });
                            eliminar.addActionListener(ae -> {
                                centralPanel.remove(serverPanel);
                                centralPanel.revalidate();
                                centralPanel.repaint();
                            });
                            conexion.addActionListener(ae -> {
                                firstSelectedPanel = serverPanel;
                            });
                            menu.add(eliminar);
                            menu.add(conexion);
                            menu.add(Econexion);
                            menu.show(serverPanel, evt.getX(), evt.getY());
                        } else if (firstSelectedPanel != null && firstSelectedPanel != serverPanel) {
                            // Segundo click: crear conexión
                            conexiones.add(new JPanel[]{firstSelectedPanel, serverPanel});
                            firstSelectedPanel = null;
                            centralPanel.repaint();
                        }
                    }
                    public void mouseReleased(MouseEvent evt) {
                        // Actualiza la posición base al soltar
                        int logicX = (int)(serverPanel.getX() / zoomFactor);
                        int logicY = (int)(serverPanel.getY() / zoomFactor);
                        serverPanel.putClientProperty("baseX", logicX);
                        serverPanel.putClientProperty("baseY", logicY);
                        centralPanel.revalidate();
                        centralPanel.repaint();
                    }
                });
                serverPanel.addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent evt) {
                        int x = serverPanel.getX() + evt.getX() - serverPanel.getWidth() / 2;
                        int y = serverPanel.getY() + evt.getY() - serverPanel.getHeight() / 2;
                        serverPanel.setLocation(x, y);
                        centralPanel.revalidate();
                        centralPanel.repaint();
                    }
                });

                centralPanel.add(serverPanel);
                centralPanel.revalidate();
                centralPanel.repaint();

                if (serverCurrentSize == null) {
                    serverCurrentSize = new Dimension(baseW, baseH);
                }
                serverCount++;
            }
        });

        // ---- ROUTER ----
        routerButton.addActionListener(e -> {
            int cantidad = 1;
            boolean valido = false;
            while (!valido) {
                String input = JOptionPane.showInputDialog(null, "¿Cuántos routers desea agregar?", "Agregar Routers", JOptionPane.QUESTION_MESSAGE);
                if (input == null) return; // Cancelado
                try {
                    cantidad = Integer.parseInt(input);
                    if (cantidad < 1) throw new NumberFormatException();
                    valido = true;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número entero mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            for (int i = 0; i < cantidad; i++) {
                String imagePath = "src\\Images\\router.png";
                int baseW = 60, baseH = 60;
                if (routerCurrentSize != null) {
                    baseW = routerCurrentSize.width;
                    baseH = routerCurrentSize.height;
                }
                int baseX = 20 * routerCount, baseY = 20 * routerCount;

                ImageIcon originalIcon = new ImageIcon(imagePath);

                JPanel routerPanel = new JPanel();
                routerPanel.setLayout(new BoxLayout(routerPanel, BoxLayout.Y_AXIS));
                routerPanel.setOpaque(false);

                JLabel routerLabel = new JLabel(new ImageIcon(originalIcon.getImage().getScaledInstance((int)(baseW * zoomFactor), (int)(baseH * zoomFactor), Image.SCALE_SMOOTH)));
                routerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                JLabel nameLabel = new JLabel("Router " + routerCount);
                nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                nameLabel.setForeground(Color.BLACK);
                float baseFontSize = 10f;
                nameLabel.setFont(nameLabel.getFont().deriveFont(baseFontSize * (float)zoomFactor));

                routerPanel.add(routerLabel);
                routerPanel.add(nameLabel);

                routerPanel.setBounds((int)(baseX * zoomFactor), (int)(baseY * zoomFactor), (int)(baseW * zoomFactor), (int)(baseH * zoomFactor) + 20);

                routerPanel.putClientProperty("imagePath", imagePath);
                routerPanel.putClientProperty("baseW", baseW);
                routerPanel.putClientProperty("baseH", baseH);
                routerPanel.putClientProperty("baseX", baseX);
                routerPanel.putClientProperty("baseY", baseY);

                routerPanel.addMouseListener(new MouseAdapter() {
                    Point offset;
                    public void mousePressed(MouseEvent evt) {
                        offset = evt.getPoint();
                        centralPanel.revalidate();
                        centralPanel.repaint();
                        if (SwingUtilities.isRightMouseButton(evt)) {
                            JPopupMenu menu = new JPopupMenu();
                            JMenuItem eliminar = new JMenuItem("Eliminar");
                            JMenuItem conexion = new JMenuItem("Crear Conexión");
                            JMenuItem Econexion = new JMenuItem("Eliminar Conexión");
                            Econexion.addActionListener(ae -> {
                                if (firstSelectedPanel != null && firstSelectedPanel != routerPanel) {
                                    for (JPanel[] par : conexiones) {
                                        if ((par[0] == firstSelectedPanel && par[1] == routerPanel) || (par[0] == routerPanel && par[1] == firstSelectedPanel)) {
                                            conexiones.remove(par);
                                            break;
                                        }
                                    }
                                    firstSelectedPanel = null;
                                    centralPanel.repaint();
                                }
                            });
                            eliminar.addActionListener(ae -> {
                                centralPanel.remove(routerPanel);
                                centralPanel.revalidate();
                                centralPanel.repaint();
                            });
                            conexion.addActionListener(ae -> {
                                firstSelectedPanel = routerPanel;
                            });
                            menu.add(eliminar);
                            menu.add(conexion);
                            menu.add(Econexion);
                            menu.show(routerPanel, evt.getX(), evt.getY());
                        } else if (firstSelectedPanel != null && firstSelectedPanel != routerPanel) {
                            conexiones.add(new JPanel[]{firstSelectedPanel, routerPanel});
                            firstSelectedPanel = null;
                            centralPanel.repaint();
                        }
                    }
                    public void mouseReleased(MouseEvent evt) {
                        int logicX = (int)(routerPanel.getX() / zoomFactor);
                        int logicY = (int)(routerPanel.getY() / zoomFactor);
                        routerPanel.putClientProperty("baseX", logicX);
                        routerPanel.putClientProperty("baseY", logicY);
                        centralPanel.revalidate();
                        centralPanel.repaint();
                    }
                });
                routerPanel.addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent evt) {
                        int x = routerPanel.getX() + evt.getX() - routerPanel.getWidth() / 2;
                        int y = routerPanel.getY() + evt.getY() - routerPanel.getHeight() / 2;
                        routerPanel.setLocation(x, y);
                        centralPanel.revalidate();
                        centralPanel.repaint();
                    }
                });

                centralPanel.add(routerPanel);
                centralPanel.revalidate();
                centralPanel.repaint();

                if (routerCurrentSize == null) {
                    routerCurrentSize = new Dimension(baseW, baseH);
                }
                routerCount++;
            }
        });

        // ---- CLIENT ----
        clientButton.addActionListener(e -> {
            int cantidad = 1;
            boolean valido = false;
            while (!valido) {
                String input = JOptionPane.showInputDialog(null, "¿Cuántos clientes desea agregar?", "Agregar Clientes", JOptionPane.QUESTION_MESSAGE);
                if (input == null) return; // Cancelado
                try {
                    cantidad = Integer.parseInt(input);
                    if (cantidad < 1) throw new NumberFormatException();
                    valido = true;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número entero mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            for (int i = 0; i < cantidad; i++) {
                String imagePath = "src\\Images\\computer.png";
                int baseW = 80, baseH = 80;
                if (computerCurrentSize != null) {
                    baseW = computerCurrentSize.width;
                    baseH = computerCurrentSize.height;
                }
                int baseX = 100 + 20 * computerCount, baseY = 20 * computerCount;

                ImageIcon originalIcon = new ImageIcon(imagePath);

                JPanel clientPanel = new JPanel();
                clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
                clientPanel.setOpaque(false);

                JLabel clientLabel = new JLabel(new ImageIcon(originalIcon.getImage().getScaledInstance((int)(baseW * zoomFactor), (int)(baseH * zoomFactor), Image.SCALE_SMOOTH)));
                clientLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                JLabel nameLabel = new JLabel("PC " + computerCount);
                nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                nameLabel.setForeground(Color.BLACK);
                float baseFontSize = 10f;
                nameLabel.setFont(nameLabel.getFont().deriveFont(baseFontSize * (float)zoomFactor));

                clientPanel.add(clientLabel);
                clientPanel.add(nameLabel);

                clientPanel.setBounds((int)(baseX * zoomFactor), (int)(baseY * zoomFactor), (int)(baseW * zoomFactor), (int)(baseH * zoomFactor) + 20);

                clientPanel.putClientProperty("imagePath", imagePath);
                clientPanel.putClientProperty("baseW", baseW);
                clientPanel.putClientProperty("baseH", baseH);
                clientPanel.putClientProperty("baseX", baseX);
                clientPanel.putClientProperty("baseY", baseY);

                clientPanel.addMouseListener(new MouseAdapter() {
                    Point offset;
                    public void mousePressed(MouseEvent evt) {
                        offset = evt.getPoint();
                        centralPanel.revalidate();
                        centralPanel.repaint();
                        if (SwingUtilities.isRightMouseButton(evt)) {
                            JPopupMenu menu = new JPopupMenu();
                            JMenuItem eliminar = new JMenuItem("Eliminar");
                            JMenuItem conexion = new JMenuItem("Crear Conexión");
                            JMenuItem Econexion = new JMenuItem("Eliminar Conexión");
                            Econexion.addActionListener(ae -> {
                                if (firstSelectedPanel != null && firstSelectedPanel != clientPanel) {
                                    for (JPanel[] par : conexiones) {
                                        if ((par[0] == firstSelectedPanel && par[1] == clientPanel) || (par[0] == clientPanel && par[1] == firstSelectedPanel)) {
                                            conexiones.remove(par);
                                            break;
                                        }
                                    }
                                    firstSelectedPanel = null;
                                    centralPanel.repaint();
                                }
                            });
                            eliminar.addActionListener(ae -> {
                                centralPanel.remove(clientPanel);
                                centralPanel.revalidate();
                                centralPanel.repaint();
                            });
                            conexion.addActionListener(ae -> {
                                firstSelectedPanel = clientPanel;
                            });
                            menu.add(eliminar);
                            menu.add(conexion);
                            menu.add(Econexion);
                            menu.show(clientPanel, evt.getX(), evt.getY());
                        } else if (firstSelectedPanel != null && firstSelectedPanel != clientPanel) {
                            conexiones.add(new JPanel[]{firstSelectedPanel, clientPanel});
                            firstSelectedPanel = null;
                            centralPanel.repaint();
                        }
                    }
                    public void mouseReleased(MouseEvent evt) {
                        int logicX = (int)(clientPanel.getX() / zoomFactor);
                        int logicY = (int)(clientPanel.getY() / zoomFactor);
                        clientPanel.putClientProperty("baseX", logicX);
                        clientPanel.putClientProperty("baseY", logicY);
                        centralPanel.revalidate();
                        centralPanel.repaint();
                    }
                });
                clientPanel.addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent evt) {
                        int x = clientPanel.getX() + evt.getX() - clientPanel.getWidth() / 2;
                        int y = clientPanel.getY() + evt.getY() - clientPanel.getHeight() / 2;
                        clientPanel.setLocation(x, y);
                        centralPanel.revalidate();
                        centralPanel.repaint();
                    }
                });

                centralPanel.add(clientPanel);
                centralPanel.revalidate();
                centralPanel.repaint();

                if (computerCurrentSize == null) {
                    computerCurrentSize = new Dimension(baseW, baseH);
                }
                computerCount++;
            }
        });

        panel.add(routerButton);
        panel.add(clientButton);
        panel.add(serverButton);

        panelTotal.add(label, BorderLayout.NORTH);
        panelTotal.add(panel, BorderLayout.CENTER);

        centralPanel.addMouseWheelListener(e -> {
            if (e.getPreciseWheelRotation() < 0) {
                zoomFactor *= 1.1;
            } else {
                zoomFactor /= 1.1;
            }
            actualizarZoomComponentes(centralPanel, zoomFactor);
            centralPanel.revalidate();
            centralPanel.repaint();
        });

        return panelTotal;
    }

    // Método auxiliar para obtener el centro de un panel
    private Point getPanelCenter(JPanel panel) {
        int x = panel.getX() + panel.getWidth() / 2;
        int y = panel.getY() + panel.getHeight() / 2;
        return new Point(x, y);
    }

    // Actualiza tamaño, posición, imagen y fuente según el zoom
    private void actualizarZoomComponentes(JPanel centralPanel, double zoomFactor) {
        for (Component comp : centralPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                Integer baseW = (Integer) panel.getClientProperty("baseW");
                Integer baseH = (Integer) panel.getClientProperty("baseH");
                Integer baseX = (Integer) panel.getClientProperty("baseX");
                Integer baseY = (Integer) panel.getClientProperty("baseY");
                String imagePath = (String) panel.getClientProperty("imagePath");
                if (baseW != null && baseH != null && baseX != null && baseY != null && imagePath != null) {
                    int visualW = (int) (baseW * zoomFactor);
                    int visualH = (int) (baseH * zoomFactor);
                    int visualX = (int) (baseX * zoomFactor);
                    int visualY = (int) (baseY * zoomFactor);
                    panel.setBounds(visualX, visualY, visualW, visualH + 20);

                    for (Component c : panel.getComponents()) {
                        if (c instanceof JLabel label) {
                            if (label.getIcon() != null) {
                                // Redimensionar imagen
                                ImageIcon originalIcon = new ImageIcon(imagePath);
                                Image scaledImage = originalIcon.getImage().getScaledInstance(visualW, visualH, Image.SCALE_SMOOTH);
                                label.setIcon(new ImageIcon(scaledImage));
                            } else {
                                // Redimensionar fuente
                                float baseFontSize = 10f;
                                label.setFont(label.getFont().deriveFont(baseFontSize * (float)zoomFactor));
                            }
                        }
                    }
                }
            }
        }
    }
}
