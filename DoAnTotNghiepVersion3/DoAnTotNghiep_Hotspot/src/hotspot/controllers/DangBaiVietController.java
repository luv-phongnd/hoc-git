/**
 * Copyright(C) 2018 NguyenDuyPhong
 * DangBaiVietController.java, 28/04/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;

import hotspot.entities.TblBaiViet;
import hotspot.entities.TblTinhThanh;
import hotspot.logic.TblBaiVietLogic;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.TblTinhThanhLogic;
import hotspot.logic.impl.TblBaiVietLogicImpl;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.logic.impl.TblTinhThanhLogicImpl;
import hotspot.model.BaiVietInfor;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;
import hotspot.validate.Validates;

/**
 * Xử lý yêu cầu đăng bài viết
 * 
 * @author duyphong170195
 *
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class DangBaiVietController extends HttpServlet {
	// Đối tượng thao tác với bảng tbl_tinh_thanh
	TblTinhThanhLogic tblTinhThanhLogic;
	// Đối tượng thao tác với bảng tbl_bai_viet
	TblBaiVietLogic tblBaiVietLogic;
	// Đối tượng thao tác với bang tbl_tai_khoan
	TblTaiKhoanLogic tblTaiKhoanLogic;
	Validates validates;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Phương thức được gọi 1 lần duy nhất khi class này được gọi
	 */
	@Override
	public void init() throws ServletException {
		tblTinhThanhLogic = new TblTinhThanhLogicImpl();
		tblBaiVietLogic = new TblBaiVietLogicImpl();
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
		validates = new Validates();
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
				setDataLogic(req, resp);
				TblBaiViet tblBaiViet = setDefaultValue(req, resp);
				if (tblBaiViet != null) {// Nếu bài viết tồn tại
					RequestDispatcher requestDispatcher = req.getServletContext()
							.getRequestDispatcher(CommonConstant.URL_BAI_VIET);
					requestDispatcher.forward(req, resp);
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
				TblBaiViet tblBaiViet = setDefaultValue(req, resp);
				tblBaiViet.setIdTaiKhoan(idTaiKhoan);

				List<String> listError = validates.validateBaiViet(tblBaiViet);
				if (listError.size() == 0) {
					//Đường dẫn lưu file ảnh
					String savePath = "F:\\filechuaservlet\\" + File.separator + tblBaiViet.getPathImage();
					//Ghi file ảnh xuống folder
					tblBaiViet.getPart().write(savePath + File.separator);
					// Lấy function type
					String functionType = req.getParameter("functionType");
					// biến kiểm tra insert có thành công hay không
					boolean checkInsertOrUpdate = false;
					String uri = "";
					//Nếu id bài viết không tồn tại ==> chức năng đăng bài viết
					if (tblBaiViet.getIdBaiViet() == 0) {
						if ("addBaiViet".equals(functionType)) {
							//Thực hiện insert bài viết
							checkInsertOrUpdate = tblBaiVietLogic.insertTblBaiViet(tblBaiViet);
							if (checkInsertOrUpdate) {//Nếu insert thành công
								//Đường link thông báo insert thành công
								uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_BAI_DA_VIET;
							} else {//Nếu insert thất bại
								//Đường link thông báo insert thất bại
								uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
										+ CommonConstant.MSG01_ERROR_INSERT_BAI_VIET;
							}
						}
					} else { // Nếu id bài viết tồn tại ==> chức năng update bài viết
						if ("updateBaiViet".equals(functionType)) {
							//Thực hiện update
							checkInsertOrUpdate = tblBaiVietLogic.updateTblBaiViet(tblBaiViet);
							if (checkInsertOrUpdate) { //Nếu update thành công
								//Đường link thông báo update thành công
								uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_DANH_SACH_BAI_DA_VIET;
							} else {// Nếu update tất bại
								//Đường link thông báo update thất bại
								uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
										+ CommonConstant.MSG01_ERROR_UPDATE_BAI_VIET;
							}
							//Hủy session id update bài viết
							session.removeAttribute("idBaiViet");
						}
					}
					//
					resp.sendRedirect(uri);
					
				} else {//Nếu bài viết bị lỗi check
					setDataLogic(req, resp);
					req.setAttribute("tblBaiViet", tblBaiViet);
					req.setAttribute("listError", listError);
					RequestDispatcher requestDispatcher = req.getServletContext()
							.getRequestDispatcher(CommonConstant.URL_BAI_VIET);
					requestDispatcher.forward(req, resp);
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

	public void setDataLogic(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException {
		req.setAttribute("listTinhThanh", tblTinhThanhLogic.getAllTinhThanhInfor());
	}

	public TblBaiViet setDefaultValue(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException, IOException, ServletException {
		// Lấy type screen
		String screenType = req.getParameter("screenType");
		TblBaiViet tblBaiViet = null;
		System.out.println("screenType = " + screenType);

		if ("trangchu".equals(screenType)) {
			tblBaiViet = new TblBaiViet();
			tblBaiViet.setMoTa("");
			tblBaiViet.setNoiDung("");

			tblBaiViet.setSoLuotThich(0);
			tblBaiViet.setHuyenXa("");
			tblBaiViet.setIdTinhThanh(0);
			tblBaiViet.setTenDiaDiem("");
			tblBaiViet.setTieuDe("");
			req.setAttribute("tblBaiViet", tblBaiViet);
			req.setAttribute("updateOrAdd", "add");

			return tblBaiViet;
		} else if ("addBaiViet".equals(screenType)) {
			tblBaiViet = new TblBaiViet();
			String moTa = req.getParameter("moTa");
			int idTinhThanh = Common.toInteger(req.getParameter("idTinhThanh"));
			String huyenXa = req.getParameter("huyenXa");
			String tenDiaDiem = req.getParameter("tenDiaDiem");
			Part part = req.getPart("hinhAnh");
			String noiDung = req.getParameter("noiDung");
			String tieuDe =req.getParameter("tieuDe");
			
			tblBaiViet.setMoTa(moTa);
			tblBaiViet.setIdTinhThanh(idTinhThanh);
			tblBaiViet.setHuyenXa(huyenXa);
			tblBaiViet.setTenDiaDiem(tenDiaDiem);
			tblBaiViet.setPart(part);
			tblBaiViet.setNoiDung(noiDung);
			tblBaiViet.setSoLuotThich(0);
			tblBaiViet.setNgayDangBai(Common.getDate(Common.getYearNow(), Common.getMonthNow(), Common.getDayNow()));
			tblBaiViet.setTieuDe(tieuDe);
			return tblBaiViet;
		} else if ("formUpdateBaiViet".equals(screenType)) {
			int idBaiViet = Common.toInteger(req.getParameter("idBaiViet"));
			HttpSession session = req.getSession();
			session.setAttribute("idBaiViet", idBaiViet);
			tblBaiViet = tblBaiVietLogic.getTblBaiVietById(idBaiViet);
			if (tblBaiViet != null) {
				req.setAttribute("tblBaiViet", tblBaiViet);
				req.setAttribute("updateOrAdd", "update");
			}
			return tblBaiViet;
		} else if ("updateBaiViet".equals(screenType)) {
			HttpSession session = req.getSession();
			int idBaiViet = (int) session.getAttribute("idBaiViet");

			 
			tblBaiViet = new TblBaiViet();
			String moTa = req.getParameter("moTa");
			int idTinhThanh = Common.toInteger(req.getParameter("idTinhThanh"));
			String huyenXa = req.getParameter("huyenXa");
			String tenDiaDiem = req.getParameter("tenDiaDiem");
			Part part = req.getPart("hinhAnh");
			String noiDung = req.getParameter("noiDung");
			String tieuDe =req.getParameter("tieuDe");
				
			tblBaiViet.setIdBaiViet(idBaiViet);
			tblBaiViet.setMoTa(moTa);
			tblBaiViet.setIdTinhThanh(idTinhThanh);
			tblBaiViet.setHuyenXa(huyenXa);
			tblBaiViet.setTenDiaDiem(tenDiaDiem);
			tblBaiViet.setPart(part);
			tblBaiViet.setNoiDung(noiDung);
			tblBaiViet.setSoLuotThich(0);
			tblBaiViet.setNgayDangBai(Common.getDate(Common.getYearNow(), Common.getMonthNow(), Common.getDayNow()));
			tblBaiViet.setTieuDe(tieuDe);
			return tblBaiViet;
		}

		return tblBaiViet;
	}

}
