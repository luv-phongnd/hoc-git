/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblTaiKhoanLogic.java 24/04/2018 NguyenDuyPhong
 */
package hotspot.logic;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import hotspot.entities.TblTaiKhoan;

/**
 * Giao diện xử lý logic liên quan đến thao tác với bảng tbl_tai_khoan
 * @author NguyenDuyPhong
 */
public interface TblTaiKhoanLogic {
	/**
	 * kiểm tra xem tên tài khoản có tồn tại trong bảng tbl_tai_khoan hay không ?
	 * @param tenTaiKhoan : giá trị được kiểm tra
	 * @return 
	 * true:Nếu tồn tại trong bảng tbl_tai_khoan
	 * false:Nếu không tồn tại trong bảng tbl_tai_khoan
	 */
	public boolean checkExistedTenTaiKhoan(String tenTaiKhoan) throws ClassNotFoundException, SQLException ;
	
	/**
	 * kiểm tra xem Email có tồn tại trong bảng tbl_tai_khoan hay không ?
	 * @param email : giá trị được kiểm tra
	 * @return 
	 * true:Nếu tồn tại trong bảng tbl_tai_khoan
	 * false:Nếu không tồn tại trong bảng tbl_tai_khoan
	 */
	public boolean checkExistedEmail(String email) throws ClassNotFoundException, SQLException ;
	
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
	
	/**
	 * Lấy đối tượng tblTaiKhoan theo id
	 * @param idTaiKhoan : id của tài khoản
	 * @return : đối tượng tblTaiKhoan
	 */
	public TblTaiKhoan getTblTaiKhoanById(int idTaiKhoan) throws SQLException, ClassNotFoundException;
	
	/**
	 * Kiểm tra xem Id có tồn tại trong bảng tbl_tai_khoan hay không ?
	 * @param idTaiKhoan : id cần kiểm tra
	 * @return true: Nếu tồn tại
	 *         false:Nếu không tồn tại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean checkExistedIdTaiKhoan(int idTaiKhoan) throws SQLException, ClassNotFoundException;
}
