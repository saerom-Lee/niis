<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">

var zoneSrchParam = "";
var regAllParam = "";

$(document).ready(function(){
	
	$("#mainDiv .scroll").each(function(){
		$(this).mCustomScrollbar("destroy");
		$(this).mCustomScrollbar();
	});
	
	//화면 진입시 코드 조회
	getYearList();
	
	//체크박스 클릭 이벤트
	$("#zoneProductList").on("click", "input[type=checkbox]", function(){
		
		var isChecked = $(this).is(":checked");
		
		var zoneCode = $(this).parent().children("input[id=chkZoneCode]").val();
		var zoneNam = $(this).parent().children("input[id=chkZoneNam]").val();

		var liObj = $("#form1 #zoneCheckList ul #" + zoneCode);
		var divObj = $("#form1 #zoneCheckList ul #" + $(this).val());

		if(isChecked){
			if(liObj.length == 0){
				$("#form1 #zoneCheckList ul").append("<li id=\"" + zoneCode + "\">" + zoneNam + "(<span>1</span>)<a href=\"#none\" onclick=\"remZoneSelLi('" + zoneCode + "')\">삭제</a></li>"); // 신청목록
			}
			
			if(divObj.length == 0){
				$("#form1 #zoneCheckList ul #" + zoneCode).append("<div id=\"" + $(this).val() + "\"style=\"display:none;\"></div>");
				$("#form1 #zoneCheckList ul #" + zoneCode +" div[id='" + $(this).val() + "']").append($(this).parent().children("input[type=hidden]").clone());
			}
		}else{
			if(liObj.children("div").length < 2){
				liObj.remove();
			}else{
				divObj.remove();
			}
		}
		liObj.children("span").text(liObj.children("div").length);
		
		appListInit();
	});
	
	//상단 체크박스 클릭 이벤트
	$("#zoneProductHead").on("click", "input[type=checkbox]", function(){
		
		var isChecked = $(this).is(":checked");
		
		$("#zoneProductList input[type=checkbox]").each(function(i){
			if(isChecked != $(this).is(":checked")){
				$(this).click();
			}
		});
		
		initCheckbox();
		appListInit();
	});
	
});

	//사업년도 조회
	function getYearList(){
		ajaxCallJson("/smartMap/getYearList.do", "", function(result, data){
			getSelectBox(result, "sYearPro", "0", "zoneYy", "zoneYy");
			getSelectBox(result, "eYearPro", "0", "zoneYy", "zoneYy");
			getZoneCodeList();
		});
	}
	
	//사업종류 조회
	function getZoneCodeList(){
		var data = {
				"sYear"	: $("#sYearPro option:selected").val(),
				"eYear"	: $("#eYearPro option:selected").val()
		}
		
		ajaxCallJson("/smartMap/getProjectTypeList.do", data, function(result, data){
			getSelectBox(result, "projecttypeCde", "0", "projecttypeCde", "projecttypeCde");
		});
	}
	
	//검색년도 validation
	function onChangeYear(flag){
		
		var sYear = $("#sYearPro option:selected").val();
		var eYear = $("#eYearPro option:selected").val();
		
		if(sYear > eYear){
			if("s" == flag){
				$("#sYearPro").val(eYear);
			}else{
				$("#eYearPro").val(sYear);
			}
		}
		getZoneCodeList();
	}
	
	//조회 버튼 클릭시 데이터 조회
	function getProProductList(){
		var data = {
				"zoneType"		: $("#projecttypeCde option:selected").val(),
				"sYear"	: $("#sYearPro option:selected").val(),
				"eYear"		: $("#eYearPro option:selected").val()
		}
		zoneSrchParam = data;
		getZoneProductListPaging();
	}
	
	function getZoneProductListPaging(){
		
		var data = zoneSrchParam;
		
		setPagingInfo("mainDiv", "getZoneProductListPaging", "/smartMap/getZoneList.do");
		ajaxCallJson("/smartMap/getZoneList.do", data, function(result, data){
			if (result.list != null){
				
				$("#zoneProductList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				//헤더 체크박스 초기화
				$("#zoneProductHead input:checkbox").prop("checked", false);
				
				if(result.list.length > 0){
					$("#zoneProductList").empty().append($("#tmplZoneProduct").tmpl(result.list));
					
					//선택 내역 존재시 체크박스 선택 상태로 변경
					$("#zoneProductList input:checkbox[id=chkZoneProduct]").each(function(){
						
						var len = $("#form1 #zoneCheckList ul div[id='" + $(this).val() + "']").length;
						
						if(len > 0){
							$(this).prop("checked", true);
						}
					});
				}else{
					$("#zoneProductList").html("<tr><td colspan=\"" + $("#zoneProductHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				zoneSrchParam["imageCde"] = result.imageCde;
				
				$("#zoneProductList").parent().parent(".scroll").mCustomScrollbar();
// 				initCheckbox();
				
			}
		});
	}
	
	function changePageUnitZone(){
		if(zoneSrchParam != ""){
			getZoneProductListPaging();
		}
	}
	
	function appListInit(){
		
		if($("#form1 #zoneCheckList ul li").length > 0){
			$("#form1 #zoneCheckList .inner").css("display", "none");
			$("#form1 #zoneCheckList").removeClass("noData");
			if(!$("#form1 #zoneCheckList").hasClass("appList")){
				$("#form1 #zoneCheckList").addClass("appList");
			}
			
		}else{
			$("#form1 #zoneCheckList .inner").css("display", "");
			$("#form1 #zoneCheckList").removeClass("appList");
			if(!$("#form1 #zoneCheckList").hasClass("noData")){
				$("#form1 #zoneCheckList").addClass("noData");
			}
		}
	}
	
	function zoneProductView(zoneCode){ //상세조회
		var data = {
				"zoneCode"		: zoneCode
		}
		ajaxCallPop("/smartMap/zoneListDetail.do", data, "", "");
	}
	
	//메타데이터 전송
	function metadataSend(){
		
		var zoneCodes = [];
		
		if(zoneSrchParam == ""){
			alert("데이터 조회 후 전송해 주세요.");
			return;
		}
		
		$("input[name=chkZoneProduct]:checked").each(function(){
			var zoneCode = $(this).val();
			zoneCodes.push(zoneCode);
		});
// 		$("tr.active").remove();

		if(zoneCodes.length == 0){
			alert("데이터 선택 후 전송해 주세요.");
			return;
		}
		ajaxCallPop("/smartMap/getStoInfoPop.do", "", "", 450, function(){

		});
		
	}
	
	function stoDts(){
		
		var zoneCodes = [];
		
		$("input[name=chkZoneProduct]:checked").each(function(){
			var zoneCode = $(this).val();
			zoneCodes.push(zoneCode);
		});
		
		var data = {
				"zoneCodes" : zoneCodes,
				"stoIdn" : $('#stoIdn').val()
		};
		
		//메타데이터 전송
		ajaxCallJson("/smartMap/metadataSend.do", data, function(result, data){
			console.log("메타데이터 전송 "+result.insStatus);
			if(result.insStatus == "succ"){
				alert("메타데이터 전송을 성공했습니다.");
				ajaxCallJson("/smartMap/uploadFile.do", data); // 영상전송 대기 테이블 insert
				ajaxCallJson("/smartMap/stoLocIns.do", data); // sto테이블 insert
			}else if(result.insStatus == "fail"){
				alert("메타데이터 전송을 실패했습니다.");
			}
			getProProductList();
		});
		
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
							<th class="colorOr">사업년도 *</th>
							<td>
								<select id="sYearPro" class="select" onChange="onChangeYear('s')" style="width:114px;">
								</select><span style="position:relative; top:8px; padding:0 5px">~</span>
								<select id="eYearPro" class="select" onChange="onChangeYear('e')" style="width:114px;">
								</select>
							</td>
						</tr>
						<tr>
							<th class="colorOr">사업종류 *</th>
							<td>
								<select id="projecttypeCde" class="select" >
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- .boardStyle_1 -->
			<a href="#none" class="searchDivBtn" onclick="getProProductList();">조회</a>
		</div>
	</div>
	</div>
</form>
<!-- /.twoDepthMenu -->

<!-- .container -->
<div class="container">
	<div class="top">
		<div class="contTit">
			<div class="btnTwoDepthMenuClose"></div>
			<h3>검색결과</h3>
			<select id="_pageUnit" class="select" style="width:130px;" onchange="changePageUnitZone();">
				<option value="25">25개씩 보기</option>
				<option value="50">50개씩 보기</option>
				<option value="100">100개씩 보기</option>
			</select>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="11%" />
					<col width="*%" />
				</colgroup>
				<thead id="zoneProductHead">
					<tr>
						<th><span class="checkbox theadCheckbox"><input type="checkbox" id="chkAirAll" /></span></th>
						<th>사업년도</th>
						<th>사업지구명</th>
						<th>사업지구 코드</th>
						<th>대표축척</th>
						<th>공간해상도</th>
						<th class="last">사업종류</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="scroll tbody">
		<table>
			<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="10%" />
				<col width="11%" />
				<col width="*%" />
			</colgroup>
			<tbody id="zoneProductList">
			</tbody>
		</table>
		<input type="hidden" id="stoIdn">
	</div>
	<div class="paging">
		<ul>
		</ul>
		<div class="btn" style="width:300px;background:#2a8095;">
<!-- 			<a href="#commonPop" onclick="dataSend();">영상전송</a> -->
			<a href="#commonPop" onclick="metadataSend();" style="width:300px;">스마트전자지도 시스템 동기화</a>
		</div>
	</div>
</div>
<!-- /.container -->

<script id="tmplZoneProduct" type="text/x-jquery-tmpl">
	<tr>
		<td>
			<span class="checkbox">
				<input type="checkbox" id="chkZoneProduct" name="chkZoneProduct" value="{{html zoneCode}}"/>
				<input type="hidden" id="chkZoneCode" name="chkZoneCode" value="{{html zoneCode}}" />
				<input type="hidden" id="chkZoneNam" name="chkZoneNam" value="{{html zoneNam}}" />
				<input type="hidden" id="chkZoneYy" name="chkZoneYy" value="{{html zoneYy}}" />
				<input type="hidden" id="chkScale" name="chkScale" value="{{html scale}}" />
				<input type="hidden" id="chkGsdVal" name="chkGsdVal" value="{{html gsdVal}}" />
				<input type="hidden" id="chkProjectCde" name="chkProjectCde" value="{{html projectCde}}" />
			</span>
		</td>
		<td><a href="#none" onclick="zoneProductView('{{html zoneCode}}')">{{html zoneYy}}</a></td>
		<td><a href="#none" onclick="zoneProductView('{{html zoneCode}}')">{{html zoneNam}}</a></td>
		<td><a href="#none" onclick="zoneProductView('{{html zoneCode}}')">{{html zoneCode}}</a></td>
		<td><a href="#none" onclick="zoneProductView('{{html zoneCode}}')">{{html scale}}</a></td>
		<td><a href="#none" onclick="zoneProductView('{{html zoneCode}}')">{{html gsdVal}}</a></td>
		<td><a href="#none" onclick="zoneProductView('{{html zoneCode}}')">{{html projectCde}}</a></td>
	</tr>
</script>