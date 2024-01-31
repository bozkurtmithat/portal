package my.portal.comm;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.portal.common.RequestDto;

@Getter
@Setter
@NoArgsConstructor
public class Request {
	
	private String uri;
	private RequestDto requestDto;
	private ResponseListener responseListener;
	private RequestMethods method = RequestMethods.POST;
	private final List<HttpCookie> cookies = new ArrayList<>();
	private final List<NVPair> headers = new ArrayList<>();
	private final List<NVPair> uriParams = new ArrayList<>();
	
	public Request(RequestDto requestDto, ResponseListener responseListener) {
		this.requestDto = requestDto;
		this.responseListener = responseListener;
	}
	
}
