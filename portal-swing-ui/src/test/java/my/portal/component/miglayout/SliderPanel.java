package my.portal.component.miglayout;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import my.portal.component.JPanelBase;
import my.portal.util.IOUtil;
import net.miginfocom.swing.MigLayout;

public class SliderPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;

//	private Timer timer;
	private JPanel imagePanel;
	private JButton buttonPrev;
	private JButton buttonNext;

	public SliderPanel() {
		initComponents();
	}

	private void initComponents() {
		
		setLayout(new MigLayout("fill", "[][]","[800px][40px!]"));
		imagePanel = new JPanel();
		imagePanel.setBackground(Color.PINK);
		add(imagePanel, "span,grow,wrap");

		buttonPrev = new JButton(IOUtil.loadIcon32("prev.png"));
		buttonPrev.setBorderPainted(false);
		buttonPrev.setContentAreaFilled(false);
		buttonPrev.setFocusPainted(false);
		buttonPrev.setOpaque(false);
		add(buttonPrev, "right");

		buttonNext = new JButton(IOUtil.loadIcon32("next.png"));
		buttonNext.setBorderPainted(false);
		buttonNext.setContentAreaFilled(false);
		buttonNext.setFocusPainted(false);
		buttonNext.setOpaque(false);
		add(buttonNext);
	}

}
