<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Xem chi tiết bài viết</title>

</head>
<body>

	<div class="container">
		<!-- Vùng header -->
		<jsp:include page="header.jsp"></jsp:include>
		<p style="font-size: 35px"><label>${tblBaiViet.tieuDe}</label></p>
		<div class="row">
			<div class="col-md-4">
				<div class="thumbnail">
					<img
						src="${pageContext.request.contextPath}/imageBaiViet.do?idBaiViet=${tblBaiViet.idBaiViet}" alt="Lights" style="width: 100%; height : 300px">
						<div class="caption">
							<p><label>${tblBaiViet.tenDiaDiem}</label></p>
						</div>
				</div>
			</div>
			<p>${tblBaiViet.noiDung}</p>
	    </div>
	    
	    <c:if test="${not empty listNhanXet }">
	    	<c:forEach items="${listNhanXet}" var="nhanXet">
	    		<div class="row">
	    			<div class="alert alert-info">
  						<strong>${nhanXet.biDanh}</strong> ${nhanXet.nhanXet}
					</div>
	    		</div>
	     	</c:forEach>
	    </c:if>
	    <div class="row">
	    	<form action="${pageContext.request.contextPath}/xemChiTietBaiViet.do" class="form-horizontal" method="post">
	    	<input type="hidden" name="idBaiViet" value="${idBaiViet}">
	    		<div class="form-group">
					<div class="col-sm-offset-0 col-sm-10">				
						<input type="text" class="form-control"
						placeholder="Nhập mô tả" name="nhanXet" >
						<button  type="submit" class="btn btn-primary">Post Comment</button>
					</div>
				</div>
	    	</form>
	    </div>
	</div>
</body>
</html>