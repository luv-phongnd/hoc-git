/**
 * Copyright(C) 2018 NguyenDuyPhong
 * Validates.java, 04/03/2018 NguyenDuyPhong
 */

package hotspot.validate;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import hotspot.dao.TblTaiKhoanDao;
import hotspot.dao.impl.TblTaiKhoanDaoImpl;
import hotspot.entities.TblBaiViet;
import hotspot.entities.TblTaiKhoan;
import hotspot.logic.TblTaiKhoanLogic;
import hotspot.logic.impl.TblTaiKhoanLogicImpl;
import hotspot.properties.MessageErrorProperties;
import hotspot.security.EncodingPassword;
import hotspot.utils.Common;
import hotspot.utils.CommonConstant;
/**
 * Validate các chức năng sau :
 * 1.Đăng nhập
 * 2.Đăng ký tài khoản
 * @author NguyenDuyPhong
 *
 */
public class Validates {
	//Biến thao tác với bảng tbl_tai_khoan
	private TblTaiKhoanLogic tblTaiKhoanLogic;
	//Phương thức khởi dựng
	public Validates(){
		//Khởi tạo đối tượng thao tác với bảng tbl_tai_khoan
		tblTaiKhoanLogic = new TblTaiKhoanLogicImpl();
	}
	
	/**
	 * Dùng để kiểm tra username,password theo các tiêu chí sau: 1.Username,
	 * password đã được nhập chưa ? 2.Username, password có đúng hay không ?
	 * 
	 * @param userName:Tên
	 *            tài khoản mà người dùng đã nhập.
	 * @param password:Mật
	 *            khẩu mà người dùng đã nhập.
	 * @return danh sách các lỗi.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static ArrayList<String> validateDangNhap(String userName, String password) throws ClassNotFoundException, SQLException{
		ArrayList<String> listError = new ArrayList<String>();
		String error = "";
		// Nếu tài khoản hoặc mật khẩu rỗng
		if (Common.isEmpty(userName)) {
			error = MessageErrorProperties.getData(CommonConstant.ERROR1_USER_NAME);
			listError.add(error);
		}
		if (Common.isEmpty(password)) {
			error = MessageErrorProperties.getData(CommonConstant.ERROR1_PASSWORD);
			listError.add(error);
		}
		// Nếu tài khoản và mật khẩu được nhập
		if (listError.size() == 0) {
			TblTaiKhoanDao tblTaiKhoanDao = new TblTaiKhoanDaoImpl();
			TblTaiKhoan tblTaiKhoan = tblTaiKhoanDao.getTblTaiKhoanbyUserName(userName);	
			String userNameDatabase = "";
			String passwordDatabase = "";
			String passwordEncoded = "";
			if(tblTaiKhoan != null){
				// Lấy userName từ database
				userNameDatabase = tblTaiKhoan.getTenTaiKhoan();
				// Lấy mật khẩu từ database
				passwordDatabase = tblTaiKhoan.getMatKhau();
				try {
					// Mã hóa mật khẩu
					passwordEncoded = EncodingPassword.sha1(password, tblTaiKhoan.getSalt());
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
			// Nếu tài khoản hoặc mật khẩu sai
			if (!userNameDatabase.equals(userName) || !passwordDatabase.equals(passwordEncoded)) {
				error = MessageErrorProperties.getData(CommonConstant.ERROR_USERNAME_OR_PASSWORD);
				listError.add(error);
			}
		}
		// Trả về danh sách lỗi validateLogin
		return listError;
	}
	
	/**
	 * Kiểm tra xem đối tượng TblTaiKhoan có hợp lệ để thực hiện đăng ký hay không ?
	 * @param tblTaiKhoan : đối tượng được check
	 * @return danh sách lỗi thông báo nếu không hợp lệ
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<String> validateDangKy(TblTaiKhoan tblTaiKhoan) throws ClassNotFoundException, SQLException{
		ArrayList<String> listError = new ArrayList<String>();
		//Trường hợp lỗi cho tên tài khoản
		listError.addAll(validateUserName(tblTaiKhoan.getTenTaiKhoan()));
		//Trường hợp lỗi cho mật khẩu
		listError.addAll(validatePassword(tblTaiKhoan.getMatKhau()));
		//Trường hợp lỗi cho mật khẩu xác nhận
		listError.addAll(validateAuthenticatedPassword(tblTaiKhoan.getMatKhau(), tblTaiKhoan.getMatKhauXacNhan()));
		//Trường hợp lỗi cho email
		listError.addAll(validateEmail(tblTaiKhoan.getEmail()));
		//Trường hợp lỗi cho số điện thoại
		listError.addAll(validateTelephone(tblTaiKhoan.getSoDienThoai()));
		//Trường hợp lỗi cho email
		listError.addAll(validateBiDanh(tblTaiKhoan.getBiDanh()));
		
		return listError;
	}
	/**
	 * 
	 * @param tblTaiKhoan : đôi tượng được update
	 * @param matKhau :  mật khẩu được so sanh với mật khẩu của đối tượng tblTaiKhoan
	 * @param matKhauMoi : mật khẩu mới
	 * @parama matKhauXacNhan: mật khẩu xác nhận
	 * @return mảng lỗi ( nếu có)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 */
	public List<String> validateUpdateMatKhau(TblTaiKhoan tblTaiKhoan, String matKhauCu, String matKhauMoi, String matKhauXacNhan) throws NoSuchAlgorithmException{
		ArrayList<String> listError = new ArrayList<String>();
		//validate cho trường hợp update mật khẩu
		listError.addAll(validateMatKhauInDatabase(tblTaiKhoan, matKhauCu));
		//Validate cho mật khẩu mới.
		listError.addAll(validatePassword(matKhauMoi));
		//Validate cho mật khẩu xác nhận
		listError.addAll(validateAuthenticatedPassword(matKhauMoi, matKhauXacNhan));
		//Trả về một mảng lỗi
		return listError;
	}
	
	/**
	 * Các trường hợp bắt lỗi của tên tài khoản
	 * @param userName : thuộc tính ten_tai_khoan trong bảng tbl_tai_khoan
	 * @return Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng không có phần tử.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> validateUserName(String userName) throws ClassNotFoundException, SQLException{
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi cho userName*/
		if(Common.isEmpty(userName)){ //Kiểm tra đã nhập  userName chưa  ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_USER_NAME));
		
		}else if(Common.checkOutsize(userName, 4, 15)){ //Kiểm tra  độ dài của userName có hợp lệ hay không ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR2_USER_NAME));
		
		}else if(!Common.checkFormatByPattern(userName, CommonConstant.PATTERN_USERNAME)){ //Kiểm tra định dạng userName hợp lệ hay không ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR_USERNAME_OR_PASSWORD));
		
		}else if(tblTaiKhoanLogic.checkExistedTenTaiKhoan(userName)){ // Kiểm tra userName đã tồn tại trong cơ sở dữ liệu hay chưa ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR4_USER_NAME));
		}
		//Nếu có lỗi thì trả về một chuỗi lỗi
		// Nếu không có lỗi thì trả về rỗng.
		return listError;
	}
	
	/**
	 * Các trường hợp lỗi của password
	 * @param password : thuộc tính trong bảng tbl_tai_khoan
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng.
	 */
	public ArrayList<String> validatePassword(String password){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của Password*/
		if(Common.isEmpty(password)){ //Kiểm tra dã nhập password hay chưa ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_PASSWORD));
		
		}else if(Common.checkOutsize(password, 5, 15)){ // Kiểm tra độ dài của password có hợp lệ không ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR2_PASSWORD));
		
		}else if(!Common.checkOneBytePassword(password)){ //Kiểm tra check1byte cho password
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR5_PASSWORD));
		
		}
		//Nếu có lỗi thì trả về một chuỗi lỗi
		//Nếu không có lỗi thì trả về rỗng.
		return listError;
	}
	
	/**
	 * Các trường hợp lỗi mật khẩu xác nhận
	 * @param password : thuộc tính trong bảng tbl_tai_khoan
	 * @param authenticatedPassword : Mật khẩu xác nhận trong form đăng ký
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về rỗng.
	 */
	public ArrayList<String> validateAuthenticatedPassword(String password, String authenticatedPassword){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của mật khẩu xác nhận*/
		if(!Common.isEmpty(password)){
			if(Common.isEmpty(authenticatedPassword)){ //Kiểm tra mật khẩu xác nhận đã nhập chưa ?
				listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_AUTHENTICATE_PASSWORD));
		
			}else if(!password.equals(authenticatedPassword)){ //Kiểm tra mật khẩu xác nhận và mật khẩu có giống nhau không ?
				listError.add(MessageErrorProperties.getData(CommonConstant.ERROR6_AUTHENTICATE_PASSWORD));
			}
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		//Nếu không có lỗi thì trả về rỗng.
		return listError;
	}
	
	/**
	 * Các trường hợp lỗi email
	 * @param email : thuộc tính trong bảng tbl_tai_khoan
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng. 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<String> validateEmail(String email) throws ClassNotFoundException, SQLException{
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của Email*/
		if(Common.isEmpty(email)){ //Kiểm tra đã nhập Email chưa ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_EMAIL));
			
		}else if(!Common.maxLength(email, 100)){ //Kiểm tra độ dài của Email có hợp lệ hay không ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR7_EMAIL));
		
		}else if(!Common.checkFormatByPattern(email, CommonConstant.PATTERN_EMAIL)){ // Kiểm tra định dạng email có hợp lệ hay không ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR3_EMAIL));
		
		}else if(tblTaiKhoanLogic.checkExistedEmail(email)){  //Kiểm tra email đã tồn tài trong cơ sở dữ liệu hay chưa ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR4_EMAIL));
		
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		// Nếu không có lỗi thì trả về mảng rỗng.
		return listError;
	}
	
	/**
	 * Các trường hợp lỗi của telephone
	 * @param telephone : thuộc tính trong bảng tbl_user
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả về mảng rỗng.
	 */
	public ArrayList<String> validateTelephone(String telephone){
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của telephone*/
		if(Common.isEmpty(telephone)){ //Kiểm tra đã nhập số điện thoại chưa ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_TELEPHONE));
		
		}else if(!Common.maxLength(telephone, 14)){ //Kiểm tra độ dài số điện thoại có hợp lệ hay không ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR7_TELEPHONE));
		
		}else if(!Common.checkFormatByPattern(telephone, CommonConstant.PATTERN_TELEPHONE)){ //Kiểm tra định dạng số điện thoại có hợp lệ hay không ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR3_TELEPHONE));
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		//Nếu không có lỗi thì trả về mảng rỗng.
		return listError;
	}
	
	/**
	 * Các trường hợp bắt lỗi của bí danh
	 * @param fullName : bí danh của bảng tbl_tai_khoan
	 * @return
	 * Nếu có lỗi thì trả về một mảng lỗi
	 * Nếu không có lỗi thì trả mảng rỗng.
	 */
	public ArrayList<String> validateBiDanh(String biDanh){	
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		/*Trường hợp lỗi của fullName*/
		if(Common.isEmpty(biDanh)){ //Kiểm tra đã nhập fullName chưa ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_BI_DANH));
		
		}else if(!Common.maxLength(biDanh, 20)){ // Kiểm tra độ dài fullName có hợp lệ không ?
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR7_BI_DANH));
		
		}
		//Nếu có lỗi thì trả về một mảng lỗi
		// Nếu không có lỗi thì trả về rỗng.
		return listError;
	}
	/**
	 * so sánh mật khẩu với mật khẩu database có giống nhau hay không ?
	 * @param tblTaiKhoan : tài khoản được so sánh
	 * @param matKhau : mật khẩu được so sánh
	 * @return : mảng lỗi ( nếu có )
	 * @throws NoSuchAlgorithmException 
	 */
	public ArrayList<String> validateMatKhauInDatabase(TblTaiKhoan tblTaiKhoan, String matKhau) throws NoSuchAlgorithmException{
		//mảng lỗi
		ArrayList<String> listError = new ArrayList<>();
		listError.addAll(validatePassword(matKhau));
		//Nếu mật khẩu hợp lệ
		if(listError.size() == 0){
			//Nếu mật khẩu với mật khẩu trong database không giống nhau
			if(!tblTaiKhoan.getMatKhau().equals(EncodingPassword.sha1(matKhau, tblTaiKhoan.getSalt()))){
				listError.add(MessageErrorProperties.getData(CommonConstant.ERROR_UPDATE_PASSWORD));	
			}
		}
		return listError;
	}
	
	
	public List<String> validateBaiViet(TblBaiViet tblBaiViet) {
		ArrayList<String> listError = new ArrayList<String>();
		//Lỗi cho mô tả
		listError.addAll(validateMoTa(tblBaiViet.getMoTa()));
		//Lỗi cho tỉnh thành
		listError.addAll(validateTinhThanh(tblBaiViet.getIdTinhThanh()));
		//lỗi cho huyện xã
		listError.addAll(validateHuyenXa(tblBaiViet.getHuyenXa()));
		//lỗi cho tên địa điểm
		listError.addAll(validateTenDiaDiem(tblBaiViet.getTenDiaDiem()));
		//lỗi cho hình ảnh
		listError.addAll(validateHinhAnh(tblBaiViet.getPathImage()));
		//lỗi cho nội dung
		listError.addAll(validateNoiDung(tblBaiViet.getNoiDung()));
		//Lỗi cho tiêu đề
		listError.addAll(validateTieuDe(tblBaiViet.getTieuDe()));
		return listError;
	}
	
	public ArrayList<String> validateMoTa(String moTa){
		ArrayList<String> listError = new ArrayList<String>();
		if(Common.isEmpty(moTa)){
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_MO_TA));
		}
		return listError;
	}
	
	public ArrayList<String> validateTinhThanh(int idTinhThanh){
		ArrayList<String> listError = new ArrayList<String>();
		if(idTinhThanh == 0){
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_TINH_THANH));
		}
		return listError;
	} 
	
	public ArrayList<String> validateHuyenXa(String huyenXa){
		ArrayList<String> listError = new ArrayList<String>();
		if(Common.isEmpty(huyenXa)){
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_HUYEN_XA));
		}
		return listError;
	}
	
	public ArrayList<String> validateTenDiaDiem(String tenDiaDiem){
		ArrayList<String> listError = new ArrayList<String>();
		if(Common.isEmpty(tenDiaDiem)){
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_TEN_DIA_DIEM));
		}
		return listError;
	}
	
	public ArrayList<String> validateHinhAnh(String pathImage){
		ArrayList<String> listError = new ArrayList<String>();
		if(Common.isEmpty(pathImage)){
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_HINH_ANH));
		}
		return listError;
	}
	
	public ArrayList<String> validateNoiDung(String noiDung){
		ArrayList<String> listError = new ArrayList<String>();
		if(Common.isEmpty(noiDung)){
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_NOI_DUNG));
		}
		return listError;
	}
	
	public ArrayList<String> validateTieuDe(String tieuDe){
		ArrayList<String> listError = new ArrayList<String>();
		if(Common.isEmpty(tieuDe)){
			listError.add(MessageErrorProperties.getData(CommonConstant.ERROR1_TIEU_DE));
		}
		return listError;
	}
}
