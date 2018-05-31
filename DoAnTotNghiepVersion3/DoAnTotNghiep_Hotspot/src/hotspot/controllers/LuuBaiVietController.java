/**
 * Copyright(C) 2018 NguyenDuyPhong
 * LuuBaiVietController.java, 1/5/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hotspot.logic.TblBaiVietLogic;
import hotspot.logic.TblLuuBaiVietLogic;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblBaiVietLogicImpl;
import hotspot.logic.impl.TblLuuBaiVietLogicImpl;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;

/**
 * Xử lý yêu cầu luu bài viết
 * 
 * @author duyphong170195
 *
 */
public class LuuBaiVietController extends HttpServlet {
	// Đối tượng xử lý logic thao tác với bảng tbl_luu_bai_viet
	TblLuuBaiVietLogic tblLuuBaiVietLogic;
	TblTaiKhoanLogic tblTaiKhoanLogic;
	TblBaiVietLogic tblBaiVietLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		tblLuuBaiVietLogic = new TblLuuBaiVietLogicImpl();
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
		tblBaiVietLogic = new TblBaiVietLogicImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo session
			HttpSession session = req.getSession();
			// Lấy id tài khoản từ session
			int idTaiKhoan = (int) session.getAttribute("idTaiKhoan");
			// Biến kiểm tra xem idTaiKhoan có tồn tại hay không
			boolean checkExistedIdTaiKhoan = tblTaiKhoanLogic.checkExistedIdTaiKhoan(idTaiKhoan);
			if (checkExistedIdTaiKhoan) { // Nếu tồn tại tài khoản
				// Lấy id bài viết
				int idBaiViet = Common.toInteger(req.getParameter("idBaiViet"));
				// Biến kiểm tra xem bài viết này đã được lưu trong tài khoản
				// hay chưa ?
				boolean checkExistedIdTaiKhoanIdBaiViet = tblLuuBaiVietLogic.checkExistedIdTaiKhoanIdBaiViet(idTaiKhoan,
						idBaiViet);
				if (!checkExistedIdTaiKhoanIdBaiViet) {
					// biến kiểm tra xem bài viết còn tồn tại hay không
					boolean checkExistedBaiViet = tblBaiVietLogic.checkExistedBaiVietById(idBaiViet);
					if (checkExistedBaiViet) { // Nếu bài viết tồn tại
						boolean checkInsertLuuBaiViet = tblLuuBaiVietLogic.insetTblLuuBaiViet(idTaiKhoan, idBaiViet);
						String uri = "";
						if (checkInsertLuuBaiViet) {
							// Nếu thêm thành công
							uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_BAI_VIET_TINH_THANH;
						} else {
							// Nếu thêm không thành công
							uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
									+ CommonConstant.MSG01_ERROR_INSERT_LUU_BAI_VIET;
						}
						resp.sendRedirect(uri);
					} else {
						// Nếu bài viết không tồn tại
						String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
								+ CommonConstant.MSG02_ERROR_BAI_VIET;
						resp.sendRedirect(uri);
					}
				} else {
					// Nếu tài khoản đã lưu bài viết trong bảng tbl_luu_bai_viet
					String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
							+ CommonConstant.MSG02_LUU_BAI_VIET;
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
