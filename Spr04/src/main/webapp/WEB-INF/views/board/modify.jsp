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
        <h1 class="page-header">게시글 수정</h1>
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
                        <form>
                            <input type="hidden" name="pageNum" value="${cri.pageNum}">
                            <input type="hidden" name="amount" value="${cri.amount}">
                            <input type="hidden" name="type" value="${cri.type}">
                            <input type="hidden" name="keyword" value="${cri.keyword}">
                            <div class="form-group">
                                <label>글번호</label>
                                <input class="form-control" name="bno" value="${board.bno}">
                            </div>
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name="title" placeholder="제목을 입력하세요" value="${board.title}">
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name="writer" value="${board.writer}" readonly>
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" name="content" rows="5">${board.content}</textarea>
                            </div>
                            <div class="form-group">
                                <label>등록일</label>
                                <input class="form-control" name="regdate"
                                       value="<fmt:formatDate value='${board.regdate}' pattern='yyyy-MM-dd hh:mm:ss'/>"
                                       readonly>
                            </div>
                            <div class="form-group">
                                <label>수정일</label>
                                <input class="form-control" name="updatedate"
                                       value="<fmt:formatDate value='${board.updatedate}' pattern='yyyy-MM-dd hh:mm:ss'/>"
                                       readonly>
                            </div>
                            <button type="button" data-oper="modify" class="btn btn-default btn-success">수정</button>
                            <button type="button" data-oper="list" class="btn btn-info">목록</button>
                        </form>
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

<%--button 의 data-oper 속성을 이용하여 페이지 이동 처리--%>
<script>
    $(document).ready(function (){
        const formObj = $("form");

        $("button").on("click", function (e){
            e.preventDefault();

            const operation = $(this).data("oper");

            if (operation === "modify"){
                formObj.attr("action", "/board/modify").attr("method", "POST");
            }else if (operation === "list"){
                formObj.attr("action", "/board/list").attr("method", "GET");

                const pageNumTag = $("input[name='pageNum']").clone();
                const amountTag = $("input[name='amount']").clone();
                const typeTag = $("input[name='type']").clone();
                const keywordTag = $("input[name='keyword']").clone();

                formObj.empty();
                formObj.append(pageNumTag);
                formObj.append(amountTag);
                formObj.append(typeTag);
                formObj.append(keywordTag);
            }

            formObj.submit();
        })
    })
</script>
<%@include file="../includes/footer.jsp" %>