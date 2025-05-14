import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal extends JFrame {
    public void start(){
        setTitle("Simulador de Redes de Computadores");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBounds(0, 0, this.WIDTH, this.HEIGHT);
        


        add(panelPrincipal);




        setVisible(true);
    }
}
