<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hanjae
  Date: 2023/09/12
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">게시판</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board List Page
                <button id="regBtn" type="button" class="btn btn-xs pull-right btn-primary">게시글 등록</button>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>글 번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>수정일</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="board">
                        <tr>
                            <td><c:out value="${board.bno}"/></td>
                            <td><c:out value="${board.title}"/></td>
                            <td><c:out value="${board.writer}"/></td>
                            <td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                            <td><fmt:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <!-- /.table-responsive -->

                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">알림</h4>
                            </div>
                            <div class="modal-body">
                                처리가 완료되었습니다.
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>

<%--게시글 등록 중 뒤로가기 시 다시 등록할 수 있는 현상 방지--%>
<script type="text/javascript">
    $(document).ready(function () {
        const result = "<c:out value='${result}'/>";
        checkModal(result);

        function checkModal(result) {
            if (result === "") {
                return;
            }
            if (parseInt(result) > 0) {
                $(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.")
            }
            $("#myModal").modal("show");
        }

        $("#regBtn").on("click", function (){
            // location.href = "/board/register";
            self.location = "/board/register";
        })
    })
</script>

<%@include file="../includes/footer.jsp" %>