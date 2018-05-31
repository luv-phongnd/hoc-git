<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Trang chủ</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	

	<div class="container" style="margin-top: 20px">
		<!-- Vùng header -->
		<jsp:include page="header.jsp"></jsp:include>
		<div class="row">
			<c:choose>
				<c:when test="${not empty listTinhThanh}">
					<c:forEach items="${listTinhThanh}" var="tinhThanh">
						<div class="col-md-4">
							<div class="thumbnail">
								<a
									href="${pageContext.request.contextPath}/xemDanhSachBaiVietCuaTinhThanh.do?idTinhThanh=${tinhThanh.idTinhThanh}"
									target="_blank"> <img
									src="${pageContext.request.contextPath}/imageTinhThanh.do?idTinhThanh=${tinhThanh.idTinhThanh}"
									alt="Lights" style="width: 100%; height: 200px">
									<div class="caption">
										<p>${tinhThanh.tenTinhThanh}</p>
									</div>
								</a>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<label style="color: red">${messageEmptyListCity}</label>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="lbl_paging" align="center">
			<c:if test="${listPaging[0] -2 > 0}">
				<a
					href="${pageContext.request.contextPath}/danhSachTinhThanh.do?action=paging&page=${listPaging[0]-3}"
					style="color: blue">&lt;&lt;</a>
			</c:if>
			<c:forEach items="${listPaging}" var="page">
				<c:choose>
					<c:when test="${currentPage == page}">
						<c:set var="clickPaging" value="false"></c:set>
						<c:set var="colorLinkListPaging" value="black"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="clickPaging" value="true"></c:set>
						<c:set var="colorLinkListPaging" value="blue"></c:set>
					</c:otherwise>
				</c:choose>
				<a
					href="${pageContext.request.contextPath}/danhSachTinhThanh.do?action=paging&page=${page}"
					onclick="return ${clickPaging}"
					style="color: ${colorLinkListPaging};">${page}</a>

			</c:forEach>
			<c:if
				test="${listPaging[0] + 3 <= totalPage && currentPage < totalPage}">
				<a
					href="${pageContext.request.contextPath}/danhSachTinhThanh.do?action=paging&page=${listPaging[0]+3}"
					style="color: blue"> &gt;&gt; </a>
			</c:if>

		</div>
	</div>

</body>
</html>