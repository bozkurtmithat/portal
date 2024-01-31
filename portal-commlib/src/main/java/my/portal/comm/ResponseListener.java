package my.portal.comm;

import java.util.EventListener;

import my.portal.common.ResponseDto;

public interface ResponseListener extends EventListener{
	
	public void responseCompleted(ResponseDto reponseDto);

}
