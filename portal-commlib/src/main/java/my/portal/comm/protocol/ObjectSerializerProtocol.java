package my.portal.comm.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import my.portal.comm.Request;
import my.portal.comm.ResponseListener;
import my.portal.common.RequestDto;
import my.portal.common.ResponseDto;

public class ObjectSerializerProtocol{

	/**
	 * @see tr.com.havelsan.uyap.system.comm.protocol.Protocol#receiveData(InputStream,
	 *      Request)
	 */
	public ResponseDto receiveData(InputStream inputStream, ResponseListener callbackHandler) {
		try(ObjectInputStream objectInputStream = getObjectInputStream(inputStream) ) {
			ResponseDto responseDto = (ResponseDto)objectInputStream.readObject();
			callbackHandler.responseCompleted(responseDto);
			return responseDto;
		} catch (IOException | ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe.getMessage(), cnfe);
		}
	}

	/**
	 * @see tr.com.havelsan.uyap.system.comm.protocol.Protocol#sendData(OutputStream,
	 *      Object[], CommHeader)
	 */
	public void sendData(OutputStream outputStream, RequestDto requestDto) {
		try(ObjectOutputStream objectOutputStream = getObjectOutputStream(outputStream)){
				objectOutputStream.writeObject(requestDto);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage(), ioe);
		}
	}

	ObjectInputStream getObjectInputStream(InputStream is) throws IOException {
		return new ObjectInputStream(is);
	}

	ObjectOutputStream getObjectOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
		objectOutputStream.useProtocolVersion(2);
		return objectOutputStream;
	}

}
