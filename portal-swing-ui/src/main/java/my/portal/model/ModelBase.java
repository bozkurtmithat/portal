package my.portal.model;

import java.util.Observable;

import my.portal.comm.ResponseListener;
import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;
import my.portal.controllers.HttpClientController;
import my.portal.controllers.impl.SwingHttpClient;

public class ModelBase extends Observable implements HttpClientController, ResponseListener {

	private SwingHttpClient httpClientProxy = new SwingHttpClient();

	@Override
	public void sendAsynRequest(RequestDto requestDto) {
		httpClientProxy.sendAsynRequest(requestDto, this);
		
	}
	
	@Override
	public void sendAsynRequest(RequestDto requestDto, ResponseListener responseListener) {
		httpClientProxy.sendAsynRequest(requestDto,responseListener);
	}

	@Override
	public ResponseDto sendSyncRequest(RequestDto requestDto) {
		ResponseDto responseDto = httpClientProxy.sendSyncRequest(requestDto);
//		setChanged();
//		notifyObservers(responseDto);
		return responseDto;
	}


	@Override
	public void responseCompleted(ResponseDto reponseDto) {
		setChanged();
		notifyObservers(reponseDto);
	}


}
