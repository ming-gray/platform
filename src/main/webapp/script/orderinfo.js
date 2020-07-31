//产品下拉框
$(function(){
	$.post("/platform/initpro", null, function(data){
		if(data){
			for(var i=0; i<data.length; i++){
				var pro = data[i];
				$("<option></option>").html(pro.proname).val(pro.proid).appendTo("#proid");
				$("<option></option>").html(pro.proname).val(pro.proid).appendTo("#selproid");
			}
		}
	},"json");
	});
//新建订单功能
$(function(){
	$("#addordBtn").click(function(){
		if($(this).val() == "新建"){
			$("#pagectrl").hide();
			$("#resultdiv").hide();
			$("#turntoplan").hide();
			$("#adddiv").show();		
			$(this).val("确定");
			$("#ordsource").val("");
			$("#proid").val("");
			$("#proordnum").val("");
			$("#orddl").val("");
			//$(this).hide();
			//$("#confirmaddBtn").show();
		}else if($(this).val() == "确定"){
			oBtn2=$(this);
			var ordsource=$("#ordsource").val();
			var proid=$("#proid").val();
			var proordnum=$("#proordnum").val();
			var orddl=$("#orddl").val();
			var ordid=0;
			var apc=0;
			var quacom=0;
			var	ordstate=10;
			if(!ordsource){
				alert("请输入订单来源");
				$("#ordsource").focus();
				return;
				}
			if(!proid){
				alert("请输入产品编号");
				return;
				}
			if(!proordnum){
				alert("请输入需要的产品数量");
				return;
				}
			if(!orddl){
				alert("请输入订单截止时间");
				return;
				}
			$.post("/platform/addorder","ordid="+ordid+"&ordsource="+ordsource+"&proid="+proid+"&proordnum="+proordnum+"&orddl="+orddl+"&ordstate="+ordstate+"&quacom="+quacom+"&apc="+apc,function(data){
				if(data=="true"){
					alert("订单添加成功");
					oBtn2.val("新建");
					$("#ordsource").empty();
					//$("#proid").empty();
					$("#proordnum").empty();
					//$("#orddl").empty();
					$("#adddiv").hide();
				}else{
					alert("输入的订单号冲突，请重试");
					$("#ordid").focus();
					$("#ordid").select;
				}
			},"text");	
		}			
	});
});
//分页查询的功能实现
$(function(){
	$("#searchBtnShow").click(function(){
		$("#addordBtn").val("新建");
		$("#adddiv").hide();
		$("#turntoplan").hide();
		$("#pagectrl").show();
		$("#resultdiv").show();
		$("#pageNum").val(1);
		$("#searchBtn").click();
	});
	$("#goRange").change(function(){
		var num = $(this).val();
		$("#gopage").val(num);
	});
	//给查询按钮绑定点击事件
	$("#searchBtn").click(function(){
		//发送ajax请求
		$.post("/platform/seaorder",$("[name]").serialize(),function(data){
			if(data && data.size>0){
				//1.动态添加信息
				$("#total").html(data.total);
				$("#pages").html(data.pages);
				$("#curpage").html(data.pageNum);
				$("#goRange").attr("max",data.pages);
				//2.添加记录
				$("#resulttable tr:gt(0)").remove();
				for(var i=0;i<data.list.length;i++){
					var orderinfo = data.list[i];
					var oTr = $("<tr></tr>");
					$("<td></td>").html(orderinfo.ordid).appendTo(oTr);
					$("<td></td>").html(orderinfo.ordsource).appendTo(oTr);
					$("<td></td>").html(orderinfo.proid).appendTo(oTr);
					$("<td></td>").html(orderinfo.proordnum).appendTo(oTr);
					$("<td></td>").html(new Date(orderinfo.orddl).format("yyyy-MM-dd")).appendTo(oTr);
					if(orderinfo.ordstate*1 == 10)   {$("<td></td>").html("未接单").appendTo(oTr);}
                    else if(orderinfo.ordstate*1==20){$("<td></td>").html("已接单").appendTo(oTr);}
					else if(orderinfo.ordstate*1==30){$("<td></td>").html("已拒单").appendTo(oTr);}
					else if(orderinfo.ordstate*1==40){$("<td></td>").html("生产中").appendTo(oTr);}
					else if(orderinfo.ordstate*1==50){$("<td></td>").html("已完成").appendTo(oTr);}
					//$("<td></td>").html(orderinfo.ordstate).appendTo(oTr);
					$("<td></td>").html(orderinfo.quacom).appendTo(oTr);
					$("<td></td>").html(orderinfo.apc).appendTo(oTr);
		/////////////生成删除按钮
					var oTd=$("<td></td>").appendTo(oTr);
					$("<input type='button' value='删除''>'").click(function(){
						var isOK=confirm("您是真的要删除吗？");
						if(isOK){
								var ordid=$(this).parent().parent().find("td:eq(0)").html();
								$.post("/platform/delorder","ordid="+ordid,function(data){
									if(data == "true"){
										alert("删除成功");
										$("#searchBtn").click();
									}else{
										alert("删除失败请重试");
									}
								},"text")
							}
					}).appendTo(oTd);
		/////////////删除按钮结束			
		//////////////添加修改按钮
					$("<input type='button' value='修改'>").click(function(){
						//////////状态和编号转化	
						var states=$(this).parent().parent().find("td:eq(5)").html();		
							if(states == "未接单"){var ordstate=10;} 
							else if	(states == "已接单"){var ordstate=20;} 
							else if	(states == "已拒单"){var ordstate=30;} 
							else if	(states == "生产中"){var ordstate=40;} 
							else if	(states == "已完成"){var ordstate=50;} 
						/////////////////////
						if(ordstate==30&&$(this).val() == "修改"){
							alert("已拒单订单不可修改");
							$(this).val("不可修改");
						}else if(ordstate==50&&$(this).val() == "修改"){
							alert("已完成订单不可修改");
							$(this).val("不可修改");
						}else if((ordstate==20&&$(this).val() == "修改")||(ordstate==40&&$(this).val() == "修改")){
							var isOK=confirm("您是真的要修改吗？");
							if(isOK&&$(this).val() == "修改"){
								//中途修改取消
								var oTd4=$(this).parent().parent().find("td:eq(8)");
								$("<input type='button' value='放弃修改' >").click(function(){
									$("#searchBtn").click();
								}).appendTo(oTd4);
								
								var oTd2 =$(this).parent().parent().find("td:eq(1)");
								var ordsource =oTd2.html();
								oTd2.empty();
								$("<input type='text'>").css("width","50px").val(ordsource).appendTo(oTd2);	
								$(this).val("确定");
							}
						}else if(($(this).val() == "确定"&&ordstate==20)||($(this).val() == "确定"&&ordstate==40)){
							var oText2 = $(this).parent().parent().find("td:eq(1) input");
							var ordsource = oText2.val();
							if(!ordsource){
								alert("请输入订单来源");
								oText2.focus();
								return;
							}
							var ordid = $(this).parent().parent().find("td:eq(0)").html();
							var proid = $(this).parent().parent().find("td:eq(2)").html();
							var proordnum = $(this).parent().parent().find("td:eq(3)").html();
							var orddl = $(this).parent().parent().find("td:eq(4)").html();
							var states=$(this).parent().parent().find("td:eq(5)").html();
					//////////状态和编号转化		
							if(states == "未接单") var ordstate=10;
							else if	(states == "已接单") var ordstate=20;
							else if	(states == "已拒单") var ordstate=30;
							else if	(states == "生产中") var ordstate=40;
							else if	(states == "已完成") var ordstate=50;
						/////////////////////
							var quacom = $(this).parent().parent().find("td:eq(6)").html();
							var apc = $(this).parent().parent().find("td:eq(7)").html();
							var oBtn = $(this);
							$.post("/platform/modorder","ordid="+ordid+"&ordsource="+ordsource+"&proid="+proid+"&proordnum="+proordnum+"&orddl="+orddl+"&ordstate="+ordstate+"&quacom="+quacom+"&apc="+apc,function(data){
								if(data == "true"){
									var oTd2=oBtn.parent().parent().find("td:eq(1)");
									var oText2 =oTd2.find("input");
									var ordsource=oText2.val();	
									oTd2.empty();
									oTd2.html(ordsource);
									oBtn.val("修改");
									alert("修改成功");
									$("#searchBtn").click();
								}else{
									alert("输入信息冲突，请重试");
								}
							},"text");
						}else if(ordstate==10&&$(this).val() == "修改"){
							var isOK=confirm("您是真的要修改吗？");
							if(isOK&&$(this).val() == "修改"){
								//中途修改取消
								var oTd4=$(this).parent().parent().find("td:eq(8)");
								$("<input type='button' value='放弃修改''>'").click(function(){
									$("#searchBtn").click();
								}).appendTo(oTd4);

								var oTd2 =$(this).parent().parent().find("td:eq(1)");
								var ordsource =oTd2.html();
								oTd2.empty();
								$("<input type='text'>").css("width","50px").val(ordsource).appendTo(oTd2);
								
								var oTd3 =$(this).parent().parent().find("td:eq(2)");
								var proid =oTd3.html();
								oTd3.empty();
								$("<input type='number' min='0'	onkeyup='this.value=this.value.replace(/\D/g,'')' onafterpaste='this.value=this.value.replace(/\D/g,'')'>").val(proid).appendTo(oTd3);
								
								var oTd4 =$(this).parent().parent().find("td:eq(3)");
								var proordnum =oTd4.html();
								oTd4.empty();
								$("<input type='number' min='0' onkeyup='this.value=this.value.replace(/\D/g,'')' onafterpaste='this.value=this.value.replace(/\D/g,'')'>").css("width","50px").val(ordsource).appendTo(oTd4);
								
								var oTd5 =$(this).parent().parent().find("td:eq(4)");
								var orddl =oTd5.html();
								oTd5.empty();
								$("<input type='date'>").css("width","100px").val(ordsource).appendTo(oTd5);
								$(this).val("确定");	
							}
						}else if($(this).val() == "确定"&&ordstate==10){
							var oText2 = $(this).parent().parent().find("td:eq(1) input");
							var ordsource = oText2.val();
							if(!ordsource){
								alert("请输入订单来源");
								oText2.focus();
								return;
							}
							var oText3 = $(this).parent().parent().find("td:eq(2) input");
							var proid = oText3.val();
							if(!proid){
								alert("请输入产品编号");
								oText3.focus();
								return;
							}
							var oText4 = $(this).parent().parent().find("td:eq(3) input");
							var proordnum = oText4.val();
							if(!proordnum){
								alert("请输入订单数量");
								oText4.focus();
								return;
							}
							var oText5 = $(this).parent().parent().find("td:eq(4) input");
							var orddl = oText5.val();
							if(!orddl){
								alert("请输入订单截止日期");
								oText5.focus();
								return;
							}
							var ordid = $(this).parent().parent().find("td:eq(0)").html();
							//var ordstate=$(this).parent().parent().find("td:eq(5)").html();
							var states=$(this).parent().parent().find("td:eq(5)").html();
					//////////状态和编号转化		
							if(states == "未接单") var ordstate=10;
							else if	(states == "已接单") var ordstate=20;
							else if	(states == "已拒单") var ordstate=30;
							else if	(states == "生产中") var ordstate=40;
							else if	(states == "已完成") var ordstate=50;
						/////////////////////
							var quacom = $(this).parent().parent().find("td:eq(6)").html();
							var apc = $(this).parent().parent().find("td:eq(7)").html();
							var oBtn = $(this);
							$.post("/platform/modorder","ordid="+ordid+"&ordsource="+ordsource+"&proid="+proid+"&proordnum="+proordnum+"&orddl="+orddl+"&ordstate="+ordstate+"&quacom="+quacom+"&apc="+apc,function(data){
								if(data == "true"){
								//修改成功
									var oTd2=oBtn.parent().parent().find("td:eq(1)");
									var oText2 =oTd2.find("input");
									var ordsource=oText2.val();	
									oTd2.empty();
									oTd2.html(ordsource);
									var oTd3=oBtn.parent().parent().find("td:eq(2)");
									var oText3 =oTd3.find("input");
									var proid=oText3.val();
									oTd3.empty();
									oTd3.html(proid);
									var oTd4=oBtn.parent().parent().find("td:eq(3)");
									var oText4 =oTd4.find("input");
									var proordnum=oText4.val();
									oTd4.empty();
									oTd4.html(proordnum);
									var oTd5=oBtn.parent().parent().find("td:eq(4)");
									var oText5 =oTd5.find("input");
									var orddl=oText5.val();
									oTd5.empty();
									oTd5.html(new Date(orddl).format("yyyy-MM-dd"));
									oBtn.val("修改");
									alert("修改成功");
									$("#searchBtn").click();
								}else{
									alert("请输入有效值，请重试");
								}
							},"text");
						}					
					}).appendTo(oTd);
		//////////////修改按钮添加结束
		/////////////动态生成状态改变按钮
				$("<input type='button' value='更改状态''>'").click(function(){
					var oBtn = $(this)
					var ordid = $(this).parent().parent().find("td:eq(0)").html();
					var ordsource = $(this).parent().parent().find("td:eq(1)").html();
					var proid = $(this).parent().parent().find("td:eq(2)").html();
					var proordnum = $(this).parent().parent().find("td:eq(3)").html();
					var orddl = $(this).parent().parent().find("td:eq(4)").html();
					//var ordstate=$(this).parent().parent().find("td:eq(5)").html();
					var states=$(this).parent().parent().find("td:eq(5)").html();
					//////////状态和编号转化		
							if(states == "未接单") var ordstate=10;
							else if	(states == "已接单") var ordstate=20;
							else if	(states == "已拒单") var ordstate=30;
							else if	(states == "生产中") var ordstate=40;
							else if	(states == "已完成") var ordstate=50;
						/////////////////////
					var quacom = $(this).parent().parent().find("td:eq(6)").html();
					var apc = $(this).parent().parent().find("td:eq(7)").html();
					if($(this).val() == "更改状态"){
						if(ordstate == 10){
							oBtn.val("接单");
							//动态生成拒单按钮
							var oTd4=$(this).parent().parent().find("td:eq(8)");
							$("<input type='button' value='拒单''>'").click(function(){
								var apc = $(this).parent().parent().find("td:eq(7)").html();
								if($(this).val() == "拒单"&&ordstate == 10){
									$.post("/platform/reforder","ordid="+ordid+"&ordsource="+ordsource+"&proid="+proid+"&proordnum="+proordnum+"&orddl="+orddl+"&ordstate="+ordstate+"&quacom="+quacom+"&apc="+apc,function(data){
										var isOK=confirm("您是真的要拒单吗？");
										if(isOK){
											if(data == "true"){
												alert("拒单成功");
												$("#searchBtn").click();
											}else{
												alert("当前状态无法拒单");
												oBtn2.val("无法修改状态");
											}
										}
									},"text");
								}else{
									alert("当前状态无法拒单");
									oBtn2.val("无法拒单");
								}
							}).appendTo(oTd4);
						}else if(ordstate == 20){
							oBtn.val("转成生产计划");
						}else if(ordstate == 40){
							oBtn.val("订单完成");
						}else{
							oBtn.val("无法修改状态");
						}	
					}else if($(this).val() == "接单"){
						var isOK=confirm("您是真的要接单吗？");
						if(isOK){
							$.post("/platform/accorder","ordid="+ordid+"&ordsource="+ordsource+"&proid="+proid+"&proordnum="+proordnum+"&orddl="+orddl+"&ordstate="+ordstate+"&quacom="+quacom+"&apc="+apc,function(data){
								if(data == "true"){
									alert("接单成功");
									$("#searchBtn").click();
								}else{
									alert("产能不足，接单失败");
									$("#searchBtn").click();
								}
							},"text");
						}
					}else if($(this).val() == "订单完成"){
						var isOK=confirm("您是真的要完成订单吗？");
						if(isOK){
							$.post("/platform/finorder","ordid="+ordid+"&ordsource="+ordsource+"&proid="+proid+"&proordnum="+proordnum+"&orddl="+orddl+"&ordstate="+ordstate+"&quacom="+quacom+"&apc="+apc,function(data){
								if(data == "true"){
									alert("订单已完成");
									$("#searchBtn").click();
								}else{
									alert("订单未完成，更改失败");
								}
							},"text");
						}
					}else if($(this).val() == "转成生产计划"){
						oBtn2=$(this);
						var isOK=confirm("您是真的要转成生产计划吗？");
						if(isOK){
							/////////////转成生产计划
							var ordid = $(this).parent().parent().find("td:eq(0)").html();
							var proid = $(this).parent().parent().find("td:eq(2)").html();
							$("#turntoplan").click();
							$("#checkplan").click(function(){
								var ddl=$("#ddl").val();
	                       		var plansttime=$("#plansttime").val();
	                        	var planentime=$("#planentime").val();
								var planid=0;
								var plancount=proordnum;
								var planstate=10;	     
	                       		$.post("/platform/turntoplan","planid="+planid+"&ordid="+ordid+"&proid="+proid+"&plancount="+plancount+"&ddl="+ddl+"&plansttime="+plansttime+"&planentime="+planentime+"&planstate="+planstate,function(data){
									if(data=="true"){
	                        		$("#buttondown").click();
									alert("生成计划成功");
									$("#searchBtn").click();
									$("#checkplan").unbind("click")
	                            }else{
	                                   	alert("生成计划失败，请重试")
	                                  }
	                                    return ;
	                        	},"text");
						 		});  		
							////////////////转成生产计划结束
						}
					}
				}).appendTo(oTd);
		////////////状态改变按钮结束
					oTr.appendTo("#resulttable");
				}
				//3.页面的控制
				$("#resultdiv").show();
				$("#pagectrl").show();
				if(data.isFirstPage){
					$("#prePage").hide();
					$("#prePageSpan").show();
				}else{
					$("#prePage").show();
					$("#prePageSpan").hide();
				}
				if(data.isLastPage){
					$("#nextPage").hide();
					$("#nextPageSpan").show();
				}else{
					$("#nextPage").show();
					$("#nextPageSpan").hide();
				}
			}
			else{
				$("#resultdiv").hide();
				$("#pagectrl").hide();
				alert("没有查到数据");
				}
		},"json");
	});

});
//分页控制
$(function(){
	$("#prePage").click(function(){
		var pageNum =$("#pageNum").val();
		$("#pageNum").val(pageNum-1);
		$("#searchBtn").click();	
	});
	$("#nextPage").click(function(){
		var pageNum =$("#pageNum").val();
		$("#pageNum").val(pageNum*1+1);
		$("#searchBtn").click();
	});	
	$("#goBtn").click(function(){
		var gopage=$("#gopage").val()*1;
		if(gopage>0){
			var pages=$("#pages").html();
			if(gopage > pages*1){
				gopage =pages;
			}
            $("#pageNum").val(gopage);
			$("#searchBtn").click();
		}
    });
});