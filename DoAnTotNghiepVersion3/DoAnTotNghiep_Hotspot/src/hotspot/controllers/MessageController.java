/**
 * Copyright(C) 2018 NguyenDuyPhong
 * MessageController.java, 23/04/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotspot.properties.MessageSuccessProperties;
import hotspot.utils.CommonConstant;
/**
 * Nhận yêu cầu thông báo khi thực hiện một số chức năng thành công
 * @author duyphong170195
 *
 */
public class MessageController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// nhận thông báo từ một số controller
		String keyMessage = req.getParameter("keyMessage");
		String message = MessageSuccessProperties.getData(keyMessage);
		req.setAttribute("message", message);
		RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher(CommonConstant.URL_THONG_BAO);
		
		requestDispatcher.forward(req, resp);
		
	}
	
}
