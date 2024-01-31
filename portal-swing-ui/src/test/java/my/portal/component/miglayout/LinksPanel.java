package my.portal.component.miglayout;

import java.util.ArrayList;
import java.util.List;

import my.portal.bean.ImageLink;
import my.portal.component.JLabelHyperlink;
import my.portal.component.JPanelBase;
import net.miginfocom.swing.MigLayout;

public class LinksPanel extends JPanelBase{

	private static final long serialVersionUID = 1L;
	List<ImageLink> links = new ArrayList<>();
	
	public LinksPanel(List<ImageLink> links) {
		this.links = links;
		setLayout(new MigLayout("wrap 1,gapy 5"));
		links.stream().forEach(this::addLink);
	}
	
	private void addLink(ImageLink link) {
		add(new JLabelHyperlink(link), "wrap");
	}

	public void setLinks(List<ImageLink> links) {
		this.links = links;
	}
}
