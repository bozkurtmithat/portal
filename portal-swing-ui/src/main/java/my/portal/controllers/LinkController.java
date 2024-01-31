package my.portal.controllers;

import java.util.List;

import my.portal.bean.ImageLink;

public interface LinkController{

	public List<ImageLink> getLinks();
	
	public List<ImageLink> getTopLinks();
}
