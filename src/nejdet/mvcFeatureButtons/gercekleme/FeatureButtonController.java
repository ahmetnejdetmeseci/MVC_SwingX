package nejdet.mvcFeatureButtons.gercekleme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nejdet.mvcFeatureButtons.buttonSwingX.ButtonSample_SwingX;

public class FeatureButtonController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonSample_SwingX source = (ButtonSample_SwingX) e.getSource();
        System.out.println("Button clicked: " + source.getText());
	}

}
