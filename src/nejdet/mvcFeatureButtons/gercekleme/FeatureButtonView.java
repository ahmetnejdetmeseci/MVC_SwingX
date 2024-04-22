package nejdet.mvcFeatureButtons.gercekleme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import nejdet.mvcFeatureButtons.arayuz.IFeatureButton;
import nejdet.mvcFeatureButtons.buttonSwingX.ButtonSample_SwingX;

public class FeatureButtonView extends JPanel implements IFeatureButton {
    private FeatureButtonController controller;
    private JPanel buttonPanel;

    private Dimension btnDim = new Dimension(72, 30);

    public FeatureButtonView(FeatureButtonController controller) {
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout for the entire panel

        JPanel togglePanel = new JPanel();
        togglePanel.setPreferredSize(btnDim); // Set preferred size for togglePanel

        JButton toggleButton = new JButton("Toggle");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setVisible(!buttonPanel.isVisible()); // Toggle visibility
            }
        });
        togglePanel.add(toggleButton);
        add(togglePanel);

        // Panel to hold the additional buttons
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout for buttonPanel

        for (int i = 1; i <= 4; i++) {
            String text = "Button " + i;
            Icon icon = null;
            ButtonSample_SwingX button = new ButtonSample_SwingX(text, icon);
            button.setText(text);
            button.addActionListener(controller);
            buttonPanel.add(button);
        }
        add(buttonPanel);
        buttonPanel.setVisible(false); // Initially hide the buttons
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
