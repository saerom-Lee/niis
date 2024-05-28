<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="tit">
	<h3>다운로드</h3>
	<a href="#none" class="btnLayerClose"> <img
		src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div class="con">
	<div id="downloadControl" style="display: none"></div>
	<div>
		<!-- <img src="/niis/images/bgLoad.gif" width="376" height="86" alt="" /> -->
		<img src="/niis/images/loading-bar.gif" alt="loading" title="loading" />
	</div>
	<p class="txt">
		다운로드 준비중입니다.<span class="block">잠시만 기다려 주세요.</span>
	</p>
	<!-- 
	<input type="button" value="바로열기" onclick="innorix.api.downloadAndOpen('downloadControl');" />
	<input type="button" value="선택파일 받기" onclick="innorix.api.downloadSelectedFiles('downloadControl');" />
	<input type="button" value="전체파일 받기" onclick="innorix.api.download('downloadControl');" />
	<input type="button" value="파일목록 등록" onclick="getDownloadFileArr();" />
	<input type="button" value="파일강제등록" onclick="aaa();" />
	<input type="button" value="파일목록 조회" onclick="console.log(innorix.api.getAllFiles('downloadControl', 'download'));" />
	<div class="btnArea">
		<a href="#none" class="active">전송중지</a>
		<a href="#none" class="btnLayerClose">취소</a>
	</div>
	 -->
</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.0.min.js?ver=12"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/innorix/common/innorix.config.js?ver=12"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js?ver=11"></script>

<script type="text/javascript">

	$(document).ready(function(){
		setTimeout(function(){
			initInnorix();
		}, 1000);
	});	
		
	function initInnorix(){
		// 컨트롤 설치확인
		alert("install>>>" + innorix.api.isPluginInstalled());
		if (innorix.api.isPluginInstalled() == false) {
			innorix.api.goToInstallPage("<%=request.getContextPath()%>/innorix/common/install/install.html")
		}
		 
		//컨트롤 이벤트 리스너
		innorix.event = {
			loadComplete : function(controlId, param) { // 컨트롤 로딩 완료
				alert("1111");
				//console.log("START********************************* loadComplete *********************************");
				//console.log(param);
				//console.log("E N D********************************* loadComplete *********************************");
				getDownloadFileArr();
				alert("2222");
			},
			downloadCancel : function(controlId, param) { // 다운로드 취소
				//console.log("START********************************* downloadCancel *********************************");
				//console.log(param);
				//console.log("E N D********************************* downloadCancel *********************************");
				closeLayerPop();
			},
			downloadError : function(controlId, param) { // 다운로드 에러
				//console.log("START********************************* downloadError *********************************");
				//console.log(param);
				//console.log("E N D********************************* downloadError *********************************");
			},
			downloadComplete : function(controlId, param) { // 다운로드 완료
				//console.log("START********************************* downloadComplete *********************************");
				//console.log(param);
				//console.log("E N D********************************* downloadComplete *********************************");
				var data = {
						"supIdn"   : "<c:out value="${supIdn }" />", 
						"imageCde" : "<c:out value="${imageCde }" />",
						"group"    : "<c:out value="${group }" />"
				}
				
				ajaxCallJson("/supply/downloadComplete.do", data, function(result){
					/* getMainApply();
					getAppApplyListPaging(); */
					appImgDownloadList("<c:out value="${supIdn }" />");
				});
			},
			folderPath : function(controlId, param) { // 다운로드 완료
				//console.log("START********************************* folderPath *********************************");
				//console.log(param);
				//console.log("E N D********************************* folderPath *********************************");
			}
		}
		
		// 컨트롤 생성
		innorix.api.create({
			downloadControl	: {					// 컨트롤 id
				charset			: "utf-8",      // 케릭터셋 설정, utf-8 | euc-kr
				controlType		: "default",    // 컨트롤 타입, default | bigFile1 | bigFile2 | htmlAlike
				downloadType	: "stream",		// 다운로드 방식을 선택, direct | stream
				duplicateFile	: "resume",		// 중복파일 다운로드시 파일 저장방식, cofirm | overwrite | autosave | resume
				product			: "innoexd"		// 제품모드 선택, Innoexu | Innoexd | Innoexu, Innoexd
				/* useCompress		: true,			// 압축 전송 사용 */
				/* useCrypt		: true,			// 암호화 안전송 사용 */
			}
		});
	}
	
	function getDownloadFileArr(){
		alert("들어옴====");
		var data = {
				"supIdn"   : "<c:out value="${supIdn }" />", 
				"imageCde" : "<c:out value="${imageCde }" />",
				"group"    : "<c:out value="${group }" />"
		}
		alert("11====")
		ajaxCallJson("/supply/getFileList.do", data, function(result){
			alert("222====")
			if(result != null && result.list != null){
				var obj = {};
				obj["downloadControl"] = result.list;
				
				var rtn = innorix.api.presetDownloadFiles(obj); // 컨트롤에 다운로드 목록 갱신
				console.log("파일등록 성공여부" + rtn);
				alert(rtn);
				//closeLayerPop();
				$("#commonPop").css("display", "none");
				rtn = innorix.api.download('downloadControl');
				console.log("파일다운 성공여부" + rtn);
			}
		}, function(){
			console.log("파일목록 조회 오류");
		});
	}
	
</script>