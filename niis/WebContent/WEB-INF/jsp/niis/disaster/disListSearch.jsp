﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	var disSrchParam = "";
	var regAllParam = "";
	var selectedBoard = "";
	
	$(document).ready(function(){
		
		$("#mainDiv .scroll").each(function(){
			$(this).mCustomScrollbar("destroy");
			$(this).mCustomScrollbar();
		});
		
		//화면 진입시 코드 조회
		getDisaCodeList();
		getDisaRegion1List();
		
		//화면 진입시 자동 조회
		getDisaList();
		
		//상단 체크박스 클릭 이벤트
		$("#videoDataHead").on("click", "input[type=checkbox]", function(){
			var isChecked = $(this).is(":checked");
			
			$("#videoDataList input[type=checkbox]").each(function(i){
				if(isChecked != $(this).is(":checked")){
					$(this).click();
				}
			});
			initCheckbox();
		});
	});
	
	/******************************************* 코드조회 시작 *******************************************/
	// 재난유형 조회
	function getDisaCodeList(){
		ajaxCallJson("/disaster/getDisaCodeList.do", "", function(result, data){
			getSelectBox(result, "searchCof", "1", "cdeAll", "cdeNam");
		});
	}
	
	// 시도 조회
	function getDisaRegion1List(){
		ajaxCallJson("/disaster/getDisaRegion1List.do", "", function(result, data){
			getSelectBox(result, "searchRegion1", "1", "code", "codenm");
		});
	}
	
	// 시군구 조회
	function getDisaRegion2List(){
		var bjcd = $("#searchRegion1 option:selected").val();
		if(bjcd == "") return;
		else {
			var data = {
					"bjcd"	: bjcd
			}
			
			ajaxCallJson("/disaster/getDisaRegion2List.do", data, function(result, data){
				getSelectBox(result, "searchRegion2", "1", "codenm", "codenm");
			});			
		}
	}
	/******************************************* 코드조회 끝 *******************************************/
	
	// 조회 버튼 클릭시 데이터 조회
	function getDisaList(){
		var region1 = $("#searchRegion1 option:selected").val();
		var region2 = $("#searchRegion2 option:selected").val();
		
		if(region1 != "") region1 = $("#searchRegion1 option:selected").text();
		if(region2 != "") region1 = region1+' '+region2;
		
		var data = {
				"searchCof"		: $("#searchCof option:selected").val(),
				"searchRegion"	: region1,
				"searchKeyword"	: $("#searchKeyword").val(),
				"listHeight"	: $('#ortCheckList').height()
		}
		//기존 선택 내역 삭제
		/** 추후 주석 해제 필요 ##################################################################################################################
				$("#form1").empty();
		**/
		disSrchParam = data;
		getDisaListPaging();
	}
	
	function getDisaListPaging(flag){
		var data = disSrchParam;
		
		setPagingInfo("mainDiv", "getDisaListPaging", "/disaster/getDisasterList.do", 5);
		ajaxCallJson("/disaster/getDisasterList.do", data, function(result, data){
			$("#disaList").empty();
			
			if (result.list != null){
				if(result.list.length > 0){
					$("#disaList").append($("#tmplDisaList").tmpl(result.list));
					$("#listCnt").text(result.totalcnt);
					
					$("#form1 #disaList li").each(function(){
						if($(this).find("strong").length > 0){
							$(this).html($(this).find("strong").html());
						}
					});
					
					var objli = $("#form1 #disaList li[id=" + selectedBoard + "]");
					if(objli.find("strong").length == 0){
						var child = objli.html();
						
						objli.html('<strong style="padding:0px">' + child + '</strong>');
					}
					
				}else{
					$("#listCnt").text(0);
				}
			}
			

			if ($("#form1 .boardList #disaList li").length > 0) {
				$("#form1 .boardList #disaList li").first().find("a").click();
			} else {
				$("#listCnt2").text("");
				$("#dataList").empty();
				$("#videoDataList").empty();
			}
			
			appListInit();
		});
	}
	
	// 조회 버튼 클릭시 데이터 조회
	function getDisasterVideoList(msfrtnId){
		$("#form1 #disaList li").each(function(){
			if($(this).find("strong").length > 0){
				$(this).html($(this).find("strong").html());
			}
		});
		
		var objli = $('#form1 #disaList li[id="' + msfrtnId + '"]');
		if(objli.find("strong").length == 0){
			var child = objli.html();
			
			objli.html('<strong style="padding:0px">' + child + '</strong>');
		}
		
		var data = {
				"msfrtnId" : msfrtnId
		}
		
		ajaxCallJson("/disaster/getDisasterVideoList.do", data, function(result, data){
			if (result.list != null){
				$("#videoDataList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				//헤더 체크박스 초기화
				$("#videoDataHead input:checkbox").prop("checked", false);
				
				if(result.list.length > 0){
					$("#listCnt2").text("(자료 수 : "+result.list.length+"개)");
					$("#videoDataList").empty().append($("#tmplVideoData").tmpl(result.list));
					
					//선택 내역 존재시 체크박스 선택 상태로 변경
					$("#videoDataList input:checkbox[id=chkVideoData]").each(function(){
						
						var len = $("#form1 #airCheckList ul div[id='" + $(this).val() + "']").length;
						
						if(len > 0){
							$(this).prop("checked", true);
						}
					});
				}else{
					$("#videoDataList").html("<tr><td colspan=\"" + $("#videoDataHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				
				$("#dataList").empty().append($("#tmplDataMap").tmpl(result.map));
				
				$("#videoDataList").parent().parent(".scroll").mCustomScrollbar();
				initCheckbox();
			}
			
			scroll();
			
			$("#form1 .scroll").each(function(){
				$(this).mCustomScrollbar("destroy");
				$(this).mCustomScrollbar();
			});
		});
	}
	
	// 미리보기 클릭시
	function fnDisasterPicture(datasetId, msfrtnId){
		var data = {
				"datasetId"		: datasetId,
				"msfrtnId"		: msfrtnId
		}
		
		ajaxCallPop("/disaster/disasterTifImgData.do", data, "preview", 695);
		regAllParam = disSrchParam;
	}
	
	// 선택 다운로드 클릭시
	function regApplyConfirm(){
		var gllryId = $('#gllryId').val();
	    var isSeasonChk = $("#videoDataList input[type=checkbox]").is(":checked");
        if(!isSeasonChk){
    		alert("영상정보 종류를 한개 이상 선택해주세요.");
            return;
        }
        var params = [];
		$("#videoDataList input[type=checkbox]:checked").each(function() {
			var dataArr = $(this).val().split(",");
			var insertParam = {};
			insertParam.datasetId = dataArr[0];
			insertParam.msfrtnId = dataArr[1];
			params.push(insertParam);
		});
		
		var _formdata = new FormData();	
		_formdata.append('jsonData',JSON.stringify(params));
		$.ajax({
			url: '<c:url value="/disaster/disasterFileDwn.do"/>',
			type:"POST",
			contentType: false,
			processData: false,
			cache: false,
			data: _formdata,
			success : function(result) {
				var path = 'path=' + result.zipFileName;
				var name = '&name=' + result.outFileName;
				location.href = '<c:url value="/disaster/disasterFileDown.do?"/>' + encodeURI(path , "UTF-8") + encodeURI(name , "UTF-8");
			},
			error: function(e){
				alert("영상정보 다운로드에 실패했습니다.\n다시 시도하세요.");
			    return;
			},
			complete : function() {
			}
		});
	}

	function appListInit() {
		if ($("#form1 #ortCheckList ul li").length > 0) {
			$("#form1 #ortCheckList .inner").css("display", "none");
		} else {
			$("#form1 #ortCheckList .inner").css("display", "");
		}
	}
</script>

<!-- .twoDepthMenu -->
<form id="form1">
<div class="twoDepthMenu">
	<div class="top">
		<h3 class="tit">
			<a href="#none" class="btnCloseOpen"></a>조회 항목
			<span style="margin-left:190px; font-size:14px; color:#F3F781; position:absolute;">도움말</span>
			<a href="#none" id="serviceGuide_9" class="btnServiceGuide">
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
<!-- 							<th class="colorOr">재난 유형 *</th> -->
							<th>재난 유형</th>
							<td>
								<select id="searchCof" class="select" onChange=""></select>
							</td>
						</tr>
						<tr>
							<th>재난 지역</th>
							<td>
								<select id="searchRegion1" class="select" onChange="getDisaRegion2List()">
									<option value="">전체</option>
								</select>
							</td>
						</tr>
						</tr>
							<th></th>
							<td>
								<select id="searchRegion2" class="select" onChange="">
									<option value="">전체</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>재난 명</th>
							<td>
								<input type="text" title="재난 명 입력" id="searchKeyword" name="searchKeyword" onkeydown="javascript:if(event.keyCode == 13){getDisaList();return false;}" value="" placeholder="키워드를 입력해주세요." />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- .boardStyle_1 -->
			<a href="#none" class="searchDivBtn" onclick="getDisaList();">조회</a>
		</div>
		<h3 class="tit">재난 목록 (<span id="listCnt">0</span>건)</h3>
	</div>
	<div id="ortCheckList" class="scroll boardList">
		<div class="inner" style="padding-top: 70px; text-align: center;">
			<p style="padding-top: 30px; font-size: 16px;">조회된 재난 목록이 없습니다.</p>
		</div>
		<ul id="disaList">
		</ul>
	</div>
	<div class="paging">
		<ul>
		</ul>
	</div>
<!-- 	<div class="bottom"> -->
<!-- 	</div> -->
</div>
</form>
<!-- /.twoDepthMenu -->

<!-- .container -->
<div class="container">
	<div class="top">
		<div class="contTit">
			<h3>재난 정보 및 자료 목록</h3>
		</div>
		
		<h3 class="article depth1">재난 정보</h3>
		<div class="thead">
			<table>
				<colgroup>
					<col width="30%" />
					<col width="30%" />
					<col width="20%" />
					<col width="20%" />
				</colgroup>
				<thead id="dataHead">
					<tr>
						<th>재난 명</th>
						<th>재난 지역</th>
						<th>재난 유형</th>
						<th class="last">재난 발생일</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tbody">
			<table>
				<colgroup>
					<col width="30%" />
					<col width="30%" />
					<col width="20%" />
					<col width="20%" />
				</colgroup>
				<tbody id="dataList">
				</tbody>
			</table>
		</div><br/><br/>
		
		<h3 class="article depth1">재난 자료 목록 <span id="listCnt2"></span></h3>
		<p class="attention">페이지 내에서 체크하신 영상 목록이 다운로드됩니다</p>
		<p class="attention">SHP파일 영상은 미리보기가 지원하지 않습니다.</p>
		<div class="thead">
			<table>
				<colgroup>
					<col width="50px" />
					<col width="15%" />
					<col width="15%" />
					<col width="15%" />
					<col width="20%" />
					<col width="10%" />
					<col width="*%" />
				</colgroup>
				<thead id="videoDataHead">
					<tr>
						<th><span class="checkbox theadCheckbox"><input type="checkbox" id="chkAirAll" /></span></th>
						<th>데이터 구분(대)</th>
						<th>데이터 구분(중)</th>
						<th>데이터 구분(소)</th>
						<th>데이터 명칭</th>
						<th>구축일</th>
						<th class="last">비고</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="scroll tbody">
		<table>
			<colgroup>
				<col width="50px" />
				<col width="15%" />
				<col width="15%" />
				<col width="15%" />
				<col width="20%" />
				<col width="10%" />
				<col width="*%" />
			</colgroup>
			<tbody id="videoDataList">
			</tbody>
		</table>
	</div>
	<div class="paging">
		<div class="btn"><a href="#commonPop" onclick="regApplyConfirm();">선택 다운로드</a></div>
	</div>
</div>
<!-- /.container -->

<script id="tmplDisaList" type="text/x-jquery-tmpl">
	<li id={{html msfrtnId}}>
		<a href="#none" class="noclick" onclick="getDisasterVideoList('{{html msfrtnId}}')" style="overflow:hidden; text-overflow:ellipsis; white-space: nowrap; width:85%;">{{html datasetNm}}</a>
	</li>
</script>

<script id="tmplDataMap" type="text/x-jquery-tmpl">
	<tr>
		<td>{{html datasetNm}}</td>
		<td>{{html addr}}</td>
		<td>{{html msfrtnTyNm}}</td>
		<td>{{html uploadDt}}</td>
	</tr>
</script>

<script id="tmplVideoData" type="text/x-jquery-tmpl">
	<tr>
		<td>
			<span class="checkbox">
				<input type="checkbox" id="chkVideoData" name="chkVideoData" value="{{html datasetId}},{{html msfrtnId}}" />
			</span>
		</td>
		<td><a href="#none" onclick="">{{html dataCdA}}</a></td>
		<td><a href="#none" onclick="">{{html dataCdB}}</a></td>
		<td><a href="#none" onclick="">{{html dataCdC}}</a></td>
		<td><a href="#none" onclick="">{{html fileNm}}</a></td>
		<td><a href="#none" onclick="">{{html uploadDt}}</a></td>
		<td>
			{{if fileTy == 'tif'}} <button type="button" title="미리보기" onclick="fnDisasterPicture('{{html datasetId}}', '{{html msfrtnId}}')" >미리보기</button>{{/if}}
			<!--<button type="button" class="fn btnDisasterMetaInfo" title="정보조회" >정보조회</button>-->
		</td>
	</tr>
</script>