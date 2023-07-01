<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Danh sách công việc</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                    <a href="<c:url value = "/task/add"/>" class="btn btn-sm btn-success">Thêm mới</a>
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
                                    <th>Tên Công Việc</th>
                                    <th>Dự Án</th>
                                    <th>Người Thực Hiện</th>
                                    <th>Ngày Bắt Đầu</th>
                                    <th>Ngày Kết Thúc</th>
                                    <th>Trạng Thái</th>
                                    <th>Hành Động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="listObj" items="${list}">
                                    <tr>
                                        <td><c:out value="${listObj.id}"/></td>
                                        <td><c:out value="${listObj.name}"/></td>
                                        <c:forEach var="jobListObj" items="${jobList}">

                                            <c:if test="${listObj.jobId == jobListObj.id }">
                                                <td><c:out value="${jobListObj.name}"/></td>
                                            </c:if>
                                        </c:forEach>

                                        <c:forEach var="userListObj" items="${userList}">
                                            <c:if test="${listObj.userId ==userListObj.id }">
                                                <td><c:out value="${userListObj.fullname}"/></td>
                                            </c:if>
                                        </c:forEach>
                                        <td><c:out value="${listObj.getStartDate()}"/></td>
                                        <td><c:out value="${listObj.getEndDate()}"/></td>
                                        <c:forEach var="statusListObj" items="${statusList}">
                                            <c:if test="${listObj.statusId == statusListObj.id }">
                                                <td><c:out value="${statusListObj.name}"/></td>
                                            </c:if>
                                        </c:forEach>
                                        <td>
                                            <a href=
                                                   <c:url value="/task/update?id=${listObj.id}"/> class="btn btn-sm
                                               btn-primary">Sửa</a>
                                            <span taskid="${listObj.id}" class="btn btn-sm btn-danger btn-delete-task">Xóa</span>
                                            <!-- <a href=Xem
                                                  <c:url value="/task/details?id=${listObj.id}"/> class="btn btn-sm
                                               btn-info">Xem</a> -->
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
        <footer class="footer text-center"> 2018 &copy; myclass.com</footer>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<jsp:include page="/linkJS.jsp"/>
<script src="<c:url value = "/js/task.js"/>"></script>
</body>

</html>