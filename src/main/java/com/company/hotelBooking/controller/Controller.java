package com.company.hotelBooking.controller;

import com.company.hotelBooking.controller.command.api.CommandName;
import com.company.hotelBooking.controller.command.api.ICommand;
import com.company.hotelBooking.controller.command.factory.CommandFactory;
import com.company.hotelBooking.dao.connection.DataSource;
import com.company.hotelBooking.util.ConfigurationManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

/**
 * Class for processing HttpServletRequest "/controller"
 * 
 * @author Yulia
 *
 */
@WebServlet("/controller")
@Log4j2
public class Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String commandName = req.getParameter("command");
		
		if (!CommandName.contains(commandName.toUpperCase())) {
			log.error("Incorrect address entered");
			commandName = "error";
		}
		ICommand command = CommandFactory.getINSTANCE().getCommand(commandName);
		try {
			String page = command.execute(req);
			req.getRequestDispatcher(page).forward(req, resp);
		} catch (IOException e) {
			req.getRequestDispatcher(ConfigurationManager.getInstance().getString(ConfigurationManager.PAGE_ERROR)).forward(req, resp);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void destroy() {
		DataSource.getINSTANCE().close();
	}
}