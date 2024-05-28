<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	var ortSrchParam = "";
	var regAllParam = "";
	
	$(document).ready(function(){
		
		$("#mainDiv .scroll").each(function(){
			$(this).mCustomScrollbar("destroy");
			$(this).mCustomScrollbar();
		});
		
		
		//화면 진입시 코드 조회
		getYearList();
		
		//체크박스 클릭 이벤트
		$("#ortProductList").on("click", "input[type=checkbox]", function(){
			if(($("#form1 #ortCheckList ul li div").length)+1 > 50){
				alert("영상은 50건까지 신청할 수 있습니다.");
				return false;
			}			
															   
			var isChecked = $(this).is(":checked");
			
			var zoneCode = $(this).parent().children("input[id=chkZoneCode]").val();
			var zoneNam = $(this).parent().children("input[id=chkZoneNam]").val();

			var liObj = $("#form1 #ortCheckList ul #" + zoneCode);
			var divObj = $("#form1 #ortCheckList ul #" + $(this).val());

			if(isChecked){
				if(liObj.length == 0){
					$("#form1 #ortCheckList ul").append("<li id=\"" + zoneCode + "\">" + zoneNam + "(<span>1</span>)<a href=\"#none\" onclick=\"remOrtSelLi('" + zoneCode + "')\">삭제</a></li>");
				}
				
				if(divObj.length == 0){
					$("#form1 #ortCheckList ul #" + zoneCode).append("<div id=\"" + $(this).val() + "\"style=\"display:none;\"></div>");
					$("#form1 #ortCheckList ul #" + zoneCode +" div[id='" + $(this).val() + "']").append($(this).parent().children("input[type=hidden]").clone());
				}
			}else{
				if(liObj.children("div").length < 2){
					liObj.remove();
				}else{
					divObj.remove();
				}
			}
			liObj.children("span").text(liObj.children("div").length);
			$("#form1 #ortAppTotCnt").text($("#form1 #ortCheckList ul li div").length);
			
			appListInit();
		});
		
		//상단 체크박스 클릭 이벤트
		$("#ortProductHead").on("click", "input[type=checkbox]", function(){
			if(($("#form1 #ortCheckList ul li div").length)+1 > 50){
				alert("영상은 50건까지 신청할 수 있습니다.");
				return false;
			}			
															   
			var isChecked = $(this).is(":checked");
			
			$("#ortProductList input[type=checkbox]").each(function(i){
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
		ajaxCallJson("/ortListSearch/getYearList.do", "", function(result, data){
			getSelectBox(result, "sYearOrt", "0", "zoneYy", "zoneYy");
			getSelectBox(result, "eYearOrt", "0", "zoneYy", "zoneYy");
			getZoneCodeList();
		});
	}
	
	//사업지구 종류 조회
	function getZoneCodeList(){
		var data = {
				"sYear"	: $("#sYearOrt option:selected").val(),
				"eYear"	: $("#eYearOrt option:selected").val()
		}
		
		ajaxCallJson("/ortListSearch/getZoneCodeList.do", data, function(result, data){
			getSelectBox(result, "zoneCodeOrt", "0", "zoneCode", "zoneNam");
// 			getMap5000NumList();
		});
	}
	
<!--	
	//도엽번호 종류 조회
	function getMap5000NumList(){
		var data = {
				"zoneCode"	: $("#zoneCodeOrt option:selected").val()
		}
		
		ajaxCallJson("/ortListSearch/getMap5000NumList.do", data, function(result, data){
			getSelectBox(result, "map5000NumOrt", "1", "map5000Num", "map5000Num");
			getGtypDstList();
		});
	}
	
	//해상도 종류 조회
	function getGtypDstList(){
		var data = {
				"zoneCode"		: $("#zoneCodeOrt option:selected").val(),
				"map5000Num"	: $("#phCourseOrt option:selected").val()
		}
		
		ajaxCallJson("/ortListSearch/getGtypDstList.do", data, function(result, data){
			getSelectBox(result, "gtypDst", "1", "gtypDst", "gtypDst");
		});
	}
-->	
	/******************************************* 코드조회 끝 *******************************************/
	
	//검색년도 validation
	function onChangeYear(flag){
		
		var sYear = $("#sYearOrt option:selected").val();
		var eYear = $("#eYearOrt option:selected").val();
		
		if(sYear > eYear){
			if("s" == flag){
				$("#sYearOrt").val(eYear);
			}else{
				$("#eYearOrt").val(sYear);
			}
		}
		getZoneCodeList();
	}
	
	//조회 버튼 클릭시 데이터 조회
	function getOrtProductList(){
		
		var sYear = $("#sYearOrt option:selected").val();
		var eYear = $("#eYearOrt option:selected").val();
		var zoneCode = $("#zoneCodeOrt option:selected").val();
		
		if(!sYear || !eYear || !zoneCode){
			alert("사업년도와 사업지구 항목이 로딩중입니다. \n항목이 생성되면 조회버튼을 눌러주세요.");
		}
		
		var data = {
					  "sYear" 		: $("#sYearOrt option:selected").val()
					, "eYear" 		: $("#eYearOrt option:selected").val()
					, "zoneCode"	: $("#zoneCodeOrt option:selected").val()
					, "zoneNam"		: $("#zoneCodeOrt option:selected").text()
					, "map5000Num"	: $("#map5000NumOrt").val()
		}
		ortSrchParam = data;
		getOrtProductListPaging();
	}
	
	function getOrtProductListPaging(){
		
		var data = ortSrchParam;
		
		setPagingInfo("mainDiv", "getOrtProductListPaging", "/ortListSearch/getOrtProductList.do");
		ajaxCallJson("/ortListSearch/getOrtProductList.do", data, function(result, data){
			if (result.list != null){
				
				$("#ortProductList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				//헤더 체크박스 초기화
				$("#ortProductHead input:checkbox").prop("checked", false);
				
				if(result.list.length > 0){
					$("#ortProductList").empty().append($("#tmplOrtProduct").tmpl(result.list));
					
					//선택 내역 존재시 체크박스 선택 상태로 변경
					$("#ortProductList input:checkbox[id=chkOrtProduct]").each(function(){
						
						var len = $("#form1 #ortCheckList ul div[id='" + $(this).val() + "']").length;
						
						if(len > 0){
							$(this).prop("checked", true);
						}
					});
				}else{
					$("#ortProductList").html("<tr><td colspan=\"" + $("#ortProductHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				ortSrchParam["imageCde"] = result.imageCde;
				
				$("#ortProductList").parent().parent(".scroll").mCustomScrollbar();
				initCheckbox();
				
				appListInit();
			}
		});
	}
	
	function ortProductView(zoneCode, map5000Num, gtypDst){
		var data = {
				"zoneCode"		: zoneCode, 
				"map5000Num"	: map5000Num,
				"gtypDst"		: gtypDst
		}
		ajaxCallPop("/ortListSearch/ortListSrchDetail.do", data, "", "");
	}
	
	//신청 항목 확인 팝업
	function ortListSrchPop(){
		/* 
		if($("#form1 div").length < 1){
			alert("항목을 선택해 주세요.");
			return;
		}
		
		var data = $("#form1").serializeArray();
		
		ajaxCallPop("/ortListSearch/ortListSrchPop.do", data, "", "730"); 
		*/
		
		/* 확인페이지 생략하고 바로 신청서 작성 페이지로 */
		if($("#form1 #ortCheckList ul li div").length < 1){
			alert("영상을 선택해 주세요.");
			return;
		}
	
		var imageCde 	= new Array();
		var zoneCode 	= new Array();
		var zoneNam 	= new Array();
		var	map5000Num 	= new Array();
		var gtypDst		= new Array();
		
		$("#form1 #ortCheckList ul li div").each(function(){
			
			imageCde.push($(this).children("input:hidden[id=chkImageCde]").val());
			zoneCode.push($(this).children("input:hidden[id=chkZoneCode]").val());
			zoneNam.push($(this).children("input:hidden[id=chkZoneNam]").val());
			map5000Num.push($(this).children("input:hidden[id=chkMap5000Num]").val());
			gtypDst.push($(this).children("input:hidden[id=chkGtypDst]").val());
		});
		
		var data = {
				"imageCde"	: imageCde,
				"zoneNam"	: zoneNam,
				"keyVal1"	: zoneCode,
				"keyVal2"	: map5000Num,
				"keyVal3"	: gtypDst
		};
		ajaxCallPop("/apply/regApplyPop.do", data, "totalApplication", "700");
	}
	
	//전체신청 팝업
	function regApplyConfirm(){
		
		if(ortSrchParam == ""){
			alert("데이터 조회 후 신청해 주세요.");
			return;
		}
		
		var data = ortSrchParam;
		
		ajaxCallPop("/apply/regApplyConfirm.do", data, "", 460);
		regAllParam = ortSrchParam;
	}
	
	//신청 목록 삭제
	function remOrtSelLi(zoneCode){
		
		$("#form1 #ortCheckList ul #" + zoneCode + " div").each(function(){
			var id = $(this).attr("id");
			
			$("#ortProductList input:checkbox[id=chkOrtProduct]").each(function(){
				
				if($(this).val() == id){
					$(this).prop("checked", false);
					$(this).parent(".checkbox").removeClass('active');
					if($(this).parents("tr").is('tr')){
						$(this).parents("tr").removeClass('active');
					}
				}
			});
		});
		
		$("#form1 #ortCheckList ul #" + zoneCode).remove();
		$("#form1 #ortAppTotCnt").text($("#form1 #ortCheckList ul li div").length);
		
		appListInit();
	}
	
	function appListInit(){
		
		if($("#form1 #ortCheckList ul li").length > 0){
			$("#form1 #ortCheckList .inner").css("display", "none");
			$("#form1 #ortCheckList").removeClass("noData");
			if(!$("#form1 #ortCheckList").hasClass("appList")){
				$("#form1 #ortCheckList").addClass("appList");
			}
			
		}else{
			$("#form1 #ortCheckList .inner").css("display", "");
			$("#form1 #ortCheckList").removeClass("appList");
			if(!$("#form1 #ortCheckList").hasClass("noData")){
				$("#form1 #ortCheckList").addClass("noData");
			}
		}
	}
	
	function changePageUnitOrt(){
		if(ortSrchParam != ""){
			getOrtProductListPaging();
		}
	}
	
</script>

<!-- .twoDepthMenu -->
<form id="form1">
<div class="twoDepthMenu">
	<div class="top">
		<h3 class="tit">
			<a href="#none" class="btnCloseOpen"></a>조회 항목
			<a href="#none" id="serviceGuide_11" class="btnServiceGuide">
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
								<select id="sYearOrt" class="select" onChange="onChangeYear('s')" style="width:114px;">
									<option value="">로딩중</option>
								</select><span style="position:relative; top:8px; padding:0 5px">~</span>
								<select id="eYearOrt" class="select" onChange="onChangeYear('e')" style="width:114px;">
									<option value="">로딩중</option>
								</select>
							</td>
						</tr>
						<tr>
							<th class="colorOr">사업지구 *</th>
							<td>
								<select id="zoneCodeOrt" class="select" onChange="getMap5000NumList();">
									<option value="">로딩중</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>도엽번호</th>
							<td>
								<input type="text"	id="map5000NumOrt"	name="map5000NumOrt"	/>
							</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<th>도엽번호</th> -->
<!-- 							<td> -->
<!-- 								<select id="map5000NumOrt" class="select" onChange="getGtypDstList();"> -->
<!-- 									<option value="">전체</option> -->
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<th>해상도</th> -->
<!-- 							<td> -->
<!-- 								<select id="gtypDst" class="select"> -->
<!-- 									<option value="">전체</option> -->
<!-- 								</select> -->
<!-- 							</td> -->
<!-- 						</tr> -->
					</tbody>
				</table>
			</div>
			<!-- .boardStyle_1 -->
			<a href="#none" class="searchDivBtn" onclick="getOrtProductList();">조회</a>
		</div>
		<h3 class="tit">신청 목록 (<span id="ortAppTotCnt">0</span>)</h3>
	</div>
	<div id="ortCheckList" class="scroll noData">
		<!-- .appList -->
		<div class="inner">

			<p>추가된 신청자료가 없습니다.</p>
		</div>
		<ul>
		</ul>
		<!-- /.appList -->
	</div>
	<div class="bottom">
		<a href="#commonPop" onclick="ortListSrchPop();">
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
			<select id="_pageUnit" class="select" style="width:130px;" onchange="changePageUnitOrt();">
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
				<thead id="ortProductHead">
					<tr>
						<th><span class="checkbox theadCheckbox"><input type="checkbox" id="chkAirAll" /></span></th>
						<th>사업년도</th>
						<th>사업지구명</th>
						<th>도엽명</th>
						<th>도엽번호</th>
						<th>지리정보등급</th>
						<th>지상표본거리</th>
						<th>제작기관</th>
						<th>제작년월일</th>
						<th class="last">사용수치표고</th>
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
			<tbody id="ortProductList">
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

<script id="tmplOrtProduct" type="text/x-jquery-tmpl">
	<tr>
		<td>
			<span class="checkbox">
				<input type="checkbox" id="chkOrtProduct" name="chkOrtProduct" value="{{html zoneCode}}{{html map5000Num}}{{html gtypDst}}" {{html fileExt != 'O' ? 'disabled' : '' }}/>
				<input type="hidden" id="chkImageCde" name="chkImageCde" value="{{html imageCde}}" />
				<input type="hidden" id="chkZoneCode" name="chkZoneCode" value="{{html zoneCode}}" />
				<input type="hidden" id="chkMap5000Num" name="chkMap5000Num" value="{{html map5000Num}}" />
				<input type="hidden" id="chkZoneYy" name="chkZoneYy" value="{{html zoneYy}}" />
				<input type="hidden" id="chkZoneNam" name="chkZoneNam" value="{{html zoneNam}}" />
				<input type="hidden" id="chkMap5000Nam" name="chkMap5000Nam" value="{{html map5000Nam}}" />
				<input type="hidden" id="chkGtypDst" name="chkGtypDst" value="{{html gtypDst}}" />
			</span>
		</td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html zoneYy}}</a></td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html zoneNam}}</a></td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html map5000Nam}}</a></td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html map5000Num}}</a></td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html securityCde}}</a></td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html gtypDst}}</a></td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html constCo}}</a></td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html workYmd}}</a></td>
		<td><a href="#none" onclick="ortProductView('{{html zoneCode}}', '{{html map5000Num}}', '{{html gtypDst}}')">{{html usedDem}}</a></td>
	</tr>
</script>