/**
 * Copyright(C) 2018 NguyenDuyPhong
 * DangKyTaiKhoanController.java 24/04/2018 NguyenDuyPhong
 */
package hotspot.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotspot.entities.TblTaiKhoan;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.utils.CommonConstant;
import hotspot.validate.Validates;

/**
 * Xử lý yêu cầu đăng ký tài khoản
 * 
 * @author duyphong170195
 *
 */
public class DangKyTaiKhoanController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Khởi tạo đối tượng logic thao tác với bảng tbl_tai_khoan
	TblTaiKhoanLogic tblTaiKhoanLogic;
	Validates validates;
	
	//Phương thức được gọi 1 lần duy nhất khi class này được gọi
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
		validates = new Validates();
	}
	
	/**
	 * xử lý yêu cầu đăng ký tài khoản
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			//lấy dữ liệu từ form đăng ký tài khoản
			TblTaiKhoan tblTaiKhoan = getValueForTblTaiKhoan(req);
			//Kiểm tra xem tài khoản có hợp lệ để đăng ký hay không ?
			List<String> listError = validates.validateDangKy(tblTaiKhoan);
			//Nếu không có lỗi
			if(listError.size() == 0){
				boolean checkDangKy = tblTaiKhoanLogic.insertTblTaiKhoan(tblTaiKhoan);
				if(checkDangKy){
					String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.MSG01_DANG_KY;
					resp.sendRedirect(uri);
				}else{
					String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.MSG01_ERROR_DANG_KY;
					resp.sendRedirect(uri);
				}		
			}else{
				//set tblTaiKhoan lên Attribute
				req.setAttribute("tblTaiKhoan", tblTaiKhoan);
				//set listError lên Attribute
				req.setAttribute("listError", listError);
				//
				RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher(CommonConstant.URL_DANG_KY);
				requestDispatcher.forward(req, resp);	
			}
		} catch (Exception e) { // Nếu lỗi thao tác với database
			//Link đường dẫn tới messageController
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
	}
	
	public TblTaiKhoan getValueForTblTaiKhoan(HttpServletRequest req){
		String tenTaiKhoan = req.getParameter("tenTaiKhoan");
		String matKhau = req.getParameter("matKhau");
		String matKhauXacNhan = req.getParameter("matKhauXacNhan");
		String email = req.getParameter("email");
		String soDienThoai = req.getParameter("soDienThoai");
		String biDanh = req.getParameter("biDanh");
		String salt = String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND));
		TblTaiKhoan tblTaiKhoan = new TblTaiKhoan();
		tblTaiKhoan.setTenTaiKhoan(tenTaiKhoan);
		tblTaiKhoan.setMatKhau(matKhau);
		tblTaiKhoan.setMatKhauXacNhan(matKhauXacNhan);
		tblTaiKhoan.setEmail(email);
		tblTaiKhoan.setSoDienThoai(soDienThoai);
		tblTaiKhoan.setBiDanh(biDanh);
		tblTaiKhoan.setSalt(salt);
		
		return tblTaiKhoan;
	}
}
