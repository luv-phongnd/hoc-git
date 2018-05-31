/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblBaiVietDaoImpl.java 22/04/2018 NguyenDuyPhong
 */
package hotspot.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotspot.dao.TblBaiVietDao;
import hotspot.dao.TblTaiKhoanDao;
import hotspot.entities.TblBaiViet;
import hotspot.model.BaiVietInfor;
import hotspot.utils.Common;

/**
 * Thực thi các phương thức thao tác với bảng tbl_bai_viet trong database
 * 
 * @author NguyenDuyPhong
 */
public class TblBaiVietDaoImpl extends BaseDaoImpl implements TblBaiVietDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblBaiVietDao#getTotalBaiVietCuaTinhThanh(int)
	 */
	@Override
	public int getTotalBaiVietCuaTinhThanh(int idTinhThanh, String tenDiaDiem)
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
				sqlb.append("SELECT count(baiviet.id_tinh_thanh) ");
				sqlb.append("FROM tbl_bai_viet baiviet ");
				sqlb.append("WHERE baiviet.id_tinh_thanh = ? ");
				if (!Common.isEmpty(tenDiaDiem) || tenDiaDiem != null) {
					sqlb.append("AND  baiviet.ten_dia_diem LIKE ? ");
				}
				// Gửi câu lệnh SQL đến databse
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số cho câu lệnh
				preparedStatement.setInt(parameterIndex++, idTinhThanh);
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
	 * @see hotspot.dao.TblBaiVietDao#getListBaiVietCuaTinhThanh(int,
	 * java.lang.String)
	 */
	@Override
	public List<BaiVietInfor> getListBaiVietCuaTinhThanh(int idTinhThanh, String tenDiaDiem)
			throws SQLException, ClassNotFoundException {
		// Phiên làm việc giữa ứng dụng java và sơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;

		ArrayList<BaiVietInfor> listBaiViet = new ArrayList<BaiVietInfor>();
		TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
		try {
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append(
						"SELECT id_bai_viet, mo_ta, noi_dung, ngay_dang_bai, so_luot_thich, huyen_xa, id_tai_khoan, ten_dia_diem, tieu_de ");
				sqlb.append("FROM tbl_bai_viet baiviet ");
				sqlb.append("WHERE baiviet.id_tinh_thanh = ? ");
				if (!Common.isEmpty(tenDiaDiem) || tenDiaDiem != null) {
					sqlb.append("AND (baiviet.ten_dia_diem LIKE ?) ");
				}
				// gửi câu lệnh được truyền tham số tới database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số
				preparedStatement.setInt(parameterIndex++, idTinhThanh);
				if (!Common.isEmpty(tenDiaDiem) || tenDiaDiem != null) {
					preparedStatement.setString(parameterIndex++, "%" + tenDiaDiem + "%");
				}
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
					baiVietInfor.setBiDanh(tblTaiKhoanDao.getBiDanhByIdTaiKhoan(rs.getInt("id_tai_khoan")));
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
	 * @see hotspot.dao.TblBaiVietDao#getImageBaiVietById(int)
	 */
	@Override
	public byte[] getImageBaiVietById(int idBaiViet) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Phiên làm việc giữa ứng dụng java và cơ sở dữ liệu
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		// Mảng byte ảnh
		byte[] binaryStream = null;

		try {
			// Mở kết nối tới database
			conn = openConnection();
			if (conn != null) {

				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT img_bai_viet ");
				sqlb.append("FROM tbl_bai_viet ");
				sqlb.append("WHERE id_bai_viet = ? ");
				// Gửi câu lệnh SQL đã được truyền tham số đến cơ sở dữ liệu
				preStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số id cho parameter
				preStatement.setInt(parameterIndex++, idBaiViet);
				// Gán dữ liệu vừa truy vấn được vào ResultSet(resultset như một
				// bảng trong database
				ResultSet rs = preStatement.executeQuery();
				if (rs.next()) {
					// lấy dữ liệu img_tinh_thanh từ bảng tbl_tinh_thanh
					binaryStream = rs.getBytes("img_bai_viet");
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

		return binaryStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblBaiVietDao#getTotalBaiVietCuaTaiKhoan(int,
	 * java.lang.String)
	 */
	@Override
	public int getTotalBaiVietCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem)
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
				sqlb.append("SELECT count(baiviet.id_tai_khoan) ");
				sqlb.append("FROM tbl_bai_viet baiviet ");
				sqlb.append("WHERE baiviet.id_tai_khoan = ? ");
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

	@Override
	public List<BaiVietInfor> getListBaiVietCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem)
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
						"SELECT id_bai_viet, mo_ta, noi_dung, ngay_dang_bai, so_luot_thich, huyen_xa, ten_dia_diem, bi_danh, tieu_de ");
				sqlb.append("FROM tbl_bai_viet baiviet ");
				sqlb.append("INNER JOIN tbl_tai_khoan taikhoan ON baiviet.id_tai_khoan=taikhoan.id_tai_khoan ");
				sqlb.append("WHERE baiviet.id_tai_khoan = ? ");
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
	 * @see
	 * hotspot.dao.TblBaiVietDao#insertTblBaiViet(hotspot.entities.TblBaiViet)
	 */
	@Override
	public boolean insertTblBaiViet(TblBaiViet tblBaiViet)
			throws SQLException, ClassNotFoundException, FileNotFoundException {
		// Phiên làm việc giữa database và ứng dụng
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		FileInputStream inputStream = null;
		boolean checkInsert = false;
		try {
			conn = openConnection();
			if (conn != null) {
				File image = new File("F:\\filechuaservlet\\" + tblBaiViet.getPathImage());
				inputStream = new FileInputStream(image);
				// String path =
				// "C:/Users/duyphong170195/Desktop/giaodiendoan/images";
				StringBuilder sqlb = new StringBuilder("");
				sqlb.append(
						"INSERT INTO tbl_bai_viet(mo_ta, noi_dung, img_bai_viet, ngay_dang_bai, so_luot_thich, huyen_xa, id_tinh_thanh, id_tai_khoan, ten_dia_diem, tieu_de) ");
				sqlb.append("values(?,?,?,?,?,?,?,?,?,?) ");

				preparedStatement = conn.prepareStatement(sqlb.toString());
				int parameterIndex = 1;
				preparedStatement.setString(parameterIndex++, tblBaiViet.getMoTa());
				preparedStatement.setString(parameterIndex++, tblBaiViet.getNoiDung());
				preparedStatement.setBinaryStream(parameterIndex++, (InputStream) inputStream, (int) (image.length()));
				preparedStatement.setDate(parameterIndex++,
						Common.convertDateUtilToDateSql(tblBaiViet.getNgayDangBai()));
				preparedStatement.setInt(parameterIndex++, tblBaiViet.getSoLuotThich());
				preparedStatement.setString(parameterIndex++, tblBaiViet.getHuyenXa());
				preparedStatement.setInt(parameterIndex++, tblBaiViet.getIdTinhThanh());
				preparedStatement.setInt(parameterIndex++, tblBaiViet.getIdTaiKhoan());
				preparedStatement.setString(parameterIndex++, tblBaiViet.getTenDiaDiem());
				preparedStatement.setString(parameterIndex++, tblBaiViet.getTieuDe());
				checkInsert = preparedStatement.executeUpdate() > 0;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}

		return checkInsert;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblBaiVietDao#getTblBaiVietById(int)
	 */
	@Override
	public TblBaiViet getTblBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// Phiên làm việc giữa database và ứng dụng
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// biến tblBaiViet
		TblBaiViet tblBaiViet = null;
		TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
		try {
			// Mở kết nối tới database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append(
						"SELECT id_bai_viet, mo_ta, noi_dung, ngay_dang_bai, so_luot_thich, huyen_xa, id_tai_khoan, id_tinh_thanh, ten_dia_diem, tieu_de ");
				sqlb.append("FROM tbl_bai_viet baiviet ");
				sqlb.append("WHERE baiviet.id_bai_viet = ? ");

				// gửi câu lệnh được truyền tham số tới database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số
				preparedStatement.setInt(parameterIndex++, idBaiViet);
				// Lấy bản ghi
				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					tblBaiViet = new TblBaiViet();
					tblBaiViet.setIdBaiViet(rs.getInt("id_bai_viet"));
					tblBaiViet.setMoTa(rs.getString("mo_ta"));
					tblBaiViet.setNoiDung(rs.getString("noi_dung"));
					tblBaiViet.setNgayDangBai(rs.getDate("ngay_dang_bai"));
					tblBaiViet.setSoLuotThich(rs.getInt("so_luot_thich"));
					tblBaiViet.setHuyenXa(rs.getString("huyen_xa"));
					tblBaiViet.setBiDanh(tblTaiKhoanDao.getBiDanhByIdTaiKhoan(rs.getInt("id_tai_khoan")));
					tblBaiViet.setIdTinhThanh(rs.getInt("id_tinh_thanh"));
					tblBaiViet.setTenDiaDiem(rs.getString("ten_dia_diem"));
					tblBaiViet.setTieuDe(rs.getString("tieu_de"));
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
		return tblBaiViet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hotspot.dao.TblBaiVietDao#updateTblBaiViet(hotspot.entities.TblBaiViet)
	 */
	@Override
	public boolean updateTblBaiViet(TblBaiViet tblBaiViet)
			throws SQLException, ClassNotFoundException, FileNotFoundException {
		// Phiên làm việc giữa database và ứng dụng
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		FileInputStream inputStream = null;
		// Biến kiểm tra xem update thành công hay không?
		boolean checkUpdate = false;

		try {
			conn = openConnection();
			if (conn != null) {
				File image = new File("F:\\filechuaservlet\\" + tblBaiViet.getPathImage());
				inputStream = new FileInputStream(image);
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("UPDATE tbl_bai_viet ");
				sqlb.append(
						"SET mo_ta = ?, id_tinh_thanh = ?, huyen_xa = ?, ten_dia_diem = ?, img_bai_viet = ?, noi_dung = ?, tieu_de = ? ");
				sqlb.append("WHERE id_bai_viet = ? ");
				// Gửi câu lệnh SQL tới database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số cho câu lệnh sql
				preparedStatement.setString(parameterIndex++, tblBaiViet.getMoTa());
				preparedStatement.setInt(parameterIndex++, tblBaiViet.getIdTinhThanh());
				preparedStatement.setString(parameterIndex++, tblBaiViet.getHuyenXa());
				preparedStatement.setString(parameterIndex++, tblBaiViet.getTenDiaDiem());
				preparedStatement.setBinaryStream(parameterIndex++, (InputStream) inputStream, (int) (image.length()));
				preparedStatement.setString(parameterIndex++, tblBaiViet.getNoiDung());
				preparedStatement.setString(parameterIndex++, tblBaiViet.getTieuDe());
				preparedStatement.setInt(parameterIndex++, tblBaiViet.getIdBaiViet());
				//
				checkUpdate = preparedStatement.executeUpdate() > 0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		// true: Nếu update thành công
		// false: Nếu update không thành công
		return checkUpdate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblBaiVietDao#deleteBaiVietById(int)
	 */
	@Override
	public boolean deleteBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException {
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
				sqlb.append("FROM tbl_bai_viet ");
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
	 * @see hotspot.dao.TblBaiVietDao#thichBaiVietById(int)
	 */
	@Override
	public boolean thichBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException {
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Biến kiểm tra thích thành công hay không ?
		boolean checkThichBaiViet = false;
		try {
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("UPDATE tbl_bai_viet ");
				sqlb.append("SET so_luot_thich = so_luot_thich + 1 ");
				sqlb.append("WHERE id_bai_viet = ?");
				// Gửi câu lệnh SQL đến database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số
				preparedStatement.setInt(parameterIndex++, idBaiViet);

				checkThichBaiViet = preparedStatement.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return checkThichBaiViet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblBaiVietDao#getAllLikeIdBaiVietById(int)
	 */
	@Override
	public List<Integer> getAllLikeIdBaiVietById(int idTaiKhoan) throws SQLException, ClassNotFoundException {
		// Phiên làm việc giữa database và ứng dụng
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Danh sách chứa id bài viết
		ArrayList<Integer> listIdBaiVietDaThich = new ArrayList<Integer>();
		try {
			// Mở kết nối tới database
			conn = openConnection();
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh sql
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT thichbaiviet.id_bai_viet ");
				sqlb.append("FROM tbl_thich_bai_viet thichbaiviet ");
				sqlb.append("INNER JOIN tbl_bai_viet baiviet ON thichbaiviet.id_bai_viet = baiviet.id_bai_viet ");
				sqlb.append("WHERE thichbaiviet.id_tai_khoan = ? ");

				// Gửi câu lệnh sql đến database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số cho câu lệnh sql
				preparedStatement.setInt(parameterIndex++, idTaiKhoan);
				// Gán bản ghi lấy được vào result Set
				ResultSet rs = preparedStatement.executeQuery();
				// Nếu có bản ghi
				while (rs.next()) {
					// Them vao list
					listIdBaiVietDaThich.add(rs.getInt(1));
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

		return listIdBaiVietDaThich;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotspot.dao.TblBaiVietDao#insertTblThichBaiViet(int, int)
	 */
	@Override
	public boolean insertTblThichBaiViet(int idBaiViet, int idTaiKhoan) throws SQLException, ClassNotFoundException {
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		// Biến kiểm tra xem insert có thành công hay không ?
		boolean checkInsert = false;
		try {
			if (conn != null) {
				int parameterIndex = 1;
				// Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("INSERT INTO tbl_thich_bai_viet(id_bai_viet, id_tai_khoan) ");
				sqlb.append("VALUES(?, ?) ");
				// Gán câu lệnh SQL đến database
				preparedStatement = conn.prepareStatement(sqlb.toString());
				// Truyền tham số cho câu lệnh SQL
				preparedStatement.setInt(parameterIndex++, idBaiViet);
				preparedStatement.setInt(parameterIndex++, idTaiKhoan);
				// Thực thi câu lệnh SQL
				checkInsert = preparedStatement.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		return checkInsert;
	}

}
