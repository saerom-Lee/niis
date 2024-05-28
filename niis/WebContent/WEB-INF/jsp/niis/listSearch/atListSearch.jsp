<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	var atSrchParam = "";
	var regAllParam = "";
	
	$(document).ready(function(){
		
		$("#mainDiv .scroll").each(function(){
			$(this).mCustomScrollbar("destroy");
			$(this).mCustomScrollbar();
		});
		
		//화면 진입시 코드 조회
		getYearList();
		
		//성과종류 코드 조회
		getDtsImageNamList();
		
		//체크박스 클릭 이벤트
		$("#atProductList").on("click", "input[type=checkbox]", function(){
			if(($("#form1 #atCheckList ul li div").length)+1 > 50){
				alert("영상은 50건까지 신청할 수 있습니다.");
				return false;
			}
						
			var isChecked = $(this).is(":checked");
			
			var zoneCode = $(this).parent().children("input[id=chkZoneCode]").val().trim();
			var zoneNam = $(this).parent().children("input[id=chkZoneNam]").val();
			
			var liObj = $("#form1 #atCheckList ul #" + zoneCode);
			var divObj = $("#form1 #atCheckList ul #" + $(this).val());
			if(isChecked){
				if(liObj.length == 0){
					$("#form1 #atCheckList ul").append("<li id=\"" + zoneCode + "\">" + zoneNam + "(<span>1</span>)<a href=\"#none\" onclick=\"remAtSelLi('" + zoneCode + "')\">삭제</a></li>");
				}
				
				if(divObj.length == 0){
					$("#form1 #atCheckList ul #" + zoneCode).append("<div id=\"" + $(this).val() + "\" style=\"display:none;\"></div>");
					$("#form1 #atCheckList ul #" + zoneCode +" div[id='" + $(this).val() + "']").append($(this).parent().children("input[type=hidden]").clone());
				}
			}else{
				if(liObj.children("div").length < 2){
					liObj.remove();
				}else{
					divObj.remove();
				}
			}
			liObj.children("span").text(liObj.children("div").length);
			$("#form1 #atAppTotCnt").text($("#form1 #atCheckList ul li div").length);
			
			appListInit();
		});
		
		//상단 체크박스 클릭 이벤트
		$("#atProductHead").on("click", "input[type=checkbox]", function(){
			if(($("#form1 #atCheckList ul li div").length)+1 > 50){
				alert("영상은 50건까지 신청할 수 있습니다.");
				return false;
			}
			
			var isChecked = $(this).is(":checked");
			
			$("#atProductList input[type=checkbox]").each(function(i){
				if(isChecked != $(this).is(":checked")){
					$(this).click();
				}
			});
			
			initCheckbox();
			appListInit();
		});
	});
	
	/******************************************* 코드조회 시작 *******************************************/
	//사업년도 종류 조회
	function getYearList(){
		ajaxCallJson("/atListSearch/getYearList.do", "", function(result, data){	// AtListSearchController.java
			getSelectBox(result, "sYearAt", "0", "zoneYy", "zoneYy");				// common.js
			getSelectBox(result, "eYearAt", "0", "zoneYy", "zoneYy");				// common.js
			//사업지구 조회
			getZoneCodeList();
		});
	}
	
	//사업지구 종류 조회
	function getZoneCodeList(){
		var data = {
				"sYear"			: $("#sYearAt option:selected").val(),
				"eYear"			: $("#eYearAt option:selected").val(),
				"dtsImageCde"	: $("#dtsImageNam option:selected").val()
		}
		
		ajaxCallJson("/atListSearch/getZoneCodeList.do", data, function(result, data){	// AtListSearchController.java
			getSelectBox(result, "zoneCodeAt", "1", "zoneCode", "zoneNam");				// common.js
		});
	}
	
	
	//성과 종류 조회
	function getDtsImageNamList(){
		ajaxCallJson("/atListSearch/getDtsImageNamList.do", "", function(result, data){	// AtListSearchController.java
			getSelectBox(result, "dtsImageNam", "1", "dtsImageCde", "dtsImageNam");		// common.js
			//사업지구 조회
			getZoneCodeList();
		});
	}
	/******************************************* 코드조회 끝 *******************************************/
	
	//검색년도 validation
	function onChangeYear(flag){
		
		var sYear = $("#sYearAt option:selected").val();
		var eYear = $("#eYearAt option:selected").val();
		
		if(sYear > eYear){
			if("s" == flag){
				$("#sYearAt").val(eYear);
			}else{
				$("#eYearAt").val(sYear);
			}
		}
		getZoneCodeList();
	}
	
	//성과종류
	function onChangeDtsImageNam(){
		
		var dtsImageNam = $("#dtsImageNam option:selected").val();
		$("#dtsImageNam").val(dtsImageNam);
		
		getZoneCodeList();
	}
	
	//조회 버튼 클릭시 데이터 조회
	function getAtProductList(){
		
		var sYear = $("#sYearAt option:selected").val();
		var eYear = $("#eYearAt option:selected").val();
		
		if(!sYear || !eYear){
			alert("사업년도 항목이 로딩중입니다. \n항목이 생성되면 조회버튼을 눌러주세요.");
		}
		
		var data = {
				"sYear"			: $("#sYearAt 	  option:selected").val(),
				"eYear"			: $("#eYearAt 	  option:selected").val(),
				"dtsImageCde"	: $("#dtsImageNam option:selected").val(),
				"zoneCode"		: $("#zoneCodeAt  option:selected").val(),
		}
		
		//기존 선택 내역 삭제
/** 추후 주석 해제 필요 ##################################################################################################################
		$("#form1").empty();
**/

		atSrchParam = data;
		
		getAtProductListPaging();
	}
	
	function getAtProductListPaging(){
		
		var data = atSrchParam;
		
		setPagingInfo("mainDiv", "getAtProductListPaging", "/atListSearch/getAtProductList.do");	// AtListSearchController.java // common.js
		ajaxCallJson("/atListSearch/getAtProductList.do", data, function(result, data){				// AtListSearchController.java // common.js
			if (result.list != null){
				
				$("#atProductList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				//헤더 체크박스 초기화
				$("#atProductHead input:checkbox").prop("checked", false);
				
				if(result.list.length > 0){
					$("#atProductList").empty().append($("#tmplAtProduct").tmpl(result.list));
					
					//선택 내역 존재시 체크박스 선택 상태로 변경
					$("#atProductList input:checkbox[id=chkAtProduct]").each(function(){
						var len = $("#form1 #atCheckList ul div[id='" + $(this).val() + "']").length;
						
						if(len > 0){
							$(this).prop("checked", true);
						}
					});
				}else{
					$("#atProductList").html("<tr><td colspan=\"" + $("#atProductHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				atSrchParam["imageCde"] = "atCde";
				
				$("#atProductList").parent().parent(".scroll").mCustomScrollbar();
				initCheckbox();
				
				appListInit();
			}
		});
	}
	
	// 상세조회
	function atProductView(zoneCode){
		var data = {
				"zoneCode"		: zoneCode	, 
		}
		ajaxCallPop("/atListSearch/atListSrchDetail.do", data, "", "");		// AtListSearchController.java
	}
	
	//신청 항목 확인 팝업
	function atListSrchPop(){
		
		/* 확인페이지 생략하고 바로 신청서 작성 페이지로 */
		if($("#form1 #atCheckList ul li div").length < 1){
			alert("영상을 선택해 주세요.");
			return;
		}
		
		var zoneCode 	= new Array();
		var zoneNam 	= new Array();
		$("#form1 #atCheckList ul li div").each(function(){
		
			zoneCode.push($(this).children("input:hidden[id=chkZoneCode]").val());
			zoneNam.push($(this).children("input:hidden[id=chkZoneNam]").val());
		});
		
		var data = {
				"imageCde"	: "atCde", 			//at성과는 imageCde로 따로 구분하지 않는다. (공통컴포넌트를 타기위해 설정)
				"keyVal1"	: zoneCode,
				"zoneNam"	: zoneNam
		};
		ajaxCallPop("/apply/regApplyPop.do", data, "totalApplication", "700");	// ApplyController.java
	}
	
	//전체신청 팝업 (조회된 전체항목 신청)
	function regApplyConfirm(){
		
		if(atSrchParam == ""){
			alert("데이터 조회 후 신청해 주세요.");
			return;
		}
		
		var data = atSrchParam;
		
		ajaxCallPop("/apply/regApplyConfirm.do", data, "", 460);	// ApplyController.java
		regAllParam = atSrchParam;
		
	}
	
	//신청 목록 삭제
	function remAtSelLi(zoneCode){
		
		var zoneCode = zoneCode.trim();
		
		
		$("#form1 #atCheckList ul #" + zoneCode + " div").each(function(){
			var id = $(this).attr("id");
			
			$("#atProductList input:checkbox[id=chkAtProduct]").each(function(){
				
				if($(this).val() == id){
					$(this).prop("checked", false);
					$(this).parent(".checkbox").removeClass('active');
					if($(this).parents("tr").is('tr')){
						$(this).parents("tr").removeClass('active');
					}
				}
			});
		});
		
		$("#form1 #atCheckList ul #" + zoneCode).remove();
		$("#form1 #atAppTotCnt").text($("#form1 #atCheckList ul li div").length);
		
		appListInit();
	}
	
	function appListInit(){
		
		if($("#form1 #atCheckList ul li").length > 0){
			$("#form1 #atCheckList .inner").css("display", "none");
			$("#form1 #atCheckList").removeClass("noData");
			if(!$("#form1 #atCheckList").hasClass("appList")){
				$("#form1 #atCheckList").addClass("appList");
			}
			
		}else{
			$("#form1 #atCheckList .inner").css("display", "");
			$("#form1 #atCheckList").removeClass("appList");
			if(!$("#form1 #atCheckList").hasClass("noData")){
				$("#form1 #atCheckList").addClass("noData");
			}
		}
	}
	
	function changePageUnitAt(){
		if(atSrchParam != ""){
			getAtProductListPaging();
		}
	}
	
</script>

<!-- .twoDepthMenu -->
<form id="form1">
<div class="twoDepthMenu">
	<div class="top">
		<h3 class="tit">
			<a href="#none" class="btnCloseOpen"></a>조회 항목
			<a href="#none" id="serviceGuide_12" class="btnServiceGuide">
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
								<select id="sYearAt" class="select" onChange="onChangeYear('s')" style="width:114px;">
									<option value="">로딩중</option>
								</select><span style="position:relative; top:8px; padding:0 5px">~</span>
								<select id="eYearAt" class="select" onChange="onChangeYear('e')" style="width:114px;">
									<option value="">로딩중</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>성과종류</th>
							<td>
								<select id="dtsImageNam" class="select" onChange="onChangeDtsImageNam();">
									<option value="">전체</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>사업지구</th>
							<td>
								<select id="zoneCodeAt" class="select">
									<option value="">전체</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- .boardStyle_1 -->
			<a href="#none" class="searchDivBtn" onclick="getAtProductList();">조회</a>
		</div>
		<h3 class="tit">신청 목록 (<span id="atAppTotCnt">0</span>)</h3>
	</div>
	<div id="atCheckList" class="scroll noData">
		<!-- .appList -->
		<div class="inner">
			<p>추가된 신청자료가 없습니다.</p>
		</div>
		<ul>
		</ul>
		<!-- /.appList -->
	</div>
	<div class="bottom">
		<a href="#commonPop" onclick="atListSrchPop();">
			<img src="/niis/images/sub/btn_application_form.gif" alt="신청서 작성" title="신청서 작성" />
		</a>
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
			<select id="_pageUnit" class="select" style="width:130px;" onchange="changePageUnitAt();">
				<option value="25">25개씩 보기</option>
				<option value="50">50개씩 보기</option>
				<option value="100">100개씩 보기</option>
			</select>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="50px" />
					<col width="10%" />
					<col width="*%" />
					<col width="*%" />
					<col width="10%" />
					<col width="11%" />
				</colgroup>
				<thead id="atProductHead">
					<tr>
						<th><span class="checkbox theadCheckbox"><input type="checkbox" id="chkAtAll" /></span></th>
						<th>사업년도</th>
						<th>사업지구명</th>
						<th>사업종류</th>
						<th>성과종류</th>
						<th class="last">대표축적</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="scroll tbody">
		<table>
			<colgroup>
				<col width="50px" />
				<col width="10%" />
				<col width="*%" />
				<col width="*%" />
				<col width="10%" />
				<col width="11%" />
			</colgroup>
			<tbody id="atProductList">
			</tbody>
		</table>
	</div>
	<div class="paging">
		<ul>
		</ul>
<!-- 		<div class="btn"><a href="#commonPop" onclick="regApplyConfirm();">전체신청</a></div> -->
	</div>
</div>
<!-- /.container -->

<script id="tmplAtProduct" type="text/x-jquery-tmpl">
	<tr>
		<td>
			<span class="checkbox">
				<input type="checkbox" 	id="chkAtProduct" 	name="chkAtProduct" 	value="{{html zoneCode}}"/>
				<input type="hidden" 	id="chkImageCde" 	name="chkImageCde" 		value="{{html imageCde}}" />
				<input type="hidden" 	id="chkZoneYy" 		name="chkZoneYy" 		value="{{html zoneYy}}" />
				<input type="hidden" 	id="chkZoneCode" 	name="chkZoneCode" 		value="{{html zoneCode}}" />
				<input type="hidden" 	id="chkZoneNam" 	name="chkZoneNam" 		value="{{html zoneNam}}" />
				<input type="hidden" 	id="chkDtsImageNam" name="chkDtsImageNam" 	value="{{html dtsImageNam}}" />
				<input type="hidden" 	id="chkScale" 		name="chkScale" 		value="{{html scale}}" />
			</span>
		</td>
		<td><a href="#none" onclick="atProductView('{{html zoneCode}}')">{{html zoneYy}}</a></td>
		<td><a href="#none" onclick="atProductView('{{html zoneCode}}')">{{html zoneNam}}</a></td>
		<td><a href="#none" onclick="atProductView('{{html zoneCode}}')">{{html prjNam}}</a></td>
		<td><a href="#none" onclick="atProductView('{{html zoneCode}}')">{{html dtsImageNam}}</a></td>
		<td><a href="#none" onclick="atProductView('{{html zoneCode}}')">{{html scale}}</a></td>
	</tr>
</script>