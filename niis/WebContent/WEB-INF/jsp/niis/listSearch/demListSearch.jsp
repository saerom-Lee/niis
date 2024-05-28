<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	var demSrchParam = "";
	var regAllParam = "";
	
	$(document).ready(function(){
		
		$("#mainDiv .scroll").each(function(){
			$(this).mCustomScrollbar("destroy");
			$(this).mCustomScrollbar();
		});
		
		//화면 진입시 코드 조회
		getYearList();
		
		//체크박스 클릭 이벤트
		$("#demProductList").on("click", "input[type=checkbox]", function(){
			if(($("#form1 #demCheckList ul li div").length)+1 > 50){
				alert("영상은 50건까지 신청할 수 있습니다.");
				return false;
			}			
															   
			var isChecked = $(this).is(":checked");
			
			var zoneCode = $(this).parent().children("input[id=chkZoneCode]").val();
			var zoneNam = $(this).parent().children("input[id=chkZoneNam]").val();
			
			var liObj = $("#form1 #demCheckList ul #" + zoneCode);
			var divObj = $("#form1 #demCheckList ul #" + $(this).val());
			
			if(isChecked){
				if(liObj.length == 0){
					$("#form1 #demCheckList ul").append("<li id=\"" + zoneCode + "\">" + zoneNam + "(<span>1</span>)<a href=\"#none\" onclick=\"remDemSelLi('" + zoneCode + "')\">삭제</a></li>");
				}
				
				if(divObj.length == 0){
					$("#form1 #demCheckList ul #" + zoneCode).append("<div id=\"" + $(this).val() + "\" style=\"display:none;\"></div>");
					$("#form1 #demCheckList ul #" + zoneCode +" div[id='" + $(this).val() + "']").append($(this).parent().children("input[type=hidden]").clone());
				}
			}else{
				if(liObj.children("div").length < 2){
					liObj.remove();
				}else{
					divObj.remove();
				}
			}
			liObj.children("span").text(liObj.children("div").length);
			$("#form1 #demAppTotCnt").text($("#form1 #demCheckList ul li div").length);
			
			appListInit();
		});
		
		//상단 체크박스 클릭 이벤트
		$("#demProductHead").on("click", "input[type=checkbox]", function(){
			if(($("#form1 #demCheckList ul li div").length)+1 > 50){
				alert("영상은 50건까지 신청할 수 있습니다.");
				return false;
			}			
															   
			var isChecked = $(this).is(":checked");
			
			$("#demProductList input[type=checkbox]").each(function(i){
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
		ajaxCallJson("/demListSearch/getYearList.do", "", function(result, data){
			getSelectBox(result, "sYearDem", "0", "zoneYy", "zoneYy");
			getSelectBox(result, "eYearDem", "0", "zoneYy", "zoneYy");
			getZoneCodeList();
		});
	}
	
	//사업지구 종류 조회
	function getZoneCodeList(){
		var data = {
				"sYear"	: $("#sYearDem option:selected").val(),
				"eYear"	: $("#eYearDem option:selected").val()
		}
		
		ajaxCallJson("/demListSearch/getZoneCodeList.do", data, function(result, data){
			getSelectBox(result, "zoneCodeDem", "0", "zoneCode", "zoneNam");
// 			getMap5000NumList();
		});
	}
	
<!--
	//text로 변경
	//도엽번호 종류 조회
	function getMap5000NumList(){
		var data = {
				"zoneCode"	: $("#zoneCodeDem option:selected").val()
		}
		
		ajaxCallJson("/demListSearch/getMap5000NumList.do", data, function(result, data){
			getSelectBox(result, "map5000NumDem", "1", "map5000Num", "map5000Num");
			getGridIntList();
		});
	}
	
	//제거
	//해상도 종류 조회
	function getGridIntList(){
		var data = {
				"zoneCode"		: $("#zoneCodeDem option:selected").val(),
				"map5000Num"	: $("#map5000NumDem option:selected").val()
		}
		
		ajaxCallJson("/demListSearch/getGridIntList.do", data, function(result, data){
			getSelectBox(result, "gridInt", "1", "gridInt", "gridInt");
		});
	}
-->

	/******************************************* 코드조회 끝 *******************************************/
	
	//검색년도 validation
	function onChangeYear(flag){
		
		var sYear = $("#sYearDem option:selected").val();
		var eYear = $("#eYearDem option:selected").val();
		
		if(sYear > eYear){
			if("s" == flag){
				$("#sYearDem").val(eYear);
			}else{
				$("#eYearDem").val(sYear);
			}
		}
		getZoneCodeList();
	}
	
	//조회 버튼 클릭시 데이터 조회
	function getDemProductList(){
		
		var sYear = $("#sYearDem option:selected").val();
		var eYear = $("#eYearDem option:selected").val();
		var zoneCode = $("#zoneCodeDem option:selected").val();
		
		if(!sYear || !eYear || !zoneCode){
			alert("사업년도와 사업지구 항목이 로딩중입니다. \n항목이 생성되면 조회버튼을 눌러주세요.");
		}
		
		var data = {
				"sYear"			: $("#sYearDem 	  option:selected").val(),
				"eYear"			: $("#eYearDem 	  option:selected").val(),
				"zoneCode"		: $("#zoneCodeDem option:selected").val(),
				"zoneNam"		: $("#zoneCodeDem option:selected").text(),
				"map5000Num"	: $("#map5000NumDem").val(),
// 				"gridInt"		: $("#gridInt option:selected").val()		해상도 검색 이벤트 제거
		}
		
		//기존 선택 내역 삭제
/** 추후 주석 해제 필요 ##################################################################################################################
		$("#form1").empty();
**/
		demSrchParam = data;
		getDemProductListPaging();
	}
	
	function getDemProductListPaging(){
		
		var data = demSrchParam;
		
		setPagingInfo("mainDiv", "getDemProductListPaging", "/demListSearch/getDemProductList.do");
		ajaxCallJson("/demListSearch/getDemProductList.do", data, function(result, data){
			if (result.list != null){
				
				$("#demProductList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				//헤더 체크박스 초기화
				$("#demProductHead input:checkbox").prop("checked", false);
				
				if(result.list.length > 0){
					$("#demProductList").empty().append($("#tmplDemProduct").tmpl(result.list));
					
					//선택 내역 존재시 체크박스 선택 상태로 변경
					$("#demProductList input:checkbox[id=chkDemProduct]").each(function(){
						
						var len = $("#form1 #demCheckList ul div[id='" + $(this).val() + "']").length;
						
						if(len > 0){
							$(this).prop("checked", true);
						}
					});
				}else{
					$("#demProductList").html("<tr><td colspan=\"" + $("#demProductHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				demSrchParam["imageCde"] = result.imageCde;
				
				$("#demProductList").parent().parent(".scroll").mCustomScrollbar();
				initCheckbox();
				
				appListInit();
			}
		});
	}
	
	function demProductView(zoneCode, map5000Num, gridInt){
		var data = {
				"zoneCode"		: zoneCode, 
				"map5000Num"	: map5000Num,
				"gridInt"		: gridInt
		}
		ajaxCallPop("/demListSearch/demListSrchDetail.do", data, "", "");
	}
	
	//신청 항목 확인 팝업
	function demListSrchPop(){
		/*
		if($("#form1 div").length < 1){
			alert("항목을 선택해 주세요.");
			return;
		}
		
		var data = $("#form1").serializeArray();
		
		ajaxCallPop("/demListSearch/demListSrchPop.do", data, "", "730");
		*/
		
		/* 확인페이지 생략하고 바로 신청서 작성 페이지로 */
		if($("#form1 #demCheckList ul li div").length < 1){
			alert("영상을 선택해 주세요.");
			return;
		}
		
		var imageCde 	= new Array();
		var zoneCode 	= new Array();
		var zoneNam 	= new Array();
		var map5000Num 	= new Array();
		var gridInt 	= new Array();
		
		$("#form1 #demCheckList ul li div").each(function(){
		
			imageCde.push($(this).children("input:hidden[id=chkImageCde]").val());
			zoneCode.push($(this).children("input:hidden[id=chkZoneCode]").val());
			zoneNam.push($(this).children("input:hidden[id=chkZoneNam]").val());
			map5000Num.push($(this).children("input:hidden[id=chkMap5000Num]").val());
			gridInt.push($(this).children("input:hidden[id=chkGridInt]").val());
		});
		
		var data = {
				"imageCde"	: imageCde,
				"zoneNam"	: zoneNam,
				"keyVal1"	: zoneCode,
				"keyVal2"	: map5000Num,
				"keyVal3"	: gridInt
		};
		ajaxCallPop("/apply/regApplyPop.do", data, "totalApplication", "700");
	}
	
	//전체신청 팝업
	function regApplyConfirm(){
		
		if(demSrchParam == ""){
			alert("데이터 조회 후 신청해 주세요.");
			return;
		}
		
		var data = demSrchParam;
		
		ajaxCallPop("/apply/regApplyConfirm.do", data, "", 460);
		regAllParam = demSrchParam;
	}
	
	//신청 목록 삭제
	function remDemSelLi(zoneCode){
		
		$("#form1 #demCheckList ul #" + zoneCode + " div").each(function(){
			var id = $(this).attr("id");
			
			$("#demProductList input:checkbox[id=chkDemProduct]").each(function(){
				
				if($(this).val() == id){
					$(this).prop("checked", false);
					$(this).parent(".checkbox").removeClass('active');
					if($(this).parents("tr").is('tr')){
						$(this).parents("tr").removeClass('active');
					}
				}
			});
		});
		
		$("#form1 #demCheckList ul #" + zoneCode).remove();
		$("#form1 #demAppTotCnt").text($("#form1 #demCheckList ul li div").length);
		
		appListInit();
	}
	
	function appListInit(){
		
		if($("#form1 #demCheckList ul li").length > 0){
			$("#form1 #demCheckList .inner").css("display", "none");
			$("#form1 #demCheckList").removeClass("noData");
			if(!$("#form1 #demCheckList").hasClass("appList")){
				$("#form1 #demCheckList").addClass("appList");
			}
			
		}else{
			$("#form1 #demCheckList .inner").css("display", "");
			$("#form1 #demCheckList").removeClass("appList");
			if(!$("#form1 #demCheckList").hasClass("noData")){
				$("#form1 #demCheckList").addClass("noData");
			}
		}
	}
	
	function changePageUnitDem(){
		if(demSrchParam != ""){
			getDemProductListPaging();
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
							<th class="colorOr">사업년도 *</th>
							<td>
								<select id="sYearDem" class="select" onChange="onChangeYear('s')" style="width:114px;">
									<option value="">로딩중</option>
								</select><span style="position:relative; top:8px; padding:0 5px">~</span>
								<select id="eYearDem" class="select" onChange="onChangeYear('e')" style="width:114px;">
									<option value="">로딩중</option>
								</select>
							</td>
						</tr>
						<tr>
							<th class="colorOr">사업지구 *</th>
							<td>
								<select id="zoneCodeDem" class="select"`>
									<option value="">로딩중</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>도엽번호</th>
							<td>
								<input type="text" id="map5000NumDem" name="map5000NumDem"/>
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<th>도엽번호</th> -->
<!-- 							<td> -->
<!-- 								<select id="map5000NumDem" class="select" onChange="getGridIntList();"> -->
<!-- 									<option value="">전체</option> -->
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<th>해상도</th> -->
<!-- 							<td> -->
<!-- 								<select id="gridInt" class="select"> -->
<!-- 									<option value="">전체</option> -->
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 						</tr> -->
					</tbody>
				</table>
			</div>
			<!-- .boardStyle_1 -->
			<a href="#none" class="searchDivBtn" onclick="getDemProductList();">조회</a>
		</div>
		<h3 class="tit">신청 목록 (<span id="demAppTotCnt">0</span>)</h3>
	</div>
	<div id="demCheckList" class="scroll noData">
		<!-- .appList -->
		<div class="inner">
			
			<p>추가된 신청자료가 없습니다.</p>
		</div>
		<ul>
		</ul>
		<!-- /.appList -->
	</div>
	<div class="bottom">
		<a href="#commonPop" onclick="demListSrchPop();">
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
			<select id="_pageUnit" class="select" style="width:130px;" onchange="changePageUnitDem();">
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
					<col width="10%" />
					<col width="10%" />
					<col width="11%" />
					<col width="9%" />
					<col width="9%" />
					<col width="9%" />
					<col width="10%" />
				</colgroup>
				<thead id="demProductHead">
					<tr>
						<th><span class="checkbox theadCheckbox"><input type="checkbox" id="chkAirAll" /></span></th>
						<th>사업년도</th>
						<th>사업지구명</th>
						<th>도엽명</th>
						<th>도엽번호</th>
						<th>지리정보등급</th>
						<th>수치지면자료 유무</th>
						<th>관리관측도 유무</th>
						<th>관리관측도 유무</th>
						<th class="last">해상도</th>
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
				<col width="10%" />
				<col width="10%" />
				<col width="11%" />
				<col width="9%" />
				<col width="9%" />
				<col width="9%" />
				<col width="10%" />
			</colgroup>
			<tbody id="demProductList">
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

<script id="tmplDemProduct" type="text/x-jquery-tmpl">
	<tr>
		<td>
			<span class="checkbox">
				<input type="checkbox" id="chkDemProduct" name="chkDemProduct" value="{{html zoneCode}}{{html map5000Num}}{{html gridInt}}" {{html fileExt != 'O' ? 'disabled' : '' }}/>
				<input type="hidden" id="chkImageCde" name="chkImageCde" value="{{html imageCde}}" />
				<input type="hidden" id="chkZoneCode" name="chkZoneCode" value="{{html zoneCode}}" />
				<input type="hidden" id="chkMap5000Num" name="chkMap5000Num" value="{{html map5000Num}}" />
				<input type="hidden" id="chkZoneYy" name="chkZoneYy" value="{{html zoneYy}}" />
				<input type="hidden" id="chkZoneNam" name="chkZoneNam" value="{{html zoneNam}}" />
				<input type="hidden" id="chkMap5000Nam" name="chkMap5000Nam" value="{{html map5000Nam}}" />
				<input type="hidden" id="chkGridInt" name="chkGridInt" value="{{html gridInt}}" />
			</span>
		</td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html zoneYy}}</a></td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html zoneNam}}</a></td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html map5000Nam}}</a></td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html map5000Num}}</a></td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html securityCde}}</a></td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html dtdOx}}</a></td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html shadedImgOx}}</a></td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html obsRgsOx}}</a></td>
		<td><a href="#none" onclick="demProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gridInt}}')">{{html gridInt}}</a></td>
	</tr>
</script>