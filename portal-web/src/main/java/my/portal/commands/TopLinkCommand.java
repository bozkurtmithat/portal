package my.portal.commands;

import my.portal.command.Command;
import my.portal.command.CommandExecutor;
import my.portal.common.DtoBase;
import my.portal.common.DtoList;
import my.portal.common.PortalException;
import my.portal.common.dto.ImageLinkDto;

@Command("getTopLinks")
public class TopLinkCommand implements CommandExecutor {

	@Override
	public DtoBase execute(DtoBase param) throws PortalException {
		
		DtoList<ImageLinkDto> links = new DtoList<>();
		String imageUrl = "res://link.png";
		links.add(createLink("Adalet Akademisi", "https://uzem.taa.gov.tr/", imageUrl ));
		links.add(createLink("Adalet Akademisi Uzaktan Eğitim Merkezi", "https://uzem.taa.gov.tr/", imageUrl ));
		links.add(createLink("Bir Bilen", "http://birbilen.adalet.gov.tr/", imageUrl ));
		links.add(createLink("Bilgi Güvenliği Politikası", "https://bigm.adalet.gov.tr/Resimler/pdf/bilgiguvenligi.pdf", imageUrl));
		links.add(createLink("Donanım Bakım ve Destek", "http://donanim.adalet.gov.tr/", imageUrl ));
		links.add(createLink("e-Duruşma", "https://edurusmabilgi.adalet.gov.tr/", imageUrl));
		links.add(createLink("UYAP Akademi", "https://uyapakademi.adalet.gov.tr/", imageUrl));
		links.add(createLink("SEGBİS (Video Konferans)", "http://segbis.adalet.gov.tr/", imageUrl ));
		return links;
	}
	
	
	private ImageLinkDto createLink(String text, String targetUrl, String imageUrl) {
		ImageLinkDto imageLinkDto = new ImageLinkDto();
		imageLinkDto.setText(text);
		imageLinkDto.setTargetUrl(targetUrl);
		imageLinkDto.setImageUrl(imageUrl);
		return imageLinkDto;
	}

}
