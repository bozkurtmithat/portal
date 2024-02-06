package my.portal.controllers.impl;

import java.util.ArrayList;
import java.util.List;

import my.portal.common.Impl;
import my.portal.controllers.SliderController;
import my.portal.model.ImageLink;
import my.portal.model.ImageLinkBuilder;

@Impl(SliderController.class)
public class MockSliderControllerImpl implements SliderController {

	private int currentIndex=0;
	private List<ImageLink> imageLinks = new ArrayList<>();

	@Override
	public List<ImageLink> getImageLinks() {

		if (imageLinks.isEmpty()) {
			List<ImageLink> links = new ArrayList<>();
			String baseUrl = "https://google.com";
			links.add(new ImageLinkBuilder().targetUrl(baseUrl).imageUrl("https://cdn.adalet.gov.tr/portal/duyuru/resim/27649.jpeg").build());
			links.add(new ImageLinkBuilder().targetUrl(baseUrl).imageUrl("https://cdn.adalet.gov.tr/portal/duyuru/resim/27648.jpeg").build());
			links.add(new ImageLinkBuilder().targetUrl(baseUrl).imageUrl("https://cdn.adalet.gov.tr/portal/duyuru/resim/8960.jpeg").build());
			links.add(new ImageLinkBuilder().targetUrl(baseUrl).imageUrl("https://cdn.adalet.gov.tr/portal/duyuru/resim/26881.jpeg").build());
			links.add(new ImageLinkBuilder().targetUrl(baseUrl).imageUrl("https://cdn.adalet.gov.tr/portal/duyuru/resim/26624.png").build());
			links.add(new ImageLinkBuilder().targetUrl(baseUrl).imageUrl("https://cdn.adalet.gov.tr/portal/duyuru/resim/15872.png").build());
			imageLinks = links;
		}
		return imageLinks;
	}
	
	@Override
	public ImageLink currentImageLink() {
		return getImageLinks().get(currentIndex);
	}
	
	@Override
	public ImageLink previousImageLink() {
		int previous = currentIndex - 1;
		currentIndex = (previous > 0) ? previous : getImageLinks().size()-1;
		return getImageLinks().get(currentIndex);
	}
	
	@Override
	public ImageLink nextImageLink() {
		int next = currentIndex + 1;
		currentIndex = next < getImageLinks().size() ? next : 0;
		return getImageLinks().get(currentIndex);
	}
	
	@Override
	public int getSpeed() {
		return 3;
	}
	
	

}
