<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tit">
	<h3>워터마크 추출</h3>
	<a href="#none" class="btnLayerClose"> <img
		src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div class="con">
	<div id="downloadControl" style="display: none"></div>
	
	<form id="writeForm" name="writeForm" action="./result.jsp" method="post">
    
    </form>
    
	 <div id="uploadControl"></div><br />
	 <input type="button" value="파일추가" onclick="innorix.api.openFileDialog('uploadControl');" />
    <input type="button" value="폴더추가" onclick="innorix.api.openFolderDialog('uploadControl', true);" />
    <input type="button" value="업로드 시작" onclick="innorix.api.upload('uploadControl');" />
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/innorix/common/innorix.config.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		initInnorix();
	});	
		
	function initInnorix(){

		 // 컨트롤 설치확인
        if (innorix.api.isPluginInstalled() == false) {
            innorix.api.goToInstallPage("<%=request.getContextPath()%>/innorix/common/install/install.html") // 컨트롤 설치안내 웹 페이지로 전환
        }
		 
        // 컨트롤 이벤트 리스너
        innorix.event = {
            loadComplete    : function(controlId, param) { // 컨트롤 로딩 완료
				
            },
            uploadCancel    : function(controlId, param) { // 업로드 취소
			
            },
            uploadError     : function(controlId, param) { // 업로드 에러
				alert("워터마크 추출에 실패하였습니다.\n영상을 확인해주세요.");
            },
            uploadComplete  : function(controlId, param) { // 업로드 완료
            	alert("추출이 완료되었습니다.");
    			window.close();
            }
        }
		
        // 컨트롤 생성
        innorix.api.create({
            uploadControl: {                                    // 컨트롤 id
                product         : "innoexu",                    // innoex 업로드
                uploadUrl       : "<%=request.getContextPath()%>/security/fileUpload.fu?param=value",   // 업로드 url
                limitExtension  : ["exe", "msi", "cab"],        // 정의된 확장자 파일 제외
                maxFileSize     : 1024 * 1024 * 1024 * 5,       // 첨부가능 파일 전체 크기(bytes)
                maxTotalSize    : 1024 * 1024 * 1024 * 5,       // 첨부가능 1개 파일 크기(bytes)
                maxFileCount    : 10                            // 첨부가능 파일 전체 개수
            /* --- 추가 속성 ---
                allowExtension  : ["txt", "doc", "xls"],        // 정의된 확장자 파일 허용
                charset         : "utf-8",                      // 캐릭터셋 설정, utf-8 | euc-kr
                controlType     : "default",                    // 컨트롤 타입, default | bigfile1 | bigfile2 | htmlAlike
                width           : 550,                          // 컨트롤 너비(pixel)
                height          : 200,                          // 컨트롤 높이(pixel)
                contextMenu     : true,                         // 마우스 오른쪽 버튼 클릭 컨텍스트 메뉴 활성                
                dropZone        : true                          // 파일 드랍존 활성
            */
            }
        });
	}
	
	
</script>