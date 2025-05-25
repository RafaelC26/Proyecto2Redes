import java.awt.*;
import javax.swing.*;

public class MenuThings {
    public static int width =  60;
    public static int height = 60;
    public static JButton createRouterButton() {
        

        String imagePath = "src\\Images\\router.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);

        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        return button;
    }

    public static JButton createClientButton() {
        

        String imagePath = "src\\Images\\computer.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);

        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        return button;
    }

    public static JButton createServerButton() {
        

        String imagePath = "src\\Images\\server.png";
        ImageIcon originalIcon = new ImageIcon(imagePath);

        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        return button;
    }
}
