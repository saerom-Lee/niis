<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String rgsvUrl = "http://192.168.0.102:1080";
	request.setAttribute("rgsvUrl", rgsvUrl);
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
<meta name=author content="ese, 이에스이">
<link rel=stylesheet type=text/css href=${rgsvUrl}/rgsv/viewer/rg/css/materialdesignicons.css>
<link rel=stylesheet type=text/css href=${rgsvUrl}/rgsv/viewer/rg/css/its-icons.css>
<link rel=stylesheet type=text/css href=${rgsvUrl}/rgsv/viewer/iLt/Tmplt/eBsc/cPBlue/skin.css>
<!-- <meta name=viewport content="width=device-width,initial-scale=1"> -->
<title>공간정보 행정망 서비스</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/fancytree/skin-lion/ui.fancytree.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/map/style.css" />

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.mCustomScrollbar.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/default.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.fancytree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.tmpl.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/OpenLayers.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/lib/proj4js-combined.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/lib/ol5.3.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/commonUtil.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/timeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/popup.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/Map.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/Marker.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/search/search.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/search/search_ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/imgDown/imgDown.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/innorix/common/innorix.config.js"></script>

<link href=${rgsvUrl}/rgsv/viewer/js/locale-en.js rel=prefetch>
<link href=${rgsvUrl}/rgsv/viewer/js/locale-ko.js rel=prefetch>
<link href=${rgsvUrl}/rgsv/viewer/css/chunk-common.css rel=preload as=style>
<link href=${rgsvUrl}/rgsv/viewer/css/rino.css rel=preload as=style>
<link href=${rgsvUrl}/rgsv/viewer/js/chunk-common.js rel=modulepreload as=script>
<link href=${rgsvUrl}/rgsv/viewer/js/chunk-vendors.js rel=modulepreload as=script>
<link href=${rgsvUrl}/rgsv/viewer/js/rino.js rel=modulepreload as=script>
<link href=${rgsvUrl}/rgsv/viewer/css/chunk-common.css rel=stylesheet>
<link href=${rgsvUrl}/rgsv/viewer/css/rino.css rel=stylesheet>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery-ui-themes-1.13.2/jquery-ui.min.css" />

<style>
	div#viewLoading {
		text-align: center;
		vertical-align: middle;
		padding-top: 70px;
		background: #FFFFF0;
		filter: alpha(opacity='60');
		opacity: 0.6;
	}
	/* 수치지형도 tree 리스트 */
	dl#tree {
		display: inline-block;
		border-radius: 3px;
	    background: #e1e1e1;
	    color: #535353;
	    padding: 1px 3px 0 3px;
    }
    /* 주점 marker Icon */
    img.markerIcon {
	 	margin-left: auto;
		margin-right: auto;
		display: block;
	}
</style>
</head>

<body style="margin: 0px;">
	<!-- #wrap -->
	<div id="wrap">

		<!-- 로딩 이미지 -->
		<div id="viewLoading" style="display:none;">
			<img src="/niis/images/progressbar1.png" alt="로딩중입니다. 잠시만 기다려 주세요." title="로딩중입니다. 잠시만 기다려 주세요." /><!-- width="50" height="50"/> -->
		</div>
	
		<!-- #header -->
		<div id="header">
			<h1>
			<a href="#main" id="btnMain2" class="pageMove"><img src="/niis/images/common/ci.png" style="margin-bottom:20px; margin-top:-10px;" alt="국토지리정보원" title="국토지리정보원" /></a><br/>
			<a href="#main" id="btnMain" class="pageMove"><img src="/niis/images/common/h1_logo.png" alt="공간정보 행정망 서비스" title="공간정보 행정망 서비스" /></a>
			</h1>
			<a href="#none" id="serviceGuide_1" class="btnServiceGuide"><img src="/niis/images/common/btn_service_guide.png" alt="?" /></a>
			<div class="utilMenu">
				<div class="info">
					<p><strong>${aUserName }</strong>님, 안녕하세요</p>
					<ul>
						<li>
							<!-- #myInfoModify -->
							<a href="#none" class="btnLayerOpen" onclick="myInfoDetail();">
								<img src="/niis/images/common/btn_h_util_1.gif" alt="정보수정" title="정보수정" />
							</a>
						</li>
						<li>
							<!-- #sistemClose -->
							<a href="#logoutCheckPop" class="btnLogout btnLayerOpen" onclick="logoutCheckStart();">
								<img src="/niis/images/common/btn_h_util_2.gif" alt="로그아웃" title="로그아웃" />
							</a>
						</li>
					</ul>
				</div>
				
				<div id="mainApply" class="iden">
					<ul>
						<li><a href="#mainDiv" class="pageMove" menuId="/apply/waitApply.do">대기<span style="color:#87ade5">0</span></a></li>
						<li><a href="#mainDiv" class="pageMove" menuId="/apply/appApply.do">승인<span style="color:#b6d657">0</span></a></li>
						<li class="last"><a href="#mainDiv" class="pageMove" menuId="/apply/rejApply.do">반려<span style="color:#f29d6e">0</span></a></li>
					</ul>
				</div>
			</div>
			
			<!-- .gnb 현재 페이지 활성화시 <li class="active"> 넣어주세요. -->
			<ul class="gnb">
				<li class="menu_1 active">
					<a href="#main" id="btnCombineSearch" class="pageMove">원본DB 검색</a>
				</li>
				<li class="menu_2">
					<a href="#none">원본DB 목록</a>
					<ul>
						<!-- #pageAerialPhoto -->
						<li><a href="#mainDiv" class="pageMove" menuId="/airListSearch/listSearch.do">- 항공사진</a></li>
						<!-- <li><a href="#mainDiv" class="pageMove" menuId="/airLibListSearch/listSearch.do">- 항공사진(해방전후)</a></li> -->
						<!-- #pageDigitalElevation -->
						<li><a href="#mainDiv" class="pageMove" menuId="/demListSearch/listSearch.do">- 수치표고</a></li>
						<!-- #pageOrthoPhoto -->
						<li><a href="#mainDiv" class="pageMove" menuId="/ortListSearch/listSearch.do">- 정사영상</a></li>
						<!-- #pageAtPhoto -->
						<li><a href="#mainDiv" class="pageMove" menuId="/atListSearch/listSearch.do">- AT성과</a></li>
						<!-- #pageMapPhoto -->
<!-- 						<li><a href="#mainDiv" class="pageMove" menuId="/mapListSearch/listSearch.do">- 수치지형도</a></li> -->
						<li><a href="#mainDiv" class="pageMove" menuId="/originList/digitListSearch.do">- 수치지형도</a></li>
					</ul>
				</li>
				<%-- <c:if test="${aUserAuth == '00'}">
				<li class="menu_3">
					<a href="#mainDiv" class="pageMove" menuId="/smartMap/listSearch.do" id="smart">스마트 전자지도 <br>관리시스템 연계</a>
				</li>
				</c:if> --%>
				<li class="menu_4">
					<a href="#mainDiv" class="pageMove" menuId="/disaster/listSearch.do" id="">긴급 공간정보</a>
				</li>
<!-- 			</ul> -->
			<!-- /.gnb -->
<!-- 		</div> -->
<!-- 		/#header -->


		<!-- #footer -->
		<!--  원본db목록 메뉴 클릭시 footer 메뉴와 겹치는 문제로 인하여 header 메뉴로 편입 -->
<!-- 		<div id="footer"> -->
<!-- 			<h3><a href="#notice" class="btnPageUp" onclick="ajaxCall('/disaster/disaster.do?boardCate=101', '', 'notice');">긴급공간정보</a></h3> -->
			<h3 style="font-size: 18px; padding: 10px 0;"><a href="#serviceGuide" class="btnPageUp">이용안내</a></h3>
			<h3 style="font-size: 18px; padding: 10px 0;"><a href="#notice" class="btnPageUp" onclick="ajaxCall('/board/board.do?boardCate=101', '', 'notice');">공지사항</a></h3>
			<ul id="mainNotice">
			</ul>
			<p class="copyright">copyright(c) 2012 NGII All Rights Reserved.</p>
			</ul>
		</div>
		<!-- /#footer -->

		<hr />

		<!-- #pageWrap -->
		<div id="pageWrap">
			<!-- 원본DB 검색 -->
			<div id="main" class="active">
				<!-- .twoDepthMenu -->
				<!-- <div class="twoDepthMenu" style="left:-98px;"> -->
				<div class="twoDepthMenu">
					<div class="btnServiceGuideWrap">
						<a href="#none" id="serviceGuide_2" class="btnServiceGuide">
							<img src="/niis/images/common/btn_service_guide.png" alt="도움말" title="도움말" />
						</a>
						<a href="#none" id="serviceGuide_3" class="btnServiceGuide">
							<img src="/niis/images/common/btn_service_guide.png" alt="도움말" title="도움말" />
						</a>
						<a href="#none" id="serviceGuide_4" class="btnServiceGuide">
							<img src="/niis/images/common/btn_service_guide.png" alt="도움말" title="도움말" />
						</a>
						<a href="#none" id="serviceGuide_5" class="btnServiceGuide">
							<img src="/niis/images/common/btn_service_guide.png" alt="도움말" title="도움말" />
						</a>
						<a href="#none" id="serviceGuide_6" class="btnServiceGuide">
							<img src="/niis/images/common/btn_service_guide.png" alt="도움말" title="도움말" />
						</a>
						<a href="#none" id="serviceGuide_10" class="btnServiceGuide">
							<img src="/niis/images/common/btn_service_guide.png" alt="도움말" title="도움말" />
						</a>
					</div>
					<div class="top">
						<!-- <h2 class="tit">원본DB 검색</h2> -->
						<h3 class="tit">
							<a href="#none" class="btnCloseOpen"></a>검색 구분
						</h3>
						<div class="selectTab parentWrap">
							<select id="searchTabSelect" class="select" style="width:90px;" onchange="setAreaTabType();">
								<option value="0">행정</option>
								<option value="1">명칭</option>
								<option value="2">지번</option>
								<option value="3">도로명</option>
								<option value="4">도엽</option>
								<option value="5">사용자영역</option>
							</select>
							<div class="tab">
								<!-- 행정 -->
								<div>
									<!-- .boardStyle_1 -->
									<div class="boardStyle_1">
										<table>
											<colgroup>
												<col width="85px" />
												<col width="*" />
											</colgroup>
											<tbody>
												<tr>
													<th>시도</th>
													<td>
														<select id="areaSido" class="select">
															<option value="00">선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>시군구</th>
													<td>
														<select id="areaSigungu" class="select">
															<option value="00">선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>읍면동</th>
													<td>
														<select id="areaDong" class="select">
															<option value="00">선택</option>
														</select>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- /.boardStyle_1 -->
									<a href="#none" class="searchDivBtn" onclick="moveToArea();">이동</a>
								</div>
								<!-- /행정 -->
								<!-- 명칭 -->
								<div>
									<div class="search">
										<input type="text" id="poiKeyword" placeholder="명칭을 입력해주세요" style="width:254px" />
										<a href="#none" id="poiCodeBtn"><img src="/niis/images/board/ic_search.png" alt="검색" title="검색" /> 검색</a>
									</div>
									<div style="text-align:right;">
										<strong>검색결과 </strong>(<span id="poiCnt">0</span>건)
									</div>
									<ul id="poiList" class="topAppList mCustomScrollbar">
									</ul>
									<dl class="territoryBuffer">
										<dt>영역버퍼</dt>
										<dd>
											<div id="areaBuffer" class="areaBuffer"></div>
											<p>
												<input type="text" id="amount" readonly="readonly" /><label for="amount">m</label>
											</p>
										</dd>
									</dl>
								</div>
								<!-- /명칭 -->
								<!-- 지번 -->
								<div>
									<!-- .boardStyle_1 -->
									<div class="boardStyle_1">
										<table>
											<colgroup>
												<col width="85px" />
												<col width="*" />
											</colgroup>
											<tbody>
												<tr>
													<th>시도</th>
													<td>
														<select id="zipSido" class="select">
															<option>선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>시군구</th>
													<td>
														<select id="zipSigungu" class="select">
															<option>선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>읍면동</th>
													<td>
														<select id="zipDong" class="select">
															<option>선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>리</th>
													<td>
														<select id="zipLi" class="select">
															<option>선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>지번</th>
													<td>
														<span class="checkbox"><input type="checkbox" id="mountYn" /></span>
														<label class="labelTxt" style="position:relative; top:9px; margin-right:13px">산</label>
														<input type="text" id="lnbrMnnm" style="width:88px;" /> - <input type="text" id="lnbrSlno" style="width:88px;" />
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- /.boardStyle_1 -->
									<!-- <a href="#none" id="zipCodeBtn" class="searchDivBtn" onclick="getZipCodeList();">검색</a> -->
									<a href="#none" id="zipCodeBtn" class="searchDivBtn" onclick="getJibunList();">검색</a>
									<div class="mgt10" style="text-align:right;">
										<strong>검색결과 </strong>(<span id="zipCodeCnt">0</span>건)
									</div>
									<ul id="zipCodeList" class="topAppList mCustomScrollbar mgt10">
									</ul>
									<dl class="territoryBuffer">
										<dt>영역버퍼</dt>
										<dd>
											<div id="areaBuffer2" class="areaBuffer"></div>
											<p>
												<input type="text" id="amount2" readonly="readonly" /><label for="amount2">m</label>
											</p>
										</dd>
									</dl>
								</div>
								<!-- /지번 -->
								<!-- 도로명 -->
								<div>
									<!-- .boardStyle_1 -->
									<div class="boardStyle_1">
										<table>
											<colgroup>
												<col width="85px" />
												<col width="*" />
											</colgroup>
											<tbody>
												<tr>
													<th>시도</th>
													<td>
														<select id="jusoSido" class="select">
															<option value="00">선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>시군구</th>
													<td>
														<select id="jusoSigungu" class="select">
															<option value="00">선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>읍면</th>
													<td>
														<select id="jusoEmd" class="select">
															<option value="00">선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>도로명</th>
													<td>
														<select id="jusoRoad" class="select">
															<option value="00">선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>건물번호</th>
													<td><input type="text" id="buldMnnm" style="width:88px;" /> - <input type="text" id="buldSlno" style="width:88px;" /></td>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- /.boardStyle_1 -->
									<a href="#none" id="buldCodeBtn" class="searchDivBtn" onclick="getJusoList();">검색</a>
									<div class="mgt10" style="text-align:right;">
										<strong>검색결과 </strong>(<span id="buldCodeCnt">0</span>건)
									</div>
									<ul id="buldCodeList" class="topAppList mCustomScrollbar mgt10">
									</ul>
									<dl class="territoryBuffer">
										<dt>영역버퍼</dt>
										<dd>
											<div id="areaBuffer3" class="areaBuffer"></div>
											<p>
												<input type="text" id="amount3" readonly="readonly" /><label for="amount3">m</label>
											</p>
										</dd>
									</dl>
								</div>
								<!-- /도로명 -->
								<!-- 도엽 -->
								<div>
									<!-- .boardStyle_1 -->
									<div class="boardStyle_1">
										<table>
											<colgroup>
												<col width="85px" />
												<col width="*" />
											</colgroup>
											<tbody>
												<tr>
													<th>축적</th>
													<td>
														<select id="indexMapScale" class="select">
															<option value="0">선택</option>
															<option value="50000" >1:50000</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>도엽명</th>
													<td>
														<select id="indexMapInitName" class="select" style="width:30%">
															<option>선택</option>
														</select>
														<select id="indexMapName" class="select" style="width:70%">
															<option>선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>도엽번호</th>
													<td>
														<select id="indexMapNum" class="select">
															<option>선택</option>
														</select>
													</td>
												</tr>
												<tr>
													<th>도엽번호<br />(직접 검색)</th>
													<td>
														<input type="text" id="indexMapNum2" value="" />
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- /.boardStyle_1 -->
									<a href="#none" class="searchDivBtn" onclick="getIndexMap();">이동</a>
								</div>
								<!-- /도엽 -->
								<!-- 사용자영역 -->
								<div>
									<!-- .boardStyle_1 -->
									<div class="boardStyle_1" style="display:flex; justify-content:center;">
										<ul id="searchCustom" class="sortToggle">
											<li>원</li>
											<li>사각형</li>
											<li>다각형</li>
											<li class="last" style="background-color: #dfdfdf;">지우기</li>
										</ul>
										
										<input type="hidden" id="search_custom_wkt" />
										<input type="hidden" id="search_custom_type" />
									</div>
									<!-- /.boardStyle_1 -->
								</div>
								<!-- /사용자영역 -->
							</div>
						</div>
						<h3 class="tit"><a href="#none" class="btnCloseOpen"></a>성과 구분</h3>
						<!-- 영상 구분 -->
						<div class="parent parentWrap">
							<!-- .boardStyle_1 -->
							<div class="boardStyle_1">
								<table>
									<colgroup>
										<col width="85px" />
										<col width="*" />
									</colgroup>
									<tbody>
										<tr>
											<th>성과종류</th>
											<td>
												<ul id="imgView" class="sortToggle">
													<li class="active">항공사진</li>
													<li class="">정사영상</li>
													<li class="last">수치표고</li>
													<li class="">AT</li>
													<li class="">수치지형도</li>
												</ul>
											</td>
										</tr>
										<tr>
											<th>검색년도</th>
											<td>
												<select id="sSearchYear" class="select" onchange="onChangeSrchYear('s');" style="width:113px;">
												</select>
												<span style="position:relative; top:8px; padding:0 5px">~</span>
												<select id="eSearchYear" class="select" onchange="onChangeSrchYear('e');" style="width:114px;">
												</select>
											</td>
										</tr>
										<tr>
											<th>사업지구</th>
											<td>
												<select id="zoneList" class="select">
													<option value="">전체</option>
												</select>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- /.boardStyle_1 -->
							<a href="#none" class="searchDivBtn btnParentHide" onclick="searchList();">검색</a>
						</div>
						<!-- /영상 구분 -->
						<h3 class="tit">검색 결과</h3>
					</div>
					<div class="scroll mCustomScrollbar">
						<div id="combineTreeMenu" style="padding:5px 20px;">
							<ul>
							</ul>
						</div>
					</div>
					<div class="bottom">
						<!-- #totalApplicationAlert -->
						<a href="#none" onclick="applicationList(1,'checkedList');">
							<img src="/niis/images/sub/btn_application_form.gif" alt="신청서 작성" title="신청서 작성" />
						</a>
					</div>
				</div>
				<!-- /.twoDepthMenu -->
	
				<!-- .mapArea -->
				<div class="container mapArea">
					
					<!-- .mapMenuToggle -->
					<div id="mapAreaBtn" class="btnTwoDepthMenuClose active" style="display:block;position:absolute;z-index:1900;"></div>
					<div class="mapTit" id="curPosition" style="position:absolute;z-index:1800;"><h3><img src="/niis/images/sub/ic_map_tit.png" width="14" height="20" alt="" /></h3></div>
					<!-- /.mapMenuToggle -->
					
					<!-- Map Contents -->
					<div style="position: absolute; left: 280px; width: calc(100vw - 280px); height: calc(100vh); background: #ccc;">
						<div id="rino-map" data-rino-map="1000" data-rino-gis-server="${rgsvUrl}"></div>
						<script>
							window.addEventListener('rino-map-mounted', function(e) {
								//지도 중심 위치
								eventListenerCallback();
								rinoGIS.ol.map.on("moveend", function(e) {
									eventListenerCallback();
								});
								
								completeDrawArea();	//사용자영역 검색
							});
						</script>
					</div>
					
					<script src=${rgsvUrl}/rgsv/viewer/js/template.min.js></script>
					<script type=module	src=${rgsvUrl}/rgsv/viewer/js/chunk-vendors.js></script>
					<script type=module	src=${rgsvUrl}/rgsv/viewer/js/chunk-common.js></script>
					<script type=module	src=${rgsvUrl}/rgsv/viewer/js/rino.js></script>
					<script>
						!function() {
							var e = document, t = e.createElement("script");
							if (!("noModule" in t) && "onbeforeload" in t) {
								var n = !1;
								e.addEventListener("beforeload", function(e) {
									if (e.target === t)
										n = !0;
									else if (!e.target.hasAttribute("nomodule") || !n)
										return;
									e.preventDefault()
								}, !0), t.type = "module", t.src = ".", e.head.appendChild(t),
										t.remove()
							}
						}();
					</script>
					<script	src=${rgsvUrl}/rgsv/viewer/js/chunk-vendors-legacy.js nomodule></script>
					<script	src=${rgsvUrl}/rgsv/viewer/js/chunk-common-legacy.js	nomodule></script>
					<script src=${rgsvUrl}/rgsv/viewer/js/rino-legacy.js	nomodule></script>
					<!-- //Map Contents -->

				</div>
				<!-- /.mapArea -->
				
			</div>
			<!-- /원본DB 검색 -->
	
			<hr />
	
			<!-- 메인레이어 -->
			<div id="mainDiv" style="display:none;">
			</div>
			<!-- /메인레이어 -->
			
		</div>
		<!-- /#pageWrap -->
		
		<!-- #pageUp -->
		<div id="pageUp">
			<!-- 이용안내 -->
			<div id="serviceGuide">
				<%@ include file="/WEB-INF/jsp/niis/guide/useGuide.jsp" %>
			</div>
			<!-- /이용안내 -->
			
			<hr />

			<!-- 공지사항 -->
			<div id="notice">
			</div>
		</div>
		
		<div class="ServiceGuideWrap" id="serviceGuidePop" style=""></div>
	</div>
	<!-- /#wrap -->

	<hr />
	<!-- 초기에 마스크 보일시에는 class="maskShow" 주시면 됩니다. -->
	<div id="mask"></div>
	
	<!-- 공통 팝업 레이어 -->
	<div id="commonPop" class="layer" style="display:none">
		<div class="tit">
			<h3>알림</h3>
			<a href="#none" class="btnLayerClose">
				<img src="/niis/images/popup/btn_layer_close.png" alt="취소" title="취소" />
			</a>
		</div>
		<div class="con">
			<p class="txt">
				다른 메뉴로 이동시 신청하지 않은 목록이 삭제됩니다.
				<span class="block">이동하시겠습니까?</span>
			</p>
			<div class="btnArea">
				<a href="#none" class="active">확인</a>
				<a href="#none" class="btnLayerClose">취소</a>
			</div>
		</div>
	</div>
	
	<!-- 시스템 종료 -->
	<div id="logoutCheckPop" class="layer" style="display:none">
		<div class="tit">
			<h3>시스템 종료</h3>
			<a href="#none" class="btnLayerClose" onclick="logoutCheckEnd();">
				<img src="/niis/images/popup/btn_layer_close.png" alt="취소" title="취소" />
			</a>
		</div>
		<div class="con">
			<p class="txt">
				정상적으로 로그아웃 되었습니다.<span class="block">이용해 주셔서 감사합니다.</span>
			</p>
			<p class="count"><span id="remainSec">5</span>초 뒤 종료</p>
			<div class="btnArea">
				<a href="#none" class="active" onclick="logout();">확인</a>
			</div>
		</div>
	</div>
	<!-- /시스템 종료 -->
	
	<!-- 세션 만료 -->
	<div id="extendLoginPop" class="layer" style="display:none">
		<div class="tit">
			<h3>시스템 종료 알림</h3>
			<a href="#none" class="btnLayerClose" onclick="extendSession();">
				<img src="/niis/images/popup/btn_layer_close.png" alt="연장" title="연장" />
			</a>
		</div>
		<div class="con">
			<p class="txt">
				로그인 허용시간이 곧 만료됩니다.<span class="block">연장을 원하실 경우 연장 버튼을 눌러 주세요.</span>
			</p>
			<p class="count"><span id="remainSec">5</span>초 뒤 종료</p>
			<div class="btnArea">
				<a href="#none" class="btnLayerClose" onclick="extendSession();">연장</a>
				<a href="#none" class="active" onclick="logout();">종료</a>
			</div>
		</div>
	</div>
	<!-- /시스템 종료 -->
	
	<div id="downloadPop" class="layer" style="display:none">
	</div>
	
	<div id="mapMetaDownloadPop" class="layer" style="display:none">
	</div>
	
	<form id="popupFrom" name="popupFrom">
		<input type="hidden" id="boardCate" name="boardCate" />
		<input type="hidden" id="boardSeq" name="boardSeq" />
		<input type="hidden" id="popGbnCde" name="popGbnCde" />
		<input type="hidden" id="title" name="title" />
		<input type="hidden" id="contents" name="contents" />
		<input type="hidden" id="imgUrl" name="imgUrl" />
	</form>
	
<c:if test="${aHelpYn == 'Y' }">
<script type="text/javascript">
	$(document).ready(function(){
		initAcsGuidePop();
	});
	
	function initAcsGuidePop(){
		<%--
		$("body").append('<div id="serviceGuideLayer" class="layer" style="width:1100px;display:none;"></div>');
		ajaxCallPop("/guide/initGuidePop.do", "", "serviceGuideLayer");
		--%>
		var top = (screen.availHeight-430)/2;
		var left = (screen.availWidth-660)/2;
		var option = "left=" + left + ",top=" + top + ",width=1100px,height=492px,location=no,menubar=no,toolbar=no,scrollbars=no,status=no";
		window.open("/niis/guide/initGuidePop.do", "userGuide", option);
	}
</script>
</c:if>
</body>
</html>