/**
 * Copyright(C) 2018 NguyenDuyPhong
 * CommonConstant.java, 18/04/2018 NguyenDuyPhong
 */
package hotspot.utils;
/**
 * 
 * @author duyphong170195
 *
 */
public class CommonConstant {
	/*Đường dẫn kết nối tới các file properties*/
	public static final String DATABASE_PROPERTIES = "database.properties";
	public static final String CONFIG_PROPERTIES = "config.properties";
	public static final String FILE_MESSAGE_ERROR_PROPERTIES = "MessageError.properties";
	public static final String FILE_MESSAGE_SUCCESS_PROPERTIES = "MessageSuccess.properties";
	/*keyName trong file database.properties*/
	public static final String DBURL = "dburl";
	public static final String DBUSER = "dbuser";
	public static final String DBPASSWORD = "dbpassword";
	/*keyName trong file config.properties*/
	public static final String LIMIT_TINHTHANH = "limitTinhThanh";
	public static final String LIMIT_PAGE = "limitPage";
	/*Giá trị mặc định ở DanhSachTinhThanhController*/
	public static final String DEFAULT_VALUE_SEARCH = "";
	public static final String DEFAULT_PAGE = "1";
	/*Các hành động click chức năng ở màn hình trang chủ*/
	public static final String ACTION = "action";
	public static final String ACTION_SEARCH = "search";
	public static final String ACTION_LIST_PAGING = "paging";
	
	public static final String KEY_SEARCH_VALUE= "searchValue";
	/*chức năng add or update*/
	public static final String FUNCTION_ADD_BAI_VIET = "addBaiViet";
	
	//URL jsp
	public static final String URL_TRANG_CHU = "/JSP/trangchu.jsp";
	public static final String URL_DANG_NHAP = "/JSP/dangnhap.jsp";
	public static final String URL_TINH_THANH = "/JSP/tinhthanh.jsp";
	public static final String URL_DANG_KY = "/JSP/dangky.jsp";
	public static final String URL_THONG_BAO = "/JSP/thongbao.jsp";
	public static final String URL_BAI_DA_VIET = "/JSP/baidaviet.jsp";
	public static final String URL_BAI_VIET_DA_LUU = "/JSP/baivietdaluu.jsp";
	public static final String URL_BAI_VIET = "/JSP/baiviet.jsp";
	public static final String URL_XEM_CHI_TIET_BAI_VIET = "/JSP/xemchitietbaiviet.jsp";
	//URL controller
	public static final String URL_CONTROLLER_DANH_SACH_TINH_THANH = "/danhSachTinhThanh.do";
	public static final String URL_CONTROLLER_THONG_BAO = "/message.do";
	public static final String URL_CONTROLLER_DANH_SACH_BAI_VIET_TINH_THANH = "/xemDanhSachBaiVietCuaTinhThanh.do";
	public static final String URL_CONTROLLER_DANH_SACH_BAI_VIET_DA_LUU = "/xemDanhSachBaiVietDaLuu.do";
	public static final String URL_CONTROLLER_DANH_SACH_BAI_DA_VIET = "/danhSachBaiDaViet.do";
	public static final String URL_CONTROLLER_XEM_CHI_TIET_BAI_VIET = "/xemChiTietBaiViet.do";
	/*Các format*/
	public static final String PATTERN_USERNAME = "[a-zA-Z_][a-zA-Z0-9_]+";
	public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String PATTERN_TELEPHONE = "[0-9]+";
	/*Check empty là ERROR1*/
	public static final String ERROR1_USER_NAME = "ERROR1_USER_NAME";
	public static final String ERROR1_PASSWORD = "ERROR1_PASSWORD";
	public static final String ERROR1_AUTHENTICATE_PASSWORD = "ERROR1_AUTHENTICATE_PASSWORD";
	public static final String ERROR1_EMAIL = "ERROR1_EMAIL";
	public static final String ERROR1_TELEPHONE = "ERROR1_TELEPHONE";
	public static final String ERROR1_BI_DANH = "ERROR1_BI_DANH";
	public static final String ERROR_USERNAME_OR_PASSWORD = "ERROR_USERNAME_OR_PASSWORD";
	public static final String ERROR_UPDATE_PASSWORD = "ERROR_UPDATE_PASSWORD";
	
	public static final String ERROR1_MO_TA = "ERROR1_MO_TA";
	public static final String ERROR1_TINH_THANH = "ERROR1_TINH_THANH";
	public static final String ERROR1_HUYEN_XA = "ERROR1_HUYEN_XA";
	public static final String ERROR1_TEN_DIA_DIEM = "ERROR1_TEN_DIA_DIEM";
	public static final String ERROR1_HINH_ANH = "ERROR1_HINH_ANH";
	public static final String ERROR1_NOI_DUNG = "ERROR1_NOI_DUNG";
	public static final String ERROR1_TIEU_DE = "ERROR1_TIEU_DE";
	/*Check outsize là ERROR2*/
	public static final String ERROR2_USER_NAME = "ERROR2_USER_NAME";
	public static final String ERROR2_PASSWORD = "ERROR2_PASSWORD";
	/*Check lỗi định dạng là ERROR3*/
	public static final String ERROR3_USER_NAME = "ERROR3_USER_NAME";
	public static final String ERROR3_EMAIL = "ERROR3_EMAIL";
	public static final String ERROR3_TELEPHONE = "ERROR3_TELEPHONE";
	/*Check lỗi tồn tại là ERROR4*/
	public static final String ERROR4_USER_NAME = "ERROR4_USER_NAME";
	public static final String ERROR4_EMAIL = "ERROR4_EMAIL";
	/*Check lỗi ký tự 1 byte là ERROR5*/
	public static final String ERROR5_PASSWORD = "ERROR5_PASSWORD";
	/*Check lỗi mật khẩu và mật khẩu xác nhận không giống nhau là ERROR6*/
	public static final String ERROR6_AUTHENTICATE_PASSWORD = "ERROR6_AUTHENTICATE_PASSWORD";
	/*Check maxlength*/
	public static final String ERROR7_EMAIL = "ERROR7_EMAIL";
	public static final String ERROR7_TELEPHONE = "ERROR7_TELEPHONE";
	public static final String ERROR7_BI_DANH = "ERROR7_BI_DANH";
	
	/*MessageSuccess */
	public static final String MSG01_DANG_KY = "MSG01_DANG_KY";
	public static final String MSG01_ERROR_DANG_KY = "MSG01_ERROR_DANG_KY";
	public static final String MSG01_MAT_KHAU = "MSG01_MAT_KHAU";
	public static final String MSG01_ERROR_MAT_KHAU= "MSG01_ERROR_MAT_KHAU";
	public static final String MSG01_INSERT_BAI_VIET = "MSG01_INSERT_BAI_VIET";
	public static final String MSG01_ERROR_INSERT_BAI_VIET = "MSG01_ERROR_INSERT_BAI_VIET";
	public static final String MSG01_UPDATE_BAI_VIET = "MSG01_UPDATE_BAI_VIET";
	public static final String MSG01_ERROR_UPDATE_BAI_VIET = "MSG01_ERROR_UPDATE_BAI_VIET";
	public static final String MSG01_DELETE_BAI_VIET = "MSG01_DELETE_BAI_VIET";
	public static final String MSG01_ERROR_DELETE_BAI_VIET = "MSG01_ERROR_DELETE_BAI_VIET";
	public static final String MSG01_INSERT_LUU_BAI_VIET ="MSG01_INSERT_LUU_BAI_VIET";
	public static final String MSG01_ERROR_INSERT_LUU_BAI_VIET = "MSG01_ERROR_INSERT_LUU_BAI_VIET";

	
	public static final String MSG02_ERROR_TAI_KHOAN = "MSG02_ERROR_TAI_KHOAN";
	public static final String MSG02_ERROR_TINH_THANH = "MSG02_ERROR_TINH_THANH";
	public static final String MSG02_ERROR_BAI_VIET = "MSG02_ERROR_BAI_VIET";
	public static final String MSG02_LUU_BAI_VIET = "MSG02_LUU_BAI_VIET";
	/*Thông báo lỗi về cơ sở dữ liệu*/
	public static final String ERROR_DATABASE = "ERROR_DATABASE";
	
	
}
