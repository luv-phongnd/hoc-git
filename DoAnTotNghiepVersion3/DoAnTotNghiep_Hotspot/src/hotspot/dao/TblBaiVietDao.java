/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblBaiVietDao.java 22/04/2018 NguyenDuyPhong
 */
package hotspot.dao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import hotspot.entities.TblBaiViet;
import hotspot.model.BaiVietInfor;

/**
 * Giao diện thao tác với bảng tbl_bai_viet trong database
 * @author duyphong170195
 *
 */
public interface TblBaiVietDao {
	/**
	 * Lấy tổng số bài viết theo id của tỉnh thành
	 * @param idTinhThanh : id_tinh_thanh trong bảng tbl_bai_viet
	 * @param tenDiaDiem : tên địa điểm trong bài viết
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
	 * Lấy mảng byte ảnh của bài viết theo id
	 * @param idTinhThanh
	 * @return mảng byte ảnh
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public byte[] getImageBaiVietById(int idBaiViet) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy tổng số bài viết theo id tài khoản và tên địa điểm
	 * @param idTaiKhoan : idTaiKhoan trong bảng tbl_bai_viet
	 * @param tenDiaDiem : tên địa điểm trong bài viết
	 * @return tổng số bài viết
	 */
	public int getTotalBaiVietCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy tất cả bài viết theo id tài khoản và tên địa điểm
	 * @param idTaiKhoan : id tài khoản trong bảng tbl_bai_viet
	 * @param tenDiaDiem : tên địa điểm trong bài viết
	 * @return : list danh sách bài viết
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<BaiVietInfor> getListBaiVietCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem) throws SQLException, ClassNotFoundException;
	
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
	 * Lấy đối tượng BaiVietInfor theo id
	 * @param idBaiViet : id của bài viết
	 * @return đối tượng BaiVietInfor
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TblBaiViet getTblBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException;
	
	/**
	 * Xóa bài viết trong bảng tbl_bai_viet theo id bài viết
	 * @param idBaiViet : id bài viết của bản ghi bị xóa
	 * @return  true: nếu xóa thành công
	 * false : nếu xóa thất bại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean deleteBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException;
	
	/**
	 * Thích bài viết trong bảng tbl_bai_viet theo id bài viết
	 * @param idBaiViet : id của bài viết
	 * @return true:nếu thích thành công
	 * false: nếu thích không thành công
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean thichBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy tất cả idbaiviet mà tai khoản đã thích
	 * @param idTaiKhoan : id của tài khoản
	 * @return danh sách id tài khoản đã like
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Integer> getAllLikeIdBaiVietById(int idTaiKhoan) throws SQLException, ClassNotFoundException;
	
	/**
	 * 
	 * @param idBaiViet
	 * @param idTaiKhoan
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertTblThichBaiViet(int idBaiViet, int idTaiKhoan) throws SQLException, ClassNotFoundException;
}
