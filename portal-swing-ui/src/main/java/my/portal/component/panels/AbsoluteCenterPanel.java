package my.portal.component.panels;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;

import my.portal.component.JPanelBase;
import my.portal.model.SliderModel;
import my.portal.util.IOUtil;

public class AbsoluteCenterPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;

	private SliderModel sliderModel;

	public AbsoluteCenterPanel(Dimension prefereSize) {
		setPreferredSize(prefereSize);
		initComponents();
	}

	public void setSliderModel(SliderModel sliderModel) {
		this.sliderModel = sliderModel;
	}

	private void initComponents() {
		setLayout(null);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (sliderModel.currentImageLink() != null
						&& sliderModel.currentImageLink().getTargetUrl() != null) {
					IOUtil.openBrowser(AbsoluteCenterPanel.this, sliderModel.currentImageLink().getTargetUrl());
				}
			}
		});
//		setBackgroundImage(IOUtil.getImage(sliderModel.currentImageLink()));

		int middle = (int) getPreferredSize().getWidth() / 2;
		JButton buttonPrev = createSliderButton("left-arrow-circle.png", middle - 80);
		buttonPrev.addActionListener(e -> setBackgroundImageAndRepaint(
				IOUtil.getImage(sliderModel.previousImageLink())));
		add(buttonPrev);

		JButton buttonNext = createSliderButton("right-arrow-circle.png", middle);
		buttonNext.addActionListener(e -> setBackgroundImageAndRepaint(
				IOUtil.getImage(sliderModel.nextImageLink())));
		add(buttonNext);
	}

	private JButton createSliderButton(String iconResource, int locationX) {
		JButton button = new JButton(IOUtil.loadIcon32(iconResource));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBounds(locationX,
				getHeight(getPreferredSize()) - 40,
				getWidth(button.getPreferredSize()),
				getHeight(button.getPreferredSize()));
		return button;
	}

	private void setBackgroundImageAndRepaint(Image image) {
		setBackgroundImage(image);
		repaint();
	}
}
