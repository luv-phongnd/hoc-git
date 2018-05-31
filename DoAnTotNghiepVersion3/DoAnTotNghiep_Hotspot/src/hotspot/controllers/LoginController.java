/**
 * Copyright(C) 2018 NguyenDuyPhong
 * LoginController.java, 18/04/2018 NguyenDuyPhong 
 */
package hotspot.controllers;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hotspot.dao.TblTaiKhoanDao;
import hotspot.dao.impl.TblTaiKhoanDaoImpl;
import hotspot.entities.TblTaiKhoan;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;
import hotspot.validate.Validates;

/**
 * 
 * @author NguyenDuyPhong
 *
 */
public class LoginController extends HttpServlet {
	TblTaiKhoanLogic tblTaiKhoanLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = req.getSession();
			String user = (String) session.getAttribute("username");

			if (user == null) { // Trường hợp chưa đăng nhập
				// Lấy username và password từ trang đăng nhập
				String userName = req.getParameter("username");
				String password = req.getParameter("password");
				// Kiểm tra xem username và password có hợp lệ không
				List<String> listErrors = Validates.validateDangNhap(userName, password);
				// Nếu hợp lệ
				if (listErrors.size() == 0) {
					TblTaiKhoan tblTaiKhoan = tblTaiKhoanDao.getTblTaiKhoanbyUserName(userName);
					// Lưu dữ liệu vào session
					session.setAttribute("username", userName);
					session.setAttribute("idTaiKhoan", tblTaiKhoan.getIdTaiKhoan());
					String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_TINH_THANH;
					resp.sendRedirect(uri);
				} else {// Nếu không hợp lệ
						// Gửi lỗi lên màn hình đăng nhập
					req.setAttribute("listError", listErrors);
					req.setAttribute("userName", userName);
					// forward về trang dangnhap
					RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher(CommonConstant.URL_DANG_NHAP);
					requestDispatcher.forward(req, resp);
				}
			} else { // Trường hợp đã đăng nhập rồi đăng nhập thêm một tài khoản khác
				String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_TINH_THANH;
				resp.sendRedirect(uri);
			}
		} catch (Exception e) { // Nếu có lỗi	
			e.printStackTrace();
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}

	}

	/**
	 * Nếu link vào đường dẫn login.do
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("Vào đây");
			// Khởi tạo session
			HttpSession session = req.getSession();
			// Lấy username trên Session
			String username = (String) session.getAttribute("username");

			if (username != null) { // Nếu đã đăng nhập rồi
				// Chuyển hướng đến trang chủ khi đã đăng nhập
				resp.sendRedirect(req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_TINH_THANH);
			} else {// Nếu chưa đăng nhập
					// Chuyển hướng đến trang login
				resp.sendRedirect(req.getContextPath() + CommonConstant.URL_DANG_NHAP);
			}
		} catch (Exception e) {
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
	}
}
