/**
 * Copyright(C) 2018 NguyenDuyPhong
 * Common.java, 18/04/2018 NguyenDuyPhong
 */

package hotspot.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import hotspot.properties.ConfigProperties;

/**
 * Lớp này dùng để tái sử dụng những phương thức.
 * 
 * @author NguyenDuyPhong
 *
 */

public class Common {
	/**
	 * replace các kí tự đặc biệt trong giá trị search SQL
	 * @param data : chuỗi ban đầu
	 * @return : chuỗi đã được replace
	 */
	public static String replaceWildcard(String data){
		data =	//data.replace("\"", "\"").
				data.replace("\\", "\\\\").
					replace("_", "\\_").
					replace("%", "\\%");
		return data;
	}
	
	/**
	 * Chuyển đối kiểu một chuỗi số sang kiểu số nguyên
	 * 
	 * @param sNumber:một
	 *            chuôi số nguyên
	 * @return Nếu chuỗi là một chuỗi số thì trả về số nguyên, ngược lại trả về
	 *         0
	 */
	public static int toInteger(String sNumber) {
		int value = 0;
		try {
			value = Integer.parseInt(sNumber.trim());
		} catch (NumberFormatException e) {
			value = 0;
		}catch (NullPointerException e){
			value = 0;
		}
		
		return value;
	}
	
	/**
	 *  Lấy số lượng bản ghi hiển thị trên một trang.
	 * @return số lượng bản ghi trên một trang
	 */
	public static int getLimitTinhThanh(){
		return Common.toInteger(ConfigProperties.getData(CommonConstant.LIMIT_TINHTHANH));
	}
	
	/**
	 * Lấy số trang cho phép hiển thị trên một trang.
	 * @return
	 */
	public static int getLimitPage(){
		return Common.toInteger(ConfigProperties.getData(CommonConstant.LIMIT_PAGE));
	}
	/**
	 * Lấy tổng số trang
	 * @param totalUser : tổng số user (bảng user innner join với bảng mst_group) 
	 * @param limit : số lượng bản ghi hiển thị trên một trang
	 * @return tổng số trang.
	 */
	public static int getTotalPage(int totalTinhThanh, int limit){
		return (int)Math.ceil((double)totalTinhThanh/limit);
	}
	
	
	
	/**
	 * Lấy danh sách các trang cần hiển thị ở chuỗi paging theo trang hiện tại.
	 * @param totalUser : Số user hiện có
	 * @param limit : số user hiển thị trên 1 trang
	 * @param currentPage : Trang hiện tại
	 * @return list trang theo trang hiện tại
	 */
	public static List<Integer> getListPaging(int totalUser, int limit, int currentPage){
		ArrayList<Integer> listPaging = new ArrayList<Integer>();
		//tính tổng số page
		int totalPage = getTotalPage(totalUser, limit);
		//Số trang muốn hiển thị trong một listPaging
		int limitPage  = Integer.parseInt(ConfigProperties.getData(CommonConstant.LIMIT_PAGE));
		//Lấy trang đầu tiên trong một offset
		int firstPage = getFirstPage(currentPage, limitPage);
		//Lấy trang cuối trong một offset
		int endPage = getEndPage(currentPage, limitPage, totalPage);
		
		for(int i = firstPage ; i <= endPage ; i++){
			listPaging.add(i);
		}
		
		return listPaging;
		
	}
	/**
	 * Lấy trang đầu tiên của listPaging được giới hạn bởi limitPage
	 * @param currentPage :Trang hiện tại
	 * @param limitPage: số trang hiển thị trong listPaging
	 * @return : trang đầu tiên trong listPaging
	 */
	public static int getFirstPage(int currentPage, int limitPage){
		//Lấy ra vị trí cụm mà current page thuộc nó
		int offset = (currentPage - 1)/limitPage;
		//Lấy ra trang đầu tiên
		int firstPage = offset*limitPage +1;
		return firstPage;
	}
	
	/**
	 * Lấy trang cuối của listPaging được giới hạn bởi limitPage
	 * @param currentPage : Trang hiện tại
	 * @param limitPage : Số trang hiển thị
	 * @param totalPage : tổng số trang của tất cả user
	 * @return : trang cuối thuộc một offset theo trang hiện tại
	 */
	public static int getEndPage(int currentPage, int limitPage, int totalPage){
		int firstPage = getFirstPage(currentPage, limitPage);
		int endPage = (firstPage + limitPage) - 1;
		if(endPage > totalPage){
			endPage = totalPage;
		}
		return endPage;
	}
	
	/**
	 * Lấy vị trí offset user theo trang hiện tại
	 * @param currentPage : 
	 * @param limit
	 * @return vị trí offset theo trang
	 */
	public static int getOffset(int currentPage, int limit){
		return (currentPage-1)*limit;
	}
	
	/**
	 * Kiểm tra một chuỗi xem có rỗng hay không ?
	 * @param data:Chuỗi được kiểm tra.
	 * @return true : Nếu chuỗi rỗng.
	 * 			false: Nếu chuỗi không rỗng.
	 */
	public static boolean isEmpty(String data){
		if(data.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Kiểm tra 1 chuỗi có nằm ngoài đoạn hay không ? (range1,range2)
	 * 
	 * @param xau:Một chuỗi .
	 * @param range1:Giá trị nhỏ nhất trong đoạn.
	 * @param range2:Giá trị lớn nhất trong đoạn.
	 * @return true nếu chuỗi số nằm trong đoạn.
	 * 			false nếu chuỗi số không nằm trong đoạn.
	 */
	public static boolean checkOutsize(String xau, int range1, int range2) {
		if (!isEmpty(xau)) {
			int lengthString = xau.trim().length();
			if( lengthString >= range1 && lengthString <= range2){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Kiểm tra một xâu theo một khuôn mẫu
	 * @param xau : xâu được kiểm tra
	 * @param pattern : khuôn mẫu
	 * @return 
	 * true: nếu xâu đúng theo như khuôn mẫu
	 * false:nếu xâu không đúng khuôn mẫu
	 */
	public static boolean checkFormatByPattern(String xau, String pattern){
		if(xau.matches(pattern)){
			return true;
		}	
		return false;
	}
	
	/**
	 * Kiểm tra ký tự byte cho password
	 * @param password chuỗi cần kiểm tra
	 * @return true nếu là ký tự 1 byte, false nếu không phải là ký tự 1 byte
	 */
	public static boolean checkOneBytePassword(String password) {
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (c < 0 || c > 255) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Kiểm tra độ dài một xâu có vượt độ dài cho phép hay không ?
	 * 
	 * @param xau
	 *            này sẽ được kiểm tra.
	 * @param maxLength:Độ
	 *            dài cho phép.
	 * @return true nếu đồ dài xâu nhỏ hơn hoặc bằng độ dài cho phép, false nếu
	 *         độ dài xâu lớn hơn độ dài cho phép
	 */
	public static boolean maxLength(String xau, int maxLength) {
		if (xau.trim().length() <= maxLength) {
			return true;
		}
		return false;
	}
	
	/**
	 * Lấy Date khi có dữ liệu năm , ngày , tháng
	 * @param year : Năm 
	 * @param month :Tháng
	 * @param day : Ngày
	 * @return : Date với năm tháng ngày
	 */
	public static Date getDate(int year, int month, int day){
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
		Calendar calendar = new GregorianCalendar(year, month -1, day);
		return calendar.getTime();
	}
	
	/**
	 * Lấy năm hiện tại
	 * @return : năm hiện tại
	 */
	public static int getYearNow(){
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		return currentYear;
	}
	
	/**
	 * Lấy tháng hiện tại
	 * @return : tháng hiện tại
	 */
	public static int getMonthNow(){
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		
		return currentMonth;
	}
	
	/**
	 * Lấy ngày hiện tại
	 * @return : ngày hiện tại
	 */
	public static int getDayNow(){
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		return currentDay;
	}
	
	/**
	 * chuyển đổi kiểu util.date sang sql.date
	 * @param date
	 * @return
	 */
	public static java.sql.Date convertDateUtilToDateSql(Date date){
		if(date != null){
			java.sql.Date dateSql=new java.sql.Date( date.getTime());
			return dateSql;
		}
		return null;
	}
}
