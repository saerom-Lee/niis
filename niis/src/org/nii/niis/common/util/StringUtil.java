package org.nii.niis.common.util;

public class StringUtil {
	
	/**
	 * Object 타입 null check
	 * @param obj
	 * @return boolean
	 */
	public static boolean isEmpty(Object obj){
    	boolean b = false;
    	
    	if(null == obj){
    		return true;
    	}
    	return b;
    }
	public static boolean isNotEmpty(Object obj){
    	return !isEmpty(obj);
    }
	
	/**
	 * String 타입 null check
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str){
		boolean b = false;
    	
    	if(null == str || "".equals(str)){
    		return true;
    	}
    	return b;
    }
	public static boolean isNotEmpty(String str){
    	return !isEmpty(str);
    }
	
	/**
	 * null일 결우 "0" 반환
	 * @param str
	 * @return String
	 */
	public static String nullToZero(String str){
		if(null == str || "".equals(str)){
			str = "0";
    	}
		return str;
	}

	/**
	 * String 타입 null일 경우 지정값 반환
	 * @param str
	 * @param val
	 * @return String
	 */
	public static String nvl(String str, String val) {
		if(isEmpty(str)){
			return val;
		}
		return str;
	}
	
	/**
	 * String 타입을 int형으로 변환(오류시 지정값 반환)
	 * @param str
	 * @param val
	 * @return int
	 */
	public static int parseInt(String str, int val) {
		int x;
		try{
			x = Integer.parseInt(str);
		}catch(NumberFormatException e){
			return val;
		}
		return x;
	}
	
	/**
	 * String 타입을 int형으로 변환(오류시 0 반환)
	 * @param str
	 * @param val
	 * @return int
	 */
	public static int parseInt(String str) {
		return parseInt(str, 0);
	}
}