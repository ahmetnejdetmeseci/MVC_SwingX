package nejdet.mvcFeatureButtons.buttonSwingX;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;

public class ButtonSample_SwingX extends JXButton {

    public ButtonSample_SwingX(String text, Icon icon) {
        super(text, icon);
        customizeAppearance();
    }

    private void customizeAppearance() {
        // Set font
        Font font = new Font("Calibri", Font.BOLD, 10);
        setFont(font);

        // Set foreground color
        setForeground(Color.BLACK);

        
        setPreferredSize(new Dimension(72, getPreferredSize().height));
        // Set background color
        // Create gradient color
        Color startColor = new Color(220, 220, 220); // Light gray
        Color endColor = new Color(100, 100, 100); // Dark gray
        GradientPaint gradient = new GradientPaint(
                new Point2D.Double(0, 0),
                startColor,
                new Point2D.Double(0, getHeight()),
                endColor
        );

        // Set background painter with gradient
        setBackgroundPainter(new Painter<JXButton>() {
            @Override
            public void paint(Graphics2D g, JXButton object, int width, int height) {
                g.setPaint(gradient);
                g.fillRect(0, 0, width, height);
            }
        });

        // Make the button opaque
        setOpaque(true);

        // Create a matte painter for additional styling
        MattePainter mattePainter = new MattePainter(new Color(51, 153, 255));
        putClientProperty("JButton.backgroundPainter", mattePainter);
    }
}
