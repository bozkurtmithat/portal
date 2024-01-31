package my.portal.controllers.impl;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import my.portal.bean.ImageLink;
import my.portal.common.Impl;
import my.portal.controllers.LinkController;
import my.portal.util.IOUtil;

@Impl(value = LinkController.class)
public class MockLinkController implements LinkController{
	
	public List<ImageLink> getLinks() {

		List<ImageLink> links = new ArrayList<>();
		Image linkImage = IOUtil.getImage("link.png");
		links.add(createLink("Adalet Akademisi", "https://uzem.taa.gov.tr/", linkImage ));
		links.add(createLink("Adalet Akademisi Uzaktan Eğitim Merkezi", "https://uzem.taa.gov.tr/", linkImage ));
		links.add(createLink("Bir Bilen", "http://birbilen.adalet.gov.tr/", linkImage ));
		links.add(createLink("Bilgi Güvenliği Politikası", "https://bigm.adalet.gov.tr/Resimler/pdf/bilgiguvenligi.pdf", linkImage));
		links.add(createLink("Donanım Bakım ve Destek", "http://donanim.adalet.gov.tr/", linkImage ));
		links.add(createLink("e-Duruşma", "https://edurusmabilgi.adalet.gov.tr/", linkImage));
		links.add(createLink("UYAP Akademi", "https://uyapakademi.adalet.gov.tr/", linkImage));
		links.add(createLink("SEGBİS (Video Konferans)", "http://segbis.adalet.gov.tr/", linkImage ));
		return links;
	}

	@Override
	public List<ImageLink> getTopLinks() {
		List<ImageLink> links = new ArrayList<>();
		Image linkImage = null;//IOUtil.loadIcon("link.png");
		links.add(createLink("Makanlık", "https://google.com", linkImage ));
		links.add(createLink("Sargı Birimleri", "https://google.com", linkImage ));
		links.add(createLink("Yardım Merkezi", "https://google.com", linkImage ));
		links.add(createLink("Şifre İşlemleri", "https://google.com", linkImage));
		return links;
	}
	
	private ImageLink createLink(String text, String url, Image image) {
		return ImageLink.builder().text(text).targetUrl(url).image(image).build();
	}
	

}
