package my.portal.comm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.portal.common.ResponseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	
	private int responseCode;
	private ResponseDto responseDto;

}
