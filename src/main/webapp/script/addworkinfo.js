$(function () {


    $("#selplan").change(function () {
        $("#selpro").attr("disabled", false);
        $('#selpro').selectpicker('refresh');

    });

    $("#selpro").change(function () {

        $("#seleq").attr("disabled", false);
        $('#seleq').selectpicker('refresh');
    });
    
    $("#save").click(function () {

        $.post("/platform/addwork", $("[name]").serialize(), function (data) {
            if (data == "true") {
                $("#searchBtn").click();
                window.location.href = "/platform/workinfo.html";
            } else {
                alert("请重试！");

            }

        }, "text");


    });

});