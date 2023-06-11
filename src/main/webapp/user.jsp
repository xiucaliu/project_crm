<!DOCTYPE html>
<%@ page import="model.Users" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <jsp:include page="/linkHeader.jsp"/>
    <![endif]-->
</head>
<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
                <jsp:include page="/navbarHeader.jsp"/>
                <!-- /.navbar-header -->
                <!-- /.navbar-top-links -->
                <!-- /.navbar-static-side -->
            </nav>
        <!-- Left navbar-header -->
        <jsp:include page="/leftNavbar.jsp"/>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="cdol-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Danh sách thành viên</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                        <a href="<c:url value ="/user/add"/>" class="btn btn-sm btn-success">Thêm mới</a>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="table-responsive">
                                <table class="table" id="example">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Full Name</th>
                                            <th>Email</th>
                                            <th>Role</th>
                                            <th>#</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="listObj" items="${list}">
                                            <tr>
                                                <td><c:out value="${listObj.id}"/></td>
                                                <td><c:out value="${listObj.fullname}"/></td>
                                                <td><c:out value="${listObj.email}"/></td>
                                                <td><c:out value="${listObj.role_id}"/></td>
                                                <td>

                                                    <a href = <c:url value = "/user/update?id=${listObj.id}"/> class="btn btn-sm btn-primary btn-update-user">Sửa</a>
                                                    <!-- <a href=<c:url value = "/user/delete?idXem='${listObj.id}' "/> class="btn btn-sm btn-danger">Xóa</a> -->
                                                    <span userid="${listObj.id}" class="btn btn-sm btn-danger btn-delete-user">Xóa</span>
                                                    <a href=<c:url value = "/user/details?id=${listObj.id}"/> class="btn btn-sm btn-info">Xem</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
        <jsp:include page="/linkJS.jsp"/>
        <script src="<c:url value = "/js/user-table.js"/>"></script>
</body>
</html>