package hotspot.test;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.Part;

import hotspot.dao.TblLuuBaiVietDao;
import hotspot.dao.impl.TblBaiVietDaoImpl;
import hotspot.dao.impl.TblLuuBaiVietDaoImpl;
import hotspot.dao.impl.TblTaiKhoanDaoImpl;
import hotspot.dao.impl.TblTinhThanhDaoImpl;
import hotspot.entities.TblBaiViet;
import hotspot.logic.TblNhanXetBaiVietLogic;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblNhanXetBaiVietLogicImpl;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.model.NhanXetInfor;
import hotspot.security.EncodingPassword;
import hotspot.validate.Validates;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		TblTinhThanhDaoImpl tblTinhThanhDaoImpl = new TblTinhThanhDaoImpl();
		TblTaiKhoanDaoImpl tblTaiKhoanDaoImpl = new TblTaiKhoanDaoImpl();
		System.out.println(tblTaiKhoanDaoImpl.getTblTaiKhoanbyUserName("duyphong").getMatKhau());
		System.out.println(tblTinhThanhDaoImpl.getTotalTinhThanh("Phú Thọ"));
		
		ArrayList<String> listError = Validates.validateDangNhap("duyphong", "fsdfsd");
		System.out.println(listError.size());
		
		TblBaiVietDaoImpl tblBaiVietDaoImpl = new TblBaiVietDaoImpl();
		//int total = tblBaiVietDaoImpl.getTotalBaiVietCuaTinhThanh(2);
		//System.out.println("total = " + total);
		TblTaiKhoanLogic tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
		System.out.println("check = "+tblTaiKhoanLogic.checkExistedEmail("duyphong1701@gmail.comd"));
		
		System.out.println(EncodingPassword.sha1("night123", "1200"));
		
		TblNhanXetBaiVietLogic tblNhanXetBaiVietLogic = new TblNhanXetBaiVietLogicImpl();
		List<NhanXetInfor> listNhanXet = tblNhanXetBaiVietLogic.getAllComment(3);
		for( NhanXetInfor nhanXetInfor : listNhanXet){
			System.out.println(nhanXetInfor.getBiDanh() + "    " + nhanXetInfor.getNhanXet());
		}
	}
	
}
