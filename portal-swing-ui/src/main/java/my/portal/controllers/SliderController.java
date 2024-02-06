package my.portal.controllers;

import java.util.List;

import my.portal.model.ImageLink;

public interface SliderController{

	public List<ImageLink> getImageLinks();
	
	public ImageLink currentImageLink();
	
	public ImageLink nextImageLink();
	
	public ImageLink previousImageLink();
	
	public int getSpeed();
}
