/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblBaiVietLogic.java 23/04/2018 NguyenDuyPhong
 */
package hotspot.logic;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import hotspot.entities.TblBaiViet;
import hotspot.model.BaiVietInfor;
/**
 * giao diện chứa phương thức logic liên quan đến bảng tbl_bai_viet trong database
 * @author duyphong170195
 *
 */
public interface TblBaiVietLogic {
	/**
	 * Lấy tổng số bài viết theo id của tỉnh thành
	 * @param idTinhThanh : id của tỉnh thành trong bảng tbl_bai_viet
	 * @param tenDiaDiem : tên địa điểm trong bảng tbl_bai_viet
	 * @return tổng số bài viết
	 */
	public int getTotalBaiVietCuaTinhThanh(int idTinhThanh, String tenDiaDiem) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy tất cả bài viết theo id của tỉnh thành
	 * @param idTinhThanh : id tỉnh thành trong bài viết
	 * @param tenDiaDiem : tên địa điểm trong bài viết
	 * @return : list danh sách bài viết
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<BaiVietInfor> getListBaiVietCuaTinhThanh(int idTinhThanh, String tenDiaDiem) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy tổng số bài viết theo id của tỉnh thành
	 * @param idTaiKhoan : id tài khoản trong bảng tbl_bai_viet
	 * @param tenDiaDiem: tên địa điểm trong bảng tbl_bai_viet
	 * @return tổng số bài viết
	 */
	public int getTotalBaiVietCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy tất cả bài viết theo id của tỉnh thành
	 * @param idTinhThanh : id tài khoản trong bảng tbl_bai_viet
	 * @param tenDiaDiem : tên địa điểm trong bảng tbl_bai_viet
	 * @return : list danh sách bài viết
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<BaiVietInfor> getListBaiVietCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy mảng byte ảnh của bài viết theo id
	 * @param idTinhThanh
	 * @return mảng byte ảnh
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public byte[] getImageBaiVietById(int idBaiViet) throws ClassNotFoundException, SQLException;
	
	/**
	 * Thêm đối tượng tbl_bai_viet vào bảng tbl_bai_viet
	 * @param tblBaiViet : đối tượng được thêm
	 * @return true: nếu thêm thành công
	 * false : nếu thêm thất bại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertTblBaiViet(TblBaiViet tblBaiViet) throws SQLException, ClassNotFoundException, FileNotFoundException;
	
	/**
	 * Lấy đối tượng BaiVietInfor theo id
	 * @param idBaiViet : id của bài viết
	 * @return đối tượng BaiVietInfor
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TblBaiViet getTblBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException;
	/**
	 * Update đối tượng TblBaiViet vào bảng tbl_bai_viet
	 * @param tblBaiViet
	 * @return true: nếu update thành công
	 * false: nếu update thất bại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public boolean updateTblBaiViet(TblBaiViet tblBaiViet) throws SQLException, ClassNotFoundException, FileNotFoundException;
	/**
	 * Xóa bài viết theo id bài viết
	 * @param idBaiViet
	 * @return true: nếu xóa bài viết thành công
	 * false: nếu xóa bài viết thất bại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean deleteBaiViet(int idBaiViet) throws SQLException, ClassNotFoundException;
	
	/**
	 * kiểm tra bài viết có trong bảng tbl_bai_viet hay không ?
	 * @param idBaiViet : id bai viết
	 * @return true: tồn tại bài viết
	 * false : không tồn tại bài viết
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean checkExistedBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException;
	
	/**
	 * Thích bài viết trong bảng tbl_bai_viet theo id bài viết
	 * @param idBaiViet : id của bài viết
	 * @return true:nếu thích thành công
	 * false: nếu thích không thành công
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean thichBaiVietById(int idBaiViet, int idTaiKhoan) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy tất cả idbaiviet mà tai khoản đã thích
	 * @param idTaiKhoan : id của tài khoản
	 * @return danh sách id tài khoản đã like
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Integer> getAllLikeIdBaiVietById(int idTaiKhoan) throws SQLException, ClassNotFoundException;
	
}
