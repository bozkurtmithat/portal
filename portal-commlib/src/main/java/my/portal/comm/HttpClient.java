package my.portal.comm;

import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;

public interface HttpClient {

	public void sendAsynRequest(RequestDto requestDto);
	
	public ResponseDto sendSyncRequest(RequestDto requestDto);
}
