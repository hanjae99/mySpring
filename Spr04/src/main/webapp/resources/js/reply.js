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
                callback(data);
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

    return {
        add: add,
        get: get,
        getList: getList,
        remove: remove,
        modify: modify
    };
})();
