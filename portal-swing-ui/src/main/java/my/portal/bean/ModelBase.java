package my.portal.bean;

import java.util.Observable;

import my.portal.comm.HttpClient;
import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;
import my.portal.common.controllers.CDI;
import my.portal.controllers.HttpClientController;

public class ModelBase extends Observable implements HttpClientController {

	HttpClient httpClient = CDI.getImpl(HttpClient.class);
	
	@Override
	public void sendAsynRequest(RequestDto requestDto) {
		httpClient.sendAsynRequest(requestDto, (reponseDto) -> {
			setChanged();
			notifyObservers(reponseDto);
		});
		
	}

	@Override
	public ResponseDto sendSyncRequest(RequestDto requestDto) {
		ResponseDto responseDto = httpClient.sendSyncRequest(requestDto);
//		setChanged();
//		notifyObservers(responseDto);
		return responseDto;
	}

}
