/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblTinhThanhDaoImpl.java 18/04/2018 NguyenDuyPhong
 */

package hotspot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotspot.dao.TblTinhThanhDao;
import hotspot.entities.TblTinhThanh;
import hotspot.model.TinhThanhInfor;
import hotspot.utils.Common;

/**
 * Chứa các method thao tác với bảng tbl_tinh_thanh
 * 
 * @author NguyenDuyPhong
 *
 */
public class TblTinhThanhDaoImpl extends BaseDaoImpl implements TblTinhThanhDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblTinhThanhDao#getTotalTinhThanh(java.lang.String)
	 */
	@Override
	public int getTotalTinhThanh(String tenTinhThanh) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		// Phiên làm việc giữa ứng dụng java và sơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Tổng số bản ghi
		int total = 0;

		try {
			// Mở kết nối tới database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL lấy tổng số bản ghi
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT count(tinhthanh.id_tinh_thanh) ");
				sqlb.append("FROM tbl_tinh_thanh tinhthanh ");
				sqlb.append("WHERE (tinhthanh.ten_tinh_thanh LIKE ?) ");
				// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số email cho parameter
				preparedStatement.setString(parameterIndex++, "%" + tenTinhThanh + "%");
				// Gán dữ liệu vừa truy vấn được vào ResultSet(resultset như một
				// bảng trong database)
				ResultSet rs = preparedStatement.executeQuery();
				// Nếu có bản ghi thì sẽ vào if
				if (rs.next()) {
					total = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblTinhThanhDao#getListUsers(int, int, java.lang.String)
	 */
	@Override
	public List<TinhThanhInfor> getListTinhThanh(int offset, int limit, String tenTinhThanh)
			throws ClassNotFoundException, SQLException {
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		// Danh sách chứa đối tượng userInfor
		ArrayList<TinhThanhInfor> tblTinhThanhs = new ArrayList<TinhThanhInfor>();
		try {
			// Mở kết nối tới database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh sql lấy tất cả bản ghi sau khi kết nối 4 bảng
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT  id_tinh_thanh, ten_tinh_thanh ");
				sqlb.append("FROM tbl_tinh_thanh tinhthanh ");
				if(!Common.isEmpty(tenTinhThanh) || tenTinhThanh != null){
					sqlb.append("WHERE (tinhthanh.ten_tinh_thanh LIKE ?) ");
				}
				// Vị trí và số bản ghi hiển thị trên trang hiện tại
				sqlb.append(" LIMIT " + offset + ", " + limit);
				// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
				preStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số email cho parameter
				preStatement.setString(parameterIndex++, "%" + tenTinhThanh + "%");
				// Gán dữ liệu vừa truy vấn được vào ResultSet(resultset như một
				// bảng trong database
				ResultSet rs = preStatement.executeQuery();
				// Nếu có bản ghi
				while (rs.next()) {
					// Khởi tạo đối tượng tblTinhThanh
					TinhThanhInfor tinhThanhInfor = new TinhThanhInfor();
					// Sét dữ liệu cho tblTinhThanh
					tinhThanhInfor.setIdTinhThanh(rs.getInt("id_tinh_thanh"));
					tinhThanhInfor.setTenTinhThanh(rs.getString("ten_tinh_thanh"));
					// Thêm đối tượng userInfor vào danh sách
					tblTinhThanhs.add(tinhThanhInfor);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		// Trả về danh sách UserInfor
		return tblTinhThanhs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblTinhThanhDao#getImageTinhThanhById(int)
	 */
	@Override
	public byte[] getImageTinhThanhById(int idTinhThanh) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		// Mảng byte ảnh
		byte[] binaryStream = null;
		
		try{
			//Mở kết nối tới database
			conn = openConnection();
			if(conn != null){
				
				int parameterIndex = 1;
				//Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT img_tinh_thanh ");
				sqlb.append("FROM tbl_tinh_thanh ");
				sqlb.append("WHERE id_tinh_thanh = ? ");
				// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
				preStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số id cho parameter
				preStatement.setInt(parameterIndex++, idTinhThanh);
				// Gán dữ liệu vừa truy vấn được vào ResultSet(resultset như một
				// bảng trong database
				ResultSet rs = preStatement.executeQuery();
				if(rs.next()){
					//lấy dữ liệu img_tinh_thanh từ bảng tbl_tinh_thanh
					binaryStream = rs.getBytes("img_tinh_thanh");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}

		return binaryStream;
	}

	/* (non-Javadoc)
	 * @see hotspot.dao.TblTinhThanhDao#getTinhThanhInforById(int)
	 */
	@Override
	public TinhThanhInfor getTinhThanhInforById(int idTinhThanh) throws ClassNotFoundException, SQLException {
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		// Danh sách chứa đối tượng userInfor
		TinhThanhInfor tinhThanhInfor = null;
		try {
			// Mở kết nối tới database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh sql lấy tất cả bản ghi sau khi kết nối 4 bảng
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT  id_tinh_thanh, ten_tinh_thanh ");
				sqlb.append("FROM tbl_tinh_thanh tinhthanh ");
				sqlb.append("WHERE tinhthanh.id_tinh_thanh = ? ");
				// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
				preStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số email cho parameter
				preStatement.setInt(parameterIndex++, idTinhThanh);
				// Gán dữ liệu vừa truy vấn được vào ResultSet(resultset như một
				// bảng trong database
				ResultSet rs = preStatement.executeQuery();
				// Nếu có bản ghi
				if (rs.next()) {
					// Khởi tạo đối tượng tblTinhThanh
					tinhThanhInfor = new TinhThanhInfor();
					// Sét dữ liệu cho tblTinhThanh
					tinhThanhInfor.setIdTinhThanh(rs.getInt("id_tinh_thanh"));
					tinhThanhInfor.setTenTinhThanh(rs.getString("ten_tinh_thanh"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		// Trả về danh sách UserInfor
		return tinhThanhInfor;
	}

	/* (non-Javadoc)
	 * @see hotspot.dao.TblTinhThanhDao#getAllTinhThanhInfor()
	 */
	@Override
	public List<TinhThanhInfor> getAllTinhThanhInfor() throws ClassNotFoundException, SQLException {
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		//Mảng chứa tỉnh thành infor
		ArrayList<TinhThanhInfor> tinhThanhInfors = new ArrayList<TinhThanhInfor>();
		try{
			//Mở kết nối tới database
			conn = openConnection();
			if(conn != null){
				//Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT id_tinh_thanh, ten_tinh_thanh ");
				sqlb.append("FROM tbl_tinh_thanh ");
				
				//Gửi câu lệnh SQL đến database
				preStatement = conn.prepareStatement(sqlb.toString());
				//Thực thi câu lệnh SQL rồi gán vào ResultSet
				ResultSet rs = preStatement.executeQuery();
				//Nếu có bản ghi
				while(rs.next()){
					//Khởi tạo đối tượng tỉnh thành
					TinhThanhInfor tinhThanhInfor = new TinhThanhInfor();
					tinhThanhInfor.setIdTinhThanh(rs.getInt("id_tinh_thanh"));
					tinhThanhInfor.setTenTinhThanh(rs.getString("ten_tinh_thanh"));
					//Thêm đối tượng tỉnh thành vào mảng
					tinhThanhInfors.add(tinhThanhInfor);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}finally{
			closeConnection();
		}
		return tinhThanhInfors;
	}

	
}
