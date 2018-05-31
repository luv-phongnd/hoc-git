/**
 * Copyright(C) 2018 NguyenDuyPhong
 * ImageBaiVietController.java, 23/04/2018 NguyenDuyPhong 
 */
package hotspot.controllers;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotspot.logic.TblBaiVietLogic;
import hotspot.logic.impl.TblBaiVietLogicImpl;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;

/**
 * Nhận yêu cầu lấy hình ảnh của bài viết 
 * @author duyphong170195
 *
 */
public class ImageBaiVietController extends HttpServlet {
	//
	TblBaiVietLogic tblBaiVietLogic;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		tblBaiVietLogic = new TblBaiVietLogicImpl();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			int idBaiViet = Common.toInteger(req.getParameter("idBaiViet"));
			byte[] binaryStream = tblBaiVietLogic.getImageBaiVietById(idBaiViet);
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
