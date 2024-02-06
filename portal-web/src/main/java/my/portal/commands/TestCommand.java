package my.portal.commands;

import my.portal.command.Command;
import my.portal.command.CommandExecutor;
import my.portal.common.DtoBase;
import my.portal.common.DtoStringWrapper;
import my.portal.common.PortalException;

@Command("test")
public class TestCommand implements CommandExecutor{

	@Override
	public DtoBase execute(DtoBase param) throws PortalException {
		
		return new DtoStringWrapper("Hello " +((DtoStringWrapper)param).getValue());
	}
	
}
