package my.portal.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PortalException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String errorCode;
	
	public PortalException(String errorCode, Exception e) {
		super(e);
		this.errorCode = errorCode;
	}
	
}
