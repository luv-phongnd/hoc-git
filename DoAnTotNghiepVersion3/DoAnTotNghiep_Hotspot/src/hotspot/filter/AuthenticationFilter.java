/**
 * Copyright(C) 2018 Luvina Software
 * AuthenticationFilter.java, 22/04/2018 NguyenDuyPhong
 */
package hotspot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hotspot.utils.CommonConstant;
/**
 *  xác thực người dùng đã đăng nhập chưa rồi chuyển hướng đến trang họ muốn
 * @author duyphong170195
 *
 */
public class AuthenticationFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		boolean loggedIn = (session != null) && (username != null);
		System.out.println("loggedIn = " + loggedIn);
		String uri = req.getRequestURI();
		System.out.println("uri = " + uri);
		//Nếu người dùng đã đăng nhập rồi link đến trang chủ hoặc trang đăng nhập thì chuyển hướng đến controller tỉnh thành
		if(loggedIn && ((uri.endsWith("/")) || uri.endsWith("dangnhap.jsp"))){ 
			resp.sendRedirect(req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_TINH_THANH);
		}
		//Nếu người dùng đã login thì cho đi đến đường dẫn họ muốn
		else if(loggedIn || uri.endsWith("login.do")||uri.endsWith(CommonConstant.URL_CONTROLLER_DANH_SACH_TINH_THANH) 
				||uri.endsWith("/imageTinhThanh.do")|| uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".map")
				|| uri.endsWith(".png") || uri.endsWith("dangnhap.jsp") || uri.endsWith("tinhthanh.jsp")
				|| uri.endsWith("dangky.jsp") || uri.endsWith("/dangKyTaiKhoan.do") ||uri.endsWith("/message.do")){
			chain.doFilter(req, resp);
		}else{
			resp.sendRedirect(req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_TINH_THANH);
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
