<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<c:choose>
	<c:when test="${tblBaiViet.idBaiViet != 0}">
		<c:set var="updateOrAddUserOk" value="updateBaiViet" />
	</c:when>
	<c:otherwise>
		<c:set var="updateOrAddUserOk" value="addBaiViet"></c:set>
	</c:otherwise>
</c:choose>
<body>
	<div class="container">
		<!-- Vùng header -->
		<jsp:include page="header.jsp"></jsp:include>
		
		<c:if test="${not empty listError}">
              <c:forEach items="${listError}" var="error">
                    <div class="alert alert-danger">
    					<strong>Lỗi!</strong>${error}
    				</div>
              </c:forEach>
        </c:if>
		<form class="form-horizontal" action="${pageContext.request.contextPath}/dangBaiViet.do?functionType=${updateOrAddUserOk}" method="post" enctype="multipart/form-data">
			<input type="hidden" name="screenType" value="${updateOrAddUserOk}">
			<div class="form-group">
				<label class="control-label col-sm-3">Tiêu đề:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control"
						placeholder="Nhập tiêu đề" name="tieuDe" value="<c:out value="${tblBaiViet.tieuDe}"></c:out>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pwd">Mô tả:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control"
						placeholder="Nhập mô tả" name="moTa" value="<c:out value="${tblBaiViet.moTa}"></c:out>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pwd">Tỉnh thành:</label>
				<div class="col-sm-5">
					<select name="idTinhThanh" class="form-control"> 
                       	<option value="0">Chọn tỉnh thành</option>
                        <c:if test="${not empty listTinhThanh}">
                           <c:forEach items="${listTinhThanh}" var="tinhThanh">
                              <c:choose>
                                 <c:when test="${tblBaiViet.idTinhThanh == tinhThanh.idTinhThanh}">
                                     <option value="${tinhThanh.idTinhThanh}" selected="selected">${tinhThanh.tenTinhThanh}</option>
                                 </c:when>
                                 <c:otherwise>
                                     <option value="${tinhThanh.idTinhThanh}">${tinhThanh.tenTinhThanh}</option>
                                 </c:otherwise>
                              </c:choose>
                           </c:forEach>
                       </c:if>
                    </select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pwd">Huyện/Xã:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control"
						placeholder="Nhập Huyện xã" name="huyenXa" value="<c:out value="${tblBaiViet.huyenXa}"></c:out>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pwd">Tên địa điểm:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control"
						placeholder="Nhập tên địa điểm" name="tenDiaDiem" value="<c:out value="${tblBaiViet.tenDiaDiem}"></c:out>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pwd">Hình ảnh:</label>
				<div class="col-sm-5">
					<input type="file" class="form-control" name="hinhAnh" id="i_file" multiple="multiple">
					<img src="" width="200" style="display:none;" />
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-3" for="pwd">Nội dung:</label>
				<div class="col-sm-5">
					<textarea class="form-control" rows="5" name="noiDung" placeholder="Nhập nội dung"><c:out value="${tblBaiViet.noiDung}"></c:out></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-10">				
					<button type="submit" class="btn btn-primary">Chia sẻ</button>
					<button type="button" class="btn btn-danger" onclick="location.href='${pageContext.request.contextPath}/danhSachTinhThanh.do'">Hủy bỏ</button>
				</div>
			</div>
		</form>
	</div>
	
	
	<div id="title"></div>
	<script>
	$('#i_file').change( function(event) {
    var tmppath = URL.createObjectURL(event.target.files[0]);
    $("img").fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));
    
    var filePath = $('#i_file').val();
    alert(filePath);
    
    
	});
	
	$('#i_file').change(function() {
		  //$('#title').val(this.value ? this.value.match(/([\w-_]+)(?=\.)/)[0] : '');
		  $('#title').val(this.files && this.files.length ? this.files[0].name.split('.')[0] : '');

		})
</script>  

</body>
</html>