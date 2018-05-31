/**
 * Copyright(C) 2018 NguyenDuyPhong
 * DanhSachBaiDaVietController.java 27/04/2018 NguyenDuyPhong
 */
package hotspot.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hotspot.logic.TblBaiVietLogic;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblBaiVietLogicImpl;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.model.BaiVietInfor;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;

/**
 * Xử lý yêu cầu xem bài đã viết của user
 * 
 * @author duyphong170195
 *
 */
public class DanhSachBaiDaVietController extends HttpServlet {
	TblBaiVietLogic tblBaiVietLogic;
	TblTaiKhoanLogic tblTaiKhoanLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Phương thức được gọi một lần duy nhất khi class này được gọi
	 */
	@Override
	public void init() throws ServletException {
		tblBaiVietLogic = new TblBaiVietLogicImpl();
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo session
			HttpSession session = req.getSession();
			int idTaiKhoan = (int) session.getAttribute("idTaiKhoan");	
			// Kiểm tra tài khoản còn tồn tại không ?
			boolean checkExistedTaiKhoan = tblTaiKhoanLogic.checkExistedIdTaiKhoan(idTaiKhoan);
			//Nếu tồn tại tài khoản
			if(checkExistedTaiKhoan){
				// Lấy tên địa điểm từ form search ở baidaviet.jsp
				String tenDiaDiem = req.getParameter("tenDiaDiem");
				if (tenDiaDiem == null) {
					tenDiaDiem = "";
				}
				//Lấy tổng số bài viết
				int toTalBaiVietCuaTaiKhoan = tblBaiVietLogic.getTotalBaiVietCuaTaiKhoan(idTaiKhoan, tenDiaDiem);
				//Nếu tổng số bài viết > 0 ( làm như vậy thì chương trình sẽ nhanh hơn)
				if(toTalBaiVietCuaTaiKhoan >0 ){
					List<BaiVietInfor> listBaiViet = tblBaiVietLogic.getListBaiVietCuaTaiKhoan(idTaiKhoan, tenDiaDiem);					
					req.setAttribute("listBaiViet", listBaiViet);
				}else{
					req.setAttribute("message", "Hiện tại vẫn chưa có bài viết nào.");
				}
				RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher(CommonConstant.URL_BAI_DA_VIET);
				requestDispatcher.forward(req, resp);
			}else{//Nếu không tồn tại tài khoản
				String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.MSG02_ERROR_TAI_KHOAN;
				resp.sendRedirect(uri);
			}
		} catch (Exception e) {
			//Link đường dẫn tới messageController
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
	}

}
