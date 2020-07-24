$(function () {
    $("#search").click(function () {
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
                    $("<td></td>").html(workinfo.workstate).appendTo(oTr);
                    $("<td></td>").html(workinfo.workcount).appendTo(oTr);
                    $("<td></td>").html(workinfo.cretime).appendTo(oTr);
                    $("<td></td>").html(workinfo.updtime).appendTo(oTr);
                    $("<td></td>").html(workinfo.worksttime).appendTo(oTr);
                    $("<td></td>").html(workinfo.workentime).appendTo(oTr);

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
