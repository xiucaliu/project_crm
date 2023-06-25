<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
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
                    <h4 class="page-title">Chi tiết công việc </h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">Dashboard</a></li>
                        <li class="active">Blank Page</li>
                    </ol>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- BEGIN THỐNG KÊ -->
            <div class="row">
                <c:forEach var="statusListObj" items="${statusList}">

                    <!--col -->
                    <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                        <div class="white-box">
                            <div class="col-in row">
                                <div class="col-md-6 col-sm-6 col-xs-6"><i data-icon="E"
                                                                           class="linea-icon linea-basic"></i>
                                    <h5 class="text-muted vb"><c:out value = "${statusListObj.name}"/></h5>
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-6">
                                    <h3 class="counter text-right m-t-15 text-danger"><c:out
                                            value="${statusListObj.getTaskStatusPercent()}"/>%</h3>
                                </div>
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="progress">
                                        <div class="progress-bar progress-bar-danger" role="progressbar"
                                             aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                                             style="width: 20%">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- END THỐNG KÊ -->

            <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
            <div class="row">
                <c:forEach var="userListObj" items="${userList}">
                    <div class="col-xs-12">
                        <a href=
                               <c:url value="/user/details?id=${userListObj.id}"/> class="group-title">
                            <img width="30" src=
                                <c:url value="/plugins/images/users/${userListObj.avatar}"/>  class="img-circle"/>
                            <span><c:out value="${userListObj.fullname}"/></span>
                        </a>
                    </div>
                    <c:forEach var="statusListObj" items="${statusList}">
                        <div class="col-md-4">
                            <div class="white-box">
                                <h3 class="box-title"><c:out value="${statusListObj.name}"/></h3>
                                <div class="message-center">
                                    <c:forEach var="taskListObj" items="${taskList}">
                                        <c:if test="${taskListObj.userId == userListObj.id }">
                                            <c:if test="${taskListObj.statusId == statusListObj.id }">
                                                <a href="#">
                                                    <div class="mail-contnet">
                                                        <h5><c:out value="${taskListObj.name}"/></h5>
                                                        <span class="time">Bắt đầu: <c:out
                                                                value="${taskListObj.startDate}"/></span>
                                                        <br>
                                                        <span class="time">Kết thúc: <c:out
                                                                value="${taskListObj.endDate}"/></span>
                                                    </div>
                                                </a>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:forEach>


                <!-- END DANH SÁCH CÔNG VIỆC -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com</footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <jsp:include page="/linkJS.jsp"/>
</body>

</html>