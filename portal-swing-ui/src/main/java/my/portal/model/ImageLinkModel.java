package my.portal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import my.portal.common.RequestDto;
import my.portal.controllers.LinkController;

public class ImageLinkModel extends ModelBase implements LinkController{

	private String command;
	private List<ImageLink> links = new ArrayList<>();
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public void addImageLink(ImageLink link) {
		links.add(link);
		setChanged();
		notifyObservers(link);
	}
		
	public void removeImageLink(ImageLink link) {
		boolean change = false;
		ListIterator<ImageLink> listIterator = links.listIterator();
		while(listIterator.hasNext()) {
			ImageLink l = listIterator.next();
			if( ( l.getText() != null && l.getText().equals(link.getText()) )
					&& (l.getTargetUrl()!= null && l.getTargetUrl().equals(link.getTargetUrl())) ) {
				listIterator.remove();
				change=true;
			}
		}
		
		if(change) {
			setChanged();
			notifyObservers(links);
		}
	}

	@Override
	public List<ImageLink> getImageLinks() {
		sendAsynRequest(new RequestDto(), this);
		return links;
	}

	@Override
	public List<ImageLink> getTopImageLinks() {
		return links;
	}
}
