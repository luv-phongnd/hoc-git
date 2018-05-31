/**
 * Copyright(C) 2018 NguyenDuyPhong
 * DeleteLuuBaiVietController.java, 1/5/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hotspot.logic.TblLuuBaiVietLogic;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblLuuBaiVietLogicImpl;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;

/**
 * Xử lý yêu cầu xóa lưu bài viết
 * @author duyphong170195
 *
 */
public class DeleteLuuBaiVietController extends HttpServlet{
	TblLuuBaiVietLogic tblLuuBaiVietLogic;
	TblTaiKhoanLogic tblTaiKhoanLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		tblLuuBaiVietLogic = new TblLuuBaiVietLogicImpl();
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			//Khởi tạo session
			HttpSession session = req.getSession();
			//Lấy id tài khoản từ session
			int idTaiKhoan = (int)session.getAttribute("idTaiKhoan");
			// Biến kiểm tra xem idTaiKhoan có tồn tại hay không ?
			boolean checkExistedTaiKhoan = tblTaiKhoanLogic.checkExistedIdTaiKhoan(idTaiKhoan);
			if(checkExistedTaiKhoan){
				int idBaiViet = Common.toInteger(req.getParameter("idBaiViet"));
				// Biến kiểm tra idBaiViet tồn tại hay không?
				boolean checkExistedLuuBaiViet = tblLuuBaiVietLogic.checkExistedIdTaiKhoanIdBaiViet(idTaiKhoan, idBaiViet);
				// Biến kiểm tra delete có thành công hay không?
				boolean checkDeleteLuuBaiViet = false;
				//Nếu bản luu bài viết tồn tại
				if(checkExistedLuuBaiViet){
					//Thực thi xóa bài viết
					checkDeleteLuuBaiViet = tblLuuBaiVietLogic.deleteBaiVietDaLuuCuaTaiKhoan(idTaiKhoan, idBaiViet);
					//Đường dẫn
					String uri = "";
					if(checkDeleteLuuBaiViet){
						//Nếu xóa thành công
						uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_BAI_VIET_DA_LUU;
					}else{
						// Nếu xóa thất bại
						uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
								+ CommonConstant.MSG01_ERROR_DELETE_BAI_VIET;
					}
					resp.sendRedirect(uri);
				}else{
					// Nếu bài viết không tồn tại
					String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
							+ CommonConstant.MSG02_ERROR_BAI_VIET;
					resp.sendRedirect(uri);
				}
			}else{
				// Nếu không tồn tại tài khoản
				String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
						+ CommonConstant.MSG02_ERROR_TAI_KHOAN;
				resp.sendRedirect(uri);
			}
		}catch(Exception e){
			e.printStackTrace();
			// Nếu lỗi thao tác với database
			// Link đường dẫn tới messageController
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
	}
	
}
