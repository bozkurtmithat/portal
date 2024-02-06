package my.portal.model;

import java.awt.Image;
import java.util.List;
import java.util.stream.Collectors;

import my.portal.common.dto.ImageLinkDto;

public class ImageLinkBuilder {

	public static final String STYLED_ANCHOR_LINK = "<html><a href=''>%s</a></html>";
	public static final String STYLED_PLAIN_TEXT = "<html>%s</html>"; // this is for autowrap of text

	ImageLink imageLink = new ImageLink();

	public static ImageLinkBuilder builder() {
		return new ImageLinkBuilder();
	}

	public static ImageLink buildFrom(ImageLinkDto imageLinkDto) {
		ImageLink imageLink = new ImageLink();
		imageLink.setTargetUrl(imageLinkDto.getTargetUrl());
		imageLink.setImageUrl(imageLinkDto.getImageUrl());
		imageLink.setSubLinks(imageLinkDto.getSubLinks().stream().map(ImageLinkBuilder::buildFrom).collect(Collectors.toList()));
		imageLink.setText(imageLinkDto.getText());
		imageLink.setHtmlAnchorText(createString(STYLED_ANCHOR_LINK, imageLinkDto.getText()));
		imageLink.setHtmlPlainText(createString(STYLED_PLAIN_TEXT, imageLinkDto.getText()));
		return imageLink;
	}
	
	public static ImageLink clone(ImageLink org)  {
		return builder().image(org.getImage())
				.imageUrl(org.getImageUrl())
				.subLinks(org.getSubLinks())
				.targetUrl(org.getTargetUrl())
				.text(org.getText())
				.build();
	}
	
	public ImageLink build() {
		ImageLink result = new ImageLink();
		result.setTargetUrl(imageLink.getTargetUrl());
		result.setImage(imageLink.getImage());
		result.setImageUrl(imageLink.getImageUrl());
		result.setSubLinks(imageLink.getSubLinks());
		result.setText(imageLink.getText());
		result.setHtmlAnchorText(createString(STYLED_ANCHOR_LINK, imageLink.getText()));
		result.setHtmlPlainText(createString(STYLED_PLAIN_TEXT, imageLink.getText()));
		return result;
	}

	private static String createString(String format, String text) {
		if (text == null)
			return null;
		String result = text;
		int limit = 50;
		if (text.length() > limit) {
			int i = limit;
			for (; i > 0; i--) {
				if (Character.isWhitespace(text.charAt(i))) {
					break;
				}
			}
			result = text.substring(0, i) + "<br>" + text.substring(i);
		}
		return String.format(format, result);
	}

	public ImageLinkBuilder image(Image image) {
		imageLink.setImage(image);
		return this;
	}

	public ImageLinkBuilder imageUrl(String imageUrl) {
		imageLink.setImageUrl(imageUrl);
		return this;
	}

	public ImageLinkBuilder subLinks(List<ImageLink> subLinks) {
		imageLink.setSubLinks(subLinks);
		return this;
	}

	public ImageLinkBuilder targetUrl(String targetUrl) {
		imageLink.setTargetUrl(targetUrl);
		return this;
	}

	public ImageLinkBuilder text(String text) {
		imageLink.setText(text);
		return this;
	}

}
