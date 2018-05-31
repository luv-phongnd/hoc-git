/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblTaiKhoanDao.java 18/04/2018 NguyenDuyPhong
 */
package hotspot.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import hotspot.entities.TblTaiKhoan;

/**
 * Định nghĩa các phương thức thao tác với bảng tbl_tai_khoan trong database
 * @author NguyenDuyPhong
 *
 */
public interface TblTaiKhoanDao {
	/**
	 *  Lấy đối tượng TblTaiKhoan theo userName
	 * @param userName : tên tài khoản
	 * @return đối tượng TblTaiKhoan (nếu có)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TblTaiKhoan getTblTaiKhoanbyUserName(String userName) throws SQLException , ClassNotFoundException;
	
	/**
	 * Lấy tên bí danh theo id tài khoản
	 * @param idTaiKhoan
	 * @return : tên bí danh
	 */
	public String getBiDanhByIdTaiKhoan(int idTaiKhoan) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy đối tượng TblTaiKhoan theo email trong tbl_tai_khoan
	 * @param email : địa chỉ email 
	 * @return : đối tượng TblTaiKhoan(nếu có)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TblTaiKhoan getTblTaiKhoanByEmail(String email) throws SQLException, ClassNotFoundException;
	/**
	 * Lấy đối tượng tblTaiKhoan theo id
	 * @param idTaiKhoan : id của tài khoản
	 * @return : đối tượng tblTaiKhoan
	 */
	public TblTaiKhoan getTblTaiKhoanById(int idTaiKhoan) throws SQLException, ClassNotFoundException;
	/**
	 * Insert đối tượng tblTaiKhoan vào bảng tbl_tai_khoan trong database
	 * @param tblTaiKhoan : đối tượng được thêm
	 * @return true: nêu thêm thành công
	 * false: nếu thêm thất bại
	 */
	public boolean insertTblTaiKhoan(TblTaiKhoan tblTaiKhoan) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException;
	/**
	 * Update mật khẩu theo id_tai_khoan trong bảng tbl_tai_khoan
	 * @param tblTaiKhoan : đối tượng tài khoản được thay đổi mật khẩu
	 * @param matKhauMoi : Mật khẩu mới được update cho idTaiKhoan
	 * @return  true: nếu update thành công
	 * false : Nếu update thất bại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	public boolean updateMatKhau(TblTaiKhoan tblTaiKhoan, String matKhauMoi) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException;
}
