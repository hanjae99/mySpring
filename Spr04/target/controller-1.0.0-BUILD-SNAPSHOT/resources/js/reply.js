/**
 *
 */

console.log("reply module...");

const replyService = (function () {
    function add(reply, callback, error) {
        console.log("...replyService > add");

        $.ajax({
            type: "POST",
            url: "/reply/new",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=UTF-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            },
        });
    }

    function get(rno, callback, error) {
        $.get("/reply/" + rno + ".json",
            function (result) {
                if (callback) {
                    callback(result);
                }
            }).fail(function (xhr, status, err) {
            if (error) {
                error(err);
            }
        });
    }

    function getList(param, callback, error) {
        const bno = param.bno;
        const page = param.page || 1;

        $.getJSON("/reply/pages/" + bno + "/" + page + ".json", function (data) {
            if (callback) {
                callback(data.replyCnt, data.list);
            }
        }).fail(function (xhr, status, err) {
            if (error) {
                error(err);
            }
        });
    }

    function remove(rno, callback, error) {
        $.ajax({
            type: "DELETE",
            url: "/reply/" + rno,
            success: function (deleteResult, status, xhr) {
                if (callback) {
                    callback(deleteResult);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        });
    }

    function modify(reply, callback, error) {
        console.log("...modify rno: " + reply.rno);

        $.ajax({
            type: "PUT",
            url: "/reply/" + reply.rno,
            data: JSON.stringify(reply),
            contentType: "application/json; charset=UTF-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
        })
    }

    function displayTime(timeValue) {
        const today = new Date();
        const gap = today.getTime() - timeValue;

        const dateObj = new Date(timeValue);
        let str = "";

        if (gap < (1000 * 60 * 60 * 24)) {

            const hh = dateObj.getHours().toString().padStart(2, "0");
            const mm = dateObj.getMinutes().toString().padStart(2, "0");
            const ss = dateObj.getSeconds().toString().padStart(2, "0");

            // 날짜 형식 (오늘 작성된 게시글이면 시간만, 아니면 날짜만)
            // 한자리수일경우 앞에 0 처리
            // return [(hh > 9 ? "" : "0") + hh,
            //     ":", (mm > 9 ? "" : "0") + mm,
            //     ":", (ss > 9 ? "" : "0") + ss].join("");
            return hh + ":" + mm + ":" + ss;

        } else {
            const yy = dateObj.getFullYear();
            let MM = dateObj.getMonth() + 1;
            MM = MM.toString().padStart(2, "0");
            const dd = dateObj.getDate().toString().padStart(2, "0");

            // return [yy,
            //     "/", (MM > 9 ? "" : "0") + MM,
            //     "/", (dd > 9 ? "" : "0") + dd].join("");
            return [yy, MM, dd].join("/");
        }
    }

    return {
        add: add,
        get: get,
        getList: getList,
        remove: remove,
        modify: modify,
        displayTime: displayTime
    };
})();
