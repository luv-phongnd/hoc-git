/**
 * Copyright(C) 2018 NguyenDuyPhong
 * XemDanhSachBaiVietCuaTinhThanhController.java, 23/04/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Xử lý và chuyển hướng đến trang list bài viết của tình thành khi người dùng click vào tỉnh thành ở trang chủ
 * @author duyphong170195
 *
 */
import javax.servlet.http.HttpSession;

import hotspot.dao.TblBaiVietDao;
import hotspot.dao.impl.TblBaiVietDaoImpl;
import hotspot.entities.TblBaiViet;
import hotspot.logic.TblBaiVietLogic;
import hotspot.logic.TblTinhThanhLogic;
import hotspot.logic.impl.TblBaiVietLogicImpl;
import hotspot.logic.impl.TblTinhThanhLogicImpl;
import hotspot.model.BaiVietInfor;
import hotspot.model.TinhThanhInfor;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;

public class XemDanhSachBaiVietCuaTinhThanhController extends HttpServlet {
	// Đối tượng thao thác với bảng tbl_bai_viet
	TblBaiVietLogic tblBaiVietLogic;
	// Đối tượng thao tác với bảng tbl_tinh_thanh
	TblTinhThanhLogic tblTinhThanhLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		tblBaiVietLogic = new TblBaiVietLogicImpl();
		tblTinhThanhLogic = new TblTinhThanhLogicImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//Khởi tạo session
			HttpSession session = req.getSession();
			int idTinhThanh = Common.toInteger(req.getParameter("idTinhThanh"));
			
			if(idTinhThanh == 0){
				idTinhThanh = (int)session.getAttribute("idTinhThanh");
			}else{
				session.setAttribute("idTinhThanh", idTinhThanh);
			}
			TinhThanhInfor tinhThanhInfor = tblTinhThanhLogic.getTinhThanhInforById(idTinhThanh);
			//Nếu tỉnh thành tồn tại
			if (tinhThanhInfor != null) {
				//Lấy yêu cầu địa điểm search từ form
				String searchDiaDiem = req.getParameter("tenDiaDiem");
				if (searchDiaDiem == null) {
					searchDiaDiem = "";
				}
				//Lấy tên tỉnh thành để hiển thị
				String tenTinhThanh = tinhThanhInfor.getTenTinhThanh();
				// số bài viết của tỉnh thành
				int tongSoBaiVietCuaTinhThanh = tblBaiVietLogic.getTotalBaiVietCuaTinhThanh(idTinhThanh, searchDiaDiem);
				if (tongSoBaiVietCuaTinhThanh > 0) {
					int idTaiKhoan = (int)session.getAttribute("idTaiKhoan");
					
					List<BaiVietInfor> listBaiViet = tblBaiVietLogic.getListBaiVietCuaTinhThanh(idTinhThanh,
							searchDiaDiem);
					List<Integer> danhSachIdBaiVietDaThichCuaTaiKhoan = tblBaiVietLogic.getAllLikeIdBaiVietById(idTaiKhoan);
					// Set attribute idTinhThanh để dùng form search
					req.setAttribute("idTinhThanh", idTinhThanh);
					req.setAttribute("listBaiViet", listBaiViet);
					req.setAttribute("listIdBaiVietCuaTaiKhoan", danhSachIdBaiVietDaThichCuaTaiKhoan);
				} else { // Nếu chưa có bài viết nào
					req.setAttribute("message", "Hiện tại vẫn chưa có bài viết nào.");
				}
				req.setAttribute("tenTinhThanh", tenTinhThanh);
				RequestDispatcher requestDispatcher = req.getServletContext()
						.getRequestDispatcher(CommonConstant.URL_TINH_THANH);
				requestDispatcher.forward(req, resp);
			} else { // Nếu tỉnh không còn tồn tại
				String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
						+ CommonConstant.MSG02_ERROR_TINH_THANH;
				resp.sendRedirect(uri);
			}
		} catch (Exception e) { // Nếu lỗi thao tác với database
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
	}
}
