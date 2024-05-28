<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="java.sql.*, java.io.*, java.net.*, java.util.*" %>
<%@ include file="/gpkisecureweb/jsp/gpkisecureweb.jsp" %>
<%@ page import="com.gpki.servlet.GPKIHttpServletResponse" %>
<%
	String challenge = "";
	String sessionid = "";
	String url = "";
	try{
		challenge = gpkiresponse.getChallenge();
		sessionid = gpkirequest.getSession().getId();
		url = javax.servlet.http.HttpUtils.getRequestURL(request).toString();
		session.setAttribute("currentpage", "/niis/login/gpkiLogin.do");
	}catch(Exception e){
		response.sendRedirect("/niis");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>공간정보 행정망 서비스</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css" />

<script type="text/javascript" src="/niis/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/niis/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/niis/js/jquery.easing.1.3.min.js"></script>
<script type="text/javascript" src="/niis/js/default.js"></script>
<script type="text/javascript" src="/niis/js/common/common.js"></script>
<script type="text/javascript" src="/niis/js/common/commonUtil.js"></script>

<script type="text/javascript" src="/niis/gpkisecureweb/client/GPKIWeb/js/GPKIWeb_Config.js"></script>
<script type="text/javascript" src="/niis/gpkisecureweb/client/GPKIWeb/js/ext/GPKI_Config.js"></script>
<script type="text/javascript" src="/niis/gpkisecureweb/client/GPKIWeb/js/ext/jquery.blockUI.js"></script>
<script type="text/javascript" src="/niis/gpkisecureweb/client/GPKIWeb/js/ext/json2.js"></script>

<script type="text/javascript" src="/niis/gpkisecureweb/client/gpkijs_1.2.1.3.min.js" id="DSgpkijs"></script>
<script type="text/javascript" src="/niis/gpkisecureweb/client/GenerateContent.js" id="DSGenInterface"></script>
<script type="text/javascript" src="/niis/gpkisecureweb/client/GPKIJS_Crypto.js" id="DSGPKIJS_Crypto"></script>

<script type="text/javascript" src="/niis/gpkisecureweb/client/GPKISecureWebJS.js"></script>
<script type="text/javascript" src="/niis/gpkisecureweb/client/var.js"></script>
<script type="text/javascript" src="/niis/gpkisecureweb/client/GPKISecureWebNP2.js"></script>


<style type="text/css">
	html, body { height:100%; overflow:hidden }
	#loginWrap {height:96%;}
	.inner h1 {padding:80px 0 30px 0;}
	.inner .copyright {padding:30px 0;}
</style>
<script type="text/javascript">
	
// 가상 키보드 사용을 위한 보안 세션 초기화
initSecureSession();

	$(document).ready(function(){
		
		$("#loginWrap").on("keyup", "#userId", function(){
			$(this).val($(this).val().replace(/[^a-zA-Z0-9]/g, ""));
		});
		
		medeaLogin();
		$(window).resize(medeaLogin);
		
	<c:if test="${state ne null }">
		var state = "<c:out value="${state }" />";
		if (state == "2"){
			alert("회원가입 후 이용해 주세요.\n기존 사용자일 경우 인증서 갱신 후 이용하세요.");
		}else if (state == "4"){
			ajaxCallPop("/login/regReject.do", {"userId": "<c:out value="${userId }" />", "cause" : "<c:out value="${cause }" />"}, "membershipPending");
		}else if (state == "5"){
			ajaxCallPop("/login/regWait.do", "", "membershipWait");
		}else if (state == "6"){
			alert("사용이 중지된 아이디입니다.");
		}else{
			//화면에서 로그인 실패 메시지 표출 필요할듯
			//window.console.log('로그인 실패!' + result.state);
			//alert("시스템에 연결할 수 없습니다. 잠시후 다시 시도해 주세요. \n지속적으로 문제 발생시 관리자에게 문의해 주세요.");
			//$("#userPw").focus();
		}
	</c:if>
	});
	
	function medeaLogin(){
		if ($(window).height() < 550) {
			$('.loginBox').addClass('minimum');
		} else {
			$('.loginBox').removeClass('minimum');
		}
	}
	
	
	function regUser(){
		
		ajaxCallPop("/login/regUser.do", "", "", 460);
	}
	
	
	function checkedGpkiUser(){
		
// 		ajaxCallJson("/login/loginCheck.do", data, function(result){
			
// 			if (result.state == "1"){
// 				location.href="/niis/main/main.do";
// 			}else if (result.state == "3"){
// 				alert("비밀번호를 정확히 입력해 주세요.");
// 				$("#userPw").val("").focus();
// 			}else if (result.state == "2"){
// 				alert("사용자 아이디를 확인해 주세요.");
// 				$("#userId").focus();
// 				$("#userPw").val("");
// 			}else if (result.state == "4"){
// 				ajaxCallPop("/login/regReject.do", {"userId": result.userId, "cause" : result.cause}, "membershipPending");
// 			}else if (result.state == "5"){
// 				ajaxCallPop("/login/regWait.do", "", "membershipWait");
// 			}else if (result.state == "6"){
// 				alert("사용이 중지된 아이디입니다.");
// 				$("#userId").focus();
// 				$("#userPw").val("");
// 			}else{
// 				//화면에서 로그인 실패 메시지 표출 필요할듯
// 				//window.console.log('로그인 실패!' + result.state);
// 				//alert("시스템에 연결할 수 없습니다. 잠시후 다시 시도해 주세요. \n지속적으로 문제 발생시 관리자에게 문의해 주세요.");
// 				$("#userPw").focus();
// 			}
// 		}, function(result){
// 			window.console.log('로그인 실패!' + result.state);
// 			alert("시스템에 연결할 수 없습니다. 잠시후 다시 시도해 주세요. \n지속적으로 문제 발생시 관리자에게 문의해 주세요.");
// 			$("#userPw").focus();
// 		});
	}
	
</script>
</head>
<body>
	<!-- #loginWrap -->
	<div id="loginWrap">
		<!-- .loginBox -->
		<div class="loginBox">
			<div class="inner">
				<h1><img src="/niis/images/login/h1_logo.png" alt="" /></h1>
				<%-- 
				<select id="keysec">
					<option value="1">가상 키보드</option>
					<option value="2" selected="selected">키보드 보안</option>
					<option value="0">사용하지 않음</option>
				</select><br/><br/>
				 --%>
				 <input type="hidden" id="keysec" value="2" />
				<ul>
					<li class="btnLogin">
						<a href="/niis/gpkisecureweb/jsp/gpkiLoginCheck.jsp?param1=123&param2=345&challenge=<%=challenge%>" onclick="return LoginLink(this,'<%=sessionid%>');"><img src="/niis/images/login/ic_login_btn.png" alt="GPKI 로그인" title="로그인" />로그인</a>
					</li>
				</ul>
				<p class="btnJoin">
					<a href="/niis/gpkisecureweb/jsp/register.jsp?param1=123&param2=345&challenge=<%=challenge%>" onclick="return LoginLink(this,'<%=sessionid%>');">회원가입</a>
					<span style="margin-right:20px;"> </span>
					<a href="/niis/gpkisecureweb/jsp/updateGPKI.jsp?param1=123&param2=345&challenge=<%=challenge%>" onclick="return LoginLink(this,'<%=sessionid%>');">인증서 갱신</a>
				</p>
				<p class="copyright">copyright(c) 2012 NGII All Rights Reserved.</p>
			</div>
		</div>
		<!-- /.loginBox -->
	</div>
	<!-- /#loginWrap -->
	
	<hr />
	
	<!-- 초기에 마스크 보일시에는 class="maskShow" 주시면 됩니다. -->
	<div id="mask"></div>
	
	<div id="commonPop" class="layer" style="display:none;">
	</div>
	
	<div id="membershipWait" class="layer" style="display:none;">
	</div>
	
	<div id="membershipPending" class="layer" style="display:none;">
	</div>
</body>
</html>