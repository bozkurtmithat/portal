package my.portal.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

public class ImageLinkModel extends Observable{

	List<ImageLink> links = new ArrayList<>();
	
	public void addImageLink(ImageLink link) {
		links.add(link);
		setChanged();
		notifyObservers(link);
	}
	
	public List<ImageLink> getImageLinks() {
		return links;
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
}
