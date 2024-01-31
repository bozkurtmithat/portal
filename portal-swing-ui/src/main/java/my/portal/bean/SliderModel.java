package my.portal.bean;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.Timer;

import my.portal.controllers.SliderController;

public class SliderModel extends Observable implements SliderController {

	private int currentIndex=0;
	private List<ImageLink> imageLinks = new ArrayList<>();
	private Timer timer = new Timer(1000, this::autoNextImage);
	
	public SliderModel(List<ImageLink> images) {
		imageLinks = images;
		timer = new Timer(1000, this::autoNextImage);
		timer.start();
	}
	
	public void autoNextImage(ActionEvent e){
		getImageLinks();
		nextImageLink();
	}
	
	@Override
	public List<ImageLink> getImageLinks() {
		if(imageLinks.isEmpty()) {
			getHttpClient().sendAsynRequest(null);
		}
		return imageLinks;
	}

	@Override
	public ImageLink currentImageLink() {
		return getImageLinks().get(currentIndex);
	}
	
	@Override
	public ImageLink nextImageLink() {
		int next = currentIndex + 1;
		currentIndex = next < getImageLinks().size() ? next : 0;
		return getImageLinks().get(currentIndex);
	}

	@Override
	public ImageLink previousImageLink() {
		int previous = currentIndex - 1;
		currentIndex = (previous > 0) ? previous : getImageLinks().size()-1;
		return getImageLinks().get(currentIndex);
	}

	@Override
	public int getSpeed() {
		return 3;
	}

}
