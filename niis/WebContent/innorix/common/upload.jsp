﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.innorix.transfer.InnorixUpload" %>
<%@ page import="java.util.UUID" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="org.nii.niis.security.web.SecurityController"%>


<%
System.out.println("===========1111111111=================");
String directory = "C:/upload";
// directory += request.getParameter("param");
// System.out.println("디렉토리-----------------"+directory);
int maxPostSize = 2147482624; // bytes
SecurityController securityController = new SecurityController();
ArrayList<String> list = new ArrayList<String>();

/*
    directory
        파일이 저장될 스토리지의 전체경로
        디렉토리 구분은 윈도우, 유닉스 모두 "/" 문자 사용
        윈도우 - C:/storage/path1/path2/data
        유닉스 - /storage/path1/path2/data

    maxPostSize
        한번에 업로드 가능한 최대 데이터 사이즈(bytes)
        최소값 10485760(10MB) ~ 최대값 2147482624(2GB - 1024)
        최대값을 넘는 빅데이터 파일도 내부 처리에 의해 용량 제한없이 전송됨
*/

// uploader 객체선언
InnorixUpload uploader = new InnorixUpload(request, response, maxPostSize, directory);

/*
    전달되는 _action Param 정보
        speedCheck          : 전송속도 측정 (* innoex 제품에서 사용)
        getServerInfo       : 서버정보 확인
        getFileInfo         : 파일정보 확인
        attachFile          : 파일전송 진행
        attachFileCompleted : 파일전송 완료
*/

System.out.println("========== uploader START " + System.currentTimeMillis() + " ==========");
long startTime = System.currentTimeMillis();
String _action          = uploader.getParameter("_action");         // 동작 플래그
String _orig_filename   = uploader.getParameter("_orig_filename");  // 원본 파일명
String _new_filename    = uploader.getParameter("_new_filename");   // 저장 파일명
String _filesize        = uploader.getParameter("_filesize");       // 파일 사이즈
String _start_offset    = uploader.getParameter("_start_offset");   // 파일저장 시작지점
String _end_offset      = uploader.getParameter("_end_offset");     // 파일저장 종료지점
String _filepath        = uploader.getParameter("_filepath");       // 파일 저장경로
String _param1          = uploader.getParameter("_param1");         // 임의정의 GET Param 값
String _param2          = uploader.getParameter("_param2");         // 임의정의 POST Param 값
String _subdir 			= uploader.getParameter("_subdir");			// subdir 전달

// 중복 파일명 덮어쓰기
uploader.setOverwrite(true);

// 저장될 파일명 지정
uploader.setFileName(_orig_filename);

// 파일저장 실행
String _run_retval = uploader.run();

/*
uploader.run(); 리턴값
    0000 정상
    0001 경로 없음
    0002 쓰기권한 없음
    0003 무결성 검사 실패
    1001 디렉토리 생성 실패
    1002 압축해제 실패
*/

// 개별파일 업로드 완료
if (uploader.isUploadDone()) {
	
    System.out.println("========== uploader.isUploadDone() " + System.currentTimeMillis() + " ==========");
    System.out.println("_orig_filename \t = " + _orig_filename);
    System.out.println("_new_filename \t = " + _new_filename);
    System.out.println("_filesize \t = " + _filesize);
    System.out.println("_filepath \t = " + _filepath);
    long endTime = System.currentTimeMillis();
}

if(_action.equals("attachFileCompleted")){
	
}
System.out.println("========== innorix transfer " + System.currentTimeMillis() + " ==========");
System.out.println("_action \t = " + _action);
System.out.println("_run_retval \t = " + _run_retval);
System.out.println("_orig_filename \t = " + _orig_filename);
System.out.println("_new_filename \t = " + _new_filename);
System.out.println("_filesize \t = " + _filesize);
System.out.println("_start_offset \t = " + _start_offset);
System.out.println("_end_offset \t = " + _end_offset);
System.out.println("_filepath \t = " + _filepath);
System.out.println("_param1 \t = " + _param1);
System.out.println("_param2 \t = " + _param2);
System.out.println("_subdir \t = " + _subdir);
System.out.println("=================222222222222222=================");
%>