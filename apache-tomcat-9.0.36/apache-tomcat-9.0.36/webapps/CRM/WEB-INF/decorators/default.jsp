<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri = "http://www.opensymphony.com/sitemesh/decorator" prefix ="dec" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href='<c:url value="/static/plugins/images/favicon.png"/>'>
    <title>Pixel Admin</title>
    <jsp:include page="/WEB-INF/layout/head.jsp"></jsp:include>
    <dec:head />
    <dec:getProperty property="page.menucss"></dec:getProperty>
    <dec:getProperty property="page.toastcss"></dec:getProperty>
    <dec:getProperty property="page.morriscss"></dec:getProperty>
    <dec:getProperty property="page.colorcss"></dec:getProperty>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <jsp:include page="/WEB-INF/layout/navbar.jsp"></jsp:include>
        <!-- Left navbar-header -->
        <jsp:include page="/WEB-INF/layout/sidebar.jsp"></jsp:include>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <dec:body></dec:body>
        <!-- /.container-fluid -->
        
    </div>
    <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <jsp:include page="/WEB-INF/layout/footer.jsp"/>
    <dec:getProperty property="page.slimscroll"></dec:getProperty>
    <dec:getProperty property="page.counter"></dec:getProperty>
    <dec:getProperty property="page.morris"></dec:getProperty>
    <dec:getProperty property="page.custom"></dec:getProperty>
</body>

</html>