import java.awt.*;
import javax.swing.*;

public class MenuFactory {

    private static JButton createButton(String text, JPopupMenu menu) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(new Color(40, 40, 40));
        btn.setBackground(new Color(250, 250, 250));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(0, 11, 0, 11));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> menu.show(btn, 0, btn.getHeight()));
        return btn;
    }

    public static JButton createFileButton() {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem("Nuevo"));
        menu.add(new JMenuItem("Abrir"));
        menu.add(new JMenuItem("Guardar"));
        menu.addSeparator();
        menu.add(new JMenuItem("Salir"));
        return createButton("File", menu);
    }

    public static JButton createZoomButton() {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem("Acercar"));
        menu.add(new JMenuItem("Alejar"));
        menu.add(new JMenuItem("Restablecer"));
        return createButton("Zoom", menu);
    }

    public static JButton createHerramientasButton() {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem("Agregar Nodo"));
        menu.add(new JMenuItem("Agregar Enlace"));
        menu.add(new JMenuItem("Simular"));
        return createButton("Herramientas", menu);
    }
}
