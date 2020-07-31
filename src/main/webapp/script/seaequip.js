$(function () {
    $("#addequip").click(function () {
       $.post("/platform/addequip", null, function (data) {
           if(data =="true"){
               alert("添加成功");
           }
           else{
               alert("添加失败");
           }
       },"text");
    });
});
//分页查询的功能实现
$(function () {
//    //给显示的查询按钮绑定点击事件
          $("#searchequip").click(function(){
                $("#equip").show();
                $("#equipcondition").show();

                $("#con").hide();
                $("#concondition").hide();
                $("#condiv").hide();
                $("#conpagectrl").hide();

});


            // 给隐藏查询按钮绑定点击事件searchcon2
          $("#searchbtnequip").click(function () {
                // 发送Ajax请求
                $.post("/platform/seaequip", $("[name]").serialize(), function (data) {
                        var pagenum  = $("#equippageNum").val();
                        var pagesize = $("#equippageSize").val();
                    if (data && data.size > 0) {
                        // 1.动态添加信息
                        // 添加总记录条数
                        $("#equiptotal").html(data.total);
                        // 添加总页数
                        $("#equippages").html(data.pages);
                        // 添加当前页数
                        $("#equipcurpage").html(data.pageNum);

                        // 2.添加记录
                        // 先清空之前的查询记录，删除原有记录行
                        $("#equiptable tr:gt(0)").remove();
                        //循环遍历结果集，添加数据行
                        for (var i = 0; i < data.list.length; i++) {
                            var equipinfo = data.list[i];
                            //创建一行，生成tr元素  tr代表行
                            var oTr = $("<tr></tr>");
                            // 生成单元格,写入数据，放到tr中
                            $("<td></td>").html(i+1+( pagenum*1-1)*pagesize).appendTo(oTr);
                            $("<td></td>").html(equipinfo.eqid).appendTo(oTr);
                            if(equipinfo.eqstate*1==10){$("<td></td>").html("已启动").appendTo(oTr);}
                            else if(equipinfo.eqstate*1==20){$("<td></td>").html("空闲中").appendTo(oTr);}
                            else if(equipinfo.eqstate*1==30){$("<td></td>").html("故障").appendTo(oTr);}
                            //动态生成删除按钮
                            var oTd = $("<td></td>").appendTo(oTr);
                            if(equipinfo.eqstate*1!=10){
                                $("<input type='button' value='删除'>'").click(function () {
                                //进行二次确认
                                var isOK = confirm("请再次确认要删除此产品");
                                //真正删除产品
                                if (isOK) {
                                    //如何获取当前删除的id
                                    //通过Dao模型之间的父子关系来获取
                                    var eqid = $(this).parent().parent().find("td:eq(1)").html();
                                    $.post("/platform/delequip", "eqid=" + eqid, function (data) {
                                        if (data == "true") {
                                            //删除成功
                                            alert("删除成功");
                                            $("#searchbtnequip").click();
                                        } else {
                                            //删除失败
                                            alert("删除失败");
                                        }
                                    }, "text")

                                }
                                }).appendTo(oTd);   
                            }
                            //动态生成修改按钮
                            $("<input type='button' value='修改'>").click(function () {
                                if ($(this).val() == "修改") {
                                    //如果当前用户点击的是修改，则用户点击的是修改功能
                                    //1.将用户名变为可编辑状态
                                    //1.1获取当前用户名
                                    //获取当前行的第二个单元格对象
                                    var oTd4 = $(this).parent().parent().find("td:eq(2)");
                                    //获取其中的文本
                                    var eqstate = oTd4.html();
                                    //1.2清空单元格
                                    oTd4.empty();
                                    //1.3动态生成一个文本框，并将用户名初始化到其中并将其放到oTd2中
        //                             <select name="pid" id="selprov">
                                    $("<select name='eqstate' id='selstate'>").css("width", "50px").val(eqstate).appendTo(oTd4);

                                    $("<option></option>").html("已启动").val(10).appendTo("#selstate");
                                    $("<option></option>").html("空闲中").val(20).appendTo("#selstate");
                                    $("<option></option>").html("故障").val(30).appendTo("#selstate");
                                    //第三个单元格
                                    $(this).val("确定");
                                }

                                else if ($(this).val() == "确定") {
                                    var oText4 = $(this).parent().parent().find("td:eq(2) select");
                                    var eqstate = oText4.val();
                                    if (!eqstate) {
                                        alert("请输入设备状态");
                                        oText4.focus();
                                        return;
                                    }
                                    //2.发送ajax请求进行修改
                                    //获取当前用户的id
                                    var eqid = $(this).parent().parent().find("td:eq(1)").html();

                                    var oBtn = $(this);

                                    $.post("/platform/modequip", "eqid=" + eqid + "&eqstate=" + eqstate, function (data) {
                                        if (data == "true") {
                                            //修改成功
                                            //1.将用户名编程不可编辑状态
                                            var oTd4 = oBtn.parent().parent().find("td:eq(2)");
                                            var oText4 = oTd4.find("select");
                                            //获取用户输入的用户名
                                            var eqstate = oText4.val();

                                            oTd4.empty();
                                            //将新用户名填天会到单元格中
                                            if(eqstate*1==10){oTd4.html("已启动");}
                                            else if(eqstate*1==20){oTd4.html("空闲中");}
                                            else if(eqstate*1==30){oTd4.html("故障");}
                                            oBtn.val("修改");
                                            //4.提示信息
                                            alert("修改成功");
                                           // $("#searchbtnequip").click();
                                        } else {
                                            //修改失败
                                            alert("修改失败");
                                        }
                                    }, "text");
                                }
                            }).appendTo(oTd);

                            oTr.appendTo("#equiptable");
                        }
                        // 3.页面的控制
                        // 结果表格和分页控制按钮的显示
                        $("#equipdiv").show();
                        $("#equippagectrl").show();
                        //上一页和下一页按钮的控制
                        if (data.isFirstPage) {
                            // 当前是第一页
                            $("#equipprePage").attr("disabled", true);
                        } else {
                            $("#equipprePage").attr("disabled", false);
                        }

                        if (data.isLastPage) {
                            $("#equipnextPage").attr("disabled", true);
                        } else {
                            $("#equipnextPage").attr("disabled", false);
                        }

                    } else {
                        $("#equipresultdiv").hide();
                        $("#equippagectrl").hide();
                        alert("没有查到数据");
                    }
                }, "json");

});
        });
//分页控制
$(function () {
    //上一页功能
$("#equipprePage").click(function () {
        //将页面上隐藏的pageNum的值-1
        //val()可以获取值，也可以赋值
        var pageNum = $("#equippageNum").val();
        if(pageNum*1<1){
            $("#equippageNum").val(1);
        }else{
            $("#equippageNum").val(pageNum - 1);
        }
        //再进行一次查询操作,即点击一次查询按钮
        //click同val类似，给function为绑定事件，不给则认为进行一次点击操作
        $("#searchbtnequip").click();

    });

    //完成下一页功能
    $("#equipnextPage").click(function () {
        //pageNum是字符串类型，字符串无减的操作，而+会被字符串认为是连接操作
        var pages = $("#equippages").html();
        var pageNum = $("#equippageNum").val();
        if(pageNum*1>pages*1){
            $("#equippageNum").val(pages);
        }else{
            $("#equippageNum").val(pageNum * 1 + 1);
        }
            
        //点击查询按钮
        $("#searchbtnequip").click();
    });


    //完成Go按钮功能
    $("#equipgoBtn").click(function () {
        //获取跳转页数，转成数字
        var gopage = $("#equipgopage").val() * 1;
        //判断用户输入
        //页面上用户输入东西都是字符串
        if (gopage < 1) {
            gopage = 1;
        }
        //获取总页数
        var pages = $("#equippages").html();
        if (gopage > pages * 1) {
            gopage = pages;
        }
        $("#equippageNum").val(gopage);
        $("#equipsearchBtn").click();
    });
});

$(function(){
 $("#searchcon").click(function(){
        $("#con").show();
        $("#concondition").show();
        
        $("#equip").hide();
        $("#equipcondition").hide();
        $("#equipdiv").hide();
        $("#equippagectrl").hide();
        $("#addcon").hide();
//        
        
    });
    // 给隐藏查询按钮绑定点击事件
 $("#searchbtncon").click(function () {
        // 发送Ajax请求
        $.post("/platform/seacon", $("[name]").serialize(), function (data) {
            var pagenum  = $("#conpageNum").val();
            var pagesize = $("#conpageSize").val();
            if (data && data.size > 0) {
    
                // 1.动态添加信息
                // 添加总记录条数
                $("#contotal").html(data.total);
                // 添加总页数
                $("#conpages").html(data.pages);
                // 添加当前页数
                $("#concurpage").html(data.pageNum);

                // 2.添加记录
                // 先清空之前的查询记录，删除原有记录行
                $("#contable tr:gt(0)").remove();
                //循环遍历结果集，添加数据行
                for (var i = 0; i < data.list.length; i++) {
                    var coninfo = data.list[i];
                    //创建一行，生成tr元素  tr代表行
                    var oTr = $("<tr></tr>");
                    // 生成单元格,写入数据，放到tr中
                    $("<td></td>").html(i+1+( pagenum*1-1)*pagesize).appendTo(oTr);
                    $("<td></td>").html(coninfo.id).appendTo(oTr);
                    $("<td></td>").html(coninfo.eqid).appendTo(oTr);
                    $("<td></td>").html(coninfo.proid).appendTo(oTr);
                    $("<td></td>").html(coninfo.yield).appendTo(oTr);
                    // 将te放入表格中
                    //动态生成删除按钮
                    var oTd = $("<td></td>").appendTo(oTr);
                    $("<input type='button' value='删除'>'").click(function () {
                        //进行二次确认
                        var isOK = confirm("确认删除关联");
                        //真正删除产品
                        if (isOK) {
                            //如何获取当前删除的id
                            //通过Dao模型之间的父子关系来获取
                            var id = $(this).parent().parent().find("td:eq(1)").html();
                            $.post("/platform/delcon", "id=" + id, function (data) {
                                if (data == "true") {
                                    //删除成功
                                    alert("删除成功");
                                    $("#searchbtncon").click();
                                } else {
                                    //删除失败
                                    alert("删除失败");
                                }
                            }, "text")
                        }
                    }).appendTo(oTd);   
                    oTr.appendTo("#contable");
                }
                // 3.页面的控制
                // 结果表格和分页控制按钮的显示
                $("#condiv").show();
                $("#conpagectrl").show();
                //上一页和下一页按钮的控制
                if (data.isFirstPage) {
                    // 当前是第一页
                    $("#conprePage").attr("disabled", true);
                } else {
                    $("#conprePage").attr("disabled", false);
                }

                if (data.isLastPage) {
                    $("#connextPage").attr("disabled", true);
                } else {
                    $("#connextPage").attr("disabled", false);
                }

            } else {
                $("#conresultdiv").hide();
                $("#conpagectrl").hide();
                alert("没有查到数据");
            }
        }, "json");
        
    });
});


$(function () {
    //上一页功能
    $("#conprePage").click(function () {
        //将页面上隐藏的pageNum的值-1
        //val()可以获取值，也可以赋值
        var pageNum = $("#conpageNum").val();
        if(pageNum*1<1){
            $("#conpageNum").val(1);
        }else{
            $("#conpageNum").val(pageNum - 1);
        }
        //再进行一次查询操作,即点击一次查询按钮
        //click同val类似，给function为绑定事件，不给则认为进行一次点击操作
        $("#searchbtncon").click();

    });

    //完成下一页功能
    $("#connextPage").click(function () {
        //pageNum是字符串类型，字符串无减的操作，而+会被字符串认为是连接操作
        var pages = $("#conpages").html();
        var pageNum = $("#conpageNum").val();
        if(pageNum*1>pages*1){
            $("#conpageNum").val(pages);
        }else{
            $("#conpageNum").val(pageNum * 1 + 1);
        }
            
        //点击查询按钮
        $("#searchbtncon").click();
    });

    //完成Go按钮功能
    $("#congoBtn").click(function () {
        //获取跳转页数，转成数字
        var gopage = $("#congopage").val() * 1;
        //判断用户输入
        //页面上用户输入东西都是字符串
        if (gopage < 1) {
            gopage = 1;
            $("#congopage").val(1);
        }
        //获取总页数
        var pages = $("#conpages").html();
        if (gopage > pages * 1) {
            gopage = pages;
        }
        $("#conpageNum").val(gopage);
        $("#consearchBtn").click();
    });
    
    $("#addconbtn").click(function(){
        $("#addcon").show();
    });
});


$(function(){
    $.post("/platform/seaequip", $("[name]").serialize(), function (data){
            if (data && data.size > 0) {
               for (var i = 0; i < data.list.length; i++) {
                    var equipinfo = data.list[i];
                    $("<option></option>").html(equipinfo.eqid).val(equipinfo.eqid).appendTo("#equipin");
                }
            }

        },"json");  
    $.post("/platform/searchpro","pageNum=1&pageSize=3",function(data){
            if (data && data.size > 0) {
               for (var i = 0; i < data.list.length; i++) {
                    var proinfo = data.list[i];
                    $("<option></option>").html(proinfo.proname).val(proinfo.proid).appendTo("#proidin");
                }
            }

        },"json"); 
     $("#checkcon").unbind();
     $("#addconbtn").click(function(){
        $("#addcon").show();
        $("#concondition").hide();
        $("#condiv").hide();
        $("#conpagectrl").hide();
        $("#checkcon").click(function(){
        
            
            var equid=     $("#equipin").val();
            var productid= $("#proidin").val();
            var yield1=    $("#yieldin").val();
            alert("就这一下"); 
             if( !productid){
                 alert("产品id空了"); 
             }
             if( !equid){
                 alert("设备ID空了"); 
             }
            if( !yield1){
                 alert("产能空了"); 
             }
            $.post("/platform/addcon","proid="+productid+"&eqid="+equid+"&yield="+yield1,function(data){
            alert("进来了吗"); 
            if(data=="true"){
                $("#equipin").val(0);
                $("#proidin").val(0);
                $("#yieldin").val(0);
                $("#addcon").hide();
                $("#searchtrackbtn").click();
            }
            else{
                alert("完成添加失败")
                }
            },"text");
         }); 
     });
    
});


