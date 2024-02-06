package my.portal.command;

import java.util.HashMap;
import java.util.Map;

import my.portal.common.DtoBase;
import my.portal.common.PortalException;

public class CommandDispatcher {

	private static Map<String, CommandExecutor> commands = new HashMap<>();
	
	static {
		commands = CommandLoader.loadCommands();
	}
	
	public static DtoBase executeCommand(String commandName, DtoBase param) throws PortalException{
		CommandExecutor commandExecutor = commands.get(commandName);
		if(commandExecutor == null) {
			throw new PortalException("ERR_00001");
		}
		return commandExecutor.execute(param);
	}
}
