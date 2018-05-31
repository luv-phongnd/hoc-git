
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

                    <!-- Bootstrap -->
                    <link href="${pageContext.request.contextPath}/CSS/bootstrap.min.css" rel="stylesheet">

                    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
                    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
                    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
                    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
                    <!-- Include all compiled plugins (below), or include individual files as needed -->
                    <script src="${pageContext.request.contextPath}/JAVASCRIPT/bootstrap.min.js"></script>
                </head>

                <body>

                    <nav class="navbar navbar-inverse">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <a class="navbar-brand" href="#">WebSiteName</a>
                            </div>
                            <ul class="nav navbar-nav">
                                <li class="active">
                                    <a href="#">Home</a>
                                </li>
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#">Page 1-1</a>
                                        </li>
                                        <li>
                                            <a href="#">Page 1-2</a>
                                        </li>
                                        <li>
                                            <a href="#">Page 1-3</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">Page 2</a>
                                </li>
                                <li>
                                    <a href="#">Page 3</a>
                                </li>
                            </ul>
                        </div>
                    </nav>

                    <h1>Hello, world!</h1>
                    <div class="container">
                        <h1>My First Bootstrap Page</h1>
                        <p>This part is inside a .container class.</p>
                        <p>The .container class provides a responsive fixed width container.
                        </p>
                    </div>

                    <div class="container">
                        <h2>Dropdowns</h2>
                        <p>The .dropdown class is used to indicate a dropdown menu.</p>
                        <p>Use the .dropdown-menu class to actually build the dropdown menu.
                        </p>
                        <p>To open the dropdown menu, use a button or a link with a class of .dropdown-toggle and data-toggle="dropdown".</p>
                        <div class="dropdown">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                                Dropdown Example
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">HTML</a>
                                </li>
                                <li>
                                    <a href="#">CSS</a>
                                </li>
                                <li>
                                    <a href="#">JavaScript</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div id="header">
                        <form action="danhSachTinhThanh.do" method="get">
                            <input type="hidden" name="action" value="search">
                            <table>
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/danhSachTinhThanh.do" class="colorBlue">
                                            <label>Trang chủ</label>
                                        </a>
                                    </td>
                                    <td>
                                        <input type="text" name="searchValue" value="${searchValue}" class="inputSearch" placeholder="giá trị tìm kiếm theo tên tỉnh thành">
                                    </td>
                                    <td>
                                        <input type="image" value="submit" src="${pageContext.request.contextPath}/IMAGES/search.png" alt="Submit" style="width: 20px; height: 20px; margin-left: 10px">
                                    </td>
                                    <c:choose>
                                        <c:when test="${not empty username }">
                                            <td>
                                                <div class="dropdown">
                                                    <a class="dropbtn">
                                                        <label>Bài viết</label>
                                                    </a>
                                                    <div class="dropdown-content">
                                                        <a href="${pageContext.request.contextPath}/danhSachBaiDaViet.do">
                                                            <label>Bài đã viết</label>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/xemDanhSachBaiVietDaLuu.do">
                                                            <label>Bài viết đã lưu</label>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/dangBaiViet.do?screenType=trangchu">
                                                            <label>Viết bài
                                                            </label>
                                                        </a>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="dropdown">
                                                    <a class="dropbtn">
                                                        <label>Tài khoản</label>
                                                    </a>
                                                    <div class="dropdown-content">
                                                        <a href="${pageContext.request.contextPath}/JSP/doimatkhau.jsp">
                                                            <label>Đổi mật khẩu</label>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/logout.do">
                                                            <label>Thoát</label>
                                                        </a>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <label>Chào ${username}</label>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="width: 100px">
                                                <a href="${pageContext.request.contextPath}/JSP/dangnhap.jsp" class="colorBlue">
                                                    <label>Đăng nhập</label>
                                                </a>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/JSP/dangky.jsp" class="colorBlue">
                                                    <label>Đăng ký</label>
                                                </a>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>

                                </tr>
                            </table>
                        </form>
                    </div>

                </body>

                </html>