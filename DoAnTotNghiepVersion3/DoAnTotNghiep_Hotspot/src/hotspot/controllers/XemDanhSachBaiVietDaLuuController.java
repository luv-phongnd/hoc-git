/**
 * Copyright(C) 2018 NguyenDuyPhong
 * XemDanhSachBaiVietDaLuuController.java, 27/04/2018 NguyenDuyPhong 
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

import hotspot.logic.TblLuuBaiVietLogic;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblLuuBaiVietLogicImpl;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.model.BaiVietInfor;
import hotspot.utils.CommonConstant;
/**
 * Xử lý yêu cầu xem danh sách bài viết đã lưu
 * @author duyphong170195
 *
 */
public class XemDanhSachBaiVietDaLuuController extends HttpServlet{
	//Đối tượng thao tác với bảng tbl_luu_bai_viet
	TblLuuBaiVietLogic tblLuuBaiVietLogic;
	//Đối tượng thao tác với bảng tbl_tai_khoan
	TblTaiKhoanLogic tblTaiKhoanLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Phương thức này được gọi 1 lần duy nhất khi class này được gọi
	@Override
	public void init() throws ServletException {
		//Khơi tạo đối tượng
		tblLuuBaiVietLogic = new TblLuuBaiVietLogicImpl();
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			//Khởi tạo session.
			HttpSession session = req.getSession();
			//Lấy id tài khoản từ session
			int idTaiKhoan = (int)session.getAttribute("idTaiKhoan");
			//biến kiểm tra tài khoản có tồn tại hay không ?
			boolean checkExistedIdTaiKhoan = tblTaiKhoanLogic.checkExistedIdTaiKhoan(idTaiKhoan);
			//Nếu tài khoản tồn tại
			if(checkExistedIdTaiKhoan){
				String tenDiaDiem = req.getParameter("tenDiaDiem");
				if(tenDiaDiem == null){
					tenDiaDiem = "";
				}
				//Lấy tổng số bài viết theo tên địa điểm
				int total = tblLuuBaiVietLogic.getTotalsBaiVietDaLuuCuaTaiKhoan(idTaiKhoan, tenDiaDiem);
				//Nếu tổng số bài viết > 0
				if(total > 0){
					List<BaiVietInfor> listBaiViet = tblLuuBaiVietLogic.getListBaiVietDaLuuCuaTaiKhoan(idTaiKhoan, tenDiaDiem);
					req.setAttribute("listBaiViet", listBaiViet);
				}else{ // Nếu < 0
					req.setAttribute("message", "Hiện tại bạn chưa lưu bài viết nào cả");
				}
				RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher(CommonConstant.URL_BAI_VIET_DA_LUU);
				requestDispatcher.forward(req, resp);
			}else{//Nếu tài khoản không tồn tại
				String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="+CommonConstant.MSG02_ERROR_TAI_KHOAN;
				resp.sendRedirect(uri);
			}
		}catch(Exception e){//Nếu lỗi thao tác với database
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
		
		
	}

}
