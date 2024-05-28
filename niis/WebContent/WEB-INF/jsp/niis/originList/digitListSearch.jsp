<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%> 

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>

<style>
	.scroll{
					height: 250px !important;
	}
	
	#mapCheckList {
					height: 500px !important;
	}
	
	.select{
					width: 244px !important;
	}
</style>   
<script>
	$(document).ready(function(){
	});
	
	
	/******************************************* 코드조회 시작 *******************************************/
	// 수치지형도 종류 선택시
	function chgSearchKnd(){
		var mapKindCd = $("select[name='mapKindCd']").val();
		
		switch (mapKindCd) {
		
		  case "01":	// 수치지형도V1.0
		  	// 축척
		  	$("#mapScale_01").css("display","");
		  	$("#mapScale_04").css("display","none");
		  	$("#mapScale_02").css("display","none");
		  	$("#mapScale_03").css("display","none");
		  	$("#mapScale_20").css("display","none");
		  	
		  	// 좌표계
			$("#coordDvsnCd_01").css("display","");
		  	$("#coordDvsnCd_04").css("display","none");
		  	$("#coordDvsnCd_02").css("display","none");
		  	$("#coordDvsnCd_03").css("display","none");
		  	$("#coordDvsnCd_20").css("display","none");
		  	
		  	// 공개여부
			$("#openDvsnCd_01").css("display","");
		  	$("#openDvsnCd_04").css("display","none");
		  	$("#openDvsnCd_02").css("display","none");
		  	$("#openDvsnCd_03").css("display","none");
		  	$("#openDvsnCd_20").css("display","none");
		    break;
		    
		  case "04":	// 수치지형도V2.0
		  	// 축척
		  	$("#mapScale_01").css("display","none");
		  	$("#mapScale_04").css("display","");
		  	$("#mapScale_02").css("display","none");
		  	$("#mapScale_03").css("display","none");
		  	$("#mapScale_20").css("display","none");
		  	
		  	// 좌표계
			$("#coordDvsnCd_01").css("display","none");
		  	$("#coordDvsnCd_04").css("display","");
		  	$("#coordDvsnCd_02").css("display","none");
		  	$("#coordDvsnCd_03").css("display","none");
		  	$("#coordDvsnCd_20").css("display","none");
		  	
		  	// 공개여부
			$("#openDvsnCd_01").css("display","none");
		  	$("#openDvsnCd_04").css("display","");
		  	$("#openDvsnCd_02").css("display","none");
		  	$("#openDvsnCd_03").css("display","none");
		  	$("#openDvsnCd_20").css("display","none");
		    break;
		    
		  case "02":	// 토지특성도
		  	// 축척
		  	$("#mapScale_01").css("display","none");
		  	$("#mapScale_04").css("display","none");
		  	$("#mapScale_02").css("display","");
		  	$("#mapScale_03").css("display","none");
		  	$("#mapScale_20").css("display","none");
		  	
		  	// 좌표계
			$("#coordDvsnCd_01").css("display","none");
		  	$("#coordDvsnCd_04").css("display","none");
		  	$("#coordDvsnCd_02").css("display","");
		  	$("#coordDvsnCd_03").css("display","none");
		  	$("#coordDvsnCd_20").css("display","none");
		  	
		  	// 공개여부
			$("#openDvsnCd_01").css("display","none");
		  	$("#openDvsnCd_04").css("display","none");
		  	$("#openDvsnCd_02").css("display","");
		  	$("#openDvsnCd_03").css("display","none");
		  	$("#openDvsnCd_20").css("display","none");
		    break;
		    
		  case "03":	// 토지이용현황도
		  	// 축척
		  	$("#mapScale_01").css("display","none");
		  	$("#mapScale_04").css("display","none");
		  	$("#mapScale_02").css("display","none");
		  	$("#mapScale_03").css("display","");
		  	$("#mapScale_20").css("display","none");
		  	
		  	// 좌표계
			$("#coordDvsnCd_01").css("display","none");
		  	$("#coordDvsnCd_04").css("display","none");
		  	$("#coordDvsnCd_02").css("display","none");
		  	$("#coordDvsnCd_03").css("display","");
		  	$("#coordDvsnCd_20").css("display","none");
		  	
		  	// 공개여부
			$("#openDvsnCd_01").css("display","none");
		  	$("#openDvsnCd_04").css("display","none");
		  	$("#openDvsnCd_02").css("display","none");
		  	$("#openDvsnCd_03").css("display","");
		  	$("#openDvsnCd_20").css("display","none");
		    break;
		    
		  case "20":	// 연역해안도
		  	// 축척
		  	$("#mapScale_01").css("display","none");
		  	$("#mapScale_04").css("display","none");
		  	$("#mapScale_02").css("display","none");
		  	$("#mapScale_03").css("display","none");
		  	$("#mapScale_20").css("display","");
		  	
		  	// 좌표계
			$("#coordDvsnCd_01").css("display","none");
		  	$("#coordDvsnCd_04").css("display","none");
		  	$("#coordDvsnCd_02").css("display","none");
		  	$("#coordDvsnCd_03").css("display","none");
		  	$("#coordDvsnCd_20").css("display","");
		  	
		  	// 공개여부
			$("#openDvsnCd_01").css("display","none");
		  	$("#openDvsnCd_04").css("display","none");
		  	$("#openDvsnCd_02").css("display","none");
		  	$("#openDvsnCd_03").css("display","none");
		  	$("#openDvsnCd_20").css("display","");
		    break;
		}
		
	}
	
	// 수치지형도V1.0 1:5,000, 1:25,000 공개제한 => 공개로 변경
	// 실질적으로 1:5,000 이상의 축척은 공개제한이 의미가 없음
	$(document).on("change", "#mapScale_01", function(){

		var selectMapScale = $("#mapScale_01 select[name=mapScale] option:selected").val();
		
		// 축척이 1:5,000 미만인 수치지형도는 공개, 공개제한 코드 있음
		var openDvsnCdHtml = "";
		openDvsnCdHtml += "<select name='openDvsnCd' class='select'>";
		openDvsnCdHtml += "		<option value='01'>공개</option>";
		openDvsnCdHtml += "		<option value='02'>공개제한</option>";
		openDvsnCdHtml += "</select>";
		
		
		// 축척이 1:5,000 미만인 수치지형도는 공개제한을 공개로 바꾸고 공개로 표기한 코드만 있음
		var chgOpenDvsnCdHtml = "";
		chgOpenDvsnCdHtml += "<select name='openDvsnCd' class='select'>";
		chgOpenDvsnCdHtml += "		<option value='02'>공개</option>";
		chgOpenDvsnCdHtml += "</select>";
		
		
		switch (selectMapScale) {
			case "01":
				$("#openDvsnCd_01").empty();
				$("#openDvsnCd_01").append(openDvsnCdHtml);
				break;
			case "09":
				$("#openDvsnCd_01").empty();
				$("#openDvsnCd_01").append(openDvsnCdHtml);
				break;
			case "02":
				$("#openDvsnCd_01").empty();
				$("#openDvsnCd_01").append(chgOpenDvsnCdHtml);
				break;
			case "03":
				$("#openDvsnCd_01").empty();
				$("#openDvsnCd_01").append(chgOpenDvsnCdHtml);
				break;
			case "05":
				$("#openDvsnCd_01").empty();
				$("#openDvsnCd_01").append(chgOpenDvsnCdHtml);
				break;
		}
	});
	
	
	// 수치지형도V2.0 1:5,000 공개제한 => 공개로 변경
	// 실질적으로 1:5,000 이상의 축척은 공개제한이 의미가 없음
	$(document).on("change", "#mapScale_04", function(){

		var selectMapScale = $("#mapScale_04 select[name=mapScale] option:selected").val();
		
		// 축척이 1:5,000 미만인 수치지형도는 공개, 공개제한 코드 있음
		var openDvsnCdHtml = "";
		openDvsnCdHtml += "<select name='openDvsnCd' class='select'>";
		openDvsnCdHtml += "		<option value='01'>공개</option>";
		openDvsnCdHtml += "		<option value='02'>공개제한</option>";
		openDvsnCdHtml += "</select>";
		
		
		// 축척이 1:5,000 미만인 수치지형도는 공개제한을 공개로 바꾸고 공개로 표기한 코드만 있음
		var chgOpenDvsnCdHtml = "";
		chgOpenDvsnCdHtml += "<select name='openDvsnCd' class='select'>";
		chgOpenDvsnCdHtml += "		<option value='02'>공개</option>";
		chgOpenDvsnCdHtml += "</select>";
		
		
		switch (selectMapScale) {
			case "01":
				$("#openDvsnCd_04").empty();
				$("#openDvsnCd_04").append(openDvsnCdHtml);
				break;
			case "09":
				$("#openDvsnCd_04").empty();
				$("#openDvsnCd_04").append(openDvsnCdHtml);
				break;
			case "02":
				$("#openDvsnCd_04").empty();
				$("#openDvsnCd_04").append(chgOpenDvsnCdHtml);
				break;
		}
	});
	
	/******************************************* 코드조회 끝 *******************************************/
	
	function appListInit(){
		
		if($("#form1 #mapCheckList ul li").length > 0){
			$("#form1 #mapCheckList .inner").css("display", "none");
			$("#form1 #mapCheckList").removeClass("noData");
			if(!$("#form1 #mapCheckList").hasClass("appList")){
				$("#form1 #mapCheckList").addClass("appList");
			}
			
		}else{
			$("#form1 #mapCheckList .inner").css("display", "");
			$("#form1 #mapCheckList").removeClass("appList");
			if(!$("#form1 #mapCheckList").hasClass("noData")){
				$("#form1 #mapCheckList").addClass("noData");
			}
		}
	}
	
	// 조회 버튼
	function fn_search(){
		$("#newListForm #pageIndex").val("1");
		$("#_pageUnit_history").css("display", "none");
		
		// 최신 지도성과 목록
		getDigitProductList();
	}
	

	// 최신 지도성과 목록
	function getDigitProductList(){
		
		$("#mapHistoryList").empty();
		$("#mapHistoryPaging").empty();
		
		var formData = {
				
					"mapKindCd" 	: $("select[name='mapKindCd']").val()
				,	"scaleCd" 		: $("select[name='mapScale']").val()
				,	"coordDvsnCd" 	: $("select[name='coordDvsnCd']").val()
				,	"openDvsnCd" 	: $("select[name='openDvsnCd']").val()
				,	"mapShtNo" 		: $("#mapShtNo").val()
				,	"mapShtNm" 		: $("#mapShtNm").val()
				,	"pageIndex"		: $("#newListForm #pageIndex").val()
				,	"pageUnit"		: $("#_pageUnit_map option:selected").val()
		};
		
		$.ajax({
					type : "POST",
					data : JSON.stringify(formData),
					dataType : "json",
					url : "/niis/originList/selectNewDigitList.do",
					async : false,
					processData : false,
					contentType : "application/json",
					cache : false,
					error : function(e){
						alert("최신 지도성과 목록 조회 오류입니다. \n 관리자에게 문의하세요.")
					},
					success : function(data){
						
						
						var newDigitList = data.selectNewDigitList;
						
						$("#mapProductList").parent().parent(".scroll").mCustomScrollbar("destroy");
						$("#mapProductList").empty();
						
						var newDigitTd = "";
						newDigitTd  +="<tr><td colspan='12'>조회된 데이터가 없습니다.</td></tr>";
						$("#mapProductList").append(newDigitTd);
						
						if( data.totalCnt != 0){
						
							$("#mapProductList").empty();
							
							for(var i=0; i<newDigitList.length; i++){
								
									newDigitTd  = "";
									newDigitTd  +="<tr>																																													";	
									newDigitTd  +="	<td>                                                                                                                                                                                ";
									newDigitTd  +="		<span class='checkbox'>    			";
									newDigitTd  +="				<input type='checkbox'  id='mapRadio'			name='mapRadio'				value='"+	newDigitList[i].mapSerNo	+ "_" + 	newDigitList[i].mapShtNo	+"'/>		";
									newDigitTd  +="				<input type='hidden' 	id='chkImageCde' 		name='chkImageCde' 			value='"+	newDigitList[i].imageCde	+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkMapSerNo' 		name='chkMapSerNo' 			value='"+	newDigitList[i].mapSerNo	+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkMapShtNo' 		name='chkMapShtNo' 			value='"+	newDigitList[i].mapShtNo	+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkMapHistoryNo' 	name='chkMapHistoryNo' 		value='"+	newDigitList[i].mapHistoryNo+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkMapKindCd' 		name='chkMapKindCd' 		value='"+	newDigitList[i].mapKindCd	+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkMapKindNm' 		name='chkMapKindNm' 		value='"+	newDigitList[i].mapKindNm	+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkScaleCd' 		name='chkScaleCd' 			value='"+	newDigitList[i].scaleCd		+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkScaleNm' 		name='chkScaleNm' 			value='"+	newDigitList[i].scaleNm		+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkNoticeNo' 		name='chkNoticeNo' 			value='"+	newDigitList[i].noticeNo	+"' />";
									newDigitTd  +="				<input type='hidden' 	id='chkProductYear' 	name='chkProductYear' 		value='"+	newDigitList[i].productYear	+"' />";
									newDigitTd  +="		</span>     																																									";
									newDigitTd  +="	</td>                                                                                                                                                                               ";
									newDigitTd  +="	<td>                                                                                                                                                                                ";
									newDigitTd  +="		<input type='button' 	onclick='javascript:fn_history_search(" + newDigitList[i].mapSerNo + ",\"" + newDigitList[i].mapShtNo + "\");' 	value='조회'/>   							";
									newDigitTd  +="	</td>                                                                                                                                                                               ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].mapFormDvsnNm + "-" + newDigitList[i].mapKindNm + "-" + newDigitList[i].scaleNm +"</a></td> ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].mapShtNo    +"</a></td>                                       ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].mapShtNm    +"</a></td>                                       ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].mapKindNm   +"</a></td>                                       ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].scaleNm     +"</a></td>                                       ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].coordDvsnNm +"</a></td>                                       ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].openDvsnNm  +"</a></td>                                       ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].projectNo   +"</a></td>                                       ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].noticeDate  +"</a></td>                                       ";
									newDigitTd  +="	<td><a href='#none' onclick='newDigitDetail("+  newDigitList[i].mapSerNo +", "+ newDigitList[i].mapShtNo +", "+newDigitList[i].mapHistoryNo +")'>"+newDigitList[i].productYear +"</a></td>                                       ";
									newDigitTd  +="</tr>																																												";
							
									
									$("#mapProductList").append(newDigitTd);
							}
							
						//선택 내역 존재시 체크박스 선택 상태로 변경
						$("#mapProductList input:checkbox[id=mapRadio]").each(function(i){
							var len = $("#form1 #mapCheckList ul div[id='" + $(this).val() +"_" + newDigitList[i].mapHistoryNo  + "']").length;
							
							if(len > 0){
								$(this).prop("checked", true).change();
								$(this).parent(".checkbox").addClass('active');
								if($(this).parents("tr").is('tr')){
									$(this).parents("tr").addClass('active');
								}
							}
						});
						
						$("#mapProductList").parent().parent(".scroll").mCustomScrollbar();
						appListInit();
					 	$('#mapPaging').empty(); // 페이징 비우기
					 	
					 	// fn_page_btn(페이징 버튼 div id, 페이지사이즈, 페이지번호, 총데이터수)
					 	fn_page_btn("mapPaging", $("#_pageUnit_map option:selected").val(), $("#newListForm #pageIndex").val(), data.totalCnt);
					 	
					}
						
						
				}
		});
	}
	
	
	
	
	// 과거지도 조회 버튼
	function fn_history_search(mapSerNo, mapShtNo){
		
		$("#historyListForm #pageIndex").val("1");
		
		getHistoryDigitProductList(mapSerNo, mapShtNo)
	}
	

	/* 과거 지도성과이력 목록*/
	function getHistoryDigitProductList(mapSerNo, mapShtNo){
		
		$("#_pageUnit_history").css("display", "block");
		
		$("#historyListForm #mapSerNo").val(mapSerNo);
		$("#historyListForm #mapShtNo").val(mapShtNo);
	
		var formData = {
				
					"mapSerNo" 		: mapSerNo
				,	"mapShtNo" 		: mapShtNo
				,	"pageIndex"		: $("#historyListForm #pageIndex").val()
				,	"pageUnit"		: $("#_pageUnit_history option:selected").val()
		};
		
		$.ajax({
					type : "POST",
					data : JSON.stringify(formData),
					dataType : "json",
					url : "/niis/originList/selectHistoryDigitList.do",
					async : false,
					processData : false,
					contentType : "application/json",
					cache : false,
					error : function(e){
						alert("과거 지도성과이력 목록 조회 오류입니다. \n 관리자에게 문의하세요.")
					},
					success : function(data){
						
						
						var historyDigitList = data.selectHistoryDigitList;
						
						$("#mapHistoryList").parent().parent(".scroll").mCustomScrollbar("destroy");
						$("#mapHistoryList").empty();
						
						var historyDigitTd = "";
						historyDigitTd += "	<tr><td colspan='7'>조회된 데이터가 없습니다.</td></tr>";
						$("#mapHistoryList").append(historyDigitTd);
						
						
						$('#mapHistoryPaging').empty(); // 페이징 비우기
						
						if( data.totalCnt != 0){
						
							$("#mapHistoryList").empty();
							
							for(var i=0; i<historyDigitList.length; i++){
							
								historyDigitTd = "";
								historyDigitTd += "	<tr>                                                                                                                                                           ";
								historyDigitTd += "		<td>                                                                                                                                                       ";
								historyDigitTd +="		<span class='checkbox'>    			";
								historyDigitTd +="				<input type='checkbox'  id='mapRadio'			name='mapRadio'				value='"+	historyDigitList[i].mapSerNo	+ "_" + 	historyDigitList[i].mapShtNo	+"'/>";
								historyDigitTd +="				<input type='hidden' 	id='chkImageCde' 		name='chkImageCde' 			value='"+	historyDigitList[i].imageCde	+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkMapSerNo' 		name='chkMapSerNo' 			value='"+	historyDigitList[i].mapSerNo	+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkMapShtNo' 		name='chkMapShtNo' 			value='"+	historyDigitList[i].mapShtNo	+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkMapHistoryNo' 	name='chkMapHistoryNo' 		value='"+	historyDigitList[i].mapHistoryNo+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkMapKindCd' 		name='chkMapKindCd' 		value='"+	historyDigitList[i].mapKindCd	+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkMapKindNm' 		name='chkMapKindNm' 		value='"+	historyDigitList[i].mapKindNm	+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkScaleCd' 		name='chkScaleCd' 			value='"+	historyDigitList[i].scaleCd		+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkScaleNm' 		name='chkScaleNm' 			value='"+	historyDigitList[i].scaleNm		+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkNoticeNo' 		name='chkNoticeNo' 			value='"+	historyDigitList[i].noticeNo	+"' />";
								historyDigitTd +="				<input type='hidden' 	id='chkProductYear' 	name='chkProductYear' 		value='"+	historyDigitList[i].productYear	+"' />";
								historyDigitTd +="		</span>     																																			   ";
								historyDigitTd += "		</td>                                                                                                                                                      ";
								historyDigitTd += "		<td><a href='#none' onclick='historyDigitDetail(" + historyDigitList[i].mapSerNo + ", " + historyDigitList[i].mapShtNo + ", " + historyDigitList[i].mapHistoryNo + ")'>" + historyDigitList[i].mapShtNo + "</a></td>          ";
								historyDigitTd += "		<td><a href='#none' onclick='historyDigitDetail(" + historyDigitList[i].mapSerNo + ", " + historyDigitList[i].mapShtNo + ", " + historyDigitList[i].mapHistoryNo + ")'>" + historyDigitList[i].noticeNo + "</a></td>          ";
								historyDigitTd += "		<td><a href='#none' onclick='historyDigitDetail(" + historyDigitList[i].mapSerNo + ", " + historyDigitList[i].mapShtNo + ", " + historyDigitList[i].mapHistoryNo + ")'>" + historyDigitList[i].noticeDate + "</a></td>        ";
								historyDigitTd += "		<td><a href='#none' onclick='historyDigitDetail(" + historyDigitList[i].mapSerNo + ", " + historyDigitList[i].mapShtNo + ", " + historyDigitList[i].mapHistoryNo + ")'>" + historyDigitList[i].productYear + "</a></td>       ";
								historyDigitTd += "		<td><a href='#none' onclick='historyDigitDetail(" + historyDigitList[i].mapSerNo + ", " + historyDigitList[i].mapShtNo + ", " + historyDigitList[i].mapHistoryNo + ")'>" + historyDigitList[i].checkInDate + "</a></td>       ";
								historyDigitTd += "		<td><a href='#none' onclick='historyDigitDetail(" + historyDigitList[i].mapSerNo + ", " + historyDigitList[i].mapShtNo + ", " + historyDigitList[i].mapHistoryNo + ")'>" + historyDigitList[i].checkInReason + "</a></td>     ";
								historyDigitTd += "	</tr>                                                                                                                                                          ";
									
								$("#mapHistoryList").append(historyDigitTd);
								
							}
						
						//선택 내역 존재시 체크박스 선택 상태로 변경
						$("#mapHistoryList input:checkbox[id=mapRadio]").each(function(i){
							var len = $("#form1 #mapCheckList ul div[id='" + $(this).val() +"_" + historyDigitList[i].mapHistoryNo  + "']").length;
							
							if(len > 0){
								$(this).prop("checked", true).change();
								$(this).parent(".checkbox").addClass('active');
								if($(this).parents("tr").is('tr')){
									$(this).parents("tr").addClass('active');
								}
							}
						});	
							
						$("#mapHistoryList").parent().parent(".scroll").mCustomScrollbar();
						appListInit();
					 	$('#mapHistoryPaging').empty(); // 페이징 비우기
					 	
					 	// fn_page_btn(페이징 버튼 div ID, 페이지사이즈, 페이지번호, 총데이터수)
					 	fn_page_btn("mapHistoryPaging", $("#_pageUnit_history option:selected").val(), $("#historyListForm #pageIndex").val(), data.totalCnt);
						
					}
						
				}
		});
		
		
	}
	
	/* $("#mapPaging") 페이지 링크 function */
	function fn_mapPaging(pageNo){
	
	  // 선택한 페이지 번호 저장
	  $("#newListForm #pageIndex").val(pageNo);
	  
	  // 최신 지도성과 목록
	  getDigitProductList();
	}
	
	
	/* $("#mapHistoryPaging") 페이지 링크 function */
	function fn_mapHistoryPaging(pageNo){
	  // 선택한 페이지 번호 저장
	  $("#historyListForm #pageIndex").val(pageNo);
	  
	  var mapSerNo = $("#historyListForm #mapSerNo").val();
	  var mapShtNo = $("#historyListForm #mapShtNo").val();
	  
	  // 과거 지도성과이력 목록
	  getHistoryDigitProductList(mapSerNo, mapShtNo);
	}
	
	
	
	// 최신 지도성과 목록 상세조회	
 	function newDigitDetail(mapSerNo, mapShtNo, mapHistoryNo){
 		
 		var formData = {
 		
 						"mapSerNo" 		: mapSerNo
 					,	"mapShtNo" 		: mapShtNo
 					,	"mapHistoryNo"	: mapHistoryNo
 		};
 		
 		
 	
		$.ajax({
					type : "POST",
					data : JSON.stringify(formData),
					dataType : "json",
					url : "/niis/originList/selectNewDigitDetail.do",
					async : false,
					processData : false,
					contentType : "application/json",
					cache : false,
					error : function(e){
						alert("최신 지도성과 상세조회 조회 오류입니다. \n 관리자에게 문의하세요.")
					},
					success : function(data){
						
						setTimeout(function() { 
							$("#newDialog").dialog({
								       title	: "최신 지도성과 상세조회"
								     , modal	: true
								     , width	: "700"
								     , height	: "480"
							    	 , show		: {
								            effect: "blind",
								            duration: 1000
								          }
							});
						}, 550);
						
						var digitTopoMapVO = data.digitTopoMapVO;
						
						$("#dialog_kindNm").text(digitTopoMapVO.mapFormDvsnNm + "-" + digitTopoMapVO.mapKindNm + "-" + digitTopoMapVO.scaleNm);			// 지도분류
						$("#dialog_mapShtNo").text(digitTopoMapVO.mapShtNo);			// 도엽번호
						$("#dialog_mapShtNm").text(digitTopoMapVO.mapShtNm);			// 도엽명
						$("#dialog_mapFormDvsnNm").text(digitTopoMapVO.mapFormDvsnNm);	// 지도유형
						$("#dialog_mapKindNm").text(digitTopoMapVO.mapKindNm);			// 지도종류
						$("#dialog_scaleNm").text(digitTopoMapVO.scaleNm);				// 축척
						$("#dialog_coordDvsnNm").text(digitTopoMapVO.coordDvsnNm);		// 좌표계
						$("#dialog_openDvsnNm").text(digitTopoMapVO.openDvsnNm);		// 공개여부
						$("#dialog_noticeNo").text(digitTopoMapVO.noticeNo);			// 고시번호
						$("#dialog_noticeDate").text(digitTopoMapVO.noticeDate);		// 고시일자
						$("#dialog_surveyYear").text(digitTopoMapVO.surveyYear);		// 조사연도
						$("#dialog_productYear").text(digitTopoMapVO.productYear);		// 제작연도
						$("#dialog_photoYear").text(digitTopoMapVO.photoYear);			// 촬영연도
						$("#dialog_mediaNo").text(digitTopoMapVO.mediaNo);				// 매체번호
						
						if(digitTopoMapVO.mediaNo == null || digitTopoMapVO.mediaNo == ""){
							$("#dialog_mediaNo").text("-");								// 매체번호
						}
					}
						
		});
 	}
 	


 	// 과거 지도성과이력 목록 상세조회	
 	function historyDigitDetail(mapSerNo, mapShtNo, mapHistoryNo){
 	
 		var formData = {
 		
 						"mapSerNo" 		: mapSerNo
 					,	"mapShtNo" 		: mapShtNo
 					,	"mapHistoryNo"	: mapHistoryNo
 		};
 		
 		
 		
		$.ajax({
					type : "POST",
					data : JSON.stringify(formData),
					dataType : "json",
					url : "/niis/originList/selectHistoryDigitDetail.do",
					async : false,
					processData : false,
					contentType : "application/json",
					cache : false,
					error : function(e){
						alert("과거 지도성과 상세조회 조회 오류입니다. \n 관리자에게 문의하세요.")
					},
					success : function(data){
						
						setTimeout(function() { 
							$("#historyDialog").dialog({
								       title	: "과거 지도성과 상세조회"
								     , modal	: true
								     , width	: "700"
								     , height	: "480"
							    	 , show		: {
								            effect: "blind",
								            duration: 1000
								          }
							});
						}, 550);
						
						var digitTopoMapVO = data.digitTopoMapVO;
						
						$("#historyDiglog_mapShtNo").text(digitTopoMapVO.mapShtNo);				// 도엽번호
						$("#historyDiglog_mapShtNm").text(digitTopoMapVO.mapShtNm);				// 도엽명
						$("#historyDiglog_mapFormDvsnNm").text(digitTopoMapVO.mapFormDvsnNm);	// 지도유형
						$("#historyDiglog_mapKindNm").text(digitTopoMapVO.mapKindNm);			// 지도종류
						$("#historyDiglog_scaleNm").text(digitTopoMapVO.scaleNm);				// 축척
						$("#historyDiglog_coordDvsnNm").text(digitTopoMapVO.coordDvsnNm);		// 좌표계
						$("#historyDiglog_openDvsnNm").text(digitTopoMapVO.openDvsnNm);			// 공개여부
						$("#historyDiglog_noticeNo").text(digitTopoMapVO.noticeNo);				// 고시번호
						$("#historyDiglog_noticeDate").text(digitTopoMapVO.noticeDate);			// 고시일자
						$("#historyDiglog_surveyYear").text(digitTopoMapVO.surveyYear);			// 조사연도
						$("#historyDiglog_productYear").text(digitTopoMapVO.productYear);		// 제작연도
						$("#historyDiglog_photoYear").text(digitTopoMapVO.photoYear);			// 촬영연도
						$("#historyDiglog_mediaNo").text(digitTopoMapVO.mediaNo);				// 매체번호
						
						if(digitTopoMapVO.mediaNo == null || digitTopoMapVO.mediaNo == ""){
							$("#historyDiglog_mediaNo").text("-");								// 매체번호
						}
						
					}
						
		});
 	}
 	
 	
	// 신청 목록 리스트
	$(document).on("change","input[name='mapRadio']",function(){
		
		$("#mapCheckList").mCustomScrollbar();
		
    	var isChecked = $(this).is(":checked");
		var mapSerNo = $(this).parent().children("input[id=chkMapSerNo]").val().trim();
		var mapShtNo = $(this).parent().children("input[id=chkMapShtNo]").val().trim();
		var mapHistoryNo = $(this).parent().children("input[id=chkMapHistoryNo]").val().trim();
		
		var mapKindNm = $(this).parent().children("input[id=chkMapKindNm]").val();
		var scaleNm = $(this).parent().children("input[id=chkScaleNm]").val();
		var mapShtNo = $(this).parent().children("input[id=chkMapShtNo]").val();
		
		
		var liObj = $("#form1 #mapCheckList ul #" + $(this).val());
		var divObj = $("#form1 #mapCheckList ul #" + mapSerNo + "_" + mapShtNo + "_" + mapHistoryNo);
		
		if(isChecked){
			if(liObj.length == 0){
				$("#form1 #mapCheckList ul").append("<li id=\"" + $(this).val() + "\">" + mapKindNm + "-" + scaleNm + "(" + mapShtNo + ")" + "(<span>1</span>)<a href=\"#none\" onclick=\"remMapSelLi('" +  mapSerNo + "_" + mapShtNo + "')\">삭제</a></li>");
			}
			
			if(divObj.length == 0){
				$("#form1 #mapCheckList ul #" +  $(this).val()).append("<div id=\"" + mapSerNo + "_" + mapShtNo + "_" + mapHistoryNo + "\" style=\"display:none;\"></div>");
				$("#form1 #mapCheckList ul #" +  $(this).val()  +" div[id='" + mapSerNo + "_" + mapShtNo + "_" + mapHistoryNo + "']").append($(this).parent().children("input[type=hidden]").clone());
			}
		}else{
			if(liObj.children("div").length < 2){
				liObj.remove();
			}else{
				divObj.remove();
			}
		}
		
		liObj.children("span").text(liObj.children("div").length);
		$("#mapAppTotCnt").text($("#form1 #mapCheckList ul li div").length);
		
		appListInit();
	});
	
	//신청 목록 삭제
	function remMapSelLi(mapNo){
		
		var mapNo = mapNo.trim();
		
		$("#form1 #mapCheckList ul #" + mapNo + " div").each(function(){
			//최신 지도성과이력 목록 테이블
			$("#mapProductList input:checkbox[id=mapRadio]").each(function(i){
				
				var checkboxVal = $("#mapProductList > tr:eq("+ i +") input:checkbox[name='mapRadio']").val();
				
				if(mapNo == checkboxVal){
					$(this).prop("checked", false);
					$(this).parent(".checkbox").removeClass('active');
					if($(this).parents("tr").is('tr')){
						$(this).parents("tr").removeClass('active');
					}
				}
			});
			//과거 지도성과이력 목록 테이블
			$("#mapHistoryList input:checkbox[id=mapRadio]").each(function(i){
				var checkboxVal = $("#mapHistoryList > tr:eq("+ i +") input:checkbox[name='mapRadio']").val();
				
				if(mapNo == checkboxVal){
					$(this).prop("checked", false);
					$(this).parent(".checkbox").removeClass('active');
					if($(this).parents("tr").is('tr')){
						$(this).parents("tr").removeClass('active');
					}
				}
			});
		});
		
		
		$("#form1 #mapCheckList ul #" + mapNo).remove();
		$("#mapAppTotCnt").text($("#form1 #mapCheckList ul li div").length);
		
		appListInit();
		
	}
	
	
	
	//신청 항목 확인 팝업
	function digitListSrchPop(){
		
		if(($("#form1 #mapCheckList ul li div").length) > 50){
			alert("영상은 50건까지 신청할 수 있습니다.");
			return false;
		}
		
		/* 확인페이지 생략하고 바로 신청서 작성 페이지로 */
		if($("#form1 #mapCheckList ul li div").length < 1){
			alert("영상을 선택해 주세요.");
			return;
		}
		
		var imageCde 		= new Array();
		var mapSerNo 		= new Array();	//keyVal1 2
		var mapHistoryNo 	= new Array();	//keyVal2 3
		var mapShtNo 		= new Array();	//keyVal3 1
		var mapKindNm 		= new Array();
		var scaleNm 		= new Array();
		var noticeNo 		= new Array();
		var productYear 	= new Array();
		
		$("#form1 #mapCheckList ul li div").each(function(){
			imageCde.push($(this).children("input:hidden[id=chkImageCde]").val());
			mapSerNo.push($(this).children("input:hidden[id=chkMapSerNo]").val());
			mapHistoryNo.push($(this).children("input:hidden[id=chkMapHistoryNo]").val());
			mapShtNo.push($(this).children("input:hidden[id=chkMapShtNo]").val());
			mapKindNm.push($(this).children("input:hidden[id=chkMapKindNm]").val());
			scaleNm.push($(this).children("input:hidden[id=chkScaleNm]").val());
			noticeNo.push($(this).children("input:hidden[id=chkNoticeNo]").val());
			productYear.push($(this).children("input:hidden[id=chkProductYear]").val());
		});
		
		var data = {
				"imageCde"		: imageCde 			
				,"keyVal2"		: mapSerNo
				,"keyVal3"		: mapHistoryNo
				,"keyVal1"		: mapShtNo
				,"mapKindNm"	: mapKindNm
				,"scaleNm"		: scaleNm
				,"noticeNo"		: noticeNo
				,"productYear"	: productYear
		};
		
		// ApplyController.java
		ajaxCallPop("/apply/regApplyPop.do", data, "totalApplication", "800");
		
		
	}
	

	// 최신 지도성과 목록 전체 선택
	$(document).on("change", "#chkNewDigit", function(){
		var isChecked = $(this).is(":checked");
		
		$("#mapProductList input[type=checkbox]").each(function(i){
			if(isChecked != $(this).is(":checked")){
				$(this).click();
			}
		});
		
		appListInit();
	});
	
	// 과거 지도성과이력 목록 전체 선택
	$(document).on("change", "#chkHistoryDigit", function(){
		var isChecked = $(this).is(":checked");
		
		$("#mapHistoryList input[type=checkbox]").each(function(i){
			if(isChecked != $(this).is(":checked")){
				$(this).click();
			}
		});
		
		appListInit();
	});

	
	// 최신 지도성과 목록 페이징 갯수 선택
	function changePageUnitNewDigit(){
		
		// 최신 지도성과 목록 
		getDigitProductList();
	}
	
	// 과거 지도성과이력 목록 페이징 갯수 선택
	function changePageUnitDigitHistory(){
		
		var mapSerNo = $("#historyListForm #mapSerNo").val();
		var mapShtNo = $("#historyListForm #mapShtNo").val();
		
		// 과거 지도성과 목록
		getHistoryDigitProductList(mapSerNo, mapShtNo);
	}
</script>

<!-- .twoDepthMenu -->
<div class="twoDepthMenu">
	<div class="top">
		<h3 class="tit">
			<a href="#none" class="btnCloseOpen"></a>조회 항목
			<a href="#none" id="serviceGuide_13" class="btnServiceGuide">
				<img src="/niis/images/common/btn_service_guide.png" alt="도움말" title="도움말" />
			</a>
		</h3>
		<div class="parentWrap">
			<!-- .boardStyle_1 -->
			<div class="boardStyle_1">
				<table>
					<colgroup>
						<col width="85px" />
						<col width="*" />
					</colgroup>
					<tbody>
						<tr>
							<th class="colorOr">수치지형도 *</th>
							<td>
								<select id="mapKindCd" name="mapKindCd" class="select" onchange="javascript:chgSearchKnd();">
									<option value="01">수치지형도V1.0</option>
									<option value="04">수치지형도V2.0</option>
									<option value="02">토지특성도</option>
									<option value="03">토지이용현황도</option>
									<option value="20">연안해역도</option>
								</select>
							</td>
						</tr>
						<tr id="mapScaleTr">
							<th class="colorOr">축척 *</th>
							<!-- 수치지형도V1.0 -->
							<td id="mapScale_01">
								<select name="mapScale" class="select">
									<option value="01">1,000</option>
									<option value="09">2,500</option>
									<option value="02">5,000</option>
									<option value="03">25,000</option>
									<option value="05">250,000</option>
								</select>
							</td>
							<!-- 수치지형도V2.0 -->
							<td id="mapScale_04" style="display: none;">	
								<select name="mapScale" class="select">
									<option value="01">1,000</option>
									<option value="09">2,500</option>
									<option value="02">5,000</option>
								</select>
							</td>
							<!-- 토지특성도 -->
							<td id="mapScale_02" style="display: none;">	
								<select name="mapScale" class="select">
									<option value="01">1,000</option>
									<option value="02">5,000</option>
								</select>
							</td>
							<!-- 토지이용현황도 -->
							<td id="mapScale_03" style="display: none;">	
								<select name="mapScale" class="select">
									<option value="03">25,000</option>
								</select>
							</td>
							<!-- 연안해역도 -->
							<td id="mapScale_20" style="display: none;">	
								<select name="mapScale" class="select">
									<option value="03">25,000</option>
								</select>
							</td>	
							
						</tr>
						<tr>
							<th>좌표계</th>
							<!-- 수치지형도V1.0 -->
							<td id="coordDvsnCd_01">
								<select name="coordDvsnCd" class="select">
									<option value="01">GRS80</option>
								</select>
							</td>
							<!-- 수치지형도V2.0 -->
							<td id="coordDvsnCd_04" style="display: none;">
								<select name="coordDvsnCd" class="select">
									<option value="01">GRS80</option>
								</select>
							</td>
							<!-- 토지특성도 -->
							<td id="coordDvsnCd_02" style="display: none;">
								<select name="coordDvsnCd" class="select">
									<option value="02">BESSEL</option>
								</select>
							</td>
							<!-- 토지이용현황도 -->
							<td id="coordDvsnCd_03" style="display: none;">
								<select name="coordDvsnCd" class="select">
									<option value="02">BESSEL</option>
								</select>
							</td>
							<!-- 연안해역도 -->
							<td id="coordDvsnCd_20" style="display: none;">
								<select name="coordDvsnCd" class="select">
									<option value="01">GRS80</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>공개여부</th>
							<!-- 수치지형도V1.0 -->
							<td id="openDvsnCd_01">
								<select name="openDvsnCd" class="select">
									<option value="01">공개</option>
									<option value="02">공개제한</option>
								</select>
							</td>
							<!-- 수치지형도V2.0 -->
							<td id="openDvsnCd_04" style="display: none;">
								<select name="openDvsnCd" class="select">
									<option value="01">공개</option>
									<option value="02">공개제한</option>
								</select>
							</td>
							<!-- 토지특성도 -->
							<td id="openDvsnCd_02" style="display: none;">
								<select name="openDvsnCd" class="select">
									<option value="02">공개제한</option>
								</select>
							</td>
							<!-- 토지이용현황도 -->
							<td id="openDvsnCd_03" style="display: none;">
								<select name="openDvsnCd" class="select">
									<option value="02">공개제한</option>
								</select>
							</td>
							<!-- 연안해역도 -->
							<td id="openDvsnCd_20" style="display: none;">
								<select name="openDvsnCd" class="select">
									<option value="02">공개제한</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>도엽번호</th>
							<td>
								<input type="text" id="mapShtNo" name="mapShtNo" value=""/>
							</td>
						</tr>
						<tr>
							<th>도엽명</th>
							<td>
								<input type="text" id="mapShtNm" name="mapShtNm" value=""/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- .boardStyle_1 -->
			<a href="#none" class="searchDivBtn" onclick="fn_search();">조회</a>
		</div>
		<h3 class="tit">신청 목록 (<span id="mapAppTotCnt">0</span>)</h3>
	</div>
	<form id="form1">
		<div id="mapCheckList" class="scroll noData">
			<!-- .appList -->
			<div class="inner">
				<p>추가된 신청자료가 없습니다.</p>
			</div>
			<ul>
			</ul>
			<!-- /.appList -->
		</div>
		<div class="bottom">
			<a href="#none" onclick="digitListSrchPop();">
				<img src="/niis/images/sub/btn_application_form.gif" alt="신청서 작성" title="신청서 작성" />
			</a>
		</div>
	</form>
</div>
<!-- /.twoDepthMenu -->

<!-- 최신 지도성과 상세조회 jquery ui -->
<div id="newDialog" title="Basic dialog" style="display: none">
	<%@ include file="newDigitDetail.jsp" %>
</div>
<!-- 과거 지도성과 상세조회 jquery ui -->
<div id="historyDialog" title="Basic dialog" style="display: none">
	<%@ include file="historyDigitDetail.jsp" %>
</div>

<!-- .container -->
<form id="newListForm" >
<input type="hidden" id="pageIndex" name="pageIndex" value="1"/>
<div class="container" id="mapDiv">
	<div class="top">
		<div class="contTit">
			<div class="btnTwoDepthMenuClose"></div>
			<h3>최신 지도성과 목록</h3>
			<select id="_pageUnit_map" class="select" style="width:130px;" onchange="changePageUnitNewDigit();">
				<option value="10">10개씩 보기</option>
				<option value="25">25개씩 보기</option>
				<option value="50">50개씩 보기</option>
			</select>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="50px" />
					<col width="5%" />
					<col width="17%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="7%" />
					<col width="8%" />
					<col width="12%" />
					<col width="7%" />
				</colgroup>
				<thead id="mapProductHead">
					<tr>
						<th><span class="checkbox"><input type="checkbox" id="chkNewDigit" value=""/></span></th>
						<th>과거지도</th>
						<th>분류명</th>
						<th>도엽번호</th>
						<th>도엽명</th>
						<th>지도종류</th>
						<th>축척</th>
						<th>좌표계</th>
						<th>공개여부</th>
						<th>고시번호</th>
						<th>고시일자</th>
						<th class="last">제작연도</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div style="height: 255px;">
	<div class="scroll tbody">
		<table>
			<colgroup>
				<col width="50px" />
				<col width="5%" />
				<col width="17%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="7%" />
				<col width="8%" />
				<col width="12%" />
				<col width="7%" />
			</colgroup>
			<tbody id="mapProductList">
				
			</tbody>
		</table>
	</div>
	</div>
	<div class="paging" style="height:45px;">
		<div id="mapPaging">
		</div>
	</div>
	
</div>
</form>
<!-- /.container -->

<!-- .container -->
<form id="historyListForm" >
<input type="hidden" id="pageIndex" name="pageIndex" 	value="1"/>
<input type="hidden" id="mapSerNo" 	name="mapSerNo" 	value=""/>
<input type="hidden" id="mapShtNo" 	name="mapShtNo" 	value=""/>
<div class="container" id="historyDiv">	
		<div class="contTit">
		<div class="btnTwoDepthMenuClose"></div>
		<h3>과거 지도성과이력 목록</h3>
			<select id="_pageUnit_history" class="select" style="width:130px; display: none;" onchange="changePageUnitDigitHistory();">
				<option value="10">10개씩 보기</option>
				<option value="25">25개씩 보기</option>
				<option value="50">50개씩 보기</option>
			</select>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="50px" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="16%" />
					<col width="42%" />
				</colgroup>
				<thead id="mapHistoryHead">
					<tr>
						<th><span class="checkbox theadCheckbox"><input type="checkbox" id="chkHistoryDigit"/></span></th>
						<th>도엽번호</th>
						<th>고시번호</th>
						<th>고시일자</th>
						<th>제작연도</th>
						<th>자료변경일</th>
						<th class="last">변경사유</th>
					</tr>
				</thead>
			</table>
		</div>
		<div  style="height: 255px;">
			<div class="scroll tbody">
				<table>
					<colgroup>
						<col width="50px" />
						<col width="*%" />
						<col width="*%" />
						<col width="*%" />
						<col width="*%" />
						<col width="16%" />
						<col width="42%" />
					</colgroup>
					<tbody id="mapHistoryList">
					</tbody>
				</table>
			</div>
		</div>
		<div class="paging" style="height:45px;">
			<div id="mapHistoryPaging">
			</div>
		</div>
</div>
</form>
<!-- /.container -->
