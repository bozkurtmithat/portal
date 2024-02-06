package my.portal.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import my.portal.command.CommandDispatcher;
import my.portal.common.DtoBase;
import my.portal.common.PortalException;

/**
 * Servlet implementation class FrontController
 */
@Slf4j
@WebServlet("/exec")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response) {

		try {
			String commandName = request.getParameter("cmd");
			DtoBase requestDto = readRequestDto(request);
			DtoBase responseDto = CommandDispatcher.executeCommand(commandName, requestDto);
			sendResponseDto(response, responseDto);
		} catch (PortalException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void sendResponseDto(HttpServletResponse response, DtoBase responseDto) throws PortalException {
		try(ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream())){
			oos.writeObject(responseDto);
			oos.flush();
		}catch (IOException e) {
			throw new PortalException("ERR_00003", e);
		}
		
	}

	private DtoBase readRequestDto(HttpServletRequest request) throws PortalException {
		try(ObjectInputStream ois = new ObjectInputStream(request.getInputStream()) ){
			return (DtoBase)ois.readObject();
		}catch (IOException | ClassNotFoundException e) {
			throw new PortalException("ERR_00002", e);
		}
	}

}
