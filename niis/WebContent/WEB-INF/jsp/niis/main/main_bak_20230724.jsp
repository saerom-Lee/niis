<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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

<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common/commonUtil.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/timeout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/main/popup.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/CustomNavToolbar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/IndexMap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/Map.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/Marker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/Measure.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/resultBounds.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/map/Vector.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/search/search.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/search/search_ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/imgDown/imgDown.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/innorix/common/innorix.config.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/test.js"></script>

<style>
	div#viewLoading {
		text-align: center;
		vertical-align: middle;
		padding-top: 70px;
		background: #FFFFF0;
		filter: alpha(opacity='60');
		opacity: 0.6;
	}
</style>
</head>

<body onload="init();">
	<!-- #wrap -->
	<div id="wrap">
	
		<!-- 로딩 이미지 -->
		<div id="viewLoading" style="display:none;">
			<img src="/niis/images/progressbar1.png" alt="로딩중입니다. 잠시만 기다려 주세요." titile="로딩중입니다. 잠시만 기다려 주세요." /><!-- width="50" height="50"/> -->
		</div>
	
		<!-- #header -->
		<div id="header">
			<h1><a href="#main" id="btnMain" class="pageMove"><img src="/niis/images/common/h1_logo.png" alt="공간정보 행정망 서비스" title="공간정보 행정망 서비스" /></a></h1>
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
						<!-- #pageDigitalElevation -->
						<li><a href="#mainDiv" class="pageMove" menuId="/demListSearch/listSearch.do">- 수치표고</a></li>
						<!-- #pageOrthoPhoto -->
						<li><a href="#mainDiv" class="pageMove" menuId="/ortListSearch/listSearch.do">- 정사영상</a></li>
					</ul>
				</li>
			</ul>
			<!-- /.gnb -->
		</div>
		<!-- /#header -->

		<!-- #footer -->
		<div id="footer">
			<h3><a href="#serviceGuide" class="btnPageUp">이용안내</a></h3>
			<h3><a href="#notice" class="btnPageUp" onclick="ajaxCall('/board/board.do?boardCate=101', '', 'notice');">공지사항</a></h3>
			<ul id="mainNotice">
			</ul>
			<p class="copyright">copyright(c) 2012 NGII All Rights Reserved.</p>
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
								<option value="3">새주소</option>
								<option value="4">도엽</option>
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
									<a href="#none" id="zipCodeBtn" class="searchDivBtn" onclick="getZipCodeList();">검색</a>
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
								<!-- 새주소 -->
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
													<th>도로명</th>
													<td>
														<select id="juso_nm" class="select">
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
									<a href="#none" id="buldCodeBtn" class="searchDivBtn" onclick="getBludCodeList();">검색</a>
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
								<!-- /새주소 -->
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
														<select id="indexMapName" class="select">
															<option>선택</option>
														</select>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- /.boardStyle_1 -->
									<a href="#none" class="searchDivBtn" onclick="moveToIndexMap();">이동</a>
								</div>
								<!-- /도엽 -->
							</div>
						</div>
						<h3 class="tit"><a href="#none" class="btnCloseOpen"></a>영상 구분</h3>
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
											<th>영상종류</th>
											<td>
												<ul id="imgView" class="sortToggle">
													<li class="active">항공사진</li>
													<li class="">정사영상</li>
													<li class="last">수치표고</li>
												</ul>
											</td>
										</tr>
										<tr>
											<th>촬영년도</th>
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
				<!-- <div class="container mapArea" style="padding-Left:280px;"> -->
				<div class="container mapArea">
					<!-- .mapTit -->
					<div class="mapTit">
						<div id="mapAreaBtn" class="btnTwoDepthMenuClose active"></div>
						<h3><img src="/niis/images/sub/ic_map_tit.png" width="14" height="20" alt="" /> 제주특별자치도 서귀포시 효돈동</h3>
						<ul>
							<li class="over">
								<a href="#none" onclick="mapControl('zoomIn');">
									<img src="/niis/images/sub/ic_map_tit_1.gif" alt="확대" title="확대"/>
								</a>
							</li>
							<li class="over">
								<a href="#none" onclick="mapControl('zoomOut');">
									<img src="/niis/images/sub/ic_map_tit_2.gif" alt="축소" title="축소"/>
								</a>
							</li>
							<li class="over">
								<a href="#none" onclick="showMinMap();">
									<img src="/niis/images/sub/ic_map_tit_4.gif" alt="전체지도보기" title="전체지도보기"/>
								</a>
							</li>
							<li class="over">
								<a href="#none" onclick="mapControl('clearMap');">
									<img src="/niis/images/sub/ic_map_tit_13.gif" alt="초기화" title="초기화" />
								</a>
							</li>
							<li class="over">
								<a href="#none" onclick="mapControl('modePan');">
									<img src="/niis/images/sub/ic_map_tit_3.gif" alt="이동" title="이동"/>
								</a>
							</li>
							<!-- 
							<li class="toggle">
								<a href="#none">
									<img src="/niis/images/sub/ic_map_tit_5.gif" alt="" />
								</a>
							</li>
							 -->
							 <li class="siblingsToggle">
								<a href="#none">
									<img src="/niis/images/sub/ic_map_tit_10.gif" alt="거리재기" title="거리재기" />
								</a>
								<input type="hidden" id="toggleMapCon" value="measure_distance" />
							</li>
							<li class="siblingsToggle">
								<a href="#none">
									<img src="/niis/images/sub/ic_map_tit_11.gif" alt="면적재기" title="면적재기" />
								</a>
								<input type="hidden" id="toggleMapCon" value="measure_area" />
							</li>
							<li class="siblingsToggle">
								<a href="#none">
									<img src="/niis/images/sub/ic_map_tit_6.gif" alt="영역확대" title="영역확대" />
								</a>
								<input type="hidden" id="toggleMapCon" value="dragZoomIn" />
							</li>
							<li class="siblingsToggle">
								<a href="#none">
									<img src="/niis/images/sub/ic_map_tit_7.gif" alt="영역축소" title="영역축소" />
								</a>
								<input type="hidden" id="toggleMapCon" value="dragZoomOut" />
							</li>
							<li class="over">
								<a href="#none" onclick="changeMap();">
									<img src="/niis/images/sub/ic_map_tit_12.gif" alt="전환" title="전환" />
								</a>
							</li>
						</ul>
					</div>
					<!-- /.mapTit -->
					<!-- .map -->
					<div id="mapView" class="map">
					</div>
					<!-- /.map -->
					<script type="text/javascript">
						$(function(){
							mapHeight();
							$(window).resize(mapHeight);
							function mapHeight(){
								var wrapHeight = $('#wrap').height();
								$('.map').height(wrapHeight-50);
							}
							
						});
					</script>
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