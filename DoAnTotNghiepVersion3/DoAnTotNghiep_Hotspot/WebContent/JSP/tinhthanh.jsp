<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <title>Danh sách bài viết tỉnh thành</title>
</head>
<body>

	<div class="container">
		<!-- Vùng header -->
		<jsp:include page="header.jsp"></jsp:include>
		
		<div class="row">
					<div class="navbar-header">
                            <a class="navbar-brand">Danh sách bài viết tỉnh thành</a>
                    </div>
					<form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/xemDanhSachBaiVietCuaTinhThanh.do" method="get">
                        	<input type="hidden" name="idTinhThanh" value="${idTinhThanh}">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Tìm kiếm tên địa điểm" name="tenDiaDiem">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit">
                                        <i class="glyphicon glyphicon-search"></i>
                                    </button>
                                </div>
                            </div>
                    </form>
		</div>
		
		
			<c:choose>
               <c:when test="${not empty listBaiViet}">
                  <c:forEach items="${listBaiViet}" var="baiViet">
                  	<div class="row">
                  		<div class="col-md-4">
      						<div class="thumbnail" style="border-color: #0059b3;height:250px;">
          						<img src="${pageContext.request.contextPath}/imageBaiViet.do?idBaiViet=${baiViet.idBaiViet}" alt="Nature" style="width:100%; height:200px;">
          						<div class="caption">
            						<p><label>${baiViet.tenDiaDiem}</label></p>
          						</div>
      						</div>
   					   </div>
   					   <div class="col-md-6">
      						<div class="thumbnail" style="border-color: #0059b3; height:250px;">
          						<div class="caption">
            						<p><label>Tên người viết:${baiViet.biDanh }</label></p>
            						<p><label>Tiêu đề:${baiViet.tieuDe}</label></p>
            						<p><label>Mô tả:${baiViet.moTa}</label></p>
            						<p><label>Huyện/xã:${baiViet.huyenXa}</label></p>
            						<p><label>Số lượt thích:${baiViet.soLuotThich}</label></p>
            						
            						 <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/thichBaiViet.do?idBaiViet=${baiViet.idBaiViet}'" id="button1">Thích bài viết</button> 
                                     <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/xemChiTietBaiViet.do?idBaiViet=${baiViet.idBaiViet}'">Xem chi tiết</button>
                                     <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/luuBaiViet.do?idBaiViet=${baiViet.idBaiViet}'">Lưu địa điểm</button>
         					    </div>
      						</div>
   					   </div>
                  	</div>
                  		<c:forEach items="${listIdBaiVietCuaTaiKhoan}" var="idBaiViet">
            				<c:if test="${idBaiViet == baiViet.idBaiViet}">
            					<script>
            						$(document).ready(function(){
										$("#button1").remove();
									});
								</script>
            				</c:if>
            			</c:forEach> 
				  </c:forEach>
               </c:when>
               <c:otherwise>
                    <div class="row">
                         <p><label style="color: red">${message}</label></p>
                     </div>     	
               </c:otherwise>
            </c:choose>
	 </div>
</body>
</html>