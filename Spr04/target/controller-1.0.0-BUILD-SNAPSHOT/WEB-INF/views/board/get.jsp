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
                        <button data-oper="modify" class="btn btn-default">수정</button>
                        <button data-oper="remove" class="btn btn-danger"
                                onclick="location.href='/board/remove?bno=${board.bno}'">삭제
                        </button>
                        <button data-oper="list" class="btn btn-info">목록</button>
                    </div>
                    <!-- /.col-lg-6 (nested) -->
                    <form id="operForm" action="/board/modify" method="get">
                        <input type="hidden" name="pageNum" value="${cri.pageNum}">
                        <input type="hidden" name="amount" value="${cri.amount}">
                        <input id="bno" type="hidden" name="bno" value="${board.bno}">
                        <input type="hidden" name="type" value="${cri.type}">
                        <input type="hidden" name="keyword" value="${cri.keyword}">
                    </form>

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

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">댓글 등록</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>댓글내용</label>
                    <input type="text" name="reply" class="form-control" value="insert reply">
                </div>
                <div class="form-group">
                    <label>작성자</label>
                    <input type="text" name="replyer" class="form-control" value="insert replyer">
                </div>
                <div class="form-group">
                    <label>작성일자</label>
                    <input type="text" name="replyDate" class="form-control" value="insert replyDate">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="modalModBtn" class="btn btn-warning">변경</button>
                <button type="button" id="modalDelBtn" class="btn btn-danger">삭제</button>
                <button type="button" id="modalRegBtn" class="btn btn-primary">확인</button>
                <button type="button" id="modalOutBtn" class="btn btn-default" data-dismiss="modal">닫기</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<%--댓글 목록 구현--%>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i>댓글
                <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">댓글 추가</button>
            </div>
            <div class="panel-body">
                <ul class="chat">
                    <li class="left clearfix" data-rno="10">
                        <div>
                            <div class="header">
                                <strong class="primary-font">user00</strong>
                                <small class="pull-right text-muted">2023-09-19 10:06</small>
                            </div>
                            <p>댓글 내용 들어갈 자리</p>
                        </div>
                    </li>
                </ul>
            </div>
<%--            댓글 페이지--%>
            <div class="panel-footer">

            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function (){
        const bnoValue = "<c:out value='${board.bno}'/>";
        const replyUL = $(".chat");

        showList(1);

        function showList(page){
            replyService.getList(
                {bno: bnoValue, page: page || 1},
                function (replyCnt, list){

                    if (page === -1){
                        pageNum = Math.ceil(replyCnt / 10.0);
                        showList(pageNum);
                        return;
                    }

                    let str = "";
                    if(list === null || list.length === 0){
                        replyUL.html("");
                        return;
                    }

                    for (let i=0, len=list.length; i<len; i++){
                        str += "<li class='left clearfix' style='border-bottom: 1px solid #999' data-rno='" + list[i].rno + "'>";
                        str += " <div><div class='header'><strong class='primary-font'>" + list[i].replyer
                                + "</strong>";
                        str += " <small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
                        str += " <p>" + list[i].reply + "</p></div></li>";
                    }

                    replyUL.html(str);
                    showReplyPage(replyCnt);
                }
            );
        }

        // 댓글 페이징
        let pageNum = 1;
        let replyPageFooter = $(".panel-footer");

        function showReplyPage(replyCnt){
            let endNum = Math.ceil(pageNum / 10.0) * 10;
            let startNum = endNum - 9;
            const prev = startNum != 1;
            let next = false;

            if (endNum * 10 >= replyCnt){
                endNum = Math.ceil(replyCnt / 10.0);
            }

            if (endNum * 10 < replyCnt){
                next = true;
            }

            let str = "<ul class='pagination pull-right'>";

            if (prev){
                str += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>이전</a></li>";
            }

            for (let i=startNum; i<=endNum; i++){
                const active = pageNum === i ? "active" : "";
                str += "<li class='page-item " + active + "'><a class='page-link' href='" + i + "'>"
                        + i + "</a></li>";
            }

            if (next){
                str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>다음</a></li>";
            }

            str += "</ul>";
            console.log(str);

            replyPageFooter.html(str);
        }

        // 댓글 모달 처리
        const modal = $("#myModal");
        const modalInputReply = modal.find("input[name='reply']");
        const modalInputReplyer = modal.find("input[name='replyer']");
        const modalInputReplyDate = modal.find("input[name='replyDate']");

        const modalModBtn = $("#modalModBtn");
        const modalRegBtn = $("#modalRegBtn");
        const modalDelBtn = $("#modalDelBtn");
        const modalOutBtn = $("#modalOutBtn");

        $("#addReplyBtn").on("click", function (e){
            $("#myModalLabel").html("댓글 등록");
            modal.find("input").val("");
            modalInputReplyer.removeAttr("readonly");
            // closest: 특정 요소의 근처 요소를 찾아냄
            modalInputReplyDate.closest("div").hide();

            // 닫기, 등록버튼 이외에 다 숨기기
            modal.find("button[id != 'modalOutBtn']").hide();
            modalRegBtn.show();

            modal.modal("show");
        });

        replyUL.on("click", "li", function (e){
            const rno = $(this).data("rno");
            console.log("chat-rno: ", rno);

            replyService.get(rno, function (reply){

                modalInputReply.val(reply.reply);
                modalInputReplyer.val(reply.replyer).attr("readonly", "readonly");
                modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");

                modal.data("rno", reply.rno);
                modal.find("button[id != 'modalOutBtn']").hide();
                modalInputReplyDate.closest("div").show();
                modalModBtn.show();
                modalDelBtn.show();
                $("#myModalLabel").html("댓글 조회");

                modal.modal("show");
            });
        })

        modalRegBtn.on("click", function (e){
            const reply = {
                reply: modalInputReply.val(),
                replyer: modalInputReplyer.val(),
                bno: bnoValue
            }

            replyService.add(reply, function (result){
                // modal.find("input").val("");
                modal.modal("hide");
                alert(result);
                // location.reload();
                showList(-1);
            });

        });

        modalModBtn.on("click", function (e){
            const reply = {rno:modal.data("rno"), reply: modalInputReply.val()};

            replyService.modify(reply, function (result){
                modal.modal("hide");
                alert(result);
                location.reload();
            })
        });

        modalDelBtn.on("click", function (e){
            const rno = modal.data("rno");

            replyService.remove(rno, function (result){
                modal.modal("hide");
                alert(result);
                location.reload();
            })
        });




    });
</script>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        // console.log("----------");
        // console.log("---JS TEST---");

        <%--const bnoValue = "<c:out value='${board.bno}'/>";--%>

        // replyService.add(
        //     {reply: "JS TEST", replyer: "tester", bno: bnoValue},
        //     function (result){
        //         alert("RESULT: " + result);
        //     }
        // );

        // replyService.get(203,
        //     function (data) {
        //         console.log("...get data: ", data);
        //     }
        // );

        // replyService.getList({bno: bnoValue, page: 1},
        //     function (list) {
        //         for (let i = 0, len = list.length || 0; i < len; i++) {
        //             console.log(list[i]);
        //         }
        //     }
        // );

        // replyService.remove(204,
        //     function (result) {
        //         console.log("...remove count: " + result);
        //
        //         if (result === "success") {
        //             alert("removed successfully!");
        //         }
        //     }, function (err) {
        //         alert("ERROR!");
        //     });

        // replyService.modify({
        //     rno: 201,
        //     reply: "jsModify"
        // }, function (result) {
        //     alert("수정완료 " + result);
        // });

        console.log(replyService);

        const operForm = $("#operForm");

        $("button[data-oper='modify']").on("click", function (e) {
            operForm.submit();
        });

        $("button[data-oper='remove']").on("click", function (e) {
            operForm.attr("action", "/board/remove").submit();
        })

        $("button[data-oper='list']").on("click", function (e) {
            operForm.find("#bno").remove();
            operForm.attr("action", "/board/list").submit();
        })
    })
</script>

<%@include file="../includes/footer.jsp" %>