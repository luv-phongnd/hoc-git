/**
 * Copyright(C) 2018 NguyenDuyPhong
 * ImageTinhThanhController.java, 18/04/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotspot.dao.impl.TblTinhThanhDaoImpl;
import hotspot.entities.TblTinhThanh;
import hotspot.logic.TblTinhThanhLogic;
import hotspot.logic.impl.TblTinhThanhLogicImpl;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;

/**
 * Đẩy ảnh của tỉnh thành lên trang chủ
 * 
 * @author duyphong170195
 *
 */
public class ImageTinhThanhController extends HttpServlet {
	// Đối tượng thao tác với bảng tbl_tinh_thanh
	TblTinhThanhLogic tblTinhThanhLogic;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		// Khởi tạo đối tượng thao tác với tbl_tinh_thanh
		tblTinhThanhLogic = new TblTinhThanhLogicImpl();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			int idTinhThanh = Common.toInteger(req.getParameter("idTinhThanh"));
			byte[] binaryStream = tblTinhThanhLogic.getImageTinhThanhById(idTinhThanh);
			resp.setContentType("image/jpg");
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
			bos.write(binaryStream);
			bos.flush();
			bos.close();
		} catch (Exception e) {
			String uri = req.getContextPath() + CommonConstant.URL_CONTROLLER_THONG_BAO + "?keyMessage="
					+ CommonConstant.ERROR_DATABASE;
			resp.sendRedirect(uri);
		}

	}

}
