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
            	$("<option></option>").html(plan.ordid).val(plan.ordid).appendTo("#selordid");
            	$("<option></option>").html(plan.proid).val(plan.proid).appendTo("#selproid");
            	$("<option></option>").html(plan.planstate).val(plan.planstate).appendTo("#selpstate");
			}
		}
	},"json");
	/*//2.给计划编号下拉列表绑定change事件
	$("#selplan").change(function(){
		//获取当前用户选择的选项的value(planid)
		//select控件的value值，就是当前选中选项对象的value值
		var planid = $(this).val();
		$("#selcity option:gt(0)").remove();
			$.post("/platform/getordid","planid="+planid,function(data){
				if(data){
					for(var i=0;i<data.length;i++){
						var plan1 = data[i];
						$("<option></option>").html(plan1.ordid).val(plan1.proid).appendTo("#selordid");
					}
				}
			},"json");
	//		$("#selordid").attr("disabled",false);
			$.post("/platform/getproid","planid="+planid,function(data){
				if(data){
					for(var i=0;i<data.length;i++){
						var plan2 = data[i];
						$("<option></option>").html(plan2.proid).appendTo("#selproid");
					}
				}
			},"json");
	//		$("#selproid").attr("disabled",false);
	//	}
	});*/

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
					$("<td></td>").html(i+1).appendTo(oTr);
					$("<td></td>").html(planinfo.planid).appendTo(oTr);
					$("<td></td>").html(planinfo.ordid).appendTo(oTr);
					$("<td></td>").html(planinfo.proid).appendTo(oTr);
					$("<td></td>").html(planinfo.plancount).appendTo(oTr);
				    $("<td></td>").html(new Date(planinfo.ddl).format("yyyy-MM-dd")).appendTo(oTr);
					$("<td></td>").html(new Date(planinfo.plansttime).format("yyyy-MM-dd")).appendTo(oTr);
					$("<td></td>").html(new Date(planinfo.planentime).format("yyyy-MM-dd")).appendTo(oTr);
					$("<td></td>").html(planinfo.planstate).appendTo(oTr);
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
								$.post("/platform/delplan","planid="+planid,function(data){
									if(data == "true"){
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
					
					$("<input type='button' value='修改''>'").click(function(){
					//		window.location.href = "/platform/modplan.html";
						}).appendTo(oTd);
				}
				
				// 3.页面的控制
				// 结果表格和分页控制按钮的显示
				   $("#resultdiv").show();
				   $("#pagectrl").show();
				//上一页和下一页按钮的控制
				   if(data.isFirstPage){
					   // 当前是第一页
					   $("#prePage").attr("disabled",true);
				   }else{
					   $("#prePage").attr("disabled",false);
				   }
				   
				   if(data.isLastPage){
					   $("#nextPage").attr("disabled",true);
				   }else{
					   $("#nextPage").attr("disabled",false);
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