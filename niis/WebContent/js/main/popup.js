$(document).ready(function(){
	
	ajaxCallJson("/main/getMainPopup.do", "", function(result, data){
		if(result != null && result.list != null){
			for(var i=0; i<result.list.length; i++){
				openPopup(result.list[i], 350, 73);
			}
		}
	});
	
	openNotice();
});

function openNotice(){
	var cookeiName = "popnotice";
	
	if(getCookie(cookeiName) != "no"){
		
		var url = "/niis/html/html/notice_2023.html";
		var option = "width=480px,height=640px,top=20px,left=20px,location=no,menubar=no,toolbar=no,scrollbars=no,status=no";
		
		window.open(url, cookeiName, option);
	}
}
function openPopup(map, width, height){
	
	var cookeiName = "pop" + map.boardSeq;
	
	if(getCookie(cookeiName) != "no"){
		
		var top = map.popPosY;
		var left = map.popPosX;
		/* 지리원 IE상에서 TAB으로 오픈됨
		var url = "/niis/main/openPopup.do";
		var option = "width=" + width + "px,height=" + height + "px,top=" + top + "px,left=" + left +"px,location=no,menubar=no,toolbar=no,scrollbars=no,status=no";
		
		window.open('', cookeiName, option);
		
		$("#popupFrom #boardCate").val(map.boardCate);
		$("#popupFrom #boardSeq").val(map.boardSeq);
		$("#popupFrom #popGbnCde").val(map.popGbnCde);
		$("#popupFrom #title").val(map.title);
		$("#popupFrom #contents").val(map.contents);
		$("#popupFrom #imgUrl").val(map.imgUrl);
		
		$("#popupFrom").prop("action", url);
		$("#popupFrom").prop("target", cookeiName);
		$("#popupFrom").prop("method", "post");
		$("#popupFrom").submit();
		*/
		var contents = map.contents != null ? map.contents.replace(/\n/g, "<br/>") : map.contents;
		
		var url = "/niis/main/openPopup.do?boardCate="+map.boardCate+"&boardSeq="+map.boardSeq+"&popGbnCde="+map.popGbnCde+"&title="+map.title+"&contents="+contents+"&imgUrl="+map.imgUrl;
		var option = "width=" + width + "px,height=" + height + "px,top=" + top + "px,left=" + left +"px,location=no,menubar=no,toolbar=no,scrollbars=no,status=no";
		
		window.open(url, cookeiName, option);
	}
}

//쿠키값을 가져오는 함수
function getCookie(name){
	var Found = false;
	var start, end;
	var i = 0;
	
	while(i <= document.cookie.length) {
		start = i;
		end = start + name.length;
		
		if(document.cookie.substring(start, end) == name) {
			Found = true;
			break;
		}
		i++;
	}
	
	if(Found == true) {
		start = end + 1;
		end = document.cookie.indexOf(";", start);
		
		if(end < start) end = document.cookie.length;
		return document.cookie.substring(start, end);
	}
	return "";
}