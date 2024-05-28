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
<%-- 
<%
	if(subDN.toString().contains("yessign")){
%>
		alert("행정전자서명으로만 로그인 됩니다.")
		location.href = "/niis/login/gpkiLogin.do";
<%
	}
%>
<%
	if(null == b || "".equals(b)){
%>
		location.href = "/niis";
<%
	}
%>
<% 
	if(message_type != gpkirequest.LOGIN_ENVELOP_SIGN_DATA){
%>	
		location.href = "/niis";
<%
	}
%>
 --%>	
	
	
	$(document).ready(function(){

		//gpki 중복 체크
		checkedGpkiUser2();
		
		$("#usrNm").focus();
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
	
	function chkDuplication(){
		
		$("#usrId").val($("#usrId").val().replace(/ /g, ""));
		
		var id = $("#usrId").val().trim();
		
		if(id == ""){
			checkRegVal("usrId", "idcheck", "아이디를 입력해 주세요.");
			$("#usrId").focus();
			return;
		}
		
		if(checkId(id)){
			checkRegVal("usrId", "idcheck", "아이디는 영문대/소문자, 숫자만 가능하며 <br/>4~18 자리로 입력해 주세요.");
			$("#usrId").focus();
			return;
		}
		
		var data = {
				"usrId"	:	id
		}
		
		ajaxCallJson("/login/chkDuplication.do", data, function(result, data){
			
			$("#dup").val("");
			
			if(result != null){
				if(result.state == "1"){
					checkRegVal("usrId", "idcheckOk", "사용가능한 아이디입니다.");
					$("#dup").val(id);
				}else if(result.state == "2"){
					checkRegVal("usrId", "idcheck", "이미 사용중인 아이디입니다.");
					$("#usrId").focus();
				}else{
					alert("중복체크에 실패하였습니다.");
				}
			}else{
				alert("중복체크에 실패하였습니다.");
			}
		});
	}
	
	
	function register(){
		
	
		var usrId 			= $("#usrId").val();
		var dup 			= $("#dup").val();
		var usrNm 			= $("#usrNm").val();
		var usrPw 			= '<%= b%>';
		var post 			= $("#post").val();
		var department 		= $("#department").val();
		var position 		= $("#position").val();
		
		//var useAt 			= $("#useAt").val();
		
		var usrTel1 		= $("#usrTel1").val();
		var usrTel2 		= $("#usrTel2").val();
		var usrTel3 		= $("#usrTel3").val();
		var usrEml1 		= $("#usrEml1").val();
		var usrEml2			= $("#usrEml2").val();
		
		var usrTel 			= usrTel1 + "-" + usrTel2 + "-" + usrTel3;
		var usrEml 			= usrEml1 + "@" + usrEml2;
		
		var stplat1 	= $("#chkStplat1:checked").val();
		var stplat2 	= $("#chkStplat2:checked").val();
		var stplat3 	= $("#chkStplat3:checked").val();
		
		if(usrId == ""){
			checkRegVal("usrId", "idcheck", "아이디를 입력해 주세요.");
			$("#usrId").focus();
			return;
		}
		
		if(dup == "" || dup != usrId){
			checkRegVal("usrId", "idcheck", "아이디 중복체크를 해주세요.");
			$("#usrId").siblings("a").focus();
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
		
		if(department == null || department == "" || department == "undefined"){
			checkRegVal("department", "idcheck", "부서명을 입력해 주세요.");
			$("#department").focus();
			return;
		}
		
		if(position == null || position == "" || position == "undefined"){
			checkRegVal("position", "idcheck", "직급을 입력해 주세요.");
			$("#position").focus();
			return;
		}
		
		/* 
		if(useAt == null || useAt == "" || useAt == "undefined"){
			alert("사용자권한을 선택해 주세요.");
			$("#useAt").focus();
			return;
		}
		 */
		
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
		
		if((usrEml1 == null || usrEml1 == "" || usrEml1 == "undefined")
				&& (usrEml2 == null || usrEml2 == "" || usrEml2 == "undefined")){
			usrEml = "";
		}else{
			
			if(checkEmail(usrEml)) {
				checkRegVal("usrEml2", "idcheck", "잘못된 이메일주소 형식입니다.");
				$("#usrEml2").focus();
				return;
			}
		}
		
		if(stplat1 != "Y"){
			alert("회원약관에 동의하셔야 합니다.");
			$("#chkStplat1").focus();
			return;
		}
		
		if(stplat2 != "Y"){
			alert("개인정보보호방침 약관에 동의하셔야 합니다.(필수항목)");
			$("#chkStplat2").focus();
			return;
		}
		
		if(stplat3 != "Y"){
			stplat3 = "N";
		}
		
		$("#usrTel").val(usrTel);
		$("#usrEml").val(usrEml);
		
		var data = $("#userRegForm").serializeObject();
		data.stplatAgre1 = stplat1;
		data.stplatAgre2 = stplat2;
		data.stplatAgre3 = stplat3;
		
		ajaxCallJson("/login/register.do", data, function(result, data){
			if(result != null){
				if(result.state == "1"){
					alert("사용자 승인이 성공적으로 요청되었습니다.");
					location.href="/niis/login/gpkiLogin.do";
				}else if(result.state == "2"){
					checkRegVal("usrEml2", "idcheck", "이미 사용중인 아이디입니다.");
				}else if(result.state == "3"){
					alert("반려된 아이디가 아닙니다.");
					$("#userRegForm .btnLayerClose").click();
				}else{
					alert("회원가입 요청에 실패하였습니다.");
				}
			}else{
				alert("회원가입 요청에 실패하였습니다.");
			}
		});
	}
	
	function moveMain(){
		location.href = "/niis/login/gpkiLogin.do"
	}
	
	function checkRegVal(id, clsNm, text){
		
		$("#userRegForm tr").prop("class", "");
		$("#userRegForm tr").find("p").remove();
		var tr = $("#userRegForm #" + id).parents("tr");
		
		tr.prop("class", clsNm);
		tr.children("td").append("<p>" + text + "</p>");
		/* 
		tr.prop("class", clsNm);
		$("#userRegForm #chkMsg").parents("tr").prop("class", clsNm);
		$("#userRegForm #chkMsg").parents("tr").children("td").append("<p>" + text + "</p>");
		 */
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
			}else if(result.state == "4"){
				reRegUser(result.userInfo);
				return false;
			}
			
			$("#loginForm").submit();
		}, function(result){
			$("#state").val("99");
		});
	}
	
	function reRegUser(user){
		
		$.each(user, function(key, value){
			
			var obj = $("#userRegForm table tbody #" + key);
			if(obj.hasClass("select")){
				obj.initSelect(value);
			}else{
				obj.val(value);
			}
		});
		
		$("#userRegForm #dup").val(user.usrId);
		$("#userRegForm #usrNm").prop("disabled", true);
		$("#userRegForm #usrId").prop("disabled", true);
		$("#userRegForm #usrId").css("width", "100%");
		$("#userRegForm #usrId").siblings("a").hide();
		$("#userRegForm").append('<input type="hidden" id="reapp" name="reapp" value="Y">');
		$("#btnReg").text("재신청");
	}
</script>
</head>
<body>
<div class="register">
<form id="userRegForm">
<input type="hidden" id="dup" name="dup" value="" />
<input type="hidden" id="usrTel" name="usrTel" value="" />
<input type="hidden" id="usrEml" name="usrEml" value="" />

	<!-- .boardStyle_1 -->
	<div class="loginBox">
		<div class="inner">
		<h1><img src="/niis/images/login/h1_logo.png" alt="" /></h1>
			<table>
				<colgroup>
					<col width="115px" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th class="essential">이름 *</th>
						<td>
							<input type="text" id="usrNm" name="usrNm" value="" style="width:100%;ime-mode:active;" maxlength="10" />
						</td>
					</tr>
					<tr>
						<th class="essential">아이디 *</th>
						<td>
							<input type="text" id="usrId" name="usrId" style="width:148px;ime-mode:disabled;" maxlength="20" />
							<a href="#none" class="btnStyle btnStyle_1" onclick="chkDuplication();">중복확인</a>
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
						<th class="essential">부서명 *</th>
						<td>
							<input type="text" id="department" name="department" value="" style="width:100%;ime-mode:active;" maxlength="25" />
						</td>
					</tr>
					<tr>
						<th class="essential">직급 *</th>
						<td>
							<input type="text" id="position" name="position" value="" style="width:100%;ime-mode:active;" maxlength="25" />
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
					<!-- 
					<tr>
						<th></th>
						<td>* 휴대폰 번호를 입력하면 승인여부를 SMS 서비스로 제공 받으실 수 있습니다.</td>
					</tr>
					 -->
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" id="usrEml1" name="usrEml1" value="" style="width:113px;ime-mode:disabled;" maxlength="25" />
							@
							<input type="text" id="usrEml2" name="usrEml2" value="" style="width:110px;ime-mode:disabled;" maxlength="25" />
						</td>
					</tr>
				<!-- 
					<tr>
						<td colspan="2">
							<input type="hidden" id="chkMsg" name="chkMsg" />
						</td>
					</tr>
					 -->
				</tbody>
			</table>
			<!-- /.boardStyle_1 -->
			<p class="announcement">가입 후 관리자의 승인처리가 완료되면 서비스를 이용하실 수 있습니다.<br/>휴대폰 번호 입력 시 SMS 서비스를 통해 승인여부를  제공 받으실 수 있습니다.</p>
			<div class="stplat">
				<table>
					<colgroup>
						<col width="*" />
						<col width="100px" />
					</colgroup>
					<tbody>
						<tr>
							<th><input type="checkbox" id="chkStplat1" name="chkStplat1" value="Y" /><label for="chkStplat1">국토지리정보원의 약관에 동의합니다.</label></th>
							<td><a href="#stplatView1" class="btnStplat btnLayerOpen">약관보기</a></td>
						</tr>
						<tr>
							<th>
								<input type="checkbox" id="chkStplat2" name="chkStplat2" value="Y" /><label for="chkStplat2">개인정보 수집 및 이용에 동의합니다.(필수항목)</label><br />
								<input type="checkbox" id="chkStplat3" name="chkStplat3" value="Y" /><label for="chkStplat3">개인정보 수집 및 이용에 동의합니다.(선택항목)</label>
							</th>
							<td><a href="#stplatView2" class="btnStplat btnLayerOpen">약관보기</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btnArea">
				<a href="#none" id="btnReg" class="active" onclick="register();">회원가입</a>
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
	
<div id="stplatView1" class="stplatRule layer" style="display:none;">
	<div class="tit">
		<h3>회원약관 동의</h3>
		<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" /></a>
	</div>
	<div class="con">
		<%@ include file="/WEB-INF/jsp/niis/login/stplat/stplatRule1.jsp" %>
		<div class="btnArea">
			<a href="#none" class="active btnLayerClose" onclick="$('#chkStplat1').prop('checked', true);">동의</a>
		</div>
	</div>
</div>

<div id="stplatView2" class="stplatRule layer" style="display:none;">
	<div class="tit">
		<h3>개인정보 수집 및 이용에 대한 안내</h3>
		<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" /></a>
	</div>
	<div class="con">
		<%@ include file="/WEB-INF/jsp/niis/login/stplat/stplatRule2.jsp" %>
		<div class="btnArea">
			<a href="#none" class="active btnLayerClose" onclick="$('#chkStplat2, #chkStplat3').prop('checked', true);">동의</a>
		</div>
	</div>
</div>
</body>
</html>