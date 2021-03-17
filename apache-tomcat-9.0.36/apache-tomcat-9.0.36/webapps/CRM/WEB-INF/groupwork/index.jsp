<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<head>
	<!-- Menu CSS -->
	<content tag="menucss">
		<link rel="stylesheet" href='<c:url value="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>'>
	</content>
	<!-- color CSS -->
	<content tag="colorcss">
		<link rel="stylesheet" href='<c:url value="/static/css/custom.css"/>'>
	</content>
</head>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Danh sách công việc</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                <a href="/CRM/groupwork/add" class="btn btn-sm btn-success">Thêm mới</a>
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
                                    <th>Ngày Bắt Đầu</th>
                                    <th>Ngày Kết Thúc</th>
                                    <th>Hành Động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Phân tích dự án</td>
                                    <td>22/05/2019</td>
                                    <td>30/05/2019</td>
                                    <td>
                                        <a href="#" class="btn btn-sm btn-primary">Sửa</a>
                                        <a href="#" class="btn btn-sm btn-danger">Xóa</a>
                                        <a href="groupwork-details.html" class="btn btn-sm btn-info">Xem</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td>Thiết kế hệ thống</td>
                                    <td>22/05/2019</td>
                                    <td>30/05/2019</td>
                                    <td>
                                        <a href="#" class="btn btn-sm btn-primary">Sửa</a>
                                        <a href="#" class="btn btn-sm btn-danger">Xóa</a>
                                        <a href="groupwork-details.html" class="btn btn-sm btn-info">Xem</a>
                                    </td>
                                </tr>
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

<!--slimscroll JavaScript -->
<content tag="slimscroll">
	<script src='<c:url value="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"/>'></script>
</content>

<!-- Custom Theme JavaScript -->
<content tag="custom">
	<script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>
</content>