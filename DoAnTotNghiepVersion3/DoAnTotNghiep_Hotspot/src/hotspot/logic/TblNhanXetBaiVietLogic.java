/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblNhanXetBaiVietLogic.java 12/05/2018 NguyenDuyPhong
 */
package hotspot.logic;

import java.sql.SQLException;
import java.util.List;

import hotspot.model.NhanXetInfor;
/**
 * Giao diện thao tác với bảng tbl_Nhan_xet_bai_viet
 * @author duyphong170195
 *
 */
public interface TblNhanXetBaiVietLogic {
	/**
	 * Lấy tất cả các nhận xét của bài viết theo id bài viết
	 * @param idBaiViet
	 * @return list nhận xét.
	 */
	public List<NhanXetInfor> getAllComment(int idBaiViet) throws SQLException, ClassNotFoundException;
	
	/**
	 * Thêm nhận xét bài viết
	 * @param idBaiViet : id của bài viết
	 * @param idTaiKhoan : id của tài khoản
	 * @param nhanXet : Nhận xet
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertComment(int idBaiViet, int idTaiKhoan, String nhanXet) throws SQLException, ClassNotFoundException;
}
