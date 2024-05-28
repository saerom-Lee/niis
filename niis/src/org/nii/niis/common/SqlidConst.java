package org.nii.niis.common;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.common
* @FileName : SqlidConst.java 
* @Date : 2016. 12. 19.
* @description : 
* </pre>
*/
public final class SqlidConst {

	/**
	 * 공통 코드 조회
	 */
	public static final String COMMON_DETAIL_CODE 				= "mainDAO.getCommonCode";
	/**
	 * 항공사진 성과 목록 사업년도 코드
	 */
	public static final String AIR_LISTSEARCH_CODE_YEAR 		= "listSearchDAO.getAirYearCode";
	/**
	 * 항공사진 성과 목록 사업지구 코드
	 */
	public static final String AIR_LISTSEARCH_CODE_ZONECODE 	= "listSearchDAO.getAirZoneCode";
	/**
	 * 항공사진 성과 목록
	 */
	public static final String AIR_LISTSEARCH_CODE_PHCOURSE 	= "listSearchDAO.getAirPhCourseCode";
	/**
	 * 항공사진 성과 목록
	 */
	public static final String AIR_LISTSEARCH_CODE_PHNUM 		= "listSearchDAO.getAirPhNumCode";
	/**
	 * 항공사진 성과 목록
	 */
	public static final String AIR_LISTSEARCH_CODE_RESOLUTION 	= "listSearchDAO.getAirResolutionCode";
	
	/**
	 * 항공사진(해방전후) 성과 목록 사업년도 코드
	 */
	public static final String AIRLIB_LISTSEARCH_CODE_YEAR 		= "listSearchDAO.getAirLibYearCode";
	/**
	 * 항공사진(해방전후) 성과 목록 사업지구 코드
	 */
	public static final String AIRLIB_LISTSEARCH_CODE_ZONECODE 	= "listSearchDAO.getAirLibZoneCode";
	/**
	 * 항공사진(해방전후) 성과 목록
	 */
	public static final String AIRLIB_LISTSEARCH_CODE_PHCOURSE 	= "listSearchDAO.getAirLibPhCourseCode";
	/**
	 * 항공사진 성과 목록
	 */
	public static final String AIRLIB_LISTSEARCH_CODE_PHNUM 		= "listSearchDAO.getAirLibPhNumCode";
	/**
	 * 항공사진 성과 목록
	 */
	public static final String AIRLIB_LISTSEARCH_CODE_RESOLUTION 	= "listSearchDAO.getAirLibResolutionCode";
	
	
	
	/**
	 * 수치표고 성과 목록 사업년도 코드
	 */
	public static final String DEM_LISTSEARCH_CODE_YEAR 		= "listSearchDAO.getDemYearCode";
	/**
	 * 수치표고 성과 목록 사업지구 코드
	 */
	public static final String DEM_LISTSEARCH_CODE_ZONECODE 	= "listSearchDAO.getDemZoneCode";
	/**
	 * 수치표고 성과 목록
	 */
	public static final String DEM_LISTSEARCH_CODE_MAP5000NUM 	= "listSearchDAO.getDemMap5000NumCode";
	/**
	 * 수치표고 성과 목록 해상도 코드
	 */
	public static final String DEM_LISTSEARCH_CODE_GRIDINT 		= "listSearchDAO.getDemGridIntCode";
	
	/**
	 * 정사영상 성과 목록 사업년도 코드
	 */
	public static final String ORT_LISTSEARCH_CODE_YEAR 		= "listSearchDAO.getOrtYearCode";
	/**
	 * 정사영상 성과 목록 사업지구 코드
	 */
	public static final String ORT_LISTSEARCH_CODE_ZONECODE 	= "listSearchDAO.getOrtZoneCode";
	/**
	 * 정사영상 성과 목록
	 */
	public static final String ORT_LISTSEARCH_CODE_MAP5000NUM 	= "listSearchDAO.getOrtMap5000NumCode";
	/**
	 * 정사영상 성과 목록 해상도 코드
	 */
	public static final String ORT_LISTSEARCH_CODE_GTYPDST 		= "listSearchDAO.getOrtGtypDstCode";
	/**
	 * 사업지구 사업년도 코드
	 */
	public static final String MAP_LISTSEARCH_CODE_YEAR 		= "listSearchDAO.getAirYearCode";
	/**
	 * 사업지구 사업종류 코드
	 */
	//public static final String MAP_LISTSEARCH_CODE_ZONECODE 		= "smartMapDAO.getZoneType";
	public static final String MAP_LISTSEARCH_CODE_ZONECODE 		= "managementDAO.getZoneType";
	/**
	 * 사업지구 목록
	 */
	public static final String MAP_LISTSEARCH_CODE_ZONELIST 		= "smartMapDAO.getZoneList";
	
	
	/**
	 * AT 성과 목록 사업연도 코드
	 * nii_listSearch_oracle.xml
	 */
	public static final String AT_LISTSEARCH_CODE_YEAR 				= "listSearchDAO.getAtYearCode";
	/**
	 * AT 성과 목록 사업지구 코드
	 */
	public static final String AT_LISTSEARCH_CODE_ZONECODE 			= "listSearchDAO.getAtZoneCode";
	/**
	 * AT 성과 목록 성과종류 코드
	 */
	public static final String AT_LISTSEARCH_CODE_IMAGECODE 		= "listSearchDAO.getDtsImageNamList";
	
	
}
