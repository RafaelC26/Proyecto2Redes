import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkPanel extends JPanel {
    private final List<NetworkComponent> components = new ArrayList<>();
    private final ConnectionManager connectionManager = new ConnectionManager();

    public NetworkPanel() {
        setLayout(null);
        
        NetworkComponent router = new RouterComponent("Router 1", 100, 100);
        NetworkComponent client = new ClientComponent("Cliente 1", 300, 200);
        NetworkComponent server = new ServerComponent("Servidor 1", 500, 400);

        addComponent(router);
        addComponent(client);
        addComponent(server);

        connectionManager.addConnection(router, client);
        connectionManager.addConnection(router, server);
    }

    public void addComponent(NetworkComponent component) {
        components.add(component);
        this.add(component.getPanel());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        connectionManager.drawConnections(g);
    }
}
