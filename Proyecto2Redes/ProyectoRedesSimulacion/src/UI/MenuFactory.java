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


    public static JButton createHerramientasButton(JFrame frame) {
        JPopupMenu menu = new JPopupMenu();
        JMenu cambiarPaletaMenu = new JMenu("Cambiar paleta de colores");
        JMenuItem clara = new JMenuItem("Paleta clara");
        JMenuItem oscura = new JMenuItem("Paleta oscura");

        oscura.addActionListener(e -> {
            aplicarPaletaOscura(frame);
            frame.repaint();
        });

        clara.addActionListener(e -> {
            aplicarPaletaClara(frame);
            frame.repaint();
        });

        cambiarPaletaMenu.add(clara);
        cambiarPaletaMenu.add(oscura);
        menu.add(cambiarPaletaMenu);
        return createButton("Herramientas", menu);
    }

    public static void aplicarPaletaOscura(Component comp) {
        Color fondo = new Color(34, 34, 34);
        Color texto = new Color(220, 220, 220);
        Color boton = new Color(50, 50, 50);

        if (comp instanceof JPanel || comp instanceof JPopupMenu) {
            comp.setBackground(fondo);
        }
        if (comp instanceof JLabel) {
            comp.setForeground(texto);
        }
        if (comp instanceof JButton) {
            comp.setBackground(boton);
            comp.setForeground(texto);
            ((JButton) comp).setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
        }
        if (comp instanceof JMenuBar || comp instanceof JMenu || comp instanceof JMenuItem) {
            comp.setBackground(boton);
            comp.setForeground(texto);
        }
        if (comp instanceof JTextField) {
            comp.setBackground(new Color(44, 44, 44));
            comp.setForeground(texto);
            ((JTextField) comp).setCaretColor(texto);
        }
        if (comp instanceof JScrollPane) {
            comp.setBackground(fondo);
            ((JScrollPane) comp).getViewport().setBackground(fondo);
        }
        if (comp instanceof JFrame) {
            comp.setBackground(fondo);
        }
        if (comp instanceof JToolBar) {
            comp.setBackground(fondo);
            comp.setForeground(texto);
            ((JToolBar) comp).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(70, 70, 70)));
        }
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                aplicarPaletaOscura(child);
            }
        }
    }

    public static void aplicarPaletaClara(Component comp) {
        Color fondo = new Color(245, 249, 255);
        Color texto = new Color(30, 30, 30);
        Color boton = new Color(250, 250, 250);

        if (comp instanceof JPanel || comp instanceof JPopupMenu) {
            comp.setBackground(fondo);
        }
        if (comp instanceof JLabel) {
            comp.setForeground(texto);
        }
        if (comp instanceof JButton) {
            comp.setBackground(boton);
            comp.setForeground(texto);
            ((JButton) comp).setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        }
        if (comp instanceof JMenuBar || comp instanceof JMenu || comp instanceof JMenuItem) {
            comp.setBackground(boton);
            comp.setForeground(texto);
        }
        if (comp instanceof JTextField) {
            comp.setBackground(Color.WHITE);
            comp.setForeground(texto);
            ((JTextField) comp).setCaretColor(texto);
        }
        if (comp instanceof JScrollPane) {
            comp.setBackground(fondo);
            ((JScrollPane) comp).getViewport().setBackground(fondo);
        }
        if (comp instanceof JFrame) {
            comp.setBackground(fondo);
        }
        if (comp instanceof JToolBar) {
            comp.setBackground(fondo);
            comp.setForeground(texto);
            ((JToolBar) comp).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        }
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                aplicarPaletaClara(child);
            }
        }
    }
}
