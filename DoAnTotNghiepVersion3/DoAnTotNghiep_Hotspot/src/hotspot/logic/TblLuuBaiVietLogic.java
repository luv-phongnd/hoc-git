/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblLuuBaiVietLogic.java 27/04/2018 NguyenDuyPhong
 */
package hotspot.logic;

import java.sql.SQLException;
import java.util.List;

import hotspot.model.BaiVietInfor;

/**
 * Xử lý logic liên quan đến bảng tbl_luu_bai_viet
 * @author duyphong170195
 *
 */
public interface TblLuuBaiVietLogic {
	/**
	 * Lấy danh sách bài viết đã lưu của tài khoản theo giá trị search
	 * @param idTaiKhoan : id tài khoản
	 * @param tenDiaDiem : tên địa điểm ( giá trị search)
	 * @return : danh sách bài viết đã lưu của tài khoản theo giá trị search
	 */
	public List<BaiVietInfor> getListBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy tổng số bài viết đã lưu của tài khoản theo giá trị search
	 * @param idTaiKhoan : id tài khoản
	 * @param tenDiaDiem : tên địa điểm (giá trị search)
	 * @return : tổng số bài viết đã lưu của tài khoản theo giá trị search
	 */
	public int getTotalsBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem) throws SQLException, ClassNotFoundException;
	
	/**
	 * thêm đối tượng TblLuuBaiViet vào bang tbl_bai_viet
	 * @param idBaiViet : id bài viết muốn thêm
	 * @param idTaiKhoan : id tài khoản muốn thêm
	 * @return true: thêm thành công
	 * false : thêm thất bại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insetTblLuuBaiViet(int idTaiKhoan, int idBaiViet) throws SQLException, ClassNotFoundException;
	
	/**
	 * kiểm tra xem tài khoản của người dùng đã lưu bài viết này chưa
	 * @param idTaiKhoan : id tài khoản
	 * @param idBaiViet : id bài viết
	 * @return true: Nếu lưu rồi
	 * false: nếu chưa lưu
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean checkExistedIdTaiKhoanIdBaiViet(int idTaiKhoan,int idBaiViet) throws SQLException, ClassNotFoundException;
	
	/**
	 * Xóa bài viết đã lưu theo id bài viết trong bảng tbl_luu_bai_viet
	 * @param idTaiKhoan : id tài khoản
	 * @param idBaiViet : id bài viết trong bảng tbl_luu_bai_viet
	 * @return true: nếu xóa thành công
	 * false: nếu xóa thất bại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean deleteBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, int idBaiViet) throws SQLException, ClassNotFoundException;
}
