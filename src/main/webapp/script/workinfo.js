


$(function () {
    $("#searchBtnShow").click(function () {
        $("#pageNum").val(1);
        $("#searchBtn").click();
    });

    $("#searchBtn").click(function () {
        $.post("/platform/searchall", $("[name]").serialize(), function (data) {
            if (data && data.size > 0) {
                $("#total").html(data.total);
                $("#pages").html(data.pages);
                $("#curpage").html(data.pageNum);

                $("#resulttable tr:gt(0)").remove();
                for (var i = 0; i < data.list.length; i++) {
                    var workinfo = data.list[i];
                    var oTr = $("<tr></tr>");
                    $("<td></td>").html(workinfo.workid).appendTo(oTr);
                    $("<td></td>").html(workinfo.planid).appendTo(oTr);
                    $("<td></td>").html(workinfo.eqid).appendTo(oTr);
                    $("<td></td>").html(workinfo.proid).appendTo(oTr);
                    if(workinfo.workstate == 10){
                        $("<td></td>").html("未启动").appendTo(oTr);
                    }else if(workinfo.workstate == 20){
                        $("<td></td>").html("生产中").appendTo(oTr);
                    }else{
                        $("<td></td>").html("已完成").appendTo(oTr);
                    }
                    
                    $("<td></td>").html(workinfo.workcount).appendTo(oTr);
                    
                    $("<td></td>").html(workinfo.cretime).appendTo(oTr);                  
                    $("<td></td>").html(workinfo.updtime).appendTo(oTr);
                    $("<td></td>").html(workinfo.worksttime).appendTo(oTr);
                    $("<td></td>").html(workinfo.workentime).appendTo(oTr);
 

                    var oTd = $("<td></td>").appendTo(oTr);
                    if (workinfo.workstate == 10) {
                        $("<input type='button' class='btn btn-danger' value='删除'>").click(function () {
                            var isOK = confirm("您确认要删除吗？");
                            if (isOK) {
                                var workid = $(this).parent().parent().find("td:eq(0)").html();

                                $.post("/platform/delwork", "workid=" + workid, function (data) {
                                    if (data == "true") {
                                        alert("删除成功");
                                        $("#searchBtn").click();
                                    } else {
                                        alert("删除失败，请重试");
                                    }
                                }, "text");
                            }

                        }).appendTo(oTd);


                        $("<input type='button' class='btn btn-success' value=' 启动'>").click(function () {
                            var workid = $(this).parent().parent().find("td:eq(0)").html();
                            var updtime = $(this).parent().parent().find("td:eq(7)").html();

                            $.post("/platform/start", "workid=" + workid, function (data) {
                                if (data) {
                                    alert("启动成功");
                                    
                                    $("#searchBtn").click();
                                } else {
                                    alert("启动失败，请重试");
                                }
                            }, "json");

                        }).appendTo(oTd);
                    }



                    oTr.appendTo("#resulttable");
                }
                $("#resultdiv").show();
                $("#pagectrl").show();

                if (data.isFirstPage) {
                    $("#prePage").hide();
                    $("#prePageSpan").show();
                } else {
                    $("#prePage").show();
                    $("#prePageSpan").hide();
                }
                if (data.isLastPage) {
                    $("#nextPage").hide();
                    $("#nextPageSpan").show();
                } else {
                    $("#nextPage").show();
                    $("#nextPageSpan").hide();
                }
            } else {
                $("#resultdiv").hide();
                $("#pagectrl").hide();

                alert("未查到数据");
            }
        }, "json");
    });

});



$(function () {
    $("#prePage").click(function () {
        var pageNum = $("#pageNum").val();
        $("#pageNum").val(pageNum - 1);
        $("#searchBtn").click();
    });

    $("#nextPage").click(function () {
        var pageNum = $("#pageNum").val();
        $("#pageNum").val(pageNum * 1 + 1);
        $("#searchBtn").click();
    });

    $("#goBtn").click(function () {
        var gopage = $("#gopage").val() * 1;
        if (gopage < 1) {
            gopage = 1;
        }

        var pages = $("#pages").html();
        if (gopage > pages * 1) {
            gopage = pages;
        }

        $("#pageNum").val(gopage);
        $("#searchBtn").click();
    });
    
    /*window.onload = function() {
    var from = sessionStorage.getItem("from");
    if(from == 'pageA') {
        //要触发的点击事件  $('#xxx').click()
        $("#searchBtn").click();
        sessionStorage.setItem("from",""); //销毁 from 防止在b页面刷新 依然触发$('#xxx').click()
    }
}*/
    
});
