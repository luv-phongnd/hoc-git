<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Đăng nhập</title>
</head>
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
		<form class="form-horizontal" action="${pageContext.request.contextPath}/login.do" method="post">
			<div class="form-group">
				<label class="control-label col-sm-3">Tên tài khoản:</label>
				<div class="col-sm-3">
					<input type="text" class="form-control"
						placeholder="Nhập tên tài khoản" name="username" value="<c:out value="${userName}"></c:out>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="pwd">Mật khẩu:</label>
				<div class="col-sm-3">
					<input type="password" class="form-control" id="pwd"
						placeholder="Enter password" name="password" value="">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">				
					<button type="submit" class="btn btn-primary">Đăng nhập</button>
					<button type="button" class="btn btn-danger" onclick="location.href='${pageContext.request.contextPath}/danhSachTinhThanh.do'">Hủy bỏ</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>