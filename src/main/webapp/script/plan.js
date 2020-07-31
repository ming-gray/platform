//添加计划按钮绑定点击事件
$(function(){
	$("#addBtn").click(function(){
		
				window.location.href = "/platform/addplan.html";
	});
});
//对计划编号下拉列表的操作
$(document).ready(function(){
	$.post("/platform/initplan",null,function(data){
		if(data){
			for(var i=0;i<data.length;i++){
				var plan = data[i];
				//动态生成页面元素
            	$("<option></option>").html(plan.planid).val(plan.planid).appendTo("#selplan");
            	$("<option></option>").html(plan.planstate).val(plan.planstate).appendTo("#selpstate");
			}
		}
	},"json");
	$.post("/platform/initorder",null,function(data){
		if(data){
			for(var i=0;i<data.length;i++){
				var order = data[i];
				//动态生成页面元素
				$("<option></option>").html(order.ordid).val(order.ordid).appendTo("#selordid");
			}
		}
	},"json");
	$.post("/platform/initproduct",null,function(data){
		if(data){
			for(var i=0;i<data.length;i++){
				var pro = data[i];
				//动态生成页面元素
				$("<option></option>").html(pro.proid).val(pro.proid).appendTo("#selproid");
			}
		}
	},"json");
	//2.给计划编号下拉列表绑定change事件
	$("#selplan").change(function(){
		var planid = $(this).val();
		$("#selordid option:gt(0)").remove();
			$.post("/platform/getordid","planid="+planid,function(data){
				if(data){
					for(var i=0;i<data.length;i++){
						var plan1 = data[i];
						$("<option></option>").html(plan1.ordid).val(plan1.ordid).appendTo("#selordid");
					}
				}
			},"json");
		$("#selproid option:gt(0)").remove();
			$.post("/platform/getproid","planid="+planid,function(data){
				if(data){
					for(var i=0;i<data.length;i++){
						var plan2 = data[i];
						$("<option></option>").html(plan2.proid).appendTo("#selproid");
					}
				}
			},"json");
	});

});


//分页查询的功能实现
$(function(){
	//给显示的查询按钮绑定点击事件
	$("#searchBtnShow").click(function(){
		$("#pageNum").val(1);
		$("#searchBtn").click();
	});
	
	// 给隐藏查询按钮绑定点击事件
	$("#searchBtn").click(function(){
		// 发送Ajax请求
		$.post("/platform/searchplan",$("[name]").serialize(),function(data){
			if(data && data.size>0){
				// 1.动态添加信息
				// 添加总记录条数
				$("#total").html(data.total);
				// 添加总页数
				$("#pages").html(data.pages);
				// 添加当前页数
				$("#curpage").html(data.pageNum);
				
				// 2.添加记录
				// 先清空之前的查询记录，删除原有记录行
				$("#resulttable tr:gt(0)").remove();
				//循环遍历结果集，添加数据行
				for(var i=0;i< data.list.length;i++){
					var planinfo = data.list[i];
					//创建一行，生成tr元素  tr代表行
					var oTr = $("<tr></tr>");
					// 生成单元格,写入数据，放到tr中
					$("<td></td>").html(i+1+(data.pageNum*1-1)*data.pageSize).appendTo(oTr);
					$("<td></td>").html(planinfo.planid).appendTo(oTr);
					$("<td></td>").html(planinfo.ordid).appendTo(oTr);
					$("<td></td>").html(planinfo.proid).appendTo(oTr);
					$("<td></td>").html(planinfo.plancount).appendTo(oTr);
				    $("<td></td>").html(new Date(planinfo.ddl).format("yyyy-MM-dd")).appendTo(oTr);
					$("<td></td>").html(new Date(planinfo.plansttime).format("yyyy-MM-dd")).appendTo(oTr);
					$("<td></td>").html(new Date(planinfo.planentime).format("yyyy-MM-dd")).appendTo(oTr);
				    if(planinfo.planstate*1 == 10) {$("<td></td>").html("未启动").appendTo(oTr);}
				    else if(planinfo.planstate*1 == 20) {$("<td></td>").html("已启动").appendTo(oTr);}
				    else if(planinfo.planstate*1 == 30) {$("<td></td>").html("已完成").appendTo(oTr);}
					//$("<td></td>").html(planinfo.planstate).appendTo(oTr);
					// 将te放入表格中
					oTr.appendTo("#resulttable");
					//动态生成删除按钮
					var oTd=$("<td></td>").appendTo(oTr);
						$("<input type='button' value='删除''>'").click(function(){
							//进行二次确认
							var isOK=confirm("请再次确认要删除此计划");
							//真正删除产品
							if(isOK){
								var planid=$(this).parent().parent().find("td:eq(1)").html();
								//发ajax请求删除数据
								$.post("/platform/delplan","planid="+planid,function(data2){
									if(data2 == "true"){
										//删除成功
										alert("删除成功");
										$("#searchBtn").click();
									}else{
										//删除失败
										alert("删除失败,请重试");
									}
								},"text")
								
							}
						}).appendTo(oTd);
	//////////////////////生成修改按钮并添加到单元格中
		         if(planinfo.planstate==10){
							$("<input type='button' value='修改'>").click(function(){
         //////////////////////////////////修改
								if($(this).val() == "修改"){
								//1.将用户名变为可编辑状态
									var oTd1 = $(this).parent().parent().find("td:eq(1)");
									var planid = oTd1.html();
									var  oTd2=$(this).parent().parent().find("td:eq(2)");
									var ordid = oTd2.html();
									var  oTd3=$(this).parent().parent().find("td:eq(3)");
									var proid = oTd3.html();
									//数量
									var oTd4 =$(this).parent().parent().find("td:eq(4)");
									var plancount =oTd4.html();
									oTd4.empty();
									$("<input type='text'>").css("width","50px").val(plancount).appendTo(oTd4);
								    //交货日期
									var oTd5 =$(this).parent().parent().find("td:eq(5)");
									var ddl =oTd5.html();
									oTd5.empty();
									$("<input type='date'>").css("width","100px").val(ddl).appendTo(oTd5);
									//计划开始日期
									var oTd6 =$(this).parent().parent().find("td:eq(6)");
									var plansttime =oTd6.html();
									oTd6.empty();
									$("<input type='date'>").css("width","100px").val(plansttime).appendTo(oTd6);
									//计划结束日期
									var oTd7 =$(this).parent().parent().find("td:eq(7)");
									var planentime =oTd7.html();
									oTd7.empty();
									$("<input type='date'>").css("width","100px").val(plansttime).appendTo(oTd7);
									var oTd8 =$(this).parent().parent().find("td:eq(8)");
									var planentime =oTd8.html();
	  ////////////////////////////////3.将按钮变为确定状态
									$(this).val("确定");	
									var oTd = $(this).parent();
									$("<input type='button' value='取消修改'>").click(function(){
										$("#searchBtn").click();
									}).appendTo(oTd);
								}else if($(this).val() == "确定"){
								//1.页面认证
									var oText5 = $(this).parent().parent().find("td:eq(5) input");
									var ddl = oText5.val();
									if(!ddl){
										alert("请填写交货日期");
										oText5.focus();
										return;
									}
								 	var oText4 = $(this).parent().parent().find("td:eq(4) input");
									var plancount = oText4.val();
									var oText6 = $(this).parent().parent().find("td:eq(6) input");
									var plansttime = oText6.val();
									var oText7 = $(this).parent().parent().find("td:eq(7) input");
									var planentime = oText7.val();
									var planid = $(this).parent().parent().find("td:eq(1)").html();
									var ordid =$(this).parent().parent().find("td:eq(2)").html();
									var proid =$(this).parent().parent().find("td:eq(3)").html();
								    var planstate =$(this).parent().parent().find("td:eq(8)").html();
							//////////状态和编号转化		
									if(planstate == "未启动") var state=10;
									else if	(planstate == "已启动") var state=20;
									else if	(planstate == "已完成") var state=30;
									var oBtn = $(this);
									//2.发送ajax请求进行修改
									//"planid="+planid+"&plancount="+plancount+"&ddl="+ddl+"&plansttime="+plansttime+"&planentime="+planentime+"&planstate="+planstate
									$.post("/platform/modplan","planid="+planid+"&ordid="+ordid+"&proid="+proid+"&plancount="+plancount+"&ddl="+ddl+"&plansttime="+plansttime+"&planentime="+planentime+"&planstate="+state,function(data){
									if(data == "true"){
										//修改成功
											var oTd4=oBtn.parent().parent().find("td:eq(4)");
											var oText4 =oTd4.find("input");
											var plancount=oText4.val();
											oTd4.empty();
											oTd4.html(plancount);
											var oTd5=oBtn.parent().parent().find("td:eq(5)");
											var oText5 =oTd5.find("input");
											var ddl=oText5.val();
											oTd5.empty();
											oTd5.html(ddl);
											var oTd6=oBtn.parent().parent().find("td:eq(6)");
											var oText6 =oTd6.find("input");
											var plansttime=oText6.val();
											oTd6.empty();
											oTd6.html(plansttime);
											var oTd7=oBtn.parent().parent().find("td:eq(7)");
											var oText7 =oTd7.find("input");
											var planentime=oText7.val();
											oTd7.empty();
											oTd7.html(planentime);
										//3.将当前按钮变成修改按钮
											oBtn.val("修改");
										//4.提示信息
											alert("修改成功");
											$("#searchBtn").click();
										}else{
											//修改失败
											alert("修改失败");
											$("#searchBtn").click();
										}
									},"text");
								}
							}).appendTo(oTd);	
						}
		         
		 //////////////////////////////////////
		                if(planinfo.planstate==10){
		                	$("<input type='button' value='启动 ''>'").click(function(){
								var planid=$(this).parent().parent().find("td:eq(1)").html();
								//发ajax请求删除数据
								$.post("/platform/startplan","planid="+planid,function(data){
									if(data == "true"){
										//删除成功
										alert("启动成功");
										$("#searchBtn").click();
									}else{
										//删除失败
										alert("启动失败,请重试");
									}
								},"text");
							
						}).appendTo(oTd);
		                }
		                
						
				}
				
				// 3.页面的控制
				// 结果表格和分页控制按钮的显示
				   $("#resultdiv").show();
				   $("#pagectrl").show();
				//上一页和下一页按钮的控制
				   if(data.isFirstPage){
					   // 当前是第一页
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
	//上一页功能
	$("#prePage").click(function(){
		//将页面上隐藏的pageNum的值-1
		//val()可以获取值，也可以赋值
		var pageNum = $("#pageNum").val();
		$("#pageNum").val(pageNum-1);
		//再进行一次查询操作,即点击一次查询按钮
		//click同val类似，给function为绑定事件，不给则认为进行一次点击操作
		$("#searchBtn").click();
		
	});
	
	//完成下一页功能
	$("#nextPage").click(function(){
		//pageNum是字符串类型，字符串无减的操作，而+会被字符串认为是连接操作
		var pageNum = $("#pageNum").val();
		$("#pageNum").val(pageNum*1+1);
		//点击查询按钮
		$("#searchBtn").click();
	});
	
	//完成Go按钮功能
	$("#goBtn").click(function(){
		//获取跳转页数，转成数字
		var gopage = $("#gopage").val()*1;
		//判断用户输入
			//页面上用户输入东西都是字符串
			if(gopage<1){
				gopage = 1;
			}
			//获取总页数
			var pages = $("#pages").html();
			if(gopage> pages*1){
				gopage = pages;
			}
			$("#pageNum").val(gopage);
			$("#searchBtn").click();
});
});