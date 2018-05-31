/**
 * Copyright(C) 2018 NguyenDuyPhong
 * DeleteBaiVietController.java, 1/5/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hotspot.logic.TblBaiVietLogic;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblBaiVietLogicImpl;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;

/**
 * Xử lý yêu cầu xóa bài viết
 * 
 * @author duyphong170195
 *
 */
public class DeleteBaiVietController extends HttpServlet {
	// Đối tượng logic thao tác với bảng tbl_bai_viet
	TblBaiVietLogic tblBaiVietLogic;
	// Đối tượng logic thao tác với bảng tbl_tai_khoan
	TblTaiKhoanLogic tblTaiKhoanLogic;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		tblBaiVietLogic = new TblBaiVietLogicImpl();
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			// Lấy id tài khoản từ session
			int idTaiKhoan = (int) session.getAttribute("idTaiKhoan");
			// Biến kiểm tra xem idTaiKhoan có tồn tại hay không ?
			boolean checkExistedTaiKhoan = tblTaiKhoanLogic.checkExistedIdTaiKhoan(idTaiKhoan);
			if (checkExistedTaiKhoan) { // Nếu tồn tại tài khoản
				int idBaiViet = Common.toInteger(req.getParameter("idBaiViet"));
				// Biến kiểm tra idBaiViet tồn tại hay không?
				boolean checkExistedBaiViet = tblBaiVietLogic.checkExistedBaiVietById(idBaiViet);
				// Biến kiểm tra delete có thành công hay không?
				boolean checkDelete = false;
				if (checkExistedBaiViet) { // Nếu bài viết tồn tại
					checkDelete = tblBaiVietLogic.deleteBaiViet(idBaiViet);
					String uri = "";
					if (checkDelete) {
						// Nếu xóa thành công
						uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_BAI_DA_VIET;
					} else {
						// Nếu xóa thất bại
						uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
								+ CommonConstant.MSG01_ERROR_DELETE_BAI_VIET;
					}
					resp.sendRedirect(uri);
				} else { // Nếu bài viết không tồn tại
					String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
							+ CommonConstant.MSG02_ERROR_BAI_VIET;
					resp.sendRedirect(uri);
				}
			} else {
				// Nếu không tồn tại tài khoản
				String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
						+ CommonConstant.MSG02_ERROR_TAI_KHOAN;
				resp.sendRedirect(uri);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Nếu lỗi thao tác với database
			// Link đường dẫn tới messageController
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}

	}

}
