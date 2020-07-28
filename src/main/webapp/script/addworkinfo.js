$(function () {
    $.post("/platform/initplan", null, function (data) {
        if (data) {
            for (var i = 0; i < data.length; i++) {
                var planinfo = data[i];
                $("<option></option>").html(planinfo.planid).val(planinfo.planid).appendTo("#selplan");
            }
        }
    }, "json");

    $("#selplan").change(function () {
        var planid = $(this).val();
        $("#selpro option:gt(0)").remove();
        if (planid == 0) {
            $("#selpro").attr("disabled", true);
        } else {

            $.post("/platform/getprid", "planid=" + planid, function (data) {
                if (data) {
                    $("<option></option>").html(data).val(data).appendTo("#selpro");
                }

            }, "text");
            $("#selpro").attr("disabled", false);
        }
    });

    $("#selpro").change(function () {
        var pid = $(this).val();
        $("#seleq option:gt(0)").remove();
        if (pid == 0) {
            $("#seleq").attr("disabled", true);
        } else {

            $.post("/platform/seteq", $("[name]").serialize(), function (data) {
                if (data) {
                    var workinfo = data;
                    $("<option></option>").html(workinfo.eqid).val(workinfo.eqid).appendTo("#seleq");
                }
                else{
                    alert("未找到匹配的未启动设备");
                }

            }, "json");
            $("#seleq").attr("disabled", false);
        }





    });

    $("#save").click(function () {

        var planid = $("#selplan").val();
        if (!planid) {
            alert("请选择计划编号");
            return;
        }
        var proid = $("#selpro").val();
        if (!proid) {
            alert("请选择产品编号");
            return;
        }
        var eqid = $("#seleq").val();
        if (!eqid) {
            alert("请选择设备编号");
            return;
        }
        var workcount = $("#workcount").val();
        if (!workcount) {
            alert("请填写工单数量");
            return;
        }
        var workstate = $("#workstate").val();
        if (!workstate) {
            alert("请选择工单状态");
            return;
        }
        var worksttime = $("#worksttime").val();
        if (!worksttime) {
            alert("请填写开始日期");
            return;
        }
        var workentime = $("#workentime").val();
        if (!workentime) {
            alert("请填写结束日期");
            return;
        }

        $.post("/platform/addwork", $("[id]").serialize(), function (data) {
            if (data == "true") {
                window.location.href = "/platform/workinfo.html";
                // $("#searchBtn").click();

            } else {
                alert("请重试！");

            }

        }, "text");


    });

});
