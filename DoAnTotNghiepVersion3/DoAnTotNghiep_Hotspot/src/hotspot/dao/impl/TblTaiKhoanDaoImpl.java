/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblTaiKhoanDaoImpl.java 18/04/2018 NguyenDuyPhong
 */
package hotspot.dao.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import hotspot.dao.TblTaiKhoanDao;
import hotspot.entities.TblTaiKhoan;
import hotspot.entities.TblTinhThanh;
import hotspot.security.EncodingPassword;

/**
 * Thực thi các phương thức thao tác với bảng tbl_tai_khoản trong database
 * 
 * @author duyphong170195
 *
 */
public class TblTaiKhoanDaoImpl extends BaseDaoImpl implements TblTaiKhoanDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hotspot.dao.TblTaiKhoanDao#getTblTaiKhoanbyUserName(java.lang.String)
	 */
	@Override
	public TblTaiKhoan getTblTaiKhoanbyUserName(String userName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		// Danh sách chứa đối tượng userInfor
		TblTaiKhoan tblTaiKhoan = null;
		try {
			// Mở kết nối database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append(
						"SELECT id_tai_khoan, ten_tai_khoan, email, so_dien_thoai, mat_khau, salt, user_role, bi_danh ");
				sqlb.append("FROM tbl_tai_khoan ");
				sqlb.append("WHERE ten_tai_khoan = ?");
				// Truyền cậu lệnh SQL đến database
				preStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số cho câu lệnh SQL
				preStatement.setString(parameterIndex++, userName);
				// Thực thi câu lệnh SQL lấy ra các bản ghi (nếu có)
				ResultSet rs = preStatement.executeQuery();
				// Nếu có bản ghi
				if (rs.next()) {
					tblTaiKhoan = new TblTaiKhoan();
					tblTaiKhoan.setIdTaiKhoan(rs.getInt("id_tai_khoan"));
					tblTaiKhoan.setTenTaiKhoan(rs.getString("ten_tai_khoan"));
					tblTaiKhoan.setEmail(rs.getString("email"));
					tblTaiKhoan.setSoDienThoai(rs.getString("so_dien_thoai"));
					tblTaiKhoan.setMatKhau(rs.getString("mat_khau"));
					tblTaiKhoan.setSalt(rs.getString("salt"));
					tblTaiKhoan.setUserRole(rs.getString("user_role"));
					tblTaiKhoan.setBiDanh(rs.getString("bi_danh"));
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
		return tblTaiKhoan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblTaiKhoanDao#getBiDanhByIdTaiKhoan(int)
	 */
	@Override
	public String getBiDanhByIdTaiKhoan(int idTaiKhoan) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		// Biến bí danh
		String biDanh = "";
		try {
			// Mở kết nối tới database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT bi_danh ");
				sqlb.append("FROM tbl_tai_khoan ");
				sqlb.append("WHERE id_tai_khoan = ? ");

				// Gửi câu lệnh SQL tới databse
				preStatement = conn.prepareStatement(sqlb.toString());
				// Sét parameter
				preStatement.setInt(parameterIndex++, idTaiKhoan);
				// Thực thi câu lệnh sql rồi gán vào Resultset
				ResultSet rs = preStatement.executeQuery();
				// nếu có bản ghi
				if (rs.next()) {
					biDanh = rs.getString("bi_danh");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}

		return biDanh;
	}

	@Override
	public TblTaiKhoan getTblTaiKhoanByEmail(String email) throws SQLException, ClassNotFoundException {
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		// Danh sách chứa đối tượng userInfor
		TblTaiKhoan tblTaiKhoan = null;
		try {
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append(
						"SELECT id_tai_khoan, ten_tai_khoan, email, so_dien_thoai, mat_khau, salt, user_role, bi_danh ");
				sqlb.append("FROM tbl_tai_khoan ");
				sqlb.append("WHERE email = ?");
				// Truyền cậu lệnh SQL đến database
				preStatement = conn.prepareStatement(sqlb.toString());
				// Sét tham số cho câu lệnh
				preStatement.setString(parameterIndex++, email);
				// Thực thi câu lệnh SQL lấy ra các bản ghi (nếu có)
				ResultSet rs = preStatement.executeQuery();
				// Nếu có bản ghi
				if (rs.next()) {
					tblTaiKhoan = new TblTaiKhoan();
					tblTaiKhoan.setIdTaiKhoan(rs.getInt("id_tai_khoan"));
					tblTaiKhoan.setTenTaiKhoan(rs.getString("ten_tai_khoan"));
					tblTaiKhoan.setEmail(rs.getString("email"));
					tblTaiKhoan.setSoDienThoai(rs.getString("so_dien_thoai"));
					tblTaiKhoan.setMatKhau(rs.getString("mat_khau"));
					tblTaiKhoan.setSalt(rs.getString("salt"));
					tblTaiKhoan.setUserRole(rs.getString("user_role"));
					tblTaiKhoan.setBiDanh(rs.getString("bi_danh"));
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
		return tblTaiKhoan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblTaiKhoanDao#insertTblTaiKhoan(hotspot.entities.
	 * TblTaiKhoan)
	 */
	@Override
	public boolean insertTblTaiKhoan(TblTaiKhoan tblTaiKhoan)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
		PreparedStatement preparedStatement = null;
		// phiên làm việc giữa ứng dụng và database
		Connection conn = null;
		// biến check insert có thành công hay không ?
		boolean checkInsertSuccess = false;

		try {
			conn = openConnection();
			// Nếu connection database thành công
			if (conn != null) {
				int parameterIndex = 1;

				// Câu lệnh insert
				StringBuilder sqlb = new StringBuilder();
				sqlb.append(
						"INSERT INTO tbl_tai_khoan (ten_tai_khoan, email, so_dien_thoai, mat_khau, salt, user_role, bi_danh) ");
				sqlb.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
				// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
				preparedStatement = conn.prepareStatement(sqlb.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
				// Truyền tham số cho parameter
				preparedStatement.setString(parameterIndex++, tblTaiKhoan.getTenTaiKhoan());
				preparedStatement.setString(parameterIndex++, tblTaiKhoan.getEmail());
				preparedStatement.setString(parameterIndex++, tblTaiKhoan.getSoDienThoai());
				preparedStatement.setString(parameterIndex++,
						EncodingPassword.sha1(tblTaiKhoan.getMatKhau(), tblTaiKhoan.getSalt()));
				preparedStatement.setString(parameterIndex++, tblTaiKhoan.getSalt());
				preparedStatement.setString(parameterIndex++, "no");
				preparedStatement.setString(parameterIndex++, tblTaiKhoan.getBiDanh());
				// Thực thi câu lệnh SQL
				checkInsertSuccess = preparedStatement.executeUpdate() > 0;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			closeConnection();
		}
		// Insert thành công:true
		// Insert thất bại : false
		return checkInsertSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hotspot.dao.TblTaiKhoanDao#updateMatKhau(hotspot.entities.TblTaiKhoan,
	 * String)
	 */
	@Override
	public boolean updateMatKhau(TblTaiKhoan tblTaiKhoan, String matKhauMoi)
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
		PreparedStatement preparedStatement = null;
		// phiên làm việc giữa ứng dụng và database
		Connection conn = null;
		// biến check insert có thành công hay không ?
		boolean checkUpdateSuccess = false;

		try {
			conn = openConnection();
			// Nếu connection database thành công
			if (conn != null) {

				int parameterIndex = 1;

				// Câu lệnh insert
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("UPDATE tbl_tai_khoan ");
				sqlb.append("SET mat_khau = ? ");
				sqlb.append("WHERE id_tai_khoan = ? ");
				// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
				preparedStatement = conn.prepareStatement(sqlb.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
				// Truyền tham số cho parameter
				preparedStatement.setString(parameterIndex++, EncodingPassword.sha1(matKhauMoi, tblTaiKhoan.getSalt()));
				preparedStatement.setInt(parameterIndex++, tblTaiKhoan.getIdTaiKhoan());
				// Thực thi câu lệnh SQL
				checkUpdateSuccess = preparedStatement.executeUpdate() > 0;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			closeConnection();
		}
		// Update thành công: true
		// Update thất bại : false
		return checkUpdateSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblTaiKhoanDao#getTblTaiKhoanById(int)
	 */
	@Override
	public TblTaiKhoan getTblTaiKhoanById(int idTaiKhoan) throws SQLException, ClassNotFoundException{
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		// Danh sách chứa đối tượng userInfor
		TblTaiKhoan tblTaiKhoan = null;
		try {
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append(
						"SELECT id_tai_khoan, ten_tai_khoan, email, so_dien_thoai, mat_khau, salt, user_role, bi_danh ");
				sqlb.append("FROM tbl_tai_khoan ");
				sqlb.append("WHERE id_tai_khoan = ?");
				// Truyền cậu lệnh SQL đến database
				preStatement = conn.prepareStatement(sqlb.toString());
				// Sét tham số cho câu lệnh
				preStatement.setInt(parameterIndex++, idTaiKhoan);
				// Thực thi câu lệnh SQL lấy ra các bản ghi (nếu có)
				ResultSet rs = preStatement.executeQuery();
				// Nếu có bản ghi
				if (rs.next()) {
					tblTaiKhoan = new TblTaiKhoan();
					tblTaiKhoan.setIdTaiKhoan(rs.getInt("id_tai_khoan"));
					tblTaiKhoan.setTenTaiKhoan(rs.getString("ten_tai_khoan"));
					tblTaiKhoan.setEmail(rs.getString("email"));
					tblTaiKhoan.setSoDienThoai(rs.getString("so_dien_thoai"));
					tblTaiKhoan.setMatKhau(rs.getString("mat_khau"));
					tblTaiKhoan.setSalt(rs.getString("salt"));
					tblTaiKhoan.setUserRole(rs.getString("user_role"));
					tblTaiKhoan.setBiDanh(rs.getString("bi_danh"));
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
		return tblTaiKhoan;
	}

}
