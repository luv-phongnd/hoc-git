/**
 * Copyright(C) 2018 NguyenDuyPhong
 * DanhSachTinhThanhController.java, 18/04/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Show ra danh sách tỉnh thành ở màn hình trang chủ
 * @author NguyenDuyPhong
 *
 */
import javax.servlet.http.HttpSession;

import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.TblTinhThanhLogic;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.logic.impl.TblTinhThanhLogicImpl;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;

/**
 * Nhận yêu cầu xem danh sách bài viết của tỉnh thành
 * 
 * @author duyphong170195
 *
 */
public class DanhSachTinhThanhController extends HttpServlet {
	// đối tượng thao tác với bảng tbl_tinh_thanh
	TblTinhThanhLogic tblTinhThanhLogic;
	// đối tượng thao tác với bảng tbl_tai_khoan
	TblTaiKhoanLogic tblTaiKhoanLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Phương thức được gọi 1 lần duy nhất khi class DanhSachTinhThanhController
	 * được gọi
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// Khởi tạo đối tượng
		tblTinhThanhLogic = new TblTinhThanhLogicImpl();
		//tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// Khởi tạo session
			HttpSession session = req.getSession();
			// Trang mặc định
			String sCurrentPage = CommonConstant.DEFAULT_PAGE;
			int iCurrentPage = Common.toInteger(sCurrentPage);
			// Tên tỉnh thành mặc định khi search
			String tenTinhThanh = CommonConstant.DEFAULT_VALUE_SEARCH;
			
			// Hành động khi click
			String action = req.getParameter(CommonConstant.ACTION);
			// Trường hợp click vào button search ở màn hình trang chủ.
			if (CommonConstant.ACTION_SEARCH.equals(action)) {
				tenTinhThanh = req.getParameter(CommonConstant.KEY_SEARCH_VALUE);
				System.out.println("Ten tinh thanh = " + tenTinhThanh);
			} else if (CommonConstant.ACTION_LIST_PAGING.equals(action)) {
				sCurrentPage = req.getParameter("page");
				iCurrentPage = Common.toInteger(sCurrentPage);
				//
				tenTinhThanh = (String) session.getAttribute(CommonConstant.KEY_SEARCH_VALUE);
			}
			// Số lượng tỉnh thành hiển thị trên một trang
			int totalTinhThanh = tblTinhThanhLogic.getTotalTinhThanh(tenTinhThanh);

			if (totalTinhThanh > 0) {
				int limitTinhThanh = Common.getLimitTinhThanh();
				// Vị trí tỉnh thành bắt đầu hiển thị
				int offSet = Common.getOffset(iCurrentPage, limitTinhThanh);
				// lấy tổng số tỉnh thành hiện có (Gợi ý: Nên để thêm điều
				// kiện)
				// lấy Tổng số trang
				int totalPage = Common.getTotalPage(totalTinhThanh, limitTinhThanh);
				// Lấy list paging theo trang hiện tại
				List<Integer> listPaging = Common.getListPaging(totalTinhThanh, limitTinhThanh, iCurrentPage);
				// đẩy lên session để khi thao tác chức năng listPaging cho
				// giá trị được search
				session.setAttribute(CommonConstant.KEY_SEARCH_VALUE, tenTinhThanh);
				String sessionUserName = (String) session.getAttribute("username");
				if (sessionUserName != null) {
					req.setAttribute("username", sessionUserName);
				}
				// đẩy dữ liệu lên màn hình trang chủ
				req.setAttribute(CommonConstant.KEY_SEARCH_VALUE, tenTinhThanh);
				req.setAttribute("totalPage", totalPage);
				req.setAttribute("currentPage", iCurrentPage);
				req.setAttribute("listPaging", listPaging);
				req.setAttribute("listTinhThanh",
						tblTinhThanhLogic.getListTinhThanh(offSet, limitTinhThanh, tenTinhThanh));
			} else {
				// sét message nếu
				String message = "Không tìm thấy tỉnh thành!!!";
				req.setAttribute("messageEmptyListCity", message);
			}
			//
			RequestDispatcher requestDispatcher = req.getServletContext()
					.getRequestDispatcher(CommonConstant.URL_TRANG_CHU);
			requestDispatcher.forward(req, resp);

		} catch (Exception e) {
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}
	}

}
