package my.portal.controllers.impl;


import my.portal.comm.HttpClient;
import my.portal.comm.NVPair;
import my.portal.comm.Request;
import my.portal.comm.RequestMethods;
import my.portal.comm.ResponseListener;
import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;
import my.portal.common.controllers.CDI;
import my.portal.controllers.HttpClientController;
import my.portal.model.StaticAuthorizedUser;

/**
 * Responsible for interaction with portal-commlib library.
 */
public class SwingHttpClient implements HttpClientController {

	private SwingHttpClientConfig config = CDI.lookup(SwingHttpClientConfig.class);
	HttpClient httpClient = CDI.getImpl(HttpClient.class);

	private Request createRequest(RequestDto requestDto) {
		Request request = new Request();
		request.setRequestDto(requestDto);
		request.setMethod(RequestMethods.POST);
		request.setUri(config.getServiceUrl());
		request.getUriParams().add(new NVPair("screen", "test"));
		request.getHeaders().add(new NVPair("UYAP-User", StaticAuthorizedUser.getSicil()));
		return request;
	}

	public void sendAsynRequest(RequestDto requestDto, ResponseListener responseListener) {
		Request request = createRequest(requestDto);
		request.setResponseListener(responseListener);
		httpClient.sendAsynRequest(request);
	}

	@Override
	public void sendAsynRequest(RequestDto requestDto) {
		throw new RuntimeException("use method -> sendAsynRequest(RequestDto requestDto, ResponseListener responseListener)");

	}

	@Override
	public ResponseDto sendSyncRequest(RequestDto requestDto) {
		Request request = createRequest(requestDto);
		return httpClient.sendSyncRequest(request);
	}

}
