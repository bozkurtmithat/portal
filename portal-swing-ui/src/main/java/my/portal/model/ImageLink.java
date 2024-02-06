package my.portal.model;

import java.awt.Image;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageLink{

	private String targetUrl;
	private String imageUrl;
	private Image  image;
	private String text;
	private String htmlPlainText;
	private String htmlAnchorText;
	private List<ImageLink> subLinks;
		
}
