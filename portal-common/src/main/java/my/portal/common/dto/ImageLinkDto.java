package my.portal.common.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.portal.common.ResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageLinkDto extends ResponseDto {

	private static final long serialVersionUID = 1L;
	private String targetUrl;
	private String imageUrl;
	private String text;
	
	private List<ImageLinkDto> subLinks;

}
