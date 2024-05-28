/********************************************************
파일명 : commonUtil.js
설 명 : 공통유틸 관련 스크립트
수정일 수정자 Version Function 명
------- -------- ---------- --------------
2015.10.30 SHW  1.0 최초 생성
*********************************************************/


/**
 * @title	null 변환 
 * @desc	null일 경우 "" 리턴
 * @param	text	: null 체크할 값
 * 			value	: null일시 반환될 값
 */
function fnNvl(text, value){
	
	if (text == null) {
		if(value != null && value != "" && value != "undefined"){
			text = value;
		}else{
			text = "";
		}
	}
	return text;
}


/**
 * @title	텍스트 포멧 변경 
 * @desc	텍스트를 지정된 포멧으로 반환한다 
 * @param	text	: 변환될 텍스트 값
 * 			form	: 변환될 형태
 * 
 * @usage	getFormattedText("01012341234", "###-####-####"); -> return 010-1234-1234 
 */
function getFormattedText(text, form){
	
	if(text == null || text == "" || text == "undefined") return "";
	if(form == null || form == "" || form == "undefined") return "";
	
	var result = "";
	var j = 0;
	
	for(var i=0; i<form.length; i++){
		if(form.charAt(i) == "#"){
			result += text.charAt(j);
			
			if(j >= text.length) break;
			j++;
		}else{
			result += form.charAt(i);
		}
	}
	return result;
}


/**
 * @title	숫자형식으로 포멧 변환 
 * @desc	텍스트를 숫자형식으로 포멧 변환 
 * @param	text	: 변환될 텍스트 값
 * 
 * @usage	getNumberFormat("1101234"); -> return 1,101,234 
 */
function getNumberFormat(text){
	
	if(text == null || text == "" || text == "undefined") return "0";
	
	text = ""+text;
	
	var result = "";
	var j = 0;
	
	for(var i=(text.length-1); i>=0; i--){
		if(j == 3){
			result = "," + result;
			j = 0;
		}
		result = text.charAt(i) + result;
		j++;
	}
	return result;
}


/**
 * @title	onkeyup 이벤트
 * @desc	실수값 검증 
 * @param	_this	: 오브젝트
 * 			len		: 전체자리수(소수점 제외)
 * 			scale	: 소수점 자리수
 * 
 * @usage	onkeyup="chkRealNum(this, 11, 3);"
 */
function chkRealNum(_this, len, scale){
	
	var text = _this.value;
	
	var result = text.replace(/(^[\.]|[^0-9\.])/g, "");
	
	if(result.length > 1 && result.substr(0, 1) == "0"){
		result = "0." + result.substr(1);
	}
	
	var index = result.search(/\./g);
	
	if(index < 0){
		if(text.length > (len - scale)){
			result = result.substr(0, (len - scale)) + "." + result.substr(len - scale); 
		}
	}else{
		
		var int = result.substr(0, index);
		var fra = result.substr(index).replace(/\./g, "");
		
		if(int.length > (len - scale)){
			int = int.substr(0, (len - scale));
		}
		
		if(fra.length > scale){
			fra = fra.substr(0, scale);
		}
		
		result = int + "." + fra;
	}
	
	_this.value = result;
	/*
	if(text.replace(/\./g, "").length > len){
		jAlert("글자수는 소수점 제외 전체" + len + "자리, 소수점" + sacle + "자리까지 입력 가능합니다.", "", function(){
			_this.focus();
		});
		_this.value = text.substr(0, text.length-1);
		return;
	}
	*/
}


/**
 * @title	id 유효성체크 
 * @desc	id 유효성을 체크한다.
 * @param	id	: 유효성을 체크할 값
 * 
 * @usage	if(checkId(아이디)) {
 * 				alert("아이디를 확인해주세요");
 * 				$("#id").focus();
 * 				return;
 * 			}
 * 			
 * 			성공시 return false;
 * 			실패시 return true;
 */
function checkId(id){
	
	var regExp1 = /^[a-zA-Z0-9]{4,18}$/;
	var regExp2 = /^(?=.*[a-zA-Z]).*$/;
	
	if(!regExp1.test(id) || !regExp2.test(id)) {
		return true;
	}
	return false;
}


/**
 * @title	비밀번호 유효성체크 
 * @desc	비밀번호 유효성을 체크한다.
 * @param	password	: 유효성을 체크할 값
 * 
 * @usage	if(checkPass(비밀번호)) {
 * 				alert("비밀번호를 확인해주세요");
 * 				$("#password").focus();
 * 				return;
 * 			}
 * 			
 * 			성공시 return false;
 * 			실패시 return true;
 */
function checkPass(password){
	
	var regExp = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*+=-]).{9,20}$/;
	
	if(!regExp.test(password)) {
		return true;
	}
	return false;
}


/**
 * @title	휴대전환/전화번호 유효성 체크 
 * @desc	휴대전환/전화번호 유효성 체크한다 
 * @param	tel	: 유효성을 체크할 값
 * 
 * @usage	if(checkTel(전화번호)) {
 * 				alert("잘못된 전화번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.\n예) 050-XXXX-XXXX");
 * 				$("#textId").focus();
 * 				return;
 * 			}
 * 			
 * 			성공시 return false;
 * 			실패시 return true;
 */
function checkTel(tel){
	
	//var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-[0-9]{3,4}-[0-9]{4}$/;
	
	if(!regExp.test(tel)) {
		return true;
	}
	return false;
}


/**
 * @title	e-mail 유효성 체크 
 * @desc	e-mail 유효성 체크한다 
 * @param	email	: 유효성을 체크할 값
 * 
 * @usage	if(checkEmail(이메일)) {
 * 				alert("잘못된 이메일주소 형식입니다.");
 * 				$("#textId").focus();
 * 				return;
 * 			}
 * 			
 * 			성공시 return false;
 * 			실패시 return true;
 */
function checkEmail(email){
	
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	if(!regExp.test(email)) {
		return true;
	}
	return false;
}


/**
 * @title	날짜 유효성 체크 
 * @desc	날짜 유효성 체크한다.
 * @param	date	: 유효성을 체크할 값
 * 
 * @usage	if(checkDate(날짜)) {
 * 				alert("일자를 확인해 주세요.");
 * 				$("#textId").focus();
 * 				return;
 * 			}
 * 			
 * 			성공시 return false;
 * 			실패시 return true;
 */
function checkDate(date){
	
	var regExp = /^(19[4-9][0-9]|20\d{2})((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01])|(0[469]|11)(0[1-9]|[12][0-9]|30)|02(0[1-9]|[1-2][0-9]))$/;
	
	if(!regExp.test(date)) {
		return true;
	}
	return false;
}
