<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#usrNm").focus();
		
		$("td input").keyup(function(){
			$(this).parents("tr").prop("class", "");
			$(this).siblings("p").remove();
		});

<c:if test="${map ne null }">
		$("#usrTel1").initSelect('<c:out value="${map.usrTel1 }" />');
</c:if>
	
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
					$("#usrPw").focus();
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
		var usrPw 			= $("#usrPw").val();
		var usrPwConfirm 	= $("#usrPwConfirm").val();
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
		
<c:if test="${map eq null }">
		//password 검증
		if(usrPw == null || usrPw == "" || usrPw == "undefined"){
			checkRegVal("usrPw", "idcheck", "비밀번호를 입력해 주세요.");
			$("#usrPw").focus();
			return;
		}else{
			if(checkPass(usrPw)){
				checkRegVal("usrPw", "idcheck", "비밀번호는 영문자, 숫자, 특수문자가 포함된  \n9~20 자리로 입력해 주세요.");
				$("#usrPw").focus();
				return;
			}
			
			if(usrPw != usrPwConfirm){
				checkRegVal("usrPwConfirm", "idcheck", "비밀번호가 일치하지 않습니다.");
				$("#usrPwConfirm").focus();
				return;
			}
		}
</c:if>
		
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
		
		$("#usrTel").val(usrTel);
		$("#usrEml").val(usrEml);
		
		var data = $("#userRegForm").serializeObject();
		
		ajaxCallJson("/login/register.do", data, function(result, data){
			if(result != null){
				if(result.state == "1"){
					alert("사용자 승인이 성공적으로 요청되었습니다.");
					$("#userRegForm .btnLayerClose").click();
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
	
	
</script>
<div class="tit">
	<h3><c:if test="${map == null }">회원가입</c:if><c:if test="${map != null }">재신청</c:if></h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" /></a>
</div>
<div class="con">
<form id="userRegForm">
<input type="hidden" id="dup" name="dup" value="${map.usrId }">
<input type="hidden" id="usrTel" name="usrTel" value="">
<input type="hidden" id="usrEml" name="usrEml" value="">

<c:if test="${map != null }">
<input type="hidden" id="reapp" name="reapp" value="Y">
</c:if>
	<!-- .boardStyle_1 -->
	<div class="boardStyle_1">
		<table>
			<colgroup>
				<col width="115px" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th class="essential">이름 *</th>
					<td>
						<input type="text" id="usrNm" name="usrNm" value="${map.usrNm }" style="width:100%;ime-mode:active;" maxlength="10" <c:if test="${map != null }">disabled</c:if> />
					</td>
				</tr>
				<tr>
					<th class="essential">아이디 *</th>
					<td>
					<c:if test="${map == null }">
						<input type="text" id="usrId" name="usrId" style="width:185px;ime-mode:disabled;" maxlength="20" />
						<a href="#none" class="btnStyle btnStyle_1" onclick="chkDuplication();">중복확인</a>
					</c:if>
					<c:if test="${map != null }">
						<input type="text" id="usrId" name="usrId" value="${map.usrId }" style="width:100%;ime-mode:disabled;" maxlength="20" onlyengnum="true" disabled />
					</c:if>
					</td>
				</tr>
			<c:if test="${map == null }">
				<tr>
					<th class="essential">비밀번호 입력 *</th>
					<td>
						<input type="password" id="usrPw" name="usrPw" style="width:100%" />
					</td>
				</tr>
				<tr>
					<th class="essential">비밀번호 확인 *</th>
					<td>
						<input type="password" id="usrPwConfirm" name="usrPwConfirm" style="width:100%" />
					</td>
				</tr>
			</c:if>
				<tr>
					<th class="essential">기관 *</th>
					<td>
						<input type="text" id="post" name="post" value="${map.post }" style="width:100%;ime-mode:active;" maxlength="25" />
					</td>
				</tr>
				<tr>
					<th class="essential">부서명 *</th>
					<td>
						<input type="text" id="department" name="department" value="${map.department }" style="width:100%;ime-mode:active;" maxlength="25" />
					</td>
				</tr>
				<tr>
					<th class="essential">직급 *</th>
					<td>
						<input type="text" id="position" name="position" value="${map.position }" style="width:100%;ime-mode:active;" maxlength="25" />
					</td>
				</tr>
				<tr>
					<th class="essential">연락처 *</th>
					<td>
						<select id="usrTel1" name="usrTel1" class="select" style="width:85px">
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
						<input type="text" id="usrTel2" name="usrTel2" value="${map.usrTel2 }" style="width:88px;ime-mode:disabled;" maxlength="4" />
						-
						<input type="text" id="usrTel3" name="usrTel3" value="${map.usrTel3 }" style="width:88px;ime-mode:disabled;" maxlength="4" />
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" id="usrEml1" name="usrEml1" value="${map.usrEml1 }" style="width:150px;ime-mode:disabled;" maxlength="25" />
						@
						<input type="text" id="usrEml2" name="usrEml2" value="${map.usrEml2 }" style="width:135px;ime-mode:disabled;" maxlength="25" />
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
	</div>
	<!-- /.boardStyle_1 -->
	<p class="announcement">가입 후 관리자의 승인처리가 완료되면 서비스를 이용하실 수 있습니다.</p>
	<div class="btnArea">
		<a href="#none" class="active" onclick="register();"><c:if test="${map == null }">회원가입</c:if><c:if test="${map != null }">재신청</c:if></a>
		<a href="#none" class="btnLayerClose">취소</a>
	</div>
</form>
</div>