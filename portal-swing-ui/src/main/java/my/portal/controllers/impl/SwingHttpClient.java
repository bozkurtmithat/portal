package my.portal.controllers.impl;

import my.portal.comm.ResponseListener;
import my.portal.common.Impl;
import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;
import my.portal.controllers.HttpClientController;

@Impl(HttpClientController.class)
public class SwingHttpClient implements HttpClientController {

	@Override
	public void sendAsynRequest(RequestDto requestDto, ResponseListener responseListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseDto sendSyncRequest(RequestDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
