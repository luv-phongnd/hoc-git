/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblBaiVietLogicImpl.java 23/04/2018 NguyenDuyPhong
 */
package hotspot.logic.impl;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotspot.dao.BaseDao;
import hotspot.dao.TblBaiVietDao;
import hotspot.dao.TblLuuBaiVietDao;
import hotspot.dao.impl.BaseDaoImpl;
import hotspot.dao.impl.TblBaiVietDaoImpl;
import hotspot.dao.impl.TblLuuBaiVietDaoImpl;
import hotspot.entities.TblBaiViet;
import hotspot.logic.TblBaiVietLogic;
import hotspot.logic.TblTinhThanhLogic;
import hotspot.model.BaiVietInfor;
import hotspot.utils.Common;
/**
 * thực thi phương thức logic liên quan đến bảng tbl_bai_viet trong database
 * @author duyphong170195
 *
 */
public class TblBaiVietLogicImpl implements TblBaiVietLogic{

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#getTotalBaiVietCuaTinhThanh(int, java.lang.String)
	 */
	@Override
	public int getTotalBaiVietCuaTinhThanh(int idTinhThanh, String tenDiaDiem) throws SQLException, ClassNotFoundException {
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		return tblBaiVietDao.getTotalBaiVietCuaTinhThanh(idTinhThanh, Common.replaceWildcard(tenDiaDiem));
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#getListBaiVietCuaTinhThanh(int, java.lang.String)
	 */
	@Override
	public List<BaiVietInfor> getListBaiVietCuaTinhThanh(int idTinhThanh, String tenDiaDiem) throws SQLException, ClassNotFoundException {
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
	 
		return tblBaiVietDao.getListBaiVietCuaTinhThanh(idTinhThanh, Common.replaceWildcard(tenDiaDiem));
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#getImageBaiVietById(int)
	 */
	@Override
	public byte[] getImageBaiVietById(int idBaiViet) throws ClassNotFoundException, SQLException {
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		return tblBaiVietDao.getImageBaiVietById(idBaiViet);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#getTotalBaiVietCuaTaiKhoan(int, java.lang.String)
	 */
	@Override
	public int getTotalBaiVietCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem)
			throws SQLException, ClassNotFoundException {
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		return tblBaiVietDao.getTotalBaiVietCuaTaiKhoan(idTaiKhoan, Common.replaceWildcard(tenDiaDiem));
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#getListBaiVietCuaTaiKhoan(int, java.lang.String)
	 */
	@Override
	public List<BaiVietInfor> getListBaiVietCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem)
			throws SQLException, ClassNotFoundException {
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		return tblBaiVietDao.getListBaiVietCuaTaiKhoan(idTaiKhoan, Common.replaceWildcard(tenDiaDiem));
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#insertTblBaiViet(hotspot.entities.TblBaiViet)
	 */
	@Override
	public boolean insertTblBaiViet(TblBaiViet tblBaiViet)
			throws SQLException, ClassNotFoundException, FileNotFoundException {
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		return tblBaiVietDao.insertTblBaiViet(tblBaiViet);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#getTblBaiVietById(int)
	 */
	@Override
	public TblBaiViet getTblBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException {
		
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		return tblBaiVietDao.getTblBaiVietById(idBaiViet);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#updateTblBaiViet(hotspot.entities.TblBaiViet)
	 */
	@Override
	public boolean updateTblBaiViet(TblBaiViet tblBaiViet)
			throws SQLException, ClassNotFoundException, FileNotFoundException {
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		return tblBaiVietDao.updateTblBaiViet(tblBaiViet);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#deleteBaiViet(int)
	 */
	@Override
	public boolean deleteBaiViet(int idBaiViet) throws SQLException, ClassNotFoundException {
		//Đối tượng thao tác với bảng tbl_bai_viet
		TblBaiVietDaoImpl tblBaiVietDao = new TblBaiVietDaoImpl();
		//Đối tượng thao tác với bảng tbl_luu_bai_viet
		TblLuuBaiVietDaoImpl tblLuuBaiVietDao = new TblLuuBaiVietDaoImpl();
		//Phiên làm việc giữa database và ứng dụng
		Connection conn = null;
		//Đối tượng thao tác với database
		BaseDao baseDao = new BaseDaoImpl();
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		//Biến kiểm tra xem idLuuBaiViet trong bảng tbl_luu_bai_viet được xóa hay chưa
		//Để là true vì nhẽ đâu bài viết này không lưu trong bảng tbl_luu_bai_viet
		boolean checkDeleteLuuBaiViet = true;
		//Biến kiểm tra xem  idbaiViet trong bảng tbl_bai_viet được xóa hay không ?
		boolean checkDeleteBaiViet = false;
		//Biến kiểm tra delete thành công hay không ?
		boolean checkDelete = false;
		try{
			//Mở kết nối tới database
			conn = baseDao.openConnection();
			if(conn != null){
				//Dừng thực thi câu lệnh SQL
				conn.setAutoCommit(false);
				//Biến kiểm tra có tồn tại id bài viết trong bảng tbl_luu_bai_viet hay không ?
				boolean checkExistedIdLuuBaiViet = tblLuuBaiVietDao.checkExistedIdLuuBaiViet(idBaiViet);
				if(checkExistedIdLuuBaiViet){
					//mở kết nối cho đối tượng tblLuuBaiVietDao
					tblLuuBaiVietDao.setConnection(conn);
					//Xóa id bài viết trong bảng tbl_luu_bai_viet
 					checkDeleteLuuBaiViet = tblLuuBaiVietDao.deleteLuuBaiVietByIdBaiViet(idBaiViet);
				}
				//Mở kết nối cho đối tượng tblBaiVietDao
				tblBaiVietDao.setConnection(conn);
				//Xóa bài viết trong bảng tbl_bai_viet
				checkDeleteBaiViet  = tblBaiVietDao.deleteBaiVietById(idBaiViet);
				if(checkDeleteLuuBaiViet && checkDeleteBaiViet){
					//gán biến checkDelete là true
					checkDelete = true;
					//Thực thi câu lệnh SQL
					conn.commit();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			conn.rollback();
			throw e;
		}finally{
			baseDao.closeConnection();
		}
		//Nếu delete thành công trả về true
		//Nếu delete thất bại trả về false
		return checkDelete;
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#checkExistedBaiVietById(int)
	 */
	@Override
	public boolean checkExistedBaiVietById(int idBaiViet) throws SQLException, ClassNotFoundException {
		//Đối tượng thao tác với bảng tbl_bai_viet
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		//Lấy bài viết theo id
		TblBaiViet tblBaiViet = tblBaiVietDao.getTblBaiVietById(idBaiViet);
		//nếu có bài viết
		if(tblBaiViet != null){
			return true;
		}
		//Nếu không có bài viết
		return false;
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#thichBaiVietById(int)
	 */
	@Override
	public boolean thichBaiVietById(int idBaiViet, int idTaiKhoan) throws SQLException, ClassNotFoundException {
		//Đối tượng thao tác với bảng tbl_bai_viet
		TblBaiVietDaoImpl tblBaiVietDao = new TblBaiVietDaoImpl();
		//Phiên làm việc giữa database và ứng dụng
		Connection conn = null;
		//Đối tượng thao tác với database
		BaseDao baseDao = new BaseDaoImpl();
		// Biến chứa câu lệnh SQL đã được biên dịch và thực thi câu lệnh SQL
		PreparedStatement preparedStatement = null;
		//Check delete success
		boolean checkInsertLike = false;
		boolean checkInsertTblThichBaiViet = false;
		try{
			//Mở kết nối tới database
			conn = baseDao.openConnection();
			if(conn !=  null){
				//Dừng thực thi câu lệnh SQL
				conn.setAutoCommit(false);
				tblBaiVietDao.setConnection(conn);
				checkInsertLike = tblBaiVietDao.thichBaiVietById(idBaiViet);
				checkInsertTblThichBaiViet = tblBaiVietDao.insertTblThichBaiViet(idBaiViet, idTaiKhoan);
				
				if(checkInsertLike && checkInsertTblThichBaiViet){
					conn.commit();
					return true;
				}
			}
		}catch(Exception e){
			conn.rollback();
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblBaiVietLogic#getAllLikeIdBaiVietById(int)
	 */
	@Override
	public List<Integer> getAllLikeIdBaiVietById(int idTaiKhoan) throws SQLException, ClassNotFoundException {
		TblBaiVietDao tblBaiVietDao = new TblBaiVietDaoImpl();
		List<Integer> danhSachIdBaiVietDaThichCuaTaiKhoan = tblBaiVietDao.getAllLikeIdBaiVietById(idTaiKhoan);
		return danhSachIdBaiVietDaThichCuaTaiKhoan;
	}
}
