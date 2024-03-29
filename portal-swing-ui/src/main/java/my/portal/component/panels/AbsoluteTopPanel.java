package my.portal.component.panels;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;

import my.portal.component.JLabelHyperlink;
import my.portal.component.JPanelBase;
import my.portal.model.ImageLink;
import my.portal.model.ImageLinkModel;

public class AbsoluteTopPanel extends JPanelBase{

	private static final long serialVersionUID = 1L;
	private ImageLinkModel linkModel;

	public AbsoluteTopPanel() {
		linkModel = new ImageLinkModel();
		linkModel.addObserver(this);
		setLayout(null);
		updateLinkComponents();
	}
	
	public ImageLinkModel getModel() {
		return linkModel;
	}

	@Override
	public void update(Observable o, Object arg) {
		updateLinkComponents();
	}

	private void updateLinkComponents() {
		removeAll();
		int gapx=30;
		int locationX = 30;
		int locationY = 30; //20 button için 
		for (int i = 0; i < linkModel.getImageLinks().size(); i++) {
			ImageLink imageLink = linkModel.getImageLinks().get(i);
			JLabelHyperlink jLink = new JLabelHyperlink(imageLink);
			jLink.setForeground(Color.WHITE);
			jLink.setFont(new Font(getFont().getName(), Font.BOLD, getFont().getSize()+2));
			jLink.setBounds(locationX, locationY, (int) jLink.getPreferredSize().getWidth(),
					(int) jLink.getPreferredSize().getHeight());
			add(jLink);
			locationX += jLink.getPreferredSize().getWidth()+gapx;
		}
		revalidate();
	}

	

}
