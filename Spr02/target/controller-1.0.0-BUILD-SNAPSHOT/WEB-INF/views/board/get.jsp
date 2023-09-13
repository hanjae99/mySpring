<%--
  Created by IntelliJ IDEA.
  User: hanjae
  Date: 2023/09/12
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">게시글 조회</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Insert
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="form-group">
                            <label>글번호</label>
                            <input class="form-control" name="bno" placeholder="제목을 입력하세요" value="${board.bno}"
                                   readonly>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input class="form-control" name="title" placeholder="제목을 입력하세요" value="${board.title}"
                                   readonly>
                        </div>
                        <div class="form-group">
                            <label>작성자</label>
                            <input class="form-control" name="writer" placeholder="작성자를 입력하세요" value="${board.writer}"
                                   readonly>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea class="form-control" name="content" rows="5" readonly>${board.content}</textarea>
                        </div>
                        <button data-oper="modify" class="btn btn-default" onclick="location.href='/board/modify?bno=${board.bno}'">수정</button>
                        <button data-oper="remove"  class="btn btn-danger" onclick="location.href='/board/remove?bno=${board.bno}'">삭제</button>
                        <button data-oper="list"  class="btn btn-info" onclick="location.href='/board/list'">목록</button>
                    </div>
                    <!-- /.col-lg-6 (nested) -->

                </div>
                <!-- /.row (nested) -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp" %>