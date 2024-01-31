package my.portal.component.panels;

import java.awt.Font;
import java.util.Observable;

import my.portal.bean.ImageLink;
import my.portal.bean.ImageLinkModel;
import my.portal.component.JLabelHyperlink;
import my.portal.component.JPanelBase;

public class AbsoluteLinksPanel extends JPanelBase {

	private static final long serialVersionUID = 1L;
	private ImageLinkModel linkModel;

	public AbsoluteLinksPanel() {
		linkModel = new ImageLinkModel();
		linkModel.addObserver(this);
		setLayout(null);
	}

	private void addLinks() {
		removeAll();
		int gapy = 5;
		int locationX = 5;
		int locationY = 30; // 20 button için
		for (int i = 0; i < linkModel.getImageLinks().size(); i++) {
			ImageLink imageLink = linkModel.getImageLinks().get(i);
			JLabelHyperlink jLink = new JLabelHyperlink(imageLink.clone());
			jLink.setFont(new Font(getFont().getName(), Font.BOLD, getFont().getSize()));
			jLink.setBounds(locationX, locationY,
					getWidth(jLink.getPreferredSize()),
					getHeight(jLink.getPreferredSize()));
			add(jLink);
			locationY += jLink.getPreferredSize().getHeight() + gapy;
		}
		revalidate();
	}

	public ImageLinkModel getModel() {
		return linkModel;
	}

	@Override
	public void update(Observable o, Object arg) {
		addLinks();
	}
}
