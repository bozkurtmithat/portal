package my.portal.controllers;

import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;

public interface HttpClientController {

	public void sendAsynRequest(RequestDto requestDto);
	
	public ResponseDto sendSyncRequest(RequestDto requestDto);
}
