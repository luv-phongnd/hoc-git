/**
 * Copyright(C) 2018 NguyenDuyPhong
 * TblTinhThanhLogicImpl.java 18/04/2018 NguyenDuyPhong
 */
package hotspot.logic.impl;

import java.sql.SQLException;
import java.util.List;

import hotspot.dao.TblTinhThanhDao;
import hotspot.dao.impl.TblTinhThanhDaoImpl;
import hotspot.entities.TblTinhThanh;
import hotspot.logic.TblTinhThanhLogic;
import hotspot.model.TinhThanhInfor;
import hotspot.utils.Common;
/**
 * class thực thi logic bảng TblTinhThanh;
 * @author duyphong170195
 *
 */
public class TblTinhThanhLogicImpl implements TblTinhThanhLogic{

	/* (non-Javadoc)
	 * @see hotspot.logic.TblTinhThanhLogic#getListUsers(int, int, java.lang.String)
	 */
	@Override
	public List<TinhThanhInfor> getListTinhThanh(int offset, int limit, String tenTinhThanh)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//Khởi tạo đối tượng thao tác với bảng tbl_tinh_thanh
		TblTinhThanhDao tblTinhThanhDaoImpl = new TblTinhThanhDaoImpl();
		//Trả về list danh sách tỉnh thanh
		return tblTinhThanhDaoImpl.getListTinhThanh(offset, limit, Common.replaceWildcard(tenTinhThanh));
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblTinhThanhLogic#getTotalTinhThanh(java.lang.String)
	 */
	@Override
	public int getTotalTinhThanh(String tenTinhThanh) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//Khởi tạo đối tượng thao tác với bảng tbl_tinh_thanh
		TblTinhThanhDao tblTinhThanhDaoImpl = new TblTinhThanhDaoImpl();
		//TRả về tổng số bản ghi theo tên tỉnh thành
		return tblTinhThanhDaoImpl.getTotalTinhThanh(Common.replaceWildcard(tenTinhThanh));
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblTinhThanhLogic#getImageTinhThanhById(int)
	 */
	@Override
	public byte[] getImageTinhThanhById(int idTinhThanh) throws ClassNotFoundException, SQLException {
		//Khởi tạo đối tượng thao tác với bảng tbl_tinh_thanh
		TblTinhThanhDao tblTinhThanhDaoImpl = new TblTinhThanhDaoImpl();
		return tblTinhThanhDaoImpl.getImageTinhThanhById(idTinhThanh);
	}

	/* (non-Javadoc)
	 * @see hotspot.logic.TblTinhThanhLogic#getTinhThanhInforById(int)
	 */
	@Override
	public TinhThanhInfor getTinhThanhInforById(int idTinhThanh) throws ClassNotFoundException, SQLException {
		//Khởi tạo đối tượng thao tác với bảng tbl_tinh_thanh
		TblTinhThanhDao tblTinhThanhDaoImpl = new TblTinhThanhDaoImpl();
		return tblTinhThanhDaoImpl.getTinhThanhInforById(idTinhThanh);
	}

	@Override
	public List<TinhThanhInfor> getAllTinhThanhInfor() throws ClassNotFoundException, SQLException {
		//Khởi tạo đối tượng thao tác với bảng tbl_tinh_thanh
		TblTinhThanhDao tblTinhThanhDaoImpl = new TblTinhThanhDaoImpl();
		return tblTinhThanhDaoImpl.getAllTinhThanhInfor();
	}
	
}
