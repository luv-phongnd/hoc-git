/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblNhanXetBaiVietLogic.java 12/05/2018 NguyenDuyPhong
 */
package hotspot.logic.impl;

import java.sql.SQLException;
import java.util.List;

import hotspot.dao.TblNhanXetBaiVietDao;
import hotspot.dao.impl.TblNhanXetBaiVietDaoImpl;
import hotspot.logic.TblNhanXetBaiVietLogic;
import hotspot.model.NhanXetInfor;
/**
 * xử lý logic thao tác với bảng tbl_Nhan_xet_bai_viet
 * @author duyphong170195
 *
 */
public class TblNhanXetBaiVietLogicImpl implements TblNhanXetBaiVietLogic{

	/* (non-Javadoc)
	 * @see hotspot.logic.TblNhanXetBaiVietLogic#getAllComment(int)
	 */
	@Override
	public List<NhanXetInfor> getAllComment(int idBaiViet) throws SQLException, ClassNotFoundException {
		TblNhanXetBaiVietDao tblNhanXetBaiVietDao = new TblNhanXetBaiVietDaoImpl();
		return tblNhanXetBaiVietDao.getAllComment(idBaiViet);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblNhanXetBaiVietLogic#insertComment(int, int, java.lang.String)
	 */
	@Override
	public boolean insertComment(int idBaiViet, int idTaiKhoan, String nhanXet)
			throws SQLException, ClassNotFoundException {
		TblNhanXetBaiVietDao tblNhanXetBaiVietDao = new TblNhanXetBaiVietDaoImpl();
		return tblNhanXetBaiVietDao.insertComment(idBaiViet, idTaiKhoan, nhanXet);
	}

}
