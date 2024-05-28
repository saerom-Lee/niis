<%@ page import="com.gpki.gpkiapi.exception.GpkiApiException"%>
<%@ page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="com.gpki.gpkiapi.cert.*" %>
<%@ page import="com.gpki.gpkiapi.cms.*" %>
<%@ page import="com.gpki.gpkiapi.util.*" %>
<%@ page import="com.dsjdf.jdf.Logger" %>
<%@ page import="java.net.URLDecoder" %>
<%@ include file="./gpkisecureweb.jsp"%>
<%
	X509Certificate cert      = null; 
	byte[]  signData          = null;
	byte[]  privatekey_random = null;
	String  signType          = null;
	String  subDN             = null;
	String  queryString       = "";
	boolean checkPrivateNum   = false;
	
	java.math.BigInteger b = new java.math.BigInteger("-1".getBytes()); 

	int message_type =  gpkirequest.getRequestMessageType();

	if( message_type == gpkirequest.ENCRYPTED_SIGNDATA || message_type == gpkirequest.LOGIN_ENVELOP_SIGN_DATA ||
		message_type == gpkirequest.ENVELOP_SIGNDATA || message_type == gpkirequest.SIGNED_DATA){

		cert              = gpkirequest.getSignerCert(); 
		subDN             = cert.getSubjectDN();
		b                 = cert.getSerialNumber();
		signData          = gpkirequest.getSignedData();
		privatekey_random = gpkirequest.getSignerRValue();
		signType          = gpkirequest.getSignType();
	}

	queryString = gpkirequest.getQueryString();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/gpkisecureweb/client/jquery-1.7.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<script type="text/javascript">
	$(document).ready(function(){checkedGpkiUser();});
	</script>
</head>
<body>
	<script type="text/javascript">
	function checkedGpkiUser(){
		
		X509Certificate cert      = null; 
		java.math.BigInteger b = new java.math.BigInteger("-1".getBytes()); 

		int message_type =  gpkirequest.getRequestMessageType();

		if( message_type == gpkirequest.ENCRYPTED_SIGNDATA || message_type == gpkirequest.LOGIN_ENVELOP_SIGN_DATA ||
			message_type == gpkirequest.ENVELOP_SIGNDATA || message_type == gpkirequest.SIGNED_DATA){

			cert              = gpkirequest.getSignerCert(); 
			b                 = cert.getSerialNumber();
		}

		queryString = gpkirequest.getQueryString();
		
		var data = {
				"userpw" : b
		}
		
		ajaxCallJson("/login/loginCheck.do", data, function(result){
			
			if (result.state == "1"){
				location.href="/niis/main/main.do";
			}else if (result.state == "4"){
				ajaxCallPop("/login/regReject.do", {"userId": result.userId, "cause" : result.cause}, "membershipPending");
			}else if (result.state == "5"){
				ajaxCallPop("/login/regWait.do", "", "membershipWait");
			}else if (result.state == "6"){
				alert("사용이 중지된 아이디입니다.");
				$("#userId").focus();
				$("#userPw").val("");
			}else{
				//화면에서 로그인 실패 메시지 표출 필요할듯
				//window.console.log('로그인 실패!' + result.state);
				//alert("시스템에 연결할 수 없습니다. 잠시후 다시 시도해 주세요. \n지속적으로 문제 발생시 관리자에게 문의해 주세요.");
				$("#userPw").focus();
			}
		}, function(result){
			window.console.log('로그인 실패!' + result.state);
			alert("시스템에 연결할 수 없습니다. 잠시후 다시 시도해 주세요. \n지속적으로 문제 발생시 관리자에게 문의해 주세요.");
			$("#userPw").focus();
		});
	}
	</script>
</body>
</html>
