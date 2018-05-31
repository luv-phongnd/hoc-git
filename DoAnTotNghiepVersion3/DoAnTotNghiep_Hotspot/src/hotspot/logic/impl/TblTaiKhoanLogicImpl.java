/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblTaiKhoanLogicImpl.java 24/04/2018 NguyenDuyPhong
 */
package hotspot.logic.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import hotspot.dao.TblTaiKhoanDao;
import hotspot.dao.impl.TblTaiKhoanDaoImpl;
import hotspot.entities.TblTaiKhoan;
import hotspot.logic.TblTaiKhoanLogic;

/**
 * Thực thi giao diện xử lý logic liên quan đến bảng tbl_tai_khoan
 * 
 * @author duyphong170195
 *
 */
public class TblTaiKhoanLogicImpl implements TblTaiKhoanLogic {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hotspot.logic.TblTaiKhoanLogic#checkExistedTenTaiKhoan(java.lang.String)
	 */
	@Override
	public boolean checkExistedTenTaiKhoan(String tenTaiKhoan) throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng thao tác với bảng tbl_tai_khoan
		TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
		// Lấy đối tượng TblTaiKhoan theo tenTaiKhoan
		TblTaiKhoan tblTaiKhoan = tblTaiKhoanDao.getTblTaiKhoanbyUserName(tenTaiKhoan);
		// Nếu có bản ghi
		if (tblTaiKhoan != null) {
			return true;
		}
		// Nếu không có bản ghi
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.logic.TblTaiKhoanLogic#checkExistedEmail(java.lang.String)
	 */
	@Override
	public boolean checkExistedEmail(String email) throws ClassNotFoundException, SQLException {
		// Khởi tạo đối tượng thao tác với bảng tbl_tai_khoan
		TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
		// Lấy đối tượng TblTaiKhoan theo tenTaiKhoan
		TblTaiKhoan tblTaiKhoan = tblTaiKhoanDao.getTblTaiKhoanByEmail(email);
		// Nếu có bản ghi
		if (tblTaiKhoan != null) {
			return true;
		}
		// Nếu không có bản ghi
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.logic.TblTaiKhoanLogic#insertTblTaiKhoan(hotspot.entities.
	 * TblTaiKhoan)
	 */
	@Override
	public boolean insertTblTaiKhoan(TblTaiKhoan tblTaiKhoan)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		// Khởi tạo đối tượng thao tác với bảng tbl_tai_khoan
		TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
		return tblTaiKhoanDao.insertTblTaiKhoan(tblTaiKhoan);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.logic.TblTaiKhoanLogic#updateMatKhau(hotspot.entities.
	 * TblTaiKhoan, java.lang.String)
	 */
	@Override
	public boolean updateMatKhau(TblTaiKhoan tblTaiKhoan, String matKhauMoi)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		// Khởi tạo đối tượng thao tác với bảng tbl_tai_khoan
		TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
		return tblTaiKhoanDao.updateMatKhau(tblTaiKhoan, matKhauMoi);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.logic.TblTaiKhoanLogic#getTblTaiKhoanById(int)
	 */
	@Override
	public TblTaiKhoan getTblTaiKhoanById(int idTaiKhoan) throws SQLException, ClassNotFoundException {
		// Khởi tạo đối tượng thao tác với bảng tbl_tai_khoan
		TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
		return tblTaiKhoanDao.getTblTaiKhoanById(idTaiKhoan);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblTaiKhoanLogic#checkExistedIdTaiKhoan(int)
	 */
	@Override
	public boolean checkExistedIdTaiKhoan(int idTaiKhoan) throws SQLException, ClassNotFoundException {
		// Khởi tạo đối tượng thao tác với bảng tbl_tai_khoan
		TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
		TblTaiKhoan tblTaiKhoan = tblTaiKhoanDao.getTblTaiKhoanById(idTaiKhoan);
		//Nếu đối tượng tồn tại
		if(tblTaiKhoan != null){
			return true;
		}
		//Nếu đối tượng không tồn tại
		return false;
	}

}
