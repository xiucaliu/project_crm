<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                    <h4 class="page-title">Chi tiết thành viên</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-4 col-xs-12">
                    <div class="white-box">
                        <div class="user-bg"><img width="100%" alt="user" src="plugins/images/large/img1.jpg">
                            <div class="overlay-box">
                                <div class="user-content">
                                    <a href="javascript:void(0)"><img src=
                                                                      <c:url value="/plugins/images/users/${avatar}"/>
                                                                              class="thumb-lg img-circle" alt="img"></a>
                                    <h4 class="text-white"><c:out value="${name}"/></h4>
                                    <h5 class="text-white"><c:out value="${email}"/></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 col-xs-12">
                    <!-- BEGIN THỐNG KÊ -->
                    <div class="row">
                        <!--col -->
                        <c:forEach var="statusListObj" items="${statusList}">

                            <!--col -->
                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                                <div class="white-box">
                                    <div class="col-in row">
                                        <div class="col-xs-12">
                                            <h3 class="counter text-right m-t-15 text-danger"><c:out
                                                    value="${statusListObj.getTaskStatusPercent()}"/>%</h3>
                                        </div>
                                        <div class="col-xs-12"><i data-icon="E"
                                                                                   class="linea-icon linea-basic"></i>
                                            <h5 class="text-muted vb"><c:out value="${statusListObj.name}"/></h5>
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
                        <!-- /.col -->
                    </div>
                    <!-- END THỐNG KÊ -->

                </div>
            </div>
            <br/>
            <!-- /.row -->
            <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
            <h4>DANH SÁCH CÔNG VIỆC</h4>
            <div class="row">
                <c:forEach var="statusListObj" items="${statusList}">
                    <div class="col-md-4">
                        <div class="white-box">
                            <h3 class="box-title"><c:out value="${statusListObj.name}"/></h3>
                            <div class="message-center">
                                <c:forEach var="tasksListObj" items="${tasksList}">
                                    <c:if test="${tasksListObj.statusId == statusListObj.id}">
                                        <a href="#">
                                            <div class="mail-content">
                                                <h5><c:out value="${tasksListObj.name}"/></h5>
                                                <span class="time">Bắt đầu: <c:out
                                                        value="${tasksListObj.startDate}"/></span>
                                                <br>
                                                <span class="time">Kết thúc: <c:out
                                                        value="${tasksListObj.endDate}"/></span>
                                            </div>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
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