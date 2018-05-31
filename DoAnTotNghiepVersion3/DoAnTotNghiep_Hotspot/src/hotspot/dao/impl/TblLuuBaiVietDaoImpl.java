package hotspot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.ChoiceCallback;

import hotspot.dao.TblLuuBaiVietDao;
import hotspot.dao.TblTaiKhoanDao;
import hotspot.model.BaiVietInfor;
import hotspot.utils.Common;

public class TblLuuBaiVietDaoImpl extends BaseDaoImpl implements TblLuuBaiVietDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblLuuBaiVietDao#getListBaiVietDaLuuCuaTaiKhoan(int,
	 * java.lang.String)
	 */
	@Override
	public List<BaiVietInfor> getListBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem)
			throws SQLException, ClassNotFoundException {
		// Phiên làm việc giữa ứng dụng java và sơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;

		ArrayList<BaiVietInfor> listBaiViet = new ArrayList<BaiVietInfor>();
		try {
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append(
						"SELECT baiviet.id_bai_viet, mo_ta, noi_dung, ngay_dang_bai, so_luot_thich, huyen_xa , ten_dia_diem, bi_danh, tieu_de ");
				sqlb.append("FROM tbl_luu_bai_viet luubaiviet ");
				sqlb.append("INNER JOIN tbl_bai_viet baiviet ON luubaiviet.id_bai_viet = baiviet.id_bai_viet ");
				sqlb.append("INNER JOIN tbl_tai_khoan taikhoan ON baiviet.id_tai_khoan = taikhoan.id_tai_khoan ");
				sqlb.append("WHERE luubaiviet.id_tai_khoan = ? ");
				if (!Common.isEmpty(tenDiaDiem) || tenDiaDiem != null) {
					sqlb.append("AND (baiviet.ten_dia_diem LIKE ?) ");
				}
				// gửi câu lệnh được truyền tham số tới database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số
				preparedStatement.setInt(parameterIndex++, idTaiKhoan);
				preparedStatement.setString(parameterIndex++, "%" + tenDiaDiem + "%");
				// Lấy bản ghi
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					BaiVietInfor baiVietInfor = new BaiVietInfor();
					baiVietInfor.setIdBaiViet(rs.getInt("id_bai_viet"));
					baiVietInfor.setMoTa(rs.getString("mo_ta"));
					baiVietInfor.setNoiDung(rs.getString("noi_dung"));
					baiVietInfor.setNgayVietBai(rs.getDate("ngay_dang_bai"));
					baiVietInfor.setSoLuotThich(rs.getInt("so_luot_thich"));
					baiVietInfor.setTenDiaDiem(rs.getString("ten_dia_diem"));
					baiVietInfor.setHuyenXa(rs.getString("huyen_xa"));
					baiVietInfor.setBiDanh(rs.getString("bi_danh"));
					baiVietInfor.setTieuDe(rs.getString("tieu_de"));
					listBaiViet.add(baiVietInfor);
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
		return listBaiViet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblLuuBaiVietDao#getTotalsBaiVietDaLuuCuaTaiKhoan(int,
	 * java.lang.String)
	 */
	@Override
	public int getTotalsBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem)
			throws SQLException, ClassNotFoundException {
		// Phiên làm việc giữa ứng dụng java và sơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Tổng số bản ghi
		int total = 0;
		try {
			// Mở kết nối database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT count(luubaiviet.id_bai_viet) ");
				sqlb.append("FROM tbl_luu_bai_viet luubaiviet ");
				sqlb.append("INNER JOIN tbl_bai_viet baiviet ON luubaiviet.id_bai_viet = baiviet.id_bai_viet ");
				sqlb.append("WHERE luubaiviet.id_tai_khoan = ? ");
				if (!Common.isEmpty(tenDiaDiem) || tenDiaDiem != null) {
					sqlb.append("AND (baiviet.ten_dia_diem LIKE ?) ");
				}
				// Gửi câu lệnh SQL đến databse
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số cho câu lệnh
				preparedStatement.setInt(parameterIndex++, idTaiKhoan);
				preparedStatement.setString(parameterIndex++, "%" + tenDiaDiem + "%");
				// Thực thi câu lệnh lấy ra bản ghi
				ResultSet rs = preparedStatement.executeQuery();
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
	 * @see hotspot.dao.TblLuuBaiVietDao#checkExistedIdLuuBaiViet(int)
	 */
	@Override
	public boolean checkExistedIdLuuBaiViet(int idBaiViet) throws SQLException, ClassNotFoundException {
		// Phiên làm việc giữa ứng dụng và database
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Biến kiểm tra xem id luu bài viết có tồn tại trong bảng
		// tbl_luu_bai_viet
		// hay không ?
		boolean checkExistedId = false;
		try {
			// Mở kết nối database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh sql
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT id_bai_viet ");
				sqlb.append("FROM tbl_luu_bai_viet ");
				sqlb.append("WHERE id_bai_viet = ? ");
				// Gán câu lệnh SQL đến database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// truyền tham số
				preparedStatement.setInt(parameterIndex++, idBaiViet);
				// thực thi câu lệnh SQL rồi lấy bản ghi về
				ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) {
					checkExistedId = true;
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
		// Nếu tồn tại id: true
		// Nếu không tồn tại : false
		return checkExistedId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblLuuBaiVietDao#deleteLuuBaiVietByIdBaiViet(int)
	 */
	@Override
	public boolean deleteLuuBaiVietByIdBaiViet(int idBaiViet) throws SQLException, ClassNotFoundException {
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Biến kiểm tra xem id luu bài viết có tồn tại trong bảng
		// tbl_luu_bai_viet
		// hay không ?
		boolean checkDeleteIdBaiViet = false;
		try {
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh sql
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("DELETE ");
				sqlb.append("FROM tbl_luu_bai_viet ");
				sqlb.append("WHERE id_bai_viet = ? ");
				// Gán câu lệnh SQL đến database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// truyền tham số
				preparedStatement.setInt(parameterIndex++, idBaiViet);

				checkDeleteIdBaiViet = preparedStatement.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return checkDeleteIdBaiViet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblLuuBaiVietDao#insetTblLuuBaiViet(int, int)
	 */
	@Override
	public boolean insetTblLuuBaiViet(int idTaiKhoan, int idBaiViet) throws SQLException, ClassNotFoundException {
		// Phiên làm việc giữa ứng dụng và database
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Biến kiểm tra xem insert có thành công hay không ?
		boolean checkInsert = false;
		try {
			//Mở kết nối tới database
			conn = openConnection();
			if(conn != null){
				int parameterIndex = 1;
				//Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("INSERT INTO tbl_luu_bai_viet(id_tai_khoan, id_bai_viet, ngay_luu) ");
				sqlb.append("VALUES(?, ?, ?) ");
				// Gán câu lệnh SQL đến database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				//Truyền tham số cho câu lệnh SQL
				preparedStatement.setInt(parameterIndex++, idTaiKhoan);
				preparedStatement.setInt(parameterIndex++, idBaiViet);
				preparedStatement.setDate(parameterIndex++, Common.convertDateUtilToDateSql(Common.getDate(Common.getYearNow(), Common.getMonthNow(), Common.getDayNow())));
				//Thực thi câu lệnh SQL
				checkInsert = preparedStatement.executeUpdate() > 0;
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
		return checkInsert;
	}

	/* (non-Javadoc)
	 * @see hotspot.dao.TblLuuBaiVietDao#checkExistedIdTaiKhoanIdBaiViet(int, int)
	 */
	@Override
	public boolean checkExistedIdTaiKhoanIdBaiViet(int idTaiKhoan, int idBaiViet)
			throws SQLException, ClassNotFoundException {
		// Phiên làm việc giữa ứng dụng và database
				Connection conn = null;
				// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
				PreparedStatement preparedStatement = null;
				// Biến kiểm tra xem id luu bài viết có tồn tại trong bảng
				// tbl_luu_bai_viet
				// hay không ?
				boolean checkExisted = false;
				try {
					// Mở kết nối database
					conn = openConnection();
					if (conn != null) {
						int parameterIndex = 1;
						// Câu lệnh sql
						StringBuilder sqlb = new StringBuilder();
						sqlb.append("SELECT id_bai_viet ");
						sqlb.append("FROM tbl_luu_bai_viet ");
						sqlb.append("WHERE id_tai_khoan = ? AND id_bai_viet = ? ");
						// Gán câu lệnh SQL đến database
						preparedStatement = conn.prepareStatement(sqlb.toString());
						// truyền tham số
						preparedStatement.setInt(parameterIndex++, idTaiKhoan);
						preparedStatement.setInt(parameterIndex++, idBaiViet);
						// thực thi câu lệnh SQL rồi lấy bản ghi về
						ResultSet rs = preparedStatement.executeQuery();
						if (rs.next()) {
							checkExisted = true;
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
				// Nếu tồn tại id: true
				// Nếu không tồn tại : false
				return checkExisted;
	}

	/* (non-Javadoc)
	 * @see hotspot.dao.TblLuuBaiVietDao#deleteBaiVietDaLuuCuaTaiKhoan(int, int)
	 */
	@Override
	public boolean deleteBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, int idBaiViet)
			throws SQLException, ClassNotFoundException {
		//Phiên làm việc giữa database và ứng dụng
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Biến kiểm tra xem id luu bài viết có tồn tại trong bảng
		// tbl_luu_bai_viet
		// hay không ?
		boolean checkDeleteIdTaiKhoanIdBaiViet = false;
		try {
			//Mở kết nối database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh sql
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("DELETE ");
				sqlb.append("FROM tbl_luu_bai_viet ");
				sqlb.append("WHERE id_tai_khoan = ? AND id_bai_viet = ? ");
				// Gán câu lệnh SQL đến database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// truyền tham số
				preparedStatement.setInt(parameterIndex++, idTaiKhoan);
				preparedStatement.setInt(parameterIndex++, idBaiViet);
				//Thực thi delete
				checkDeleteIdTaiKhoanIdBaiViet = preparedStatement.executeUpdate() > 0;
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
		//Nếu delete thành công : true
		//Nếu delete thất bại : false
		return checkDeleteIdTaiKhoanIdBaiViet;
	}

}
