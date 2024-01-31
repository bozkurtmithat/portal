package my.portal.bean;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageLink implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;
	public static final String STYLED_ANCHOR_LINK = "<html><a href=''>%s</a></html>";
	public static final String STYLED_PLAIN_TEXT = "<html>%s</html>"; // this is for autowrap of text
	
	private String targetUrl;
	private String imageUrl;
	private Image  image;
	private String text;
	
	private List<ImageLink> subLinks;
	
	public ImageLink(String targetUrl, String imageUrl) {
		super();
		this.targetUrl = targetUrl;
		this.imageUrl = imageUrl;
	}
	
	public boolean hasText() {
		return text != null ;
	}
	
	public boolean hasIcon() {
		return image != null ;
	}
	
	public boolean hasTargetUrl() {
		return targetUrl != null ;
	}
	
	public boolean hasImageUrl() {
		return imageUrl != null ;
	}
	
	public boolean hasSubLink(){
		return subLinks != null && !subLinks.isEmpty();
	}
	
	public void addSublink(ImageLink subLink) {
		subLinks.add(subLink);
	}
	
	public String getHyperlinkText() {
		return hasText() ? createString(STYLED_ANCHOR_LINK) : "";
	}

	public String getPlainText() {
		return hasText() ? createString(STYLED_PLAIN_TEXT) : "";
	}
	
	private String createString(String format) {
		String result = text;
		int limit=50;
		if(text.length() > limit) {
			int i=limit;
			for(;i>0;i--) {
				if(Character.isWhitespace(text.charAt(i))) {
					break;
				}
			}
			result = text.substring(0,i)+"<br>"+text.substring(i);
		}
		return String.format(format, result);
	}
	
	@Override
	public ImageLink clone()  {
		return builder().image(image)
				.imageUrl(imageUrl)
				.subLinks(subLinks)
				.targetUrl(targetUrl)
				.text(text)
				.build();
	}
		
}
