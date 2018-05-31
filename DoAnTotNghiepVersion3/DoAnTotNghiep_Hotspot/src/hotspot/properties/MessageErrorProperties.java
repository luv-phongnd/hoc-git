/**
 * Copyright(C) 2018 Luvina software
 * MessageErrorProperties.java 12/03/2018 NguyenDuyPhong
 */
package hotspot.properties;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import hotspot.utils.CommonConstant;
/**
 * Dùng để thao tác với file MessageError.Properties
 * @author NguyenDuyPhong
 *
 */
public class MessageErrorProperties {
	private static Properties prop;
	private static Map<String,String> hashmap;
	//Khởi tạo 1 lần duy nhất khi lớp được gọi cho tới khi tắt chương trình
	static{
		KetNoiProperties conProperty = new KetNoiProperties();
		//Kết nối tới file config_properties
		prop=conProperty.connectFileProperty(CommonConstant.FILE_MESSAGE_ERROR_PROPERTIES);
		//Khởi tạo hashMap chứa giá trị key,value trong file config.properties
		hashmap = new HashMap<String,String>();
		//Enum chứa các key
		Enumeration<?> e = prop.propertyNames();
		//Duyệt enum 
		while(e.hasMoreElements()){
			//Lấy giá trị từ enum
			String key = (String) e.nextElement();
			//Gán giá trị key,value vào hashmap
			hashmap.put(key, prop.getProperty(key,"UTF-8"));
		}
	}
	
	/**
	 * Lấy dữ liệu từ file MessageErrror.Properties
	 * @param propertyKey : tên key trong file MessageErrror.Properties.
	 * @return giá trị theo propertyKey
	 */
	public static String getData(String hashKey){
		//Lấy value từ Key
		String value = hashmap.get(hashKey);
		//Trả về Value
		return value;
	}
}
