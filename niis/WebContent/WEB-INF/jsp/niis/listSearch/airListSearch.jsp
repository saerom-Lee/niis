<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	var airSrchParam = "";
	var regAllParam = "";
	
	$(document).ready(function(){
		$("#mainDiv .scroll").each(function(){
			$(this).mCustomScrollbar("destroy");
			$(this).mCustomScrollbar();
		});
		
		//화면 진입시 코드 조회
		getYearList();
		getCameraCodeList();
		
		//체크박스 클릭 이벤트
		$("#airProductList").on("click", "input[type=checkbox]", function(){
			if(($("#form1 #airCheckList ul li div").length)+1 > 50){
				alert("영상은 50건까지 신청할 수 있습니다.");
				return false;
			}
															   
	
			var isChecked = $(this).is(":checked");
			
			var zoneCode = $(this).parent().children("input[id=chkZoneCode]").val();
			var zoneNam = $(this).parent().children("input[id=chkZoneNam]").val();
		
			var liObj = $("#form1 #airCheckList ul #" + zoneCode);
			var divObj = $("#form1 #airCheckList ul #" + $(this).val());
   
			if(isChecked){
				if(liObj.length == 0){
					$("#form1 #airCheckList ul").append("<li id=\"" + zoneCode + "\">" + zoneNam + "(<span>1</span>)<a href=\"#none\" onclick=\"remAirSelLi('" + zoneCode + "')\">삭제</a></li>");
				}
				
				if(divObj.length == 0){
					$("#form1 #airCheckList ul #" + zoneCode).append("<div id=\"" + $(this).val() + "\" style=\"display:none;\"></div>");
					$("#form1 #airCheckList ul #" + zoneCode +" div[id='" + $(this).val() + "']").append($(this).parent().children("input[type=hidden]").clone());
				}
			}else{
				if(liObj.children("div").length < 2){
					liObj.remove();
				}else{
					divObj.remove();
				}
			}
			liObj.children("span").text(liObj.children("div").length);
			$("#form1 #airAppTotCnt").text($("#form1 #airCheckList ul li div").length);
			
			appListInit();
		});
		
		//상단 체크박스 클릭 이벤트
		$("#airProductHead").on("click", "input[type=checkbox]", function(){
			if(($("#form1 #airCheckList ul li div").length)+1 > 50){
				alert("영상은 50건까지 신청할 수 있습니다.");
				return false;
			}
															   	 
			var isChecked = $(this).is(":checked");
			
			$("#airProductList input[type=checkbox]").each(function(i){
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
		ajaxCallJson("/airListSearch/getYearList.do", "", function(result, data){
			getSelectBox(result, "sYearAir", "0", "zoneYy", "zoneYy");
			getSelectBox(result, "eYearAir", "0", "zoneYy", "zoneYy");
			getZoneCodeList();
		});
	}
	
	//사업지구 종류 조회
	function getZoneCodeList(){
		var data = {
				"sYear"	: $("#sYearAir option:selected").val(),
				"eYear"	: $("#eYearAir option:selected").val()
		}
		
		ajaxCallJson("/airListSearch/getZoneCodeList.do", data, function(result, data){
			getSelectBox(result, "zoneCodeAir", "0", "zoneCode", "zoneNam");
			getPhCourseList();
		});
	}
	
	//코스번호 졸류 조회
	function getPhCourseList(){
		var data = {
				"zoneCode"	: $("#zoneCodeAir option:selected").val()
		}
		
		ajaxCallJson("/airListSearch/getPhCourseList.do", data, function(result, data){
			getSelectBox(result, "phCourseAir", "1", "phCourse", "phCourse");
			getPhNumList();
		});
	}
	
	//사진번호 종류 조회
	function getPhNumList(){
		var data = {
				"zoneCode"	: $("#zoneCodeAir option:selected").val(),
				"phCourse"	: $("#phCourseAir option:selected").val()
		}
		
		ajaxCallJson("/airListSearch/getPhNumList.do", data, function(result, data){
			getSelectBox(result, "phNumAir", "1", "phNum", "phNum");
			getResolutionList();
		});
	}
	
	//카메라구분 코드 조회
	function getCameraCodeList(){
		ajaxCallJson("/airListSearch/getCameraCodeList.do", "", function(result, data){
			getSelectBox(result, "cameraCodeAir", "1");
		});
	}
	//해상도 종류 조회
	function getResolutionList(){
		var data = {
				"zoneCode"	: $("#zoneCodeAir option:selected").val(),
				"phCourse"	: $("#phCourseAir option:selected").val(),
				"phNum"		: $("#phNumAir option:selected").val()
		}
		
		ajaxCallJson("/airListSearch/getResolutionList.do", data, function(result, data){
			getSelectBox(result, "resolutionAir", "1", "resolution", "resolution");
		});
	}
	/******************************************* 코드조회 끝 *******************************************/
	
	//검색년도 validation
	function onChangeYear(flag){
		
		var sYear = $("#sYearAir option:selected").val();
		var eYear = $("#eYearAir option:selected").val();
		
		if(sYear > eYear){
			if("s" == flag){
				$("#sYearAir").val(eYear);
			}else{
				$("#eYearAir").val(sYear);
			}
		}
		getZoneCodeList();
	}
	
	//조회 버튼 클릭시 데이터 조회
	function getAirProductList(){
		
		var sYear = $("#sYearAir option:selected").val();
		var eYear = $("#eYearAir option:selected").val();
		var zoneCode = $("#zoneCodeAir option:selected").val();
		
		if(!sYear || !eYear || !zoneCode){
			alert("사업년도와 사업지구 항목이 로딩중입니다. \n항목이 생성되면 조회버튼을 눌러주세요.");
		}
			
		var data = {
				"sYear"			: $("#sYearAir option:selected").val(),
				"eYear"			: $("#eYearAir option:selected").val(),
				"zoneCode"		: $("#zoneCodeAir option:selected").val(),
				"zoneNam"		: $("#zoneCodeAir option:selected").text(),
				"phCourse"		: $("#phCourseAir option:selected").val(),
				"phNum"			: $("#phNumAir option:selected").val(),
				"cameraCde" 	: $("#cameraCodeAir option:selected").val(),
				"resolution"	: $("#resolutionAir option:selected").val()
		}
		//기존 선택 내역 삭제
/** 추후 주석 해제 필요 ##################################################################################################################
		$("#form1").empty();
**/
		airSrchParam = data;
		getAirProductListPaging();
	}
	
	function getAirProductListPaging(){
		
		var data = airSrchParam;
		
		setPagingInfo("mainDiv", "getAirProductListPaging", "/airListSearch/getAirProductList.do");
		ajaxCallJson("/airListSearch/getAirProductList.do", data, function(result, data){
			if (result.list != null){
				
				$("#airProductList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				//헤더 체크박스 초기화
				$("#airProductHead input:checkbox").prop("checked", false);
				
				if(result.list.length > 0){
					$("#airProductList").empty().append($("#tmplAirProduct").tmpl(result.list));
					
					//선택 내역 존재시 체크박스 선택 상태로 변경
					$("#airProductList input:checkbox[id=chkAirProduct]").each(function(){
						
						var len = $("#form1 #airCheckList ul div[id='" + $(this).val() + "']").length;
						
						if(len > 0){
							$(this).prop("checked", true);
						}
					});
				}else{
					$("#airProductList").html("<tr><td colspan=\"" + $("#airProductHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				airSrchParam["imageCde"] = result.imageCde;
				
				$("#airProductList").parent().parent(".scroll").mCustomScrollbar();
				initCheckbox();
				
				appListInit();
			}
		});
	}
	
	function airProductView(zoneCode, phCourse, phNum){
		var data = {
				"zoneCode"	: zoneCode, 
				"phCourse"	: phCourse, 
				"phNum"		: phNum
		}
		ajaxCallPop("/airListSearch/airListSrchDetail.do", data, "", "");
	}
	
	//신청 항목 확인 팝업
	function airListSrchPop(){
		/* 
		if($("#form1 #airCheckList ul li div").length < 1){
			alert("영상을 선택해 주세요.");
			return;
		}
		
		var data = $("#form1").serializeArray();
		
		ajaxCallPop("/airListSearch/airListSrchPop.do", data, "", "730");
		 */
		 
		/* 확인페이지 생략하고 바로 신청서 작성 페이지로 */
		if($("#form1 #airCheckList ul li div").length < 1){
			alert("영상을 선택해 주세요.");
			return;
		}
		 
		var imageCde = new Array();
		var zoneCode = new Array();
		var zoneNam = new Array();
		var phCourse = new Array();
		var phNum = new Array();
		
		$("#form1 #airCheckList ul li div").each(function(){

			imageCde.push($(this).children("input:hidden[id=chkImageCde]").val());
			zoneCode.push($(this).children("input:hidden[id=chkZoneCode]").val());
			zoneNam.push($(this).children("input:hidden[id=chkZoneNam]").val());
			phCourse.push($(this).children("input:hidden[id=chkPhCourse]").val());
			phNum.push($(this).children("input:hidden[id=chkPhNum]").val());
		});
	
		var data = {
				"imageCde"	: imageCde,
				"zoneNam"	: zoneNam,
				"keyVal1"	: zoneCode,
				"keyVal2"	: phCourse,
				"keyVal3"	: phNum
		};
		ajaxCallPop("/apply/regApplyPop.do", data, "totalApplication", "700");
	}
	
	//전체신청 팝업
	function regApplyConfirm(){
		
		if(airSrchParam == ""){
			alert("데이터 조회 후 신청해 주세요.");
			return;
		}
		
		var data = airSrchParam;
		
		ajaxCallPop("/apply/regApplyConfirm.do", data, "", 460);
		regAllParam = airSrchParam;
	}
	
	//신청 목록 삭제
	function remAirSelLi(zoneCode){
		
		$("#form1 #airCheckList ul #" + zoneCode + " div").each(function(){
			var id = $(this).attr("id");
			
			$("#airProductList input:checkbox[id=chkAirProduct]").each(function(){
				
				if($(this).val() == id){
					$(this).prop("checked", false);
					$(this).parent(".checkbox").removeClass('active');
					if($(this).parents("tr").is('tr')){
						$(this).parents("tr").removeClass('active');
					}
				}
			});
		});
		
		$("#form1 #airCheckList ul #" + zoneCode).remove();
		$("#form1 #airAppTotCnt").text($("#form1 #airCheckList ul li div").length);
		
		appListInit();
	}
	
	function appListInit(){
		
		if($("#form1 #airCheckList ul li").length > 0){
			$("#form1 #airCheckList .inner").css("display", "none");
			$("#form1 #airCheckList").removeClass("noData");
			if(!$("#form1 #airCheckList").hasClass("appList")){
				$("#form1 #airCheckList").addClass("appList");
			}
			
		}else{
			$("#form1 #airCheckList .inner").css("display", "");
			$("#form1 #airCheckList").removeClass("appList");
			if(!$("#form1 #airCheckList").hasClass("noData")){
				$("#form1 #airCheckList").addClass("noData");
			}
		}
	}
	
	function changePageUnitAir(){
		if(airSrchParam != ""){
			getAirProductListPaging();
		}
	}
	
</script>

<!-- .twoDepthMenu -->
<form id="form1">
<div class="twoDepthMenu">
	<div class="top">
		<h3 class="tit">
			<a href="#none" class="btnCloseOpen"></a>조회 항목
			<a href="#none" id="serviceGuide_7" class="btnServiceGuide">
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
								<select id="sYearAir" class="select" onChange="onChangeYear('s')" style="width:114px;">
									<option value="">로딩중</option> 
								</select><span style="position:relative; top:8px; padding:0 5px">~</span>
								<select id="eYearAir" class="select" onChange="onChangeYear('e')" style="width:114px;">
									<option value="">로딩중</option> 
								</select>
							</td>
						</tr>
						<tr>
							<th class="colorOr">사업지구 *</th>
							<td>
								<select id="zoneCodeAir" class="select" onChange="getPhCourseList();">
									<option value="">로딩중</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>코스번호</th>
							<td>
								<select id="phCourseAir" class="select" onChange="getPhNumList();">
									<option value="">전체</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>사진번호</th>
							<td>
								<select id="phNumAir" class="select">
									<option value="">전체</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>해상도</th>
							<td>
								<select id="resolutionAir" class="select">
									<option value="">전체</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>카메라구분</th>
							<td>
								<select id="cameraCodeAir" class="select">
									<option value="">전체</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /.boardStyle_1 -->
			<a href="#none" class="searchDivBtn" onclick="getAirProductList();">조회</a>
		</div>
		<h3 class="tit">신청 목록 (<span id="airAppTotCnt">0</span>)</h3>
	</div>
	<div id="airCheckList" class="scroll noData">
		<!-- .appList -->
		<div class="inner">
			
			<p>추가된 신청자료가 없습니다.</p>
		</div>
		<ul>
		</ul>
		<!-- /.appList -->
	</div>
	<div class="bottom">
		<a href="#commonPop" onclick="airListSrchPop();">
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
			<select id="_pageUnit" class="select" style="width:130px;" onchange="changePageUnitAir();">
				<option value="25">25개씩 보기</option>
				<option value="50">50개씩 보기</option>
				<option value="100">100개씩 보기</option>
			</select>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="50px" />
					<col width="8%" />
					<col width="*%" />
					<col width="10%" />
					<col width="8%" />
					<col width="8%" />
					<col width="10%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="5%" />
					<col width="10%" />
				</colgroup>
				<thead id="airProductHead">
					<tr>
						<th><span class="checkbox theadCheckbox"><input type="checkbox" id="chkAirAll" /></span></th>
						<th>사업년도</th>
						<th>사업지구명</th>
						<th>카메라구분</th>
						<th>코스번호</th>
						<th>사진번호</th>
						<th>지리정보등급</th>
						<th>항공사진<br />수량</th>
						<th>양화필름<br />수량</th>
						<th>성과사진<br />수량</th>
						<th>저해상도<br />파일유무</th>
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
				<col width="8%" />
				<col width="*%" />
				<col width="10%" />
				<col width="8%" />
				<col width="8%" />
				<col width="10%" />
				<col width="5%" />
				<col width="5%" />
				<col width="5%" />
				<col width="5%" />
				<col width="10%" />
			</colgroup>
			<tbody id="airProductList">
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

<script id="tmplAirProduct" type="text/x-jquery-tmpl">
	<tr>
		<td>
			<span class="checkbox">
				<input type="checkbox" id="chkAirProduct" name="chkAirProduct" value="{{html zoneCode}}{{html phCourse}}{{html phNum}}" {{html fileExt != 'O' ? 'disabled' : '' }}/>
				<input type="hidden" id="chkImageCde" name="chkImageCde" value="{{html imageCde}}" />
				<input type="hidden" id="chkZoneCode" name="chkZoneCode" value="{{html zoneCode}}" />
				<input type="hidden" id="chkPhCourse" name="chkPhCourse" value="{{html phCourse}}" />
				<input type="hidden" id="chkPhNum" name="chkPhNum" value="{{html phNum}}" />
				<input type="hidden" id="chkZoneYy" name="chkZoneYy" value="{{html zoneYy}}" />
				<input type="hidden" id="chkZoneNam" name="chkZoneNam" value="{{html zoneNam}}" />
				<input type="hidden" id="chkCameraCde" name="chkCameraCde" value="{{html cameraCde}}" />
				<input type="hidden" id="chkResolution" name="chkResolution" value="{{html resolution}}" />
			</span>
		</td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html zoneYy}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html zoneNam}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html cameraCde}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html phCourse}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html phNum}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html securityCde}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html aphNum}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html filmNum}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html rphNum}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html nixOx}}</a></td>
		<td><a href="#none" onclick="airProductView('{{html zoneCode}}', '{{html phCourse}}', '{{html phNum}}')">{{html resolution}}</a></td>
	</tr>
</script>