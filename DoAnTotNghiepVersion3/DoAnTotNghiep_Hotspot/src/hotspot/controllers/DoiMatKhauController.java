/**
 * Copyright(C) 2018 NguyenDuyPhong
 * DoiMatKhauController.java 26/04/2018 NguyenDuyPhong
 */
package hotspot.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hotspot.dao.TblTaiKhoanDao;
import hotspot.entities.TblTaiKhoan;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;
import hotspot.validate.Validates;

/**
 * Xử lý yêu cầu đổi mật khẩu
 * 
 * @author duyphong170195
 *
 */
public class DoiMatKhauController extends HttpServlet {
	Validates validates;
	TblTaiKhoanLogic tblTaiKhoanLogic;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Phương thực được gọi 1 lần duy nhất khi class được gọi
	 */
	@Override
	public void init() throws ServletException {
		validates = new Validates();
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
	}
	/**
	 * Phương thức nhận yêu cầu đổi mật khẩu từ trang doimatkhau.jsp
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//Khởi tạo session
			HttpSession session = req.getSession();
			//Lấy id từ session
			int idTaiKhoan = (int) session.getAttribute("idTaiKhoan");
			//Biến kiểm tra xem id có tồn tại trong bảng tbl_tai_khoan hay không.
			boolean checkExistedId = tblTaiKhoanLogic.checkExistedIdTaiKhoan(idTaiKhoan);
			//Nếu tồn tại tài khoản
			if (checkExistedId) {
				/*Lấy dữ liệu từ trang doimatkhau.jsp*/
				String matKhauHienTai = req.getParameter("matKhauHienTai");
				String matKhauMoi = req.getParameter("matKhauMoi");
				String matKhauXacNhan = req.getParameter("matKhauXacNhan");
				//Lấy đối tượng tblTaiKhoan theo id
				TblTaiKhoan tblTaiKhoan = tblTaiKhoanLogic.getTblTaiKhoanById(idTaiKhoan);
				//Biến kiểm tra update có thành công hay không.
				boolean checkUpdate = false;
				//Validate mật khẩu có hợp lệ hay không
				List<String> listError = validates.validateUpdateMatKhau(tblTaiKhoan, matKhauHienTai, matKhauMoi, matKhauXacNhan);
				//Nếu mật khẩu hợp lệ
				if(listError.size() == 0){
					checkUpdate = tblTaiKhoanLogic.updateMatKhau(tblTaiKhoan, matKhauMoi);
					if(checkUpdate){
						String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.MSG01_MAT_KHAU;
						resp.sendRedirect(uri);
					}else{
						String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.MSG01_ERROR_MAT_KHAU;
						resp.sendRedirect(uri);
					}
				}else{ // Nếu mật khẩu không hợp lệ
					//setattribute mảng lỗi
					req.setAttribute("listError", listError);
					RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/JSP/doimatkhau.jsp");
					requestDispatcher.forward(req, resp);
				}
				
			} else {// Nếu tài khoản không tồn tại
				String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.MSG02_ERROR_TAI_KHOAN;
				resp.sendRedirect(uri);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//Link đường dẫn tới messageController
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
	}

}
