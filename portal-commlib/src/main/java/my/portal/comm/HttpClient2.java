package my.portal.comm;

import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;

public interface HttpClient2 {

	public void sendAsynRequest(RequestDto requestDto, ResponseListener responseListener);
	
	public ResponseDto sendSyncRequest(RequestDto requestDto);
}
