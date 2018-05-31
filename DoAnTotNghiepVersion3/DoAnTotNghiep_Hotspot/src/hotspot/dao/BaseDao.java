/**
 * Copyright(C) 2018 NguyenDuyPhong
 * BaseDAO.java 18/04/2018 NguyenDuyPhong
 */

package hotspot.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Lớp này dùng để khai báo các method thao tác cơ sở dữ liệu.
 * @author NguyenDuyPhong
 *
 */
public interface BaseDao {
	/**
	 *Mở kết nối tới database 
	 * @return : đã được kết nối
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Connection openConnection() throws SQLException, ClassNotFoundException;
	
	
	/**
	 * gán đối tượng kết nối cho thuộc tính connection
	 * @param conn : đối tượng đã connection tới database
	 */
	public void setConnection(Connection conn);
	
	/**
	 * rollback connection
	 * 
	 */
	public void rollback() throws SQLException ;
	
	/**
	 * đóng kết nối tới database
	 *
	 */
	public void closeConnection() throws SQLException;
}
