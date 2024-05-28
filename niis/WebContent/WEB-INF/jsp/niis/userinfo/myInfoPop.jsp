<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
	});
	function register(){
		var usrId 			= $("#usrId").val();
		var dup 			= $("#dup").val();
		var exPw 			= $("#exPw").val();
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
		
		if(exPw == null || exPw == "" || exPw == "undefined"){
			alert("비밀번호를 입력해 주세요.");
			$("#exPw").focus();
			return;
		}
		
		if(usrPw == null || usrPw == "" || usrPw == "undefined"){
			
		}else{
			if(checkPass(usrPw)){
				alert("비밀번호는 영문자, 숫자, 특수문자가 포함된  \n9~20 자리로 입력해 주세요.");
				$("#usrPw").focus();
				return;
			}
			if(usrPw != usrPwConfirm){
				alert("새 비밀번호와 비밀번호 확인란의 비밀번호가 다릅니다. ");
				$("#usrPwConfirm").focus();
				return;
			}
		}
		
		if(post == null || post == "" || post == "undefined"){
			alert("기관명을 입력해 주세요.");
			$("#post").focus();
			return;
		}
		
		if(department == null || department == "" || department == "undefined"){
			alert("부서명을 입력해 주세요.");
			$("#department").focus();
			return;
		}
		
		if(position == null || position == "" || position == "undefined"){
			alert("직급을 입력해 주세요.");
			$("#position").focus();
			return;
		}

		if(usrTel1 == null || usrTel1 == "" || usrTel1 == "undefined"){
			alert("연락처를 입력해 주세요.");
			$("#usrTel1").focus();
			return;
		}
		
		if(usrTel2 == null || usrTel2 == "" || usrTel2 == "undefined"){
			alert("연락처를 입력해 주세요.");
			$("#usrTel2").focus();
			return;
		}
		
		if(usrTel3 == null || usrTel3 == "" || usrTel3 == "undefined"){
			alert("연락처를 입력해 주세요.");
			$("#usrTel3").focus();
			return;
		}
		
		if(checkTel(usrTel)){
			alert("잘못된 전화번호입니다.\n전화번호를 확인해 주세요.");
			$("#usrTel1").focus();
			return;
		}
		
		if((usrEml1 == null || usrEml1 == "" || usrEml1 == "undefined")
				&& (usrEml2 == null || usrEml2 == "" || usrEml2 == "undefined")){
			usrEml = "";
		}else{
			
			if(checkEmail(usrEml)) {
				alert("잘못된 이메일주소 형식입니다.");
				$("#usrEml2").focus();
				return;
			}
		}
		$("#usrTel").val(usrTel);
		$("#usrEml").val(usrEml);
		
		var data = $("#userModForm").serializeObject();
		
		 ajaxCallJson("/userinfo/userinfoUpt.do", data, function(result, data){
			 if(result != null){
				if(result.isNewPass == "0"){
					alert("비밀번호를 정확히 입력해 주세요.");
					return;
				}else if(result.isNewPass == "1"){
					alert("신규 비밀번호가 기존 비밀번호와 동일합니다.\n새로운 비밀번호를 입력해 주세요.");
					$("#exPw").focus();
					return;
				}
				alert("수정 되었습니다.");
				$('.btnLayerClose').each(function(){
					if($(this).parents('.layerHide').css("display") != "none"){
						$(this).click();
					}
				});
			}
		});  
		
	}
	
	</script>
	
	<div class="tit">
		<h3>정보 수정</h3>
		<a href="#none" class="btnLayerClose">
			<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
		</a>
	</div>
	<div class="con">
	<form id="userModForm">
	<input type="hidden" id="dup" name="dup" value="${userinfo.usrId }">
	<input type="hidden" id="usrTel" name="usrTel" value="">
	<input type="hidden" id="usrEml" name="usrEml" value="">

		<!-- .boardStyle_1 -->
		<div class="boardStyle_1">
			<table>
				<colgroup>
					<col width="115px" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th>이름 *</th>
						<td>
							<input type="text" id="usrNm" name="usrNm" value="${userinfo.usrNm}" style="width:100%;ime-mode:active;" maxlength="10" disabled/>
						</td>
					</tr>
					<tr id="trIdcheck">
						<th>아이디 *</th>
						<td>
							<input type="text" id="usrId" name="usrId" value="${userinfo.usrId}" style="width:100%;ime-mode:disabled;" maxlength="20" disabled />
						</td>
					</tr>
					
					<tr>
						<th class="essential">비밀번호  *</th>
						<td>
							<input type="password" id="exPw" name="exPw" style="width:100%" />
						</td>
					</tr>
					
					<tr>
						<th>새 비밀번호 입력 </th>
						<td>
							<input type="password" id="usrPw" name="usrPw" style="width:100%" />
						</td>
					</tr>
					<tr>
						<th>새 비밀번호 확인</th>
						<td>
							<input type="password" id="usrPwConfirm" name="usrPwConfirm" style="width:100%" />
						</td>
					</tr>
					<tr>
						<th class="essential">기관 *</th>
						<td>
							<input type="text" id="post" name="post" value="${userinfo.post}" style="width:100%;ime-mode:active;" maxlength="25" />
						</td>
					</tr>
					<tr>
						<th class="essential">부서명 *</th>
						<td>
							<input type="text" id="department" name="department" value="${userinfo.department}" style="width:100%;ime-mode:active;" maxlength="25" />
						</td>
					</tr>
					<tr>
						<th class="essential">직급 *</th>
						<td>
							<input type="text" id="position" name="position" value="${userinfo.position}" style="width:100%;ime-mode:active;" maxlength="25" />
						</td>
					</tr>
					<tr>
						<th class="essential">연락처 *</th>
						<td>
							<select id="usrTel1" name="usrTel1" class="select"  style="width:85px">
								<option value="">선택</option>
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
								<option value="02">02</option>
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
							<script type="text/javascript">
								$("#userModForm #usrTel1").initSelect("<c:out value="${userinfo.usrTel1 }" />");
							</script>
							-
							<input type="text" id="usrTel2" name="usrTel2" value="${userinfo.usrTel2}" style="width:88px;ime-mode:disabled;" maxlength="4" />
							-
							<input type="text" id="usrTel3" name="usrTel3" value="${userinfo.usrTel3}" style="width:88px;ime-mode:disabled;" maxlength="4" />
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" id="usrEml1" name="usrEml1" value="${userinfo.usrEml1}" style="width:150px;ime-mode:disabled;" maxlength="25" />
							@
							<input type="text" id="usrEml2" name="usrEml2" value="${userinfo.usrEml2}" style="width:135px;ime-mode:disabled;" maxlength="25" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- /.boardStyle_1 -->
		<p class="announcement">가입시 입력하신 주요 항목은 변경하실 수 없습니다.<br>
			<span class="block">문제 발생시 아래 연락처로 문의 바랍니다.</span>
			<%@ include file="/WEB-INF/jsp/niis/main/callGuide.jsp" %>
		</p>
		<div class="btnArea">
			<a href="#none" class="active" onclick="register();">수정</a>
			<a href="#none" class="btnLayerClose">취소</a>
		</div>
	</form>
	</div>