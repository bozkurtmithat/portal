package my.portal.controllers;

import my.portal.comm.ResponseListener;
import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;

public interface HttpClientController {

	public void sendAsynRequest(RequestDto requestDto);
	
	public void sendAsynRequest(RequestDto requestDto, ResponseListener responseListener);
	
	public ResponseDto sendSyncRequest(RequestDto requestDto);
}
