package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class JPanelGraphics extends JPanel {

    protected final BufferedImage image;

    public JPanelGraphics(Dimension d) {
        super();
        this.setSize(d);
        this.setOpaque(false);
        this.setBackground(Color.WHITE);
        image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public Dimension getPreferredSize() {
        return isPreferredSizeSet() ? super.getPreferredSize() : new Dimension(getWidth(), getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

}
