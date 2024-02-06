package my.portal.controllers;

import java.util.List;

import my.portal.model.ImageLink;

public interface LinkController{

	public List<ImageLink> getImageLinks();
	
	public List<ImageLink> getTopImageLinks();

} 