/**
 * Copyright(C) 2018 Luvina software
 * BaseDaoImpl.java 18/04/2018 NguyenDuyPhong
 */
package hotspot.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hotspot.dao.BaseDao;
import hotspot.properties.DatabaseProperties;
import hotspot.utils.CommonConstant;
/**
 * Lớp này dùng để thực thi kết nối với cơ sở dữ liệu.
 * @author duyphong170195
 *
 */
public class BaseDaoImpl implements BaseDao{

	protected Connection conn;
	/* (non-Javadoc)
	 * @see manageuser.dao.BaseDAO#connectDataBase()
	 */
	@Override
	public Connection openConnection() throws SQLException, ClassNotFoundException{
		// TODO Auto-generated method stub
		//Đường dẫn tới cơ sở dữ liệu
		String theDbUrl = DatabaseProperties.getData(CommonConstant.DBURL);
		//tên user đăng nhập để truy cập vào cơ sở dữ liệu
		String theDbUser = DatabaseProperties.getData(CommonConstant.DBUSER);
		//Mật khẩu để truy cập vào cơ sở dữ liệu
		String theDbPassword = DatabaseProperties.getData(CommonConstant.DBPASSWORD);
		//Phiên làm việc giữa ứng dụng java và database
		//Specify to the DriverManager which JDBC drivers to try to make Connections with.
		//The easiest way to do this is to use Class.forName()
		Class.forName("com.mysql.jdbc.Driver");		
		//The basic service for managing a set of JDBC drivers.
		conn = DriverManager.getConnection(theDbUrl, theDbUser, theDbPassword);
		//Trả về đối tượng connection đã kết nối với cơ sở dữ liệu.	
		return conn;
	}
	
	/* (non-Javadoc)
	 * @see manageuser.dao.BaseDAO#setConnection(java.sql.Connection)
	 */
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	
	/* (non-Javadoc)
	 * @see manageuser.dao.BaseDAO#rollback(java.sql.Connection)
	 */
	@Override
	public void rollback() throws SQLException {
		// TODO Auto-generated method stub
		if(this.conn != null){
			this.conn.rollback();
		}
	}

	/* (non-Javadoc)
	 * @see manageuser.dao.BaseDAO#closeConnection(java.sql.Connection)
	 */
	@Override
	public void closeConnection() throws SQLException {
		// TODO Auto-generated method stub
		if(this.conn != null){
			conn.close();
		}
	}
}
