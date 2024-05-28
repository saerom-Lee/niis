<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>공간정보 행정망 서비스</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easing.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/default.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/commonUtil.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#loginWrap").on("keyup", "#userId", function(){
	    	$(this).val($(this).val().replace(/[^a-zA-Z0-9]/g, ""));
	    });
		
		medeaLogin();
		$(window).resize(medeaLogin);
		
		$("#userId").focus();
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
	
	
	function checkedUser(){
		
		if ($("#userId").val() == ""){
			alert("사용자 아이디를 입력해주세요!");
			$("#userId").focus();
			return;
		}
		
		if ($("#userPw").val() == ""){
			alert("비밀번호를 입력해주세요!");
			$("#userPw").focus();
			return;
		}
		
		var data = {
				"userId" : $("#userId").val(), 
				"userpw" : $("#userPw").val()
		}
		
		ajaxCallJson("/login/loginCheck.do", data, function(result){
			
			if (result.state == "1"){
				location.href="/niis/main/main.do";
			}else if (result.state == "3"){
				alert("비밀번호를 정확히 입력해 주세요.");
				$("#userPw").val("").focus();
			}else if (result.state == "2"){
				alert("사용자 아이디를 확인해 주세요.");
				$("#userId").focus();
				$("#userPw").val("");
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
</head>
<body>
	<!-- #loginWrap -->
	<div id="loginWrap">
		<!-- .loginBox -->
		<div class="loginBox">
			<div class="inner">
				<h1><img src="/niis/images/login/h1_logo.png" alt="" /></h1>
				<ul>
					<li><input type="text" class="placeholder" id="userId" style="ime-mode:disabled;" onlyengnum="true" /><label for="userId">아이디</label></li>
					<li><input type="password" class="placeholder" id="userPw" onkeydown="javascript:if(event.keyCode == 13){checkedUser();return false;}"/><label for="userPw">비밀번호를 입력해주세요.</label></li>
					<li class="btnLogin">
						<a href="#none" onclick="checkedUser();"><img src="/niis/images/login/ic_login_btn.png" alt="로그인" title="로그인" />로그인</a>
					</li>
				</ul>
				<p class="btnJoin">
					<a href="#none" onclick="regUser();" class="btnLayerOpen">회원가입</a>
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