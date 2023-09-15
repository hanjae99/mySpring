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

            <%--            검색창--%>
            <form class="form-inline" id="searchForm" action="/board/list" method="get">
                <select name="type" class="form-control form-select-lg mb-3 " aria-label="Default select example">
                    <option value="">==== 선택 ====</option>
                    <option value="T" <c:out value="${pageMaker.cri.type eq 'T' ? 'selected' : ''} "/> >제목</option>
                    <option value="C" <c:out value="${pageMaker.cri.type eq 'C' ? 'selected' : ''} "/>>내용</option>
                    <option value="W" <c:out value="${pageMaker.cri.type eq 'W' ? 'selected' : ''} "/>>글쓴이</option>
                    <option value="TC" <c:out value="${pageMaker.cri.type eq 'TC' ? 'selected' : ''} "/>>제목 or 내용</option>
                    <option value="TW" <c:out value="${pageMaker.cri.type eq 'TW' ? 'selected' : ''} "/>>제목 or 글쓴이</option>
                    <option value="TWC" <c:out value="${pageMaker.cri.type eq 'TWC' ? 'selected' : ''} "/>>제목 or 내용 or 글쓴이</option>
                </select>
                <input class="form-control mr-sm-2" type="search" name="keyword"
                       placeholder="검색어를 입력하세요" aria-label="Search" value="${pageMaker.cri.keyword}">
                <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                <button class="btn btn-outline-success my-2 my-sm-0">검색</button>
            </form>
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
                            <td><a class="move" href="${board.bno}"><c:out value="${board.title}"/></a></td>
                            <td><c:out value="${board.writer}"/></td>
                            <td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                            <td><fmt:formatDate value="${board.updatedate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <!-- /.table-responsive -->

                <div class="pull-right">
                    <ul class="pagination">
                        <c:if test="${pageMaker.prev}">
                            <li class="paginate_button previous">
                                <a href="${pageMaker.startPage -1}">Prev</a>
                            </li>
                        </c:if>
                        <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                            <li class="paginate_button ${pageMaker.cri.pageNum == num ? 'active' : ''}">
                                <a href="${num}">${num}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${pageMaker.next}">
                            <li class="paginate_button next">
                                <a href="${pageMaker.endPage + 1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
                <form action="/board/list" id="actionForm" method="get">
                    <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
                    <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                    <input type="hidden" name="type" value="${pageMaker.cri.type}">
                    <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
                </form>

                <!-- Modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                                </button>
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

<%--게시글 등록 시 모달 창 띄우기--%>
<script type="text/javascript">
    $(document).ready(function () {
        const result = "<c:out value='${result}'/>";
        checkModal(result);

        <%--게시글 등록 후 뒤로가기 시 모달창이 다시 뜨는 현상 방지--%>
        console.log(history);
        history.replaceState({}, null, null);

        function checkModal(result) {
            if (result === "" || history.state) {
                return;
            }
            if (parseInt(result) > 0) {
                $(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.")
            }
            $("#myModal").modal("show");
        }

        $("#regBtn").on("click", function () {
            // location.href = "/board/register";
            self.location = "/board/register";
        })

        const actionForm = $("#actionForm");
        $(".paginate_button a").on("click", function (e) {
            e.preventDefault();

            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.attr("action", "/board/list");
            actionForm.submit();
        });
        $(".move").on("click", function (e) {
            e.preventDefault();

            actionForm.find("input[name='bno']").remove();
            actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href") + "'>");
            actionForm.attr("action", "/board/get");
            actionForm.submit();
        });

        const searchForm = $("#searchForm");
        $("#searchForm button").on("click", function (e) {
            if (!searchForm.find("option:selected").val()) {
                alert("검색 조건을 선택하세요");
                return false;
            }
            if (!searchForm.find("input[name='keyword']").val()) {
                alert("검색 키워드를 입력하세요");
                return false;
            }

            searchForm.find("input[name='pageNum']").val("1");
            searchForm.submit();
        })
    })
</script>

<%@include file="../includes/footer.jsp" %>