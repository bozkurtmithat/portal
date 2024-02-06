package my.portal.comm;

import my.portal.common.ResponseDto;


public interface HttpClient{
	
	public void sendAsynRequest(Request request);
	
	public ResponseDto sendSyncRequest(Request request);
	
}
