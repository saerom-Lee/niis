<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.gpki.gpkiapi.exception.GpkiApiException"%>
<%@ page import="java.util.Enumeration"%>
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
	if(null == subDN || "".equals(subDN)){
		subDN = "yessign";
	}
	queryString = gpkirequest.getQueryString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>국토영상정보 공급시스템</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easing.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/default.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/commonUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){

		//gpki 중복 체크
		checkedGpkiUser2();
		
		$("#usrId").focus();
		var btest 	= '<%= b%>';
		$("td input").keyup(function(){
			$(this).parents("tr").prop("class", "");
			$(this).siblings("p").remove();
			if(typeof String.prototype.trim !== 'function') {
				  String.prototype.trim = function() {
				    return this.replace(/^\s+|\s+$/g, ''); 
				  }
			}
		});
	});
	
	
	function update(){
		
	
		var usrId 			= $("#usrId").val();
		var usrNm 			= $("#usrNm").val();
		var usrPw 			= '<%= b%>';
		var post 			= $("#post").val();
		var usrTel1 		= $("#usrTel1").val();
		var usrTel2 		= $("#usrTel2").val();
		var usrTel3 		= $("#usrTel3").val();
		
		var usrTel 			= usrTel1 + "-" + usrTel2 + "-" + usrTel3;

		if(usrId == ""){
			checkRegVal("usrId", "idcheck", "아이디를 입력해 주세요.");
			$("#usrId").focus();
			return;
		}
				
		if(checkId(usrId)){
			checkRegVal("usrId", "idcheck", "아이디는 영문대/소문자, 숫자만 가능하며 \n영문 포함 4~18 자리로 입력해 주세요.");
			$("#usrId").focus();
			return;
		}
		
		if(usrNm == null || usrNm == "" || usrNm == "undefined"){
			checkRegVal("usrNm", "idcheck", "이름을 입력해 주세요.");
			$("#usrNm").focus();
			return;
		}
		
		if(post == null || post == "" || post == "undefined"){
			checkRegVal("post", "idcheck", "기관명을 입력해 주세요.");
			$("#post").focus();
			return;
		}
		
		if(usrTel1 == null || usrTel1 == "" || usrTel1 == "undefined"){
			checkRegVal("usrTel1", "idcheck", "연락처를 입력해 주세요.");
			$("#usrTel1").focus();
			return;
		}
		
		if(usrTel2 == null || usrTel2 == "" || usrTel2 == "undefined"){
			checkRegVal("usrTel2", "idcheck", "연락처를 입력해 주세요.");
			$("#usrTel2").focus();
			return;
		}
		
		if(usrTel3 == null || usrTel3 == "" || usrTel3 == "undefined"){
			checkRegVal("usrTel3", "idcheck", "연락처를 입력해 주세요.");
			$("#usrTel3").focus();
			return;
		}
		
		if(checkTel(usrTel)){
			checkRegVal("usrTel1", "idcheck", "잘못된 전화번호입니다.\n전화번호를 확인해 주세요.");
			$("#usrTel1").focus();
			return;
		}
		
		$("#usrTel").val(usrTel);
		
		var data = $("#userUpdateForm").serializeObject();
		
		ajaxCallJson("/login/update.do", data, function(result, data){
			if(result != null){
				if(result.state == "1"){
					alert("인증서 갱신이 완료되었습니다.");
					location.href="/niis/login/gpkiLogin.do";
				}else if(result.state == "2"){
					alert("사용자 정보를 찾을 수 없습니다.\n회원가입을 해주세요.");
					location.href="/niis/login/gpkiLogin.do";
				}else{
					alert("인증서 갱신 요청에 실패하였습니다.");
				}
			}else{
				alert("인증서 갱신 요청에 실패하였습니다.");
			}
		});
	}
	
	function moveMain(){
		location.href = "/niis/login/gpkiLogin.do"
	}
	
	function checkRegVal(id, clsNm, text){
		
		$("#userUpdateForm tr").prop("class", "");
		$("#userUpdateForm tr").find("p").remove();
		var tr = $("#userUpdateForm #" + id).parents("tr");
		
		tr.prop("class", clsNm);
		tr.children("td").append("<p>" + text + "</p>");
	}
	
	
	function checkedGpkiUser2(){
		
		var data = {
				"userpw" : '<%= b%>'
		}
		
		ajaxCallJson("/login/regCheckGpki.do", data, function(result){
			
			$("#state").val(result.state);
			
			if(result.state == "1"){
				alert("이미 등록된 사용자 입니다.");
				location.href="/niis/main/main.do";
				return false;
			}else if(result.state == "2"){
				return false;
			}
			
			$("#loginForm").submit();
		}, function(result){
			$("#state").val("99");
		});
	}
	
	
</script>
</head>
<body>
<div class="register">
<form id="userUpdateForm">
<input type="hidden" id="usrTel" name="usrTel" value="" />

	<!-- .boardStyle_1 -->
	<div class="loginBox">
		<div class="inner">
		<h1 class="check"><img src="/niis/images/login/h1_logo.png" alt="" /></h1>
		<h2>인증서 갱신</h2>
			<table>
				<colgroup>
					<col width="115px" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th class="essential">아이디 *</th>
						<td>
							<input type="text" id="usrId" name="usrId" style="width:100%" maxlength="20" />
						</td>
					</tr>
					<tr>
						<th class="essential">이름 *</th>
						<td>
							<input type="text" id="usrNm" name="usrNm" style="width:100%" maxlength="20" />
						</td>
					</tr>
					<tr style="display:none;">
						<th class="essential">비밀번호 입력 *</th>
						<td>
							<input type="password" id="usrPw" name="usrPw" value="<%= b%>" style="width:100%" />
						</td>
					</tr>
					<tr>
						<th class="essential">기관 *</th>
						<td>
							<input type="text" id="post" name="post" value="" style="width:100%;ime-mode:active;" maxlength="25" />
						</td>
					</tr>
					<tr>
						<th class="essential">연락처 *</th>
						<td>
							<select id="usrTel1" name="usrTel1" class="select" style="width:80px">
								<option value="">선택</option>
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
								<option value="02" >02 </option>
								<option value="031">031</option>
								<option value="032">032</option>
								<option value="033">033</option>
								<option value="041">041</option>
								<option value="042">042</option>
								<option value="043">043</option>
								<option value="044">044</option>
								<option value="051">051</option>
								<option value="052">052</option>
								<option value="053">053</option>
								<option value="054">054</option>
								<option value="055">055</option>
								<option value="061">061</option>
								<option value="062">062</option>
								<option value="063">063</option>
								<option value="064">064</option>
							</select>
							-
							<input type="text" id="usrTel2" name="usrTel2" value="" style="width:73px;ime-mode:disabled;" maxlength="4" />
							-
							<input type="text" id="usrTel3" name="usrTel3" value="" style="width:73px;ime-mode:disabled;" maxlength="4" />
						</td>
					</tr>
				</tbody>
			</table>
			<!-- /.boardStyle_1 -->
			
			<div class="btnArea">
				<a href="#none" id="btnReg" class="active" onclick="update();">인증서 갱신</a>
				<a href="#none" onclick="moveMain();" class="btnLayerClose">취소</a>
			</div>
		</div>
	</div>
</form>
<form id="loginForm" action="/niis/login/gpkiLogin.do">
	<input type="hidden" id="state" name="state" />
</form>
</div>
<div id="mask"></div>
	
</body>
</html>