<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
            <!DOCTYPE html>
            <html lang="en">

            <head>
               <meta charset="utf-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
                    <title>Bootstrap 101 Template</title>

                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  					<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 				    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 				    
 				    <style>
  						/* Note: Try to remove the following lines to see the effect of CSS positioning */
  						.affix {
      						top: 0;
      						width: 100%;
      						z-index: 9999 !important;
 						 }

 						 .affix + .container-fluid {
     						 padding-top: 5px;
  							}
 					 </style>
            </head>

            <body>

                <nav class="navbar navbar-inverse" >
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand">HotSpot</a>
                        </div>
                        
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="${pageContext.request.contextPath}/danhSachTinhThanh.do">Trang chủ</a>
                            </li>
		                    <c:if test="${not empty username }">
			                    <li class="dropdown">
			                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Bài viết
			                            <span class="caret"></span>
			                        </a>
			                        <ul class="dropdown-menu">
			                            <li>
			                                <a href="${pageContext.request.contextPath}/danhSachBaiDaViet.do">Bài đã viết</a>
			                            </li>
			                            <li>
			                                <a href="${pageContext.request.contextPath}/xemDanhSachBaiVietDaLuu.do">Bài đã lưu</a>
			                            </li>
			                            <li>
			                                <a href="${pageContext.request.contextPath}/dangBaiViet.do?screenType=trangchu">Viết bài</a>
			                            </li>
			                        </ul>
			                    </li>
			                    <li class="dropdown">
			                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Tài khoản
			                            <span class="caret"></span>
			                        </a>
			                        <ul class="dropdown-menu">
			                            <li>
			                                <a href="${pageContext.request.contextPath}/JSP/doimatkhau.jsp">Đổi mật khẩu</a>
			                            </li>
			                            <li>
			                                <a href="${pageContext.request.contextPath}/logout.do">Thoát</a>
			                            </li>
			                        </ul>
			                    </li>
               				 </c:if>
                            
                        </ul>
                        <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/danhSachTinhThanh.do" method="get">
                        	<input type="hidden" name="action" value="search">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Tìm kiếm tỉnh thành" name="searchValue" value="${searchValue}">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit">
                                        <i class="glyphicon glyphicon-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
						
                        <ul class="nav navbar-nav navbar-right">
                        	<c:choose>
                        		<c:when test="${empty username }">
                        			<li>
		                                <a href="${pageContext.request.contextPath}/JSP/dangnhap.jsp">
		                                    <span class="glyphicon glyphicon-log-in"></span>
		                                    Đăng nhập</a>
		                            </li>
		                            <li>
		                                <a href="${pageContext.request.contextPath}/JSP/dangky.jsp">
		                                    <span class="glyphicon glyphicon-user"></span>
		                                    Đăng ký</a>
		                            </li>
                             	</c:when>
                             	<c:otherwise>
                             		<li>
		                                <a>
		                                    <span class="glyphicon glyphicon-user"></span>
		                                     ${username}
		                                </a>
		                            </li>
                             	</c:otherwise>
                        	</c:choose>
                        </ul>
                       
                    </div>
                </nav>

            </body>

            </html>