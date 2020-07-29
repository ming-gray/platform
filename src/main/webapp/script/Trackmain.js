
//track
$(function(){
    
    $("#searchtrack").click(function(){
        $("#track").show();
        $("#trackcondition").show();
       
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
                            
                $("<input type='button' value='完成报工' class='btn btn-primary' data-toggle='modal'>").click(function(){
                                
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
                                       return ;
                                   },"text");
                               }
                            }).appendTo(oTd);
                        
                        }
                        
                        if(Trackinfo.trackstate*1 != 20 ){
//                    class='btn btn-primary' data-toggle='modal' data-target='#myModal
                    $(" <input type='button' value='报工'  class='btn btn-primary' data-toggle='modal' data-target='#myModal'>").click(function(){
                                                        
                        var workid=$(this).parent().parent().find("td:eq(2)" ).html();   
//                            $("#myModal").show();
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
                                           $("#buttondown").click();
                                           $("#newquacount").val(0);
                                           $("#newdayworkcount").val(0);
                                           $("#newsttime").val();
                                           $("#newentime").val();
                                           $("#searchtrackbtn").click();
                                       }
                                       else{
                                           alert("完成报工失败")
                                       }
                                    return ;
                                   },"text");
                              });  
                        
                         }).appendTo(oTd);
                        
                        }
                        
                    $("<input type='button'value='删除'class='btn btn-primary' data-toggle='modal'>").click(function(){
                            
                           var isok =confirm("删除确认？");
                           if(isok){
                               //this表示当前点击的按钮
                              var id = $(this).parent().parent().find("td:eq(1)").html();
                              "userid是函数变量"
                               $.post("/platform/deltrack","id="+id,function(data){
                                   if(data=="true"){
                                       $("#searchtrackbtn").click();
                                   }
                                   else{
                                       alert("删除失败")
                                   }
                                   return ;
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
                        $("<td></td>").html(new Date(Daywork.sttime).format("yyyy-MM-dd")).appendTo(oTr);
                        $("<td></td>").html(new Date(Daywork.entime).format("yyyy-MM-dd")).appendTo(oTr);
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

