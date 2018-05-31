/**
 * Copyright(C) 2018 NguyenDuyPhong
 * LogoutController.java, 18/04/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import hotspot.utils.CommonConstant;

/**
 * nhận yêu cầu logout khỏi tài khoản
 * @author duyphong170195
 *
 */
public class LogoutController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				System.out.println("Cookie = " + cookie.getValue());
				cookie.setMaxAge(0);
			}
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_TINH_THANH;
			resp.sendRedirect(uri);
		} catch (Exception e) {
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
	}
}
