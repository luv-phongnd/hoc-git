/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblNhanXetBaiVietDaoImpl.java 12/05/2018 NguyenDuyPhong
 */
package hotspot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotspot.dao.TblNhanXetBaiVietDao;
import hotspot.model.NhanXetInfor;

/**
 * Xử lý thao tác với bảng tbl_Nhan_xet_bai_viet
 * @author duyphong170195
 *
 */
public class TblNhanXetBaiVietDaoImpl extends BaseDaoImpl implements TblNhanXetBaiVietDao{

	/* (non-Javadoc)
	 * @see hotspot.dao.TblNhanXetBaiVietDao#getAllComment(int)
	 */
	@Override
	public List<NhanXetInfor> getAllComment(int idBaiViet) throws SQLException, ClassNotFoundException{
		//Phiên làm việc giữa ứng dụng và database
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		ArrayList<NhanXetInfor> listNhanXet = new ArrayList<NhanXetInfor>();
		try{
			//Mở kết nối tới database
			conn = openConnection();
			if(conn != null){
				int parameterIndex = 1;
				//Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("SELECT taikhoan.bi_danh, nhan_xet ");
				sqlb.append("FROM tbl_nhan_xet_bai_viet nhanxet ");
				sqlb.append("INNER JOIN tbl_tai_khoan taikhoan ON nhanxet.id_tai_khoan = taikhoan.id_tai_khoan ");
				sqlb.append("WHERE id_bai_viet = ? ");
				
				//Truyền câu lệnh sql đến database
				preStatement = conn.prepareStatement(sqlb.toString());
				//truyền tham số cho câu lệnh sql
				preStatement.setInt(parameterIndex++, idBaiViet);
				//Gán các bản ghi vào result_set
				ResultSet rs = preStatement.executeQuery();
				//Nếu có bản ghi
				while(rs.next()){
					NhanXetInfor nhanXetInfor = new NhanXetInfor();
					nhanXetInfor.setBiDanh(rs.getString("bi_danh"));
					nhanXetInfor.setNhanXet(rs.getString("nhan_xet"));
					listNhanXet.add(nhanXetInfor);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		return listNhanXet;
	}


	/* (non-Javadoc)
	 * @see hotspot.dao.TblNhanXetBaiVietDao#insertComment(int, int, java.lang.String)
	 */
	@Override
	public boolean insertComment(int idBaiViet,int idTaiKhoan, String nhanXet) throws SQLException, ClassNotFoundException {
		//Phiên làm việc giữa ứng dụng và database
		Connection conn = null;
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh sql
		PreparedStatement preStatement = null;
		//Biến kiểm tra xem post comment có thành công hay không.
		boolean checkComment = false;
		try{
			//Mở kết nối tới database
			conn = openConnection();
			if(conn != null){
				int parameterIndex = 1;
				//Câu lệnh SQL
				StringBuilder sqlb = new StringBuilder();
				sqlb.append("INSERT INTO tbl_nhan_xet_bai_viet (id_bai_viet, id_tai_khoan, nhan_xet) ");
				sqlb.append("VALUES (?,?,?) ");
				
				//Gửi câu lệnh SQL tới database
				preStatement = conn.prepareStatement(sqlb.toString());
				//Truyền tham số cho câu lệnh SQL
				preStatement.setInt(parameterIndex++, idBaiViet);
				preStatement.setInt(parameterIndex++, idTaiKhoan);
				preStatement.setString(parameterIndex++, nhanXet);
				
				//Thực thi insert
				checkComment =  preStatement.executeUpdate() > 0;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			closeConnection();
		}
		//Trả về true comment thành công
		//Trả về false comment thất bại
		return checkComment;
	}

	
	
}
