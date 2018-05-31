/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblTinhThanhLogic.java 18/04/2018 NguyenDuyPhong
 */
package hotspot.logic;

import java.sql.SQLException;
import java.util.List;

import hotspot.entities.TblTinhThanh;
import hotspot.model.TinhThanhInfor;

/**
 * Giao diện xử lý logic bảng TblTinhThanh;
 * @author duyphong170195
 *
 */
public interface TblTinhThanhLogic {
	/**
	 * Lấy danh sách tỉnh thành theo tên tỉnh thành
	 * @param offset : Vị trí data cần lấy
	 * @param limit : Số lượng lấy
	 * @param tenTinhThanh : Tên tìm kiếm
	 * @return : danh sách các tỉnh thành
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<TinhThanhInfor> getListTinhThanh(int offset, int limit, String tenTinhThanh) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy số bản ghi theo tenTinhThanh;
	 * @param tenTinhThanh
	 * @return số bản ghi
	 */
	public int getTotalTinhThanh(String tenTinhThanh) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy mảng byte ảnh của tỉnh thành theo id
	 * @param idTinhThanh
	 * @return mảng byte ảnh
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public byte[] getImageTinhThanhById(int idTinhThanh) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy đối tượng TblTinhThanh theo id tỉnh thành
	 * @param idTinhThanh : id tỉnh thành
	 * @return đối tượng TblTinhThanh
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public TinhThanhInfor getTinhThanhInforById(int idTinhThanh) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy tất cả bản ghi trong bảng tbl_tinh_thanh
	 * @return : tất cả bản ghi trong bảng tbl_tinh_thanh
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TinhThanhInfor> getAllTinhThanhInfor() throws ClassNotFoundException, SQLException;
}
