/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblLuuBaiVietLogicImpl.java 27/04/2018 NguyenDuyPhong
 */
package hotspot.logic.impl;

import java.sql.SQLException;
import java.util.List;

import hotspot.dao.TblLuuBaiVietDao;
import hotspot.dao.impl.TblLuuBaiVietDaoImpl;
import hotspot.logic.TblLuuBaiVietLogic;
import hotspot.model.BaiVietInfor;

/**
 * thực thi xử lý logic liên quan đến bảng tbl_luu_bai_viet
 * @author duyphong170195
 *
 */
public class TblLuuBaiVietLogicImpl implements TblLuuBaiVietLogic{

	/* (non-Javadoc)
	 * @see hotspot.logic.TblLuuBaiVietLogic#getListBaiVietDaLuuCuaTaiKhoan(int, java.lang.String)
	 */
	@Override
	public List<BaiVietInfor> getListBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem)
			throws SQLException, ClassNotFoundException {
		TblLuuBaiVietDao tblLuuBaiVietDao = new TblLuuBaiVietDaoImpl();
		return tblLuuBaiVietDao.getListBaiVietDaLuuCuaTaiKhoan(idTaiKhoan, tenDiaDiem);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblLuuBaiVietLogic#getTotalsBaiVietDaLuuCuaTaiKhoan(int, java.lang.String)
	 */
	@Override
	public int getTotalsBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, String tenDiaDiem)
			throws SQLException, ClassNotFoundException {
		TblLuuBaiVietDao tblLuuBaiVietDao = new TblLuuBaiVietDaoImpl();
		return tblLuuBaiVietDao.getTotalsBaiVietDaLuuCuaTaiKhoan(idTaiKhoan, tenDiaDiem);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblLuuBaiVietLogic#insetTblLuuBaiViet(int, int)
	 */
	@Override
	public boolean insetTblLuuBaiViet(int idTaiKhoan, int idBaiViet) throws SQLException, ClassNotFoundException {
		TblLuuBaiVietDao tblLuuBaiVietDao = new TblLuuBaiVietDaoImpl();
		return tblLuuBaiVietDao.insetTblLuuBaiViet(idTaiKhoan, idBaiViet);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblLuuBaiVietLogic#checkExistedIdTaiKhoanIdBaiViet(int, int)
	 */
	@Override
	public boolean checkExistedIdTaiKhoanIdBaiViet(int idTaiKhoan, int idBaiViet)
			throws SQLException, ClassNotFoundException {
		TblLuuBaiVietDao tblLuuBaiVietDao = new TblLuuBaiVietDaoImpl();
		return tblLuuBaiVietDao.checkExistedIdTaiKhoanIdBaiViet(idTaiKhoan, idBaiViet);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblLuuBaiVietLogic#deleteBaiVietDaLuuCuaTaiKhoan(int, int)
	 */
	@Override
	public boolean deleteBaiVietDaLuuCuaTaiKhoan(int idTaiKhoan, int idBaiViet)
			throws SQLException, ClassNotFoundException {
		TblLuuBaiVietDao tblLuuBaiVietDao = new TblLuuBaiVietDaoImpl();
		return tblLuuBaiVietDao.deleteBaiVietDaLuuCuaTaiKhoan(idTaiKhoan, idBaiViet);
	}
	
}
