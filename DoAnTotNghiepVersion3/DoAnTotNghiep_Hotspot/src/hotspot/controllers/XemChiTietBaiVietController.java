/**
 * Copyright(C) 2018 NguyenDuyPhong
 * XemChiTietBaiVietController.java, 2/5/2018 NguyenDuyPhong 
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

import hotspot.entities.TblBaiViet;
import hotspot.logic.TblBaiVietLogic;
import hotspot.logic.TblNhanXetBaiVietLogic;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblBaiVietLogicImpl;
import hotspot.logic.impl.TblNhanXetBaiVietLogicImpl;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.model.NhanXetInfor;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Xử lý yêu cầu xem chi tiết bài viết
 * 
 * @author duyphong170195
 *
 */
public class XemChiTietBaiVietController extends HttpServlet {
	TblBaiVietLogic tblBaiVietLogic;
	TblTaiKhoanLogic tblTaiKhoanLogic;
	TblNhanXetBaiVietLogic tblNhanXetBaiVietLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		tblBaiVietLogic = new TblBaiVietLogicImpl();
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
		tblNhanXetBaiVietLogic = new TblNhanXetBaiVietLogicImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tại session
			HttpSession session = req.getSession();
			// Lấy idTaiKhoan từ session
			int idTaiKhoan = (int) session.getAttribute("idTaiKhoan");
			// Biến kiểm tra xem idTaiKhoan có tồn tại hay không ?
			boolean checkExistedTaiKhoan = tblTaiKhoanLogic.checkExistedIdTaiKhoan(idTaiKhoan);
			if (checkExistedTaiKhoan) {
				// Lấy id bài viết
				int idBaiViet = Common.toInteger(req.getParameter("idBaiViet"));
				
				boolean checkExistedBaiViet = tblBaiVietLogic.checkExistedBaiVietById(idBaiViet);
				if (checkExistedBaiViet) {
					List<NhanXetInfor> listNhanXet = tblNhanXetBaiVietLogic.getAllComment(idBaiViet);
					TblBaiViet tblBaiViet = tblBaiVietLogic.getTblBaiVietById(idBaiViet);
					req.setAttribute("tblBaiViet", tblBaiViet);
					req.setAttribute("listNhanXet", listNhanXet);
					req.setAttribute("idBaiViet", idBaiViet);
					
					RequestDispatcher requestDispatcher = req.getServletContext()
							.getRequestDispatcher(CommonConstant.URL_XEM_CHI_TIET_BAI_VIET);
					requestDispatcher.forward(req, resp);

				} else {
					// Nếu bài viết không tồn tại
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tại session
			HttpSession session = req.getSession();
			// Lấy idTaiKhoan từ session
			int idTaiKhoan = (int) session.getAttribute("idTaiKhoan");
			// Biến kiểm tra xem idTaiKhoan có tồn tại hay không ?
			boolean checkExistedTaiKhoan = tblTaiKhoanLogic.checkExistedIdTaiKhoan(idTaiKhoan);
			if (checkExistedTaiKhoan) {
				// Lấy id bài viết
				int idBaiViet = Common.toInteger(req.getParameter("idBaiViet"));
				boolean checkExistedBaiViet = tblBaiVietLogic.checkExistedBaiVietById(idBaiViet);
				System.out.println("id Bai Viet = " + idBaiViet);
				if (checkExistedBaiViet) {
					String nhanXet = req.getParameter("nhanXet");
					boolean checkComment = tblNhanXetBaiVietLogic.insertComment(idBaiViet, idTaiKhoan, nhanXet);
										
					String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_XEM_CHI_TIET_BAI_VIET +"?idBaiViet=" + idBaiViet;
					resp.sendRedirect(uri);

				} else {
					// Nếu bài viết không tồn tại
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
