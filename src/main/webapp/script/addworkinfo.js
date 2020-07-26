$(function () {


    $("#selplan").change(function () {
        $("#selpro").attr("disabled", false);
        $('#selpro').selectpicker('refresh');

    });

    $("#selpro").change(function () {
        var pid = $(this).selectpicker('val');
        //$("#seleq option:gt(0)").remove();
        if (pid == 0) {
            $("#seleq").attr("disabled", true);
        } else {

            $.post("/platform/seteq", $("[name]").serialize(), function (data) {
                if (data) {
                    var workinfo = data;
                    $("<option></option>").html(workinfo.eqid).val(workinfo.eqid).appendTo("#seleq");
                }

            }, "json");
            $("#seleq").attr("disabled", false);
        }





    });

    $("#save").click(function () {

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
