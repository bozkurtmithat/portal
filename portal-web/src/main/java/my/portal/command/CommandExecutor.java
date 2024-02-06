package my.portal.command;

import my.portal.common.DtoBase;
import my.portal.common.PortalException;

public interface CommandExecutor {

	public DtoBase execute(DtoBase param) throws PortalException;
	
}
