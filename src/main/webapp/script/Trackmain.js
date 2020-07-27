//track
$(function(){
    
    $("#searchtrack").click(function(){
        $("#track").show();
        $("#trackcondition").show();

        $("#work").hide();
        $("#workcondition").hide();
        $("#workdiv").hide();
        $("#workpagectrl").hide();
       
        $("#daywork").hide();
        $("#dayworkcondition").hide();
        $("#dayworkdiv").hide();
        $("#dayworkpagectrl").hide();
    });
    
                     
    $("#searchtrackbtn").click(function(){

            $.post("/platform/checktrack",$("[name]").serialize(),function(data){
                
                var pagenum  = $("#trackpageNum").val();
                var pagesize = $("#trackpageSize").val();
                
                if(data && data.size>0){
                    data.list=data.list.sort(function (a,b){
                        return a.trackstate*1>b.trackstate*1;
                    });
                    $("#tracktotal").html(data.total);
                    $("#trackpages").html(data.pages);
                    $("#trackcurpage").html(data.pageNum);
    //                $("goRange").attr("max",data.pages);
                    $("#tracktable tr:gt(0)").remove();
                    for(var i=0;i<data.list.length;i++){
                        var Trackinfo = data.list[i];
                        var oTr = $("<tr></tr>");
                        $("<td></td>").html(i+1+( pagenum*1-1)*pagesize).appendTo(oTr);
                        $("<td></td>").html(Trackinfo.id).appendTo(oTr);
                        $("<td></td>").html(Trackinfo.workid).appendTo(oTr);
                        $("<td></td>").html(Trackinfo.planid).appendTo(oTr);
                        $("<td></td>").html(Trackinfo.proid).appendTo(oTr);
                        if(Trackinfo.trackstate*1 == 10)   {$("<td></td>").html("进行中").appendTo(oTr);}
                        else if(Trackinfo.trackstate*1==20){$("<td></td>").html("已完成").appendTo(oTr);}
                        $("<td></td>").html(Trackinfo.workingCount).appendTo(oTr);
                        $("<td></td>").html(Trackinfo.qualifiedCount).appendTo(oTr);

                        
                        var oTd=$("<td></td>").appendTo(oTr);
                        
                        if(Trackinfo.workingCount*1<=Trackinfo.qualifiedCount*1 && Trackinfo.trackstate*1 != 20 ){
                            
                            $("<input type='button' value='完成报工'>").click(function(){
                                
                            var id=$(this).parent().parent().find("td:eq(1)").html();
                            var isok =confirm("确认完成报工");
                               
                                if(isok){
                                   //this表示当前点击的按钮
                                  "userid是函数变量"
                                   $.post("/platform/finishtrack","id= " + id ,function(data){
                                       
                                       if(data=="true"){
                                           $("#searchtrackbtn").click();
                                       }
                                       else{
                                           alert("完成报工失败")
                                       }
                                   },"text");
                               }
                            }).appendTo(oTd);
                        
                        }
                        
                        if(Trackinfo.trackstate*1 != 20 ){
                            
                        $("<input type='button' value='报工'>").click(function(){
                                                        
                        var workid=$(this).parent().parent().find("td:eq(2)" ).html();   
                            
                            $("#adddaywork").show();

                            $("#checkwork").click(function(){
                                var count=  $("#newdayworkcount").val();
                                var quacount=$("#newquacount").val();
                                var sttime=$("#newsttime").val();
                                var entime=$("#newentime").val();
                                 alert("就这一下"); 
//                                "workid="+workid+"&workingCount="+count+"&quaCount="+quacount
                                $.post("/platform/adddaiwork","workid="+workid+"&workingCount="+count+"&quaCount="+quacount+"&sttime="+sttime+"&entime="+entime,function(data){
                                       alert("进来了吗"); 
                                       if(data=="true"){
                                           $("#adddaywork").hide();
                                           $("#searchtrackbtn").click();
                                           $("#newquacount").val(0);
                                           $("#newdayworkcount").val(0);
                                       }
                                       else{
                                           alert("完成报工失败")
                                       }
                                   },"text");
                              });  
                         }).appendTo(oTd);
                        
                        }
                        
                        $("<input type='button' value='删除'>").click(function(){
                            
                           var isok =confirm("删除确认？");
                           if(isok){
                               //this表示当前点击的按钮
                              var id = $(this).parent().parent().find("td:eq(1)").html();
                              "userid是函数变量"
                               $.post("/mavendemo/deluser","id="+id,function(data){
                                   if(data=="true"){
                                       $("#searchtrackbtn").click();
                                   }
                                   else{
                                       alert("删除失败")
                                   }
                               },"text");
                           }
                        }).appendTo(oTd);
                        
                        
                        oTr.appendTo("#tracktable");
                        
                        
                        

                    }
                    $("#trackdiv").show();
                    $("#trackpagectrl").show();
                    //布尔型
                    if(data.isFirstPage){
                        //当前是第一页
                        $("#trackprePage").hide();
                        $("#trackprePageSpan").show();
                    }else{
                        $("#trackprePage").show();
                        $("#trackprePageSpan").hide();
                    }

                    if(data.isLastPage){
                        $("#tracknextPage").hide();
                        $("#tracknextPageSpan").show();

                    }
                    else{
                        $("#tracknextPage").show();
                        $("#tracknextPageSpan").hide();
                    }
                }
                else{
                    $("#trackdiv").hide();
                    $("#trackpagectrl").hide();
                    alert("没有查到数据");
                }
            },"json");
    });
    
    
    $("#trackprePage").click(function(){
        var pageNum=$("#trackpageNum").val();
        
        $("#trackpageNum").val(pageNum-1);
        
        $("#searchtrackbtn").click();
    });
    
    $("#tracknextPage").click(function(){
        var pageNum=$("#trackpageNum").val();
         
        $("#trackpageNum").val(pageNum*1+1);
         
        $("#searchtrackbtn").click();
    });
    
    $("#trackgoBtn").click(function(){
        var gopage=$("#trackgopage").val();
        
            if(gopage < 1){
                gopage = 1;
            }
            //页码大于最后一页
            var pages = $("#trackpages").html();
            //乘一转化为数据
            if(gopage > pages*1 ){
                gopage = pages;
            }      
            //将页面上用户隐藏的pageNum设定为gopage
            $("#trackpageNum").val(gopage);
            
            $("#searchtrackbtn").click();
        });
});

//daywork
$(function(){
    
   $("#searchdaywork").click(function(){
       
        $("#daywork").show();
        $("#dayworkcondition").show();

       
        $("#track").hide();
        $("#trackcondition").hide();
        $("#trackdiv").hide();
        $("#trackpagectrl").hide();
       
        $("#work").hide();
        $("#workcondition").hide();
        $("#workdiv").hide();
        $("#workpagectrl").hide();
    });
                                 
     $("#searchdayworkbtn").click(function(){
            $.post("/platform/checkdaiwork",$("[name]").serialize(),function(data){
                
                var pagenum  = $("#dayworkpageNum").val();
                var pagesize = $("#dayworkpageSize").val();
                
                if(data && data.size>0){

                    $("#dayworktotal").html(data.total);

                    $("#dayworkpages").html(data.pages);

                    $("#dayworkcurpage").html(data.pageNum);

    //                $("goRange").attr("max",data.pages);

                    $("#dayworktable tr:gt(0)").remove();

                    for(var i=0;i<data.list.length;i++){
                        var Daywork = data.list[i];

                        var oTr = $("<tr></tr>");

                        $("<td></td>").html(i+1+( pagenum*1-1)*pagesize).appendTo(oTr);
                        $("<td></td>").html(Daywork.workid).appendTo(oTr);
                        $("<td></td>").html(Daywork.eqid).appendTo(oTr);
                        $("<td></td>").html(new Date(Daywork.sttime).format("yyyy-MM-dd hh:mm:ss")).appendTo(oTr);
                        $("<td></td>").html(new Date(Daywork.entime).format("yyyy-MM-dd hh:mm:ss")).appendTo(oTr);
                        $("<td></td>").html(Daywork.workingCount).appendTo(oTr);
                        $("<td></td>").html(Daywork.quaCount).appendTo(oTr);
                        $("<td></td>").html(Daywork.unquaCount).appendTo(oTr);

//                        (new Date(Daywork.entime).format("yyyy-MM-dd hh:mm:ss")
                        //var oTd =$("<td></td>").appendTo(oTr);

                        oTr.appendTo("#dayworktable");

                    }
                    $("#dayworkdiv").show();
                    $("#dayworkpagectrl").show();
                    //布尔型
                    if(data.isFirstPage){
                        //当前是第一页
                        $("#dayworkprePage").hide();
                        $("#dayworkprePageSpan").show();
                    }else{
                        $("#dayworkprePage").show();
                        $("#dayworkprePageSpan").hide();
                    }

                    if(data.isLastPage){
                        $("#dayworknextPage").hide();
                        $("#dayworknextPageSpan").show();

                    }
                    else{
                        $("#dayworknextPage").show();
                        $("#dayworknextPageSpan").hide();
                    }
                }
                else{
                    $("#dayworktable").hide();
                    $("#dayworkpagectrl").hide();
                    alert("没有查到数据");
                }
            },"json");
    })
    
    
    
    $("#dayworkprePage").click(function(){
        var pageNum=$("#dayworkpageNum").val();
        
        $("#dayworkpageNum").val(pageNum-1);
        
        $("#searchdayworkbtn").click();
    });
    
    $("#dayworknextPage").click(function(){
        var pageNum=$("#dayworkpageNum").val();
         
        $("#dayworkpageNum").val(pageNum*1+1);
         
        $("#searchdayworkbtn").click();
    });
    
    $("#dayworkgoBtn").click(function(){

        var gopage=$("#dayworkgopage").val();
        
            if(gopage < 1){
                gopage = 1;
            }
            //页码大于最后一页
            var pages = $("#dayworkpages").html();
            //乘一转化为数据
            if(gopage > pages*1 ){
                gopage = pages;
            }      
            //将页面上用户隐藏的pageNum设定为gopage
            $("#dayworkpageNum").val(gopage);
            
            $("#searchdayworkbtn").click();
    });
    
    
    
});


//work
$(function(){
    
    $("#searchwork").click(function(){
        $("#work").show();
        $("#workcondition").show();
        
        
               
        $("#track").hide();
        $("#trackcondition").hide();
        $("#trackdiv").hide();
        $("#trackpagectrl").hide();
       
        $("#daywork").hide();
        $("#dayworkcondition").hide();
        $("#dayworkdiv").hide();
        $("#dayworkpagectrl").hide();
    });
    
       
                                 
    $("#searchworkbtn").click(function(){

            $.post("/platform/searchall",$("[name]").serialize(),function(data){
                var pagenum  = $("#workpageNum").val();
                var pagesize = $("#workpageSize").val();
                alert("进来了吗");
                if(data && data.size>0){
                    
                    $("#worktotal").html(data.total);
                    $("#workpages").html(data.pages);
                    $("#workcurpage").html(data.pageNum);
                    
                    $("#worktable tr:gt(0)").remove();
                    
                    for(var i=0;i<data.list.length;i++){
                        
                        var workinfo = data.list[i];
                        
                        var oTr = $("<tr></tr>");
                        
                        $("<td></td>").html(i+1+( pagenum*1-1)*pagesize).appendTo(oTr);
                        $("<td></td>").html(workinfo.workid).appendTo(oTr);
                        $("<td></td>").html(workinfo.workcount).appendTo(oTr);
                        $("<td></td>").html(workinfo.eqid).appendTo(oTr);
                        $("<td></td>").html(workinfo.planid).appendTo(oTr);
                        $("<td></td>").html(workinfo.proid).appendTo(oTr);
                        if(workinfo.workstate*1 == 10)   {$("<td></td>").html("未开始").appendTo(oTr);}
                        else if(workinfo.workstate*1==20){$("<td></td>").html("进行中").appendTo(oTr);}
                        else if(workinfo.workstate*1==30){$("<td></td>").html("已完成").appendTo(oTr);}
                        
                        var oTd =$("<td></td>").appendTo(oTr);
                            
                        if(workinfo.workstate * 1  == 10 ){

                            $("<input type='button' value='添加报工'>").click(function(){

                               var isok =confirm("确认添加");
                               if(isok){
                                   //this表示当前点击的按钮
                                  var workid = $(this).parent().parent().find("td:eq(1)").html();

                                   $.post("/platform/dotrack","workid="+workid,function(data){
                                       if(data=="true"){
                                           $("#msg").show().css("color","red").html("添加报工成功").fadeOut(3000);
                                           $("#searchworkbtn").click();
                                       }
                                       else{
                                           alert("添加失败")
                                       }
                                   },"text");
                               }
                            }).appendTo(oTd);

                        }
                    oTr.appendTo("#worktable");
            
                    }
                    $("#workdiv").show();
                    $("#workpagectrl").show();
                    //布尔型
                    if(data.isFirstPage){
                        //当前是第一页
                        $("#workprePage").hide();
                        $("#workprePageSpan").show();
                    }else{
                        $("#workprePage").show();
                        $("#workprePageSpan").hide();
                    }

                    if(data.isLastPage){
                        $("#worknextPage").hide();
                        $("#worknextPageSpan").show();

                    }
                    else{
                        $("#worknextPage").show();
                        $("#worknextPageSpan").hide();
                    }
                }
                else{
                    $("#workdiv").hide();
                    $("#workpagectrl").hide();
                    alert("没有查到数据");
                }
            },"json");
    });
    
    
    $("#workprePage").click(function(){
        var pageNum=$("#workpageNum").val();
        
        $("#workpageNum").val(pageNum-1);
        
        $("#searchworkbtn").click();
    });
    
    $("#worknextPage").click(function(){
        var pageNum=$("#workpageNum").val();
         
        $("#workpageNum").val(pageNum*1+1);
         
        $("#searchworkbtn").click();
    });
    
    $("#workgoBtn").click(function(){
        var gopage=$("#workgopage").val();
        
            if(gopage < 1){
                gopage = 1;
            }
            //页码大于最后一页
            var pages = $("#workpages").html();
            //乘一转化为数据
            if(gopage > pages*1 ){
                gopage = pages;
            }      
            //将页面上用户隐藏的pageNum设定为gopage
            $("#workpageNum").val(gopage);
            
            $("#searchworkbtn").click();
    });
});