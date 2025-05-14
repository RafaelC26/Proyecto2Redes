import javax.swing.*;
import java.awt.*;

public class Principal extends JFrame {
    public void start() {
        setTitle("Simulador de Redes de Computadores");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Fondo principal minimalista
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 249, 255));
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        // Barra de herramientas minimalista centrada
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(250, 250, 250));
        toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 8)); 

        // Estilo para los botones
        Font btnFont = new Font("Segoe UI", Font.PLAIN, 15);
        Color btnFg = new Color(40, 40, 40);
        Color btnBg = new Color(250, 250, 250);

        // Botones solo con texto, sin iconos ni flechas
        JButton btnFile = new JButton("File");
        JButton btnZoom = new JButton("Zoom");
        JButton btnHerramientas = new JButton("Herramientas");
        JButton[] btns = {btnFile, btnZoom, btnHerramientas};
        for (JButton btn : btns) {
            btn.setFont(btnFont);
            btn.setForeground(btnFg);
            btn.setBackground(btnBg);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(0, 11, 0, 11));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        // Menú File
        JPopupMenu fileMenu = new JPopupMenu();
        JMenuItem miNuevo = new JMenuItem("Nuevo");
        JMenuItem miAbrir = new JMenuItem("Abrir");
        JMenuItem miGuardar = new JMenuItem("Guardar");
        JMenuItem miSalir = new JMenuItem("Salir");
        fileMenu.add(miNuevo);
        fileMenu.add(miAbrir);
        fileMenu.add(miGuardar);
        fileMenu.addSeparator();
        fileMenu.add(miSalir);
        btnFile.addActionListener(e -> fileMenu.show(btnFile, 0, btnFile.getHeight()));

        // Menú Zoom
        JPopupMenu zoomMenu = new JPopupMenu();
        JMenuItem miZoomIn = new JMenuItem("Acercar");
        JMenuItem miZoomOut = new JMenuItem("Alejar");
        JMenuItem miZoomReset = new JMenuItem("Restablecer");
        zoomMenu.add(miZoomIn);
        zoomMenu.add(miZoomOut);
        zoomMenu.add(miZoomReset);
        btnZoom.addActionListener(e -> zoomMenu.show(btnZoom, 0, btnZoom.getHeight()));

        // Menú Herramientas
        JPopupMenu herramientasMenu = new JPopupMenu();
        JMenuItem miAgregarNodo = new JMenuItem("Agregar Nodo");
        JMenuItem miAgregarEnlace = new JMenuItem("Agregar Enlace");
        JMenuItem miSimular = new JMenuItem("Simular");
        herramientasMenu.add(miAgregarNodo);
        herramientasMenu.add(miAgregarEnlace);
        herramientasMenu.add(miSimular);
        btnHerramientas.addActionListener(e -> herramientasMenu.show(btnHerramientas, 0, btnHerramientas.getHeight()));

        // Añadir botones centrados
        toolBar.add(btnFile);
        toolBar.add(btnZoom);
        toolBar.add(btnHerramientas);

        // Panel para la barra de herramientas (más pequeño y alineado a la izquierda)
        JPanel pn_Herramientas = new JPanel(null);
        pn_Herramientas.setBounds(0, 0, this.getWidth(), 30); // Más pequeño y solo ocupa el ancho de los botones
        pn_Herramientas.setBackground(new Color(250, 250, 250));
        toolBar.setBounds(0, 0, this.getWidth(), 30);
        pn_Herramientas.add(toolBar);

        // Título minimalista centrado
        JLabel lblTitulo = new JLabel("Simulador de Redes de Computadores", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(0, 120, 200));
        lblTitulo.setBounds(0, 70, 900, 40);

        // Panel central blanco y limpio, más centrado
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        panelCentral.setLayout(null);

        // Centramos el panel central en la ventana
        int panelWidth = 780;   // Aumenta el ancho
        int panelHeight = 500;  // Aumenta el alto
        int panelX = (900 - panelWidth) / 2;
        int panelY = 120;       // Opcional: sube un poco el panel
        panelCentral.setBounds(panelX, panelY, panelWidth, panelHeight);

        // Agregar componentes al panel principal
        mainPanel.add(pn_Herramientas);
        mainPanel.add(lblTitulo);
        mainPanel.add(panelCentral);

        setVisible(true);
    }
}
