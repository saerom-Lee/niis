<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>

<style>
	.scroll{
					height: 250px !important;
	}
	
	.select{
					width: 244px !important;
	}
</style>

<script type="text/javascript">
	
	var mapSrchParam = "";
	var regAllParam = "";
	var mapHistorySrchParam = "";
	
	$(document).ready(function(){
		
		$("#mainDiv .scroll").each(function(){
			$(this).mCustomScrollbar("destroy");
			$(this).mCustomScrollbar();
		});
		
		
	});
	
	
	//체크박스 클릭 이벤트
	$("#mapHistoryList").on("click", "input[type=checkbox]", function(){
		if(($("#form1 #mapCheckList ul li div").length)+1 > 25){
			alert("영상은 25건까지 신청할 수 있습니다.");
			return false;
		}
					
		var isChecked = $(this).is(":checked");
		
		var mapSerNo = $(this).parent().children("input[id=chkMapSerNo]").val().trim();
		var mapShtNo = $(this).parent().children("input[id=chkMapShtNo]").val().trim();
		var mapHistoryNo = $(this).parent().children("input[id=chkMapHistoryNo]").val().trim();
		
		var mapKindNm = $(this).parent().children("input[id=chkMapKindNm]").val();
		var scaleNm = $(this).parent().children("input[id=chkScaleNm]").val();
		var mapShtNo = $(this).parent().children("input[id=chkMapShtNo]").val();
		
		
		var liObj = $("#form1 #mapCheckList ul #" + mapSerNo + "_" + mapShtNo);
		var divObj = $("#form1 #mapCheckList ul #" + $(this).val());
		
		
		if(isChecked){
			if(liObj.length == 0){
				$("#form1 #mapCheckList ul").append("<li id=\"" + mapSerNo + "_" + mapShtNo + "\">" + mapKindNm + "-" + scaleNm + "(" + mapShtNo + ")" + "(<span>1</span>)<a href=\"#none\" onclick=\"remMapSelLi('" +  mapSerNo + "_" + mapShtNo + "')\">삭제</a></li>");
			}
			
			if(divObj.length == 0){
				$("#form1 #mapCheckList ul #" +  mapSerNo + "_" + mapShtNo).append("<div id=\"" + $(this).val() + "\" style=\"display:none;\"></div>");
				$("#form1 #mapCheckList ul #" +  mapSerNo + "_" + mapShtNo  +" div[id='" + $(this).val() + "']").append($(this).parent().children("input[type=hidden]").clone());
			}
		}else{
			if(liObj.children("div").length < 2){
				liObj.remove();
			}else{
				divObj.remove();
			}
		}
		
		
		liObj.children("span").text(liObj.children("div").length);
		$("#form1 #mapAppTotCnt").text($("#form1 #mapCheckList ul li div").length);
		
		appListInit();
	});
	
	/******************************************* 코드조회 시작 *******************************************/
	// 수치지형도 종류 선택시
	function chgSearchKnd(){
		var mapKindCd = $("#mapKindCd option:selected").val();
		
		
		
		
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
	
	/******************************************* 코드조회 끝 *******************************************/
	
	
	//조회 버튼 클릭시 데이터 조회
	function getMapProductList(){
		var data = {
				 	"mapKindCd"		: $("select[name='mapKindCd']").val()
				, 	"scaleCd"		: $("select[name='mapScale']").val()
				, 	"coordDvsnCd"	: $("select[name='coordDvsnCd']").val()
				, 	"openDvsnCd"	: $("select[name='openDvsnCd']").val()
				, 	"searchNum"		: $("#searchNum").val()
				, 	"searchWrd"		: $("#searchWrd").val()
		}
		//기존 선택 내역 삭제
/** 추후 주석 해제 필요 ##################################################################################################################
		$("#form1").empty();
**/
		mapSrchParam = data;
		
		getMapProductListPaging();
	}
	
	// 지도성과 목록
	function getMapProductListPaging(){
		
		var data = mapSrchParam;
		
		setPagingInfo("mapDiv", "getMapProductListPaging", "/mapListSearch/getMapProductList.do");
		ajaxCallJson("/mapListSearch/getMapProductList.do", data, function(result, data){
			if (result.list != null){
				
				$("#mapProductList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				//헤더 체크박스 초기화
				$("#mapProductHead input:radio").prop("checked", false);
				
				if(result.list.length > 0){
					$("#mapProductList").empty().append($("#tmplMapProduct").tmpl(result.list));
					
				}else{
					$("#mapProductList").html("<tr><td colspan=\"" + $("#mapProductHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				
				$("#mapHistoryList").empty();
				
				$("#mapProductList").parent().parent(".scroll").mCustomScrollbar();
				
				appListInit();
			}
		});
	}
	
	// 지도성과 목록 radio 클릭 이벤트
	function getMapHisImgDataList(mapSerNo, mapShtNo){
		mapHistorySrchParam = {
										"mapSerNo"	: 	mapSerNo
									, 	"mapShtNo"	:	mapShtNo
									, 	"imageCde"	:	"PDT008"		// 수치지형도 imageCde
		}
		
		// 지도성과이력 목록
		getMapHisImgDataListPaging();
	}
	
	// 지도성과이력 목록
	function getMapHisImgDataListPaging(){
		
		var data = mapHistorySrchParam;
		
		setPagingInfo("historyDiv", "getMapHisImgDataListPaging", "/mapListSearch/getMapHisImgDataList.do");
		ajaxCallJson("/mapListSearch/getMapHisImgDataList.do", data, function(result, data){
			if (result.list != null){
				
				//헤더 체크박스 초기화
				$("#mapProductHead input:checkbox").prop("checked", false);
				
				if(result.list.length > 0){
					$("#mapHistoryList").empty().append($("#tmplMapHistory").tmpl(result.list));
					
					//선택 내역 존재시 체크박스 선택 상태로 변경
					$("#mapHistoryList input:checkbox[id=chkMapHistory]").each(function(){
						
						var len = $("#form1 #mapCheckList ul div[id='" + $(this).val() + "']").length;
						if(len > 0){
							$(this).prop("checked", true);
							$(this).parent(".checkbox").addClass('active');
						}
					});
				}else{
					$("#mapHistoryList").html("<tr><td colspan=\"" + $("#mapHistoryHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				mapSrchParam["imageCde"] = result.imageCde;
				
				$("#mapHistoryList").parent().parent(".scroll").mCustomScrollbar();
				
			}
		});
		
	}

	
	// 지도성과 목록 상세조회
	function mapProductView(mapSerNo, mapShtNo, mapHistoryNo){
		var data = {
					"mapSerNo"		: mapSerNo
				, 	"mapShtNo"		: mapShtNo
				,	"mapHistoryNo"	: mapHistoryNo
		}
		
		ajaxCallPop("/mapListSearch/mapListSrchDetail.do", data, "", "");
	}
	
	
	// 지도성과이력 목록 상세조회
	function mapHistoryProductView(mapSerNo, mapShtNo, mapHistoryNo){
		var data = {
					"mapSerNo"		: mapSerNo
				, 	"mapShtNo"		: mapShtNo
				,	"mapHistoryNo"	: mapHistoryNo
		}
		
		ajaxCallPop("/mapListSearch/mapHistoryListSrchDetail.do", data, "", "");
	}
	
	
	//신청 항목 확인 팝업
	function mapListSrchPop(){
		
		/* 확인페이지 생략하고 바로 신청서 작성 페이지로 */
		if($("#form1 #mapCheckList ul li div").length < 1){
			alert("영상을 선택해 주세요.");
			return;
		}
		
		var imageCde 		= new Array();
		var mapSerNo 		= new Array();	//keyVal1
		var mapHistoryNo 	= new Array();	//keyVal2
		var mapShtNo 		= new Array();	//keyVal3
		var mapKindNm 		= new Array();
		var scaleNm 		= new Array();
		
		$("#form1 #mapCheckList ul li div").each(function(){
			imageCde.push($(this).children("input:hidden[id=chkImageCde]").val());
			mapSerNo.push($(this).children("input:hidden[id=chkMapSerNo]").val());
			mapHistoryNo.push($(this).children("input:hidden[id=chkMapHistoryNo]").val());
			mapShtNo.push($(this).children("input:hidden[id=chkMapShtNo]").val());
			mapKindNm.push($(this).children("input:hidden[id=chkMapKindNm]").val());
			scaleNm.push($(this).children("input:hidden[id=chkScaleNm]").val());
		});
		
		var data = {
				"imageCde"	: imageCde 			
				,"keyVal1"	: mapSerNo
				,"keyVal2"	: mapHistoryNo
				,"keyVal3"	: mapShtNo
				,"mapKindNm": mapKindNm
				,"scaleNm"	: scaleNm
		};
		
		// ApplyController.java
		ajaxCallPop("/apply/regApplyPop.do", data, "totalApplication", "700");
	}
	
	//전체신청 팝업 (조회된 전체항목 신청)
	function regApplyConfirm(){
		
		if(mapHistorySrchParam == ""){
			alert("데이터 조회 후 신청해 주세요.");
			return;
		}
		
		var data = mapHistorySrchParam;
		
		ajaxCallPop("/apply/regApplyConfirm.do", data, "", 460);	// ApplyController.java 
		regAllParam = mapHistorySrchParam;
	}
	
	//신청 목록 삭제
	function remMapSelLi(mapNo){
		
		var mapNo = mapNo.trim();
		alert(mapNo);
		
		$("#form1 #mapCheckList ul #" + mapNo + " div").each(function(){
			var id = $(this).attr("id");
			
			$("#mapHistoryList input:checkbox[id=chkMapHistory]").each(function(){
				if($(this).val() == id){
					$(this).prop("checked", false);
					$(this).parent(".checkbox").removeClass('active');
					if($(this).parents("tr").is('tr')){
						$(this).parents("tr").removeClass('active');
					}
				}
			});
		});
		
		$("#form1 #mapCheckList ul #" + mapNo).remove();
		$("#form1 #mapAppTotCnt").text($("#form1 #mapCheckList ul li div").length);
		
		appListInit();
	}
	
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
	
	function changePageUnitMap(){
		if(mapSrchParam != ""){
			getMapProductListPaging();
		}
	}
	
	function changePageUnitMapHistory(){
		
		if(mapHistorySrchParam != ""){
			
			var mapSerNo = mapHistorySrchParam.mapSerNo;
			var mapShtNo = mapHistorySrchParam.mapShtNo;
			
			getMapHisImgDataList(mapSerNo, mapShtNo);
		}
	}
	
</script>

<!-- .twoDepthMenu -->
<form id="form1">
<div class="twoDepthMenu">
	<div class="top">
		<h3 class="tit">
			<a href="#none" class="btnCloseOpen"></a>조회 항목
			<a href="#none" id="serviceGuide_8" class="btnServiceGuide">
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
									<option value="01">공개-KOR</option>
									<option value="02">공개제한-KOR</option>
								</select>
							</td>
							<!-- 수치지형도V2.0 -->
							<td id="openDvsnCd_04" style="display: none;">
								<select name="openDvsnCd" class="select">
									<option value="01">공개-KOR</option>
									<option value="02">공개제한-KOR</option>
								</select>
							</td>
							<!-- 토지특성도 -->
							<td id="openDvsnCd_02" style="display: none;">
								<select name="openDvsnCd" class="select">
									<option value="02">공개제한-KOR</option>
								</select>
							</td>
							<!-- 토지이용현황도 -->
							<td id="openDvsnCd_03" style="display: none;">
								<select name="openDvsnCd" class="select">
									<option value="02">공개제한-KOR</option>
								</select>
							</td>
							<!-- 연안해역도 -->
							<td id="openDvsnCd_20" style="display: none;">
								<select name="openDvsnCd" class="select">
									<option value="02">공개제한-KOR</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>도엽번호</th>
							<td>
								<input type="text" id="searchNum" name="searchNum" value=""/>
							</td>
						</tr>
						<tr>
							<th>도엽명</th>
							<td>
								<input type="text" id="searchWrd" name="searchWrd" value=""/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- .boardStyle_1 -->
			<a href="#none" class="searchDivBtn" onclick="getMapProductList();">조회</a>
		</div>
		<h3 class="tit">신청 목록 (<span id="mapAppTotCnt">0</span>)</h3>
	</div>
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
		<a href="#commonPop" onclick="mapListSrchPop();">
			<img src="/niis/images/sub/btn_application_form.gif" alt="신청서 작성" title="신청서 작성" />
		</a>
	</div>
</div>
</form>
<!-- /.twoDepthMenu -->

<!-- .container -->
<div class="container" id="mapDiv">
	<div class="top">
		<div class="contTit">
			<div class="btnTwoDepthMenuClose"></div>
			<h3>지도성과 목록</h3>
			<select id="_pageUnit_map" class="select" style="width:130px;" onchange="changePageUnitMap();">
				<option value="10">10개씩 보기</option>
				<option value="25">25개씩 보기</option>
				<option value="50">50개씩 보기</option>
			</select>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="50px" />
					<col width="17%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="8%" />
					<col width="7%" />
				</colgroup>
				<thead id="mapProductHead">
					<tr>
						<th></th>
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
				<col width="17%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="8%" />
				<col width="7%" />
			</colgroup>
			<tbody id="mapProductList">
			</tbody>
		</table>
	</div>
	</div>
	<div class="paging" style="height:45px;">
		<div>
			<ul>
			</ul>
		</div>
	</div>
	
</div>
<!-- /.container -->

<!-- .container -->
<div class="container" id="historyDiv">	
		<div class="contTit">
		<div class="btnTwoDepthMenuClose"></div>
		<h3>지도성과이력 목록</h3>
			<select id="_pageUnit_history" class="select" style="width:130px;" onchange="changePageUnitMapHistory();">
				<option value="10">10개씩 보기</option>
				<option value="25">25개씩 보기</option>
				<option value="50">50개씩 보기</option>
			</select>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="50px" />
					<col width="16%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
					<col width="16%" />
					<col width="42%" />
				</colgroup>
				<thead id="mapHistoryHead">
					<tr>
						<th><span class="checkbox theadCheckbox"><input type="checkbox" id="chkAtAll" /></span></th>
						<th>고시번호</th>
						<th>고시일자</th>
						<th>제작연도</th>
						<th>자료변경자</th>
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
						<col width="16%" />
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
		<div class="paging">
			<div>
				<ul>
				</ul>
				<div class="btn"><a href="#commonPop" onclick="regApplyConfirm();">전체신청</a></div>
			</div>
		</div>
</div>
<!-- /.container -->

<script id="tmplMapProduct" type="text/x-jquery-tmpl">
	<tr>
		<td>
			<input type="radio"		name="mapRadio"		onclick="javascript:getMapHisImgDataList({{html mapSerNo}}, {{html mapShtNo}});"/>
		</td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html mapFormDvsnNm}}-{{html mapKindNm}}-{{html scaleNm}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html mapShtNo}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html mapShtNm}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html mapKindNm}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html scaleNm}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html coordDvsnNm}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html openDvsnNm}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html projectNo}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html noticeDate}}</a></td>
		<td><a href="#none" onclick="mapProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html productYear}}</a></td>
	</tr>
</script>

<script id="tmplMapHistory" type="text/x-jquery-tmpl">
	<tr>
		<td>
			<span class="checkbox">
				<input type="checkbox"  id="chkMapHistory" 		name="chkMapHistory"		value="{{html mapSerNo}}_{{html mapShtNo}}_{{html mapHistoryNo}}"/>
				<input type="hidden" 	id="chkImageCde" 		name="chkImageCde" 			value="{{html imageCde}}" />
				<input type="hidden" 	id="chkMapSerNo" 		name="chkMapSerNo" 			value="{{html mapSerNo}}" />
				<input type="hidden" 	id="chkMapShtNo" 		name="chkMapShtNo" 			value="{{html mapShtNo}}" />
				<input type="hidden" 	id="chkMapHistoryNo" 	name="chkMapHistoryNo" 		value="{{html mapHistoryNo}}" />
				<input type="hidden" 	id="chkMapKindCd" 		name="chkMapKindCd" 		value="{{html mapKindCd}}" />
				<input type="hidden" 	id="chkMapKindNm" 		name="chkMapKindNm" 		value="{{html mapKindNm}}" />
				<input type="hidden" 	id="chkScaleCd" 		name="chkScaleCd" 			value="{{html scaleCd}}" />
				<input type="hidden" 	id="chkScaleNm" 		name="chkScaleNm" 			value="{{html scaleNm}}" />
				<input type="hidden" 	id="chkNoticeNo" 		name="chkNoticeNo" 			value="{{html noticeNo}}" />
			</span>
		</td>
		<td><a href="#none" onclick="mapHistoryProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html noticeNo}}</a></td>
		<td><a href="#none" onclick="mapHistoryProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html noticeDate}}</a></td>
		<td><a href="#none" onclick="mapHistoryProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html productYear}}</a></td>
		<td><a href="#none" onclick="mapHistoryProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html checkInUserId}}</a></td>
		<td><a href="#none" onclick="mapHistoryProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html checkInDate}}</a></td>
		<td><a href="#none" onclick="mapHistoryProductView('{{html mapSerNo}}', '{{html mapShtNo}}', '{{html mapHistoryNo}}')">{{html checkInReason}}</a></td>
	</tr>
</script>