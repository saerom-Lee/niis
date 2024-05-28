<%@ page import="com.gpki.gpkiapi.exception.GpkiApiException"%>
<%@ page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="com.gpki.gpkiapi.cert.*" %>
<%@ page import="com.gpki.gpkiapi.cms.*" %>
<%@ page import="com.gpki.gpkiapi.util.*" %>
<%@ page import="com.dsjdf.jdf.Logger" %>
<%@ page import="java.net.URLDecoder" %>
<%@ include file="/gpkisecureweb/jsp/gpkisecureweb.jsp"%>
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/commonUtil.js"></script>
</head>
<body>
	<script type="text/javascript">
	function checkedGpkiUser(){
		
		var data = {
				"userpw" : '<%= b%>'
		}
		
		ajaxCallJson("/login/loginCheckGpki.do", data, function(result){
			
			$("#state").val(result.state);
			console.log(result.state);
			if(result.state == "1"){
				location.href="/niis/main/main.do";
				return false;
			}else if(result.state == "2"){
				//location.href="/niis/gpkisecureweb/jsp/register.jsp";
				//return false;
			//반려
			}else if(result.state == "4"){
				$("#loginForm").append('<input type="hidden" id="userId" name="userId" value="' + result.userId + '" />');
				$("#loginForm").append('<input type="hidden" id="cause" name="cause" value="' + result.cause + '" />');
			}
			
			$("#loginForm").submit();
		}, function(result){
			$("#state").val("99");
		});
	}
	
	$(document).ready(function(){checkedGpkiUser();});
	</script>
	<form id="loginForm" action="/niis/login/gpkiLogin.do" method="post">
		<input type="hidden" id="state" name="state" />
	</form>
</body>
</html>
