import java.awt.*;
import javax.swing.*;

public class TitleBuilder {
    public JLabel build() {
        JLabel lblTitulo = new JLabel("Simulador de Redes de Computadores", JLabel.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(0, 120, 200));
        lblTitulo.setBounds(0, 70, 900, 40);
        return lblTitulo;
    }
}
